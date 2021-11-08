package jdbc.kedar.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScrollablePSTest {
	private static final String SELECT_QUERY = "SELECT ENAME,EMPNO FROM EMP";
	public static void main(String[] args) {
		try ( // Establish Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
				// Creating PreparedStatement Object
				PreparedStatement st = con.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				// Creating ResultSet Object 
				ResultSet rs = st.executeQuery();){
			// Process the result
			if(rs!=null) {
				System.out.println("Top to Bottom");
				while(rs.next()) {
					System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));
				} // while
				System.out.println("Bottom to top");
				rs.afterLast();
				while(rs.previous()) {
					System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));					
				} // while
				System.out.println("First Row");
				rs.first();
				System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));
				rs.last();
				System.out.println("Last Row");
				System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));
				rs.absolute(4);
				System.out.println("Fourth Row");
				System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));
				rs.relative(1);
				System.out.println("Fifth Row");
				System.out.println(rs.getRow() + " ------>" + rs.getString(1) + "      " + rs.getInt(2));
			} // if
		} // try
		catch (Exception e) {
			// handle exception
		} // catch
	} // main
} // class
