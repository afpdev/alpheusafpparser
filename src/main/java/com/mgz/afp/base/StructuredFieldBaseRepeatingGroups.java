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

import com.mgz.afp.base.annotations.AFPField;

import java.util.ArrayList;
import java.util.List;

public abstract class StructuredFieldBaseRepeatingGroups extends StructuredField implements IHasRepeatingGroups {
  @AFPField
  private List<IRepeatingGroup> repeatingGroups;

  @Override
  public final List<IRepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  @Override
  public final void setRepeatingGroups(List<IRepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  @Override
  public final void addRepeatingGroup(IRepeatingGroup rg) {
    if (rg != null) {
      if (repeatingGroups == null) {
        repeatingGroups = new ArrayList<IRepeatingGroup>();
      }
      repeatingGroups.add(rg);
    }
  }

  @Override
  public final void removeRepeatingGroup(IRepeatingGroup rg) {
    if (repeatingGroups != null) {
      repeatingGroups.remove(rg);
    }
  }

  @Override
  public void accept(final StructuredFieldVisitor visitor) {
    visitor.handle(this);
  }
}
