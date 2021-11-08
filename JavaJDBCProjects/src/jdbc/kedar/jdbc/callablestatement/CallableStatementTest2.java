package jdbc.kedar.jdbc.callablestatement;

import java.sql.CallableStatement;

/*CREATE OR REPLACE PROCEDURE SAMPLETESTPROCEDURE 
(
  VALUE1 IN NUMBER 
, VALUE2 IN NUMBER 
, RES OUT VARCHAR2 
) AS 
BEGIN
  RES:='YOU HAVE ENTERED '+VALUE1 + ' AND ' + VALUE2;
END SAMPLETESTPROCEDURE;*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementTest2 {
	private static final String CALL_PROCEDURE = "'{CALL SAMPLETESTPROCEDURE(?,?,?)}";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			// Reading inputs from end user
			int value1 = 0, value2 = 0;
			if(scan!=null) {
				System.out.println("Enter first value : ");
				value1 = scan.nextInt();
				System.out.println("Enter second value : ");
				value2 = scan.nextInt();
			} // if
			try( // Establish Connection 
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// Create CallableStatement Object
					CallableStatement cs = con.prepareCall(CALL_PROCEDURE);){
				// Register OUT parameter 
				if(cs!=null)
					cs.registerOutParameter(3, Types.VARCHAR);
				// Set values to IN parameter
				if(cs!=null) {
					cs.setInt(1, value1);
					cs.setInt(2, value2);
				} // if
				//Execute or Call the procedure
				if(cs!=null)
					cs.execute();
				// Gather the result
				if(cs!=null) {
					String res = cs.getString(3);
					System.out.println(res);
				} // if
			} // try - 2
		} // try - 1  
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle unknown exception
			e.printStackTrace();
		} // catch
	} // main
} // class
