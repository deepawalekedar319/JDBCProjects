package jdbc.kedar.jdbc.transactionmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferMoneyTxMgmt {

	public static void main(String[] args) {
		long sourceAccountNumber = 0,designationAccountNumber = 0;
		long money = 0;
		// Reading Inputs
		try (Scanner scan = new Scanner(System.in)){
			if(scan!=null) {
				System.out.println("Enter the source Account number : ");
				sourceAccountNumber = scan.nextLong();
				System.out.println("Enter the destignation Account  number : ");
				designationAccountNumber = scan.nextLong();
				System.out.println("Enter the amount to be transfer : ");
				money = scan.nextLong();
			} // if
		} // try
		catch (Exception e) {
			// handle exception
			e.printStackTrace();
		} // catch
		try ( // Establishing the connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
				// creating the statement object
				Statement st = con.createStatement();){
			// Begin the Transaction
			if(con!=null)
				con.setAutoCommit(false);
			// Adding the queries to the batch ( Executing the queries in the batch)
			if(st!=null) {
				// Logic for Withdraw Operation
				st.addBatch("UPDATE USER_BANK SET MONEY = MONEY - " + money + " WHERE ACNO = " + sourceAccountNumber);
				// Logic for Deposit Operation
				st.addBatch("UPDATE USER_BANK SET MONEY = MONEY + " + money + " WHERE ACNO = " + designationAccountNumber);
				// Execute the batch
				int[] result = st.executeBatch();
				// process the result
				boolean flag = true;
				for(int i=0;i<result.length;i++) {
					if(result[i] == 0) {
						flag = false;
						break;
					} // if
				} // for
				if(flag == true) {
					con.commit();
					System.out.println("Amount deposited successfully...");
				} // if
				else {
					con.rollback();
					System.out.println("An Error has occured while transfering the money...");
				} // else
			} // if
		} // try
		catch (SQLException se) {
			// handle exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			//  handle exception
			e.printStackTrace();
		} // catch
	} // main
} // class
