package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class RetrivingSampleTestDates {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				// Prepare statement object
				Statement st = con.createStatement();	){
			// send and execute the query
			if(st!=null) {
				try(ResultSet rs = st.executeQuery("SELECT DOB,DOJ,DOL FROM TESTDATEINSERTION")){
					if(rs!=null) {
						boolean flag = false;
						while(rs.next()) {
							flag = true;
							java.sql.Date sqlDob = rs.getDate(1);
							java.sql.Date sqlDoj = rs.getDate(2);
							java.sql.Date sqlDol = rs.getDate(3);
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String dob = sdf.format(sqlDob);
							String doj = sdf.format(sqlDoj);
							String dol = sdf.format(sqlDol);
							System.out.println(dob + "   " + doj + "   " + dol);
						} //while
						if(!flag) System.out.println("No records...");
					} // if
				} // try - 2
			} // if
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
