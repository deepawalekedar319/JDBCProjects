//GetStudentRecords.java
package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* jdbc program to get all the students records */
public class GetStudentsRecords {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs =null;
		try {
			//Load the JDBC Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			 
			 //creating statement object
			 if(con!=null)
				 st = con.createStatement();
			 
			 //Preparing SQL query
			 //SELECT * FROM STUDENT
			 String query = "SELECT * FROM STUDENT";
			 
			 //create ResultSet object
			 if( st!=null)
				 rs = st.executeQuery(query);
			 // printing the result
			 if(rs!=null) {
				 System.out.println("Records are : ");
				 while(rs.next()) {
					 System.out.println(rs.getInt(1)+ "   " + rs.getString(2)+ "    " + rs.getString(3) + "    " + rs.getFloat(4));
				 } // while
			 } // if
		} // try
		catch(SQLException se) {
			se.printStackTrace();
		} // catch
		finally {
			// CLosing objects
			try {
				if(rs!=null)
					rs.close();
			} // try 
			catch (SQLException se) {
				se.printStackTrace();
			} // catch
			try {
				if(st!=null)
					st.close();
			} // try 
			catch (SQLException se) {
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try 
			catch (SQLException se) {
				se.printStackTrace();
			} // catch
		} // finally 
	} // main
} // class
