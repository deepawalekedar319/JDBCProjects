package jdbc.kedar.jdbcguilogin;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogInGUIApplication extends JFrame implements ActionListener , WindowListener{
	private static final String CHECK_ME = "SELECT COUNT(*) FROM USER_CREDENTIALS WHERE UNAME=? AND UPASS=?";
	private JTextField username;
	private JPasswordField password;
	private JButton login,signup,exit;
	private Connection con = null;
	private PreparedStatement ps =null;
	private ResultSet rs = null;
	public LogInGUIApplication() {
		//Auto-generated constructor stub
		setSize(500,250);
		setTitle("Login...");
		setLayout(new FlowLayout());
		//Adding Components
		// Adding Labels ams text fields 
		JLabel userId = new JLabel("Enter UserName : ");
		add(userId);
		username = new JTextField(10);
		add(username);
		JLabel pass = new JLabel("Enter Password : ");
		add(pass);
		password = new JPasswordField(10);
		add(password);
		// Adding Buttons
		login = new JButton("Login");
		add(login);
		signup = new JButton("Signup");
		add(signup);
		exit = new JButton("Exit");
		add(exit);
		login.addActionListener(this);
		signup.addActionListener(this);
		exit.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	} // Constructor
	public static void main(String[] args) {
		new LogInGUIApplication();
	} // main
	@Override
	public void actionPerformed(ActionEvent e) {
		// Auto-generated method stub
		if(e.getSource()==login) {
			if(getJDBCConnections()) {
				JLabel valid = new JLabel("Valid Credentials");
				add(valid);
			} // if
			else System.out.println("no");
		} // login
		else if(e.getSource()==signup) {
			System.out.println("signup");
		} // sign up
		else {
			System.out.println("Exit");
		} //exit
	} // ActionEvent
	@SuppressWarnings("deprecation")
	private boolean getJDBCConnections(){
		boolean flag = false;
		try {
			// Establishing Connections
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","advjava","tiger");
			// Preparing PreparedStatement object
			if(con!=null)
				ps = con.prepareStatement(CHECK_ME);
			// Settings values to PreparedStatement
			if(ps!=null) {
				ps.setString(1, username.getText());
				ps.setString(2, password.getText());
			} // if
			// executing the query
			if(ps!=null)
				rs = ps.executeQuery();
			int count = 0;
			if(rs!=null) {
				rs.next();
				count = rs.getInt(1);
				if(count==0) flag = false;
				else flag = true;
			} // if
		} // try
		catch (SQLException se) {
			// handle known exception
			se.printStackTrace();
		} // catch
		return flag;
	} // getJDBCConnection
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
} // class
