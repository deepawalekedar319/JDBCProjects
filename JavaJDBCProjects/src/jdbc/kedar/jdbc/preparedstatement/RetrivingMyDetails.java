package jdbc.kedar.jdbc.preparedstatement;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;


public class RetrivingMyDetails {
	private static final String 	RETRIEVE_QUERY = "SELECT ID,NAME,IMAGE FROM MY_DETAILS WHERE ID=?";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			// Read inputs 
			int id = 0;
			if(scan!=null) {
				System.out.println("Enter the I'd : ");
				id = scan.nextInt();
			} // if 
			// Create Connection and PreparedStatement object
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					PreparedStatement ps = con.prepareStatement(RETRIEVE_QUERY)){
				//Set the parameters
				if(ps!=null)
					ps.setInt(1, id);
				// execute the query
				try(ResultSet rs = ps.executeQuery()){
					// Process the query
					if(rs!=null) {
						if(rs.next()) {
							id = rs.getInt(1);
							String name = rs.getString(2);
							System.out.println(id + "    " + name);
							// Get InputStream pointing to BLOB objet
							try(InputStream is = rs.getBinaryStream(3);
									OutputStream os = new FileOutputStream("Retireve.jpg")){
								// Copy Blob object to the destination file
								IOUtils.copy(is,os);
								System.out.println("File copied in the specified location...");
							} // try - 4
						} // if
					} // if
				} // try - 3
			} // try - 2
		} // try - 1
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
