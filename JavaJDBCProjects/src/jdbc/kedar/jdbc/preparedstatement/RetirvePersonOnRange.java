package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RetirvePersonOnRange {

	private static final String RETIRVE_RANGE_DATE_QUERY = "SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_DETAILS WHERE DOB IN(?,?)";
	
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			scan = new Scanner(System.in);
			// Reading user inputs 
			String startDate = null, endDate = null;
			if(scan!=null) {
				System.out.println("Enter start date (dd-MM-yyyy)");
				startDate = scan.next();
				System.out.println("Enter end date (dd-MM-yyyy)");
				endDate = scan.next();
			} // if
			// Converting string date values to java.util.Date class object
			// for startDate
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date utilStartDate = sdf.parse(startDate);
			// Converting util.Date class object to sql.Date class object
			long milliSeconds = utilStartDate.getTime();
			java.sql.Date sqlStartDate = new java.sql.Date(milliSeconds);
			
			// for endDate
			java.util.Date utilEndDate = sdf.parse(endDate);
			// Converting util.Date class object to sql.Date class object
			milliSeconds = utilEndDate.getTime();
			java.sql.Date sqlEndDate = new java.sql.Date(milliSeconds);
			
			// Load JDBC driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// Prepare PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(RETIRVE_RANGE_DATE_QUERY);
			
			// Setting values to query 
			if(ps!=null) {
				ps.setDate(1, sqlStartDate);
				ps.setDate(2, sqlEndDate);
			} // if
			
			// execute the query
			if(ps!=null)
				rs = ps.executeQuery();
			
	/*  Mathod - 1
			//process the result set
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1) + "     " + rs.getString(2) + "     " + rs.getString(3) + "     " + rs.getString(4) + "     " + rs.getString(5));
				} // while
				if(!flag) System.out.println("No records found...");
			} // if 
	*/
			// process the ResultSet 
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					int personId = rs.getInt(1);
					String name = rs.getString(2);
					java.util.Date utilDob = rs.getDate(3);
					java.util.Date utilDoj = rs.getDate(4);
					java.util.Date utilDom = rs.getDate(5);
					
					// Converting sql date to string date value
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
					String stringDob = sdf1.format(utilDob);
					String stringDoj = sdf1.format(utilDoj);
					String stringDom = sdf1.format(utilDom);
					
					System.out.println(personId + "     " + name + "     " + stringDob + "     " + stringDoj + "     " + stringDom );
				} // while
				if(!flag) System.out.println("No records found...");
			} // if
		} // try
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} //  catch
		catch (Exception e) {
			//  handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			// Closing all JDBc and Scanner objects
			try {
				if(rs!=null)
					rs.close();
			} // try
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(ps!=null)
					ps.close();
			} // try
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(scan!=null)
					scan.close();
			} // try
			catch (Exception e) {
				// handle known exception
				e.printStackTrace();
			} // catch
		} // finally
	} // main
} // class
