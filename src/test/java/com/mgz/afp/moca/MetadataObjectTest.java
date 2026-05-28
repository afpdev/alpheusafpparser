package com.mgz.afp.moca;

import com.mgz.afp.exceptions.AFPParserException;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class MetadataObjectTest {

    @Test
    public void testMetadataObjectDecode() throws Exception {
        // [MOCA-4-001] [MOCA-4-002] [MOCA-4-004] [MOCA-4-006] [MOCA-4-007] [MOCA-4-008] [MOCA-4-010] [MOCA-4-014] [MOCA-4-015] [MOCA-4-018]
        int headerBaseSize = 46;
        String name = "TEST NAME";
        byte[] nameBytes = name.getBytes(StandardCharsets.UTF_16BE);
        int nameLen = nameBytes.length;
        int headerTotalSize = headerBaseSize + nameLen;

        byte[] moData = "HELLO MOCA".getBytes(StandardCharsets.UTF_16BE);
        int totalPayloadLen = 4 + headerTotalSize + moData.length;

        byte[] data = new byte[totalPayloadLen];

        // MOLength (4B)
        data[0] = (byte)((totalPayloadLen >> 24) & 0xFF);
        data[1] = (byte)((totalPayloadLen >> 16) & 0xFF);
        data[2] = (byte)((totalPayloadLen >> 8) & 0xFF);
        data[3] = (byte)(totalPayloadLen & 0xFF);

        // HeaderLength (2B)
        data[4] = (byte)((headerTotalSize >> 8) & 0xFF);
        data[5] = (byte)(headerTotalSize & 0xFF);

        // MOType (6B): "DES"
        System.arraycopy("DES".getBytes(StandardCharsets.UTF_16BE), 0, data, 6, 6);

        // MOFormat (8B): "AFPT" + "@@" (which is X'0041 0046 0050 0054' followed by X'0040 0040'?)
        // Spec says AFPT is X'0041 0046 0050 0054'. That's 8 bytes.
        System.arraycopy("AFPT".getBytes(StandardCharsets.UTF_16BE), 0, data, 12, 8);

        // MOCompression (20B): "NONE" + "@@@@@@@@"
        // NONE is 4 characters (8 bytes), we need 6 more characters (12 bytes) of padding
        System.arraycopy("NONE@@@@@@@@".getBytes(StandardCharsets.UTF_16BE), 0, data, 20, 20);

        // Reserved (8B) - 40 to 47 already 0

        // MONameLength (2B)
        data[48] = (byte)((nameLen >> 8) & 0xFF);
        data[49] = (byte)(nameLen & 0xFF);

        // MOName
        System.arraycopy(nameBytes, 0, data, 50, nameLen);

        // MOData
        System.arraycopy(moData, 0, data, 4 + headerTotalSize, moData.length);

        MetadataObject mo = new MetadataObject();
        mo.decode(data);

        assertEquals(totalPayloadLen, mo.getMoLength());
        assertEquals(headerTotalSize, mo.getHeaderLength());
        assertEquals("DES", mo.getMoType());
        assertEquals("AFPT", mo.getMoFormat());
        assertEquals("NONE", mo.getMoCompression());
        assertEquals(nameLen, mo.getMoNameLength());
        assertEquals(name, mo.getMoName());
        assertArrayEquals(moData, mo.getMoData());
        assertEquals("HELLO MOCA", mo.getMoDataText());
    }

    @Test
    public void testMetadataObjectShortData() {
        MetadataObject mo = new MetadataObject();
        assertThrows(AFPParserException.class, () -> mo.decode(new byte[49]));
    }
}
