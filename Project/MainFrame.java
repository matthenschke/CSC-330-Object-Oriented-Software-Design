package edu.cuny.csi.csc330.project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * The "MainFrame" of the application
 * that can be only accessed if the User is a valid user
 * The GUI consists of buttons that will take you to
 * the different method GUI's depending on what the User
 * wants to do
 */


public class MainFrame {

	protected JFrame frame;
	protected User user;
    

	

	/**
	 * Create the application.
	 */
	

	/**
	 * Initialize the contents of the frame.
	 */
	protected MainFrame(User user) {
		this.user = user;
		initialize();
	}
	protected void initialize() {
		
		frame = new JFrame();
	
		frame.setBounds(250, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		JLabel title;
		
		//mhenschke is admin account
		if (user.getUsername().equals("mhenschke")) {
		 title = new JLabel("Welcome Admin", SwingConstants.CENTER);
		}
		else
	    title = new JLabel("Welcome " + user.getUsername(), SwingConstants.CENTER);
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		title.setBounds(104, 11, 341, 38);
		
		frame.getContentPane().add(title);
		
		JButton updatePass = new JButton("Update Password");
		
		updatePass.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		updatePass.setBounds(63, 254, 117, 30);
		frame.getContentPane().add(updatePass);
		updatePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// go to the update password gui
				frame.setVisible(false);
				UpdatePassword u = new UpdatePassword(user);
				u.frame.setVisible(true);
				
				
			}
		});
		
		JButton viewData = new JButton("View All Info");
		viewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check to see if it is admin 
				if (user.getUsername().equals("mhenschke")) {
				frame.setVisible(false);
				ViewAll v = new ViewAll(user);
				v.frame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Admin Access Only!!!");
			}
		});
		viewData.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		viewData.setBounds(223, 254, 117, 30);
		frame.getContentPane().add(viewData);
		
		JButton logOut = new JButton("Log Out");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//log out 
				int result = JOptionPane.showConfirmDialog(null,
				        "Are you sure you want to quit?",
				        "Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.YES_OPTION) {
				//if the user wants to quit, then the instance becomes null, making it eligible for garbage collection
				user = null;
				
				//go back to the login GUI form
				frame.setVisible(false);
				Login l = new Login();
				l.frame.setVisible(true);
				}
			}
		});
		logOut.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		logOut.setBounds(378, 254, 117, 30);
		frame.getContentPane().add(logOut);
		
		JButton AddAccount = new JButton("Add Account");
		AddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go to the add account GUI
				frame.setVisible(false);
				AccountAdd a = new AccountAdd(user);
				a.frame.setVisible(true);
			}
		});
		AddAccount.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		AddAccount.setBounds(63, 190, 117, 30);
		frame.getContentPane().add(AddAccount);
		
		JButton delAccount = new JButton("Delete Account");
		delAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go to the delete account GUI
				frame.setVisible(false);
				AccountDelete d = new AccountDelete(user);
				d.frame.setVisible(true);
			}
		});
		delAccount.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		delAccount.setBounds(223, 190, 117, 30);
		frame.getContentPane().add(delAccount);
		
		JButton viewAccounts = new JButton("View Accounts");
		viewAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go to the View Accounts GUI
				frame.setVisible(false);
				ViewAccounts v = new ViewAccounts(user);
				v.frame.setVisible(true);
				
			}
		});
		viewAccounts.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		viewAccounts.setBounds(378, 190, 117, 30);
		frame.getContentPane().add(viewAccounts);
		frame.setVisible(true);
	
	}
}
