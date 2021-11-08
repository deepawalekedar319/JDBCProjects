package jdbc.kedar.jdbc.dateconversions;

import java.text.SimpleDateFormat;

public class DateConvesions {

	public static void main(String[] args) {
		try {
			String date = "13-06-2021";
			SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyyy");
			
			// converting string into util date class object
			java.util.Date ud = sfd.parse(date);
			System.out.println("String Date : " + date);
			System.out.println("util.Date : " + ud);
			
			//Date in sql date format
			long ms = ud.getTime();
			java.sql.Date sd = new java.sql.Date(ms);
			System.out.println("sql.Date : " + sd);
			
			// If String pattern is yyyy-MM-dd Then no need to convert 
			String s2 = "2021-06-21";
			java.sql.Date sd1 = java.sql.Date.valueOf(s2);
			System.out.println("Direct conversions... : " + sd1);
		} // try
		catch (Exception e) {
			// handling exception
			e.printStackTrace();
		} // catch
	} // main
} // class
