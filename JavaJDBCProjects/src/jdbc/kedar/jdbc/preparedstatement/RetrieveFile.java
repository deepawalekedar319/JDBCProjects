package jdbc.kedar.jdbc.preparedstatement;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.io.IOUtils;

public class RetrieveFile {
	private static final String GET_FILE ="SELECT NAME,DATE,RESUME FROM ";
	public static void main(String[] args) {
		try ( // Establish Connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// prepare PreparedStatement object
				PreparedStatement ps = con.prepareStatement(GET_FILE);
				// Preparing ResultSet Object
				ResultSet rs = ps.executeQuery();	){
			// Processing the query
			if(rs!=null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println("Name :: " + rs.getString(1) + "Date :: " + rs.getString(2));
					// Processing the File
					try(Reader reader = rs.getCharacterStream(3);
							Writer writer = new FileWriter("Retrieved")){
						IOUtils.copy(reader,writer);
						System.out.println("Data copied....");
					} // try - 2
				} // while
				if(!flag)
					System.out.println("Records Not found...");
			} // if
		} // try - 1
		catch (Exception e) {
			//  handle unknown exception
			e.printStackTrace();
		} // catch
	} // main
} // class
