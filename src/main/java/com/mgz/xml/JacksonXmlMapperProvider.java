/*
Copyright 2024 Rudolf Fiala

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

package com.mgz.xml;

import com.fasterxml.aalto.stax.InputFactoryImpl;
import com.fasterxml.aalto.stax.OutputFactoryImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * Provides a thread-safe, singleton {@link XmlMapper} configured for AFP to XML conversion.
 */
public class JacksonXmlMapperProvider {

  private static final XmlMapper XML_MAPPER;
  private static final XmlMapper FRAGMENT_MAPPER;

  static {
    XML_MAPPER = XmlMapper.builder(new XmlFactory(new InputFactoryImpl(), new OutputFactoryImpl()))
        .nameForTextElement("text")
        .build();
    // Honor JAXB annotations
    XML_MAPPER.registerModule(new JaxbAnnotationModule());
    // Match current JAXB output formatting
    XML_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    XML_MAPPER.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    // Do not serialize empty or null fields, similar to JAXB default behavior in many cases
    XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

    FRAGMENT_MAPPER = XML_MAPPER.copy();
    FRAGMENT_MAPPER.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, false);
  }

  /**
   * Returns the singleton {@link XmlMapper} instance.
   *
   * @return the XmlMapper
   */
  public static XmlMapper getMapper() {
    return XML_MAPPER;
  }

  /**
   * Returns the singleton {@link XmlMapper} instance configured for fragment writing.
   *
   * @return the fragment XmlMapper
   */
  public static XmlMapper getFragmentMapper() {
    return FRAGMENT_MAPPER;
  }
}
