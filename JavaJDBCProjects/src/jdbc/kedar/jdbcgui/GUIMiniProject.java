package jdbc.kedar.jdbcgui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUIMiniProject extends JFrame implements ActionListener,WindowListener{
	private JLabel lableSno,lableName,lableAddress,lableAvg;
	private JTextField textSno,textName,textAddress,textAvg;
	private JButton nextButton,previousButton,firstButton,lastButton;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private static final String GET_STUDENTS_RECORDS = "SELECT SNO,SNAME,SADDR,SAVG FROM STUDENT";
	public GUIMiniProject() {
		// Auto-generated constructor stub
		setTitle("College Management"); // Setting title
		setSize(300,200); // Setting size 
		setLayout(new FlowLayout()); // Setting layout for the frame
		
		// Adding Components
		lableSno = new JLabel("Student number : ");
		add(lableSno);
		textSno = new JTextField(10);
		add(textSno);
		lableName = new JLabel("Student name : ");
		add(lableName);
		textName = new JTextField(10);
		add(textName);
		lableAddress = new JLabel("Student address : ");
		add(lableAddress);
		textAddress = new JTextField(10);
		add(textAddress);
		lableAvg = new JLabel("Student Average : ");
		add(lableAvg);
		textAvg = new JTextField(10);
		add(textAvg);
		
		// Adding buttons
		firstButton = new JButton("First");
		add(firstButton);
		nextButton = new JButton("Next");
		add(nextButton);
		previousButton = new JButton("Previous");
		add(previousButton);
		lastButton = new JButton("Last");
		add(lastButton);
		
		// Register and Activate all the four buttons
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		
		// Add window Listener to Frame
		this.addWindowListener(this);
		
		// Disable the edit on Text Box
		textSno.setEditable(false);
		textName.setEditable(false);
		textAddress.setEditable(false);
		textAvg.setEditable(false);
		
		setVisible(true); // Making the frame to be visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // The closing of frame window will terminates the application
		initializeJDBC();
	} // Constructor
	
	private void initializeJDBC() {
		try {
			// Establish the Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","xe","tiger");
			// Prepare PreparedStatement Object
			if(con!=null)
				ps = con.prepareStatement(GET_STUDENTS_RECORDS,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			// Prepare Scroll able ResultSet object
			if(ps!=null)
				rs = ps.executeQuery();
		} //try 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		catch (Exception e) {
			//  handle known exception
			e.printStackTrace();
		} // catch
	} // Initialize JDBC method
	public static void main(String[] args) { 
		new GUIMiniProject(); //  Anonymous Class		
	} // main
	@Override
	public void actionPerformed(ActionEvent e) {
		// Auto-generated method stub
		boolean flag = false;
		if(e.getSource()==nextButton) {
			try {
				if(!rs.isLast()) {
					rs.next();
					flag = true;					
				} // if
			} //try 
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
		} // Next Button
		else if(e.getSource()==previousButton) {
			 try {
				 if(!rs.isFirst()) {
					 rs.previous();
						flag = true;
					} // if
			} // try 
			 catch (SQLException se) {
				// handle known exception
				 se.printStackTrace();
			} // catch
		} // Previous Button
		else if(e.getSource()==firstButton) {
			try {
				rs.first();
				flag = true;
				
			} //try 
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
		} // First Button
		else {
			try {
				rs.last();
				flag = true;
			} // try 
			catch (SQLException se) {
				// handle known exception
				se.printStackTrace();
			} // catch
		} // Last Button
		try {
			if(flag==true) {
				textSno.setText(rs.getString(1));
				textName.setText(rs.getString(2));
				textAddress.setText(rs.getString(3));
				textAvg.setText(rs.getString(4));
			} // if
		} // try
		catch(SQLException se) {
			se.printStackTrace();
		} // catch
	} // action performed method

	@Override
	public void windowOpened(WindowEvent e) {
		// Auto-generated method stub
	} // Window Opened

	@Override
	public void windowClosing(WindowEvent e) {
		// Auto-generated method stub
		try {
			if(rs!=null)
				rs.close();
		} // try 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		try {
			if(ps!=null)
				ps.close();
		} // try 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		try {
			if(con!=null)
				con.close();
		} // try 
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
	} // Window Closing

	@Override
	public void windowClosed(WindowEvent e) {
		// Auto-generated method stub
		System.out.println("WondowClosed");
	} // Window Closed

	@Override
	public void windowIconified(WindowEvent e) {
		//  Auto-generated method stub
		System.out.println("WondowConified");
	} // Window Conified

	@Override
	public void windowDeiconified(WindowEvent e) {
		// Auto-generated method stub
		System.out.println("Wondow Deconified");
	} // Window Deconified

	@Override
	public void windowActivated(WindowEvent e) {
		// Auto-generated method stub
	} // Window Activated

	@Override
	public void windowDeactivated(WindowEvent e) {
		// Auto-generated method stub
	} // Window Deactivated
} // class