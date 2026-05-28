package com.mgz.xml;

import com.mgz.afp.modca.BDT_BeginDocument;
import com.mgz.afp.modca.TLE_TagLogicalElement;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AfpStreamingXmlWriterTest {

    @Test
    public void testStreamingWriteDirectly() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(baos)) {
            BDT_BeginDocument bdt = new BDT_BeginDocument();
            bdt.setName("TESTDOC ");
            writer.writeField(bdt);
        }
        String output = baos.toString();
        // The output uses xsi:type and lowercase name for the tag because of how JAXB is configured or defaults.
        assertTrue(output.contains("<AFPDocument>"), "Should contain AFPDocument");
        assertTrue(output.contains("<BDT_BeginDocument"), "Should contain BDT_BeginDocument");
        assertTrue(output.contains("<name>TESTDOC </name>"), "Should contain name tag");
    }

    @Test
    public void testStreamingWriteWithXPath() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(baos, "//TLE_TagLogicalElement")) {
            TLE_TagLogicalElement tle = new TLE_TagLogicalElement();
            writer.writeField(tle);
        }
        String output = baos.toString();
        assertTrue(output.contains("<TLE_TagLogicalElement"));
    }
}
