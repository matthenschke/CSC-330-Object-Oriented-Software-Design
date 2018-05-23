package edu.cuny.csi.csc330.project;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/*
 * This class consists of the GUI for the Update Password method 
 * and the method itself
 */
public class UpdatePassword {

	protected JFrame frame;
	protected User user;
	private JTextField accountType;
	private JTextField username;
	private JPasswordField password;
	private JLabel h2;
	private JLabel h3;

	
	/**
	 * Create the application.
	 */
	//this User parameter is the active user in the application
	public UpdatePassword(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Update Password");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(125, 11, 136, 25);
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
		password.setBounds(125, 128, 137, 20);
		frame.getContentPane().add(password);
		
		JLabel h1 = new JLabel("<html>Account Type: <br< (i.e Facebook, Twitter, etc.)   </html>");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h1.setBounds(52, 58, 118, 28);
		frame.getContentPane().add(h1);
		JButton submitButton = new JButton("Submit!");
		submitButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {	
				//create new instance of an Account that will be verified before making any modifications
				user.a = new Account();
				user.a.setType(accountType.getText());
				user.a.setUsername(username.getText());
				user.a.setPassword(password.getPassword());
				
			 
				//check to see if any of the fields are empty
				if(accountType.getText().isEmpty() || username.getText().isEmpty() || password.getPassword().length == 0 
						 )
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");
			
				//check to see if the account actually exists
				else if (Database.accountExists(user) == false)
					
					JOptionPane.showMessageDialog(null, "Account does not exist. Please enter another account.");
                else
                	//if it passes all test cases then update the password
				 updatePassword(user);
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
		
		h3 = new JLabel("New Password:");
		h3.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h3.setBounds(52, 132, 78, 16);
		frame.getContentPane().add(h3);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go back to MainFrame if pressed
				frame.setVisible(false);
				MainFrame m = new MainFrame(user);
				m.frame.setVisible(true);	
			}
		});
		btnBack.setBounds(74, 189, 75, 24);
		frame.getContentPane().add(btnBack);
	}
	
	//method that updates the password
	public static void updatePassword(User u) {
		try {
			Connection c = Database.getConnection();
	        String sql = "UPDATE Accounts SET password = ? WHERE appUser = ? and type = ? and username = ?;"; // sql query
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, String.valueOf(Hash.hashPassword(u.a.getPassword()))); //Encrypt password
			ps.setString(2, u.getUsername());
			ps.setString(3,u.a.getType().toUpperCase());
			ps.setString(4, u.a.getUsername());
			
			
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Password was successfully updated.");
			
			//closing sql variables to prevent resource leak
			 ps.close();
			 c.close();
			 
			
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Password could not be updated at this time. Please try again later.");
			
		
	}
		
	}
}
