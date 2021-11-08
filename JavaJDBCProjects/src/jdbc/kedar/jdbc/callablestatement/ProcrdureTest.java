package jdbc.kedar.jdbc.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcrdureTest {
	private static final String CALL_PROCEDURE = "{CALL TESTINGPROCEDURE(?,?,?)}";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			int firstNumber = 0, secondNumber = 0;
			if(scan!=null) {
				System.out.println("Enter the first number...");
				firstNumber = scan.nextInt();
				System.out.println("Enter the second number...");
				secondNumber = scan.nextInt();
			} // if
			try(
					// Creating Connection object
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// Creating CallableStatement Object
					CallableStatement callStatement = con.prepareCall(CALL_PROCEDURE);	){
				// Registering out parameters to the query
				if(callStatement!=null) {
					callStatement.registerOutParameter(3, Types.INTEGER);
				} // if
				// Setting values to In Parameters
				if(callStatement!=null) {
					callStatement.setInt(1, firstNumber);
					callStatement.setInt(2, secondNumber);
				} // if
				// executing the procedure
				if(callStatement!=null) 
					callStatement.execute();
				// Gathering the results
				if(callStatement!=null) {
					int result = callStatement.getInt(3);
					System.out.println("Result is : " + result);
				} // if
			} // try - 2
			catch(SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
		} // try - 1
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
	} //main
} // class