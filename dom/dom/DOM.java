package dom;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException; 
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;

import java.io.*;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;




public class DOM {

    

    public static void main(String[] args) 
    {
     DOM obj = new DOM();
     
     try
     {
     obj.buildDOM();
     } 
     catch(Exception e)
     {
      System.out.println("failed to build dom");
     }
     }
   
   public void buildDOM() throws Exception
   {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try 
        {
           builder = builderFactory.newDocumentBuilder();
        } 
        catch (Exception e)
        {
           e.printStackTrace();  
           
        }
    
       
        Document DOM = null;
        try 
        {
           DOM = builder.parse(new FileInputStream(".\\UMLExample.xmi"));
        } 
        catch (SAXException e) 
        {
           e.printStackTrace();
           
        } 
        catch (IOException e) 
        {
           e.printStackTrace();
          
        }   
        
      DOM.getDocumentElement().normalize();
      
      Element root = DOM.getDocumentElement();
      
     NodeList list = root.getElementsByTagName("ownedMember");
     NodeList sublist = list.item(0).getChildNodes();
     
     System.out.println(sublist.item(0).getNodeValue().toString());
      
      ///////
      DOM obj = new DOM();
      
      DataOutputStream stream = new DataOutputStream(new FileOutputStream(".\\testfile.txt"));
      obj.printDocument(DOM, stream);
      
      
     
     
     }
   
   public static void printDocument(Document doc, OutputStream out) throws Exception{
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

    transformer.transform(new DOMSource(doc), 
         new StreamResult(new OutputStreamWriter(out, "UTF-8")));
}
   
   
   }

