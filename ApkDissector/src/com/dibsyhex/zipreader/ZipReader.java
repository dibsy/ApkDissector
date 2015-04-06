/*
 * ZipReader: Decompiles APK Files 
    Copyright (C) 2015  Dibyendu Sikdar @dibsyhex

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */


package com.dibsyhex.zipreader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.*;

import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
public class ZipReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipReader zipReader=new ZipReader();
		zipReader.getZipContents("apks/PDFReader.apk");
		
		
		//zipReader.getZipEntries("SillyGame.jpg");
		
		//zipReader.getStegFiles("df.jpg");
		//zipReader.getZipEntries("PdfReader.apk");
		//zipReader.zipOpener();
		
		
		
		
	}
	
	

	public void getZipEntries(String name){
		try{
			File file=new File(name);
			ZipFile zipFile=new ZipFile(file);
			Enumeration<? extends ZipEntry>enumeration=zipFile.entries();
			
			System.out.println("Listing Entries in the zipfile");
			while(enumeration.hasMoreElements()){
				Object key=enumeration.nextElement();
				System.out.println(key.toString()+":"+zipFile.getEntry(key.toString()));
			}
						
			zipFile.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	
	public void getZipContents(String name){
		try {
			File file=new File(name);			
			FileInputStream fileInputStream=new FileInputStream(file);
			ZipInputStream zipInputStream=new ZipInputStream(fileInputStream);			
			System.out.println(zipInputStream.available());
			//System.out.println("Reading each entries in details:");
			ZipFile zipFile=new ZipFile(file);
			ZipEntry zipEntry;
			while((zipEntry=zipInputStream.getNextEntry())!=null){
				String filename="extracts/"+File.separator+zipEntry.getName();
				System.out.println(filename);
				File extractDirectory=new File(filename);
				
				//Create the directories
				new File(extractDirectory.getParent()).mkdirs();
				
				//Now write the contents
				
				InputStream inputStream=zipFile.getInputStream(zipEntry);
				OutputStream outputStream=new FileOutputStream(extractDirectory);
				
				FileUtils.copyInputStreamToFile(inputStream,extractDirectory);
				
				
				//Decode the xml files
				
				if(filename.endsWith(".xml")){
					//First create a temp file at a location temp/extract/...
					File temp=new File("temp"+File.separator+extractDirectory);
					new File(temp.getParent()).mkdirs();
					
					//Create an object of XML Decoder
					XmlDecoder xmlDecoder=new XmlDecoder();
					InputStream inputStreamTemp=new FileInputStream(extractDirectory);
					byte[] buf = new byte[50000];
					int bytesRead = inputStreamTemp.read(buf);
					String xml = xmlDecoder.decompressXML(buf);					
					System.out.println(xml);
					FileUtils.writeStringToFile(temp, xml);
					 
					//Now rewrite the files at the original locations
					
					FileUtils.copyFile(temp, extractDirectory);
					
					
					
					
				}
				
				
				
			}
			
			
			zipInputStream.close();
			
		
			
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
