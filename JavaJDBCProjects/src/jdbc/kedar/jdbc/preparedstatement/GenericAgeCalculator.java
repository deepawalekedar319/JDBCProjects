package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class GenericAgeCalculator {
	private static final String GENERIC_AGE_QUERY = "SELECT DOB FROM PERSON_DETAILS WHERE PID=?";
	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in)){
			// Reading Inputs 
			int id = 0;
			if(scan!=null) {
				System.out.println("Enter the person i'd : ");
				id = scan.nextInt();
			} // if
			// Create Connections and PreparedStarement object.
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
					PreparedStatement ps = con.prepareStatement(GENERIC_AGE_QUERY); 
					){
				// Set the parameters and execute the query 
				if(ps!=null) {
					ps.setInt(1, id);
					try(ResultSet rs = ps.executeQuery();){
						// Process the result
						if(rs!=null) {
							if(rs.next()) {
								java.sql.Date dateOfBirth = rs.getDate(1);
								java.util.Date utilDate = new java.util.Date();
								float age = (utilDate.getTime()-dateOfBirth.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
								DecimalFormat nf = new DecimalFormat();
								nf.setMaximumFractionDigits(2);
								System.out.println("Person age is : " + nf.format(age));
							} // if
							else
								System.out.println("Record not found...");
						} // if
					} // try - 3
				} // if
			} // try - 2
		} // try - 1
		catch(SQLException se) {
			se.printStackTrace();
		} // catch
		catch(Exception e) {
			e.printStackTrace();
		} // catch
	} // main
} // class
