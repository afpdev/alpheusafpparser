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
package com.mgz.afp.lineData;

import com.mgz.afp.base.StructuredFieldBaseName;

/**
 *  Programming Guide and Line Data Reference(ha3l3r04.pdf), page 93.<br>
 *  <br>
 *  The Invoke Data Map structured field selects a new Data Map for printing line
data and ends the current line-format page. With LND Data Maps, processing
begins with the first Line Descriptor (LND) structured field of the invoked Data
Map for the next line-format page. With RCD Data Maps, processing begins with
the first Record Descriptor (RCD) structured field that matches the Record ID of
the current line-data record. With XMD Data Maps, processing begins with the first
XML Descriptor (XMD) structured field that matches the current Qualified Tag.
 */

public class IDM_InvokeDataMap extends StructuredFieldBaseName{
}
