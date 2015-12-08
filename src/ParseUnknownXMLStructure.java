import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;

import jdk.internal.org.xml.sax.SAXException;

public class ParseUnknownXMLStructure {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = null;
		try {
			document = builder.parse(new File("teacher"));
		} catch (org.xml.sax.SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();
		// System.out.println(root.getNodeName());

		NodeList diaObject = document.getElementsByTagName("dia:object");

		for (int i = 0; i < diaObject.getLength(); i++) {

			if (diaObject.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String s = (((Element) (diaObject.item(i))).getAttributes().getNamedItem("type").getNodeValue());

				if ((s.equalsIgnoreCase("Database - Table"))) {

					// NodeList nl = (diaObject.item(i)).getChildNodes();
					Element node = (Element) diaObject.item(i);
					//NodeList nl = node.getElementsByTagName("dia:attribute");
					Node firstChild = node.getFirstChild();
					while (firstChild != null) {
						if (firstChild.getNodeType() == Node.ELEMENT_NODE && ((Element) firstChild).hasAttribute("name")
								&& ((Element) firstChild).getAttribute("name").equals("name")) {
							NodeList stringChildren = ((Element) firstChild).getElementsByTagName("dia:string");
							for (int x = 0; x < stringChildren.getLength(); x++) {
								System.out.print("i = " + i);
								System.out.print(", x = " + x);
								System.out.println(stringChildren.item(x).getTextContent());
							}
							break;
						} else {
							firstChild = firstChild.getNextSibling();
							
						}
						
						
					}
					while (firstChild != null) {
					if (firstChild.getNodeType() == Node.ELEMENT_NODE && ((Element) firstChild).hasAttribute("name")
							&& ((Element) firstChild).getAttribute("name").equals("attributes")) {
						NodeList stringChildren = ((Element) firstChild).getElementsByTagName("dia:string");
						
						
						for (int x = 0; x < stringChildren.getLength(); x++) {
							System.out.print("i = " + i);
							System.out.print(", x = " + x);
							System.out.println(stringChildren.item(x).getTextContent());
						}
						break;
					} else {
						firstChild = firstChild.getNextSibling();
					}
					}
					
					
				}
				
			}
			
		}
	}
	
}

