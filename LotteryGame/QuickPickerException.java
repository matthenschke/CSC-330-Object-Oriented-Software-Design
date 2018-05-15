package edu.cuny.csi.csc330.lab6;
import java.util.* ;

public class QuickPickerException extends Exception {

	
	
	  // static publicly defined error codes 
	  public static int	NO_PROPERTY_FILE = 0; 
	  public static int	MISSING_PROPERTY = 1; 
	
	    
	  // static publicly defined exception messages  
	  public static String[]  MESSAGE = { 
	  		"The specified game does not exist", "A required property is missing from the game"
	  		
	  } ;
	
		protected int code;  // have this exception carry a code as well as 
		
		// Constructors ... 
	    private  QuickPickerException() { 
	    	super();
	    } 
	    
	    // with thrower  message 
	    public QuickPickerException(String m) { 
	    	super(m); 
	    } 
	    
	    // with thrower message and code 
	   
	    public QuickPickerException(String message, int code) { 
	    	super(message);
	    	this.code = code;
	    } 
	    
		public int getCode() { 
			return code;
		}
		

	    @Override
		public String toString() {
			return "QuickPickerException [code=" + code + ", toString()="
					+ super.toString() + "]\n" + MESSAGE[code] ;
		}
	    
}
