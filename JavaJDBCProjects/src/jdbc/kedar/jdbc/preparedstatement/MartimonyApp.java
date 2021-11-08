package jdbc.kedar.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MartimonyApp {
	private static final String INSERT_DETAILS = "INSERT INTO MARTIMONY VALUES(PERSON_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			System.out.println("Welcome to Martimony App...");
			// Reading user inputs
			String name = null,gender = null,dob = null, photo = null,resume = null,doj = null,firstJob = null,bioData = null,audioInfo = null;
			if(scan!=null) {
				System.out.println("Enter your name : ");
				name = scan.next();
				System.out.println("Enter your gender [ Male/Female ]");
				gender = scan.next();
				System.out.println("Enter your Date Of Birth : ");
				dob = scan.next();
				System.out.println("Enter your photo location : ");
				photo = scan.next();
				System.out.println("Enter your resume : ");
				resume = scan.next();
				System.out.println("Enter your Date Of Joining : ");
				doj = scan.next();
				System.out.println("Enter your First Job name  : ");
				firstJob = scan.next();
				System.out.println("Enter your Bio-Date file location : ");
				bioData = scan.next();
				System.out.println("Enter your audio information file location  : ");
				audioInfo= scan.next();
				System.out.println("Please wait your data is being updating...");
			} // if
			// Prepare Connection and Prepared Statement object
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					PreparedStatement ps = con.prepareStatement(INSERT_DETAILS)){
						// Setting parameters to the query
						if(ps!=null) {
							ps.setString(1, name);
							ps.setString(2,gender);
							// DOB
							// Converting String date to util date
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date utilDob = sdf.parse(dob);
							// converting java.util to java.sql date
							long milliSeconds = utilDob.getTime();
							java.sql.Date sqlDob = new java.sql.Date(milliSeconds);
							
							ps.setDate(3, sqlDob);
							// Setting image
							try(InputStream is = new FileInputStream(photo)){
								ps.setBinaryStream(4, is);
							} // try - 3
							
							// Setting resume
							try(Reader reader = new FileReader(resume)){
								ps.setCharacterStream(5, reader);
							} // try -4
							//DOJ
							java.util.Date utilDoj = sdf.parse(doj);
							// converting java.util to java.sql date
							milliSeconds = utilDoj.getTime();
							java.sql.Date sqlDoj = new java.sql.Date(milliSeconds);
							
							ps.setDate(6, sqlDoj);
							ps.setString(7, firstJob);
							
							// Setting bio data
							try(Reader reader = new FileReader(bioData)){
								ps.setCharacterStream(8, reader);
							} // try -4
							
							// Setting audioFile
							try(InputStream is = new FileInputStream(audioInfo)){
								ps.setBinaryStream(9, is);
							} // try - 3
							
						} //if
						//Execute the query
						int count = 0;
						if(ps!=null)
							count = ps.executeUpdate();
						//Process the query
						if(count==0) System.out.println("Failed to insert...");
						else System.out.println("congratulations... you have successfully registerd in martimony app.");
					} // try - 2
		} // try - 1
		catch (SQLException se) {
			//handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// TODO: handle exception
		} // catch
	} // main
} // class
