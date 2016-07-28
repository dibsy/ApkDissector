package com.dibsyhex.apkdissector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jadx.api.JadxDecompiler;

public class DecompileDex2Class {

	public Response response=null;
	
	public boolean isWindows(){
		String os=System.getProperty("os.name");
		if(os.toLowerCase().indexOf("windows")>=0)
			return true;
		else
			return false;
	}
	
	public void dexDecompile(String dexFileName){
		
		
		
		
		try {
			
			response.displayLog("Processing...");
			
			JadxDecompiler jadx = new JadxDecompiler();
			File file=new File(dexFileName);//file object
			String outDirName=file.getParent()+File.separator+"DEX_extracts";
			
			System.out.println(file);
			System.out.println(outDirName);
			
			File outDir=new File(outDirName);
			jadx.setOutputDir(outDir);
			List<File> fileList=new ArrayList<File>();
			fileList.add(new File(dexFileName));
			jadx.loadFiles(fileList);
			jadx.save();
			
			response.displayLog("Done");
			
		}catch(Exception e){
			response.displayError(e.toString());
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dexFileName = "D:\\Projects\\Java\\ApkDissector\\extracts\\PDFReader.apk\\classes.dex";
		
		DecompileDex2Class decompileDex2Class=new DecompileDex2Class();
		decompileDex2Class.dexDecompile(dexFileName);
		
	}

}
