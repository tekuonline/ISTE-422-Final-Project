import java.io.*;
import java.util.*;

public class mySQL {

	public final String CT = "CREATE TABLE ";
	ArrayList<XmiTable> list = null;

	public mySQL(ArrayList<XmiTable> list) {
		this.list = list;
		try {
			System.out.println("working");
			BufferedWriter writer = new BufferedWriter(new FileWriter("sqltext.txt"));

			int i = 0;

			do {
				System.out.println("i=" + i);

				writer.write(CT + list.get(i).getTableName());
				writer.newLine();
				writer.write("(");
				writer.newLine();
				writer.flush();
				int j = 0;

				do {
					System.out.println("j=" + j);

					writer.write((list.get(i)).getArrField().get(j).getColumnName() + " "
							+ (list.get(i)).getArrField().get(j).getDatatype() + ",");
					writer.newLine();
					writer.flush();
					j++;
				} while (j < list.get(i).arrField.size());
				i++;

			} while (i < list.size());
			System.out.println("made it");
			writer.write(");");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("error");
		}
	}
}
