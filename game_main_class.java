package Poker_game;
import java.util.*;
public class game_main_class {
	public static Table gameTable;
	
	public static void game_main_class() {
		
	}
	
	public static void main(String [] args) {
		Cards.createDeck();
		Cards.printAllCards();
		/*
		 * gameTable.set(); gameTable.dealHands(); gameTable.dealFlop();
		 * gameTable.dealTurn(); gameTable.dealRiver();
		 */
	}
}
