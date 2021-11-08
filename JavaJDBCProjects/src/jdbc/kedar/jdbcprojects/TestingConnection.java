package jdbc.kedar.jdbcprojects;

/* Testing JDBC Connection with  Oracle DataBase*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestingConnection {

	public static void main(String[] args) {
		// create jdbc object 
		try {
			//Creating jdbc object
			oracle.jdbc.driver.OracleDriver obj = new oracle.jdbc.driver.OracleDriver();
			//Registering object
			DriverManager.registerDriver(obj);
			//creating Connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			System.out.println(con);
			con.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
