package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* Java program which demonstrate all the basic crud operations*/

public class BasicCRUDOperations {
	public static void main(String[] args) {
		Connection con = null;	
		Scanner scan = null;
		Statement st = null;
		ResultSet rs = null; 
		try {
				System.out.println("Enter your operations : ");
				System.out.println("1 . Get Employee table records ");
				System.out.println("2 . Add Employee record");
				System.out.println("3 . Update Employee record");
				System.out.println("4 . Delete Employee record");
				scan = new Scanner(System.in);
				int choise = 0;
				if(scan!=null) {
					choise = scan.nextInt();
				} // if 
			 // Load jdbc class
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establish Connection
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				
				// create statement object
				if(con!=null)
					st = con.createStatement();
				
				// performing operations based on user needs
				switch(choise) {
				case 1:
					// prepare query
					// SELECT SNO,SNAME,SADDR,SAVG FROM STUDENT;
					String query = " SELECT SNO,SNAME,SADDR,SAVG FROM STUDENT";
					
					// send and execute query
					if(st!=null)
						rs = st.executeQuery(query);
					//process ResultSet object
					if(rs!=null) {
						boolean flag = false;
						while(rs.next()) {
							flag = true;
							System.out.println(rs.getInt(1)+ "    "+rs.getString(2) + "    " + rs.getString(3) + "    "+rs.getFloat(4));
						} // while 
						if(flag==false) System.out.println("No records");
					} // if
					break;
				
				// insert operation	
				case 2:
					int studentNumber = 0;
					String studentName = null, studentAddress = null;
					float studentAverage = 0.0f;
					if(scan!=null) {
						System.out.println("Enter student Number : ");
						studentNumber = scan.nextInt();
						System.out.println("Enter student Name : ");
						studentName = scan.next();
						System.out.println("Enter student Address : ");
						studentAddress = scan.next();
						System.out.println("Enter student Average : ");
						studentAverage = scan.nextFloat();
					} //if
					
					// converting into sql query
					studentName = "'"+studentName+"'";
					studentAddress = "'"+studentAddress+"'";
					
					//prepare sql query
					//INSERT INTO STUDENT VALUES(4,'KEDAR','HYD',99.8);
					String insertQuery = "INSERT INTO STUDENT VALUES(" + studentNumber + "," + studentName + "," + studentAddress + "," + studentAverage + ")";
					
					// send and execute query
					int count = 0;
					if(st!=null)
						count = st.executeUpdate(insertQuery);
					if(count==0)
						System.out.println("Record not inserted");
					else System.out.println("Record successfully inserted");
					break;
				
				// update operation	
				case 3:
					System.out.println("This operation is in process... please wait...");
					break;
				// delete operation
				case 4:
					int studentToBeDeleted = 0;
					if(scan!=null) {
						System.out.println("Enter the student number to be deleted : ");
						studentToBeDeleted = scan.nextInt();
					} // if
					
					//prepare sql query
					// DELETE FROM STUDENT WHERE SNO = 1;
					String deleteQuery = "DELETE FROM STUDENT WHERE SNO =" + studentToBeDeleted;
					//send and execute query
					int deleteCount = 0;
					if(st!=null)
						deleteCount = st.executeUpdate(deleteQuery);
					if(deleteCount == 0) System.out.println("No records Deleted... plese check the student number ");
					else System.out.println(deleteCount + " record deleted");
					
					break;
				
					default : 
						System.out.println("Please enter valid option");
			} // switch				
		} // try
		catch (SQLException se) {
			// TODO: handle known exceptions
			if(se.getErrorCode()==1) System.out.println("This record already exist... Please vertify the student number ");
			else if(se.getErrorCode()>=900 && se.getErrorCode()<=999) System.out.println("Please check the column name or address");
			else if(se.getErrorCode()==12899) System.out.println("You are crossing the maximun limit length of the StudentName or sudentAddress");
			System.out.println("Problem raised whlile insertion... ");
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			// closing JDBC and stream objects
			try {
				if(rs!=null)
					rs.close();					
			} // try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} // catch
			try {
				if(st!=null)
					st.close();					
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
} // class
