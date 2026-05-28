package com.mgz.afp.base;

import com.mgz.afp.exceptions.AFPParserException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SFIErrorHandlingTest {

    @Test
    public void testInvalidSFLengthInputStream() {
        // [MODCA-3-020] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x07, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, 0x00, 0x00, 0x00};
        InputStream is = new ByteArrayInputStream(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Invalid SF length: 7"));
    }

    @Test
    public void testReachedEndOfStreamFlagByte() {
        // [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x08, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8};
        InputStream is = new ByteArrayInputStream(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Reached end of stream while parsing SF flag byte"));
    }

    @Test
    public void testReachedEndOfStreamExtensionLength() {
        // [MODCA-3-021] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x09, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, (byte) 0x80, 0x00, 0x00};
        InputStream is = new ByteArrayInputStream(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Reached end of stream while parsing SF extension length"));
    }

    @Test
    public void testInvalidExtensionLength() {
        // [MODCA-3-022] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x09, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, (byte) 0x80, 0x00, 0x00, 0x00};
        InputStream is = new ByteArrayInputStream(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Invalid SF extension length: 0"));
    }

    @Test
    public void testFailedToReadExtensionData() {
        // [MODCA-3-023] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x0A, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, (byte) 0x80, 0x00, 0x00, 0x02};
        InputStream is = new ByteArrayInputStream(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Failed to read SFI extension data"));
    }

    @Test
    public void testNotEnoughBytesForSFIntroducerOffset() {
        // [MODCA-3-011]
        ByteBuffer buffer = ByteBuffer.allocate(7);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(buffer, 0));
        assertTrue(ex.getMessage().contains("Not enough bytes for SF introducer"));
    }

    @Test
    public void testNotEnoughBytesRemainingInBuffer() {
        // [MODCA-3-011]
        ByteBuffer buffer = ByteBuffer.allocate(7);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(buffer));
        assertTrue(ex.getMessage().contains("Not enough bytes remaining in buffer for SF introducer"));
    }

    @Test
    public void testInvalidSFLengthByteBuffer() {
        // [MODCA-3-020] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x07, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, 0x00, 0x00, 0x00};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(buffer));
        assertTrue(ex.getMessage().contains("Invalid SF length: 7"));
    }

    @Test
    public void testInvalidExtensionLengthByteBuffer() {
        // [MODCA-3-022] [MODCA-3-011]
        byte[] data = new byte[]{0x00, 0x09, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, (byte) 0x80, 0x00, 0x00, 0x00};
        ByteBuffer buffer = ByteBuffer.wrap(data);
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(buffer));
        assertTrue(ex.getMessage().contains("Invalid SF extension length: 0"));
    }

    @Test
    public void testIOExceptionHeader() {
        // [MODCA-3-011]
        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO error");
            }
        };
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Failed to decode SFI header"));
    }

    @Test
    public void testIOExceptionExtension() {
        // [MODCA-3-011]
        InputStream is = new InputStream() {
            int count = 0;
            byte[] header = new byte[]{0x00, 0x0A, (byte) 0xD3, (byte) 0xA8, (byte) 0xA8, (byte) 0x80, 0x00, 0x00};
            @Override
            public int read() throws IOException {
                if (count < header.length) {
                    return header[count++] & 0xFF;
                }
                throw new IOException("Simulated IO error during extension");
            }
        };
        AFPParserException ex = assertThrows(AFPParserException.class, () -> StructuredFieldIntroducer.parse(is));
        assertTrue(ex.getMessage().contains("Failed to decode decode SFI extension data"));
    }
}
