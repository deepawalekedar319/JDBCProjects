//GetLowestSalaryEmployee
package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* JDBC program to get the lowest salary employee from employee database table*/
public class GetLowestSalaryEmployee {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//Load JDBC driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Creating connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//creating statement object
			if(con!=null)
				st = con.createStatement();
			
			//prepare sql query
			//SELECT EMPNO,ENAME,ESAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)
			String query = "SELECT EMPNO,ENAME,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			
			// create ResultSet object
			if(st!=null)
				rs = st.executeQuery(query);
			
			 //process the query
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"   "+ rs.getString(2)+"   "+rs.getFloat(3)+"   ");
				} // while 
			} // if
		} // try 
		catch (SQLException se) {
			// handling known  exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			//  handle unknown exception
			e.printStackTrace();
		} // catch
		finally {
			//closing jdbc objects 
			try {
				if(rs!=null)
					rs.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}// catch
			try {
				if(st!=null)
					st.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}// catch
			try {
				if(con!=null)
					con.close();
			} //try
			catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}// catch
		} // finally
	} // main
} // class
