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
package com.mgz.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.writer.AFPWriterHumanReadable;
import com.mgz.afp.writer.IAFPWriter;


public class AFPWriterTest {

	@Test
	public void testWriteSF() throws IOException, AFPParserException {
		AFPParserConfiguration pc = new AFPParserConfiguration();
		
		IAFPWriter afpWriter = new AFPWriterHumanReadable();
		
		OutputStream os = new FileOutputStream("./output/" + AFPWriterTest.class.getSimpleName()+ ".tmp");
		
		for(File afpFile : Constants.afpFiles){

			System.out.println(" --- -------------------------------------");
			System.out.println(" --- FILE: " + afpFile.getAbsolutePath());
			System.out.println(" --- -------------------------------------");
			
			pc.setInputStream(new FileInputStream(afpFile));

			AFPParser parser = new AFPParser(pc);

			StructuredField sf = null;
			do{
				sf = parser.parseNextSF();
				if(sf!=null){
					os.write(afpWriter.writeSF(sf).getBytes());
				}
			}while(sf!=null); 

			pc.getInputStream().close();		
		}
		os.close();
	}

}
