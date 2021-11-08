package jdbc.kedar.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MyDetails {
	private static final String INSERT_MY_RECORD = "INSERT INTO MY_DETAILS VALUES(PERSON_ID.NEXTVAL,?,?)";
	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in);){
			// Reading inputs
			String name = null;
			String photoLocation = null;
			if(scan!=null) {
				System.out.println("Enter name : ");
				name =scan.next();
				System.out.println("Enter the photo location ");
				photoLocation = scan.next();
			} // if
			try(InputStream is = new FileInputStream(photoLocation)){
				// Create connection and prepare statement object
				try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
						PreparedStatement ps = con.prepareStatement(INSERT_MY_RECORD)){
					// set the parameters
					if(ps!=null) {
						ps.setString(1, name);
						ps.setBinaryStream(2, is);
					} // if
					int count = 0;
					if(ps!=null)
						count = ps.executeUpdate();
					if(count==0) System.out.println("Error while inserting the record...");
					else System.out.println("Record Inserted...");
				} // try - 3
			} // try - 2
		} // try - 1		
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			//handle unknown  exception
			e.printStackTrace();
		} // catch
	} // main
} // class
