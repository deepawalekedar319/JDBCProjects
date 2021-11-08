package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class RetrivePersonDetails {

	private static final String RETRIVE_PERSON_DETAILS_QUERY = "SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_DETAILS";
	
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// Load JDBC driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish Connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// Prepare PreparedStatement Object
			if(con!=null)
				ps =  con.prepareStatement(RETRIVE_PERSON_DETAILS_QUERY);
			
			// Execute query
			if(ps!=null)
				rs = ps.executeQuery();
			
			// Process the SQL query
			/*   Method - 1 
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1) + " 		" + rs.getString(2) + "  	 " + rs.getString(3) + "  	 " + rs.getString(4) + "  	 " + rs.getString(5));
				} // while
				if(!flag) System.out.println("No records found...");				
			} // if
			*/
			// Process the sql query  [ Method - 2]
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					int pid = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date sqlDob = rs.getDate(3);
					java.sql.Date sqlDoj = rs.getDate(4);
					java.sql.Date sqlDom = rs.getDate(5);
					
					// Converting java.sql Date to String object
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String stringDob = sdf.format(sqlDob);
					String stringDoj = sdf.format(sqlDoj);
					String stringDom = sdf.format(sqlDom);
					
					System.out.println(pid + "     " + name + "     " + stringDob + "     " + stringDoj + "     " + stringDom);
				} // while
				if(!flag) System.out.println("No records found...");
			} // if
		} // try 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
		finally {
			// closing all JDBC objects
			try {
				if(rs!=null)
					rs.close();
			} // try
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
			try {
				if(ps!=null)
					ps.close();
			} // try
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
		} //  finally
	} // main
} // class
