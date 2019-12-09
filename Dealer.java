package edu.cuny.csi.csc330.holdemPoker;

import java.util.*;

public class Dealer {
	private CardDeck cardDeck;
	public Dealer(){}
	
	public Dealer(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}
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
	public Card dealCard() {
		Card card = cardDeck.getDeck().peek();
		cardDeck.getDeck().pop();
		return card;
	}
	
	public void printSmallBig(Player [] players) {
		int smallBlindIndex = -2;

		int n = 0;
		while (smallBlindIndex == -2)
			if (players[n % players.length].isSmall() && !players[n % players.length].isFolded())
				smallBlindIndex = (n % players.length);
			else
				n++;
		System.out.printf("Small blind is player %s %n Big blind is player %s %n",
				players[smallBlindIndex].getName(), players[(smallBlindIndex + 1) % players.length].getName());;
	}
	
	public CardDeck shuffleCards() {
		Collections.shuffle(cardDeck.getDeck());
		return cardDeck;
	}
	//methods to do what the dealer would do
	//deal holecards
	//deal flop
	//deal turn
	//deal river
	public CardDeck resetDeck() {
		cardDeck.resetDeck();
		return cardDeck;
	}
	public Card[] dealFlop() {
		Card [] flopCards = new Card[3];
		for(int i = 0; i < 3; i++) {
			flopCards[i] = cardDeck.getDeck().peek();
			cardDeck.getDeck().pop();
		}
		return flopCards;
	}
	public Card dealTurn() {
		return dealCard();
	}
	public Card dealRiver() {
		return dealCard();
	}

	public boolean nextHandPrompt() {
		System.out.println("Continue playing? Type yes or 'y' for yes");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		if (userInput.contains("yes") || userInput.contains("y"))
			return true;
		else return false;
	}
}
