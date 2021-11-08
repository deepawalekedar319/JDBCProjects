package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NthLowestSalary {
	private static final String LOWEST_EMP_QUERY = "SELECT EMPNO,ENAME,SAL,DEPTNO  FROM EMP E1 WHERE ?=(SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL<E1.SAL)";
	public static void main(String[] args) {
		System.out.println("NthLowestSalary.main()");
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			scan = new Scanner(System.in);
			int lowestSalary = 0;
			//Reading user inputs 
			if(scan!=null) {
				System.out.println("Enter the nth lowest salary employee : ");
				lowestSalary = scan.nextInt();
				lowestSalary = lowestSalary -1;
			} // if
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "tiger");
			
			//create PreparedStatement Object
			if(con!=null)
				ps = con.prepareStatement(LOWEST_EMP_QUERY);
			//setting values to the query [pre-compiled query]
			ps.setInt(1, lowestSalary);
			
			//create ResultSet object
			if(ps!=null)
				rs = ps.executeQuery();
			
			//process the query
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1)+"		" + rs.getString(2) + "		" + rs.getFloat(3) + "		" + rs.getInt(4));
				} //while
				if(!flag) System.out.println(" No records found...");
			} // if
		} //try
		catch (SQLException se) {
			// TODO: handle known exception
			se.printStackTrace();
		} //catch
		catch (Exception e) {
			// TODO: handle unknown exception
			e.printStackTrace();
		} //catch
		finally {
			//close all JDBC and Stream objects
			try {
				if(rs!=null)
					rs.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			} //catch
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
