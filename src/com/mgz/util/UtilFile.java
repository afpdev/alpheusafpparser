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
package com.mgz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilFile {
	public static byte[] digestFile(File file) throws IOException{
		byte[] buffer = new byte[10 * 1024];

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

			InputStream is = null;
			DigestInputStream dis = null;
			try  {
				is = new FileInputStream(file);
				dis = new DigestInputStream(is, md);
				while(dis.read(buffer)>-1);
			}finally{
				try {
					if(dis!=null) dis.close();
					else if(is!=null) is.close();
				} catch (IOException e) {}
			}
			byte[] digest = md.digest();
			return digest;
		} catch (NoSuchAlgorithmException e1) {
			return null;
		}		
	}
}
