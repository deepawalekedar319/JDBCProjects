package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestDateInsertion {
	private static final String INSERT_INTO_TESTDATE = "INSERT INTO TESTDATEINSERTION VALUES(?,?,?)";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			// Reading Inputs.
			String dob = null,doj = null, dol = null;
			if(scan!=null) {
				System.out.println("Enter Date Of Birth (dd-MM-yyyy) : ");
				dob = scan.next();
				System.out.println("Enter Date Of Joining (dd-MM-yyyy) : ");
				doj = scan.next();
				System.out.println("Enter Date Of Leaving (yyyy-MM-dd) : ");
				dol = scan.next();
			} // if
			/// Create Connection and PreparedStatement Object
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
					PreparedStatement ps = con.prepareStatement(INSERT_INTO_TESTDATE);){
				// Converting String to util Dates.
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date utilDob = sdf.parse(dob);
				// Converting util date to sql date
				long milliSeconds = utilDob.getTime();
				java.sql.Date sqlDate = new java.sql.Date(milliSeconds);
				
				// converting doj into java.util date
				java.util.Date utilDoj = sdf.parse(doj);
				milliSeconds = utilDoj.getTime();
				java.sql.Date sqlDoj = new java.sql.Date(milliSeconds);
				
				// Direct convertion 
				java.sql.Date sqlDol = java.sql.Date.valueOf(dol);
				
				// Setting the parameters
				ps.setDate(1 , sqlDate);
				ps.setDate(2, sqlDoj);
				ps.setDate(3, sqlDol);
				
				// Execute the query
				int count = ps.executeUpdate();
				if(count==0) System.out.println("Not inserted...");
				else System.out.println("Inserted...");
			} // try - 2
		} // try -1 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch(Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} // catch
	} // main
} // class
