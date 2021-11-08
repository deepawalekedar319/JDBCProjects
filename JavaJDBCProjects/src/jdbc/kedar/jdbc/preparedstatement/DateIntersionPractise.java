package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateIntersionPractise {
	private static final String INSERT_DATE_QUERY = "INSERT INTO TEST_DATE VALUES(?,?)";
	public static void main(String[] args) {
		String birthDate = null, startDate = null;
		try (Scanner scan = new Scanner(System.in)){
			System.out.println("Enter the Date of Birth...");
			birthDate = scan.next();
			System.out.println("Enter the Date of Joining...");
			startDate = scan.next();		
			
			// Converting date values into DateBase understandable form
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date utilBirthDate = sdf.parse(birthDate);
			java.sql.Date sqlBirthDate = new java.sql.Date(utilBirthDate.getTime());
			// Converting StartDate into Oracle Understand able form
			java.util.Date utilStartDate = sdf.parse(startDate);
			java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
			
			// Connectivity to Data Base
			try (
					// Establishing Connection Object
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// Preparing PreparedStatement Object
					PreparedStatement ps = con.prepareStatement(INSERT_DATE_QUERY);	){
				// Setting Values to the Date base
				if(ps!=null) {
					ps.setDate(1, sqlBirthDate);
					ps.setDate(2, sqlStartDate);
				} // if
				// Executing the Query
				int count = 0;
				if(ps!=null) {
					count = ps.executeUpdate();
					if(count==0)
						System.out.println("Error While insterting the record...");
					else 
						System.out.println("Record Interted Succssfully...");
				} // if					
			} // try - 2 
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
			catch(Exception e) {
				// handle Exception
				e.printStackTrace();
			} // catch
		} // try
		catch (Exception e) {
			// handle exception
			e.printStackTrace(); 
		} // catch
	} // main
} // class
