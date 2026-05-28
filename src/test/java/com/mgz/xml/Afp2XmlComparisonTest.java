package com.mgz.xml;

import com.mgz.afp.base.AFPDocument;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Afp2XmlComparisonTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/afp/afp-goca-reference-03/Chapter_4.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_5.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_6.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_1.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_4.afp"
    })
    public void testDomVsStreamingOutput(String afpPath) throws Exception {
        byte[] domXml = convertWithDom(afpPath, null);
        byte[] streamingXml = convertWithStreaming(afpPath, null);

        String domNormalized = normalizeXml(new String(domXml, "UTF-8"));
        String streamingNormalized = normalizeXml(new String(streamingXml, "UTF-8"));

        assertEquals(domNormalized, streamingNormalized, "Full XML output (JAXB DOM vs JAXB Streaming) should be identical for " + afpPath);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/afp/afp-goca-reference-03/Chapter_4.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_5.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_6.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_1.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_4.afp"
    })
    public void testJAXBVsJacksonStreamingOutput(String afpPath) throws Exception {
        byte[] jaxbXml = convertWithStreaming(afpPath, null);
        byte[] jacksonXml = convertWithJacksonStreaming(afpPath, null);

        String jaxbNormalized = normalizeXmlForJackson(new String(jaxbXml, "UTF-8"));
        String jacksonNormalized = normalizeXmlForJackson(new String(jacksonXml, "UTF-8"));

        assertEquals(jaxbNormalized, jacksonNormalized, "Full XML output (JAXB Streaming vs Jackson Streaming) should be functionally identical for " + afpPath);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/test/resources/afp/afp-goca-reference-03/Chapter_4.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_5.afp",
            "src/test/resources/afp/afp-goca-reference-03/Chapter_6.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_1.afp",
            "src/test/resources/afp/cmoca-reference-02/Chapter_4.afp"
    })
    public void testDomVsStreamingOutputWithXPath(String afpPath) throws Exception {
        String xpath = "//BDT_BeginDocument";
        byte[] domXml = convertWithDom(afpPath, xpath);
        byte[] streamingXml = convertWithStreaming(afpPath, xpath);

        String domNormalized = normalizeXml(new String(domXml, "UTF-8"));
        String streamingNormalized = normalizeXml(new String(streamingXml, "UTF-8"));

        assertEquals(domNormalized, streamingNormalized, "XPath filtered output should be identical for " + afpPath);
    }

    private byte[] convertWithDom(String afpPath, String xpath) throws Exception {
        AFPDocument doc = new AFPDocument();
        try (InputStream is = new BufferedInputStream(new FileInputStream(afpPath))) {
            AFPParserConfiguration config = new AFPParserConfiguration();
            config.setInputStream(is);
            AFPParser parser = new AFPParser(config);
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                var qualifiedName = new QName(sf.getClass().getSimpleName());
                @SuppressWarnings("unchecked")
                var element = new JAXBElement(qualifiedName, sf.getClass(), sf);
                doc.addStructuredField(element);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (xpath != null) {
            Afp2XmlWriter.writeXML(baos, doc, xpath);
        } else {
            Afp2XmlWriter.writeXML(baos, doc);
        }
        return baos.toByteArray();
    }

    private byte[] convertWithJacksonStreaming(String afpPath, String xpath) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is = new BufferedInputStream(new FileInputStream(afpPath));
             AfpJacksonXmlWriter writer = new AfpJacksonXmlWriter(baos, xpath)) {
            AFPParserConfiguration config = new AFPParserConfiguration();
            config.setInputStream(is);
            AFPParser parser = new AFPParser(config);
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                writer.writeField(sf);
                sf.release();
            }
        }
        return baos.toByteArray();
    }

    private byte[] convertWithStreaming(String afpPath, String xpath) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is = new BufferedInputStream(new FileInputStream(afpPath));
             AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(baos, xpath)) {
            AFPParserConfiguration config = new AFPParserConfiguration();
            config.setInputStream(is);
            AFPParser parser = new AFPParser(config);
            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                writer.writeField(sf);
                sf.release();
            }
        }
        return baos.toByteArray();
    }

    private String normalizeXml(String xml) {
        if (xml == null || xml.isEmpty()) {
            return "";
        }
        // Basic normalization: remove XML declaration, collapse whitespace between tags, trim
        String normalized = xml.replaceAll("<\\?xml.*?\\?>", "");
        // Remove xsi:type and xmlns:xsi attributes which are added by streaming marshaller but not by DOM one
        normalized = normalized.replaceAll("\\s+xmlns:xsi=\".*?\"", "");
        normalized = normalized.replaceAll("\\s+xsi:type=\".*?\"", "");
        normalized = normalized.replaceAll(">\\s+<", "><");
        return normalized.trim();
    }

    private String normalizeXmlForJackson(String xml) {
        String normalized = normalizeXml(xml);
        // JAXB sometimes outputs duplicate <text> elements in StructuredFieldBaseNameAndTriplets
        // One from StructuredFieldBaseName and one from StructuredFieldBaseNameAndTriplets
        // Jackson correctly identifies them as the same property and only outputs it once.
        // To reconcile them, we collapse multiple identical adjacent tags if they contain the same text.
        // Actually, a simpler way is to just remove duplicate <text>...</text> tags.
        normalized = normalized.replaceAll("(<text>[^<]*</text>)\\1+", "$1");
        return normalized;
    }
}
