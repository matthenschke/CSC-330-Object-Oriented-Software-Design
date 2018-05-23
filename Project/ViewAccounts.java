package edu.cuny.csi.csc330.project;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * This class consists of the View Account GUI
 * This GUI only shows the active users accounts 
 * and shows its accounts using a JTable
 */
public class ViewAccounts {

	protected JFrame frame;
	protected User user;
	private JTable table;

	

	/**
	 * Create the application.
	 */
	public ViewAccounts(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 700 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JLabel title = new JLabel("All Accounts");
		title.setBounds(217, 0, 132, 47);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		
		
		
		table = new JTable();
		table.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Account Type", "Username", "Password", "Date Added"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(86);
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.setBounds(1,200, 450, 1);
		frame.getContentPane().add(table);
		table.setEnabled(false);
		frame.getContentPane().add(title);
		frame.getContentPane().setLayout(null);
		JScrollPane js = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setBounds(0, 45, 584, 309);
		frame.getContentPane().add(js);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//go back to MainFrame
				frame.setVisible(false);
				MainFrame m = new MainFrame(user);
				m.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 10));
		btnNewButton.setBounds(260, 500, 89, 23);
		frame.getContentPane().add(btnNewButton);
		showTable(); //display table
	}
	
	/*
	 * this method adds all the rows to this table, only the Account Type, Username, Password, and
	 *  Date of when the account was added is shown unlike the methods used in the ViewAll GUI that
	 *  shows all data from each table
	 * 
	 */
	public void showTable() {
			try {
					
					Connection c = Database.getConnection(); //get sql connection
					
					//prepare sql statement that checks if user already exists in the system
					String sql = "select type,username,password,date from Accounts where appUser = ?";  
					PreparedStatement pstmt  = c.prepareStatement(sql);
					pstmt.setString(1,user.getUsername());
					
					//create a result set that will hold the results when the prepared statement executes
			        ResultSet rs = null;          
			        rs = pstmt.executeQuery();
			        while(table.getRowCount() > 0) 
			        {
			            ((DefaultTableModel) table.getModel()).removeRow(0);
			        }
			        int columns = rs.getMetaData().getColumnCount();
			        while(rs.next())
			        {  
			            Object[] row = new Object[columns];
			            for (int i = 1; i <= columns; i++)
			            {  
			                row[i - 1] = rs.getObject(i);
			            }
			            ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
			        }

			        rs.close();
			        pstmt.close();
			        c.close();
				}
				catch(Exception e) {
					e.printStackTrace();
	}
		
	}
}

