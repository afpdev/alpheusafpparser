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

package com.mgz.afp.modca;

import com.mgz.afp.base.StructuredFieldBaseData;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.moca.MetadataObject;
import com.mgz.afp.parser.AFPParserConfiguration;
import javax.xml.bind.annotation.XmlElement;

/**
 * MO:DCA, page 304.<br> <br> The Object Container Data structured field contains the data for an
 * object carried in an object container. See “Object Type Identifiers” in MO:DCA spec on page 623
 * for the list of object types that may be carried in an object container.
 */
public class OCD_ObjectContainerData extends StructuredFieldBaseData {

  private MetadataObject metadataObject;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config)
      throws AFPParserException {
    super.decodeAFP(sfData, offset, length, config);

    // MOCA Metadata Object starts with MOLength (4 bytes, >= 50) and HeaderLength (2 bytes, >= 46)
    // and is carried in OCD structured fields.
    if (data != null && data.length >= 50) {
      try {
        MetadataObject mo = new MetadataObject();
        mo.decode(data);
        // Basic validation: moLength should match data length (though it can be split across OCDs,
        // for now we handle single-OCD MOCA objects)
        if (mo.getMoLength() == data.length && mo.getHeaderLength() >= 46) {
          this.metadataObject = mo;
        }
      } catch (Exception e) {
        // Not a MOCA object or failed to decode, keep it as opaque data
      }
    }
  }

  @XmlElement(name = "MetadataObject")
  public MetadataObject getMetadataObject() {
    return metadataObject;
  }

  public void setMetadataObject(MetadataObject metadataObject) {
    this.metadataObject = metadataObject;
  }
}
