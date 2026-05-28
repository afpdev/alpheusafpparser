package com.mgz.xml;

import com.mgz.afp.modca.NOP_NoOperation;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Afp2XmlInvalidCharTest {

    @Test
    public void testInvalidXmlChar() throws Exception {
        NOP_NoOperation nop = new NOP_NoOperation();
        // 0x1a is invalid in XML 1.0
        byte[] data = "Hello\u001aWorld".getBytes(StandardCharsets.US_ASCII);
        // We need to simulate how it's decoded. NOP_NoOperation uses StructuredFieldBaseData.decodeAFP
        // which uses config.getAfpCharSet()
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(StandardCharsets.US_ASCII);

        nop.decodeAFP(data, 0, data.length, config);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // This might fail here or in the XPath version
        Afp2XmlWriter.writeXML(baos, nop, config);
    }

    @Test
    public void testInvalidXmlCharWithXPath() throws Exception {
        com.mgz.afp.base.AFPDocument doc = new com.mgz.afp.base.AFPDocument();
        NOP_NoOperation nop = new NOP_NoOperation();
        byte[] data = "Hello\u001aWorld".getBytes(StandardCharsets.US_ASCII);
        AFPParserConfiguration config = new AFPParserConfiguration();
        config.setAfpCharSet(StandardCharsets.US_ASCII);
        nop.decodeAFP(data, 0, data.length, config);

        doc.addStructuredField(new javax.xml.bind.JAXBElement<>(
                new javax.xml.namespace.QName("NOP_NoOperation"),
                NOP_NoOperation.class,
                nop));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // The error happened here in the report
        Afp2XmlWriter.writeXML(baos, doc, "//NOP_NoOperation/text");
    }
}
