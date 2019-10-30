package Poker_game;

import java.util.*;

public class Dealer {
	public Dealer(){}
	/*public static void dealHoleCards(Stack<String> cards, Player[] players) {
		for (int i = 0; i < playerCount; i++) {
			String tempCard = cards.dealCard();
			players[i].setHoleCards(tempCard);
			tempCard = cards.dealCard();
			players[i].setHoleCards(tempCard);
		}
	}
	*/
	//deal card returns card object
	public static Card dealCard(Stack<Card> cardDeck) {
		Card dealCard = cardDeck.peek();
		cardDeck.pop();
		return dealCard;
	}
	
	public static void shuffle(Stack<Card> cardDeck) {
		Collections.shuffle(cardDeck);
	}
	//methods to do what the dealer would do
	//deal holecards
	//deal flop
	//deal turn
	//deal river
	public static void resetDeck(CardDeck deck) {
		// TODO Auto-generated method stub
		deck.resetDeck();
	}
}
