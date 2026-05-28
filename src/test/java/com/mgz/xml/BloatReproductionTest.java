package com.mgz.xml;

import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.modca.NOP_NoOperation;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class BloatReproductionTest {
    @Test
    public void reproduceBloat() throws Exception {
        NOP_NoOperation nop = new NOP_NoOperation();
        String content = "SPLIT_DEFAULT:05 ; 0;DU;BT;0";
        byte[] data = content.getBytes(StandardCharsets.US_ASCII);

        AFPParserConfiguration config = new AFPParserConfiguration();
        nop.decodeAFP(data, 0, data.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(baos)) {
            writer.writeField(nop);
        }

        System.out.println("Generated XML:\n" + baos.toString("UTF-8"));
    }

    @Test
    public void reproduceBloatWithText() throws Exception {
        NOP_NoOperation nop = new NOP_NoOperation();
        // Use CP500 EBCDIC for "HELLO"
        byte[] data = new byte[] { (byte)0xC8, (byte)0xC5, (byte)0xD3, (byte)0xD3, (byte)0xD6 };

        AFPParserConfiguration config = new AFPParserConfiguration();
        nop.decodeAFP(data, 0, data.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(baos)) {
            writer.writeField(nop);
        }

        System.out.println("Generated XML with text:\n" + baos.toString("UTF-8"));
    }
}
