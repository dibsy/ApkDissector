package com.dibsyhex.zipreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class HexDump {

	public static void hexDump(PrintStream out, File file) throws Exception {
	      InputStream is = new FileInputStream(file);
	      int i = 0;
	 
	      while (is.available() > 0) {
	        StringBuilder sb1 = new StringBuilder();
	        StringBuilder sb2 = new StringBuilder("   ");
	        out.printf("%04X  ", i * 16);
	        for (int j = 0; j < 16; j++) {
	           if (is.available() > 0) {
	             int value = (int) is.read();
	             sb1.append(String.format("%02X ", value));
	             if (!Character.isISOControl(value)) {
	               sb2.append((char)value);
	             }
	             else {
	               sb2.append(".");
	             }
	           }
	           else {
	             for (;j < 16;j++) {
	               sb1.append("   ");
	             }
	           }
	        }
	        out.print(sb1);
	        out.println(sb2);
	        i++;
	      }
	      is.close();
	  }

	  
	  public static void main(String args[]) throws Exception {
	    // dump to the console
	    HexDump.hexDump(System.out, new File("df.jpg"));
	    // dump to a file
	    HexDump.hexDump(new java.io.PrintStream("df.hex"), new File("df.jpg"));
	    System.out.println("Done.");
	  }  

}
