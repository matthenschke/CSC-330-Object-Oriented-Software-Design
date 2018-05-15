package edu.cuny.csi.csc330.lab6;
import java.util.*;
import edu.cuny.csi.csc330.lib.*;
public class QuickPicker {
	
	

	
		
		// constants  specific to current game - BUT NOT ALL GAMES 
		public final static int DEFAULT_GAME_COUNT = 1; 
		private final static String GAME_NAME = "Sweet Million"; 
		private final static int SELECTION_POOL_SIZE1 = 40; 
		private final static int SELECTION_POOL_SIZE2 = 0; 
		private final static int SELECTION_COUNT1 = 6;
		private final static int SELECTION_COUNT2 = 0; 
		private final static String STORE_NAME = "S.I. Corner Deli";
		
		
		// non-constants specific to current game
		private int gamesCount;
		private int poolSize1;
		private int poolSize2;
		private int selectionCount1;
		private int selectionCount2;
		private String storename;
		private String vendor;
		private String gameName;
		private ResourceBundle bundle;
		private Randomizer randomizer; 

     //all properties are set to default values from the SweetMillion Game
		public QuickPicker() {
			randomizer = new Randomizer();
			gamesCount = DEFAULT_GAME_COUNT;
			gameName = GAME_NAME;
			poolSize1 = SELECTION_POOL_SIZE1;
			poolSize2 = SELECTION_POOL_SIZE2;
			selectionCount1 = SELECTION_COUNT1;
			selectionCount2 = SELECTION_COUNT2;
			vendor = STORE_NAME;
		}
		
	
		public QuickPicker(String game) throws QuickPickerException {
		
			try { 
				
				bundle = ResourceBundle.getBundle(game);
			}
			catch (Exception e) {
				throw new QuickPickerException(QuickPickerException.MESSAGE[0], QuickPickerException.NO_PROPERTY_FILE);
			}
				randomizer = new Randomizer();
				gamesCount = DEFAULT_GAME_COUNT;
				getKeys();	
		}
			
		
		
		
		public QuickPicker(String game, int games) throws QuickPickerException {
			try { 
				bundle = ResourceBundle.getBundle(game);
			
			}
			catch (Exception e) {
				throw new QuickPickerException(QuickPickerException.MESSAGE[0], QuickPickerException.NO_PROPERTY_FILE);
			}
				randomizer = new Randomizer();
				gamesCount = games;
				getKeys();	
			   
			  
				   
			
			
		}
       private void getKeys() throws QuickPickerException {
    	   //declaration of variables that would hold strings from the files  
    	   String s;
    	   String [] parts;
    	   
    	   //if statements to check if the specific keys exist
    	   if (bundle.containsKey("GameName")) 
    	    	  gameName = bundle.getString("GameName");
    	    	  
    	      else 
    	    	  throw new QuickPickerException("Missing Property", QuickPickerException.MISSING_PROPERTY);
    		
			if (bundle.containsKey("Pool1")) {
				
    	       s = bundle.getString("Pool1");
    	      parts = s.split("/");
    	      selectionCount1 = Integer.parseInt(parts[0]);
    	      poolSize1 = Integer.parseInt(parts[1]);
    	      
			}
    	    	  
    	      else 
    	    	  throw new QuickPickerException("Missing Property", QuickPickerException.MISSING_PROPERTY);
    	      
			if (bundle.containsKey("Pool2")) {
				 s = bundle.getString("Pool2");
	    	      parts = s.split("/");
	    	      selectionCount2 = Integer.parseInt(parts[0]);
	    	      poolSize2 = Integer.parseInt(parts[1]);
	    	      
				
			}
    	    	  
    	      else 
    	    	  throw new QuickPickerException("Missing Property", QuickPickerException.MISSING_PROPERTY);
    	      if (bundle.containsKey("Vendor")) 
    	    	  vendor = bundle.getString("Vendor");
    	    	  
    	      else 
    	    	  throw new QuickPickerException("Missing Property", QuickPickerException.MISSING_PROPERTY);          
       }
	
		
		
		public void displayTicket() {
			
			/**
			 * display heading 
			 * 
			 * for i in gameCount 
			 * 		generate selectionCount number of unique random selections in ascending order 
			 * 
			 * display footer 
			 */
			
			System.out.print("\n");
			
			// display ticket heading 
			displayHeading(); 
			
			
			
			
			 // Display selected numbers 
			 
			for (int i = 0; i < gamesCount; i++)
			{
				 int [] pool1 = numGenerator(selectionCount1, poolSize1);
				 int [] pool2 = numGenerator(selectionCount2, poolSize2);
				 displayGame(i+1, pool1, pool2);
				 
				
			}

			
			
			// display ticket footer 
			displayFooter(); 
			
			
		}
		protected int [] numGenerator(int length, int pool ) {
			
			//loop to go through pool1
			int [] randNum = new int[length];
			for (int i = 0; i < randNum.length; i++) {
				int temp = this.randomizer.generateInt(i, pool);
				
				boolean dup = isDuple(randNum, temp);
				
				//loop to make sure generated number is unique
				while (dup) {
					temp = this.randomizer.generateInt(i, pool);
					dup = isDuple(randNum, temp);
				}
				
			randNum[i] = temp;
			}
			
			Arrays.sort(randNum);
			    return randNum;
		}
		
		//checks to see if there is duplicate number in each game
		protected boolean isDuple(int [] num, int temp) {
			for(int value: num) 
				if (value == temp)
					return true;
			
			return false;
			
		}
		protected void displayGame(int index, int [] num, int [] num2) {
			System.out.printf(" (%2d) ", index);
			for (int i = 0; i < num.length; i++)
				System.out.printf("%02d ", num[i]);
			for (int j = 0; j < num2.length; j++)
				System.out.printf("%02d ", num2[j]);
			System.out.print("\n");
				
				
		}
		
		protected void displayHeading() {
			Date date = new Date();
		 System.out.println("----------------------------------");
		 System.out.println("------ " + gameName + " ------ ");
		 System.out.println(date.toString());
		 System.out.print("\n");
			
		}
		
		protected void displayFooter() {
			 System.out.print("\n");
			 System.out.println("--- (c) " + vendor  + " ----");
			 System.out.println("----------------------------------");
			
		}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// takes an optional command line parameter specifying number of QP games to be generated
					//  By default, generate 1  
					int numberOfGames  = 1; 
					if(args.length > 0) {
						numberOfGames = Integer.parseInt(args[0]);
					}
					try {
						QuickPicker game = new QuickPicker("MegaMillions", numberOfGames);
						game.displayTicket(); 
					}
					catch(QuickPickerException re) {
						
						System.err.println(re);
					}
					
						
					
					
					
					
					
					
					 
					

	}

}
