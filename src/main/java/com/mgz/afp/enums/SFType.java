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
 * The structured field type code. The type code identifies the function of the structured field,
 * such as begin, end, descriptor, or data. See “Type Codes” on page 22 for a description of type
 * codes.
 */
public enum SFType {
  /**
   * Indicates that the {@link SFType} is unknown.
   */
  Undefined(0x00),

  /**
   * FOCA ???
   */
  Index(0x8C),

  /**
   * An attribute structured field such as name and value defines an attribute with parameters.
   */
  Attribute(0xA0),
  /**
   * A copy count structured field specifies groups of sheet copies, called copy subgroups, that are
   * to be generated, and identifies modification control structured fields that specify
   * modifications to be applied to each group.
   */
  CopyCount_PatternsMap(0xA2),
  /**
   * A descriptor structured field defines the initial characteristics and,	optionally, the
   * formatting directives for all objects, object areas, and pages. Depending on the specific
   * descriptor structured field type, it may contain some set of parameters that identify:<ul><li>
   * The size of the page or object<li>Measurement units<li>Initial presentation conditions</ul>
   */
  Descriptor(0xA6),
  /**
   * A control structured field specifies the type of modifications that are to be applied to a
   * group of sheet copies, or a copy subgroup.
   */
  Control(0xA7),
  /**
   * A begin structured field introduces and identifies a document component. In general, a begin
   * structured field may contain a parameter that identifies the name of the component.
   */
  Begin(0xA8),
  /**
   * An end structured field identifies the end of a document component. In general, an end
   * structured field may contain a parameter that identifies the name of the component.
   */
  End(0xA9),
  /**
   * ???
   */
  Size(0xAA),
  /**
   * A map structured field	provides the following functions in the MO:DCA architecture:<ul><li>All
   * occurrences of a variable embedded in structured field parameter data can be given a new value
   * by changing only one reference in the mapping, rather than having to physically change each
   * occurrence. Thus all references to font X may cause a Times Roman font to be used in one
   * instance and a Helvetica font in another instance merely by specifying the proper map coded
   * font structured field. <li>The presence of the map structured field in a MO:DCA environment
   * group indicates use of the named resource within the scope of the environment group.</ul>
   */
  Map(0xAB),
  /**
   * A position structured field specifies the coordinate offset value and orientation for
   * presentation spaces.
   */
  Position(0xAC),
  /**
   * A process structured field specifies processing to be performed on an object.
   */
  Process(0xAD),
  /**
   * FOCA ???
   */
  Orientation(0xAE),
  /**
   * An include structured field selects a named resource which is to be embedded in the including
   * data stream as if it appeared inline. External resource object names on the begin structured
   * field may or may not coincide with the library name of that object, as library name resolution
   * is outside the scope of the MO:DCA architecture.
   */
  Include(0xAF),
  /**
   * See MO:DCA-L: The OS/2 Presentation Manager Metafile (.met) Format.
   */
  Reserved(0xB0),
  /**
   * A migration structured field is used to distinguish the MO:DCA structured field from a
   * structured field with the same acronym from an earlier data-stream architecture. The earlier
   * version is called Format 1. The MO:DCA version is called Format 2.
   */
  Migration(0xB1),
  /**
   * A variable structured field defines or contains variable information.
   */
  Variable(0xB2),
  /**
   * A link structured field defines a logical connection, or linkage, between two document
   * components.
   */
  Link(0xB4),
  /**
   * A data structured field consists of data whose meaning and interpretation is governed by the
   * object architecture for the particular data object type.
   */
  Data(0xEE);


  int val;

  SFType(int val) {
    this.val = val;
  }

  public static SFType valueOf(int sfTypeByte) {
    for (SFType sfType : SFType.values()) {
      if (sfType.val == sfTypeByte) return sfType;
    }
    return Undefined;
  }

  public int toByte() {
    return val;
  }
}
