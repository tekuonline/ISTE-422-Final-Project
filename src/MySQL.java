import java.io.*;
import java.util.*;

public class MySQL {

	public final String CT = "CREATE TABLE ";
	ArrayList<XmiTable> list = null;
	StringBuffer sb;

	public MySQL(ArrayList<XmiTable> list) {
		this.list = list;
		try {
			sb = new StringBuffer();

			int i = 0;

			do {
				System.out.println("i=" + i);

				sb.append(CT + list.get(i).getTableName()+ ";\r\n");
				sb.append("("+ ";\r\n");
				int j = 0;

				do {
					System.out.println("j=" + j);

					sb.append((list.get(i)).getArrField().get(j).getColumnName() + " "
							+ (list.get(i)).getArrField().get(j).getDatatype() + ","+ ";\r\n");
					
					j++;
				} while (j < list.get(i).arrField.size());
				i++;

			} while (i < list.size());
			///System.out.println("made it");
			sb.append(");");
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public String getSQLString() {
	      return sb.toString();
	   }	
}

