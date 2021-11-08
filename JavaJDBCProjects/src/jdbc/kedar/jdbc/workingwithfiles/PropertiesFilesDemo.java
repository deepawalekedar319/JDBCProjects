package jdbc.kedar.jdbc.workingwithfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PropertiesFilesDemo {
//	private static final String SQL_QUERY = "SELECT * FROM XE.EMP";
	public static void main(String[] args) {
		Properties props = null;
		try (InputStream is = new FileInputStream("src/jdbc/kedar/jdbc/commons/jdbc.properties")){
			props = new Properties();
			props.load(is);
		} // try
		catch (FileNotFoundException fne) {
			//  handle exception
			fne.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
		try ( // Establish Connections
				Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"),
																									props.getProperty("jdbc.username"),
																									props.getProperty("jdbc.pass"));
				// Establishing the Statement
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				// Execute the query
				ResultSet rs = st.executeQuery(props.getProperty("jdbc.query"));
				){
			// Process the query
			if(rs!=null) {
				while(rs.next())
					System.out.println(rs.getInt(1));
			} // if
		} // try
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
