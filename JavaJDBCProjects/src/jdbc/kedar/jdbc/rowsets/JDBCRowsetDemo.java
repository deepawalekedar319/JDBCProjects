package jdbc.kedar.jdbc.rowsets;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JDBCRowsetDemo {

	public static void main(String[] args) {
		Properties props = null;
		try ( //  Creating the JDBCRowset object
				OracleJDBCRowSet jrowset = new OracleJDBCRowSet();
				// Creating the FileInputStream object
				InputStream is = new FileInputStream("src/jdbc/kedar/jdbc/commons/jdbc.properties");
				){
			props = new Properties();
			if(props!=null) 
				props.load(is);
			if(props!=null && jrowset!=null) {
				jrowset.setUrl(props.getProperty("jdbc.url"));
				jrowset.setUsername(props.getProperty("jdbc.username"));
				jrowset.setPassword(props.getProperty("jdbc.pass"));
				jrowset.setCommand(props.getProperty("jdbc.query"));
				jrowset.execute();
				while(jrowset.next())
					System.out.println(jrowset.getInt(1));
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
