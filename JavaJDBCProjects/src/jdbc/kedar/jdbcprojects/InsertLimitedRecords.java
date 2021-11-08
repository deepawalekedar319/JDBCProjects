package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* JDBc project that will insert limited data into table (eno,ename,job,sal)*/

public class InsertLimitedRecords {

	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		Statement st = null;
		try {
				scan =  new  Scanner(System.in);
				int employeeNumber = 0;
				String employeeName = null,employeeJob = null;
				Float employeeSalary = 0.0f;
				if(scan!=null) {
					System.out.println("Enter the Employee number : ");
					employeeNumber = scan.nextInt(); // Gives 3
					System.out.println("Enter the Employee name : ");
					employeeName = scan.next(); // Gives Sarwesh
					System.out.println("Enter the Employee job : ");
					employeeJob = scan.next(); // Gives Clerk
					System.out.println("Enter the Employee salary : ");
					employeeSalary = scan.nextFloat(); // Gives 5000
				} // if
				//converting into SQL understandable query
				employeeName = "'" + employeeName + "'"; // Gives 'sarwesh'
				employeeJob= "'" + employeeJob + "'"; // Gives 'clerk'
				//register JDBC class by loading JDBC driver class
				// Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//Establish Connection 
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe" ,"tiger");
				
				//create Statement object
				if(con!=null)
					st = con.createStatement();
				
				//prepare sql query
				//INSERT INTO MY_COMPANY (EMPNO,ENAME,JOB,SAL) VALUES(1,'ANAND SINGH','KING',100000);
				String query = "INSERT INTO MY_COMPANY (EMPNO,ENAME,JOB,SAL) VALUES(" + employeeNumber + "," + employeeName + "," + employeeJob + "," + employeeSalary + ")";
				System.out.println(query);
				
				//send and execute query
				int count = 0;
				if(st!=null)
					count = st.executeUpdate(query);
				
				//process result
				if(count == 0) System.out.println("Record not inserted");
				else System.out.println("Record inerted...");
				
		} // try
		catch (SQLException se) {
			// TODO: handle known exception
			if(se.getErrorCode()==1) System.out.println("This record already exist");
			else if(se.getErrorCode()==12899) System.out.println("value too large for column or job");
			else if(se.getErrorCode()>=900 && se.getErrorCode()<=999) System.out.println("Please check the column name or table name or SQL keyword");
			System.out.println("Problem while insertion record...");
		} // catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			// closing all JDBC and stream objects
			try {
				if(st!=null)
					st.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(scan!=null)
					scan.close();
			} // try 
			catch (Exception e) {
				// TODO: handle unknown exception
				e.printStackTrace();
			} // catch
		} // finally
	} //main
} //class
