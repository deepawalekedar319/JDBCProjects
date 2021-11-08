package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferFormXeToAdvjava {
	private static final String XE_SELECT_QUERY = "SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_DETAILS";
	private static final String ADVJAVA_INSERT_QUERY = "INSERT INTO PERSON_DETAILS VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		try (Connection conXe = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				Connection conAdvjava = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				 Statement st = conXe.createStatement();
				PreparedStatement ps = conAdvjava.prepareStatement(ADVJAVA_INSERT_QUERY);){
			// send and execute the query in Xe user and get the records.
			if(st!=null) {
				try(ResultSet rs = st.executeQuery(XE_SELECT_QUERY)){
					//Process the result
					int count = 0;
					if(rs!=null) {
						while(rs.next()) {
							int id = rs.getInt(1);
							String name = rs.getString(2);
							java.sql.Date dob = rs.getDate(3);
							java.sql.Date doj = rs.getDate(4);
							java.sql.Date dom = rs.getDate(5);
							
							// set the parameters 
							ps.setInt(1, id);
							ps.setString(2, name);
							ps.setDate(3, dob);
							ps.setDate(4, doj);
							ps.setDate(5, dom);
							// Execute the query
							count = ps.executeUpdate();
						} // while
						if(count ==0) System.out.println("Not Copied... Some Problem please check");
						else System.out.println("Copied...");
					} // if
				} // try - 2
			} // if
		} // try - 1  
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			//handle unknown exception
			e.printStackTrace();;
		}
	} // main
} // class
