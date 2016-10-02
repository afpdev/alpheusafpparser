/*
Copyright 2015 Rudolf Fiala

This file is part of Alpheus AFP Parser.

Alpheus AFP Parser is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Alpheus AFP Parser is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Alpheus AFP Parser.  If not, see <http://www.gnu.org/licenses/>
*/
package com.mgz.afp.triplets;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.enums.AFPColorSpace;
import com.mgz.afp.enums.AFPObjectType;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.enums.AFPUnitBase;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet.ColorFidelity.ExceptionContinuationRule;
import com.mgz.afp.triplets.Triplet.ColorFidelity.ExceptionReportingRule;
import com.mgz.afp.triplets.Triplet.ResourceObjectType.ROT_ObjectType;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@AFPType
public abstract class Triplet implements IAFPDecodeableWriteable {
  public static short UNFORTUNATE_TRIPLETID = 0x21;
  @AFPField(isEditable = false, isHidden = true)
  short length;
  @AFPField(isHidden = true)
  TripletID tripletID;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    this.length = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    tripletID = TripletID.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 1, 1));
  }

  public short getLength() {
    return length;
  }

  public void setLength(short length) {
    this.length = length;
  }

  public TripletID getTripletID() {
    return tripletID;
  }

  public void setTripletID(TripletID tripletID) {
    this.tripletID = tripletID;
  }

  public enum TripletID {
    Undefined(0x00),
    CodedGraphicCharacterSetGlobalID(0x01),
    FullyQualifiedName(0x02),
    MappingOption(0x04),
    ObjectClassification(0x10),
    MODCAInterchangeSet(0x18),

    TextOrientation(0x1D), // Retired.
    LineDataObjectPositionMigration(0x27), // Retired.


    FontDescriptorSpecification(0x1F),
    FontCodedGraphicCharacterSetGlobalID(0x20),


    /**
     * MODCA page 379.<br><br> The Resource Object Type triplet identifies the type of object
     * enveloped by the Begin Resource (BRS) and End Resource (ERS) structured fields.<br> <br>
     * <b>UNFORTUNATLY:</b><br> A similar triplet, the Object Function Set Specification triplet,
     * that <b>unfortunately also uses triplet ID X'21'</b>, is retired but is still used on the BDT
     * structured field; see “Object Function Set Specification Triplet X'21'” on page 570.
     */
    ResourceObjectType(UNFORTUNATE_TRIPLETID),
    ObjectFunctionSetSpecification_Retired(UNFORTUNATE_TRIPLETID),


    ExtendedResourceLocalIdentifier(0x22),
    ResourceLocalIdentifier(0x24),
    ResourceSectionNumber(0x25),
    CharacterRotation(0x26),
    ObjectByteOffset(0x2D),
    AttributeValue(0x36),
    DescriptorPosition(0x43),
    MediaEjectControl(0x45),
    PageOverlayConditionalProcessing(0x46),
    ResourceUsageAttribute(0x47),
    MeasurementUnits(0x4B),
    ObjectAreaSize(0x4C),
    AreaDefinition(0x4D),
    ColorSpecification(0x4E),
    EncodingSchemeID(0x50),
    MediumMapPageNumber(0x56),
    ObjectByteExtent(0x57),
    ObjectStructuredFieldOffset(0x58),
    ObjectStructuredFieldExtent(0x59),
    ObjectOffset(0x5A),
    FontHorizontalScaleFactor(0x5D),
    ObjectCount(0x5E),
    LocalObjectDateAndTimeStamp(0x62),
    ObjectChecksum(0x63), // Retired.
    ObjectOriginIdentifier(0x64), // Retired.

    Comment(0x65),
    MediumOrientation(0x68),
    ResourceObjectInclude(0x6C),
    PresentationSpaceResetMixing(0x70),
    PresentationSpaceMixingRule(0x71),
    UniversalDateAndTimeStamp(0x72),

    IMMInsertionTriplet(0x73), // Retired.


    TonerSaver(0x74),
    ColorFidelity(0x75),
    FontFidelity(0x78),
    AttributeQualifier(0x80),
    PagePositionInformation(0x81),
    ParameterValue(0x82),
    PresentationControl(0x83),
    FontResolutionAndMetricTechnology(0x84),
    FinishingOperation(0x85),
    TextFidelity(0x86),
    MediaFidelity(0x87),
    FinishingFidelity(0x88),
    DataObjectFontDescriptor(0x8B),
    LocaleSelector(0x8C),
    UP3iFinishingOperation(0x8E),
    ColorManagementResourceDescriptor(0x91),
    RenderingIntent(0x95),
    CMRTagFidelity(0x96),
    DeviceAppearance(0x97),
    ImageResolution(0x9A),
    ObjectContainerPresentationSpaceSize(0x9C);
    int code;

    TripletID(int code) {
      this.code = code;
    }

    public static TripletID valueOf(short codeByte) throws AFPParserException {
      for (TripletID id : values()) if (id.code == codeByte) return id;
      throw new AFPParserException(TripletID.class.getSimpleName() + ": the ID 0x" + Integer.toHexString(codeByte) + " is undefined.");
    }

    public int toByte() {
      return code;
    }
  }

  /**
   * Specifies how the GID will be used, eg. in Fully Qualified Name Triplet 0x02.
   */
  public enum GlobalID_Use {
    ReplaceFirstGIDBame(0x01),
    FontFamilyName(0x07),
    FontTypefaceName(0x08),
    MODCAResourceHierarchyReference(0x09),
    BeginResourceGroupReference(0x0A),
    AttributeGID(0x0B),
    ProcessElementGID(0x0C),
    BeginPageGroupReference(0x0D),
    MediaTypeReference(0x11),
    MediaDestinationReference(0x12),
    ColorManagementResourceReference(0x41),
    DataObjectFontBaseFontIdentifier(0x6E),
    DataObjectFontLinkedFontIdentifier(0x7E),
    BeginDocumentReference(0x83),
    ResourceObjectReference(0x84),
    CodePageNameReference(0x85),
    FontCharacterSetNameReference(0x86),
    BeginPageReference(0x87),
    BeginMediumMapReference(0x8D),
    CodedFontNameReference(0x8E),
    BeginDocumentIndexReference(0x98),
    BeginOverlayReference(0xB0),
    DataObjectInternalResourceReference(0xBE),
    IndexElementGID(0xCA),
    OtherObjectDataReference(0xCE),
    DataObjectExternalResourceReference(0xDE);
    int code;

    GlobalID_Use(int code) {
      this.code = code;
    }

    public static GlobalID_Use valueOf(short codeByte) {
      for (GlobalID_Use gidu : values()) if (gidu.code == codeByte) return gidu;
      return null;
    }

    public int toByte() {
      return code;
    }
  }

  /**
   * Specifies the GID format
   */
  public enum GlobalID_Format {
    CharacterString(0x00),
    OID(0x10),
    URL(0x20);
    int code;

    GlobalID_Format(int code) {
      this.code = code;
    }

    public static GlobalID_Format valueOf(byte codeByte) {
      for (GlobalID_Format f : values()) return f;
      return null;
    }

    public int toByte() {
      return code;
    }
  }

  /**
   * The {@link Undefined} triplet is used when problem occur with corrupted AFP Data. The {@link
   * #tripletData} field contains the entire of the data. The {@link TripletID} field contains
   * {@link TripletID#Undefined} and is not read from the data given to {@link #decodeAFP(byte[],
   * int, int, AFPParserConfiguration)}, nor is it written by {@link #writeAFP(OutputStream,
   * AFPParserConfiguration)}. <br> <br> The method {@link #writeAFP(OutputStream,
   * AFPParserConfiguration)} simple writes the data contained in field {@link #tripletData}. In
   * addition it resets the value of  the length field.
   */
  public static class Undefined extends Triplet {
    byte[] tripletData;
    AFPParserException parsingException;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      length = UtilBinaryDecoding.parseShort(sfData, offset, 1);
      if (length > 2) {
        tripletData = new byte[length];
        System.arraycopy(sfData, offset, tripletData, 0, tripletData.length);
      } else {
        tripletData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (tripletData != null) {
        length = (short) tripletData.length;
        os.write(tripletData);
      } else {
        os.write(0);
        os.write(0);
      }
    }


    public byte[] getTripletData() {
      return tripletData;
    }

    public void setTripletData(byte[] tripletData) {
      this.tripletData = tripletData;
    }

    public AFPParserException getParsingException() {
      return parsingException;
    }

    public void setParsingException(AFPParserException parsingException) {
      this.parsingException = parsingException;
    }
  }

  /**
   * MO:DCA, page 349.<br><br> The Coded Graphic Character Set Global Identifier (CGCSGID) triplet
   * is used to establish the values of the code page and character set for interpretation of all
   * structured field parameters having a CHAR data type, such as name parameters, except where such
   * parameters define a fixed encoding. An example of a parameter that defines its own encoding is
   * the character string specified with a Fully Qualified Name (X'02') triplet using FQNFmt = X'20'
   * - URL, which is encoded using the US-ASCII coded character set.
   */
  public static class CodedGraphicCharacterSetGlobalID extends Triplet {
    int graphicCharacterSetGlobalID;
    int codePageGlobalID_codedCharacterSetID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      graphicCharacterSetGlobalID = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
      codePageGlobalID_codedCharacterSetID = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(UtilBinaryDecoding.intToByteArray(graphicCharacterSetGlobalID, 2));
      baos.write(UtilBinaryDecoding.intToByteArray(codePageGlobalID_codedCharacterSetID, 2));

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }


    /**
     * Returns true, if this in the "CCSID Form", meaning the {@link #codePageGlobalID_codedCharacterSetID}
     * contains a codedCharacterSetID (CCSID) instead of a code page global ID.
     */
    public boolean isCCSIDForm() {
      return graphicCharacterSetGlobalID == 0;
    }
  }

  /**
   * MO:DCA, page 353.<br><br> The Fully Qualified Name triplet enables the identification and
   * referencing of objects using Global Identifiers (GIDs).
   */
  public static class FullyQualifiedName extends Triplet {
    GlobalID_Use type;
    GlobalID_Format format;
    byte[] nameAsBytes;
    String nameAsString;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      type = GlobalID_Use.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      format = GlobalID_Format.valueOf(sfData[offset + 3]);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      nameAsBytes = new byte[actualLength - 4];
      System.arraycopy(sfData, offset + 4, nameAsBytes, 0, nameAsBytes.length);
      nameAsString = new String(nameAsBytes, config.getAfpCharSet());
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      baos.write(tripletID.toByte());
      baos.write(type.toByte());
      baos.write(format.toByte());
      if (nameAsBytes != null) baos.write(nameAsBytes);
      else baos.write(nameAsString.getBytes(config.getAfpCharSet()));

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }

    public GlobalID_Use getType() {
      return type;
    }

    public void setType(GlobalID_Use type) {
      this.type = type;
    }

    public GlobalID_Format getFormat() {
      return format;
    }

    public void setFormat(GlobalID_Format format) {
      this.format = format;
    }

    public byte[] getNameAsBytes() {
      return nameAsBytes;
    }

    public void setNameAsBytes(byte[] nameAsBytes) {
      this.nameAsBytes = nameAsBytes;
    }

    public String getNameAsString() {
      return nameAsString;
    }

    public void setNameAsString(String nameAsString) {
      this.nameAsString = nameAsString;
    }
  }

  /**
   * MO:DCA, page 365.<br><br> The Mapping Option is used to specify the mapping of a data object
   * presentation space to an object area.
   */
  public static class MappingOption extends Triplet {
    DataObjecMapingOption dataObjecMapingOption;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      dataObjecMapingOption = DataObjecMapingOption.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(tripletID.toByte());
      os.write(dataObjecMapingOption.toByte());
    }

    public enum DataObjecMapingOption {
      Position(0x00),
      PositionAndTrim(0x10),
      ScaleToFit(0x20),
      CenterAndTrim(0x30),
      ImagePointToPel(0x41),
      ImagePointToPelWithDoubleDot(0x42),
      ReplicateAndTrim(0x50),
      ScaleToFill(0x60),
      UP3iPrintDataMapping(0x70);
      int code;

      DataObjecMapingOption(int code) {
        this.code = code;
      }

      public static DataObjecMapingOption valueOf(byte codeByte) {
        for (DataObjecMapingOption o : values()) if (o.code == codeByte) return o;
        return null;
      }

      public int toByte() {
        return code;
      }
    }
  }

  /**
   * MO:DCA, page 368.<br><br> The Object Classification is used to classify and identify object
   * data. The object data may or may not be defined by an AFP presentation architecture.
   */
  public static class ObjectClassification extends Triplet {
    byte reserved2 = 0x00;
    ObjectClass objectClass;
    byte[] reserved4_5 = new byte[2];
    EnumSet<StructureFlag> structureFlags;
    byte[] registeredObjectID; // 16 bytes.
    String objectTypeName;
    String objectVersion;
    String companyName;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 24);
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      objectClass = ObjectClass.valueOf(sfData[offset + 3]);
      reserved4_5 = new byte[2];
      System.arraycopy(sfData, offset + 4, reserved4_5, 0, reserved4_5.length);
      structureFlags = StructureFlag.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 6, 2));
      registeredObjectID = new byte[16];
      System.arraycopy(sfData, offset + 8, registeredObjectID, 0, registeredObjectID.length);

      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 24) {
        objectTypeName = new String(sfData, offset + 24, 32, config.getAfpCharSet());
      } else {
        objectTypeName = null;
      }
      if (actualLength > 56) {
        objectVersion = new String(sfData, offset + 56, 8, config.getAfpCharSet());
      } else {
        objectVersion = null;
      }
      if (actualLength > 64) {
        companyName = new String(sfData, offset + 64, 32, config.getAfpCharSet());
      } else {
        companyName = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(reserved2);
      baos.write(objectClass.toByte());
      baos.write(reserved4_5);
      baos.write(StructureFlag.toBytes(structureFlags));
      if (objectTypeName != null) {
        baos.write(UtilCharacterEncoding.stringToByteArray(objectTypeName, config.getAfpCharSet(), 32, Constants.EBCDIC_BLANK));
        if (objectVersion != null) {
          baos.write(UtilCharacterEncoding.stringToByteArray(objectVersion, config.getAfpCharSet(), 8, Constants.EBCDIC_BLANK));
          if (companyName != null) {
            baos.write(UtilCharacterEncoding.stringToByteArray(companyName, config.getAfpCharSet(), 32, Constants.EBCDIC_BLANK));
          }
        }
      }

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());


    }

    /**
     * Sets the given {@link StructureFlag} and unsets mutual exclusive flags in {@link
     * #structureFlags}.
     */
    public void setStructureFlag(StructureFlag flag) {
      StructureFlag.setFlag(structureFlags, flag);
    }


    /**
     * Specifies the object class based on differentiators such as temporal characteristics and
     * presentation form.
     */
    public enum ObjectClass {
      TimeInvariantPaginatedPresentationObject(0x01),
      TimeVariantPresentationObject(0x10),
      ExecutableProgram(0x20),
      SetupFile(0x30),
      SecondaryResource(0x40),
      DataObjectFont(0x41);
      int code;

      ObjectClass(int code) {
        this.code = code;
      }

      public static ObjectClass valueOf(byte codeByte) throws AFPParserException {
        for (ObjectClass oc : values()) if (oc.code == codeByte) return oc;
        throw new AFPParserException(ObjectClass.class.getSimpleName() + ": object class 0x" + Integer.toHexString(codeByte) + " is unknwon.");
      }

      public int toByte() {
        return code;
      }
    }

    public enum StructureFlag implements IMutualExclusiveGroupedFlag {
      // b 0-1
      OC_Reserved(0),
      OC_DataNotCarriedInObjectContainer(0),
      OC_UnknownContainerStructure(0),
      OC_DataCarriedInObjectContainer(0),
      // b 2-3
      OEG_Reserved(1),
      OEG_NotIncluded(1),
      OEG_Unknown(1),
      OEG_Included(1),
      // b 4-5
      OCD_Reserved(2),
      OCD_DataNotCarriedInOCD(2),
      OCD_UnknownIfOCDCarriesData(2),
      OCD_DataCarriedInOCD(2);

      static MutualExclusiveGroupedFlagHandler<StructureFlag> handler = new MutualExclusiveGroupedFlagHandler<StructureFlag>();
      int group;

      StructureFlag(int code) {
        this.group = code;
      }

      public static EnumSet<StructureFlag> valueOf(int codeByte) {
        EnumSet<StructureFlag> result = EnumSet.noneOf(StructureFlag.class);

        if ((codeByte & 0xC000) == 0) result.add(OC_Reserved);
        else if ((codeByte & 0x8000) != 0 && (codeByte & 0x4000) != 0)
          result.add(OC_DataCarriedInObjectContainer);
        else if ((codeByte & 0x8000) != 0) result.add(OC_UnknownContainerStructure);
        else if ((codeByte & 0x4000) != 0) result.add(OC_DataNotCarriedInObjectContainer);

        if ((codeByte & 0x3000) == 0) result.add(OEG_Reserved);
        else if ((codeByte & 0x2000) != 0 && (codeByte & 0x1000) != 0)
          result.add(OEG_Included);
        else if ((codeByte & 0x2000) != 0) result.add(OEG_Unknown);
        else if ((codeByte & 0x1000) != 0) result.add(OEG_NotIncluded);

        if ((codeByte & 0x0C00) == 0) result.add(OCD_Reserved);
        else if ((codeByte & 0x0800) != 0 && (codeByte & 0x0400) != 0)
          result.add(OCD_DataCarriedInOCD);
        else if ((codeByte & 0x0800) != 0) result.add(OCD_UnknownIfOCDCarriesData);
        else if ((codeByte & 0x0400) != 0) result.add(OCD_DataNotCarriedInOCD);

        return result;
      }

      public static byte[] toBytes(EnumSet<StructureFlag> flags) {
        int result = 0;

        if (flags.contains(OC_DataCarriedInObjectContainer)) result |= 0xC000;
        else if (flags.contains(OC_UnknownContainerStructure)) result |= 0x8000;
        else if (flags.contains(OC_DataNotCarriedInObjectContainer)) result |= 0x4000;

        if (flags.contains(OEG_Included)) result |= 0x3000;
        else if (flags.contains(OEG_Unknown)) result |= 0x2000;
        else if (flags.contains(OEG_NotIncluded)) result |= 0x1000;

        if (flags.contains(OCD_DataCarriedInOCD)) result |= 0x0C00;
        else if (flags.contains(OCD_UnknownIfOCDCarriesData)) result |= 0x0800;
        else if (flags.contains(OCD_DataNotCarriedInOCD)) result |= 0x0400;

        return UtilBinaryDecoding.intToByteArray(result, 2);
      }

      /**
       * Sets the given flag and unsets mutual exclusive flags.
       */
      public static void setFlag(EnumSet<StructureFlag> flags, StructureFlag flag) {
        handler.setFlag(flags, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }

  /**
   * MO:DCA, page 372.<br><br> The MO:DCA Interchange Set triplet identifies the interchange set and
   * the data stream type.
   */
  public static class MODCAInterchangeSet extends Triplet {
    MODCAInterchangeSet_Type type;
    MODCAInterchangeSet_Identifier identifier;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 5);
      super.decodeAFP(sfData, offset, length, config);
      type = MODCAInterchangeSet_Type.valueOf(sfData[offset + 2]);
      identifier = MODCAInterchangeSet_Identifier.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 3, 2));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(type.toByte());
      baos.write(identifier.toBytes());

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }


    public enum MODCAInterchangeSet_Type {
      Presentation;

      public static MODCAInterchangeSet_Type valueOf(byte typeCode) throws AFPParserException {
        if (typeCode == 0x01) return Presentation;
        throw new AFPParserException(MODCAInterchangeSet_Type.class.getSimpleName() + ": type code 0x" + Integer.toHexString(typeCode) + " is unknown.");
      }

      public int toByte() {
        return 0x01;
      }
    }

    public enum MODCAInterchangeSet_Identifier {
      MODCA_IS1(0x0900),
      MODCA_IS2_Retired(0x0C00),
      MODCA_IS3(0x0D00);
      int code;

      MODCAInterchangeSet_Identifier(int code) {
        this.code = code;
      }

      public static MODCAInterchangeSet_Identifier valueOf(short code) throws AFPParserException {
        for (MODCAInterchangeSet_Identifier id : values()) if (id.code == code) return id;
        throw new AFPParserException(MODCAInterchangeSet_Identifier.class.getSimpleName() + ": type code 0x" + Integer.toHexString(code) + " is unknown.");
      }

      public byte[] toBytes() {
        return UtilBinaryDecoding.intToByteArray(code, 2);
      }
    }

  }

  /**
   * MO:DCA, page 374.<br><br> The Font Descriptor Specification triplet specifies the attributes of
   * the desired font in a coded font reference.
   */
  public static class FontDescriptorSpecification extends Triplet {
    FDS_FontWeigthClass fontWeigthClass;
    FDS_FontWidthClass fontWidthClass;
    short fontHeight;
    short fontWidth;
    EnumSet<FDS_FontDsFlag> fontDsFlags;
    byte[] reserved9_18;
    EnumSet<FDS_FontUsFlag> fontUsFlags;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 9);
      super.decodeAFP(sfData, offset, length, config);
      fontWeigthClass = FDS_FontWeigthClass.valueOf(sfData[offset + 2]);
      fontWidthClass = FDS_FontWidthClass.valueOf(sfData[offset + 3]);
      fontHeight = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      fontWidth = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
      fontDsFlags = FDS_FontDsFlag.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 8, 1));

      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 9) {
        reserved9_18 = new byte[10];
        System.arraycopy(sfData, offset + 9, reserved9_18, 0, reserved9_18.length);
      } else {
        reserved9_18 = null;
      }
      if (actualLength > 19) {
        fontUsFlags = FDS_FontUsFlag.valueOf(sfData[offset + 19]);
      } else {
        fontUsFlags = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(fontWeigthClass.toByte());
      baos.write(fontWidthClass.toByte());
      baos.write(UtilBinaryDecoding.shortToByteArray(fontHeight, 2));
      baos.write(UtilBinaryDecoding.shortToByteArray(fontWidth, 2));
      baos.write(FDS_FontDsFlag.toByte(fontDsFlags));
      if (reserved9_18 != null) {
        baos.write(reserved9_18);
        if (fontUsFlags != null) baos.write(FDS_FontUsFlag.toByte(fontUsFlags));
      }

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }


    public enum FDS_FontWeigthClass {
      NotSpecified(0x00),
      UltraLight(0x01),
      ExtraLight(0x02),
      Light(0x03),
      SemiLight(0x04),
      Medium_Normal(0x05),
      SemiBold(0x06),
      Bold(0x07),
      ExtraBold(0x08),
      UltraBold(0x09);
      int code;

      FDS_FontWeigthClass(int code) {
        this.code = code;
      }

      public static FDS_FontWeigthClass valueOf(byte codeByte) throws AFPParserException {
        for (FDS_FontWeigthClass wc : values()) if (wc.ordinal() == codeByte) return wc;
        throw new AFPParserException(FDS_FontWeigthClass.class.getSimpleName() + ": class code 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        return ordinal();
      }
    }

    public enum FDS_FontWidthClass {
      NotSpecified(0x00),
      UltraCondensed(0x01),
      ExtraCondensed(0x02),
      Condensed(0x03),
      SemiCondensed(0x04),
      Medium_Normal(0x05),
      Semi_Expanded(0x06),
      Expanded(0x07),
      Extra_Expanded(0x08),
      Ultra_Expanded(0x09);
      int code;

      FDS_FontWidthClass(int code) {
        this.code = code;
      }

      public static FDS_FontWidthClass valueOf(byte codeByte) throws AFPParserException {
        for (FDS_FontWidthClass wc : values()) if (wc.ordinal() == codeByte) return wc;
        throw new AFPParserException(FDS_FontWidthClass.class.getSimpleName() + ": class code 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        return ordinal();
      }
    }

    public enum FDS_FontDsFlag implements IMutualExclusiveGroupedFlag {
      NoItalicCharacters(0),
      ItalicCharacters(0),
      NoUnderscoredCharacters(1),
      UnderscoredCharacters(1),
      // bit 2 is reserved.
      NoHollowCharacters(3),
      HollowCharacters(3),
      NoOverstruckCharacters(4),
      OverstruckCharacters(4),
      UniformlySpacedCharacters(5),
      ProportionallyCharacters(5),
      NoPairwiseKernedCharacters(6),
      PairwiseKernedCharacters(6),
      ParameterIsNotSpecified(7),
      ParameterIsSpecified(7);

      static MutualExclusiveGroupedFlagHandler<FDS_FontDsFlag> handler = new MutualExclusiveGroupedFlagHandler<FDS_FontDsFlag>();
      int group;

      FDS_FontDsFlag(int group) {
        this.group = group;
      }

      public static EnumSet<FDS_FontDsFlag> valueOf(short flagByte) {
        EnumSet<FDS_FontDsFlag> result = EnumSet.noneOf(FDS_FontDsFlag.class);

        if ((flagByte & 0x80) == 0) result.add(NoItalicCharacters);
        else result.add(ItalicCharacters);
        if ((flagByte & 0x40) == 0) result.add(NoUnderscoredCharacters);
        else result.add(UnderscoredCharacters);

        if ((flagByte & 0x10) == 0) result.add(NoHollowCharacters);
        else result.add(HollowCharacters);
        if ((flagByte & 0x08) == 0) result.add(NoOverstruckCharacters);
        else result.add(OverstruckCharacters);
        if ((flagByte & 0x04) == 0) result.add(UniformlySpacedCharacters);
        else result.add(ProportionallyCharacters);
        if ((flagByte & 0x02) == 0) result.add(NoPairwiseKernedCharacters);
        else result.add(PairwiseKernedCharacters);
        if ((flagByte & 0x01) == 0) result.add(ParameterIsNotSpecified);
        else result.add(ParameterIsSpecified);

        return result;
      }

      public static int toByte(EnumSet<FDS_FontDsFlag> flags) {
        int result = 0;

        if (flags.contains(ItalicCharacters)) result |= 0x80;
        if (flags.contains(UnderscoredCharacters)) result |= 0x40;

        if (flags.contains(HollowCharacters)) result |= 0x10;
        if (flags.contains(OverstruckCharacters)) result |= 0x08;
        if (flags.contains(ProportionallyCharacters)) result |= 0x04;
        if (flags.contains(PairwiseKernedCharacters)) result |= 0x02;
        if (flags.contains(ParameterIsSpecified)) result |= 0x01;

        return result;
      }

      public static void setFlag(EnumSet<FDS_FontDsFlag> flags, FDS_FontDsFlag flag) {
        handler.setFlag(flags, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }

    public enum FDS_FontUsFlag implements IMutualExclusiveGroupedFlag {
      // bit 0 is reserved.
      FontType_BitmapFont(1),
      FontType_OutlineOrVector(1),
      TransformFont_WillNotBeTransformed(2),
      TransformFont_MayBeTransformed(2)
      // bits 3-7 are reserved.
      ;
      static MutualExclusiveGroupedFlagHandler<FDS_FontUsFlag> handler = new MutualExclusiveGroupedFlagHandler<FDS_FontUsFlag>();
      int group;

      FDS_FontUsFlag(int group) {
        this.group = group;
      }

      public static EnumSet<FDS_FontUsFlag> valueOf(byte flagByte) {
        EnumSet<FDS_FontUsFlag> result = EnumSet.noneOf(FDS_FontUsFlag.class);

        if ((flagByte & 0x40) == 0) result.add(FontType_BitmapFont);
        else result.add(FontType_OutlineOrVector);
        if ((flagByte & 0x20) == 0) result.add(TransformFont_WillNotBeTransformed);
        else result.add(TransformFont_MayBeTransformed);

        return result;
      }

      public static int toByte(EnumSet<FDS_FontUsFlag> flags) {
        int result = 0;

        if (flags.contains(FontType_OutlineOrVector)) result |= 0x40;
        if (flags.contains(TransformFont_MayBeTransformed)) result |= 0x20;

        return result;
      }

      public static void setFlag(EnumSet<FDS_FontUsFlag> flags, FDS_FontUsFlag flag) {
        handler.setFlag(flags, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }

  }

  /**
   * MO:DCA, page 378.<br><br> The Font Coded Graphic Character Set Global Identifier triplet is
   * used to specify the code page and character set for a coded font.
   */
  public static class FontCodedGraphicCharacterSetGlobalID extends Triplet {
    int codedGraphicCharacterSetGlobalID;
    int codePageGlobalID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      codedGraphicCharacterSetGlobalID = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
      codePageGlobalID = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(UtilBinaryDecoding.intToByteArray(codedGraphicCharacterSetGlobalID, 2));
      baos.write(UtilBinaryDecoding.intToByteArray(codePageGlobalID, 2));

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }
  }

  /**
   * MODCA page 379.<br><br> The Resource Object Type triplet identifies the type of object
   * enveloped by the Begin Resource (BRS) and End Resource (ERS) structured fields.<br> <br>
   * <b>UNFORTUNATLY:</b><br> A similar triplet, the Object Function Set Specification triplet, that
   * <b>unfortunately also uses triplet ID X'21'</b>, is retired but is still used on the BDT
   * structured field; see “Object Function Set Specification Triplet X'21'” on page 570.
   */
  public static class ResourceObjectType extends Triplet {
    ROT_ObjectType objectType;
    byte[] constantData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      objectType = ROT_ObjectType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      constantData = new byte[actualLength - 3];
      System.arraycopy(sfData, offset + 3, constantData, 0, constantData.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(objectType.toByte());
      baos.write(constantData);

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }

    /**
     * Unfortunately, this type is different to {@link AFPObjectType}.
     */
    public enum ROT_ObjectType {
      PresentationText(0x02),
      GraphicsObject_GOCA(0x03),
      BarCodeObject_BCOCA(0x05),
      ImageObject_IOCA(0x06),
      FontCharacterSetObject(0x40),
      CodePageObject(0x41),
      CodedFontObject(0x42),
      ObjectContainer(0x92),
      DocumentObject(0xA8),
      PageSegmentObject(0xFB),
      OverlayObject(0xFC),
      Reserved(0xFD),
      FormMapObject(0xFE);

      int code;

      ROT_ObjectType(int code) {
        this.code = code;
      }

      public static ROT_ObjectType valueOf(short codeByte) throws AFPParserException {
        for (ROT_ObjectType t : values()) if (t.code == codeByte) return t;
        throw new AFPParserException(ROT_ObjectType.class.getSimpleName() + ": type 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        return code;
      }
    }
  }

  /**
   * RETIRED<br> The use of this triplet is restricted to the BDT structured field in the following
   * products: Pre-year 2012 AFP applications.<br> <br> The Object Function Set Specification
   * triplet is used to specify the Object Content Architecture (OCA) level for objects in a MO:DCA
   * document.
   */
  public static class ObjectFunctionSetSpecification_Retired extends Triplet {
    ROT_ObjectType objectType;
    byte ocaArchitectureLevel;
    int modcaFunctionSetIdentifier;
    OCAFunctionSet ocaFunctionSet;
    byte[] reserved;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      objectType = ROT_ObjectType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      ocaArchitectureLevel = sfData[offset + 3];
      modcaFunctionSetIdentifier = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2);
      ocaFunctionSet = OCAFunctionSet.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 6, 2));
      if (length > 8) {
        reserved = new byte[length - 8];
        System.arraycopy(sfData, offset + 8, reserved, 0, reserved.length);
      } else {
        reserved = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(objectType.toByte());
      baos.write(ocaArchitectureLevel);
      baos.write(UtilBinaryDecoding.intToByteArray(modcaFunctionSetIdentifier, 2));
      baos.write(ocaFunctionSet.toByte());
      if (reserved != null) baos.write(reserved);

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }


    public enum OCAFunctionSet {
      PTOCA_PT1_or_BCOCA_BCD1(0x0000),
      PTOCA_PT2_or_GOCA_DR2V0(0x4000),
      IOCA_FS10(0x8000);
      int code;

      OCAFunctionSet(int code) {
        this.code = code;
      }

      public static OCAFunctionSet valueOf(int code) throws AFPParserException {
        for (OCAFunctionSet fs : values()) if (fs.code == code) return fs;
        throw new AFPParserException(OCAFunctionSet.class.getSimpleName() + ": code 0x" + Integer.toHexString(code) + " is unknown.");
      }

      public int toByte() {
        return code;
      }
    }
  }

  /**
   * MODCA page 381.<br><br> The Extended Resource Local Identifier triplet specifies a resource
   * type and a four-byte local identifier or LID. The LID usually is associated with a specific
   * resource name by a map structured field, such as a Map Media Type structured field.
   */
  public static class ExtendedResourceLocalIdentifier extends Triplet {
    ERLI_ResourceType resourceType;
    long extendedResourceLocalID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 7);

      super.decodeAFP(sfData, offset, length, config);
      resourceType = ERLI_ResourceType.valueOf(sfData[offset + 2]);
      extendedResourceLocalID = UtilBinaryDecoding.parseLong(sfData, offset + 3, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(tripletID.toByte());

      baos.write(resourceType.toByte());
      baos.write(UtilBinaryDecoding.longToByteArray(extendedResourceLocalID, 4));

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());

    }

    /**
     * Specifies the resource type associated with the extended local ID.
     */
    public enum ERLI_ResourceType {
      IOBReference_Reserved,
      MediaTypeResource,
      MediaDestinationResource;

      public static ERLI_ResourceType valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x30) return IOBReference_Reserved;
        else if (codeByte == 0x40) return MediaTypeResource;
        else if (codeByte == 0x42) return MediaDestinationResource;
        else
          throw new AFPParserException(ERLI_ResourceType.class.getSimpleName() + ": resource type code 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        if (this == IOBReference_Reserved) return 0x30;
        else if (this == MediaTypeResource) return 0x40;
        else if (this == MediaDestinationResource) return 0x42;
        else return 0;
      }
    }
  }

  /**
   * MODCA page 383.<br><br> The Resource Local Identifier triplet may be used to specify a resource
   * type and a one-byte local identifier or LID. The LID usually is associated with a specific
   * resource name by a map structured field, such as a Map Coded Font structured field.
   */
  public static class ResourceLocalIdentifier extends Triplet {
    RLI_ResourceType resourceType;
    short resourceLocalID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 4);
      super.decodeAFP(sfData, offset, length, config);
      resourceType = RLI_ResourceType.valueOf(sfData[offset + 2]);
      resourceLocalID = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(tripletID.toByte());
      os.write(resourceType.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(resourceLocalID, 1));
    }

    public enum RLI_ResourceType {
      UsageDependent,
      PageOverlay,
      CodedFont,
      ColorAttributeTable;

      public static RLI_ResourceType valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x00) return UsageDependent;
        else if (codeByte == 0x02) return PageOverlay;
        else if (codeByte == 0x05) return CodedFont;
        else if (codeByte == 0x07) return ColorAttributeTable;
        else
          throw new AFPParserException(RLI_ResourceType.class.getSimpleName() + ": resource type code 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        if (this == UsageDependent) return 0x00;
        else if (this == PageOverlay) return 0x02;
        else if (this == CodedFont) return 0x05;
        else if (this == ColorAttributeTable) return 0x07;
        else return 0x00;
      }
    }
  }

  /**
   * MODCA page 385.<br><br> The Resource Section Number triplet specifies a coded font section
   * number. It may be used to select a single section of a double-byte coded font if less than the
   * entire double-byte coded font is required for processing. For a description of coded fonts see
   * the Font Object Content Architecture Reference.
   */
  public static class ResourceSectionNumber extends Triplet {
    short resourceSectionNumber;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 3);
      super.decodeAFP(sfData, offset, length, config);
      resourceSectionNumber = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(resourceSectionNumber, 1));
    }
  }

  /**
   * MODCA page 386.<br><br> The Character Rotation triplet is  used to specify character rotation
   * relative to the Character coordinate system. See  the Font Object Content Architecture
   * Reference for further information.
   */
  public static class CharacterRotation extends Triplet {
    AFPOrientation characterRotation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 4);
      super.decodeAFP(sfData, offset, length, config);
      characterRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 2, 2));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(tripletID.toByte());
      os.write(characterRotation.toBytes());
    }
  }

  /**
   * MODCA page 387.<br><br> The Object Byte Offset triplet is used to specify the byte offset of an
   * indexed object within a document.
   */
  public static class ObjectByteOffset extends Triplet {
    long byteOffset;
    Long byteOffsetHighOrder;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      byteOffset = UtilBinaryDecoding.parseLong(sfData, offset + 2, 4);
      if (length > 6) {
        byteOffsetHighOrder = UtilBinaryDecoding.parseLong(sfData, offset + 6, 4);
      } else {
        byteOffsetHighOrder = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (byteOffsetHighOrder == null ? 6 : 10);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.longToByteArray(byteOffset, 4));
      if (byteOffsetHighOrder != null)
        os.write(UtilBinaryDecoding.longToByteArray(byteOffsetHighOrder, 4));
    }
  }

  /**
   * MODCA page 388.<br><br> The Attribute Value triplet is used to specify a value for a document
   * attribute.
   */
  public static class AttributeValue extends Triplet {
    byte[] reserved2_3 = new byte[2];
    String attributeValue;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2_3 = new byte[2];
      System.arraycopy(sfData, offset + 2, reserved2_3, 0, 2);
      if (length > 4) {
        attributeValue = new String(sfData, offset + 4, length - 4, config.getAfpCharSet());
      } else {
        attributeValue = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      baos.write(tripletID.toByte());
      baos.write(reserved2_3);
      if (attributeValue != null) {
        baos.write(attributeValue.getBytes(config.getAfpCharSet()));
      }

      length = (short) (baos.size() + 1);
      os.write(UtilBinaryDecoding.shortToByteArray(length, 1));
      os.write(baos.toByteArray());
    }

    public byte[] getReserved2_3() {
      return reserved2_3;
    }

    public void setReserved2_3(byte[] reserved2_3) {
      this.reserved2_3 = reserved2_3;
    }

    public String getAttributeValue() {
      return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
      this.attributeValue = attributeValue;
    }
  }

  /**
   * MODCA page 389.<br><br> The Descriptor Position triplet is used to associate an Object Area
   * Position structured field with an Object Area Descriptor structured field.
   */
  public static class DescriptorPosition extends Triplet {
    short objectAreaDescriptorID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 3);
      super.decodeAFP(sfData, offset, length, config);
      objectAreaDescriptorID = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(objectAreaDescriptorID);
    }
  }

  /**
   * MODCA page 390.<br><br> The Media Eject Control triplet is used to specify the type of media
   * eject that is performed and the type of controls that are activated when a new medium map is
   * invoked and N-up partitioning is specified.
   */
  public static class MediaEjectControl extends Triplet {
    byte reserved2 = 0x00;
    MediaEjectControlType mediaEjectControl;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      mediaEjectControl = MediaEjectControlType.valueOf(sfData[offset + 3]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(mediaEjectControl.toByte());
    }

    public enum MediaEjectControlType {
      EjectToNewSheet,
      ConditionalEjectToNextPartition,
      ConditionalEjectToNextFrontsidePartition,
      ConditionalEjectToNextBacksidePartition;

      public static MediaEjectControlType valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x01) return EjectToNewSheet;
        else if (codeByte == 0x02) return ConditionalEjectToNextPartition;
        else if (codeByte == 0x03) return ConditionalEjectToNextFrontsidePartition;
        else if (codeByte == 0x04) return ConditionalEjectToNextBacksidePartition;
        else
          throw new AFPParserException(MediaEjectControlType.class.getSimpleName() + ": code byte 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == EjectToNewSheet) return 0x01;
        else if (this == ConditionalEjectToNextPartition) return 0x02;
        else if (this == ConditionalEjectToNextFrontsidePartition) return 0x03;
        else if (this == ConditionalEjectToNextBacksidePartition) return 0x04;
        else return 0;
      }
    }
  }

  /**
   * MODCA page 576.<br><br> The use of this triplet is restricted to products that generate or
   * process the retired MO:DCA interchange set MO:DCA IS/2. The Page Overlay Conditional Processing
   * triplet is used to identify the intended utilization of a page overlay as produced by a
   * generator. This triplet can also be used to define an overlay level that determines whether the
   * overlay is to be processed.
   */
  public static class PageOverlayConditionalProcessing extends Triplet {
    PageOverlayType pageOverlayType;
    Short levelOfOverlay;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      pageOverlayType = PageOverlayType.valueOf(sfData[offset + 2]);
      if (length > 3) {
        levelOfOverlay = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      } else {
        levelOfOverlay = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (levelOfOverlay == null ? 3 : 4);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(pageOverlayType.toByte());
      if (levelOfOverlay != null) os.write(levelOfOverlay);
    }

    public enum PageOverlayType {
      Normal,
      Annotation,
      Redaction,
      Highlight;

      public static PageOverlayType valueOf(byte codeByte) throws AFPParserException {
        for (PageOverlayType loo : values()) if (loo.ordinal() == codeByte) return loo;
        throw new AFPParserException(PageOverlayType.class.getSimpleName() + ": page overlay type 0x" + Integer.toHexString(codeByte) + " is unknown.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /**
   * MODCA page 578.<br><br> The use of this triplet is restricted to products that generate or
   * process the retired MO:DCA interchange set MO:DCA IS/2. The Resource Usage Attribute triplet
   * can be used for resource management. It is used with the Include Page Overlay and Map Page
   * Overlay structured fields to identify the approximate frequency with which an associated page
   * overlay is processed. This is indicated by assigning either a low or high value to this
   * triplet. The Resource Usage Attribute triplet has no processing semantics associated with it.
   */
  public static class ResourceUsageAttribute extends Triplet {
    FrequencyOfUse frequencyOfUse;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      frequencyOfUse = FrequencyOfUse.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(frequencyOfUse.toByte());
    }

    /**
     * Specifies the processing frequency of the associated page overlay.
     */
    public enum FrequencyOfUse {
      Low, High;

      public static FrequencyOfUse valueOf(byte codeByte) {
        if (codeByte == 0x00) return Low;
        else return High;
      }

      public int toByte() {
        if (this == Low) return 0x00;
        else return 0xFF;
      }
    }
  }

  /**
   * MODCA page 579<br><br>
   *
   * The use of this triplet is restricted to the BMO and BPS structured fields in external (print
   * file level) AFP resource groups for the following products: v PSF/MVS v PSF/VSE v RPM 2.0 v RPM
   * 3.0 v PSF/2 (DPF) v RMARK The Object Checksum object specifies a qualifier that can be used to
   * identify or fingerprint an object.
   */
  public static class ObjectChecksum extends Triplet {
    CheckSumFormat checksumFormat;
    int crcCheckSum;
    EnumSet<ChecksumFlag> objectCheckSumFlags;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 6);
      super.decodeAFP(sfData, offset, length, config);
      checksumFormat = CheckSumFormat.valueOf(sfData[offset + 2]);
      crcCheckSum = UtilBinaryDecoding.parseInt(sfData, offset + 3, 2);
      objectCheckSumFlags = ChecksumFlag.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 5, 1));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      this.length = 6;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(checksumFormat.toByte());
      os.write(UtilBinaryDecoding.intToByteArray(crcCheckSum, 2));
      os.write(ChecksumFlag.toByte(objectCheckSumFlags));
    }

    public CheckSumFormat getChecksumFormat() {
      return checksumFormat;
    }

    public void setChecksumFormat(CheckSumFormat checksumFormat) {
      this.checksumFormat = checksumFormat;
    }

    public int getCrcCheckSum() {
      return crcCheckSum;
    }

    public void setCrcCheckSum(int crcCheckSum) {
      this.crcCheckSum = crcCheckSum;
    }

    public EnumSet<ChecksumFlag> getObjectCheckSumFlags() {
      return objectCheckSumFlags;
    }

    public void setObjectCheckSumFlags(EnumSet<ChecksumFlag> objectCheckSumFlags) {
      this.objectCheckSumFlags = objectCheckSumFlags;
    }

    /**
     * Sets the given flag and un-sets all mutually exclusive flags.
     *
     * @param objectCheckSumFlags set of flags to change.
     * @param flag                to set
     */
    public void setObjectCheckSumFlag(EnumSet<ChecksumFlag> objectCheckSumFlags, ChecksumFlag flag) {
      ChecksumFlag.handler.setFlag(objectCheckSumFlags, flag);
    }

    public enum CheckSumFormat {
      ObjectCycleRedundancyCheck,
      Retired_PrivateUse;

      public static CheckSumFormat valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x01) return ObjectCycleRedundancyCheck;
        if (codeByte == 0x02) return Retired_PrivateUse;
        else
          throw new AFPParserException(CheckSumFormat.class.getSimpleName() + ": checksum format code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == ObjectCycleRedundancyCheck) return 0x01;
        else return 0x02;
      }
    }

    public enum ChecksumFlag implements IMutualExclusiveGroupedFlag {
      UsageScope_PublicUnlimited(0),
      UsageScope_PrivateLimited(0),
      ResourceRetention_SaveResource(1),
      ResourceRetention_DoNotSaveResource(1);
      public static final MutualExclusiveGroupedFlagHandler<ChecksumFlag> handler = new MutualExclusiveGroupedFlagHandler<ChecksumFlag>();
      int group;

      ChecksumFlag(int group) {
        this.group = group;
      }

      public static EnumSet<ChecksumFlag> valueOf(short codByte) {
        EnumSet<ChecksumFlag> result = EnumSet.noneOf(ChecksumFlag.class);

        if ((codByte & 0x80) == 0) result.add(UsageScope_PublicUnlimited);
        else result.add(UsageScope_PrivateLimited);
        if ((codByte & 0x40) == 0) result.add(ResourceRetention_SaveResource);
        else result.add(ResourceRetention_DoNotSaveResource);

        return result;
      }

      public static int toByte(EnumSet<ChecksumFlag> set) {
        int result = 0x00;

        if (set.contains(UsageScope_PrivateLimited)) result |= 0x80;
        if (set.contains(ResourceRetention_DoNotSaveResource)) result |= 0x40;

        return result;
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }

  /**
   * MODCA page 581. Retired function<br><br> The use of this triplet is restricted to the BMO and
   * BPS structured fields in external (print file level) AFP resource groups for the following
   * products: v PSF/MVS v PSF/VSE v RPM 2.0 v PSF/2 v RMARK The Object Origin Identifier triplet is
   * used to identify the system on which an object originated.
   */
  public static class ObjectOriginIdentifier extends Triplet {
    AFPSystem originationSystem;
    String systemIDSerialNumber;
    String storageMediaID;
    String dataSetID;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 61);

      super.decodeAFP(sfData, offset, length, config);
      originationSystem = AFPSystem.valueOf(sfData[offset + 2]);
      systemIDSerialNumber = new String(sfData, offset + 3, 8, config.getAfpCharSet());
      storageMediaID = new String(sfData, offset + 11, 6, config.getAfpCharSet());
      dataSetID = new String(sfData, offset + 17, 44, config.getAfpCharSet());

    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 61;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(originationSystem.toByte());
      os.write(UtilCharacterEncoding.stringToByteArray(systemIDSerialNumber, config.getAfpCharSet(), 8, Constants.EBCDIC_BLANK));
      os.write(UtilCharacterEncoding.stringToByteArray(storageMediaID, config.getAfpCharSet(), 6, Constants.EBCDIC_BLANK));
      os.write(UtilCharacterEncoding.stringToByteArray(dataSetID, config.getAfpCharSet(), 44, Constants.EBCDIC_BLANK));
    }

    public enum AFPSystem {
      MVS,
      VM,
      PC_DOS,
      VSE;

      public static AFPSystem valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x01) return MVS;
        else if (codeByte == 0x02) return VM;
        else if (codeByte == 0x03) return PC_DOS;
        else if (codeByte == 0x04) return VSE;
        throw new AFPParserException(AFPSystem.class.getSimpleName() + ": system code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }
  }

  /**
   * MODCA page 582, retired function.<br><br>
   *
   * The use of this triplet is restricted to the IMM structured field for the following products: v
   * AFP OnDemand v AFP Workbench The IMM Insertion triplet is used to indicate that the Invoke
   * Medium Map (IMM) structured field on which it is specified was inserted at the beginning of a
   * page group by a filtering application. The IMM was inserted between the BNG and the first BPG
   * in the group, but only if an IMM was not already specified there. The purpose of the inserted
   * IMM is to allow the page group to be processed in standalone fashion. This triplet is ignored
   * by presentation servers, and the IMM on which it is specified is processed as if the triplet
   * were absent. The presence of this triplet on an IMM may be used by an inverse filtering
   * application to remove the IMM when it is desired to present the complete document as it
   * appeared before the IMM was inserted.
   */
  public static class IMMInsertionTriplet extends Triplet {
    byte[] reserved2_3 = new byte[2];

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 4);
      super.decodeAFP(sfData, offset, length, config);
      reserved2_3 = new byte[2];
      System.arraycopy(sfData, offset + 2, reserved2_3, 0, reserved2_3.length);

    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2_3);
    }

  }

  /**
   * MODCA page 569, retired function.<br><br>
   *
   * Text Orientation Triplet X'1D' The use of this triplet is restricted to the MCF-2 structured
   * field for 3800 compatibility for the following products: v PSF/MVS v PSF/VM v PSF/VSE v PSF/400
   * v PSF/2 v Infoprint Manager (IPM) v 3800 printer v Applications that generate MCF-2s in
   * documents to be printed on the 3800 printer The Text Orientation triplet is used to specify the
   * text orientation for a coded font. When the MCF-2 structured field is used to reference
   * different sections of the same double-byte font, a Text Orientation (X'1D') triplet may be
   * specified in any of the repeating groups associated with the font and need only be specified in
   * one of the repeating groups. However, if specified in more than one of the associated repeating
   * groups, the value of all Text Orientation (X'1D') triplets must be identical.
   */
  public static class TextOrientation extends Triplet {
    AFPOrientation xOrientation;
    AFPOrientation yOrientation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 6);
      super.decodeAFP(sfData, offset, length, config);
      xOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 2, 2));
      yOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 4, 2));
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 6;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(xOrientation.toBytes());
      os.write(yOrientation.toBytes());
    }
  }

  /**
   * MODCA page 572, retired function.<br><br> The use of this triplet is restricted to the BBC,
   * BGR, BII, BIM, IPS structured fields for the migration of line-data containing bar code
   * objects, graphic objects, image objects, and page segments to MO:DCA document format. This
   * triplet may be specified on these structured fields only for objects that occur directly in a
   * page. The triplet may not be specified on objects in a resource group or in a resource library;
   * if it is specified, it is ignored. Triplet X'27' Syntax: Use of this triplet is restricted to
   * the following products: v ACIF v PSF/MVS v PSF/VM v PSF/VSE v PSF/2 v Infoprint Manager (IPM) v
   * PSF/400 v AFP Workbench
   */
  public static class LineDataObjectPositionMigration extends Triplet {
    LocationAndOrientation locationAndOrientation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 3);
      super.decodeAFP(sfData, offset, length, config);
      locationAndOrientation = LocationAndOrientation.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(locationAndOrientation.toByte());
    }

    public enum LocationAndOrientation {
      Standard_0,
      LowerLeft_270,
      LowerRight_180,
      UperRight_90;

      public static LocationAndOrientation valueOf(byte codeByte) throws AFPParserException {
        for (LocationAndOrientation lao : values())
          if (lao.ordinal() == codeByte) return lao;
        throw new AFPParserException(LocationAndOrientation.class.getSimpleName() + ": location/orientation code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return this.ordinal();
      }
    }
  }

  /**
   * MODCA, page 395.<br><br> The Measurement Units triplet is used to specify the units of measure
   * for a presentation space.
   */
  public static class MeasurementUnits extends Triplet {
    AFPUnitBase xUnitBase;
    AFPUnitBase yUnitBase;
    short xUnitsPerUnitbase;
    short yUnitsPerUnitbase;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 8);

      super.decodeAFP(sfData, offset, length, config);
      xUnitBase = AFPUnitBase.valueOf(sfData[offset + 2]);
      yUnitBase = AFPUnitBase.valueOf(sfData[offset + 3]);
      xUnitsPerUnitbase = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      yUnitsPerUnitbase = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);


    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 8;
      os.write(length);
      os.write(tripletID.toByte());

      os.write(xUnitBase.toByte());
      os.write(yUnitBase.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitbase, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitbase, 2));
    }

  }

  /**
   * MODCA, page 396.<br><br> The Object Area Sizeand Y directions. triplet is used to specify
   * theextent of an object area in the X
   */
  public static class ObjectAreaSize extends Triplet {
    byte sizeType_0x02;
    int xSize;
    int ySize;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 9);
      super.decodeAFP(sfData, offset, length, config);
      sizeType_0x02 = sfData[offset + 2];
      xSize = UtilBinaryDecoding.parseInt(sfData, offset + 3, 3);
      ySize = UtilBinaryDecoding.parseInt(sfData, offset + 6, 3);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 9;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(sizeType_0x02);
      os.write(UtilBinaryDecoding.intToByteArray(xSize, 3));
      os.write(UtilBinaryDecoding.intToByteArray(ySize, 3));
    }
  }

  /**
   * MOIDCA, page 397<br><br> The Area Definition triplet is used to define the position and size of
   * a rectangular area on a document component presentation space. The document component may be a
   * page or overlay, in which case the area is defined on the page or overlay presentation space,
   * or it may be a data object, in which case the area is defined on the object area presentation
   * space.
   */
  public static class AreaDefinition extends Triplet {
    byte reserved2 = 0x00;
    int xOrigin;
    int yOrigin;
    int xSize;
    int ySize;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      StructuredField.checkDataLength(sfData, offset, length, 15);
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      xOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 3, 3);
      yOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 6, 3);
      xSize = UtilBinaryDecoding.parseInt(sfData, offset + 9, 3);
      ySize = UtilBinaryDecoding.parseInt(sfData, offset + 12, 3);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 15;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
      os.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
      os.write(UtilBinaryDecoding.intToByteArray(xSize, 3));
      os.write(UtilBinaryDecoding.intToByteArray(ySize, 3));
    }

  }

  /**
   * MODCA, page 398.<br><br> The Color Specification triplet is used to specify a color value and
   * defines the color space and encoding for that value.
   */
  public static class ColorSpecification extends Triplet {
    byte reserved2 = 0x00;
    AFPColorSpace colorSpace;
    byte[] reserved4_7 = new byte[4];
    byte nrOfBitsComponent1;
    byte nrOfBitsComponent2;
    byte nrOfBitsComponent3;
    byte nrOfBitsComponent4;
    byte[] colorValue;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);

      reserved2 = sfData[offset + 2];
      colorSpace = AFPColorSpace.valueOf(sfData[offset + 3]);
      reserved4_7 = new byte[4];
      System.arraycopy(sfData, offset + 4, reserved4_7, 0, reserved4_7.length);
      nrOfBitsComponent1 = sfData[offset + 8];
      nrOfBitsComponent2 = sfData[offset + 9];
      nrOfBitsComponent3 = sfData[offset + 10];
      nrOfBitsComponent4 = sfData[offset + 11];
      colorValue = new byte[length - 12];
      System.arraycopy(sfData, offset + 12, colorValue, 0, colorValue.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (12 + colorValue.length);
      os.write(length);
      os.write(tripletID.toByte());

      os.write(reserved2);
      os.write(colorSpace.toByte());
      os.write(reserved4_7);
      os.write(nrOfBitsComponent1);
      os.write(nrOfBitsComponent2);
      os.write(nrOfBitsComponent3);
      os.write(nrOfBitsComponent4);
      os.write(colorValue);
    }
  }

  /**
   * MODCA, page 403.<br><br> The Encoding Scheme ID triplet is used to specify the encoding scheme
   * associated with a code page. It may optionally also specify the encoding scheme for the user
   * data.
   */
  public static class EncodingSchemeID extends Triplet {
    EnumSet<EncodingScheme> encodingSchemeForCodePage;
    EnumSet<EncodingScheme> encodingSchemeForUserData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      encodingSchemeForCodePage = EncodingScheme.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 2, 2));
      if (length > 4) {
        encodingSchemeForUserData = EncodingScheme.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 4, 2));
      } else {
        encodingSchemeForUserData = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (encodingSchemeForUserData == null ? 4 : 6);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(EncodingScheme.toBytes(encodingSchemeForCodePage));
      if (encodingSchemeForUserData != null) {
        os.write(EncodingScheme.toBytes(encodingSchemeForUserData));
      }
    }

    public EnumSet<EncodingScheme> getEncodingSchemeForCodePage() {
      return encodingSchemeForCodePage;
    }

    public void setEncodingSchemeForCodePage(
            EnumSet<EncodingScheme> encodingSchemeForCodePage) {
      this.encodingSchemeForCodePage = encodingSchemeForCodePage;
    }

    /**
     * Sets the given {@link EncodingScheme} and un-sets the mutual exclusive {@link EncodingScheme}
     * values.
     */
    public void setEncodingSchemeForCodePage(EncodingScheme encodingScheme) {
      if (encodingSchemeForCodePage == null)
        encodingSchemeForCodePage = EnumSet.noneOf(EncodingScheme.class);
      EncodingScheme.handler.setFlag(encodingSchemeForCodePage, encodingScheme);
    }

    public EnumSet<EncodingScheme> getEncodingSchemeForUserData() {
      return encodingSchemeForUserData;
    }

    public void setEncodingSchemeForUserData(
            EnumSet<EncodingScheme> encodingSchemeForUserData) {
      this.encodingSchemeForUserData = encodingSchemeForUserData;
    }

    public enum EncodingScheme implements IMutualExclusiveGroupedFlag {
      BasicEncoding_NotSpecified(0),
      BasicEncoding_IBMPC_Data(0),
      BasicEncoding_IBMPC_Display(0),
      BasicEncoding_EBCDIC_Presentation(0),
      BasicEncoding_UTF16(0),
      BasicEncoding_UnicodePresentation(0),

      NumberOfBytes_NotSpecified(1),
      NumberOfBytes_Fixed_SingleByte(1),
      NumberOfBytes_Fixed_DoubleByte(1),
      NumberOfBytes_UTFnVariable(1),

      CodeExtension_NotSpecified(2),
      CodeExtension_UTF8(2);

      public static final MutualExclusiveGroupedFlagHandler<EncodingScheme> handler = new MutualExclusiveGroupedFlagHandler<EncodingScheme>();
      int group;

      EncodingScheme(int code) {
        this.group = code;
      }

      public static EnumSet<EncodingScheme> valueOf(int code) throws AFPParserException {
        int basicEncoding = code >>> 12;
        int numberOfBytes = ((code >> 8) & 0x0F);
        int extension = code & 0xFF;

        EnumSet<EncodingScheme> result = EnumSet.noneOf(EncodingScheme.class);
        if (basicEncoding == 0x00) result.add(BasicEncoding_NotSpecified);
        else if (basicEncoding == 0x02) result.add(BasicEncoding_IBMPC_Data);
        else if (basicEncoding == 0x03) result.add(BasicEncoding_IBMPC_Display);
        else if (basicEncoding == 0x06) result.add(BasicEncoding_EBCDIC_Presentation);
        else if (basicEncoding == 0x07) result.add(BasicEncoding_UTF16);
        else if (basicEncoding == 0x08) result.add(BasicEncoding_UnicodePresentation);
        else
          throw new AFPParserException(EncodingScheme.class.getSimpleName() + ": basic encoding value 0x" + Integer.toHexString(basicEncoding) + " is undefined.");

        if (numberOfBytes == 0x00) result.add(NumberOfBytes_NotSpecified);
        else if (numberOfBytes == 0x01) result.add(NumberOfBytes_Fixed_SingleByte);
        else if (numberOfBytes == 0x02) result.add(NumberOfBytes_Fixed_SingleByte);
        else if (numberOfBytes == 0x08) result.add(NumberOfBytes_UTFnVariable);
        else
          throw new AFPParserException(EncodingScheme.class.getSimpleName() + ": number of bytes value 0x" + Integer.toHexString(numberOfBytes) + " is undefined.");

        if (extension == 0x00) result.add(CodeExtension_NotSpecified);
        else if (extension == 0x07) result.add(CodeExtension_UTF8);

        return result;

      }

      public static byte[] toBytes(EnumSet<EncodingScheme> flags) {
        int result = 0;

        if (flags.contains(BasicEncoding_IBMPC_Data)) result |= 0x02;
        else if (flags.contains(BasicEncoding_IBMPC_Display)) result |= 0x03;
        else if (flags.contains(BasicEncoding_EBCDIC_Presentation)) result |= 0x06;
        else if (flags.contains(BasicEncoding_UTF16)) result |= 0x07;
        else if (flags.contains(BasicEncoding_UnicodePresentation)) result |= 0x08;

        result <<= 4;

        if (flags.contains(NumberOfBytes_Fixed_SingleByte)) result |= 0x01;
        else if (flags.contains(NumberOfBytes_Fixed_DoubleByte)) result |= 0x02;
        else if (flags.contains(NumberOfBytes_UTFnVariable)) result |= 0x08;

        result <<= 8;

        if (flags.contains(CodeExtension_UTF8)) result |= 0x7;

        return UtilBinaryDecoding.intToByteArray(result, 2);
      }

      @Override
      public int getGroup() {
        return group;
      }

    }


  }

  /**
   * MODCA, page 406.<br><br> The Medium Map Page Number triplet is used to specify the sequence
   * number of the page in the set of sequential pages whose presentation is controlled by the most
   * recently activated medium map.
   */
  public static class MediumMapPageNumber extends Triplet {
    int pageNumber;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      pageNumber = UtilBinaryDecoding.parseInt(sfData, offset + 2, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.intToByteArray(pageNumber, 4));
    }
  }

  /**
   * MODCA, page 407.<br><br> The Object Byte Extent triplet is used to specify the number of bytes
   * contained in an object.
   */
  public static class ObjectByteExtent extends Triplet {
    long byteExtentLow;
    long byteExtentHigh;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      byteExtentLow = UtilBinaryDecoding.parseLong(sfData, offset + 2, 4);
      byteExtentHigh = UtilBinaryDecoding.parseLong(sfData, offset + 6, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 10;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.longToByteArray(byteExtentLow, 4));
      os.write(UtilBinaryDecoding.longToByteArray(byteExtentHigh, 4));
    }
  }

  /**
   * MODCA, page 408.<br><br> The Object Structured Field Offset triplet is used to specify the
   * structuredoffset of an indexed object from the beginning of the document. field
   */
  public static class ObjectStructuredFieldOffset extends Triplet {
    long offsetLow;
    Long offsetHigh;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      offsetLow = UtilBinaryDecoding.parseLong(sfData, offset + 2, 4);
      if (this.length > 6) offsetHigh = UtilBinaryDecoding.parseLong(sfData, offset + 6, 4);
      else offsetHigh = null;
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (offsetHigh != null ? 10 : 6);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.longToByteArray(offsetLow, 4));
      if (offsetHigh != null) os.write(UtilBinaryDecoding.longToByteArray(offsetHigh, 4));
    }
  }

  /**
   * MODCA, page 409.<br><br>
   *
   * The Object Structured Field Extent triplet is used to specify the number of structured fields
   * contained in an object, starting with the Begin Object structured field and ending with the End
   * Object structured field.
   */
  public static class ObjectStructuredFieldExtent extends Triplet {
    long numberOfSFLow;
    Long numberOfSFHigh;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      numberOfSFLow = UtilBinaryDecoding.parseLong(sfData, offset + 2, 4);
      if (this.length > 6)
        numberOfSFHigh = UtilBinaryDecoding.parseLong(sfData, offset + 6, 4);
      else numberOfSFHigh = null;
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (numberOfSFHigh != null ? 10 : 6);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.longToByteArray(numberOfSFLow, 4));
      if (numberOfSFHigh != null)
        os.write(UtilBinaryDecoding.longToByteArray(numberOfSFHigh, 4));
    }
  }

  /**
   * MODCA, page 410.<br><br>
   *
   * The Object Offset triplet specifies the number of objects of a particular type that precede a
   * selected object in the document. If the object being counted is a document, this triplet
   * specifies the number of documents that precede the selected object in the print file.
   */
  public static class ObjectOffset extends Triplet {
    ObjectType objectType;
    byte reserved3 = 0x00;
    long nrOfPrecedingObjectsLow;
    Long nrOfPrecedingObjectsHigh;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);

      objectType = ObjectType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      reserved3 = sfData[offset + 3];
      nrOfPrecedingObjectsLow = UtilBinaryDecoding.parseLong(sfData, offset + 4, 4);
      if (this.length > 7)
        nrOfPrecedingObjectsHigh = UtilBinaryDecoding.parseLong(sfData, offset + 8, 4);
      else nrOfPrecedingObjectsHigh = null;
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (nrOfPrecedingObjectsHigh != null ? 12 : 8);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(objectType.toByte());
      os.write(reserved3);
      os.write(UtilBinaryDecoding.longToByteArray(nrOfPrecedingObjectsLow, 4));
      if (nrOfPrecedingObjectsHigh != null)
        os.write(UtilBinaryDecoding.longToByteArray(nrOfPrecedingObjectsHigh, 4));
    }

    public enum ObjectType {
      Document,
      Page_PaginatedObject;

      public static ObjectType valueOf(short codeByte) throws AFPParserException {
        if (codeByte == 0xA8) return Document;
        else if (codeByte == 0xAF) return Page_PaginatedObject;
        else
          throw new AFPParserException(ObjectType.class.getSimpleName() + ": object type 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == Document) return 0xA8;
        else if (this == Page_PaginatedObject) return 0xAF;
        return 0;
      }
    }
  }

  /**
   * MODCA, page 413.<br><br>
   *
   * The Font Horizontal Scale Factor triplet is used to carry information to support anamorphic
   * scaling of an outline technology font.
   */
  public static class FontHorizontalScaleFactor extends Triplet {
    short horizontalScaleFactor;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      horizontalScaleFactor = UtilBinaryDecoding.parseShort(sfData, offset + 2, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 4;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(horizontalScaleFactor, 2));
    }
  }

  /**
   * MODCA, page 414.<br><br> The Object Count triplet specifies the number of subordinate objects
   * of a particular type contained in an object.
   */
  public static class ObjectCount extends Triplet {
    short subordinateObjectType = 0xFA;
    byte reserved3 = 0x00;
    long numberOfObjectsLow;
    Long numberOfObjectsHigh;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      subordinateObjectType = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3 = sfData[offset + 3];
      numberOfObjectsLow = UtilBinaryDecoding.parseLong(sfData, offset + 4, 4);
      if (this.length > 8) {
        numberOfObjectsHigh = UtilBinaryDecoding.parseLong(sfData, offset + 8, 4);
      } else {
        numberOfObjectsHigh = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (numberOfObjectsHigh != null ? 12 : 8);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(subordinateObjectType);
      os.write(reserved3);
      os.write(UtilBinaryDecoding.longToByteArray(numberOfObjectsLow, 4));
      if (numberOfObjectsHigh != null) {
        os.write(UtilBinaryDecoding.longToByteArray(numberOfObjectsHigh, 4));
      }
    }
  }

  /**
   * MODCA, page 416.<br><br> The Local Date and Time Stamp triplet specifies a date and time stamp
   * to be associated with an object.
   */
  public static class LocalObjectDateAndTimeStamp extends Triplet {
    DateAndTimeStampType dateAndTimeStampType;
    short hundreds;
    int tens;
    int dayOfYear;
    int hourOfDay;
    int minuteOfHour;
    int secondOfMinute;
    int hundredthOfSecond;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      dateAndTimeStampType = DateAndTimeStampType.valueOf(sfData[offset + 2]);
      hundreds = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      tens = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2);
      dayOfYear = UtilBinaryDecoding.parseInt(sfData, offset + 6, 3);
      hourOfDay = UtilBinaryDecoding.parseInt(sfData, offset + 9, 2);
      minuteOfHour = UtilBinaryDecoding.parseInt(sfData, offset + 11, 2);
      secondOfMinute = UtilBinaryDecoding.parseInt(sfData, offset + 13, 2);
      hundredthOfSecond = UtilBinaryDecoding.parseShort(sfData, offset + 15, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 17;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(dateAndTimeStampType.toByte());
      os.write(hundreds);
      os.write(UtilBinaryDecoding.intToByteArray(tens, 2));
      os.write(UtilBinaryDecoding.intToByteArray(dayOfYear, 3));
      os.write(UtilBinaryDecoding.intToByteArray(hourOfDay, 2));
      os.write(UtilBinaryDecoding.intToByteArray(minuteOfHour, 2));
      os.write(UtilBinaryDecoding.intToByteArray(secondOfMinute, 2));
      os.write(UtilBinaryDecoding.intToByteArray(hundredthOfSecond, 2));

    }

    public enum DateAndTimeStampType {
      Creation,
      RMARK_Retired,
      Revision;

      public static DateAndTimeStampType valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x00) return Creation;
        else if (codeByte == 0x01) return RMARK_Retired;
        else if (codeByte == 0x03) return Revision;
        else
          throw new AFPParserException(DateAndTimeStampType.class.getSimpleName() + ": type 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == Creation) return 0x00;
        else if (this == RMARK_Retired) return 0x01;
        else if (this == Revision) return 0x03;
        else return 0;
      }
    }

  }

  /**
   * MODCA, page 418.<br><br>
   *
   * The Comment triplet is used to include comments for documentation purposes within a structured
   * field.
   */
  public static class Comment extends Triplet {
    String comment;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      comment = UtilCharacterEncoding.decodeEBCDIC(sfData, offset + 2, this.length - 2, config);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      byte[] data = comment.getBytes(config.getAfpCharSet());
      length = (short) (data.length + 2);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(data);
    }
  }

  /**
   * MODCA, page 419. The Medium Orientation triplet may be used to specify the orientation of the
   * medium presentation space on the physical medium.
   */
  public static class MediumOrientation extends Triplet {
    MediumOrientationValue mediumOrientation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      mediumOrientation = MediumOrientationValue.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(mediumOrientation.toByte());
    }

    public enum MediumOrientationValue {
      Portrait,
      Landscape,
      ReversePortrait,
      ReverseLandscape,
      Portrait90,
      Landscape90;

      public static MediumOrientationValue valueOf(byte codeByte) throws AFPParserException {
        for (MediumOrientationValue v : values()) if (v.ordinal() == codeByte) return v;
        throw new AFPParserException(MediumOrientationValue.class.getSimpleName() + ": medium orientation value 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /**
   * MODCA, page 421.<br><br> The Resource Object Include triplet identifies an object to be
   * included on a presentation space at a specified position.
   */
  public static class ResourceObjectInclude extends Triplet {
    short objectType = 0xDF;
    String objectName;
    int xOrigin;
    int yOrigin;
    AFPOrientation orientation;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      objectType = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      objectName = UtilCharacterEncoding.decodeEBCDIC(sfData, offset + 3, 8, config);
      xOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 11, 3);
      yOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 14, 3);
      if (this.length > 17) {
        orientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 17, 2));
      } else {
        orientation = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (orientation == null ? 17 : 19);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(objectType);
      os.write(UtilCharacterEncoding.stringToByteArray(objectName, config.getAfpCharSet(), 8, Constants.EBCDIC_BLANK));
      os.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
      os.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
      if (this.orientation != null) {
        os.write(orientation.toBytes());
      }
    }
  }

  /**
   * MODCA, page 423.<br><br> This triplet is used to specify the resulting appearance when data in
   * a new presentation space is merged with data in an existing presentation space.
   */
  public static class PresentationSpaceResetMixing extends Triplet {
    BackgroundMixingFlag backgroundMixingFlag;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      backgroundMixingFlag = BackgroundMixingFlag.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(backgroundMixingFlag.toByte());
    }

    public enum BackgroundMixingFlag {
      DoNotResetColor,
      ResetColor;

      public static BackgroundMixingFlag valueOf(byte codeByte) {
        if (codeByte == 0x00) return DoNotResetColor;
        else return ResetColor;
      }

      public int toByte() {
        if (this == DoNotResetColor) return 0x00;
        else return 0x80;
      }
    }
  }

  /**
   * MODCA, page 425.<br><br>
   *
   * This triplet is used to specify the rules for establishing the color attribute of areas formed
   * by the intersection of two presentation spaces. It is specified on structured fields associated
   * with a presentation space that is to be merged onto an existing presentation space.
   */
  public static class PresentationSpaceMixingRule extends Triplet {
    List<MixingKeywordAndRule> mixingRules;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      int pos = 2;
      mixingRules = new ArrayList<MixingKeywordAndRule>((this.length - 2) / 2);
      while (pos < this.length) {
        MixingKeywordAndRule mr = new MixingKeywordAndRule();
        mr.keyword = MixingKeyword.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + pos, 1));
        mr.rule = MixingRule.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + pos + 1, 1));
        mixingRules.add(mr);
        pos += 2;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (2 + 2 * mixingRules.size());
      os.write(length);
      os.write(tripletID.toByte());
      for (MixingKeywordAndRule mr : mixingRules) os.write(mr.toBytes());
    }

    public List<MixingKeywordAndRule> getMixingRules() {
      return mixingRules;
    }

    public void setMixingRules(List<MixingKeywordAndRule> mixingRules) {
      this.mixingRules = mixingRules;
    }

    public void addMixingRule(MixingKeywordAndRule mixingRule) {
      if (mixingRule == null) return;
      if (this.mixingRules == null) this.mixingRules = new ArrayList<MixingKeywordAndRule>();
      mixingRules.add(mixingRule);
    }

    public void removeMixingRule(MixingKeywordAndRule mixingRule) {
      if (this.mixingRules == null) return;
      mixingRules.remove(mixingRule);
    }

    public enum MixingKeyword {
      BackgroudOnBackground,
      BackgroundOnForeground,
      ForegroundOnBackground,
      ForegroundOnForeground;

      public static MixingKeyword valueOf(short codeByte) throws AFPParserException {
        for (MixingKeyword mk : values()) if (mk.ordinal() + 0x70 == codeByte) return mk;
        throw new AFPParserException(MixingKeyword.class.getSimpleName() + ": mixing keyword 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 0x70;
      }
    }

    public enum MixingRule {
      Overpaint,
      Underpaint,
      Blend,
      MODCADefaultMixing;

      public static MixingRule valueOf(short xodeByte) throws AFPParserException {
        for (MixingRule mr : values()) if (mr.ordinal() + 1 == xodeByte) return mr;
        throw new AFPParserException(MixingRule.class.getSimpleName() + ": mixing rule code 0x" + Integer.toHexString(xodeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }

    public static class MixingKeywordAndRule {
      MixingKeyword keyword;
      MixingRule rule;

      public byte[] toBytes() {
        byte[] result = new byte[]{
                (byte) keyword.toByte(),
                (byte) rule.toByte()
        };
        return result;
      }

      public MixingKeyword getKeyword() {
        return keyword;
      }

      public void setKeyword(MixingKeyword keyword) {
        this.keyword = keyword;
      }

      public MixingRule getRule() {
        return rule;
      }

      public void setRule(MixingRule rule) {
        this.rule = rule;
      }
    }

  }

  /**
   * MODCA, page 427.<br><br> The Universal Date and Time Stamp triplet specifies a date and time in
   * accordance with the format defined in ISO 8601: 1988 (E).
   */
  public static class UniversalDateAndTimeStamp extends Triplet {
    byte reserved2 = 0x00;
    int year;
    byte monthOfYear;
    byte dayOfMonth;
    byte hourOfDay;
    byte minuteOfHour;
    byte secondOfMinute;
    TimeZone timeZone;
    byte diffHours;
    byte diffMinutes;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      year = UtilBinaryDecoding.parseInt(sfData, offset + 3, 2);
      monthOfYear = sfData[offset + 5];
      dayOfMonth = sfData[offset + 6];
      hourOfDay = sfData[offset + 7];
      minuteOfHour = sfData[offset + 8];
      secondOfMinute = sfData[offset + 9];
      timeZone = TimeZone.valueOf(sfData[offset + 10]);
      diffHours = sfData[offset + 11];
      diffMinutes = sfData[offset + 12];
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 13;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(UtilBinaryDecoding.intToByteArray(year, 2));
      os.write(monthOfYear);
      os.write(dayOfMonth);
      os.write(hourOfDay);
      os.write(minuteOfHour);
      os.write(secondOfMinute);
      os.write(timeZone.toByte());
      os.write(diffHours);
      os.write(diffMinutes);
    }

    public enum TimeZone {
      CoordinatedUTC,
      AheadUTC,
      BehindUTC;

      public static TimeZone valueOf(byte codeByte) throws AFPParserException {
        for (TimeZone tz : values()) if (tz.ordinal() == codeByte) return tz;
        throw new AFPParserException(TimeZone.class.getSimpleName() + ": time zone code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /**
   * MODCA, page 430.<br><br>
   *
   * The Toner Saver triplet activates a toner saver mode for printing. The toner saver control
   * specified by this triplet overrides any other toner saver controls that may be active in the
   * printer.
   */
  public static class TonerSaver extends Triplet {
    byte reserved2 = 0x00;
    TonerSaverFunction tonerSaverFunction;
    byte[] reserved4_5 = new byte[2];

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      tonerSaverFunction = TonerSaverFunction.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 3, 1));
      reserved4_5 = new byte[]{
              sfData[offset + 4],
              sfData[offset + 5],
      };
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 6;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(tonerSaverFunction.toByte());
      os.write(reserved4_5);
    }

    public enum TonerSaverFunction {
      DeactivateTonerSaver,
      ActivateTonerSaver,
      DefaultTonerSaverSetting;

      public static TonerSaverFunction valueOf(short codeByte) throws AFPParserException {
        if (codeByte == 0x00) return DeactivateTonerSaver;
        else if (codeByte == 0x01) return ActivateTonerSaver;
        else if (codeByte == 0xFF) return DefaultTonerSaverSetting;
        else
          throw new AFPParserException(TonerSaverFunction.class.getSimpleName() + ": tonser saver function code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == DefaultTonerSaverSetting) return 0xFF;
        else return ordinal();
      }
    }
  }

  /**
   * MODCA, page 432.<br><br>
   */
  public static class ColorFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte reserved3 = 0x00;
    ExceptionReportingRule exceptionReportingRule;
    byte reserved5 = 0x00;
    ExceptionSubstitutionRule exceptionSubstitutionRule;
    byte reserved7 = 0x00;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3 = sfData[offset + 3];
      exceptionReportingRule = ExceptionReportingRule.valueOf(sfData[offset + 4]);
      reserved5 = sfData[offset + 5];
      exceptionSubstitutionRule = ExceptionSubstitutionRule.valueOf(sfData[offset + 6]);
      reserved7 = sfData[offset + 7];
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 8;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3);
      os.write(exceptionReportingRule.toByte());
      os.write(reserved5);
      os.write(exceptionSubstitutionRule.toByte());
      os.write(reserved7);
    }

    public enum ExceptionContinuationRule {
      Stop,
      DoNotStop;

      public static ExceptionContinuationRule valueOf(byte ruleByte) throws AFPParserException {
        for (ExceptionContinuationRule ecr : values())
          if (ecr.ordinal() + 1 == ruleByte) return ecr;
        throw new AFPParserException(ExceptionContinuationRule.class.getSimpleName() + ": continuation rule 0x" + Integer.toHexString(ruleByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }

    public enum ExceptionReportingRule {
      Report,
      DoNotReport;

      public static ExceptionReportingRule valueOf(byte ruleByte) throws AFPParserException {
        for (ExceptionReportingRule ecr : values())
          if (ecr.ordinal() + 1 == ruleByte) return ecr;
        throw new AFPParserException(ExceptionReportingRule.class.getSimpleName() + ": reporting rule 0x" + Integer.toHexString(ruleByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }

    public enum ExceptionSubstitutionRule {
      AnySubstitution_Default;

      public static ExceptionSubstitutionRule valueOf(byte ruleByte) throws AFPParserException {
        for (ExceptionSubstitutionRule ecr : values())
          if (ecr.ordinal() + 1 == ruleByte) return ecr;
        throw new AFPParserException(ExceptionSubstitutionRule.class.getSimpleName() + ": substitution rule 0x" + Integer.toHexString(ruleByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }
  }

  /**
   * MODCA, page 435. <br><br> The Font Fidelity triplet is used to specify the exception
   * continuation rules for font resolution exceptions.
   */
  public static class FontFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte[] reserved3_6 = new byte[4];

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3_6 = new byte[]{
              sfData[offset + 3], sfData[offset + 4],
              sfData[offset + 5], sfData[offset + 6],
      };
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3_6);
    }
  }

  /**
   * MODCA, page 436.<br><br>
   *
   * The Attribute Qualifier triplet is used to specify a qualifier for a document attribute.
   */
  public static class AttributeQualifier extends Triplet {
    int sequenceNumber;
    int levelNumber;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      sequenceNumber = UtilBinaryDecoding.parseInt(sfData, offset + 2, 4);
      levelNumber = UtilBinaryDecoding.parseInt(sfData, offset + 6, 4);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 10;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(UtilBinaryDecoding.intToByteArray(sequenceNumber, 4));
      os.write(UtilBinaryDecoding.intToByteArray(levelNumber, 4));
    }
  }

  /**
   * MODCA, page 437.<br><br> The Page Position Information triplet is used to tag a page with the
   * Page Position (PGP) structured field repeating group information that is used to present the
   * page. The PGP is specified in the medium map referenced by the FQN type X'8D'—Begin Medium Map
   * Reference triplet. This information is used for viewing the page with a particular form map,
   * which is normally the form map that the document containing this page was archived
   * with.<br><br> This triplet is not used for printing and is ignored by print servers.
   */
  public static class PagePositionInformation extends Triplet {
    byte repeatingGroupNumber;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      repeatingGroupNumber = sfData[offset + 2];
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(repeatingGroupNumber);
    }
  }

  /**
   * MODCA, page 438.<br><br>
   *
   * The Parameter Value triplet is used to pass parameter values to an executable program such as
   * an object handler or a system command interpreter.
   */
  public static class ParameterValue extends Triplet {
    byte reserved2 = 0x00;
    ParameterSyntax parameterSyntax;
    byte[] parameterValue;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      parameterSyntax = ParameterSyntax.valueOf(sfData[offset + 3]);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength < 4) {
        parameterValue = new byte[actualLength - 4];
        System.arraycopy(sfData, offset + 4, parameterValue, 0, parameterValue.length);
      } else {
        parameterValue = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (parameterValue == null ? 4 : 4 + parameterValue.length);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(parameterSyntax.toByte());
      if (parameterValue != null) os.write(parameterValue);
    }

    public enum ParameterSyntax {
      Undefined,
      UnsignedNumber,
      SignedNumber,
      BitString,
      DefinedConstant,
      CharacterString,
      Name;

      public static ParameterSyntax valueOf(byte codeByte) throws AFPParserException {
        for (ParameterSyntax ps : values()) if (ps.ordinal() == codeByte) return ps;
        throw new AFPParserException(ParameterSyntax.class.getSimpleName() + ": systax code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /**
   * MODCA, page 439.<br><br> The Presentation Control triplet specifies flags that control the
   * presentation of an object.
   */
  public static class PresentationControl extends Triplet {
    EnumSet<PresentationControlFlags> presentationControlFlags;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      presentationControlFlags = PresentationControlFlags.valueOf(sfData[offset + 2]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 3;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(PresentationControlFlags.toByte(presentationControlFlags));
    }

    public EnumSet<PresentationControlFlags> getPresentationControlFlags() {
      return presentationControlFlags;
    }

    public void setPresentationControlFlags(
            EnumSet<PresentationControlFlags> presentationControlFlags) {
      this.presentationControlFlags = presentationControlFlags;
    }

    /**
     * Sets the given flag and unsets all mutual exclusive flags.
     */
    public void setPresentationControlFlag(PresentationControlFlags presentationControlFlag) {
      if (presentationControlFlag == null) return;
      if (presentationControlFlags == null)
        presentationControlFlags = EnumSet.noneOf(PresentationControlFlags.class);
      PresentationControlFlags.handler.setFlag(presentationControlFlags, presentationControlFlag);
    }

    public enum PresentationControlFlags implements IMutualExclusiveGroupedFlag {
      ViewControl_View(0),
      ViewControl_DoNotView(0),
      IndexingControl_Indexing(1),
      IndexingControl_NoIndexing(1);

      public static final MutualExclusiveGroupedFlagHandler<PresentationControlFlags> handler = new MutualExclusiveGroupedFlagHandler<PresentationControlFlags>();
      int group;

      PresentationControlFlags(int group) {
        this.group = group;
      }

      public static EnumSet<PresentationControlFlags> valueOf(byte codeByte) {
        EnumSet<PresentationControlFlags> result = EnumSet.noneOf(PresentationControlFlags.class);

        if ((codeByte & 0x80) == 0) result.add(ViewControl_View);
        else result.add(ViewControl_DoNotView);
        if ((codeByte & 0x40) == 0) result.add(IndexingControl_Indexing);
        else result.add(IndexingControl_NoIndexing);

        return result;
      }

      public static int toByte(EnumSet<PresentationControlFlags> flags) {
        if (flags == null) return 0x00;
        int result = 0;
        if (flags.contains(ViewControl_DoNotView)) result |= 80;
        if (flags.contains(IndexingControl_NoIndexing)) result |= 40;
        return result;
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }

  /**
   * MODCA, page 440.<br><br>
   *
   * The Font Resolution and Metric Technology specifies certain metric characteristics of a FOCA
   * raster-technology font character set which may have affected the formatting of the document
   * with this font. This information, as carried by the X'84' triplet, may be used by presentation
   * servers and presentation devices to select the best-matching coded font for presentation.
   */
  public static class FontResolutionAndMetricTechnology extends Triplet {
    MetricTechnology metricTechnology;
    AFPUnitBase unitBase;
    short unitsPerUnitBase;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      metricTechnology = MetricTechnology.valueOf(sfData[offset + 2]);
      unitBase = AFPUnitBase.valueOf(sfData[offset + 3]);
      unitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 6;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(metricTechnology.toByte());
      os.write(unitBase.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(unitsPerUnitBase, 2));
    }

    public enum MetricTechnology {
      Fixed,
      Relative;

      public static MetricTechnology valueOf(byte codeByte) throws AFPParserException {
        if (codeByte == 0x01) return Fixed;
        else if (codeByte == 0x02) return Relative;
        else
          throw new AFPParserException(MetricTechnology.class.getSimpleName() + ": technology code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }
  }

  /**
   * MODCA, page 442.<br><br>
   *
   * The Finishing Operation triplet is used to specify finishing operations that are to be applied
   * to media.
   */
  public static class FinishingOperation extends Triplet {
    OperationType operationType;
    byte[] reserved3_4 = new byte[2];
    ReferenceCorner referenceCorner;
    byte operationCount;
    int offsetOfOperation;
    List<Short> positions;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      operationType = OperationType.valueOf(sfData[offset + 2]);
      reserved3_4 = new byte[2];
      System.arraycopy(sfData, offset + 2, reserved3_4, 0, reserved3_4.length);
      referenceCorner = ReferenceCorner.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 5, 1));
      operationCount = sfData[offset + 6];
      offset = UtilBinaryDecoding.parseInt(sfData, offset + 7, 2);
      if (this.length > 9) {
        positions = new ArrayList<Short>();
        int pos = 9;
        while (pos < this.length) {
          positions.add(UtilBinaryDecoding.parseShort(sfData, offset + pos, 2));
          pos += 2;
        }
      } else {
        positions = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (positions == null ? 9 : 9 + positions.size());
      os.write(length);
      os.write(tripletID.toByte());
      os.write(operationType.toByte());
      os.write(reserved3_4);
      os.write(referenceCorner.toByte());
      os.write(operationCount);
      os.write(UtilBinaryDecoding.intToByteArray(offsetOfOperation, 2));
      if (positions != null) {
        for (Short s : positions) os.write(UtilBinaryDecoding.shortToByteArray(s, 2));
      }
    }

    public enum OperationType {
      CornerStaple(0x01),
      SaddleStitchOut(0x02),
      EdgeStitch(0x03),
      FoldIn(0x04),
      SeparationCut(0x05),
      PerforationCut(0x06),
      ZFold(0x07),
      CenterFoldIn(0x08),
      TrimAfterCenterFoldOrSaddleStitch(0x09),
      Punch(0x0A),
      PerfectBind(0x0C),
      RingBind(0x0D),
      SaddleStitchIn(0x12);
      int code;

      OperationType(int code) {
        this.code = code;
      }

      public static OperationType valueOf(byte codeValue) throws AFPParserException {
        for (OperationType ot : values()) if (ot.code == codeValue) return ot;
        throw new AFPParserException(OperationType.class.getSimpleName() + ": operation type 0x" + Integer.toHexString(codeValue) + " is undefined.");
      }

      public int toByte() {
        return code;
      }
    }

    public enum ReferenceCorner {
      BottomRightCorner_BottomEdge(0x00),
      TopRightCorner_RightEdge(0x01),
      TopLeftCorner_TopEdge(0x02),
      BottomLeftCorner_LeftEdge(0x03),
      DefaultCorner_DefaultEdge(0xFF);
      int code;

      ReferenceCorner(int code) {
        this.code = code;
      }

      public static ReferenceCorner valueOf(short codeByte) throws AFPParserException {
        for (ReferenceCorner rc : values()) if (rc.code == codeByte) return rc;
        throw new AFPParserException(ReferenceCorner.class.getSimpleName() + ": corner/edge code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return code;
      }
    }
  }

  /**
   * MODCA, page 450.<br><br>
   *
   * The Text Fidelity triplet is used to specify the exception continuation and reporting rules for
   * text exceptions. A text exception is detected when an unrecognized or unsupported text control
   * sequence is encountered in a PTOCA text object.
   */
  public static class TextFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte reserved3 = 0x00;
    ExceptionReportingRule exceptionReportingRule;
    byte[] reserved5_6 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3 = sfData[offset + 3];
      exceptionReportingRule = ExceptionReportingRule.valueOf(sfData[offset + 4]);
      reserved5_6 = new byte[2];
      System.arraycopy(sfData, offset + 5, reserved5_6, 0, reserved5_6.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3);
      os.write(exceptionReportingRule.toByte());
      os.write(reserved5_6);
    }
  }

  /**
   * MODCA, page 452.<br><br>
   *
   * The Media Fidelity triplet is used to specify the continuation rule if a request for a specific
   * media or a specific media bin cannot be satisfied.
   */
  public static class MediaFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte reserved3 = 0x00;
    ExceptionReportingRule exceptionReportingRule;
    byte[] reserved5_6 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3 = sfData[offset + 3];
      exceptionReportingRule = ExceptionReportingRule.valueOf(sfData[offset + 4]);
      reserved5_6 = new byte[2];
      System.arraycopy(sfData, offset + 5, reserved5_6, 0, reserved5_6.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3);
      os.write(exceptionReportingRule.toByte());
      os.write(reserved5_6);
    }
  }

  /**
   * MODCA, page 454.<br><br>
   *
   * The Finishing Fidelity triplet is used to specify the exception continuation and reporting
   * rules for finishing exceptions. A finishing exception is detected when the specified finishing
   * operation cannot be satisfied.
   */
  public static class FinishingFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte reserved3 = 0x00;
    ExceptionReportingRule exceptionReportingRule;
    byte[] reserved5_6 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3 = sfData[offset + 3];
      exceptionReportingRule = ExceptionReportingRule.valueOf(sfData[offset + 4]);
      reserved5_6 = new byte[2];
      System.arraycopy(sfData, offset + 5, reserved5_6, 0, reserved5_6.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3);
      os.write(exceptionReportingRule.toByte());
      os.write(reserved5_6);
    }
  }

  /**
   * MODCA, page 456.<br><br> The Data-Object Font Descriptor triplet is used to specify the
   * parameters needed to render a data-object font. Data-object fonts are non-FOCA font resources,
   * such as TrueType and OpenType fonts. An MDR structured field is used to map a data-object font
   * as a resource.
   */
  public static class DataObjectFontDescriptor extends Triplet {
    EnumSet<FontInformationFlag> fontInformationFlags;
    short fontTechnology;
    short specifiedVerticalFontSize;
    short horizontalScaleFactor;
    AFPOrientation characterOrientation;
    short encodingEnvironment;
    short encodingIdentifier;
    byte[] reserved14_15 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      fontInformationFlags = FontInformationFlag.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 2, 1));
      fontTechnology = UtilBinaryDecoding.parseShort(sfData, offset + 3, 1);
      specifiedVerticalFontSize = UtilBinaryDecoding.parseShort(sfData, offset + 4, 2);
      horizontalScaleFactor = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
      characterOrientation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 8, 2));
      encodingEnvironment = UtilBinaryDecoding.parseShort(sfData, offset + 10, 2);
      encodingIdentifier = UtilBinaryDecoding.parseShort(sfData, offset + 12, 2);
      reserved14_15 = new byte[2];
      System.arraycopy(sfData, offset + 14, reserved14_15, 0, reserved14_15.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 16;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(FontInformationFlag.toByte(fontInformationFlags));
      os.write(fontTechnology);
      os.write(UtilBinaryDecoding.shortToByteArray(specifiedVerticalFontSize, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(horizontalScaleFactor, 2));
      os.write(characterOrientation.toBytes());
      os.write(UtilBinaryDecoding.shortToByteArray(encodingEnvironment, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(encodingIdentifier, 2));
      os.write(reserved14_15);
    }

    public enum FontInformationFlag implements IMutualExclusiveGroupedFlag {
      MICR_NonMICR(0),
      MICR_MICR(0),
      Location_Anyware(1),
      Location_ResourceGroup(1);
      public static final MutualExclusiveGroupedFlagHandler<FontInformationFlag> handler = new MutualExclusiveGroupedFlagHandler<FontInformationFlag>();
      int group;

      FontInformationFlag(int group) {
        this.group = group;
      }

      public static EnumSet<FontInformationFlag> valueOf(short codeByte) {
        EnumSet<FontInformationFlag> result = EnumSet.noneOf(FontInformationFlag.class);
        if ((codeByte & 0x80) == 0) result.add(MICR_NonMICR);
        else result.add(MICR_MICR);
        if ((codeByte & 0x40) == 0) result.add(Location_Anyware);
        else result.add(Location_ResourceGroup);
        return result;
      }

      public static int toByte(EnumSet<FontInformationFlag> flags) {
        int result = 0;
        if (flags.contains(MICR_MICR)) result |= 0x80;
        if (flags.contains(Location_ResourceGroup)) result |= 0x40;
        return result;
      }

      public int getGroup() {
        return group;
      }
    }

  }

  /**
   * MODCA, page 461.<br><br>
   *
   * The Locale Selector triplet is used to identify the end-user community for presentation text
   * data. The locale information consists of an ISO-639 based language code, an ISO-15924 based
   * script code, an ISO-3166 based region code, and an application-specific variant code. The
   * encoding for all four parameters is UTF-16BE. Additional information on these parameters can be
   * found at the following urls: <ul> <li>The definition of language codes can be found at
   * http://lcweb.loc.gov/standards/ iso639-2/iso639jac.html <li> The definition of script codes can
   * be found at http://www.unicode.org/reports/tr24 <li> The definition of region codes can be
   * found at http://www.iso.org/iso/en/prods- services/iso3166ma/index.html </ul>
   */
  public static class LocaleSelector extends Triplet {
    byte reserved2 = 0x00;
    EnumSet<LocalSelectorFlag> flags;
    String languageCode;
    String scriptCode;
    String regionCode;
    byte[] reserved28_35 = new byte[8];
    String variantCode;


    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      flags = LocalSelectorFlag.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 3, 1));
      languageCode = new String(sfData, offset + 4, 8);
      scriptCode = new String(sfData, offset + 12, 8);
      regionCode = new String(sfData, offset + 20, 8);
      reserved28_35 = new byte[8];
      System.arraycopy(sfData, offset + 21, reserved28_35, 0, reserved28_35.length);
      if (this.length > 36) {
        variantCode = new String(sfData, offset + 36, this.length - 36);
      } else {
        variantCode = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      byte[] variantCodeData = variantCode != null ? variantCode.getBytes(Charset.defaultCharset()) : null;
      length = (short) (variantCodeData == null ? 36 : 36 + variantCodeData.length);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(LocalSelectorFlag.toByte(flags));
      os.write(UtilCharacterEncoding.stringToByteArray(languageCode, Charset.defaultCharset(), 8, (byte) 0x00));
      os.write(UtilCharacterEncoding.stringToByteArray(scriptCode, Charset.defaultCharset(), 8, (byte) 0x00));
      os.write(UtilCharacterEncoding.stringToByteArray(regionCode, Charset.defaultCharset(), 8, (byte) 0x00));
      os.write(reserved28_35);
      if (variantCodeData != null) os.write(variantCodeData);
    }

    public byte getReserved2() {
      return reserved2;
    }

    public void setReserved2(byte reserved2) {
      this.reserved2 = reserved2;
    }

    public EnumSet<LocalSelectorFlag> getFlags() {
      return flags;
    }

    public void setFlags(EnumSet<LocalSelectorFlag> flags) {
      this.flags = flags;
    }

    /**
     * Sets the given flag and un-sets the corresponding mutual exclusive flags.
     *
     * @param flag flag to set.
     */
    public void setFlag(LocalSelectorFlag flag) {
      if (flag == null) return;
      if (flags == null) flags = EnumSet.noneOf(LocalSelectorFlag.class);
      LocalSelectorFlag.handler.setFlag(flags, flag);
    }

    public String getLanguageCode() {
      return languageCode;
    }

    public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode;
    }

    public String getScriptCode() {
      return scriptCode;
    }

    public void setScriptCode(String scriptCode) {
      this.scriptCode = scriptCode;
    }

    public String getRegionCode() {
      return regionCode;
    }

    public void setRegionCode(String regionCode) {
      this.regionCode = regionCode;
    }

    public byte[] getReserved28_35() {
      return reserved28_35;
    }

    public void setReserved28_35(byte[] reserved28_35) {
      this.reserved28_35 = reserved28_35;
    }

    public String getVariantCode() {
      return variantCode;
    }

    public void setVariantCode(String variantCode) {
      this.variantCode = variantCode;
    }

    public enum LocalSelectorFlag implements IMutualExclusiveGroupedFlag {
      LanguageCode_NotSpecified(0),
      LanguageCode_TwoBytes(0),
      LanguageCode_ThreeBytes(0),

      ScriptCode_NotSpecified(1),
      ScriptCode_FourCharacter(1),

      RegionCode_NotSpecified(2),
      RegionCode_TwoBytes(2),
      RegionCode_ThreeBytes(3);

      public static final MutualExclusiveGroupedFlagHandler<LocalSelectorFlag> handler = new MutualExclusiveGroupedFlagHandler<LocalSelectorFlag>();
      int group;

      LocalSelectorFlag(int group) {
        this.group = group;
      }

      public static EnumSet<LocalSelectorFlag> valueOf(short codeByte) {
        EnumSet<LocalSelectorFlag> result = EnumSet.noneOf(LocalSelectorFlag.class);

        short languageCode = (short) (codeByte >>> 4);
        short regionCode = (short) (codeByte & 0x07);

        if (languageCode == 0x00) result.add(LanguageCode_NotSpecified);
        else if (languageCode == 0x02) result.add(LanguageCode_TwoBytes);
        else if (languageCode == 0x03) result.add(LanguageCode_ThreeBytes);
        if ((codeByte & 0x08) == 0) result.add(ScriptCode_NotSpecified);
        else result.add(ScriptCode_FourCharacter);
        if (regionCode == 0x00) result.add(RegionCode_NotSpecified);
        else if (regionCode == 0x02) result.add(RegionCode_TwoBytes);
        else if (regionCode == 0x03) result.add(RegionCode_ThreeBytes);

        return result;
      }

      public static int toByte(EnumSet<LocalSelectorFlag> flags) {
        int result = 0;

        if (flags.contains(LanguageCode_TwoBytes)) result |= 0x02;
        else if (flags.contains(LanguageCode_ThreeBytes)) result |= 0x03;
        result <<= 1;
        if (flags.contains(ScriptCode_FourCharacter)) result |= 0x01;
        result <<= 3;
        if (flags.contains(RegionCode_TwoBytes)) result |= 0x02;
        else if (flags.contains(RegionCode_ThreeBytes)) result |= 0x03;

        return result;
      }

      public int getGroup() {
        return group;
      }

    }

  }

  /**
   * MODCA, page 461.<br><br> The UP3i Finishing Operation triplet is used to specify finishing
   * operations that are to be applied to media. More specifically, this triplet is a carrier for
   * finishing operations and parameters that are defined by the UP3i consortium in the UP3i
   * Specification.
   */
  public static class UP3iFinishingOperation extends Triplet {
    short sequenceNumber;
    byte reserved3 = 0x00;
    byte[] up3iData;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      sequenceNumber = UtilBinaryDecoding.parseShort(sfData, offset + 2, 1);
      reserved3 = sfData[offset + 3];
      up3iData = new byte[this.length - 4];
      System.arraycopy(sfData, offset + 4, up3iData, 0, up3iData.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = (short) (4 + up3iData.length);
      os.write(length);
      os.write(tripletID.toByte());
      os.write(sequenceNumber);
      os.write(reserved3);
      os.write(up3iData);
    }

    public short getSequenceNumber() {
      return sequenceNumber;
    }

    public void setSequenceNumber(short sequenceNumber) {
      this.sequenceNumber = sequenceNumber;
    }

    public byte getReserved3() {
      return reserved3;
    }

    public void setReserved3(byte reserved3) {
      this.reserved3 = reserved3;
    }

    public byte[] getUp3iData() {
      return up3iData;
    }

    public void setUp3iData(byte[] up3iData) {
      this.up3iData = up3iData;
    }
  }

  /**
   * MODCA, page 466.<br><br>
   *
   * The Color Management Resource Descriptor triplet specifies the processing mode and scope for a
   * Color Management Resource (CMR).
   */
  public static class ColorManagementResourceDescriptor extends Triplet {
    byte reserved2;
    CMRProcessingMode cmrProcessingMode;
    CMRScope cmrScope;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      cmrProcessingMode = CMRProcessingMode.valueOf(sfData[offset + 3]);
      cmrScope = CMRScope.valueOf(sfData[offset + 4]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 5;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(cmrProcessingMode.toByte());
      os.write(cmrScope.toByte());
    }

    public byte getReserved2() {
      return reserved2;
    }

    public void setReserved2(byte reserved2) {
      this.reserved2 = reserved2;
    }

    public CMRProcessingMode getCmrProcessingMode() {
      return cmrProcessingMode;
    }

    public void setCmrProcessingMode(CMRProcessingMode cmrProcessingMode) {
      this.cmrProcessingMode = cmrProcessingMode;
    }

    public CMRScope getCmrScope() {
      return cmrScope;
    }

    public void setCmrScope(CMRScope cmrScope) {
      this.cmrScope = cmrScope;
    }

    public enum CMRProcessingMode {
      AuditCMR,
      InstructionCMR,
      LinkCMR;

      public static CMRProcessingMode valueOf(byte codeByte) throws AFPParserException {
        for (CMRProcessingMode pm : values()) if (pm.ordinal() + 1 == codeByte) return pm;
        throw new AFPParserException(CMRProcessingMode.class.getSimpleName() + ": processing mode 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }

    public enum CMRScope {
      DataObject,
      PageOrOverlay,
      Document,
      PrintFile,
      PageGroup_SheetGroup;

      public static CMRScope valueOf(byte codeByte) throws AFPParserException {
        for (CMRScope sc : values()) if (sc.ordinal() + 1 == codeByte) return sc;
        throw new AFPParserException(CMRScope.class.getSimpleName() + ": scope code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }
  }

  /**
   * MODCA, page 468.<br><br>
   *
   * The Rendering Intent triplet specifies the rendering intent parameter, which is used to modify
   * the final appearance of color data. This parameter is based on the rendering intents defined by
   * the International Color Consortium (ICC). For more information on rendering intents, see the
   * International Color Consortium Specification ICC.x, File Format for Color Profiles.
   */
  public static class RenderingIntent extends Triplet {
    byte[] reserved2_3 = new byte[2];
    Intent intentForIOCA;
    Intent intentForContainerNonIOCA;
    Intent intentForPTOCA;
    Intent intentForGOCA;
    byte[] reserved8_9 = new byte[2];

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2_3 = new byte[]{sfData[offset + 2], sfData[offset + 3]};
      intentForIOCA = Intent.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 4, 1));
      intentForContainerNonIOCA = Intent.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 5, 1));
      intentForPTOCA = Intent.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 6, 1));
      intentForGOCA = Intent.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 7, 1));
      reserved8_9 = new byte[]{sfData[offset + 8], sfData[offset + 9]};
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 10;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2_3);
      os.write(intentForIOCA.toByte());
      os.write(intentForContainerNonIOCA.toByte());
      os.write(intentForPTOCA.toByte());
      os.write(intentForGOCA.toByte());
      os.write(reserved8_9);
    }

    public enum Intent {
      Perceptual,
      MediaRelativeColorimetric,
      Saturation,
      iccAbsoluteColorimetric,
      NotSpecified;

      public static Intent valueOf(short codeByte) throws AFPParserException {
        if (codeByte == 0xFF) return NotSpecified;
        for (Intent intent : values()) if (intent.ordinal() == codeByte) return intent;
        throw new AFPParserException(Intent.class.getSimpleName() + ": intent code 0x" + Integer.toHexString(codeByte) + " is undefined.");
      }

      public int toByte() {
        if (this == NotSpecified) return 0xFF;
        else return ordinal();
      }
    }
  }

  /**
   * MODCA, page 471.<br><br>
   *
   * The CMR Tag Fidelity triplet is used to specify the exception continuation and reporting rules
   * for Color Management Resource (CMR) tag exceptions. A CMR tag exception is detected when an
   * unsupported CMR tag is encountered in a Color Management Resource (CMR).
   */
  public static class CMRTagFidelity extends Triplet {
    ExceptionContinuationRule exceptionContinuationRule;
    byte reserved3 = 0x00;
    ExceptionReportingRule exceptionReportingRule;
    byte[] reserved5_6 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      exceptionContinuationRule = ExceptionContinuationRule.valueOf(sfData[offset + 2]);
      reserved3 = sfData[offset + 3];
      exceptionReportingRule = ExceptionReportingRule.valueOf(sfData[offset + 4]);
      reserved5_6 = new byte[2];
      System.arraycopy(sfData, offset + 5, reserved5_6, 0, reserved5_6.length);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(exceptionContinuationRule.toByte());
      os.write(reserved3);
      os.write(exceptionReportingRule.toByte());
      os.write(reserved5_6);
    }
  }

  /**
   * MODCA, page 473.<br><br>
   *
   * The Device Appearance triplet specifies one of a set of architected appearances to be assumed
   * by the presentation device.
   */
  public static class DeviceAppearance extends Triplet {
    byte reserved2 = 0x00;
    Appearance appearance;
    byte[] reserved5_6 = {0x00, 0x00};

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2 = sfData[offset + 2];
      appearance = Appearance.valueOf(sfData[offset + 3]);
      reserved5_6 = new byte[]{sfData[offset + 5], sfData[offset + 6]};
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 7;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2);
      os.write(appearance.toByte());
      os.write(reserved5_6);
    }


    public enum Appearance {
      DeviceDefault,
      DeviceDefaultMonochrome;

      public static Appearance valueOf(byte codeByte) {
        if (codeByte == 0x00) return DeviceDefault;
        else return DeviceDefaultMonochrome;
      }

      public int toByte() {
        return ordinal();
      }
    }
  }

  /**
   * MODCA, page 474.<br><br>
   *
   * The Image Resolution triplet specifies the resolution of a raster image.
   */
  public static class ImageResolution extends Triplet {
    byte[] reserved2_3 = {0x00, 0x00};
    AFPUnitBase xUnitBase;
    AFPUnitBase yUnitBase;
    short xUnitsPerUnitBase;
    short yUnitsPerUnitBase;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2_3 = new byte[]{sfData[offset + 2], sfData[offset + 3]};
      xUnitBase = AFPUnitBase.valueOf(sfData[offset + 4]);
      yUnitBase = AFPUnitBase.valueOf(sfData[offset + 5]);
      xUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 6, 2);
      yUnitsPerUnitBase = UtilBinaryDecoding.parseShort(sfData, offset + 8, 2);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 10;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2_3);
      os.write(xUnitBase.toByte());
      os.write(yUnitBase.toByte());
      os.write(UtilBinaryDecoding.shortToByteArray(xUnitsPerUnitBase, 2));
      os.write(UtilBinaryDecoding.shortToByteArray(yUnitsPerUnitBase, 2));
    }
  }

  /**
   * MODCA, page 476.<br><br> The Object Container Presentation Space Size triplet specifies the
   * presentation space size, or how such a size is determined, for certain container object types.
   */
  public static class ObjectContainerPresentationSpaceSize extends Triplet {
    byte[] reserved2_3 = {0x00, 0x00};
    PDFPresentationSpace pdfPresentationSpace;


    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      super.decodeAFP(sfData, offset, length, config);
      reserved2_3 = new byte[]{sfData[offset + 2], sfData[offset + 3]};
      pdfPresentationSpace = PDFPresentationSpace.valueOf(sfData[offset + 4]);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      length = 5;
      os.write(length);
      os.write(tripletID.toByte());
      os.write(reserved2_3);
      os.write(pdfPresentationSpace.toByte());
    }

    public enum PDFPresentationSpace {
      MediaBox,
      CropBox,
      BleedBox,
      TrimBox,
      ArtBox;

      public static PDFPresentationSpace valueOf(byte codeByte) throws AFPParserException {
        for (PDFPresentationSpace ps : values())
          if (ps.ordinal() + 1 == codeByte) return ps;
        throw new AFPParserException(PDFPresentationSpace.class.getSimpleName() + ": presentation space code 0x" + Integer.toHexString(codeByte) + " is undfined.");
      }

      public int toByte() {
        return ordinal() + 1;
      }
    }
  }
}

