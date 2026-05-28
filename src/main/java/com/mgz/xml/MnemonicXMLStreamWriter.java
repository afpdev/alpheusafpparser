/*
Copyright 2026 Rudolf Fiala

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

import com.mgz.util.MnemonicPerformanceMonitor;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamWriter2;
import org.codehaus.stax2.util.StreamWriter2Delegate;

/**
 * A decorator for {@link XMLStreamWriter2} that measures the time spent writing each element.
 */
public class MnemonicXMLStreamWriter extends StreamWriter2Delegate {

  private final Deque<String> elementStack = new ArrayDeque<>();

  public MnemonicXMLStreamWriter(XMLStreamWriter2 delegate) {
    super(delegate);
  }

  @Override
  public void writeStartElement(String localName) throws XMLStreamException {
    String mnemonic = MnemonicPerformanceMonitor.extractMnemonic(localName);
    MnemonicPerformanceMonitor.startWrite(localName);
    elementStack.push(mnemonic != null ? mnemonic : "");
    super.writeStartElement(localName);
  }

  @Override
  public void writeStartElement(String namespaceUri, String localName) throws XMLStreamException {
    String mnemonic = MnemonicPerformanceMonitor.extractMnemonic(localName);
    MnemonicPerformanceMonitor.startWrite(localName);
    elementStack.push(mnemonic != null ? mnemonic : "");
    super.writeStartElement(namespaceUri, localName);
  }

  @Override
  public void writeStartElement(String prefix, String localName, String namespaceUri)
      throws XMLStreamException {
    String mnemonic = MnemonicPerformanceMonitor.extractMnemonic(localName);
    MnemonicPerformanceMonitor.startWrite(localName);
    elementStack.push(mnemonic != null ? mnemonic : "");
    super.writeStartElement(prefix, localName, namespaceUri);
  }

  @Override
  public void writeEmptyElement(String namespaceUri, String localName) throws XMLStreamException {
    MnemonicPerformanceMonitor.startWrite(localName);
    super.writeEmptyElement(namespaceUri, localName);
    MnemonicPerformanceMonitor.endWrite();
  }

  @Override
  public void writeEmptyElement(String prefix, String localName, String namespaceUri)
      throws XMLStreamException {
    MnemonicPerformanceMonitor.startWrite(localName);
    super.writeEmptyElement(prefix, localName, namespaceUri);
    MnemonicPerformanceMonitor.endWrite();
  }

  @Override
  public void writeEmptyElement(String localName) throws XMLStreamException {
    MnemonicPerformanceMonitor.startWrite(localName);
    super.writeEmptyElement(localName);
    MnemonicPerformanceMonitor.endWrite();
  }

  @Override
  public void writeEndElement() throws XMLStreamException {
    super.writeEndElement();
    if (!elementStack.isEmpty()) {
      String mnemonic = elementStack.pop();
      if (!mnemonic.isEmpty()) {
        MnemonicPerformanceMonitor.endWrite();
      }
    }
  }
}
