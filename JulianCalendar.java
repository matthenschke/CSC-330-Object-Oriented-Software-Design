package edu.cuny.csi.csc330.lab1;

/**
 * Generates a non-leap year perpetual Julian Calendar 
 * @author Matthew Henschke
 *
 */
public class JulianCalendar {
	
	// Max number of Days in a month 
	static private  final int MAX_DAY = 31; 
	
	// abbreviated Month names 
	static private  final String [] MONTH_NAMES = {
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
	
	// day length of each month 
	static private final  int [] MONTH_SIZES = {
		31, 28, 31,30,31, 30, 31, 31, 30, 31, 30, 31
	};

	/**
	 * display The "DAY" Column Heading 
	 */
	static private void displayDayHeading() {
		System.out.printf("%5s", "Day");
		
	}
	
	/**
	 * display Month names between Day .... Day
	 */
	static private void displayHeading() {
		displayDayHeading(); 
		
		for(int i = 0 ; i < MONTH_NAMES.length ; ++i )  {
			System.out.printf("%5s", MONTH_NAMES[i]);
		}
		
		displayDayHeading(); 
	}
	

	static public void display() {
		displayHeading(); // display heading 
		System.out.print("\n");
		int counter = 1;
		for (int i = 1; i <= MAX_DAY; i++) {
			System.out.printf( "%5s",i);
			int update = i;
		for(int j = 0; j < MONTH_NAMES.length; j++)
{           if (i > MONTH_SIZES[j]) {
	          System.out.printf("%5s", "000");
	          update = update + MONTH_SIZES[j];
	          continue;
}
		    String s = String.format("%03d", update);
		    System.out.printf( "%5s", s);
		    update = update + MONTH_SIZES[j];
		}
		System.out.printf("%5d",i);  
		    System.out.print("\n");
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		display(); 
	}
}