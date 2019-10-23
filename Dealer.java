package Poker_game;

import java.util.*;

public class Dealer {
	public Dealer(){}
	
	public static void dealPlayerCards(Stack<String> cards, Player[] players) {
		for (int i = 0; i < playerCount; i++) {
			String tempCard = cards.dealCard();
			players[i].setHoleCards(tempCard);
			tempCard = cards.dealCard();
			players[i].setHoleCards(tempCard);
		}
	}
	//deal card returns card object
	//methods to do what the dealer would do
	//deal holecards
	//deal flop
	//deal turn
	//deal river
}
