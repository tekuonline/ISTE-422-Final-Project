import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;

import jdk.internal.org.xml.sax.SAXException;

public class ParseUnknownXMLStructure
{
   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
   {
      //Get Document Builder
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
       
      //Build Document
      Document document = null;
	try {
		document = builder.parse(new File("teacher"));
	} catch (org.xml.sax.SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
      //Normalize the XML Structure; It's just too important !!
      document.getDocumentElement().normalize();
       
      //Here comes the root node
      Element root = document.getDocumentElement();
      System.out.println(root.getNodeName());
       
      
      NodeList nAttr = document.getElementsByTagName("dia:attribute"); 
      NodeList diaObject = document.getElementsByTagName("dia:object");
      for (int i =0 ; i < diaObject.getLength(); i++){
    	  if (diaObject.item(i).getNodeType()== Node.ELEMENT_NODE) {
 	     System.out.println(((Element)(diaObject.item(i))).getAttributes().getNamedItem("type"));
    	  }
 	  }
      
      for (int temp = 0; temp < nAttr.getLength(); temp++){
    	 if (nAttr.item(temp).getAttributes().getNamedItem("name").getNodeValue().equals("name")){
         System.out.println(nAttr.item(temp).getAttributes().getNamedItem("name"));
         String s = "name=\"name\"";
         String g = nAttr.item(temp).getAttributes().getNamedItem("name").toString();
         if (g.equals(s)){
        	 NodeList table = document.getElementsByTagName("dia:string"); 
        	 for (int i =0 ; i < table.getLength(); i++){
        	     System.out.println(table.item(i).getTextContent());
        	  }
         }
         NodeList nList = document.getElementsByTagName("dia:string");
    	  for (int i =0 ; i < nList.getLength(); i++){
    	     //System.out.println(nList.item(i).getTextContent());
    	  }
    	  	}
          }
      
      //Get all employees
//      NodeList nList = document.getElementsByTagName("dia:string");
//      System.out.println("============================");
//      
//      for (int temp = 0; temp < nList.getLength(); temp++){
//      System.out.println(nList.item(temp).getTextContent());
//      }
       
 //     visitChildNodes(nList);
   }
 
   //This function is called recursively
//   private static void visitChildNodes(NodeList nList)
//   {
//	   
//	   for (int i = 0; i < nList.getLength(); i++){
//	   
//		   Node node = nList.item(i);
//		   NodeList nl = node.getChildNodes();
//	   
//	   
//	   
//	   for (int temp = 0; temp < nl.getLength(); temp++)
//	      {
//		   System.out.println(nl.item(temp).getNodeName());
//		   System.out.println(nList.item(temp).getChildNodes());
//	         
//		   Node node = nList.item(temp);
//
//	         if (node.getNodeValue() == "dia:attribute"){
//	        	 //System.out.println(node.getNodeValue());
//	        	 
//	         }
	   
		   
	   
	   
	   
	   
	   
//      for (int temp = 0; temp < nList.getLength(); temp++)
//      {
//         Node node = nList.item(temp);
//         if (node.getNodeType() == Node.ELEMENT_NODE)
//         {
//            System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
//            //Check all attributes
//            if (node.hasAttributes()) {
//               // get attributes names and values
//               NamedNodeMap nodeMap = node.getAttributes();
//               for (int i = 0; i < nodeMap.getLength(); i++)
//               {
//                   Node tempNode = nodeMap.item(i);
//                   System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
//               }
//               if (node.hasChildNodes()) {
//                  //We got more childs; Let's visit them as well
//                  visitChildNodes(node.getChildNodes());
//               }
//           }
//         }
//      }
 // }
}
//}