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

package com.mgz.afp.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an AFP document containing a list of structured fields.
 */
@XmlRootElement(name = "AFPDocument")
@XmlAccessorType(XmlAccessType.FIELD)
public class AFPDocument {
    @XmlAnyElement(lax = true)
    private List<Object> structuredFields = new ArrayList<>();

    /**
     * Default constructor for AFPDocument.
     */
    public AFPDocument() {
    }

    /**
     * Gets the list of structured fields in this document.
     *
     * @return the list of structured fields
     */
    public List<Object> getStructuredFields() {
        return structuredFields;
    }

    /**
     * Sets the list of structured fields in this document.
     *
     * @param structuredFields the list of structured fields to set
     */
    public void setStructuredFields(List<Object> structuredFields) {
        this.structuredFields = structuredFields;
    }

    /**
     * Adds a structured field to this document.
     *
     * @param sf the structured field to add
     */
    public void addStructuredField(Object sf) {
        this.structuredFields.add(sf);
    }
}
