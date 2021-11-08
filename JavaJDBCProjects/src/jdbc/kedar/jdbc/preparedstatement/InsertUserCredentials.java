package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertUserCredentials {

	private static final String INSERT_QUERY = "INSERT INTO USER_CREDENTIALS VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			scan = new Scanner(System.in);
			int count = 0;
			// taking user inputs
			if(scan!=null) {
				System.out.println("Enter number of Users you want to add ");
				count = scan.nextInt();
			} // if 
			
			// load jdbc class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			 //establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			// create statement object
			if(con!=null)
				ps = con.prepareStatement(INSERT_QUERY);
			
			// read each user record from end user and insert in pre-compiled query
			if(ps!=null && scan!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter  " + i  + "  username");
					String user = scan.next();
					System.out.println("Enter " + i  + " password");
					String pass = scan.next();
					System.out.println("Enter " + i  + " location ");
					String loc = scan.next();
					
					// Setting each values
					ps.setString(1,user);ps.setString(2,pass);ps.setString(3,loc);
					// execute query each time
					int result = ps.executeUpdate();
					
					//process the query
					if(result==0) System.out.println("Problem while inserting the " + i + "record");
					else System.out.println(i+ "  Record inserted successfully");
				} // for
			} // if
		} // try
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			//closing all jdbc objects
			try {
				if(ps!=null)
					ps.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} // catch
			try {
				if(scan!=null)
					scan.close();
			} // try 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} // catch
		} // finally
	} // main
} // class
