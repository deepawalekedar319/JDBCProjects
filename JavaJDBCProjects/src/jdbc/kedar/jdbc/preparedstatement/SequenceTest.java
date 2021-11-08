package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SequenceTest {
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(STUDENT_NUMBER.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//Creating Scanner Object
			scan = new Scanner(System. in);
			//Reading user inputs
			int count = 0;
			if(scan!=null) {
				System.out.println("Enter the number students to be inserted :");
				count = scan.nextInt();
			} // if
			
			//Load JDBC Driver clas
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//	Create PreparedStatement Object
			if(con!=null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			
			//Take the each record values fom the end user 
			if(scan!=null  && ps!=null) {
				for(int i=1;i<=count;i++) {
					System.out.println("Enter the "+ i + " student name : ");
					String studentName = scan.next();
					System.out.println("Enter the "+ i + " student address : ");
					String studentAddress = scan.next();
					System.out.println("Enter the "+ i + " student Average : ");
					float studentAverage = scan.nextFloat();
					
					//Setting each values to the pre-compiled query
					ps.setString(1, studentName);
					ps.setString(2, studentAddress);
					ps.setFloat(3, studentAverage);
					
					//Execute the pre-compiled query for each record insertion
					int result = ps.executeUpdate();
					 
					//process the result
					if(result==0)System.out.println(i + " Record not insreted..Some problem has raised... ");
					else System.out.println(i + "  Record inserted successfully...");
				} // for
			} // if
		} // try
		catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} //catch
		finally {
			// Closing all JDBC and Stream objects
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
	} // main
} // class
