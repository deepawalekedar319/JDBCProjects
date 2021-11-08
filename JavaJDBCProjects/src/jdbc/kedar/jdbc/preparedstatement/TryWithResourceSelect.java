package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TryWithResourceSelect {

	public static void main(String[] args) {
		try (
				// Establishing Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				// Creating statement object
				Statement st = con.createStatement();
				//Send and execute the SQL query 
				ResultSet rs = st.executeQuery("SELECT SNAME FROM STUDENT");){
			// Process the result 
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getString(1));
				} // while
				if(!flag) System.out.println("No records found...");
			} // if
		} //try
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
	} // main
} // class
