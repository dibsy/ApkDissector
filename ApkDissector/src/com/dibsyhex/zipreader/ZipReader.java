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
		zipReader.getZipContents("templerun.apk");
		
		
		//zipReader.getZipEntries("SillyGame.jpg");
		
		//zipReader.getStegFiles("df.jpg");
		//zipReader.getZipEntries("PdfReader.apk");
		//zipReader.zipOpener();
		
		
		
		
	}
	
	public void getStegFiles(String name) {
		try {
			File file=new File("SillyGame.jpg");	
			FileInputStream fileInputStream = new FileInputStream(file);
			
			FileOutputStream fileOutputStream=new FileOutputStream("test.zip");
			ZipOutputStream zipOutputStream=new ZipOutputStream(fileOutputStream);
			ZipEntry ze= new ZipEntry("a.zip");
    		zipOutputStream.putNextEntry(ze);
			int len;
			byte[] buffer = new byte[1024];
			while((len=fileInputStream.read(buffer))>0){
				zipOutputStream.write(buffer, 0, len);
			}
			
			zipOutputStream.close();
			fileOutputStream.close();
			fileInputStream.close();
		
			
			
			//Step 2 : Extract the contents of the zip
			
			
			
			
			
		
			
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
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
				
				
				
			}
			
			
			zipInputStream.close();
			
			
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	
	
	public void zipOpener(){
		try{
			File file=new File("a.zip");
			org.apache.commons.compress.archivers.zip.ZipFile zipFile=new org.apache.commons.compress.archivers.zip.ZipFile(file);
			Enumeration<ZipArchiveEntry>enumeration=zipFile.getEntries();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	
	

}
