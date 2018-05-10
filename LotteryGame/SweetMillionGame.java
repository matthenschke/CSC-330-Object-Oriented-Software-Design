/**
 * LAB 1 - Sweetmillion Lotto QuickOicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.util.*;
import edu.cuny.csi.csc330.lib.*;

public class SweetMillionGame {
	
	// constants  specific to current game - BUT NOT ALL GAMES 
	public final static int DEFAULT_GAME_COUNT = 1; 
	private final static String GAME_NAME = "Sweet Million"; 
	private final static int SELECTION_POOL_SIZE = 40; 
	private final static int SELECTION_COUNT = 6; 
	
	private Randomizer randomizer; 


	public SweetMillionGame() {
		init(DEFAULT_GAME_COUNT); 
	}
	
	public SweetMillionGame(int games) {
		init(games); 
	}
  

	private void init(int games) {
		randomizer = new Randomizer(); 
		/**
		 * 
		 * Now what ... START FROM HERE 
		 * What additional methods do you need?
		 * What additional data properties/members do you need? 
		 */
		
		
		
	}
	


	/**
	 * 
	 */
	public void displayTicket() {
		
		/**
		 * display heading 
		 * 
		 * for i in gameCount 
		 * 		generate selectionCount number of unique random selections in ascending order 
		 * 
		 * display footer 
		 */
		
		
		
		// display ticket heading 
		displayHeading(); 
		
		
		
		/**
		 * Display selected numbers 
		 */

		
		
		// display ticket footer 
		displayFooter(); 
		
		return;
	}
	
	protected void displayHeading() {
		Date date = new Date();
	 System.out.println("---------------------------");
	 System.out.println("------ Sweet Millon ------ ");
	 System.out.println(date.toString());
		
	}
	
	protected void displayFooter() {
		 
		
	}
	
 
  

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be generated
		//  By default, generate 1  
		int numberOfGames  = 1; 
		if(args.length > 0) {
			numberOfGames = Integer.parseInt(args[0]);
		}
		
		SweetMillionGame game = new SweetMillionGame(numberOfGames);
		// now what 
		 
		game.displayTicket(); 
		
		System.out.println("Leaving ...");

	}

}
