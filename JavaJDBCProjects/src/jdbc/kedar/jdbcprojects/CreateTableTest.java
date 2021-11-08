package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			// load JDBC class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// create statement object
			if(con!=null)
				st = con.createStatement();
			
			// prepare sql query
			//CREATE TABLE CUSTOMER (CID NUMBER(5),CNAME VARCHAR2(20), CADDR VARCHAR2(20));
			String query = "CREATE TABLE CUSTOMER (CID NUMBER(5),CNAME VARCHAR2(20), CADDR VARCHAR2(20))";
			
			// send and execute sql query to database
			int count = 0 ;
			if(st!=null)
				count = st.executeUpdate(query);
			//process the result
			if(count==0) System.out.println("Table created ");
			else System.out.println("Table not created");
		} // try
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} // catch
		catch(Exception e) {
			e.printStackTrace();
		} // catch
		finally {
			//close all jdbc objects
			try {
				if(st!=null)
					st.close();
			} //try
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} //try
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			catch (Exception e) {
				// TODO: handle unknown  exception
				e.printStackTrace();
			} // catch
		} // finally
	} //main
} //class
