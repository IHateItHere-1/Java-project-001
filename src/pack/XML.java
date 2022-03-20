package pack;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XML {	 
	 public static String GetXMLFilePTIDString (String path) 
	 {

	      // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try 
	      {	    	  
	    	  // avoid XXE stupidity (CVE-2021-38555)
	          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();
	          Document doc = db.parse(new File(path));	       
	          doc.getDocumentElement().normalize();	          
	          return doc.getElementsByTagName("patient-id")
				  .item(0).getLastChild().getTextContent();
	          
	      } 
	      catch (ParserConfigurationException | SAXException | IOException e) 
	      {
	          e.printStackTrace();
	      }
	      return null;

	  }
		 
}
