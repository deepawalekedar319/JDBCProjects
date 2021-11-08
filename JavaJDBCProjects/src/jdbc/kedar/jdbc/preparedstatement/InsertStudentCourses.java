package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertStudentCourses {
	private static final String STUDENT_INSERT_QUERY= "INSERT INTO STUDENT_COURSES VALUES(STUDENT_NUMBER.NEXTVAL,?)";
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			scan = new Scanner(System.in);
			int numberOfRecords = 0;
			//Reading user inputs
			if(scan!=null) {
				System.out.println("Enetr the number of student records you want to insert...");
				numberOfRecords = scan.nextInt();
			} // if
			
			//Load JDBC class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish Connection
			con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			// Prepare PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			
			//Read each student record
			if(scan!=null &&ps!=null){
				for(int i=1;i<=numberOfRecords;i++) {
					System.out.println("Enter the " + i + " student student name ");
					String studentName = scan.next();
					
					//Set values to query 
					ps.setString(1, studentName);
					
					//execute the pre-compiled  query
					int result = ps.executeUpdate();
					
					//process the Result
					if(result==0) System.out.println("Error while inserting the record...");
					else System.out.println( i + " record inserted successfully...");
				} // for
			} // if
		} // try 
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// TODO: handle unknown  exception
			e.printStackTrace();
		} //catch
		finally {
			// close all JDBc and Scanner objects
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
		} //finally
	} // main
} //class
