package com.mgz.afp.triplets;

import com.mgz.afp.RoundTripTestUtils;
import com.mgz.afp.triplets.Triplet.CharacterRotation;
import com.mgz.afp.triplets.Triplet.CodedGraphicCharacterSetGlobalID;
import com.mgz.afp.triplets.Triplet.Comment;
import com.mgz.afp.triplets.Triplet.FontCodedGraphicCharacterSetGlobalID;
import com.mgz.afp.triplets.Triplet.FontDescriptorSpecification;
import com.mgz.afp.triplets.Triplet.FontHorizontalScaleFactor;
import com.mgz.afp.triplets.Triplet.FullyQualifiedName;
import com.mgz.afp.triplets.Triplet.GlobalID_Format;
import com.mgz.afp.triplets.Triplet.GlobalID_Use;
import com.mgz.afp.triplets.Triplet.MODCAInterchangeSet;
import com.mgz.afp.triplets.Triplet.MODCAInterchangeSet.MODCAInterchangeSet_Identifier;
import com.mgz.afp.triplets.Triplet.MODCAInterchangeSet.MODCAInterchangeSet_Type;
import com.mgz.afp.triplets.Triplet.MappingOption;
import com.mgz.afp.triplets.Triplet.AreaDefinition;
import com.mgz.afp.triplets.Triplet.CMRTagFidelity;
import com.mgz.afp.triplets.Triplet.ColorFidelity;
import com.mgz.afp.triplets.Triplet.ColorManagementResourceDescriptor;
import com.mgz.afp.triplets.Triplet.ColorSpecification;
import com.mgz.afp.triplets.Triplet.DeviceAppearance;
import com.mgz.afp.triplets.Triplet.MappingOption.DataObjecMapingOption;
import com.mgz.afp.triplets.Triplet.PresentationSpaceMixingRule;
import com.mgz.afp.triplets.Triplet.PresentationSpaceResetMixing;
import com.mgz.afp.triplets.Triplet.RenderingIntent;
import com.mgz.afp.triplets.Triplet.TonerSaver;
import com.mgz.afp.triplets.Triplet.TripletID;
import com.mgz.afp.triplets.Triplet.ImageResolution;
import com.mgz.afp.triplets.Triplet.MeasurementUnits;
import com.mgz.afp.triplets.Triplet.ObjectAreaSize;
import com.mgz.afp.triplets.Triplet.ObjectClassification;
import com.mgz.afp.triplets.Triplet.ResourceObjectType;
import com.mgz.afp.triplets.Triplet.ExtendedResourceLocalIdentifier;
import com.mgz.afp.triplets.Triplet.ResourceLocalIdentifier;
import com.mgz.afp.triplets.Triplet.ResourceSectionNumber;
import com.mgz.afp.triplets.Triplet.ObjectByteOffset;
import com.mgz.afp.triplets.Triplet.DescriptorPosition;
import com.mgz.afp.triplets.Triplet.ObjectByteExtent;
import com.mgz.afp.triplets.Triplet.ObjectStructuredFieldOffset;
import com.mgz.afp.triplets.Triplet.ObjectStructuredFieldExtent;
import com.mgz.afp.triplets.Triplet.ObjectOffset;
import com.mgz.afp.triplets.Triplet.ObjectOffset.ObjectType;
import com.mgz.afp.triplets.Triplet.AttributeValue;
import com.mgz.afp.triplets.Triplet.MediaEjectControl;
import com.mgz.afp.triplets.Triplet.MediaEjectControl.MediaEjectControlType;
import com.mgz.afp.triplets.Triplet.PageOverlayConditionalProcessing;
import com.mgz.afp.triplets.Triplet.PageOverlayConditionalProcessing.PageOverlayType;
import com.mgz.afp.triplets.Triplet.ResourceUsageAttribute;
import com.mgz.afp.triplets.Triplet.ResourceUsageAttribute.FrequencyOfUse;
import com.mgz.afp.triplets.Triplet.MediumMapPageNumber;
import com.mgz.afp.triplets.Triplet.ObjectCount;
import com.mgz.afp.triplets.Triplet.LocalObjectDateAndTimeStamp;
import com.mgz.afp.triplets.Triplet.LocalObjectDateAndTimeStamp.DateAndTimeStampType;
import com.mgz.afp.triplets.Triplet.MediumOrientation;
import com.mgz.afp.triplets.Triplet.MediumOrientation.MediumOrientationValue;
import com.mgz.afp.triplets.Triplet.ResourceObjectInclude;
import com.mgz.afp.triplets.Triplet.UniversalDateAndTimeStamp;
import com.mgz.afp.triplets.Triplet.UniversalDateAndTimeStamp.TimeZone;
import com.mgz.afp.triplets.Triplet.AttributeQualifier;
import com.mgz.afp.triplets.Triplet.PagePositionInformation;
import com.mgz.afp.triplets.Triplet.ParameterValue;
import com.mgz.afp.triplets.Triplet.ParameterValue.ParameterSyntax;
import com.mgz.afp.triplets.Triplet.PresentationControl;
import com.mgz.afp.triplets.Triplet.LocaleSelector;
import com.mgz.afp.triplets.Triplet.LocaleSelector.LocalSelectorFlag;
import com.mgz.afp.triplets.Triplet.FontFidelity;
import com.mgz.afp.triplets.Triplet.FinishingOperation;
import com.mgz.afp.triplets.Triplet.FinishingOperation.OperationType;
import com.mgz.afp.triplets.Triplet.FinishingOperation.ReferenceCorner;
import com.mgz.afp.triplets.Triplet.TextFidelity;
import com.mgz.afp.triplets.Triplet.MediaFidelity;
import com.mgz.afp.triplets.Triplet.FinishingFidelity;
import com.mgz.afp.triplets.Triplet.UP3iFinishingOperation;
import com.mgz.afp.triplets.Triplet.EncodingSchemeID;
import com.mgz.afp.triplets.Triplet.FontResolutionAndMetricTechnology;
import com.mgz.afp.triplets.Triplet.FontResolutionAndMetricTechnology.MetricTechnology;
import com.mgz.afp.triplets.Triplet.DataObjectFontDescriptor;
import com.mgz.afp.triplets.Triplet.KeepGroupTogether;
import com.mgz.afp.triplets.Triplet.MODCAFunctionSet;
import com.mgz.afp.triplets.Triplet.SetupName;
import com.mgz.afp.triplets.Triplet.TripletExtender;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TripletRoundTripTest {

    @Test
    public void testCodedGraphicCharacterSetGlobalIDRoundTrip() throws Exception {
        CodedGraphicCharacterSetGlobalID triplet = new CodedGraphicCharacterSetGlobalID();
        triplet.setTripletID(TripletID.CodedGraphicCharacterSetGlobalID);

        // Length(1) | ID(1) | GCSGID(2) | CPGID/CCSID(2)
        byte[] data = new byte[] {
            0x06, (byte) 0x01, 0x00, 0x01, 0x01, (byte) 0xF4 // Length 6, ID 0x01, GCSGID 1, CPGID 500
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFullyQualifiedNameRoundTrip() throws Exception {
        FullyQualifiedName triplet = new FullyQualifiedName();
        triplet.setTripletID(TripletID.FullyQualifiedName);

        // Length(1) | ID(1) | Type(1) | Format(1) | Name(variable)
        // Type 0x01 (ReplaceFirstGIDName), Format 0x00 (CharacterString), Name "TEST"
        byte[] data = new byte[] {
            0x08, 0x02, 0x01, 0x00, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // "TEST" in EBCDIC
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMappingOptionRoundTrip() throws Exception {
        MappingOption triplet = new MappingOption();
        triplet.setTripletID(TripletID.MappingOption);

        // Length(1) | ID(1) | Option(1)
        byte[] data = new byte[] {
            0x03, 0x04, 0x20 // Length 3, ID 0x04, ScaleToFit 0x20
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMODCAInterchangeSetRoundTrip() throws Exception {
        MODCAInterchangeSet triplet = new MODCAInterchangeSet();
        triplet.setTripletID(TripletID.MODCAInterchangeSet);

        // Length(1) | ID(1) | Type(1) | Identifier(2)
        byte[] data = new byte[] {
            0x05, 0x18, 0x01, 0x0D, 0x00 // Length 5, ID 0x18, Type Presentation 0x01, Identifier MODCA_IS3 0x0D00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testCommentRoundTrip() throws Exception {
        Comment triplet = new Comment();
        triplet.setTripletID(TripletID.Comment);

        // Length(1) | ID(1) | Comment(variable)
        // Comment "TEST" in EBCDIC: 0xE3, 0xC5, 0xE2, 0xE3
        byte[] data = new byte[] {
            0x06, 0x65, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFontDescriptorSpecificationRoundTrip() throws Exception {
        FontDescriptorSpecification triplet = new FontDescriptorSpecification();
        triplet.setTripletID(TripletID.FontDescriptorSpecification);

        // Length(1) | ID(1) | Weight(1) | Width(1) | Height(2) | Width(2) | Flags(1)
        byte[] data = new byte[] {
            0x09, 0x1F, 0x05, 0x05, 0x00, 0x78, 0x00, 0x48, 0x01
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFontCodedGraphicCharacterSetGlobalIDRoundTrip() throws Exception {
        FontCodedGraphicCharacterSetGlobalID triplet = new FontCodedGraphicCharacterSetGlobalID();
        triplet.setTripletID(TripletID.FontCodedGraphicCharacterSetGlobalID);

        // Length(1) | ID(1) | GCSGID(2) | CPGID(2)
        byte[] data = new byte[] {
            0x06, 0x20, 0x00, 0x01, 0x01, (byte) 0xF4 // GCSGID 1, CPGID 500
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testCharacterRotationRoundTrip() throws Exception {
        CharacterRotation triplet = new CharacterRotation();
        triplet.setTripletID(TripletID.CharacterRotation);

        // Length(1) | ID(1) | Rotation(2)
        byte[] data = new byte[] {
            0x04, 0x26, 0x2D, 0x00 // 90 degrees
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFontHorizontalScaleFactorRoundTrip() throws Exception {
        FontHorizontalScaleFactor triplet = new FontHorizontalScaleFactor();
        triplet.setTripletID(TripletID.FontHorizontalScaleFactor);

        // Length(1) | ID(1) | Factor(2)
        byte[] data = new byte[] {
            0x04, 0x5D, 0x03, (byte) 0xE8 // 1000
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testColorSpecificationRoundTrip() throws Exception {
        ColorSpecification triplet = new ColorSpecification();
        triplet.setTripletID(TripletID.ColorSpecification);

        // Length(1) | ID(1) | Reserved(1) | Space(1) | Reserved(4) | C1Size(1) | C2Size(1) | C3Size(1) | C4Size(1) | Value(3)
        byte[] data = new byte[] {
            0x0F, 0x4E, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08, 0x00, (byte) 0xFF, 0x00, 0x00 // RGB(1), 8-bit, Red
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testPresentationSpaceResetMixingRoundTrip() throws Exception {
        PresentationSpaceResetMixing triplet = new PresentationSpaceResetMixing();
        triplet.setTripletID(TripletID.PresentationSpaceResetMixing);

        // Length(1) | ID(1) | Flag(1)
        byte[] data = new byte[] {
            0x03, 0x70, (byte) 0x80 // ResetColor
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testPresentationSpaceMixingRuleRoundTrip() throws Exception {
        PresentationSpaceMixingRule triplet = new PresentationSpaceMixingRule();
        triplet.setTripletID(TripletID.PresentationSpaceMixingRule);

        // Length(1) | ID(1) | (Keyword(1) | Rule(1))*2
        byte[] data = new byte[] {
            0x06, 0x71, 0x70, 0x01, 0x71, 0x02 // BackOnBack Overpaint, BackOnFore Underpaint
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testTonerSaverRoundTrip() throws Exception {
        TonerSaver triplet = new TonerSaver();
        triplet.setTripletID(TripletID.TonerSaver);

        // Length(1) | ID(1) | Reserved(1) | Function(1) | Reserved(2)
        byte[] data = new byte[] {
            0x06, 0x74, 0x00, 0x01, 0x00, 0x00 // Activate
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testColorFidelityRoundTrip() throws Exception {
        ColorFidelity triplet = new ColorFidelity();
        triplet.setTripletID(TripletID.ColorFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(1) | Reporting(1) | Reserved(1) | Substitution(1) | Reserved(1)
        byte[] data = new byte[] {
            0x08, 0x75, 0x01, 0x00, 0x01, 0x00, 0x01, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testColorManagementResourceDescriptorRoundTrip() throws Exception {
        ColorManagementResourceDescriptor triplet = new ColorManagementResourceDescriptor();
        triplet.setTripletID(TripletID.ColorManagementResourceDescriptor);

        // Length(1) | ID(1) | Reserved(1) | Mode(1) | Scope(1)
        byte[] data = new byte[] {
            0x05, (byte) 0x91, 0x00, 0x01, 0x01 // Audit, DataObject
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testRenderingIntentRoundTrip() throws Exception {
        RenderingIntent triplet = new RenderingIntent();
        triplet.setTripletID(TripletID.RenderingIntent);

        // Length(1) | ID(1) | Reserved(2) | IOCA(1) | Container(1) | PTOCA(1) | GOCA(1) | Reserved(2)
        byte[] data = new byte[] {
            0x0A, (byte) 0x95, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testCMRTagFidelityRoundTrip() throws Exception {
        CMRTagFidelity triplet = new CMRTagFidelity();
        triplet.setTripletID(TripletID.CMRTagFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(1) | Reporting(1) | Reserved(2)
        byte[] data = new byte[] {
            0x07, (byte) 0x96, 0x01, 0x00, 0x01, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testDeviceAppearanceRoundTrip() throws Exception {
        DeviceAppearance triplet = new DeviceAppearance();
        triplet.setTripletID(TripletID.DeviceAppearance);

        // Length(1) | ID(1) | Reserved(1) | Appearance(1) | Reserved(3)
        byte[] data = new byte[] {
            0x07, (byte) 0x97, 0x00, 0x01, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMeasurementUnitsRoundTrip() throws Exception {
        MeasurementUnits triplet = new MeasurementUnits();
        triplet.setTripletID(TripletID.MeasurementUnits);

        // Length(1) | ID(1) | XBase(1) | YBase(1) | XUnits(2) | YUnits(2)
        byte[] data = new byte[] {
            0x08, 0x4B, 0x00, 0x00, 0x05, (byte) 0x78, 0x05, (byte) 0x78 // Inches10, 1440 units/base
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectAreaSizeRoundTrip() throws Exception {
        ObjectAreaSize triplet = new ObjectAreaSize();
        triplet.setTripletID(TripletID.ObjectAreaSize);

        // Length(1) | ID(1) | Type(1) | XSize(3) | YSize(3)
        byte[] data = new byte[] {
            0x09, 0x4C, 0x02, 0x00, 0x20, 0x00, 0x00, 0x30, 0x00 // Type 2, XSize 8192, YSize 12288
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testAreaDefinitionRoundTrip() throws Exception {
        AreaDefinition triplet = new AreaDefinition();
        triplet.setTripletID(TripletID.AreaDefinition);

        // Length(1) | ID(1) | Reserved(1) | XOrigin(3) | YOrigin(3) | XSize(3) | YSize(3)
        byte[] data = new byte[] {
            0x0F, 0x4D, 0x00, 0x00, 0x01, 0x00, 0x00, 0x02, 0x00, 0x00, 0x03, 0x00, 0x00, 0x04, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testImageResolutionRoundTrip() throws Exception {
        ImageResolution triplet = new ImageResolution();
        triplet.setTripletID(TripletID.ImageResolution);

        // Length(1) | ID(1) | Reserved(2) | XBase(1) | YBase(1) | XUnits(2) | YUnits(2)
        byte[] data = new byte[] {
            0x0A, (byte) 0x9A, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0x90, 0x00, (byte) 0x90 // 144 PPI
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectClassificationRoundTrip() throws Exception {
        ObjectClassification triplet = new ObjectClassification();
        triplet.setTripletID(TripletID.ObjectClassification);

        // Length(1) | ID(1) | Reserved(1) | Class(1) | Reserved(2) | Flags(2) | RegObjID(16)
        byte[] data = new byte[] {
            0x18, 0x10, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testResourceObjectTypeRoundTrip() throws Exception {
        ResourceObjectType triplet = new ResourceObjectType();
        triplet.setTripletID(TripletID.ResourceObjectType);

        // Length(1) | ID(1) | ObjType(1) | Constant(variable)
        byte[] data = new byte[] {
            0x04, 0x21, 0x02, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testExtendedResourceLocalIdentifierRoundTrip() throws Exception {
        ExtendedResourceLocalIdentifier triplet = new ExtendedResourceLocalIdentifier();
        triplet.setTripletID(TripletID.ExtendedResourceLocalIdentifier);

        // Length(1) | ID(1) | ResType(1) | LID(4)
        byte[] data = new byte[] {
            0x07, 0x22, 0x40, 0x00, 0x00, 0x00, 0x01 // MediaTypeResource, LID 1
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testResourceLocalIdentifierRoundTrip() throws Exception {
        ResourceLocalIdentifier triplet = new ResourceLocalIdentifier();
        triplet.setTripletID(TripletID.ResourceLocalIdentifier);

        // Length(1) | ID(1) | ResType(1) | LID(1)
        byte[] data = new byte[] {
            0x04, 0x24, 0x02, 0x01 // PageOverlay, LID 1
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testResourceSectionNumberRoundTrip() throws Exception {
        ResourceSectionNumber triplet = new ResourceSectionNumber();
        triplet.setTripletID(TripletID.ResourceSectionNumber);

        // Length(1) | ID(1) | RSN(1)
        byte[] data = new byte[] {
            0x03, 0x25, 0x01
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectByteOffsetRoundTrip() throws Exception {
        ObjectByteOffset triplet = new ObjectByteOffset();
        triplet.setTripletID(TripletID.ObjectByteOffset);

        // Length(1) | ID(1) | OffsetLow(4) | [OffsetHigh(4)]
        byte[] data = new byte[] {
            0x06, 0x2D, 0x00, 0x00, 0x10, 0x00 // Offset 4096
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);

        byte[] dataLarge = new byte[] {
            0x0A, 0x2D, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02
        };
        RoundTripTestUtils.assertRoundTrip(triplet, dataLarge);
    }

    @Test
    public void testDescriptorPositionRoundTrip() throws Exception {
        DescriptorPosition triplet = new DescriptorPosition();
        triplet.setTripletID(TripletID.DescriptorPosition);

        // Length(1) | ID(1) | ID(1)
        byte[] data = new byte[] {
            0x03, 0x43, 0x05
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectByteExtentRoundTrip() throws Exception {
        ObjectByteExtent triplet = new ObjectByteExtent();
        triplet.setTripletID(TripletID.ObjectByteExtent);

        // Length(1) | ID(1) | Low(4) | High(4)
        byte[] data = new byte[] {
            0x0A, 0x57, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectStructuredFieldOffsetRoundTrip() throws Exception {
        ObjectStructuredFieldOffset triplet = new ObjectStructuredFieldOffset();
        triplet.setTripletID(TripletID.ObjectStructuredFieldOffset);

        // Length(1) | ID(1) | Low(4) | [High(4)]
        byte[] data = new byte[] {
            0x06, 0x58, 0x00, 0x00, 0x00, 0x01
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectStructuredFieldExtentRoundTrip() throws Exception {
        ObjectStructuredFieldExtent triplet = new ObjectStructuredFieldExtent();
        triplet.setTripletID(TripletID.ObjectStructuredFieldExtent);

        // Length(1) | ID(1) | Low(4) | [High(4)]
        byte[] data = new byte[] {
            0x06, 0x59, 0x00, 0x00, 0x00, 0x05
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectOffsetRoundTrip() throws Exception {
        ObjectOffset triplet = new ObjectOffset();
        triplet.setTripletID(TripletID.ObjectOffset);

        // Length(1) | ID(1) | Type(1) | Reserved(1) | Low(4) | [High(4)]
        byte[] data = new byte[] {
            0x08, 0x5A, (byte) 0xA8, 0x00, 0x00, 0x00, 0x00, 0x0A // Document, 10 preceding
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testAttributeValueRoundTrip() throws Exception {
        AttributeValue triplet = new AttributeValue();
        triplet.setTripletID(TripletID.AttributeValue);

        // Length(1) | ID(1) | Reserved(2) | Value(variable)
        byte[] data = new byte[] {
            0x08, 0x36, 0x00, 0x00, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // "TEST"
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMediaEjectControlRoundTrip() throws Exception {
        MediaEjectControl triplet = new MediaEjectControl();
        triplet.setTripletID(TripletID.MediaEjectControl);

        // Length(1) | ID(1) | Reserved(1) | Control(1)
        byte[] data = new byte[] {
            0x04, 0x45, 0x00, 0x01 // EjectToNewSheet
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testPageOverlayConditionalProcessingRoundTrip() throws Exception {
        PageOverlayConditionalProcessing triplet = new PageOverlayConditionalProcessing();
        triplet.setTripletID(TripletID.PageOverlayConditionalProcessing);

        // Length(1) | ID(1) | Type(1) | [Level(1)]
        byte[] data = new byte[] {
            0x04, 0x46, 0x01, 0x0A // Annotation, Level 10
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testResourceUsageAttributeRoundTrip() throws Exception {
        ResourceUsageAttribute triplet = new ResourceUsageAttribute();
        triplet.setTripletID(TripletID.ResourceUsageAttribute);

        // Length(1) | ID(1) | Frequency(1)
        byte[] data = new byte[] {
            0x03, 0x47, 0x00 // Low
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMediumMapPageNumberRoundTrip() throws Exception {
        MediumMapPageNumber triplet = new MediumMapPageNumber();
        triplet.setTripletID(TripletID.MediumMapPageNumber);

        // Length(1) | ID(1) | Number(4)
        byte[] data = new byte[] {
            0x06, 0x56, 0x00, 0x00, 0x00, 0x05
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectCountRoundTrip() throws Exception {
        ObjectCount triplet = new ObjectCount();
        triplet.setTripletID(TripletID.ObjectCount);

        // Length(1) | ID(1) | Type(1) | Reserved(1) | Low(4) | [High(4)]
        byte[] data = new byte[] {
            0x08, 0x5E, (byte) 0xFA, 0x00, 0x00, 0x00, 0x00, 0x14 // 20 objects
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testLocalObjectDateAndTimeStampRoundTrip() throws Exception {
        LocalObjectDateAndTimeStamp triplet = new LocalObjectDateAndTimeStamp();
        triplet.setTripletID(TripletID.LocalObjectDateAndTimeStamp);

        // Length(1) | ID(1) | Type(1) | YearH(1) | YearT(2) | Day(3) | Hour(2) | Minute(2) | Second(2) | Hund(2)
        byte[] data = new byte[] {
            0x11, 0x62, 0x00, 0x14, 0x00, 0x18, 0x00, 0x00, 0x01, 0x0C, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMediumOrientationRoundTrip() throws Exception {
        MediumOrientation triplet = new MediumOrientation();
        triplet.setTripletID(TripletID.MediumOrientation);

        // Length(1) | ID(1) | Orientation(1)
        byte[] data = new byte[] {
            0x03, 0x68, 0x01 // Landscape
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testResourceObjectIncludeRoundTrip() throws Exception {
        ResourceObjectInclude triplet = new ResourceObjectInclude();
        triplet.setTripletID(TripletID.ResourceObjectInclude);

        // Length(1) | ID(1) | Type(1) | Name(8) | X(3) | Y(3) | [Orient(2)]
        byte[] data = new byte[] {
            0x11, 0x6C, (byte) 0xDF,
            (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3, 0x40, 0x40, 0x40, 0x40, // "TEST"
            0x00, 0x01, 0x00, 0x00, 0x02, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testUniversalDateAndTimeStampRoundTrip() throws Exception {
        UniversalDateAndTimeStamp triplet = new UniversalDateAndTimeStamp();
        triplet.setTripletID(TripletID.UniversalDateAndTimeStamp);

        // Length(1) | ID(1) | Reserved(1) | Year(2) | Month(1) | Day(1) | Hour(1) | Min(1) | Sec(1) | TZ(1) | DH(1) | DM(1)
        byte[] data = new byte[] {
            0x0D, 0x72, 0x00, 0x07, (byte) 0xE8, 0x0C, 0x01, 0x0C, 0x00, 0x00, 0x00, 0x00, 0x00 // 2024-12-01 12:00:00 UTC
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testAttributeQualifierRoundTrip() throws Exception {
        AttributeQualifier triplet = new AttributeQualifier();
        triplet.setTripletID(TripletID.AttributeQualifier);

        // Length(1) | ID(1) | Seq(4) | Level(4)
        byte[] data = new byte[] {
            0x0A, (byte) 0x80, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testPagePositionInformationRoundTrip() throws Exception {
        PagePositionInformation triplet = new PagePositionInformation();
        triplet.setTripletID(TripletID.PagePositionInformation);

        // Length(1) | ID(1) | Number(1)
        byte[] data = new byte[] {
            0x03, (byte) 0x81, 0x05
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testParameterValueRoundTrip() throws Exception {
        ParameterValue triplet = new ParameterValue();
        triplet.setTripletID(TripletID.ParameterValue);

        // Length(1) | ID(1) | Reserved(1) | Syntax(1) | Value(variable)
        byte[] data = new byte[] {
            0x08, (byte) 0x82, 0x00, 0x05, (byte) 0xE3, (byte) 0xC5, (byte) 0xE2, (byte) 0xE3 // CharacterString "TEST"
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testPresentationControlRoundTrip() throws Exception {
        PresentationControl triplet = new PresentationControl();
        triplet.setTripletID(TripletID.PresentationControl);

        // Length(1) | ID(1) | Flags(1)
        byte[] data = new byte[] {
            0x03, (byte) 0x83, (byte) 0xC0 // DoNotView, NoIndexing
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testLocaleSelectorRoundTrip() throws Exception {
        LocaleSelector triplet = new LocaleSelector();
        triplet.setTripletID(TripletID.LocaleSelector);

        // Length(1) | ID(1) | Reserved(1) | Flags(1) | Lang(8) | Script(8) | Region(8) | Reserved(8) | [Variant(variable)]
        byte[] data = new byte[] {
            0x24, (byte) 0x8C, 0x00, 0x00,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFontFidelityRoundTrip() throws Exception {
        FontFidelity triplet = new FontFidelity();
        triplet.setTripletID(TripletID.FontFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(4)
        byte[] data = new byte[] {
            0x07, 0x78, 0x01, 0x00, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFinishingOperationRoundTrip() throws Exception {
        FinishingOperation triplet = new FinishingOperation();
        triplet.setTripletID(TripletID.FinishingOperation);

        // Length(1) | ID(1) | Type(1) | Reserved(2) | Corner(1) | Count(1) | Offset(2) | [Positions(2*N)]
        byte[] data = new byte[] {
            0x09, (byte) 0x85, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);

        byte[] dataWithPositions = new byte[] {
            0x0D, (byte) 0x85, 0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x01, 0x00, 0x02
        };
        RoundTripTestUtils.assertRoundTrip(triplet, dataWithPositions);
    }

    @Test
    public void testTextFidelityRoundTrip() throws Exception {
        TextFidelity triplet = new TextFidelity();
        triplet.setTripletID(TripletID.TextFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(1) | Reporting(1) | Reserved(2)
        byte[] data = new byte[] {
            0x07, (byte) 0x86, 0x01, 0x00, 0x01, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMediaFidelityRoundTrip() throws Exception {
        MediaFidelity triplet = new MediaFidelity();
        triplet.setTripletID(TripletID.MediaFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(1) | Reporting(1) | Reserved(2)
        byte[] data = new byte[] {
            0x07, (byte) 0x87, 0x01, 0x00, 0x01, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFinishingFidelityRoundTrip() throws Exception {
        FinishingFidelity triplet = new FinishingFidelity();
        triplet.setTripletID(TripletID.FinishingFidelity);

        // Length(1) | ID(1) | Continuation(1) | Reserved(1) | Reporting(1) | Reserved(2)
        byte[] data = new byte[] {
            0x07, (byte) 0x88, 0x01, 0x00, 0x01, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testUP3iFinishingOperationRoundTrip() throws Exception {
        UP3iFinishingOperation triplet = new UP3iFinishingOperation();
        triplet.setTripletID(TripletID.UP3iFinishingOperation);

        // Length(1) | ID(1) | Seq(1) | Reserved(1) | Data(variable)
        byte[] data = new byte[] {
            0x06, (byte) 0x8E, 0x01, 0x00, 0x01, 0x02
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testEncodingSchemeIDRoundTrip() throws Exception {
        EncodingSchemeID triplet = new EncodingSchemeID();
        triplet.setTripletID(TripletID.EncodingSchemeID);

        // Length(1) | ID(1) | CodePage(2) | [UserData(2)]
        byte[] data = new byte[] {
            0x04, 0x50, 0x61, 0x00 // EBCDIC Presentation, Fixed Single-Byte
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFontResolutionAndMetricTechnologyRoundTrip() throws Exception {
        FontResolutionAndMetricTechnology triplet = new FontResolutionAndMetricTechnology();
        triplet.setTripletID(TripletID.FontResolutionAndMetricTechnology);

        // Length(1) | ID(1) | Tech(1) | Base(1) | Units(2)
        byte[] data = new byte[] {
            0x06, (byte) 0x84, 0x01, 0x00, 0x00, (byte) 0xF0 // Fixed, Inches10, 240 units
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testDataObjectFontDescriptorRoundTrip() throws Exception {
        DataObjectFontDescriptor triplet = new DataObjectFontDescriptor();
        triplet.setTripletID(TripletID.DataObjectFontDescriptor);

        // Length(1) | ID(1) | Flags(1) | Tech(1) | Size(2) | Scale(2) | Orient(2) | Env(2) | Ident(2) | Res(2)
        byte[] data = new byte[] {
            0x10, (byte) 0x8B, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testMODCAFunctionSetRoundTrip() throws Exception {
        MODCAFunctionSet triplet = new MODCAFunctionSet();
        triplet.setTripletID(TripletID.MODCAFunctionSet);

        // Length(1) | ID(1) | Reserved(2) | FctSetID(2)
        byte[] data = new byte[] {
            0x06, (byte) 0x8F, 0x00, 0x00, 0x00, 0x01
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testKeepGroupTogetherRoundTrip() throws Exception {
        KeepGroupTogether triplet = new KeepGroupTogether();
        triplet.setTripletID(TripletID.KeepGroupTogether);

        // Length(1) | ID(1) | Reserved(2) | GrpFnct(1)
        byte[] data = new byte[] {
            0x05, (byte) 0x9D, 0x00, 0x00, 0x01
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testSetupNameRoundTrip() throws Exception {
        SetupName triplet = new SetupName();
        triplet.setTripletID(TripletID.SetupName);

        // Length(1) | ID(1) | Reserved(2) | Name(variable)
        // Name "Test" in UTF-16BE: 0x00, 0x54, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74
        byte[] data = new byte[] {
            0x0C, (byte) 0x9E, 0x00, 0x00, 0x00, 0x54, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testTripletExtenderRoundTrip() throws Exception {
        TripletExtender triplet = new TripletExtender();
        triplet.setTripletID(TripletID.TripletExtender);

        // Length(1) | ID(1) | Reserved(2) | Data(variable)
        byte[] data = new byte[] {
            0x06, (byte) 0xFF, 0x00, 0x00, 0x01, 0x02
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testTextOrientationRoundTrip() throws Exception {
        // Reference: modca-reference-10/Appendix_C.md - Text Orientation Triplet X'1D'
        Triplet.TextOrientation triplet = new Triplet.TextOrientation();
        triplet.setTripletID(TripletID.TextOrientation);
        // Length 6, ID 0x1D, I-axis 0, B-axis 90 (0x2D00)
        byte[] data = new byte[] { 0x06, 0x1D, 0x00, 0x00, 0x2D, 0x00 };
        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testLineDataObjectPositionMigrationRoundTrip() throws Exception {
        // Reference: modca-reference-10/Chapter_5.md - Line Data Object Position Migration Triplet X'27' [MODCA-5-035]
        Triplet.LineDataObjectPositionMigration triplet = new Triplet.LineDataObjectPositionMigration();
        triplet.setTripletID(TripletID.LineDataObjectPositionMigration);
        // Length 3, ID 0x27, 0x00 (Standard_0)
        byte[] data = new byte[] { 0x03, 0x27, 0x00 };
        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectOriginIdentifierRoundTrip() throws Exception {
        // Reference: modca-reference-10/Appendix_C.md - Object Origin Identifier Triplet X'64' [MODCA-C-041]
        Triplet.ObjectOriginIdentifier triplet = new Triplet.ObjectOriginIdentifier();
        triplet.setTripletID(TripletID.ObjectOriginIdentifier);
        // Length 61, ID 0x64, System 0x01 (MVS), SysID "SYSTEM11", MedID "VOL001", DSID "DATA.SET.NAME"
        byte[] data = new byte[61];
        data[0] = 61;
        data[1] = 0x64;
        data[2] = 0x01;
        // systemIDSerialNumber (8 bytes) - EBCDIC
        System.arraycopy(new byte[] { (byte)0xE2, (byte)0xE8, (byte)0xE2, (byte)0xE3, (byte)0xC5, (byte)0xD4, (byte)0xF1, (byte)0xF1 }, 0, data, 3, 8); // SYSTEM11
        // storageMediaID (6 bytes)
        System.arraycopy(new byte[] { (byte)0xE5, (byte)0xD6, (byte)0xD3, (byte)0xF0, (byte)0xF0, (byte)0xF1 }, 0, data, 11, 6); // VOL001
        // dataSetID (44 bytes)
        Arrays.fill(data, 17, 61, (byte) 0x40);
        byte[] dsid = "DATA.SET.NAME".getBytes(java.nio.charset.Charset.forName("Cp500"));
        System.arraycopy(dsid, 0, data, 17, dsid.length);

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testIMMInsertionTripletRoundTrip() throws Exception {
        // Reference: modca-reference-10/Appendix_C.md - IMM Insertion Triplet X'73' [MODCA-C-144]
        Triplet.IMMInsertionTriplet triplet = new Triplet.IMMInsertionTriplet();
        triplet.setTripletID(TripletID.IMMInsertionTriplet);
        // Length 4, ID 0x73, Reserved 0x0000
        byte[] data = new byte[] { 0x04, 0x73, 0x00, 0x00 };
        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testObjectContainerPresentationSpaceSizeRoundTrip() throws Exception {
        // Reference: modca-reference-10/Chapter_6.md - Object Container Presentation Space Size Triplet X'9C' [MODCA-6-858]
        Triplet.ObjectContainerPresentationSpaceSize triplet = new Triplet.ObjectContainerPresentationSpaceSize();
        triplet.setTripletID(TripletID.ObjectContainerPresentationSpaceSize);
        // Length 5, ID 0x9C, Reserved 0x0000, PDFSize 0x01 (MediaBox)
        byte[] data = new byte[] { 0x05, (byte) 0x9C, 0x00, 0x00, 0x01 };
        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFullyQualifiedNameURLRoundTrip() throws Exception {
        // Reference: specifications/markdown/modca-reference-10/Chapter_6.md [MODCA-6-069]
        FullyQualifiedName triplet = new FullyQualifiedName();
        triplet.setTripletID(TripletID.FullyQualifiedName);

        // Length(1) | ID(1) | Type(1) | Format(1) | URL(variable)
        // Type 0xDE (DataObjectExternalResourceReference), Format 0x20 (URL), URL "http://example.com"
        String url = "http://example.com";
        byte[] urlBytes = url.getBytes(java.nio.charset.StandardCharsets.US_ASCII);
        byte[] data = new byte[4 + urlBytes.length];
        data[0] = (byte) (4 + urlBytes.length);
        data[1] = 0x02;
        data[2] = (byte) 0xDE;
        data[3] = 0x20;
        System.arraycopy(urlBytes, 0, data, 4, urlBytes.length);

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testColorSpecificationCMYKRoundTrip() throws Exception {
        // Reference: specifications/markdown/modca-reference-10/Chapter_6.md [MODCA-6-365]
        ColorSpecification triplet = new ColorSpecification();
        triplet.setTripletID(TripletID.ColorSpecification);

        // Length(1) | ID(1) | Reserved(1) | Space(1) | Reserved(4) | C1Size(1) | C2Size(1) | C3Size(1) | C4Size(1) | Value(4)
        // CMYK(4), 8-bit components, Cyan(100%), Magenta(0%), Yellow(0%), Black(0%)
        byte[] data = new byte[] {
            0x10, 0x4E, 0x00, 0x04, 0x00, 0x00, 0x00, 0x00, 0x08, 0x08, 0x08, 0x08, (byte) 0xFF, 0x00, 0x00, 0x00
        };

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }

    @Test
    public void testFullyQualifiedNameOIDRoundTrip() throws Exception {
        // Reference: specifications/markdown/modca-reference-10/Chapter_6.md [MODCA-6-066]
        FullyQualifiedName triplet = new FullyQualifiedName();
        triplet.setTripletID(TripletID.FullyQualifiedName);

        // Length(1) | ID(1) | Type(1) | Format(1) | OID(variable)
        // Type 0x11 (MediaTypeReference), Format 0x10 (OID), OID 06082B06010401813D01
        byte[] oidBytes = new byte[] { 0x06, 0x08, 0x2B, 0x06, 0x01, 0x04, 0x01, (byte) 0x81, 0x3D, 0x01 };
        byte[] data = new byte[4 + oidBytes.length];
        data[0] = (byte) (4 + oidBytes.length);
        data[1] = 0x02;
        data[2] = 0x11;
        data[3] = 0x10;
        System.arraycopy(oidBytes, 0, data, 4, oidBytes.length);

        RoundTripTestUtils.assertRoundTrip(triplet, data);
    }
}
