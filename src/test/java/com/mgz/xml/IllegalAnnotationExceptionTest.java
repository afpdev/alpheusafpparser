package com.mgz.xml;

import org.junit.jupiter.api.Test;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class IllegalAnnotationExceptionTest {

    @Test
    public void testClashingClasses() throws JAXBException {
        Class<?>[] classes = new Class<?>[] {
            com.mgz.afp.modca.NOP_NoOperation.class,
            com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.NOP_NoOperation.class
        };
        // This should NO LONGER throw IllegalAnnotationsException
        JAXBContext.newInstance(classes);
    }

    @Test
    public void testClashingBeginSegments() throws JAXBException {
        Class<?>[] classes = new Class<?>[] {
            com.mgz.afp.goca.GAD_DrawingOrder.GBSEG_BeginSegment.class,
            com.mgz.afp.ioca.IPD_Segment.BeginSegment.class
        };
        JAXBContext.newInstance(classes);
    }

    @Test
    public void testClashingMCFRepeatingGroups() throws JAXBException {
        Class<?>[] classes = new Class<?>[] {
            com.mgz.afp.modca.MCF_MapCodedFont_Format1.MCF_RepeatingGroup.class,
            com.mgz.afp.modca.MCF_MapCodedFont_Format2.MCF_RepeatingGroup.class
        };
        JAXBContext.newInstance(classes);
    }

    @Test
    public void testClashingUndefined() throws JAXBException {
        Class<?>[] classes = new Class<?>[] {
            com.mgz.afp.base.Undefined.class,
            com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.Undefined.class,
            com.mgz.afp.triplets.Triplet.Undefined.class
        };
        JAXBContext.newInstance(classes);
    }
}
