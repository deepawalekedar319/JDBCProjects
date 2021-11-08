package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTestPS {
	private static final String INSERT_CUSTOMER_RECORDS = "INSERT INTO CUSTOMER VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner scan = null;	
		Connection con = null;
		PreparedStatement ps = null;
		try {
			scan = new Scanner(System.in);
			int numberOfCustomers = 0;
			if(scan!=null) {
				System.out.println("Enter the numbers of customers to insert into table ");
				numberOfCustomers = scan.nextInt();
			}// if				
			// register JDBC object
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			
			//create PreparedStatment Object
			if(con!=null)
				ps = con.prepareStatement(INSERT_CUSTOMER_RECORDS);
			
			//read inputs from end user and set to the sql query
			if(scan!=null && ps!=null) {
				System.out.println("\tEnter " + numberOfCustomers + " record details  ");
				for(int i=1;i<=numberOfCustomers;i++) {
					System.out.print("Enter " + i + " customer id : ");
					int id  = scan.nextInt();
					System.out.print("Enter " + i + " customer name : ");
					String name = scan.next();
					System.out.print("Enter " + i + " customer address : ");
					String address = scan.next();
					//setting each customer details to the pre-compiled query params
					ps.setInt(1, id); ps.setString(2, name); ps.setString(3, address);
					
					//execute the pre-compiled query each time
					int result = ps.executeUpdate();
					
					//process the execution result 
					if(result==0) System.out.println(i+ " Record not inserted ");
					else System.out.println(i + " Record inserted successfully");
				} // for
			} // if
		} // try
		catch (SQLException se) {
			// TODO: handle known exception
			if(se.getErrorCode()==12899) System.out.println(" value too large for column");
			else if(se.getErrorCode()==1) System.out.println("This Id is already reserved...");
			se.printStackTrace();
		} // catch
		catch(Exception e) {
			e.printStackTrace();
		} // catch
		finally {
			//close all scanner and jdbc objects
			try {
				if(ps!=null)
					ps.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(con!=null)
					con.close();
			} // try 
			catch (SQLException se) {
				// TODO: handle known exception
				se.printStackTrace();
			} // catch
			try {
				if(scan!=null)
					scan.close();
			} // try 
			catch (Exception e) {
				// TODO: handle unknown exception
				e.printStackTrace();
			} // catch
		} // finally
	} // main
} // class
