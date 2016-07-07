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

/**
 * MO:DCA, page 297.<br> <br> The No Operation structured field performs no function.<br> The No
 * Operation structured field may be used to carry comments or any other type of unarchitected data.
 * Although this is not recommended, it may also be used to carry semantic data in private or
 * exchange data streams. However, because receivers of interchange data streams should ignore the
 * content of No Operation structured fields, and because receiver-generator products are not
 * required to propagate No Operation structured fields, no semantics should be attached to the data
 * carried by the No Operation structured field in interchange data streams.
 */
public class NOP_NoOperation extends StructuredFieldBaseData {

}
