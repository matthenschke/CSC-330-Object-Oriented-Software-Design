package edu.cuny.csi.csc330.project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
/*
 * This GUI creates a new user if a new user does not have an account 
 * to use this application
 * The addUser method is only used for this GUI since it is storing a new entry to the Users table
 * 
 */
public class CreateUser {

	protected JFrame frame;
	private JPasswordField password;
	private JTextField username;

	
	

	/**
	 * Create the application.
	 */
	
	//there is no User parameter here for this constructor since we are creating a new User
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Create Account");
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(167, 11, 128, 23);
		frame.getContentPane().add(title);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if pressed, go back to the "Login" GUI
				frame.setVisible(false);
				Login l = new Login();
				l.frame.setVisible(true);
			}
		});
		backButton.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		backButton.setBounds(94, 186, 72, 23);
		frame.getContentPane().add(backButton);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create a new instance of User
				User u = new User();
				u.setUsername(username.getText());
				u.setPassword(password.getPassword());
				
				//first check if any of the fields are null
				if(username.getText().isEmpty() || password.getPassword().length == 0) 
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");
				
				//if it passes the first case, check to see if the User already exists in the database
				else if (Database.userExists(u) == true)
					JOptionPane.showMessageDialog(null, "Username already exists please choose another Username");
				
				//if the addUser method failed for some reason
				else if (addUser(u) == false)
					JOptionPane.showMessageDialog(null, "User could not be added at this time please try again later");
				
				else {
				/*
				 * if the account was successfully added, then transfer to the "MainFrame" 
				 * GUI where it can access all the application's features
				 */
				frame.setVisible(false);
				MainFrame m = new MainFrame(u);
				m.frame.setVisible(true);
				}
				
				}
			
		});
		submitButton.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		submitButton.setBounds(277, 186, 72, 23);
		frame.getContentPane().add(submitButton);
		
		JLabel h1 = new JLabel("Username:");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		h1.setBounds(149, 76, 52, 18);
		frame.getContentPane().add(h1);
		
		JLabel h2 = new JLabel("Password:");
		h2.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		h2.setBounds(149, 120, 58, 18);
		frame.getContentPane().add(h2);
		
		password = new JPasswordField();
		password.setBounds(209, 118, 86, 18);
		frame.getContentPane().add(password);
		
		username = new JTextField();
		username.setBounds(209, 74, 86, 18);
		frame.getContentPane().add(username);
		username.setColumns(10);
	}
	
	//method that adds the User to the Database
	public static boolean addUser(User u) {
		try {
			Connection c = Database.getConnection(); //get connection to database
			DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Date date = new Date();
	        String sql = "INSERT INTO Users (appUser,appPass,date) VALUES(?,?,?)"; //sql query
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1,u.getUsername());
			ps.setString(2, String.valueOf(Hash.hashPassword(u.getPassword())));
			ps.setString(3, df.format(date));
			ps.executeUpdate();
			
			
			
			//closing sql variables to prevent resource leak
			 ps.close();
			 c.close();
			 return true;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			
			
			
		
	}
	}

}
