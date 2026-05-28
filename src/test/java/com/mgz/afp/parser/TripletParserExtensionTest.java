package com.mgz.afp.parser;

import com.mgz.afp.triplets.Triplet;
import com.mgz.afp.triplets.Triplet.FullyQualifiedName;
import com.mgz.util.Constants;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TripletParserExtensionTest {

    @Test
    public void testTripletExtensionFQN() throws Exception {
        // FQN Type 0x01, Format 0x00, Name "PART1" (4 bytes)
        // PART1 in EBCDIC: D7 C1 D9 E3 F1 -> wait, PART1 is 5 bytes.
        // Let's use "ABC" -> C1 C2 C3
        // Triplet 1: Len=0x07, ID=0x02, Type=0x01, Format=0x00, Data=C1 C2 C3
        // Triplet 2 (Extender): Len=0x06, ID=0xFF, Res=0000, Data=C4 C5 ("DE")
        byte[] data = new byte[] {
            0x07, 0x02, 0x01, 0x00, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3,
            0x06, (byte) 0xFF, 0x00, 0x00, (byte) 0xC4, (byte) 0xC5
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        List<Triplet> triplets = TripletParser.parseTriplets(data, 0, data.length, config);

        Assertions.assertEquals(1, triplets.size());
        Assertions.assertTrue(triplets.get(0) instanceof FullyQualifiedName);
        FullyQualifiedName fqn = (FullyQualifiedName) triplets.get(0);
        Assertions.assertEquals("ABCDE", fqn.getNameAsString());
    }

    @Test
    public void testTripletExtensionAttributeValue() throws Exception {
        // AttributeValue Len=0x05, ID=0x36, Res=0000, Data=C1 ("A")
        // Extender Len=0x05, ID=0xFF, Res=0000, Data=C2 ("B")
        byte[] data = new byte[] {
            0x05, 0x36, 0x00, 0x00, (byte) 0xC1,
            0x05, (byte) 0xFF, 0x00, 0x00, (byte) 0xC2
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        List<Triplet> triplets = TripletParser.parseTriplets(data, 0, data.length, config);

        Assertions.assertEquals(1, triplets.size());
        Assertions.assertTrue(triplets.get(0) instanceof Triplet.AttributeValue);
        Triplet.AttributeValue av = (Triplet.AttributeValue) triplets.get(0);
        Assertions.assertEquals("AB", av.getAttributeValue());
    }

    @Test
    public void testTripletExtensionComment() throws Exception {
        // Comment Len=0x03, ID=0x65, Data=C1 ("A")
        // Extender Len=0x05, ID=0xFF, Res=0000, Data=C2 ("B")
        byte[] data = new byte[] {
            0x03, 0x65, (byte) 0xC1,
            0x05, (byte) 0xFF, 0x00, 0x00, (byte) 0xC2
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        List<Triplet> triplets = TripletParser.parseTriplets(data, 0, data.length, config);

        Assertions.assertEquals(1, triplets.size());
        Assertions.assertTrue(triplets.get(0) instanceof Triplet.Comment);
        Triplet.Comment c = (Triplet.Comment) triplets.get(0);
        Assertions.assertEquals("AB", c.getText());
    }

    @Test
    public void testTripletExtensionRoundTrip() throws Exception {
        // Comment Len=0x03, ID=0x65, Data=C1 ("A")
        // Extender Len=0x05, ID=0xFF, Res=0000, Data=C2 ("B")
        byte[] data = new byte[] {
            0x03, 0x65, (byte) 0xC1,
            0x05, (byte) 0xFF, 0x00, 0x00, (byte) 0xC2
        };

        AFPParserConfiguration config = new AFPParserConfiguration();
        List<Triplet> triplets = TripletParser.parseTriplets(data, 0, data.length, config);

        Assertions.assertEquals(1, triplets.size());
        Triplet t = triplets.get(0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        t.writeAFP(baos, config);

        byte[] output = baos.toByteArray();
        // Expecting a single triplet of length 4 (Len + ID + AB)
        Assertions.assertEquals(4, output.length);
        Assertions.assertEquals(0x04, output[0]);
        Assertions.assertEquals(0x65, output[1]);
        Assertions.assertEquals((byte) 0xC1, output[2]);
        Assertions.assertEquals((byte) 0xC2, output[3]);
    }
}
