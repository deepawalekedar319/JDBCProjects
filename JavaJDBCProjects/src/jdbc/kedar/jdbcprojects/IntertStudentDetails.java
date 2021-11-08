package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* JDBC program to insert Data into Student Table*/
public class IntertStudentDetails {

	public static void main(String[] args) {
		Scanner scan = null;
		Connection con  = null;
		Statement st = null;
		try {
			scan = new Scanner(System.in);
			int studentNumber = 0;
			String studentName = null;
			String studentAddress = null;
			float studentAverage = 0.0f;
			if(scan!=null) {
				//taking user inputs
				System.out.println("Enter Student number ");
				studentNumber = scan.nextInt(); // gives 2
				System.out.println("Enter Student name ");
				studentName = scan.next(); // gives 'Kedar'
				System.out.println("Enter Student address ");
				studentAddress = scan.next(); //gives 'Hyd'
				System.out.println("Enter Student average ");
				studentAverage = scan.nextFloat(); // gives 99.90 
			} // if			
			//converting inputs into query format
			studentName = "'"+studentName+"'"; // gives 'kedar'
			studentAddress = "'"+studentAddress+"'"; // gives 'hyd'
			
			//load JDBC class 
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
								
			//create Statement object
			if(con!=null)
				st = con.createStatement();
					
			// prepare SQL query
			// insert into student values(2,'Sarwesh','sec',97.6);
			String query = "INSERT INTO STUDENT VALUES("+studentNumber+","+studentName+","+studentAddress+","+studentAverage+")";
			
			//send and execute the query
			int count=0;
			if(st!=null)
				count = st.executeUpdate(query);
			
			//process the result
			System.out.println(count + " Record inserted ");
			
		} // try
		catch (SQLException se) {
			//  handle known exception
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Please check the column name or table name or SQL keyword");
			else if(se.getErrorCode()==1) 
				System.out.println("This Employee is already present");
			else if(se.getErrorCode()==12899)
				System.out.println("You are crossing the maximun limit length of the StudentName or sudentAddress");
			
			System.out.println("Problem raised while inserting...");
		} // catch
		catch (Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} //catch
		finally {
			// closing Scanner and JDBC objects
			try {
				if(st!=null)
					st.close();
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
} //class
