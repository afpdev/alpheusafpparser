package com.mgz.afp.parser;

import com.mgz.afp.base.IHasTriplets;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.StructuredFieldErrornouslyBuilt;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.triplets.Triplet;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorHandlingTest {

    @Test
    public void testMissing5AMarker() throws Exception {
        byte[] data = new byte[]{0x00, 0x01, 0x02}; // No 0x5A
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertNull(sf, "Parser should return null when no 0x5A marker is found before EOF");
    }

    @Test
    public void testTruncatedSFI() throws Exception {
        // 0x5A followed by only 3 bytes (SFI needs at least 8)
        byte[] data = new byte[]{0x5A, 0x00, 0x10, (byte) 0xD3};
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        try {
            parser.parseNextSF();
            fail("Parser should throw AFPParserException for truncated SFI");
        } catch (AFPParserException e) {
            // Expected
        } catch (NullPointerException npe) {
            fail("Parser should not throw NullPointerException for truncated SFI");
        }
    }

    @Test
    public void testTruncatedSFI_NoEscalation() throws Exception {
        // 0x5A followed by only 3 bytes
        byte[] data = new byte[]{0x5A, 0x00, 0x10, (byte) 0xD3};
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(false);
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof StructuredFieldErrornouslyBuilt,
                "Parser should return StructuredFieldErrornouslyBuilt when escalation is off");
    }

    @Test
    public void testSFIWithMissingExtension() throws Exception {
        // SFI: Length 9, Type BDT, Flags: HasExtension (0x20)
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x09, // Length
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            (byte)0x20, // Flag: hasExtension is 0x20
            0x00, 0x00  // Reserved
            // Missing extension length byte
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        try {
            parser.parseNextSF();
            fail("Parser should throw AFPParserException when SFI extension length is missing");
        } catch (AFPParserException e) {
            // Expected
        } catch (NullPointerException npe) {
            fail("Parser should not throw NullPointerException when SFI extension length is missing");
        }
    }

    @Test
    public void testSFIWithTruncatedExtensionData() throws Exception {
        // SFI: Length 10, Type BDT, Flags: HasExtension (0x20)
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0A, // Length
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            (byte)0x20, // Flag: hasExtension is 0x20
            0x00, 0x00, // Reserved
            0x05        // Extension Length = 5 (1 byte length + 4 bytes data)
            // Only 0 bytes of extension data follow, but 4 are expected
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException when SFI extension data is truncated");
    }

    @Test
    public void testTruncatedPayload() throws Exception {
        // SFI: Length 12, Type BDT, Flags: 0, Reserved 0
        // Payload should be 12 - 8 = 4 bytes
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0C, // Length 12
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x01, 0x02 // Only 2 bytes of payload provided, 4 expected
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        assertThrows(AFPParserException.class, parser::parseNextSF,
                "Parser should throw AFPParserException when payload is truncated");
    }

    @Test
    public void testTruncatedPayload_NoEscalation() throws Exception {
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0C, // Length 12
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x01, 0x02 // Only 2 bytes of payload provided, 4 expected
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(false);
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof StructuredFieldErrornouslyBuilt,
                "Parser should return StructuredFieldErrornouslyBuilt for truncated payload when escalation is off");
    }

    @Test
    public void testMalformedTripletLength_TooShort() throws Exception {
        // BDT with a Triplet. Triplet length 1 is invalid (min 2: length + ID)
        // BDT needs at least 8 bytes of payload to not throw IndexOutOfBounds in its decodeAFP
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x13, // Length 19
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00, // Reserved
            // BDT Payload:
            0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, // Name (8 blanks)
            0x00, 0x00, // Reserved
            0x01, 0x02 // Triplet length 1, ID 0x02
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        // BDT supports triplets via inheritance if it implements IHasTriplets
        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof IHasTriplets, "SF should be IHasTriplets");
        if (sf instanceof IHasTriplets) {
            List<Triplet> triplets = ((IHasTriplets) sf).getTriplets();
            assertNotNull(triplets, "Triplets list should not be null");
            System.out.println("Triplets size: " + triplets.size());
            assertFalse(triplets.isEmpty(), "Triplets list should not be empty");
            assertTrue(triplets.get(0) instanceof Triplet.Undefined, "Invalid triplet should be parsed as Undefined, but was " + triplets.get(0).getClass().getName());
            assertNotNull(((Triplet.Undefined)triplets.get(0)).getParsingException(), "Undefined triplet should have a parsing exception");
        }
    }

    @Test
    public void testMalformedTripletLength_TooLong() throws Exception {
        // BDT with a Triplet. Triplet length 10 exceeds SF payload (2 bytes available)
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x15, // Length 21
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00, // Reserved
            // BDT Payload:
            0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, // Name (8 blanks)
            0x00, 0x00, // Reserved
            0x0A, 0x02, // Triplet length 10, ID 0x02
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 // More data to satisfy BDT length but not triplet length
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setInputStream(new ByteArrayInputStream(data));
        config.setEscalateParsingErrors(true);
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof IHasTriplets, "SF should be IHasTriplets");
        if (sf instanceof IHasTriplets) {
            List<Triplet> triplets = ((IHasTriplets) sf).getTriplets();
            assertNotNull(triplets, "Triplets list should not be null");
            assertFalse(triplets.isEmpty(), "Triplets list should not be empty");
            assertTrue(triplets.get(0) instanceof Triplet.Undefined, "Oversized triplet should be parsed as Undefined");
        }
    }

    @Test
    public void testErroneousSFDiagnostics() throws Exception {
        // Buffer-based parsing for diagnostics check
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x0C, // Length 12
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00, // Reserved
            0x01, 0x02 // Only 2 bytes of payload provided, 4 expected
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setByteBuffer(java.nio.ByteBuffer.wrap(data));
        config.setEscalateParsingErrors(false);
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof StructuredFieldErrornouslyBuilt, "SF should be StructuredFieldErrornouslyBuilt");
        StructuredFieldErrornouslyBuilt errSf = (StructuredFieldErrornouslyBuilt) sf;

        assertEquals(0, errSf.getFileOffset());
        assertEquals(com.mgz.afp.enums.SFTypeID.BDT_BeginDocument, errSf.getTypeId());
        assertNotNull(errSf.getErrorMessage());
        assertTrue(errSf.getErrorMessage().contains("0x0"), "Error message should contain offset");
        assertTrue(errSf.getErrorMessage().contains("BDT_BeginDocument"), "Error message should contain SF type");
    }

    @Test
    public void testInvalidSFLengthDiagnostics() throws Exception {
        // SF length less than 8
        byte[] data = new byte[]{
            0x5A,
            0x00, 0x07, // Invalid length 7
            (byte)0xD3, (byte)0xA8, (byte)0xA8, // BDT
            0x00, // Flags
            0x00, 0x00  // Reserved
        };
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setByteBuffer(java.nio.ByteBuffer.wrap(data));
        config.setEscalateParsingErrors(false);
        AFPParser parser = new AFPParser(config);

        StructuredField sf = parser.parseNextSF();
        assertTrue(sf instanceof StructuredFieldErrornouslyBuilt, "SF should be StructuredFieldErrornouslyBuilt");
        StructuredFieldErrornouslyBuilt errSf = (StructuredFieldErrornouslyBuilt) sf;

        assertEquals(0, errSf.getFileOffset());
        assertNotNull(errSf.getRawIntroducer());
        assertTrue(errSf.getErrorMessage().contains("Minimum length is 8"), "Error message should contain length error");
    }
}
