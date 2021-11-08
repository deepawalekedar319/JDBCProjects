package jdbc.kedar.jdbc.preparedstatement;


import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClobInsertionTest {
	private static final String INSERT_RESUME = "INSERT INTO FILESTABLE(NAME,RESUME) VALUES(?,?)";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			String name = null, fileLocation = null;
			if(scan!=null) {
				System.out.println("Enter you name... ");
				name = scan.next();
				System.out.println("Enter your resume location...");
				fileLocation = scan.next();
			} // if
			try( // Establishing Connection
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// Creating PreparedStstement object
					PreparedStatement ps = con.prepareStatement(INSERT_RESUME);){
				// Creating InputStream object
				try(Reader reader  = new FileReader(fileLocation);){
					// Setting Values
					if(ps!=null) {
						ps.setString(1, name);
						ps.setCharacterStream(2, reader);
					} // if
					// Execute the query
					int count = 0;
					if(ps!=null) 
						count = ps.executeUpdate();
					if(count==0) System.out.println("Error while Insertion...");
					else System.out.println("Record inserted...");
				} // try - 3
			} // try - 2 
		} // try - 1 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch		
		catch (Exception e) {
			//handle unknown exception
			e.printStackTrace();
		} // catch
	} // main
} // class
