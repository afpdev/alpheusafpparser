package com.mgz.performance;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.SFTypeID;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.xml.AfpJacksonXmlWriter;
import com.mgz.xml.AfpStreamingXmlWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class MnemonicSpeedTest {

    private static class BenchResult {
        Map<String, AtomicLong> jaxbTimings = new HashMap<>();
        Map<String, AtomicLong> jacksonTimings = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
    }

    private static class NullOutputStream extends OutputStream {
        @Override public void write(int b) {}
        @Override public void write(byte[] b, int off, int len) {}
    }

    @Test
    public void runMnemonicBenchmarks() throws Exception {
        File tempAfp = File.createTempFile("mnemonic_perf", ".afp");
        tempAfp.deleteOnExit();

        generateComprehensiveMnemonicAfp(tempAfp);

        BenchResult result = benchmarkAfpFile(tempAfp);

        printResultsTable(result);
    }

    private void generateComprehensiveMnemonicAfp(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Write 5 instances of each SFTypeID (Structured Fields) to keep it fast
            for (SFTypeID type : SFTypeID.values()) {
                if (type == SFTypeID.Undefined) continue;

                for (int i = 0; i < 5; i++) {
                    byte[] header = {
                        0x5A, 0x00, 0x10,
                        (byte)type.getSfClass().toByte(),
                        (byte)type.getSfType().toByte(),
                        (byte)type.getSfCategory().toByte(),
                        0x00, 0x00, 0x00
                    };
                    fos.write(header);
                    fos.write(new byte[8]);
                }
            }
        }
    }

    private BenchResult benchmarkAfpFile(File file) throws Exception {
        BenchResult result = new BenchResult();
        runPass(file, false, result);
        runPass(file, true, result);
        return result;
    }

    private void runPass(File file, boolean useJackson, BenchResult result) throws Exception {
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAFPFile(file);
        config.setEscalateParsingErrors(false);
        AFPParser parser = new AFPParser(config);

        try (AutoCloseable writer = useJackson
                ? new AfpJacksonXmlWriter(new NullOutputStream(), null)
                : new AfpStreamingXmlWriter(new NullOutputStream(), null)) {
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                String mnemonic = sf.getStructuredFieldIntroducer().getSFTypeID().name();
                if (!useJackson) {
                    result.counts.compute(mnemonic, (k, v) -> (v == null) ? 1 : v + 1);
                }

                long start = System.nanoTime();
                try {
                    if (useJackson) {
                        ((AfpJacksonXmlWriter)writer).writeField(sf);
                    } else {
                        ((AfpStreamingXmlWriter)writer).writeField(sf);
                    }
                    long end = System.nanoTime();
                    if (useJackson) {
                        result.jacksonTimings.computeIfAbsent(mnemonic, k -> new AtomicLong()).addAndGet(end - start);
                    } else {
                        result.jaxbTimings.computeIfAbsent(mnemonic, k -> new AtomicLong()).addAndGet(end - start);
                    }
                } catch (Exception e) {}
                sf.release();
            }
        } finally {
            parser.quitParsing();
        }
    }

    private void printResultsTable(BenchResult result) {
        System.out.println("\n### Mnemonic Performance Benchmarks\n");
        System.out.println("| Mnemonic | Count | JAXB (ns/node) | Jackson (ns/node) | Speedup |");
        System.out.println("| :--- | :---: | :---: | :---: | :---: |");

        Map<String, Integer> sortedCounts = new TreeMap<>(result.counts);
        for (Map.Entry<String, Integer> entry : sortedCounts.entrySet()) {
            String m = entry.getKey();
            int count = entry.getValue();
            long jaxb = result.jaxbTimings.getOrDefault(m, new AtomicLong(0)).get();
            long jackson = result.jacksonTimings.getOrDefault(m, new AtomicLong(0)).get();
            if (count == 0) continue;
            double jaxbAvg = (double)jaxb / count;
            double jacksonAvg = (double)jackson / count;
            double speedup = (jacksonAvg > 0) ? jaxbAvg / jacksonAvg : 0;
            System.out.printf("| %s | %d | %.0f | %.0f | %.2fx |\n", m, count, jaxbAvg, jacksonAvg, speedup);
        }
    }
}
