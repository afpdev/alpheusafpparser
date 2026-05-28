package com.mgz.xml;

import com.mgz.afp.foca.FNC_FontControl;
import com.mgz.afp.parser.AFPParserConfiguration;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonOutputInspectorTest {
    @Test
    public void inspectFnc() throws Exception {
        FNC_FontControl fnc = new FNC_FontControl();
        // Minimal data to decode, let's see what's enough
        byte[] data = new byte[100];
        fnc.decodeAFP(data, 0, data.length, new AFPParserConfiguration());

        XmlMapper mapper = JacksonXmlMapperProvider.getMapper();
        System.out.println("--- FNC JACKSON START ---");
        System.out.println(mapper.writer().withRootName("FNC_FontControl").writeValueAsString(fnc));
        System.out.println("--- FNC JACKSON END ---");
    }
}
