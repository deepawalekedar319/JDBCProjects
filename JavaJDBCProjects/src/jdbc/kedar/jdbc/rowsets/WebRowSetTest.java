package jdbc.kedar.jdbc.rowsets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetTest {

	public static void main(String[] args) {
		try (OracleWebRowSet web = new OracleWebRowSet();){
			web.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			web.setUsername("xe");
			web.setPassword("tiger");
			web.setCommand("SELECT * FROM STUDENT");
			web.execute();
			while(web.next())
				System.out.println(web.getInt(1));
			// Writing DB records as XML file
			try (OutputStream os = new FileOutputStream("student.xml")){
				web.writeXml(os);
				System.out.println("Writing XML Content on Console");
				web.writeXml(System.out);
			} // try
			catch (FileNotFoundException fns) {
				// handle exception
				fns.printStackTrace();
			} // catch
		} // try
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
