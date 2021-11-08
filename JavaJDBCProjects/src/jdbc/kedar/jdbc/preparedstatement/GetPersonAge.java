package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GetPersonAge {

	private static final String GET_PERSON_AGE = "SELECT PID,NAME ,TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(DOB,'YYYY') AGE ,DOB,DOJ,DOM FROM PERSON_DETAILS WHERE PID=?";	
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			scan = new Scanner(System.in);
			int personId = 0;
			System.out.println("Find your age just by entering your I'd number...");
			if(scan!=null) {
				System.out.println("Enter the person Id : ");
				personId = scan.nextInt();
			} // if
			
			//Load JDBC driver class 
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// create PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(GET_PERSON_AGE);
			
			// Set the values to query and execute 
			if(ps!=null)
				ps.setInt(1, personId);
			
			// Send and execute the query
			if(ps!=null) 
				rs = ps.executeQuery();
			
			// Process the query
			if(rs!=null) {
				//rs.next(); // makes the rs to come in first record
				boolean flag = false;
				if(rs.next()) {
					flag = true;
					//System.out.println(rs.getString(1) + "        " + rs.getString(2));
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String age = rs.getString(3);
					java.sql.Date sqlDob = rs.getDate(4);
					java.sql.Date sqlDoj = rs.getDate(5);
					java.sql.Date sqlDom = rs.getDate(6);
					
					// Converting SQL Dates into String value date
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String stringDob = sdf.format(sqlDob);
					String stringDoj = sdf.format(sqlDoj);
					String stringDom = sdf.format(sqlDom);
					
					System.out.println(id + "     " + name + "     " + age + "     " + stringDob + "     " + stringDoj + "     " + stringDom);
				} // if
				if(!flag) System.out.println("Please enter the correct I'd number...");
			} // if
		} // try
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			// close JDBC and Scanner objects
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
			try {
				if(scan!=null)
					scan.close();
			} // try
			catch (Exception e) {
				// handle exception
				e.printStackTrace();
			} // catch
		} // finally
	} // main
} // class
