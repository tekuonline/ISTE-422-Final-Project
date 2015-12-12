import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;

import jdk.internal.org.xml.sax.SAXException;

public class DiaParser {
	public String sqlSmt;
	Document document = null;
	DocumentBuilder builder = null;
	
	public DiaParser() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
	
	public  void parse(File f) throws ParserConfigurationException, SAXException, IOException {
	
		
		try {
			document = builder.parse(f);
		} catch (org.xml.sax.SAXException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		// Element root = document.getDocumentElement();
		// System.out.println(root.getNodeName());
		
		
		ArrayList<XmiTable> tableArr = new ArrayList<XmiTable>();
		ArrayList<XmiField> fieldArr = null;
		
		int ctr = 0;
		NodeList diaObject = document.getElementsByTagName("dia:object");
		//System.out.println(diaObject.getLength());
		for (int i = 0; i < diaObject.getLength(); i++) {

			if (diaObject.item(i).getNodeType() == Node.ELEMENT_NODE) {

				// Looking for database-table
				String s = (((Element) (diaObject.item(i))).getAttributes().getNamedItem("type").getNodeValue());

				if ((s.equalsIgnoreCase("Database - Table"))) {

					// NodeList nl = (diaObject.item(i)).getChildNodes();
					Element node = (Element) diaObject.item(i);

					// diaattribute is the first child
					Node firstChild = node.getFirstChild();
					String tablename = null;
					
					// read through all diaattribute until no more
					while (firstChild != null) {

						// if dia:attribute has an attribute called name with
						// value name
						// System.out.println(firstChild.getAttributes().getNamedItem("name").getTextContent());
						if (firstChild.getNodeType() == Node.ELEMENT_NODE && ((Element) firstChild).hasAttribute("name")
								&& ((Element) firstChild).getAttribute("name").equals("name")) {
							String name = null;
							
							// get all elements of diastring within
							// dia:attribute with name= name
							NodeList stringChildren = ((Element) firstChild).getElementsByTagName("dia:string");
							fieldArr = new ArrayList<XmiField>();
							for (int x = 0; x < stringChildren.getLength(); x++) {
								name = stringChildren.item(x).getTextContent();
								String[] a = name.split("#");
								
								tablename = a[1];
								
							}

						}
						// if diaattribute has a attribute 'name' with
						// attributes
						// attributes
						else if (firstChild.getNodeType() == Node.ELEMENT_NODE
								&& ((Element) firstChild).hasAttribute("name")
								&& ((Element) firstChild).getAttribute("name").equals("attributes")) {

							// System.out.println("diaAttributes with
							// name='attributes' = " + ++ctr );
							// System.out.println(firstChild.getTextContent());

							NodeList diaComposite = ((Element) firstChild).getElementsByTagName("dia:composite");
							for (int x = 0; x < diaComposite.getLength(); x++) {

								// System.out.println(diaComposite.item(x).getTextContent());
								NodeList diaAttribute = (((Element) diaComposite.item(x))
										.getElementsByTagName("dia:attribute"));

								String fname = null;
								String fieldname = null;

								String dtype = null;
								String dataType = null;
								
								for (int z = 0; z < diaAttribute.getLength(); z++) {
									boolean found = false;
									if (((Element) diaAttribute.item(z)).getAttribute("name").equals("name")) {
										fname = diaAttribute.item(z).getTextContent();
										String[] name = fname.split("#");
										fieldname = name[1];
										//System.out.println("Field Name: " + fieldname);
										
										
									} else if (((Element) diaAttribute.item(z)).getAttribute("name").equals("type")) {
										dtype = diaAttribute.item(z).getTextContent();
										String[] type = dtype.split("#");
										dataType = type[1];
										//System.out.println("DataType: " + dataType);
										found = true; 
										//create field objects and store in arrayList fieldArr
										
									}
									
									if (found){
										fieldArr.add(new XmiField(fieldname, dataType));
									}
									
									
								}

							}
							tableArr.add(new XmiTable(tablename, fieldArr));
							//fieldArr.clear();
						}
						
						//tableArr.add(new XmiTable(tablename, fieldArr));

						// else{
						firstChild = firstChild.getNextSibling();
						// }

					} // end while

				} // end if
			} // end if
		} // end for
		MySQL mysql = new MySQL(tableArr);
		sqlSmt  = mysql.getSQLString();
		
		System.out.println("PRINTING FROM TABLE OBJECT");
		for (XmiTable x : tableArr) {
			
			System.out.println("\n");

			System.out.println(x.getTableName());
			ArrayList<XmiField> arrfieldx = x.getArrField();
			
			//System.out.println(("Fields in the table: " + arrfieldx.size()));
			
			for (int i = 0; i <= (arrfieldx.size() - 1); i++) {
				System.out.print((arrfieldx.get(i)).getColumnName() + " ");
				System.out.print((arrfieldx.get(i)).getDatatype() + " ");
				//System.out.print((arrfieldx.get(i)).getDataTypeLength() + " ");
				System.out.println();
			}
		}
		
	}// end parsemethod
	
	@Override
	public String toString(){
		return sqlSmt;
		
	}

}// end class
