import java.util.ArrayList;

public class MySQL {

	public final String CT = "CREATE TABLE ";
	ArrayList<XmiTable> list = null;
	StringBuffer sb;

	public MySQL(ArrayList<XmiTable> list) {
		this.list = list;
		try {
			sb = new StringBuffer();
			sb.append("CREATE DATABASE " + "XMLDatabase" + ";\r\n");
			sb.append("USE " + "XMLDatabase" + ";\r\n");
			int i = 0;

			do {
				// System.out.println("i=" + i);

				sb.append("\n" + CT + list.get(i).getTableName());
				sb.append(" (" + "\r\n");
				int j = 0;

				do {
					// System.out.println("j=" + j);

					if (j == (list.get(i).arrField.size()) - 1) {
						sb.append((list.get(i)).getArrField().get(j).getColumnName() + " "
								+ (list.get(i)).getArrField().get(j).getDatatype() + " ("
								+ (list.get(i)).getArrField().get(j).getDataTypeLength() + ")" + "\r\n");
					} else {
						sb.append((list.get(i)).getArrField().get(j).getColumnName() + " "
								+ (list.get(i)).getArrField().get(j).getDatatype() + " ("
								+ (list.get(i)).getArrField().get(j).getDataTypeLength() + ")" + "," + "\r\n");
					}
					j++;
				} while (j < list.get(i).arrField.size());
				i++;
				sb.append(");");
			} while (i < list.size());
			/// System.out.println("made it");

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public String getSQLString() {
		return sb.toString();
	}
}
