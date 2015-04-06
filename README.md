# APK Dissector

APK Dissector is a Java Based APK Decompiler 


And this is still in development phase!

```java
System.out.out.println("More features will be added soon");
```

### Download and import this project in Eclipse to test all its features

### How to use

Import the project in Eclipse
Put the APK file which needs to be decompiled in the apks/ directory
Go to the Java File ZipReader . Go to main and modify the line with the name of the apk to decompile.
You will find the decompiled app in the extract directory

```java
zipReader.getZipContents("apks/PDFReader.apk");
```


