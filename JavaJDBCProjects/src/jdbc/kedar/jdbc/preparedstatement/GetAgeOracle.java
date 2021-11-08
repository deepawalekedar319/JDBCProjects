package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetAgeOracle {
	private static final String 	GET_PERSON_AGE = "SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_DETAILS WHERE PID=?";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			// Reading inputs 
			int id = 0;
			if(scan!=null) {
				System.out.println("Enter the person i'd : ");
				id = scan.nextInt();
			} // if
			// Create connection and prepared statement object
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
					PreparedStatement ps = con.prepareStatement(GET_PERSON_AGE);){
				
				// set query params
				if(ps!=null) 
					ps.setInt(1, id);
				// Execute the query
				if(ps!=null) {
					try( ResultSet rs = ps.executeQuery()){
						if(rs!=null) {
							if(rs.next()) {
								float age = rs.getFloat(1);
								System.out.println("Person Age is : " + age);
							} // if
							else 
								System.out.println("Record not found...");
						} // if
					} // try 3
				} // if				
			} // try 2
		} // try 1 
		catch (SQLException se) {
			//handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} // catch
	} // main
} // class
