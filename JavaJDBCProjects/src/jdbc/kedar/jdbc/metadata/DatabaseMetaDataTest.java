package jdbc.kedar.jdbc.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaDataTest {

	public static void main(String[] args) {
		try ( // Creating connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger"); ){
				// Creating DatabaseMetaDataObject 
				DatabaseMetaData dmd = con.getMetaData(); 
				if(dmd!=null) {
					System.out.println("Database Software name : " + dmd.getDatabaseProductName());
					System.out.println("Database Version : " + dmd.getDatabaseProductVersion());
					System.out.println("JDBC Driver name : " + dmd.getDriverName());
					System.out.println("JDBC Driver version : " + dmd.getDriverVersion());
					System.out.println("SQL Keywords  : " + dmd.getSQLKeywords());
				} // if
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
