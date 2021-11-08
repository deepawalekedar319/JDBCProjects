package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class IntertIntoPersonDetails {

	private static final String INSERT_PERSON_DETAILS_QUERY = "INSERT INTO PERSON_DETAILS VALUES(PERSON_ID.NEXTVAL,?,?,?,?)";	
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			scan = new Scanner(System.in);
			// Reading user inputs
			int numberOfInsert = 0;
			if(scan!=null) {
				System.out.println("Enter the number of records to be inserted...");
				numberOfInsert = scan.nextInt();
			} // if
			
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish connection
			con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//create PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(INSERT_PERSON_DETAILS_QUERY);
			
			//Read each user details from end user
			if(scan!=null && ps!=null) {
				System.out.println("Enter Details...");
				for(int i=1;i<=numberOfInsert;i++) {
					System.out.println("Enter person name : ");
					String name = scan.next();
					System.out.println("Enter Date of Birth (dd-MM-yyyy) : ");
					String dob = scan.next();
					System.out.println("Enter Date of Joining (yyyy-MM-dd) : ");
					String doj = scan.next();
					System.out.println("Enter Date of Marraige (MMM-dd-yyyy) : ");
					String dom = scan.next();
					
					// Converting the string date values to jva.util.Dates values
					// Converting for dob(dd-MM-yyyy) 
					// Converting String to java.util.Date class object
					SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date utilDob = sfd.parse(dob);
					// Converting java.util.Date to java.sql.Date class object
					long milliSeconds = utilDob.getTime();
					java.sql.Date sqlDob = new java.sql.Date(milliSeconds);
					
					// Converting for doj(yyyy-MM-dd)
					// Converting String date value to java.util.Date class obj 
					java.sql.Date sqlDoj =  java.sql.Date.valueOf(doj);
					
					// Converting for dom(MMM-dd-yyyy)
					// Converting String date value to java.util.date class obj
					SimpleDateFormat sfd2 = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date utilDom = sfd2.parse(dom);
					// Converting java.util.Date to java.sql.Date class object
					milliSeconds = utilDom.getTime();
					java.sql.Date sqlDom = new java.sql.Date(milliSeconds);
					
					// Set values to the query
					ps.setString(1, name); 
					ps.setDate(2, sqlDob);
					ps.setDate(3, sqlDoj);
					ps.setDate(4, sqlDom);
					
					// Execute the query 
					int result = ps.executeUpdate();
					
					//Process the query
					if(result==0) System.out.println("Error while inserting the record...");
					else System.out.println("Record Inserted Successfully...");
				} // for
			} // if
		} // try
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} //  catch
		finally {
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


/*java.text.ParseException: Unparseable date: "11-09-1999"
	at java.base/java.text.DateFormat.parse(DateFormat.java:396)
	at jdbc.kedar.jdbc.preparedstatement.IntertIntoPersonDetails.main(IntertIntoPersonDetails.java:65)*/
