package jdbc.kedar.jdbc.metadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {
	private static final String SELECT_QUERY = "SELECT * FROM XE.EMP";
	public static void main(String[] args) {
		try ( // Establishing Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger"); 
				// Creating Statement object
				Statement st = con.createStatement();
				// Executing the query
				ResultSet rs = st.executeQuery(SELECT_QUERY);){
			// Creating the ResultSetMetaData object
			ResultSetMetaData rsm = null;
			if(rs!=null)
				rsm = rs.getMetaData();
			// Processing the Result
		   // get column count
			if(rsm!=null) {
				int columnCount = rsm.getColumnCount();
				for(int i=1;i<=columnCount;i++)
					System.out.println(" Column Name : " + rsm.getColumnName(i) +
													 "     Column Type : " + rsm.getColumnTypeName(i));				
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
