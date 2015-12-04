import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class test {
   public static void main(String[] args){
      try {
         DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = dBuilder.parse(new File("teacher"));
         NodeList nl=doc.getDocumentElement().getChildNodes();

         for(int k=0;k<nl.getLength();k++){
             printTags((Node)nl.item(k));
         }
      } catch (Exception e) {/*err handling*/}
   }

   public static void printTags(Node nodes){
       if(nodes.hasChildNodes()  || nodes.getNodeType()!=3){
           System.out.println(nodes.getNodeName()+" : "+nodes.getTextContent());
           NodeList nl=nodes.getChildNodes();
           for(int j=0;j<nl.getLength();j++)printTags(nl.item(j));
       }
   }
}