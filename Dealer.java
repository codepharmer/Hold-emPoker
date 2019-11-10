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
	public static Card dealCard(CardDeck cardDeck) {
		Card card = cardDeck.getDeck().peek();
		cardDeck.getDeck().pop();
		return card;
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
	public static Card[] dealFlop(CardDeck cardDeck) {
		Card [] flopCards = new Card[3];
		for(int i = 0; i < 3; i++) {
			flopCards[i] = cardDeck.getDeck().peek();
			cardDeck.getDeck().pop();
		}
		return flopCards;
	}
	public static Card dealTurn(CardDeck cardDeck) {
		return dealCard(cardDeck);
	}
	public static Card dealRiver(CardDeck cardDeck) {
		return dealCard(cardDeck);
	}
}
