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
package com.mgz.afp.enums;

/**
 * The structured field category code. It identifies the lowest level component that can be
 * constructed using the structured field, such as document, active environment group, page, or
 * object. The same category code point assigned to a component's begin structured field also is
 * assigned to that component's end structured field. These code points identify and delimit an
 * entire component within a data stream or an encompassing component. See “Category Codes” on page
 * 23 for a description of category codes.
 */
public enum SFCategory {
  Undefined(0x00),

  PageSegment(0x5F),
  ObjectArea(0x6B),
  /**
   * See MO:DCA-L: The OS/2 Presentation Manager Metafile (.met) Format.
   */
  Reserved(0x77),
  IMImage(0x7B),
  CodePage(0x87), // ???
  Medium(0x88),
  Font(0x89),
  CodedFont(0x8A),
  Record(0x8D), // From line data SF RCD.
  XML(0x8E), // From line data SF XMD.
  ProcessElement(0x90),
  ObjectContainer(0x92),
  PresentationText(0x9B),
  PrintFile(0xA5),
  Index(0xA7),
  Document(0xA8),
  PageGroup(0xAD),
  Page(0xAF),
  DataMap(0xCA),
  Graphics(0xBB),
  DataResource(0xC3),
  DocumentEnvironmentGroup(0xC4),
  FormEnvironmentGoup(0xC5), // ???
  ResourceGroup(0xC6),
  ObjectEnvironmentGroup(0xC7),
  ActiveEnvironmentGroup(0xC9),
  PageMap(0xCB),
  MediumMap(0xCC),
  FormMap(0xCD),
  NameResource(0xCE),
  PageOverlay(0xD8),
  ResourceEnvironmentGroup(0xD9),
  Overlay(0xDF),
  DataMapTransSubcase(0xE3), // ???
  LineDescriptorCount(0xE7), // ???
  DataSuppression(0xEA),
  BarCode(0xEB),
  FixedData(0xEC), // ???
  NoOperation(0xEE),
  Image(0xFB);


  int val;

  SFCategory(int val) {
    this.val = val;
  }

  public static SFCategory valueOf(int sfCategoryByte) {
    for (SFCategory sfCategory : SFCategory.values()) {
      if (sfCategory.val == sfCategoryByte) return sfCategory;
    }
    return Undefined;
  }

  public int toByte() {
    return val;
  }
}
