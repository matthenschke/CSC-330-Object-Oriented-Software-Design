package edu.cuny.csi.csc330.project;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/*
 * This method is only accessible by the Super-Admin of the application
 */
public class ViewAll {

	protected JFrame frame;
	protected User user;
	private JTable AccountTable;
	private JTable UserTable;



	/**
	 * Create the application.
	 */
	public ViewAll(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		AccountTable = new JTable();
		AccountTable.setEnabled(false);
		AccountTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Account Type", "Username", "Password", "Date Added", "appUser"
			}
		));
		
		AccountTable.setFillsViewportHeight(true);
		AccountTable.setBounds(10, 60, 400, 450);
		frame.getContentPane().add(AccountTable);
		
		JScrollPane js1 = new JScrollPane(AccountTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js1.setBounds(10, 102, 500, 357);
		frame.getContentPane().add(js1);
		
		JLabel h1 = new JLabel("Accounts");
		h1.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		h1.setBounds(210, 50, 86, 28);
		frame.getContentPane().add(h1);
		
		JLabel h2 = new JLabel("User Accounts");
		h2.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		h2.setBounds(800, 50, 125, 28);
		frame.getContentPane().add(h2);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go back to MainFrame
				frame.setVisible(false);
				MainFrame m = new MainFrame(user);
				m.frame.setVisible(true);
			
			}
		});
		btnNewButton.setBounds(525, 550, 107, 42);
		frame.getContentPane().add(btnNewButton);
		
		UserTable = new JTable();
		UserTable.setEnabled(false);
		UserTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password", "Date Created"
			}
		));
		UserTable.setBounds(650, 102, 600, 357);
		UserTable.setFillsViewportHeight(true);
		frame.getContentPane().add(UserTable);
		JScrollPane js2 = new JScrollPane(UserTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js2.setBounds(650, 102, 450, 357);
		frame.getContentPane().add(js2);
		

		
		Database.showAccounts(AccountTable, user); //display Accounts table
		Database.showUsers(UserTable, user); //display Users table
		

	}
	
}
