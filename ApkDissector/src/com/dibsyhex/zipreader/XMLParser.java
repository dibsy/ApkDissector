package com.dibsyhex.zipreader;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

	public static void main(String arg[]) throws Exception{
	    String xmlRecords = "<data><employee><name>A</name>"
	        + "<title>Manager</title></employee></data>";

	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xmlRecords));

	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("employee");

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element element = (Element) nodes.item(i);

	      NodeList name = element.getElementsByTagName("name");
	      Element line = (Element) name.item(0);
	      System.out.println("Name: " + getCharacterDataFromElement(line));

	      NodeList title = element.getElementsByTagName("title");
	      line = (Element) title.item(0);
	      System.out.println("Title: " + getCharacterDataFromElement(line));
	    }

	  }

	  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }

}
