package jdbc.kedar.jdbc.callablestatement;

/*create or replace procedure first_proc(res out varchar2) as
begin
res:='First Procedure';
end;
/
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementTest {
	private static final String CALL_PROCEDURE = "{CALL first_proc(?)}";
	public static void main(String[] args) {
		try( // Establshing connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				//  Preparing CallableStatement object
				CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
				){
			// Register OUT parameters 	
			if(cs!=null)
				cs.registerOutParameter(1, Types.VARCHAR);
			// Execute or Call the procedure
			if(cs!=null)
				cs.execute();
			// Gather the result
			if(cs!=null) {
				String res = cs.getString(1);
				System.out.println("Result is res " + res);
			} // if
		} // try 1
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
	} // main
} // class
