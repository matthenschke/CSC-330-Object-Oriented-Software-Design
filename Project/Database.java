package edu.cuny.csi.csc330.project;
import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/*
 * This class contains the methods that are used
 * in the GUIs that rely on the data from the database
 */
public class Database {
  
	//method that establishes SQLlite connection
	protected static Connection getConnection() {
	try {
			Class.forName("org.sqlite.JDBC"); 
			Connection myCon = DriverManager.getConnection("jdbc:sqlite:Project.db"); 
			return myCon; //return connection
			
		} catch (Exception e) {
			e.printStackTrace();

		}
    return null; //returns null if no connection was established

	}
		
    //method used to check to see if the User's credentials exist, used in the Login GUI
	protected static boolean verifyUser(User u) {
		Boolean exists = true; //assume it is true
		try {
			
			Connection c = getConnection(); //get sql connection
			
			//prepare sql statement that checks if user already exists in the system
			String sql = "select * from Users where appUser = ? and appPass = ?";  
			PreparedStatement pstmt  = c.prepareStatement(sql);
			pstmt.setString(1,u.getUsername());
			pstmt.setString(2, String.valueOf(Hash.hashPassword(u.getPassword())));
			
			//create a result set that will hold the results when the prepared statement executes
            ResultSet result = null;          
            result = pstmt.executeQuery();
            
            //if statement that checks if the resultset has any rows 
            if(!result.isBeforeFirst()){ 
                exists = false;
            }
            
            //closing sql variables to prevent resource leak
            pstmt.close();
            result.close();
            c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
			
		}
		if (exists == true)
			return true;
		
		return false;
		
		
	}
	/*
	 * method used in the "CreateUser" GUI that checks to see if the User already exists in database
	 * Users cannot have the same username as current Users
	 */
	protected static boolean userExists(User u) {
		Boolean exists = true; //assume it is true
		try {
			
			Connection c = getConnection(); //get sql connection
			
		    //prepare sql statement that checks if user already exists in the system
			String sql = "select * from Users where appUser = ?";  
			PreparedStatement pstmt  = c.prepareStatement(sql);
			pstmt.setString(1,u.getUsername());
			
			
			//create a result set that will hold the results when the prepared statement executes
            ResultSet result = null;          
            result = pstmt.executeQuery();
            
            //if statement that checks if the resultset has any rows 
            if(!result.isBeforeFirst()){ 
                exists = false;
            }
            
            //closing sql variables to prevent resource leak
            pstmt.close();
            result.close();
            c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
			
		}
		if (exists == true)
			return true;
		
		return false;
		
		
	}
	
	
	//method that checks to see if the account credentials already exist
	protected static boolean accountExists(User u) {
		Boolean exists = true; //assume it is true
	try {
		
		Connection c = getConnection(); //get sql connection
		
		//prepare sql statement that checks if user already exists in the system
		String sql = "select * from Accounts where appUser = ? and type = ? and username = ?";  
		PreparedStatement pstmt  = c.prepareStatement(sql);
		pstmt.setString(1,u.getUsername());
		pstmt.setString(2, u.a.getType().toUpperCase());
		pstmt.setString(3,u.a.getUsername());
		
		//create a result set that will hold the results when the prepared statement executes
        ResultSet result = null;          
        result = pstmt.executeQuery();
        
        //if statement that checks if the resultset has any rows 
        if(!result.isBeforeFirst()){ 
            exists = false;
        }
        
        //closing sql variables to prevent resource leak
        pstmt.close();
        result.close();
        c.close();
	}
	catch(Exception e) {
		e.printStackTrace();
		
		
	}
	if (exists == true)
		return true;
	
	return false;
	}
	
	/*
	 * These last two methods are used in the "ViewAll" GUI 
	 * that display all of the information in the Database tables which are
	 * shown in a JTable
	 */
	protected static void showAccounts(JTable table, User user) {
		try {
				
				Connection c = Database.getConnection(); //get sql connection
				
				//prepare sql statement that checks if user already exists in the system
				String sql = "select * from Accounts";  
				PreparedStatement pstmt  = c.prepareStatement(sql);
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
		protected static void showUsers(JTable table, User user) {
			try {
					
					Connection c = Database.getConnection(); //get sql connection
					
					//prepare sql statement that checks if user already exists in the system
					String sql = "select * from Users";  
					PreparedStatement pstmt  = c.prepareStatement(sql);
				
					
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

