package jdbc.kedar.jdbc.resultsets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementSensitiveTest {
	private static final String SELECT_QUERY = "SELECT * FROM XE.EMP";
	public static void main(String[] args) {
		try ( // Establishing Connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// Creating PreparedSttement Object
				PreparedStatement ps = con.prepareStatement(SELECT_QUERY,
																								  ResultSet.TYPE_SCROLL_SENSITIVE,
																								  ResultSet.CONCUR_UPDATABLE); 
				// Executing the query
				ResultSet rs = ps.executeQuery();	){
			// Processing the Result
			if(rs!=null) {
				System.out.println("Top to bottom...");
				while(rs.next())
					System.out.println(rs.getRow());
				rs.afterLast();
				System.out.println("Top to bottom...");
				while(rs.previous())
					System.out.println(rs.getRow());
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
