package com.mgz.afp.cmoca;

import com.mgz.afp.RoundTripTestUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CMRRoundTripTest {

    @Test
    public void testCMRRoundTrip() throws Exception {
        // CMR: D3A0A2
        // Length (4): 164
        // Signature (4): "CMR9" (C3 D4 D9 F9)
        // Reserved1 (2): 0x0000
        // CMR Name (146 bytes): Padded with 0x0040
        //   Alias (16): "ALIAS1" (00 41 00 4C 00 49 00 41 00 53 00 31 00 40...)
        // Reserved3 (8): 0x0000000000000000
        // Data (4): 0x01 02 03 04
        // Total Payload: 164 + 4 = 168
        // Total Len: 1 + 8 + 168 = 177. SFLen = 176 (0x00B0)

        byte[] data = new byte[177];
        data[0] = 0x5A;
        data[1] = 0x00;
        data[2] = (byte) 0xB0;
        data[3] = (byte) 0xD3;
        data[4] = (byte) 0xA0;
        data[5] = (byte) 0xA2;

        // Payload at offset 9
        // Length = 168 (0x000000A8)
        data[12] = (byte) 0xA8;
        // Signature "CMR9"
        data[13] = (byte) 0xC3;
        data[14] = (byte) 0xD4;
        data[15] = (byte) 0xD9;
        data[16] = (byte) 0xF9;

        // Padded CMR Name (Bytes 10-155 of payload, which is 19-164 of data)
        for (int i = 19; i < 165; i += 2) {
            data[i] = 0x00;
            data[i+1] = 0x40;
        }

        // Data
        data[173] = 0x01;
        data[174] = 0x02;
        data[175] = 0x03;
        data[176] = 0x04;

        RoundTripTestUtils.assertRoundTrip(new CMR_ColorManagementResource(), data);
    }

    @Test
    public void testCMRWithTags() throws Exception {
        // Test CMR with Tagged Data (Chapter 5)
        // Tag 1: Comment (0x0004), FieldType ASCII (0x06), Count 4, Data "TEST" (inline)
        // Tag 2: Number of Components (0x0011), FieldType UBIN1 (0x01), Count 1, Data 3 (inline)
        // Tag 3: End Data (0xFFFF), FieldType BYTE (0x05), Count 0
        // Total Tags = 3 * 12 = 36 bytes.

        int headerSize = 164;
        int tagsSize = 36;
        int totalPayload = headerSize + tagsSize;
        byte[] sf = new byte[1 + 8 + totalPayload];
        sf[0] = 0x5A;
        // SF Length = 9 + totalPayload - 1
        int sfLen = 8 + totalPayload;
        sf[1] = (byte)((sfLen >> 8) & 0xFF);
        sf[2] = (byte)(sfLen & 0xFF);
        sf[3] = (byte)0xD3;
        sf[4] = (byte)0xA0;
        sf[5] = (byte)0xA2;

        // Payload length (4 bytes)
        sf[9] = (byte)((totalPayload >> 24) & 0xFF);
        sf[10] = (byte)((totalPayload >> 16) & 0xFF);
        sf[11] = (byte)((totalPayload >> 8) & 0xFF);
        sf[12] = (byte)(totalPayload & 0xFF);

        // Signature "CMR9"
        sf[13] = (byte)0xC3;
        sf[14] = (byte)0xD4;
        sf[15] = (byte)0xD9;
        sf[16] = (byte)0xF9;

        // Padding for name
        for (int i = 19; i < 165; i += 2) {
            sf[i] = 0x00;
            sf[i+1] = 0x40;
        }

        // Tags start at offset 9 + 164 = 173
        int tagsOffset = 173;

        // Tag 1: Comment (0x0004)
        sf[tagsOffset] = 0x00;
        sf[tagsOffset+1] = 0x04;
        sf[tagsOffset+3] = 0x06; // ASCII
        sf[tagsOffset+7] = 0x04; // Count 4
        // Data "TEST" inline at offset 8-11 of tag (181-184 of SF)
        sf[tagsOffset+8] = 'T';
        sf[tagsOffset+9] = 'E';
        sf[tagsOffset+10] = 'S';
        sf[tagsOffset+11] = 'T';

        // Tag 2: Number of Components (0x0011)
        int tag2Offset = tagsOffset + 12;
        sf[tag2Offset] = 0x00;
        sf[tag2Offset+1] = 0x11;
        sf[tag2Offset+3] = 0x01; // UBIN1
        sf[tag2Offset+7] = 0x01; // Count 1
        sf[tag2Offset+8] = 0x03; // Value 3 inline

        // Tag 3: End Data (0xFFFF)
        int tag3Offset = tag2Offset + 12;
        sf[tag3Offset] = (byte)0xFF;
        sf[tag3Offset+1] = (byte)0xFF;
        sf[tag3Offset+3] = 0x05; // BYTE

        CMR_ColorManagementResource cmr = new CMR_ColorManagementResource();
        cmr.decodeAFP(sf, 9, totalPayload, null);

        List<CMRTag> tags = cmr.getTags();
        assertNotNull(tags);
        assertEquals(3, tags.size());

        CMRTag tag1 = tags.get(0);
        assertEquals(0x0004, tag1.getTagId());
        assertArrayEquals("TEST".getBytes(), tag1.getData());

        CMRTag tag2 = tags.get(1);
        assertEquals(0x0011, tag2.getTagId());
        assertEquals(1, tag2.getData().length);
        assertEquals(3, tag2.getData()[0]);
    }

    @Test
    public void testCMRWithOffsetTags() throws Exception {
        // Test CMR with Tagged Data using offsets (Chapter 5)
        // Tag 1: Comment (0x0004), FieldType ASCII (0x06), Count 8, Offset 24
        // Tag 2: End Data (0xFFFF)
        // Tag data (8 bytes) at offset 24
        // Total Tags = 2 * 12 = 24 bytes.
        // Total cmrData = 24 (tags) + 8 (data) = 32 bytes.

        int headerSize = 164;
        int tagsSize = 24;
        int dataSize = 8;
        int totalPayload = headerSize + tagsSize + dataSize;
        byte[] sf = new byte[1 + 8 + totalPayload];
        sf[0] = 0x5A;
        int sfLen = 8 + totalPayload;
        sf[1] = (byte)((sfLen >> 8) & 0xFF);
        sf[2] = (byte)(sfLen & 0xFF);
        sf[3] = (byte)0xD3;
        sf[4] = (byte)0xA0;
        sf[5] = (byte)0xA2;

        sf[9] = (byte)((totalPayload >> 24) & 0xFF);
        sf[10] = (byte)((totalPayload >> 16) & 0xFF);
        sf[11] = (byte)((totalPayload >> 8) & 0xFF);
        sf[12] = (byte)(totalPayload & 0xFF);
        sf[13] = (byte)0xC3;
        sf[14] = (byte)0xD4;
        sf[15] = (byte)0xD9;
        sf[16] = (byte)0xF9;

        for (int i = 19; i < 165; i += 2) {
            sf[i] = 0x00;
            sf[i+1] = 0x40;
        }

        int tagsOffset = 173;
        // Tag 1: Comment (0x0004)
        sf[tagsOffset] = 0x00;
        sf[tagsOffset+1] = 0x04;
        sf[tagsOffset+3] = 0x06; // ASCII
        sf[tagsOffset+7] = 0x08; // Count 8
        // Offset 24 (X'00000018')
        sf[tagsOffset+11] = 0x18;

        // Tag 2: End Data (0xFFFF)
        int tag2Offset = tagsOffset + 12;
        sf[tag2Offset] = (byte)0xFF;
        sf[tag2Offset+1] = (byte)0xFF;
        sf[tag2Offset+3] = 0x05;

        // Data "LONGTEXT" (8 bytes) at offset 24 from start of CMR data (173 + 24 = 197)
        int dataStart = 173 + 24;
        System.arraycopy("LONGTEXT".getBytes(), 0, sf, dataStart, 8);

        CMR_ColorManagementResource cmr = new CMR_ColorManagementResource();
        cmr.decodeAFP(sf, 9, totalPayload, null);

        List<CMRTag> tags = cmr.getTags();
        assertNotNull(tags);
        assertEquals(2, tags.size());

        CMRTag tag1 = tags.get(0);
        assertEquals(0x0004, tag1.getTagId());
        assertArrayEquals("LONGTEXT".getBytes(), tag1.getData());
    }
}
