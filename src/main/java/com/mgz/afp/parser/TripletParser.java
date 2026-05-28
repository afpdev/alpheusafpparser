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

package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.triplets.Triplet;
import com.mgz.afp.triplets.Triplet.TripletID;
import com.mgz.afp.triplets.TripletPool;
import com.mgz.util.UtilBinaryDecoding;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class TripletParser {

  private static final EnumMap<TripletID, Supplier<Triplet>> TRIPLET_SUPPLIERS =
      new EnumMap<>(TripletID.class);

  static {
    TRIPLET_SUPPLIERS.put(TripletID.CodedGraphicCharacterSetGlobalID, Triplet.CodedGraphicCharacterSetGlobalID::new);
    TRIPLET_SUPPLIERS.put(TripletID.FullyQualifiedName, Triplet.FullyQualifiedName::new);
    TRIPLET_SUPPLIERS.put(TripletID.MappingOption, Triplet.MappingOption::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectClassification, Triplet.ObjectClassification::new);
    TRIPLET_SUPPLIERS.put(TripletID.MODCAInterchangeSet, Triplet.MODCAInterchangeSet::new);
    TRIPLET_SUPPLIERS.put(TripletID.TextOrientation, Triplet.TextOrientation::new);
    TRIPLET_SUPPLIERS.put(TripletID.LineDataObjectPositionMigration, Triplet.LineDataObjectPositionMigration::new);
    TRIPLET_SUPPLIERS.put(TripletID.FontDescriptorSpecification, Triplet.FontDescriptorSpecification::new);
    TRIPLET_SUPPLIERS.put(TripletID.FontCodedGraphicCharacterSetGlobalID, Triplet.FontCodedGraphicCharacterSetGlobalID::new);
    TRIPLET_SUPPLIERS.put(TripletID.ResourceObjectType, Triplet.ResourceObjectType::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectFunctionSetSpecification_Retired, Triplet.ObjectFunctionSetSpecification_Retired::new);
    TRIPLET_SUPPLIERS.put(TripletID.ExtendedResourceLocalIdentifier, Triplet.ExtendedResourceLocalIdentifier::new);
    TRIPLET_SUPPLIERS.put(TripletID.ResourceLocalIdentifier, Triplet.ResourceLocalIdentifier::new);
    TRIPLET_SUPPLIERS.put(TripletID.ResourceSectionNumber, Triplet.ResourceSectionNumber::new);
    TRIPLET_SUPPLIERS.put(TripletID.CharacterRotation, Triplet.CharacterRotation::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectByteOffset, Triplet.ObjectByteOffset::new);
    TRIPLET_SUPPLIERS.put(TripletID.AttributeValue, Triplet.AttributeValue::new);
    TRIPLET_SUPPLIERS.put(TripletID.DescriptorPosition, Triplet.DescriptorPosition::new);
    TRIPLET_SUPPLIERS.put(TripletID.MediaEjectControl, Triplet.MediaEjectControl::new);
    TRIPLET_SUPPLIERS.put(TripletID.PageOverlayConditionalProcessing, Triplet.PageOverlayConditionalProcessing::new);
    TRIPLET_SUPPLIERS.put(TripletID.ResourceUsageAttribute, Triplet.ResourceUsageAttribute::new);
    TRIPLET_SUPPLIERS.put(TripletID.MeasurementUnits, Triplet.MeasurementUnits::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectAreaSize, Triplet.ObjectAreaSize::new);
    TRIPLET_SUPPLIERS.put(TripletID.AreaDefinition, Triplet.AreaDefinition::new);
    TRIPLET_SUPPLIERS.put(TripletID.ColorSpecification, Triplet.ColorSpecification::new);
    TRIPLET_SUPPLIERS.put(TripletID.EncodingSchemeID, Triplet.EncodingSchemeID::new);
    TRIPLET_SUPPLIERS.put(TripletID.MediumMapPageNumber, Triplet.MediumMapPageNumber::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectByteExtent, Triplet.ObjectByteExtent::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectStructuredFieldOffset, Triplet.ObjectStructuredFieldOffset::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectStructuredFieldExtent, Triplet.ObjectStructuredFieldExtent::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectOffset, Triplet.ObjectOffset::new);
    TRIPLET_SUPPLIERS.put(TripletID.FontHorizontalScaleFactor, Triplet.FontHorizontalScaleFactor::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectCount, Triplet.ObjectCount::new);
    TRIPLET_SUPPLIERS.put(TripletID.LocalObjectDateAndTimeStamp, Triplet.LocalObjectDateAndTimeStamp::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectChecksum, Triplet.ObjectChecksum::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectOriginIdentifier, Triplet.ObjectOriginIdentifier::new);
    TRIPLET_SUPPLIERS.put(TripletID.Comment, Triplet.Comment::new);
    TRIPLET_SUPPLIERS.put(TripletID.MediumOrientation, Triplet.MediumOrientation::new);
    TRIPLET_SUPPLIERS.put(TripletID.ResourceObjectInclude, Triplet.ResourceObjectInclude::new);
    TRIPLET_SUPPLIERS.put(TripletID.PresentationSpaceResetMixing, Triplet.PresentationSpaceResetMixing::new);
    TRIPLET_SUPPLIERS.put(TripletID.PresentationSpaceMixingRule, Triplet.PresentationSpaceMixingRule::new);
    TRIPLET_SUPPLIERS.put(TripletID.UniversalDateAndTimeStamp, Triplet.UniversalDateAndTimeStamp::new);
    TRIPLET_SUPPLIERS.put(TripletID.IMMInsertionTriplet, Triplet.IMMInsertionTriplet::new);
    TRIPLET_SUPPLIERS.put(TripletID.TonerSaver, Triplet.TonerSaver::new);
    TRIPLET_SUPPLIERS.put(TripletID.ColorFidelity, Triplet.ColorFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.FontFidelity, Triplet.FontFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.AttributeQualifier, Triplet.AttributeQualifier::new);
    TRIPLET_SUPPLIERS.put(TripletID.PagePositionInformation, Triplet.PagePositionInformation::new);
    TRIPLET_SUPPLIERS.put(TripletID.ParameterValue, Triplet.ParameterValue::new);
    TRIPLET_SUPPLIERS.put(TripletID.PresentationControl, Triplet.PresentationControl::new);
    TRIPLET_SUPPLIERS.put(TripletID.FontResolutionAndMetricTechnology, Triplet.FontResolutionAndMetricTechnology::new);
    TRIPLET_SUPPLIERS.put(TripletID.FinishingOperation, Triplet.FinishingOperation::new);
    TRIPLET_SUPPLIERS.put(TripletID.TextFidelity, Triplet.TextFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.MediaFidelity, Triplet.MediaFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.FinishingFidelity, Triplet.FinishingFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.DataObjectFontDescriptor, Triplet.DataObjectFontDescriptor::new);
    TRIPLET_SUPPLIERS.put(TripletID.LocaleSelector, Triplet.LocaleSelector::new);
    TRIPLET_SUPPLIERS.put(TripletID.MODCAFunctionSet, Triplet.MODCAFunctionSet::new);
    TRIPLET_SUPPLIERS.put(TripletID.UP3iFinishingOperation, Triplet.UP3iFinishingOperation::new);
    TRIPLET_SUPPLIERS.put(TripletID.ColorManagementResourceDescriptor, Triplet.ColorManagementResourceDescriptor::new);
    TRIPLET_SUPPLIERS.put(TripletID.RenderingIntent, Triplet.RenderingIntent::new);
    TRIPLET_SUPPLIERS.put(TripletID.CMRTagFidelity, Triplet.CMRTagFidelity::new);
    TRIPLET_SUPPLIERS.put(TripletID.DeviceAppearance, Triplet.DeviceAppearance::new);
    TRIPLET_SUPPLIERS.put(TripletID.KeepGroupTogether, Triplet.KeepGroupTogether::new);
    TRIPLET_SUPPLIERS.put(TripletID.SetupName, Triplet.SetupName::new);
    TRIPLET_SUPPLIERS.put(TripletID.ImageResolution, Triplet.ImageResolution::new);
    TRIPLET_SUPPLIERS.put(TripletID.ObjectContainerPresentationSpaceSize, Triplet.ObjectContainerPresentationSpaceSize::new);
    TRIPLET_SUPPLIERS.put(TripletID.TripletExtender, Triplet.TripletExtender::new);
  }

  /**
   * Parses Triplets from given data, starting at position offset for length bytes. If parameter
   * length = -1, it tries to parse all up to the end of sfData.
   *
   * @return List of resulting Triplets contained in the given data.
   */
  public static List<Triplet> parseTriplets(byte[] sfData, int offset, int length, AFPParserConfiguration config) {
    List<Triplet> resultingTriplets = new ArrayList<Triplet>();

    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    int pos = 0;
    while (pos < actualLength) {
      if (actualLength - pos < 2) {
        // Not enough data for even the shortest triplet (length + ID)
        Triplet.Undefined undef = new Triplet.Undefined();
        undef.setLength((short)(actualLength - pos));
        undef.setTripletID(TripletID.Undefined);
        undef.setParsingException(new AFPParserException("Not enough data for triplet at offset " + (offset + pos)));
        resultingTriplets.add(undef);
        break;
      }
      Triplet triplet;
      try {
        triplet = parseTriplet(sfData, offset + pos, actualLength - pos, config);
      } catch (Throwable pex) {
        Triplet.Undefined undef = null;
        triplet = undef = new Triplet.Undefined();
        if (pex instanceof AFPParserException) {
          undef.setParsingException((AFPParserException) pex);
        } else {
          undef.setParsingException(new AFPParserException("An exception occured while parsing triplet.", pex));
        }
        byte[] tripletData = new byte[actualLength];
        System.arraycopy(sfData, offset, tripletData, 0, actualLength);
        undef.setTripletData(tripletData);
        int tripletLength = sfData[offset + pos] & 0xFF;
        if (tripletLength < 2) {
          tripletLength = actualLength - pos;
        }
        undef.setLength((short) tripletLength);
        undef.setTripletID(TripletID.Undefined);
      }

      if (triplet instanceof Triplet.TripletExtender && !resultingTriplets.isEmpty()) {
        Triplet previous = resultingTriplets.get(resultingTriplets.size() - 1);
        handleTripletExtension(previous, (Triplet.TripletExtender) triplet, config);
      } else {
        resultingTriplets.add(triplet);
      }

      pos += triplet.getLength();
    }

    return resultingTriplets;
  }

  private static void handleTripletExtension(Triplet target, Triplet.TripletExtender extender, AFPParserConfiguration config) {
    var extraData = extender.getDatExt();
    if (extraData == null || extraData.length == 0) {
      return;
    }

    if (target instanceof Triplet.FullyQualifiedName fqn) {
      var oldBytes = fqn.getNameAsBytes();
      var newBytes = new byte[oldBytes.length + extraData.length];
      System.arraycopy(oldBytes, 0, newBytes, 0, oldBytes.length);
      System.arraycopy(extraData, 0, newBytes, oldBytes.length, extraData.length);
      fqn.setNameAsBytes(newBytes);
      fqn.setNameAsString(new String(newBytes, config.getAfpCharSet()));
    } else if (target instanceof Triplet.AttributeValue av) {
      var oldVal = av.getAttributeValue();
      if (oldVal == null) {
        oldVal = "";
      }
      var extraStr = new String(extraData, config.getAfpCharSet());
      av.setAttributeValue(oldVal + extraStr);
    } else if (target instanceof Triplet.Comment c) {
      var oldComment = c.getText();
      if (oldComment == null) {
        oldComment = "";
      }
      var extraComment = new String(extraData, config.getAfpCharSet());
      c.setComment(oldComment + extraComment);
    }
  }

  public static Triplet parseTriplet(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    Triplet resultingTriplet = null;

    StructuredField.checkDataLength(sfData, offset, length, 2);

    short tripletLength = UtilBinaryDecoding.parseShort(sfData, offset, 1);
    short actualTripletID = UtilBinaryDecoding.parseShort(sfData, offset + 1, 1);

    if (tripletLength < 2) {
      throw new AFPParserException("Invalid triplet length: " + tripletLength);
    }
    if (offset + tripletLength > sfData.length || (length != -1 && tripletLength > length)) {
      throw new AFPParserException("Triplet length exceeds available data.");
    }

    TripletID tripletID = null;

    if (actualTripletID != Triplet.UNFORTUNATE_TRIPLETID) {
      try {
        tripletID = TripletID.valueOf(actualTripletID);
      } catch (AFPParserException ex) {
        tripletID = TripletID.Undefined;
      }

    } else {
      // Handle unfortunate triplet ID.
      // Determine if it is a ResourceObjectType or ObjectFunctionSetSpecification_Retired;

      int significantNumber = UtilBinaryDecoding.parseInt(sfData, offset + 4, 2);
      if (significantNumber == 0x8000) {
        // It is a ObjectFunctionSetSpecification_Retired
        tripletID = TripletID.ObjectFunctionSetSpecification_Retired;
      } else {
        // It is a ResourceObjectType
        tripletID = TripletID.ResourceObjectType;
      }

    }

    if (tripletID == TripletID.Undefined) {
      resultingTriplet = new Triplet.Undefined();
      resultingTriplet.setLength(tripletLength);
      resultingTriplet.setTripletID(TripletID.Undefined);
    } else {
      resultingTriplet = createTripletInstance(tripletID);
      resultingTriplet.setLength(tripletLength);
    }

    resultingTriplet.decodeAFP(sfData, offset, tripletLength, config);

    return resultingTriplet;
  }

  public static final Triplet createTripletInstance(TripletID tid) {
    Triplet cs = TripletPool.acquire(tid);
    if (cs == null) {
      cs = TRIPLET_SUPPLIERS.getOrDefault(tid, Triplet.Undefined::new).get();
      cs.setTripletID(tid);
    }
    return cs;
  }
}
