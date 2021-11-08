package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NHighestOrLowestEmp {
	private static final String HIGHEST_EMP_QUERY = "SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP E1 WHERE ?>(SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL>E1.SAL) ORDER BY SAL DESC";
	private static final String LOWEST_EMP_QUERY = "SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP E1 WHERE ?>(SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL<E1.SAL) ORDER BY SAL DESC";
	public static void main(String[] args) {
		Scanner scan = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			scan = new Scanner(System.in);
			int userOption = 0;
			if(scan!=null) {
				System.out.println("Enter you option :::: \n1. To get Top n highest salary employees \n2. To get Top n lowest salary employees");
				userOption = scan.nextInt();
			} // if
			//Load JDBc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//Performing operations based on user option
			if(con!=null && scan!=null) {
				switch(userOption) {
				case 1:
					System.out.println("Enter the Top highest emp list ::");
					int highList = scan.nextInt();
					ps = 	con.prepareStatement(HIGHEST_EMP_QUERY);
					ps.setInt(1, highList);
					break;
				case 2:
					System.out.println("Enter the Top lowest emp list ::");
					int lowList = scan.nextInt();
					ps = con.prepareStatement(LOWEST_EMP_QUERY);
					ps.setInt(1, lowList);
					break;
				default:
					System.out.println("Incorrect option...");
				} //switch
			} // if			
			
			// prepare ResultSet object 
			if(ps!=null)
				rs = ps.executeQuery();
			
			//Process the result 
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1)+"		" + rs.getString(2) + "		" + rs.getFloat(3) + "		" + rs.getInt(4));
				} //while
				if(!flag) System.out.println("Error while Retrieving the records...");
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
			// Close all JDBC and Scanner Object
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
				//  handle exception
				e.printStackTrace();
			} //catch
		} // finally
	} // main
} //class
