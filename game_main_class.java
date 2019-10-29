
package Poker_game;
import java.util.*;
public class game_main_class {
	
	public game_main_class() {
		
	}
	
	public static void main(String [] args) {
		Table gameTable= new Table();

		//gameTable.checkTopCard();
		
		
		  for (int i = 0; i< gameTable.getPlayerCount(); i++) 
		  { 
			  Vector<String> twoCards = gameTable.getPlayerCards(i); 
			  System.out.println(twoCards);
		 
		
		/*
		 * 
		 * 
		 * gameTable.set(); gameTable.dealHands(); gameTable.dealFlop();
		 * gameTable.dealTurn(); gameTable.dealRiver();
		 */
	}
}
}