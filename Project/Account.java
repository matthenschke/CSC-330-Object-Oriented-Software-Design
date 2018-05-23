package edu.cuny.csi.csc330.project;

/*
 * This is the account class for which the User class will have an instance of when it manipulates
 * one of his/her accounts that are stored (Delete, Update) etc and when he/she wants to add a new account to 
 * the database
 */

public class Account {
	
	//encapsulation
    private String type;
    private String username;
    private char [] password;
    
 

    //the setters and getters
	
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
}
