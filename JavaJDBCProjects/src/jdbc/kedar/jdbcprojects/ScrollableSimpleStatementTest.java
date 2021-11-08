package jdbc.kedar.jdbcprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableSimpleStatementTest {
	private static final String SELECT_QUERY = "SELECT SNO,SNAME FROM STUDENT";
	public static void main(String[] args) {
		try( // Establishing Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				// Creating Simple Statement object
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				// Executing query
				ResultSet rs = st.executeQuery(SELECT_QUERY);){
			// Process the query
			if(rs!=null) {
				System.out.println("Top to Bottom");
				while(rs.next()) {
					System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
				} // while
				System.out.println("------------------------------------------------------------------------------------------------------------");
				rs.isAfterLast();
				System.out.println("Bottom to Top");
				while(rs.previous()) {
					System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
				} // while
				System.out.println("------------------------------------------------------------------------------------------------------------");
				rs.first();
				System.out.println("First Record");
				System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
				System.out.println("------------------------------------------------------------------------------------------------------------");
				rs.last();
				System.out.println("Last Record");
				System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
				System.out.println("------------------------------------------------------------------------------------------------------------");
				rs.absolute(3);
				System.out.println("Absolute of 3");
				System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
				rs.relative(1);
				System.out.println("------------------------------------------------------------------------------------------------------------");
				System.out.println("Relative of 1");
				System.out.println(rs.getRow() + " ----------> " + rs.getInt(1) + "    " + rs.getString(2));
			} // if
		} // try 
		catch(SQLException se) {
			// handling known exception
			se.printStackTrace();
		} //catch
	} // main
} // class
