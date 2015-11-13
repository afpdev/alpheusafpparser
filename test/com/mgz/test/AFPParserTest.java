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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.Test;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;


public class AFPParserTest {



	@Test
	public void testParsingAllTestFiles() throws IOException, AFPParserException, NoSuchAlgorithmException {
		AFPParserConfiguration pc = new AFPParserConfiguration();
		for(File afpFile : Constants.afpFiles){

			System.out.println(" --- FILE: " + afpFile.getAbsolutePath());

			pc.setInputStream(new FileInputStream(afpFile));

			AFPParser parser = new AFPParser(pc);

			StructuredField sf = null;
			do{
				sf = parser.parseNextSF();
				if(sf!=null){
					StructuredFieldIntroducer sfi = sf.getStructuredFieldIntroducer();
					assertNotNull(sfi);
					// System.out.println("0x" + Integer.toHexString(sfi.getFileOffset()).toUpperCase() + ": " + sfi.getSFTypeID().name());
				}
			}while(sf!=null); 

			pc.getInputStream().close();		
		}
	}
	
	public void processAFPFile(File afpFile) throws AFPParserException, IOException {
		AFPParserConfiguration pc = new AFPParserConfiguration();
		pc.setInputStream(new FileInputStream(afpFile));


		FileOutputStream fos = new FileOutputStream(new File("output.afp"));

		AFPParser parser = new AFPParser(pc);

		StructuredField sf = null;
		do{
			sf = parser.parseNextSF();
			if(sf!=null){

				// Do your thing with the SF.		
				// ...


				// Finally write the SF to output stream..
				sf.writeAFP(fos, pc);
			}

		}while(sf!=null); 

	}



	@Test
	public void testAFPSerializationActualClassType() throws IOException, AFPParserException, NoSuchAlgorithmException {
		AFPParserConfiguration pc = new AFPParserConfiguration();
		pc.setParseToStructuredFieldsBaseData(false);
		File tmpFile = new File("junit_testWritingTmp.afp");

		MessageDigest mdIs = MessageDigest.getInstance("MD5");
		MessageDigest mdOs = MessageDigest.getInstance("MD5");

		for(File afpFile : Constants.afpFiles){		


			DigestInputStream dis = new DigestInputStream(new FileInputStream(afpFile), mdIs);
			pc.setInputStream(dis);

			DigestOutputStream dos = new DigestOutputStream( new FileOutputStream(tmpFile),mdOs); 

			AFPParser parser = new AFPParser(pc);

			StructuredField sf = null;
			do{
				sf = parser.parseNextSF();
				if(sf!=null){ 
					sf.writeAFP(dos,pc);

					assertArrayEquals(afpFile.getName() + " 0x" + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " +sf.getClass().getSimpleName(), 
							dis.getMessageDigest().digest(),
							dos.getMessageDigest().digest()
							);

				}

			}while(sf!=null); 

			dos.close();
			dis.close();
			tmpFile.delete();
		}

	}

	@Test
	public void testAFPSerializationStructuredFieldBase() throws IOException, AFPParserException, NoSuchAlgorithmException {
		AFPParserConfiguration pc = new AFPParserConfiguration();
		pc.setParseToStructuredFieldsBaseData(true);
		File tmpFile = new File("junit_testWritingTmp.afp");

		MessageDigest mdIs = MessageDigest.getInstance("MD5");
		MessageDigest mdOs = MessageDigest.getInstance("MD5");

		for(File afpFile : Constants.afpFiles){		


			DigestInputStream dis = new DigestInputStream(new FileInputStream(afpFile), mdIs);
			pc.setInputStream(dis);

			DigestOutputStream dos = new DigestOutputStream( new FileOutputStream(tmpFile),mdOs); 

			AFPParser parser = new AFPParser(pc);

			StructuredField sf = null;
			do{
				sf = parser.parseNextSF();
				if(sf!=null){ 
					sf.writeAFP(dos,pc);
					if(!Arrays.equals(mdIs.digest(), mdOs.digest())){

						sf.writeAFP(dos,pc);
						assertArrayEquals(afpFile.getName() + " 0x" + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " +sf.getClass().getSimpleName(), 
								dis.getMessageDigest().digest(),
								dos.getMessageDigest().digest()
								);
					}

				}

			}while(sf!=null); 

			dos.close();
			dis.close();
			tmpFile.delete();
		}

	}
}
