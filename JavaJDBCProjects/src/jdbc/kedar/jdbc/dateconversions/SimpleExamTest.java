package jdbc.kedar.jdbc.dateconversions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class SimpleExamTest {

	public static void main(String[] args) {
		try(  // Establishing Connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// Creating statement object
				Statement st = con.createStatement(); ){
			int res = st.executeUpdate("CREATE TABLE TESTFOREXAM (SNO NUMBER(5))");	
			if(res==1) {
				System.out.println("Not Created");
			} // if
			else {
				System.out.println("Created");
			} // else
			System.out.println("Enter the values to enter into table");
			try(PreparedStatement ps = con.prepareStatement("INSERT INTO TESTFOREXAM VALUES(?)");
					Scanner scan = new Scanner(System.in);){
				int n = scan.nextInt();
				if(ps!=null) {
					ps.setInt(1, n);
					int op = ps.executeUpdate();
					if(op!=1) System.out.println("Not Inserted");
					else System.out.println("Done");
				} // if
			}
		} // try
		
		catch (Exception e) {
			//  handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
