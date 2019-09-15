package Poker_game;
import java.util.*;
import java.util.stream.IntStream;
public class Cards {
	private static Stack<Integer> cardDeck = new Stack<Integer>();
	public static void Cards(){
		//to create shffled deck
		
		//randomly pick a 'card' form arr of ints usig rand
		//card at arr.length - 1 gets indexed at index where card was just picked
		//arr gets shrunk
	};
	public static void createDeck() {
		//create arr of 52 ints
				Random rand = new Random();
				/*
				 * int [] tempCards= IntStream.range(1, 53).toArray(); 
				 * 
				 * 
				 */
				boolean [] cards = new boolean [52];
				int cardsLeft = 52;
				int currCard;
				final int DECK_SIZE = 52;
				System.out.println(cards.length);
				while (cardsLeft > 0) {
				currCard = rand.nextInt(DECK_SIZE);
				if (!cards[currCard]) {
				cards[currCard] = true;
				//we add 1 to currCard so that card values are 1-52 instead of 0-51
				cardDeck.push(currCard+1);
				cardsLeft--;
				}
				}
	}
	public static void printAllCards() {
		while(!cardDeck.isEmpty()) {
			System.out.printf(cardDeck.peek()+" ");
			cardDeck.pop();
		}
	}
	public static void getCardVal() {};
	public static void dealHoleCards() {};
	public static void dealFlop() {};
	public static void dealTurn() {};
	public static void dealRiver() {};
	
}
