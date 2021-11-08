package jdbc.kedar.jdbc.callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/*create or replace procedure p_authentication(username in varchar2,password in varchar2,result out varchar2)as
cnt number(2);
begin
  select count(*) into cnt from user_credentials where uname=username and upass=password;
if(cnt<>0) then
 result:='VALID CREDENTIALS WELCOME... '+ username;
else
 result:='INVALID CREDENTIALS...';
end if;
end p_authentication; 
/
*/

import java.util.Scanner;

public class ProcedureAuthenticationTest {
	private static final String AUTHENTICATION_PROCEDURE = "{ CALL p_authentication(?,?,?)}";
	public static void main(String[] args) {
		try(Scanner scan = new Scanner(System.in)){
			// Reading inputs 
			String user = null, pass = null;
			if(scan!=null) {
				System.out.println("Enter the username...");
				user = scan.next();
				System.out.println("Enter the password... ");
				pass = scan.next();
			} // if 
			try( // Establishing Connection
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
					// Preparing CallabaleStatement object
					CallableStatement cs = con.prepareCall(AUTHENTICATION_PROCEDURE);){
				// Registering OUT parameters
				if(cs!=null)
					cs.registerOutParameter(3, Types.VARCHAR);
				// Setting IN parameters
				if(cs!=null) {
					cs.setString(1, user);
					cs.setString(2, pass);
				} // if
				// Execute the procedure
				if(cs!=null)
					cs.execute();
				// Gather the result 
				if(cs!=null) {
					String result = cs.getString(3);
					System.out.println(result);
				} // if
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
