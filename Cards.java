package Poker_game;
import java.util.*;
import java.util.stream.IntStream;
public class Cards {
	private Stack<Card> cardDeck;
	public Cards(){
		createDeck();
		shuffleDeck();
	};
	public String dealCard() {
		String nextCard = cardDeck.peek().toString();
		cardDeck.pop();
		return nextCard;
	}
	public String peek() {
		return cardDeck.peek().toString();
	}
	private void createDeck() {
				final Integer DECK_SIZE = 52;
				Stack<Card> deckMaker = new Stack<Card>();
				for(Integer i= 0; i < DECK_SIZE; i++)
					deckMaker.push(new Card(i));
				cardDeck = deckMaker;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cardDeck);
	}
	public void printAllCards() {
		while(!cardDeck.isEmpty()) {
			System.out.println(cardDeck.peek()+" ");
			cardDeck.pop();
		}
	}
	
}
