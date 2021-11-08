package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementLoginTest {
	private static final String 	LOGIN_VERIFY_QUERY = "SELECT COUNT(*) FROM USER_CREDENTIALS WHERE UNAME=? AND UPASS=?";
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			scan = new Scanner(System.in);
			
			// load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//create PrepareStatement object 
			if(con!=null)
				ps = con.prepareStatement(LOGIN_VERIFY_QUERY);
			
			//Read user inputs
			if(scan!=null && ps!=null) {
				System.out.println("Enter username");
				String user = scan.next();
				System.out.println("ener Password ");
				String pass = scan.next();
				
				// set params to the query
				ps.setString(1, user);
				ps.setString(2, pass);
				
				// prepare ResultSet Object
				rs = ps.executeQuery();
				int count = 0;
				if(rs!=null) {
					rs.next();
					count = rs.getInt(1);
					if(count==0) System.out.println("Invalid Credentials");
					else System.out.println("Valid credentials");
				} // if 
			} // if
		} //try
		catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		} //catch
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} //catch
		finally {
			//close all jdbc and scanner objects
			try {
				if(rs!=null) 
					rs.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(ps!=null) 
					ps.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(con!=null) 
					con.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
			try {
				if(scan!=null) 
					scan.close();
			} //try
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} //catch
		} //finally
	} //main
} //class
