package com.mgz.xml;

import com.mgz.afp.modca.NOP_NoOperation;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NOPXMLTest {

    @Test
    public void testNOPXMLWithText() throws Exception {
        String text = "Hello World";
        byte[] data = text.getBytes(Charset.forName("cp500"));

        AFPParserConfiguration config = new AFPParserConfiguration();
        NOP_NoOperation nop = new NOP_NoOperation();
        nop.decodeAFP(data, 0, data.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Afp2XmlWriter.writeXML(baos, nop, config);

        String xml = baos.toString();
        System.out.println(xml);
        assertTrue(xml.contains("<text>Hello World</text>"), "XML should contain <text> node");
    }

    @Test
    public void testNOPXMLWithBinary() throws JAXBException {
        NOP_NoOperation nop = new NOP_NoOperation();
        byte[] data = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05 };
        nop.setData(data);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Afp2XmlWriter.writeXML(baos, nop, new AFPParserConfiguration());

        String xml = baos.toString();
        System.out.println(xml);
        assertFalse(xml.contains("<text>"), "XML should NOT contain <text> node for binary data");
    }
}
