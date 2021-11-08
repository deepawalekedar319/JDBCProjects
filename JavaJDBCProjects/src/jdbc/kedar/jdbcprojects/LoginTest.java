package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginTest {

	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		Statement st =null;
		ResultSet rs = null;
		try {
			//reading values from end user
			scan = new Scanner(System.in);
			String user = null,pass=null;
			if(scan!=null){
				System.out.println("Enter username : ");
				user = scan.next();
				System.out.println("Enter password : ");
				pass = scan.next();	
			} // if
			
			// converting the inputs into sql required form
			user = "'" + user + "'";
			pass = "'" + pass + "'";
			//load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// prepare statement object
			if(con!=null)
				st = con.createStatement();
			
			//Prepare sql query
			//SELECT COUNT(*) FROM USER_CREDENTIALS WHERE UNAME='kedar' AND UPASS='kedarsingh';
			String query = "SELECT COUNT(*) FROM USER_CREDENTIALS WHERE UNAME=" + user + " AND UPASS=" + pass;
			System.out.println(query);
			
			// send and execute the SQL query
			if(st!=null) 
				rs =  st.executeQuery(query);
			
			//process the resultSet
			int count = 0;
			if(rs!=null) {
				rs.next();
				count = rs.getInt(1);
				if(count==0) System.out.println("Invalid credentials");
				else System.out.println("Valid credentials");
			} // if
		} //try
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} // catch
		catch(Exception  e) {
			e.printStackTrace();
		} //catch
		finally {
			//close all jdbc and scanner object
			try {
				if(rs!=null)
					rs.close();
			} // try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(st!=null)
					st.close();
			} // try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(con!=null)
					con.close();
			} // try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(scan!=null)
					scan.close();
			} // try
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} //catch
		} //finally
	} //main
} //class
