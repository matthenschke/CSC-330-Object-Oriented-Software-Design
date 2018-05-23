package edu.cuny.csi.csc330.project;

/*
 * This is the User class that stores all the information of the current user in the application, 
 * including its accounts
 */

public class User {
  private String username;
  private char[] password;
  protected Account a; //account instance that will be used to manipulate the user's accounts in each of the methods


public void setUsername(String username) {
	this.username = username;
	
}
public String getUsername() {
	return username;
}
public void setPassword(char [] password) {
	this.password = password;
}
public char [] getPassword() {
	return password;
	
}
}