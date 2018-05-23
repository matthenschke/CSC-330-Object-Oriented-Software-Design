package edu.cuny.csi.csc330.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
 * This class has the hashMethod that will be used in the other classes that encrypt the users' and accounts' passwords
 */

public class Hash {
    public static char [] hashPassword(char [] password) throws NoSuchAlgorithmException{
    	String pass = new String(password);
        MessageDigest md = MessageDigest.getInstance("SHA");
        byte[] b = md.digest(pass.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b1 : b){
            String k = Integer.toHexString(b1 & 0xff).toString();
            if(k.length() == 1){
                k = "0"+ k;
           }
            sb.append(k);
            
        }
        String s = sb.toString();
        char [] result = s.toCharArray();
        return result;
        
    }

 
}
