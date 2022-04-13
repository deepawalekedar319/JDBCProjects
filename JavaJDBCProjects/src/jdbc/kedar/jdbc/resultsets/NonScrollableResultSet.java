package jdbc.kedar.jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NonScrollableResultSet {

	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				Statement sc = con.createStatement();) {
			// Execute the query
			if(sc!=null) {
				try (ResultSet rs = sc.executeQuery("SELECT * FROM EMP");){
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getInt(1) + "    " + rs.getString(2));
						} // while
					} // if
				} // try
				catch (SQLException e) {
					// handle exception
					e.printStackTrace();
				} // catch
			} // if
		} // try
		catch (Exception e) {
			// handle exception
		} // catch		 
	} // main
} // class
