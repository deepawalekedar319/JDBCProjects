package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateRetrieveTest {
	private static final String 	GET_ALL_DATA = "SELECT * FROM TEST_DATE";
	public static void main(String[] args) {
		try ( // Creating Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// Preparing PreparedStatement Object
				PreparedStatement ps = con.prepareStatement(GET_ALL_DATA);	){
			if(ps!=null) {
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next())
						System.out.println("Date of Birth : " + rs.getString(1));
				} // try - 2
			} // if
		} // try - 1
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
	} // main
} // class
