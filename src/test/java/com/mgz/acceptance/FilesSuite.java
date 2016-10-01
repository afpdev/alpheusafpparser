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
package com.mgz.acceptance;

import java.io.File;
import java.io.FileFilter;


public class FilesSuite {

  private static final String[] allowedExtensions = {".afp", ".ovl", ".240", ".300", ".cdp", ".psg"};
  private static File[] afpFiles;

  public static File[] getAfpFiles() {
    if (afpFiles == null) {
      synchronized (FilesSuite.class) {
        if (afpFiles == null) {
          afpFiles = findFiles(new File("./src/test/resources/afp"));
        }
      }
    }
    return afpFiles;
  }

  private static File[] findFiles(File root) {
    return root.listFiles(new FileFilter() {
      public boolean accept(File file) {
        return file.isFile() && isAllowedExtension(file.getName());
      }
    });
  }

  private static boolean isAllowedExtension(final String fileName) {
    for (String extension : allowedExtensions) {
      if (fileName.toLowerCase().endsWith(extension)) {
        return true;
      }
    }
    return false;
  }
}
