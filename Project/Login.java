package edu.cuny.csi.csc330.project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * This is the base GUI for the entire project; the only class that is runnable
 * since the user needs to either log in using his/her credentials or create a new account
 * that does not already exist in order to have access to the applications features
 * 
 * The project relies heavily on the use of the SQLlite database, Project.db that has two tables, Users and Accounts.
 *
 */

public class Login {

	protected JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	//No parameter since it is just the login GUI
	public Login() {
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
		
		JLabel title = new JLabel("Account Management System Login");
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(82, 24, 266, 21);
		frame.getContentPane().add(title);
		
		username = new JTextField();
		username.setBounds(182, 75, 86, 18);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JButton createButton = new JButton("Create Account");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if button is clicked, transfer to the "CreateUser" GUI
				frame.setVisible(false);
				CreateUser c = new CreateUser();
				c.frame.setVisible(true);
			}
		});
		createButton.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		createButton.setBounds(57, 186, 103, 23);
		frame.getContentPane().add(createButton);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//create an instance of a User which needs to be validated
				User u = new User();
				u.setUsername(username.getText());
				u.setPassword(password.getPassword());
				
				//check to see if any of the fields are empty
				if(username.getText().isEmpty() || password.getPassword().length == 0) 
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");
				
				//if it passes the first case check to see if the same username and password stored in the instance are found in the database
				else if (Database.verifyUser(u) == false) 
				JOptionPane.showMessageDialog(null, "Invalid Username or Password. ");
				else {
					//if it does exist in the System, then transfer to the "MainFrame" GUI where it can manipulate its accounts
					frame.setVisible(false);
					MainFrame m = new MainFrame(u);
				    m.frame.setVisible(true);
				}
				   
			}
		});
		submitButton.setBounds(263, 186, 89, 23);
		frame.getContentPane().add(submitButton);
		
		password = new JPasswordField();
		password.setBounds(182, 126, 86, 20);
		frame.getContentPane().add(password);
		
		JLabel h1 = new JLabel("Username:");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		h1.setBounds(120, 78, 66, 14);
		frame.getContentPane().add(h1);
		
		JLabel h2 = new JLabel("Password:");
		h2.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		h2.setBounds(120, 130, 57, 14);
		frame.add(h2);
	}
}
