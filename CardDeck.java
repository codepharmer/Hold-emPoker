package Poker_game;
import java.util.*;
import java.util.stream.IntStream;
public class CardDeck  {
	private Stack<Card> cardDeck;
	private final Integer DECK_SIZE = 52;
	public CardDeck (){
		createDeck();
	};
	public CardDeck (Stack<Card> cardDeckIn){
		cardDeck = cardDeckIn;
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
				Stack<Card> deckMaker = new Stack<Card>();
				for(Integer i= 0; i < DECK_SIZE; i++)
					deckMaker.push(new Card(i));
				cardDeck = deckMaker;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cardDeck);
	}
	public Stack<Card> getDeck() {
		return cardDeck;
	}
	
	public void setDeck(Stack<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}
	public void checkCardDeck (int nthCard) {
			System.out.println(cardDeck.get(nthCard)+" ");
	}
	public void resetDeck() {
		createDeck();
	}
}
