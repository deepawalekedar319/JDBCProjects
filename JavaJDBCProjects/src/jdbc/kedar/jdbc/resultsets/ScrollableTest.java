package jdbc.kedar.jdbc.resultsets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {
	private static final String SELECT_QUERY = "SELECT * FROM XE.EMP";
	public static void main(String[] args) {
		try ( // Establishing Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// Creating Simple statement  object
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				// Creating ResultSet object and Executing the query
				ResultSet rs= st.executeQuery(SELECT_QUERY); ){		
			// Processing the result
			if(rs!=null) {
				System.out.println("Top to bottom...");
				while(rs.next())
					System.out.println(rs.getRow() + "             " + rs.getInt(1));
				rs.afterLast();
				System.out.println("Bottom to top...");
				while(rs.previous())
					System.out.println(rs.getRow() + "             " + rs.getInt(1));
				System.out.println("First Record...");
				rs.first();
				System.out.println(rs.getInt(1));
				rs.last();
				System.out.println(rs.getInt(1));
			} // if
		} // try
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
