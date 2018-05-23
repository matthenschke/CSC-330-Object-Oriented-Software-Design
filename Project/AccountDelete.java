package edu.cuny.csi.csc330.project;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

/*
* This class consists of the Delete Account JFrame
* It also contains the delete account method only used for 
* this class
*
*/
public class AccountDelete {

	protected JFrame frame;
	private JTextField type;
	private JTextField username;
	protected User user;
	

	

	/**
	 * Create the application.
	 */
	//this constructor is used to pass the same User that is already logged in
	public AccountDelete(User user) {
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
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel title = new JLabel("Delete Account",SwingConstants.CENTER);
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(147, 11, 114, 25);
		frame.getContentPane().add(title);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//create an instance of the Account class stored in the active User
				user.a = new Account();
				user.a.setType(type.getText());
				user.a.setUsername(username.getText());
				
				//if one of the fields are empty
				if(type.getText().isEmpty() || username.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in");
				
				//if it passes the first case then check to see if the account actually exists
				else if (Database.accountExists(user) == false)
					JOptionPane.showMessageDialog(null, "Account does not exist. Please enter another account.");

				else
					//if it passes both cases then call the delete account method
					deleteUser(user);
				
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//go back to mainframe if this button was pressed
				MainFrame m = new MainFrame(user);
				frame.setVisible(false);
				m.frame.setVisible(true);
				
			}
		});
		backButton.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		backButton.setBounds(74, 189, 75, 24);
		frame.getContentPane().add(backButton);
		submitButton.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		submitButton.setBounds(227, 190, 75, 24);
		frame.getContentPane().add(submitButton);
		
		JLabel h1 = new JLabel("<html>Account Type: <br< (i.e Facebook, Twitter, etc.)   </html>");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h1.setBounds(52, 58, 118, 28);
		frame.getContentPane().add(h1);
		
		
		type = new JTextField();
		type.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		type.setBounds(176, 66, 137, 20);
		frame.getContentPane().add(type);
		type.setColumns(10);
		
		username = new JTextField();
		username.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		username.setBounds(106, 97, 137, 20);
		frame.getContentPane().add(username);
		type.setColumns(10);
		
		JLabel h2 = new JLabel("Username:");
		h2.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		h2.setBounds(52, 99, 54, 17);
		frame.getContentPane().add(h2);
		
	
	}
	//method that deletes user from database
	public static void deleteUser(User u) {
		try {
			Connection c = Database.getConnection(); //get connection from sqllite database
	        String sql = "DELETE FROM Accounts WHERE appUser = ? and type = ? and username = ?;"; //sql query
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,u.getUsername());
			ps.setString(2,u.a.getType().toUpperCase());
			ps.setString(3, u.a.getUsername());
			
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Account was successfully deleted from the System!");
			
			//closing sql variables to prevent resource leak
			 ps.close();
			 c.close();
			 
			
		}
		catch (Exception e) {
			e.printStackTrace();
            //if the account could not be deleted for what ever reason
			JOptionPane.showMessageDialog(null,"Account could not be deleted at this moment. Please try again later.");
	}
}
}
