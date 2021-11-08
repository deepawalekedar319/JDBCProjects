package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NthHighestSalary {
	
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//Reading the nth employee
			scan = new Scanner(System.in);
			int nth = 0;
			if(scan!=null) {
				System.out.println("Enter the Nth Salary Employee");
				nth = scan.nextInt();
				nth = nth-1;
			} // if
			//load JDBC driver  class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe","tiger");
			
			//create statement object
			if(con!=null)
				st = con.createStatement();
		
			//prepare SQL query
			//SELECT * FROM EMP E1 WHERE 1=(SELECT COUNT(DISTINCT SAL) FROM EMP E2	WHERE E2.SAL>E1.SAL);
			String query = "SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP E1 WHERE " + nth + "=(SELECT COUNT(DISTINCT SAL) FROM EMP E2	WHERE E2.SAL>E1.SAL)";
			//create ResultSet object
			if(st!=null)
				rs = st.executeQuery(query);
		
			//process the result
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1)+"		" + rs.getString(2) + "		" + rs.getFloat(3) + "		" + rs.getInt(4));
				} // while
				if(!flag) System.out.println("no records selected"); 
			} // if			
		} //try
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} //catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			//close all JDBC and stream objects
			try {
				if(rs!=null)
					rs.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();	
			} //catch
			try {
				if(st!=null)
					st.close();
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
