import java.util.ArrayList;

/**
 * 
 */

/**
 * @author TekNepal
 *
 */
public class XmiTable {
	
	ArrayList<XmiField> arrField = new ArrayList<XmiField>();
	String tableName = null;
	
	public XmiTable(String tableName_, ArrayList arrField_){
		
		tableName = tableName_;
		arrField = arrField_;
		
	}

	//add getter and setters for field array here
	
	

	public String getTableName() {
		return tableName;
	}

	public ArrayList<XmiField> getArrField() {
		return arrField;
	}

	public void setArrField(ArrayList<XmiField> arrField) {
		this.arrField = arrField;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
