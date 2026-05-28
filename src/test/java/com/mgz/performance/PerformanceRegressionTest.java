package com.mgz.performance;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.SFTypeID;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.ParallelAfpConverter;
import com.mgz.util.MnemonicPerformanceMonitor;
import com.mgz.xml.AfpJacksonXmlWriter;
import com.mgz.xml.AfpStreamingXmlWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class PerformanceRegressionTest {

    @Test
    public void test10MBConversionPerformance() throws Exception {
        File tempAfp = File.createTempFile("perf_test", ".afp");
        tempAfp.deleteOnExit();

        generateLargeAfp(tempAfp, 10);

        long startTime = System.currentTimeMillis();

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAFPFile(tempAfp);
        AFPParser parser = new AFPParser(config);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 1024);
        try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(bos, null)) {
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                writer.writeField(sf);
                sf.release();
            }
        } finally {
            parser.quitParsing();
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("10MB AFP to XML conversion took: " + duration + "ms");

        assertTrue(duration < 2000, "Performance regression: 10MB conversion took too long: " + duration + "ms");
    }

    @Test
    public void testJackson10MBConversionPerformance() throws Exception {
        File tempAfp = File.createTempFile("perf_test_jackson", ".afp");
        tempAfp.deleteOnExit();

        generateLargeAfp(tempAfp, 10);

        long startTime = System.currentTimeMillis();

        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAFPFile(tempAfp);
        AFPParser parser = new AFPParser(config);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 1024);
        try (AfpJacksonXmlWriter writer = new AfpJacksonXmlWriter(bos, null)) {
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                writer.writeField(sf);
                sf.release();
            }
        } finally {
            parser.quitParsing();
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("10MB AFP to Jackson XML (Seq) took: " + duration + "ms");

        assertTrue(duration < 2000, "Performance regression: 10MB Jackson conversion took too long: " + duration + "ms");

        // Parallel 10MB
        long startPar = System.currentTimeMillis();
        AFPParserConfiguration parConfig = new AFPParserConfiguration();
        parConfig.setAFPFile(tempAfp);
        ParallelAfpConverter converter = new ParallelAfpConverter(parConfig, 0, true, null);
        converter.convert(new ByteArrayOutputStream());
        long durationPar = System.currentTimeMillis() - startPar;
        System.out.println("10MB AFP to Jackson XML (Parallel) took: " + durationPar + "ms");
    }

    @Test
    public void testJacksonPerformanceComparison() throws Exception {
        File tempAfp = File.createTempFile("perf_comparison", ".afp");
        tempAfp.deleteOnExit();
        generateLargeAfp(tempAfp, 20);

        runConversion(tempAfp, true);
        runConversion(tempAfp, false);

        long jaxbTotal = 0;
        long jacksonTotal = 0;
        int iterations = 3;

        for (int i = 0; i < iterations; i++) {
            jaxbTotal += runConversion(tempAfp, false).totalTime;
            jacksonTotal += runConversion(tempAfp, true).totalTime;
        }

        long jaxbAvg = jaxbTotal / iterations;
        long jacksonAvg = jacksonTotal / iterations;

        System.out.println("Average 20MB JAXB conversion: " + jaxbAvg + "ms");
        System.out.println("Average 20MB Jackson conversion: " + jacksonAvg + "ms");

        if (jacksonAvg < jaxbAvg) {
            System.out.printf("Jackson is %.2fx faster than JAXB\n", (double) jaxbAvg / jacksonAvg);
        } else {
            System.out.printf("Jackson is %.2fx slower than JAXB\n", (double) jacksonAvg / jaxbAvg);
        }
    }

    @Test
    public void test100MBParallelPerformance() throws Exception {
        File tempAfp = File.createTempFile("perf_test_100mb", ".afp");
        tempAfp.deleteOnExit();

        System.out.println("Generating 100MB AFP...");
        generateLargeAfp(tempAfp, 100);

        // Warm up
        for (int i = 0; i < 2; i++) {
            runConversion(tempAfp, true);
            AFPParserConfiguration config = new AFPParserConfiguration();
            config.setAFPFile(tempAfp);
            ParallelAfpConverter converter = new ParallelAfpConverter(config, 0, true, null);
            converter.convert(new ByteArrayOutputStream(1024*1024));
        }

        // Sequential JAXB
        long startJaxb = System.currentTimeMillis();
        runConversion(tempAfp, false);
        long durationJaxb = System.currentTimeMillis() - startJaxb;
        System.out.println("100MB AFP to JAXB (Seq) took: " + durationJaxb + "ms");

        // Sequential Jackson
        long startJackson = System.currentTimeMillis();
        runConversion(tempAfp, true);
        long durationJackson = System.currentTimeMillis() - startJackson;
        System.out.println("100MB AFP to Jackson (Seq) took: " + durationJackson + "ms");

        // Parallel Jackson
        long startParallel = System.currentTimeMillis();
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAFPFile(tempAfp);
        ParallelAfpConverter converter = new ParallelAfpConverter(config, 0, true, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(20 * 1024 * 1024);
        converter.convert(bos);
        long durationParallel = System.currentTimeMillis() - startParallel;
        System.out.println("100MB AFP to Jackson (Parallel) took: " + durationParallel + "ms");

        if (durationParallel < durationJackson) {
            System.out.printf("Parallel is %.2fx faster than Sequential Jackson\n", (double) durationJackson / durationParallel);
        } else {
            System.out.printf("Parallel is %.2fx slower than Sequential Jackson\n", (double) durationParallel / durationJackson);
        }
    }

    @Test
    public void testComprehensivePerformance() throws Exception {
        File tempAfp = File.createTempFile("perf_comprehensive", ".afp");
        tempAfp.deleteOnExit();
        generateComprehensiveAfp(tempAfp, 100);

        System.out.println("Comprehensive performance test (100 fields of every type):");

        // Warm-up phase - more iterations for better JIT
        for (int i = 0; i < 5; i++) {
            runConversion(tempAfp, false);
            runConversion(tempAfp, true);
        }

        ConversionResult jaxbResult = runConversion(tempAfp, false);
        ConversionResult jacksonResult = runConversion(tempAfp, true);

        // Parallel run for comprehensive
        MnemonicPerformanceMonitor.setEnabled(true);
        MnemonicPerformanceMonitor.clear();
        long startPar = System.currentTimeMillis();
        AFPParserConfiguration parConfig = new AFPParserConfiguration();
        parConfig.setAFPFile(tempAfp);
        ParallelAfpConverter converter = new ParallelAfpConverter(parConfig, 0, true, null);
        long durationPar = -1;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            converter.convert(baos);
            durationPar = System.currentTimeMillis() - startPar;
        } catch (Exception e) {
            System.err.println("Parallel conversion failed in comprehensive test: " + e.getMessage());
            e.printStackTrace();
        }
        MnemonicPerformanceMonitor.setEnabled(false);

        System.out.println("JAXB Total: " + jaxbResult.totalTime + "ms, fields: " + jaxbResult.fieldCount + ", errors: " + jaxbResult.errorCount);
        System.out.println("Jackson Total: " + jacksonResult.totalTime + "ms, fields: " + jacksonResult.fieldCount + ", errors: " + jacksonResult.errorCount);
        if (durationPar >= 0) System.out.println("Jackson Parallel Total: " + durationPar + "ms");

        if (jacksonResult.totalTime < jaxbResult.totalTime && jaxbResult.totalTime > 0) {
            System.out.printf("Jackson is %.2fx faster than JAXB overall\n", (double) jaxbResult.totalTime / jacksonResult.totalTime);
        }

        System.out.println("\nTop 10 slowest fields in Jackson with JAXB and Parallel comparison:");
        System.out.printf("%-35s | %-12s | %-12s | %-12s | %-10s\n", "Mnemonic", "JAXB (ns)", "Jackson (ns)", "Parallel (ns)", "Speedup");
        System.out.println("--------------------------------------------------------------------------------------------------");
        jacksonResult.fieldTimings.entrySet().stream()
            .sorted((e1, e2) -> Long.compare(e2.getValue().get(), e1.getValue().get()))
            .forEach(e -> {
                String className = e.getKey();
                long jacksonTime = e.getValue().get();
                AtomicLong jaxbTimeAtomic = jaxbResult.fieldTimings.get(className);
                long jaxbTime = (jaxbTimeAtomic != null) ? jaxbTimeAtomic.get() : 0;
                long parallelTime = MnemonicPerformanceMonitor.getWriteTime(className);
                double speedup = (parallelTime > 0 && jaxbTime > 0) ? (double) jaxbTime / parallelTime : 0;
                System.out.printf("%-35s | %-12d | %-12d | %-12d | %.2fx\n", className, jaxbTime, jacksonTime, parallelTime, speedup);
            });
    }

    private static class ConversionResult {
        long totalTime;
        int fieldCount = 0;
        int errorCount = 0;
        Map<String, AtomicLong> fieldTimings = new HashMap<>();
    }

    private ConversionResult runConversion(File afpFile, boolean useJackson) throws Exception {
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAFPFile(afpFile);
        config.setEscalateParsingErrors(false);

        ConversionResult result = new ConversionResult();
        long startTime = System.currentTimeMillis();
        AFPParser parser = new AFPParser(config);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024 * 1024);

        try (AutoCloseable writer = useJackson
                ? new AfpJacksonXmlWriter(bos, null)
                : new AfpStreamingXmlWriter(bos, null)) {
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                result.fieldCount++;
                String className = sf.getClass().getSimpleName();
                long startField = System.nanoTime();
                try {
                    if (useJackson) {
                        ((AfpJacksonXmlWriter)writer).writeField(sf);
                    } else {
                        ((AfpStreamingXmlWriter)writer).writeField(sf);
                    }
                } catch (Throwable e) {
                    result.errorCount++;
                    // Silence errors during the main run to keep output clean, but we know which fields fail
                }
                long endField = System.nanoTime();
                result.fieldTimings.computeIfAbsent(className, k -> new AtomicLong()).addAndGet(endField - startField);
                sf.release();
            }
        } finally {
            parser.quitParsing();
        }
        result.totalTime = System.currentTimeMillis() - startTime;
        return result;
    }

    private void generateComprehensiveAfp(File output, int countPerType) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(output)) {
            byte[] bdt = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA8, (byte)0xA8, 0x00, 0x00, 0x00};
            byte[] nameEbcdic = {(byte)0xC4, (byte)0xD6, (byte)0xC3, (byte)0xF0, (byte)0xF0, (byte)0xF0, (byte)0xF0, (byte)0xF1};
            fos.write(bdt);
            fos.write(nameEbcdic);

            for (SFTypeID type : SFTypeID.values()) {
                if (type == SFTypeID.Undefined) continue;

                // Wrap in a page
                byte[] bpg = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA8, (byte)0xAF, 0x00, 0x00, 0x00};
                byte[] epg = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xAF, 0x00, 0x00, 0x00};

                fos.write(bpg);
                fos.write(nameEbcdic);

                byte[] header = { 0x5A, 0x00, 0x08, (byte)type.getSfClass().toByte(), (byte)type.getSfType().toByte(), (byte)type.getSfCategory().toByte(), 0x00, 0x00, 0x00 };

                for (int i = 0; i < countPerType; i++) {
                    fos.write(header);
                }

                fos.write(epg);
                fos.write(nameEbcdic);
            }

            byte[] edt = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xA8, 0x00, 0x00, 0x00};
            fos.write(edt);
            fos.write(nameEbcdic);
        }
    }

    private void generateLargeAfp(File output, int sizeMb) throws IOException {
        byte[] nameEbcdic = {(byte)0xC4, (byte)0xD6, (byte)0xC3, (byte)0xF0, (byte)0xF0, (byte)0xF0, (byte)0xF0, (byte)0xF1};
        byte[] bdt = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA8, (byte)0xA8, 0x00, 0x00, 0x00};
        byte[] edt = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xA8, 0x00, 0x00, 0x00};
        byte[] bpg = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA8, (byte)0xAF, 0x00, 0x00, 0x00};
        byte[] epg = {0x5A, 0x00, 0x10, (byte)0xD3, (byte)0xA9, (byte)0xAF, 0x00, 0x00, 0x00};

        byte[] ptxPayload = {
            0x2B, (byte)0xD3, 0x15, (byte)0xDA, // TRN CS, length 21
            (byte)0xC8, (byte)0xC0, (byte)0x93, (byte)0x93, (byte)0x6A, 0x40, // Hällö
            (byte)0xE6, (byte)0x6A, (byte)0x99, (byte)0x93, (byte)0x84, 0x40, // Wörld
            (byte)0xC0, (byte)0x6A, (byte)0xD0, (byte)0xA1, (byte)0x4A, (byte)0xE0, (byte)0x5A // äöüßÄÖÜ
        };
        byte[] ptxHeader = {0x5A, 0x00, (byte)(ptxPayload.length + 8), (byte)0xD3, (byte)0xEE, (byte)0x9B, 0x00, 0x00, 0x00};

        byte[] basePayload = "This is a NOP field to fill space and test processing speed. ".getBytes();
        byte[] nopPayload = new byte[32000];
        for (int i = 0; i < nopPayload.length; i++) {
            nopPayload[i] = basePayload[i % basePayload.length];
        }
        byte[] nopHeader = {0x5A, 0x7D, 0x08, (byte)0xD3, (byte)0xEE, (byte)0xEE, 0x00, 0x00, 0x00};

        try (FileOutputStream fos = new FileOutputStream(output)) {
            fos.write(bdt);
            fos.write(nameEbcdic);

            int targetSize = sizeMb * 1024 * 1024;
            int currentSize = bdt.length + nameEbcdic.length;

            while (currentSize < targetSize - 2000000) {
                fos.write(bpg);
                fos.write(nameEbcdic);
                currentSize += bpg.length + nameEbcdic.length;

                // 30 NOPs of 32KB = ~1MB
                for (int j = 0; j < 30; j++) {
                    fos.write(nopHeader);
                    fos.write(nopPayload);
                    currentSize += nopHeader.length + nopPayload.length;
                }

                // Some PTX
                for (int j = 0; j < 100; j++) {
                    fos.write(ptxHeader);
                    fos.write(ptxPayload);
                    currentSize += ptxHeader.length + ptxPayload.length;
                }

                fos.write(epg);
                fos.write(nameEbcdic);
                currentSize += epg.length + nameEbcdic.length;
            }

            fos.write(edt);
            fos.write(nameEbcdic);
        }
    }
}
