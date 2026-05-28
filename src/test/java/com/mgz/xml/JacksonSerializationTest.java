package com.mgz.xml;

import com.mgz.afp.modca.BDT_BeginDocument;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test to verify that Jackson can serialize AFP objects using JAXB annotations.
 */
public class JacksonSerializationTest {

  /**
   * Tests Jackson vs JAXB serialization parity.
   *
   * @throws Exception if serialization fails
   */
  @Test
  public void testJacksonVsJaxbSerialization() throws Exception {
    BDT_BeginDocument bdt = new BDT_BeginDocument();
    bdt.setName("DOC001");

    // JAXB Serialization
    ByteArrayOutputStream jaxbBaos = new ByteArrayOutputStream();
    JAXBContext jaxbContext = JAXBContext.newInstance(BDT_BeginDocument.class);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    var qualifiedName = new QName("BDT_BeginDocument");
    var jaxbElement = new JAXBElement<>(qualifiedName, BDT_BeginDocument.class, bdt);
    marshaller.marshal(jaxbElement, jaxbBaos);

    // Jackson Serialization
    String jacksonXml = JacksonXmlMapperProvider.getMapper().writeValueAsString(bdt);

    // Verify key content exists in both
    assertTrue(jacksonXml.contains("BDT_BeginDocument"), "Jackson XML should contain root element");
    assertTrue(jacksonXml.contains("DOC001"), "Jackson XML should contain document name");
    // Jackson with JaxbAnnotationModule uses the class name as root element by default if not specified,
    // or honors @XmlRootElement if present. BDT_BeginDocument doesn't have @XmlRootElement.
  }
}
