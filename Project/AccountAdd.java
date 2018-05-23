package edu.cuny.csi.csc330.project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

/*
 * This is the GUI for the add account part of the application
 * This class consists of the add account method that is only used
 * for this class
 * 
 */
public class AccountAdd  {
    
	//all components of the JFrame
	protected JFrame frame;
	private JTextField accountType;
	private JTextField username;
	private JPasswordField password;
	private JLabel h2;
	private JLabel h3;
	protected User user;


	/**
	 * Create the application.
	 */
	
	//this constructor is used to pass the same User that is already logged in
	public AccountAdd(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Sylfaen", Font.PLAIN, 11));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel title = new JLabel("Add Account");
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(166, 11, 112, 25);
		frame.getContentPane().add(title);
		
		accountType = new JTextField();
		accountType.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		accountType.setBounds(176, 66, 137, 20);
		frame.getContentPane().add(accountType);
		accountType.setColumns(10);
		
		username = new JTextField();
		username.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		username.setBounds(106, 97, 137, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		password.setBounds(106, 128, 137, 20);
		frame.getContentPane().add(password);
		
		JLabel h1 = new JLabel("<html>Account Type: <br< (i.e Facebook, Twitter, etc.)   </html>");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h1.setBounds(52, 58, 118, 28);
		frame.getContentPane().add(h1);
		

		
		
		JButton submitButton = new JButton("Submit!");
		submitButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {	
				
				//create a new instance of the account stored in the active User
				user.a = new Account();
				user.a.setType(accountType.getText());
				user.a.setUsername(username.getText());
				user.a.setPassword(password.getPassword());
				
			 
				//check to see if any of the fields are empty
				if(accountType.getText().isEmpty() || username.getText().isEmpty() || password.getPassword().length == 0 
						 )
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");
				
				//if it passes the first test, then check to see if the account already exists
				else if (Database.accountExists(user) == true)
					JOptionPane.showMessageDialog(null, "Account already exists. Please enter another account.");

				else
					//if it passes all cases then add the user to the database
					addUser(user);
			}
			}
		);
		
		
		submitButton.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		submitButton.setForeground(Color.BLACK);
		submitButton.setBackground(Color.WHITE);
		submitButton.setBounds(227, 190, 75, 24);
		frame.getContentPane().add(submitButton);
		
		h2 = new JLabel("Username:");
		h2.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h2.setBounds(52, 99, 54, 17);
		frame.getContentPane().add(h2);
		
		h3 = new JLabel("Password:");
		h3.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h3.setBounds(52, 132, 47, 13);
		frame.getContentPane().add(h3);
		
		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.WHITE);
		backButton.setForeground(Color.BLACK);
		backButton.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if button is pressed, go back to MainFrame
				frame.setVisible(false);
				MainFrame m = new MainFrame(user);
				m.frame.setVisible(true);	
			}
		});
		backButton.setBounds(74, 189, 75, 24);
		frame.getContentPane().add(backButton);
		
		
		
		
		
	}
	
	//method that adds user to database
	private void addUser(User u) {
		try {
			
			Connection c = Database.getConnection(); //establish sql connection
			DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss"); //format date
			Date date = new Date();
	        String sql = "INSERT INTO Accounts (type,username,password, date, appUser) VALUES(?,?,?,?,?);"; //query
			PreparedStatement ps = c.prepareStatement(sql);
			
			//This prepared statement will store the account type, username, password, date of when the entry was stored and the name of the username of the user
			ps.setString(1,u.a.getType().toUpperCase());
			ps.setString(2, u.a.getUsername());
			ps.setString(3, String.valueOf(Hash.hashPassword(u.a.getPassword()))); //this will encrypt the password using SHA-1 which will be shown in the database
			ps.setString(4, df.format(date));
			ps.setString(5, u.getUsername());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Account was successfully added to the System!");
			
			//closing sql variables to prevent resource leak
			 ps.close();
			 c.close();
			 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			//if it does not add for some reason
			JOptionPane.showMessageDialog(null,"Account could not be added at this moment. Please try again later.");
			
		
	}
}
	}



