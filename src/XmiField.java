/**
 * 
 */

/**
 * @author TekNepal
 *
 */
public class XmiField {

		String columnName = null;
		String datatype = null;
		int dataTypeLength = 0;
		
		
		public XmiField(String _column, String _datatype, String dataTypeLength){
			
			columnName = _column;
			datatype = _datatype;
			this.dataTypeLength = Integer.parseInt(dataTypeLength);
			
		}
		public XmiField(String _column, String _datatype){
			
			columnName = _column;
			datatype = _datatype;
			
		}

		public int getDataTypeLength() {
			return dataTypeLength;
		}

		public void setDataTypeLength(int dataTypeLength) {
			this.dataTypeLength = dataTypeLength;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getDatatype() {
			return datatype;
		}

		public void setDatatype(String datatype) {
			this.datatype = datatype;
		}
		
		
}
