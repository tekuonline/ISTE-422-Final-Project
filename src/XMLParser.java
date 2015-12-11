import java.io.*;

import javax.xml.namespace.QName;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.ArrayList;

public class XMLParser {

	// ArrayList<Student> studentArray = new ArrayList<Student>();
	private DocumentBuilder builder;
	private XPath path;
	private String qry;
	EdgeConvertGUI gui = new EdgeConvertGUI();

	/**
	 * Constructs a parser that can parse student lists.
	 */
	public XMLParser() throws ParserConfigurationException {
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		builder = dbfactory.newDocumentBuilder();
		XPathFactory xpfactory = XPathFactory.newInstance();
		path = xpfactory.newXPath();
	}

	/**
	 * Parses an XML file containing student data.
	 * 
	 * @param fileName
	 *            the name of the file
	 */
	public void parse(File f) throws SAXException, IOException, XPathExpressionException {

		ArrayList<XmiTable> arrTable = new ArrayList<XmiTable>();
		String tableName;
		String fieldName;
		String datatype;
		String dataTypeLength;

		//File f = new File(fileName);
		Document doc = builder.parse(f);

		// studentCount should be 3
		int classCount = Integer.parseInt(path.evaluate("count(/Project/Models/DBTable)", doc));
		// System.out.println(classCount);

		System.out.println("*** Model Listing ***");

		for (int i = 1; i <= classCount; i++) {

			ArrayList<XmiField> arrField = new ArrayList<XmiField>();

			tableName = (path.evaluate("//Project/Models/DBTable[" + i + "]/@Name", doc));
			int attrCount = Integer
					.parseInt(path.evaluate("count(/Project/Models/DBTable[" + i + "]/ModelChildren/DBColumn)", doc));
			// System.out.println(attrCount);
			for (int j = 1; j <= attrCount; j++) {
				fieldName = (path
						.evaluate("//Project/Models/DBTable[" + i + "]/ModelChildren/DBColumn[" + j + "]/@Name", doc));
				datatype = (path.evaluate("//Project/Models/DBTable[" + i + "]/ModelChildren/DBColumn[" + j + "]/@Type",
						doc));
				dataTypeLength = (path.evaluate(
						"//Project/Models/DBTable[" + i + "]/ModelChildren/DBColumn[" + j + "]/@Length", doc));

				XmiField fieldObj = new XmiField(fieldName, datatype, dataTypeLength);
				arrField.add(fieldObj);
			}
			XmiTable tableObj = new XmiTable(tableName, arrField);
			arrTable.add(tableObj);
		} // end outter for

		for (XmiTable x : arrTable) {

			System.out.println(x.getTableName());
			ArrayList<XmiField> arrfieldx = x.getArrField();

			for (int i = 0; i <= (arrfieldx.size() - 1); i++) {
				System.out.print((arrfieldx.get(i)).getColumnName() + " ");
				System.out.print((arrfieldx.get(i)).getDatatype() + " ");
				System.out.print((arrfieldx.get(i)).getDataTypeLength() + " ");
				System.out.println("");
			}
		}
		MySQL mysql = new MySQL(arrTable);

		// create field objects. send fieldname and datatype as arguments

		// add field objects to arraylist
		// //arrField.add(fieldObj);
		//
		// } // end of attrCount loop
		//
		// //create table objects. send table name, arraylist of field objects
		// as arguments
		// //XmiTable tableObj = new XmiTable(tableName, arrField);
		//
		// //add those table objects in an arraylist
		// //arrTable.add(tableObj);
		// }//end of class loop
		//
		// }
	}
} // end class StudentParser
