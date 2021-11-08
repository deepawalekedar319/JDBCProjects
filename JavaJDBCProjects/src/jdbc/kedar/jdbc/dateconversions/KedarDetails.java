package jdbc.kedar.jdbc.dateconversions;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.Console;

public class KedarDetails {

	public static void main(String[] args) {
		Console console = null;
		Connection con = null;
		//PreparedStatement ps = null;
		try {
			console = System.console();
			String userName = null;
			String userPassword = null;
			if(console!=null) {
				System.out.print("Enter user name : ");
				userName = console.readLine();
				System.out.print("Enter password : ");
				userPassword = new String(console.readPassword());
			} // if
			// Load JDBc driver class
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userName,userPassword);
			if(con!=null) {
				System.out.println("Welcome.... Kedar Singh");
				System.out.println("Enter the details number to be fetched....");
				System.out.println("1.Personal details. \n2.College details.\n3.Exit.");
				// create PreparedStatement Object
							
			} // if
			else System.out.println("Not connected ");			
		} // try
		catch (SQLException se) {
			// handle known exception
			if(se.getErrorCode() == 1017) System.out.println("Wrong username or password plase try again...");
			System.out.println("Unable to login...");
		} // catch
		catch (Exception e) {
			//handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			// Close all JDBC and Scanner object
			try {
				if(con!=null)
					con.close();
			} // try
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
			catch (Exception e) {
				// handle exception
				e.printStackTrace();
			} // catch
		} // finally
	} // main
} // class
