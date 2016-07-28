# Apk Dissector 

## Java Based APK Decompiler

Basic useful feature list:

 * Purely Java Based
 * Analyze the contents of the APK file
 * Decompile and extract the contents of the APK file
 * Decompile the DEX files to JAVA source files (.dex to .java) [ New feature in v2.0 ]
 
## How to use this application ?
1.From source 
2.Executable Jar

1.You can clone/download the source and import in Eclipse and then run the ApkDissector.java file


2. Download and extract the zip file containing the executable Jar file from https://sourceforge.net/projects/apkdissector/

Double click and open the jar file 

 * Select APK/DEX - Browse Selects the APK/DEX file.
 * Analyze - Find information about the contents inside the apk file.
 * Decompile APK/DEX - Decompile and extracts the contents on the APK file.
 
How to decompile an APK file ?

Once you click decompile it will create a folder called extract and inside that you will find a folder having same name as the file where you will get all the extracted contents of the apk

 * Select APK/DEX - Click this button to browse and select the APK file.
 * Decompile APK/DEX - Click this button to  decompile and extracts the contents on the APK file.
 * All the extracted files will be with in extracts directory within the folder name <apkfileName>.apk

How to decompile the DEX file ?
 * Select APK/DEX - Click this button to browse and select the DEX file which will be located inside the extracts/<apkfileName>.apk directory
 * Decompile APK/DEX - Click this button to  decompile and extracts the contents on the DEX file.
 * All the extracted files will be under extracts within <apkFilename.apk>\DEX_extracts

 
 

## How to use this project ?

If your are trying to fork or extented the project here are some information regarding this project.

Run the AppDissector to see the output !

And here's some code! :+1:

```java
System.out.println("More features will be added soon");
```
