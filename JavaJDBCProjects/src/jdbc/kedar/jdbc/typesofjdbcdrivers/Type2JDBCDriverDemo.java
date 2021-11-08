package jdbc.kedar.jdbc.typesofjdbcdrivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Type2JDBCDriverDemo {

	public static void main(String[] args) {
		try ( // Establish Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:oci8:@xe","xe","tiger");
				// Creating PreparesStatement object
				PreparedStatement ps = con.prepareStatement("SELECT SNO FROM STUDENT");){
			// Execute the query
			if(ps!=null) {
				try(ResultSet rs = ps.executeQuery();){
					if(rs!=null) {
						while(rs.next()) {
							System.out.println("Student Number : " + rs.getInt(1));
						} // while
					} // if
				} // try - 2
			} // if
		} // try-  1
		catch (Exception e) {
			e.printStackTrace();
		} // catch
	} // main
} // class
