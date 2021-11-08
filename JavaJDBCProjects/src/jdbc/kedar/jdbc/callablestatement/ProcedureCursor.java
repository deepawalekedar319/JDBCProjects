package jdbc.kedar.jdbc.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class ProcedureCursor {
	private static final String CALL_PROCEDURE = "{CALL TESTINGCURSOR(?,?)}";
	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)){
			// Reading input form user
			String initialCharacter = null;
			if(scan!=null) {
				System.out.println("Enter the initial character of the employee name...");
				initialCharacter = scan.next() + "%";
			} // if
			try ( // Creating connection 
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// creating CallableStatement object
					CallableStatement callStatement = con.prepareCall(CALL_PROCEDURE);	){
				// Registering out parameters 
				if(callStatement!=null)
					callStatement.registerOutParameter(2, OracleTypes.CURSOR);
				// Setting in parameters 
				if(callStatement!=null) 
					callStatement.setString(1, initialCharacter);
				// Executing the procedure
				if(callStatement!=null)
					callStatement.execute();
				// Gathering the results
				if(callStatement!=null) {
					try (ResultSet rs = (ResultSet)callStatement.getObject(2)){
						if(rs!=null) {
							boolean flag = false;
							while(rs.next()) {
								flag = true;
								System.out.println(rs.getString(1) + "    " + rs.getInt(2));
							} // if
							if(!flag)
								System.out.println("No match found...");
						} // if
					} // try - 3
				} // if
			} // try - 2
			catch (SQLException se) {
				// handle exception
				se.printStackTrace();
			} // catch
			catch (Exception e) {
				// handle exception
				e.printStackTrace();
			} // catch
		} // try - 1
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
