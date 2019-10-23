package Poker_game;
public class Card{
	private int cardNum;
	private int value = 0;
	private String suite = "";
	String cardName;
	public Card(Integer cardNumIn){
		cardNum = cardNumIn.intValue();
		assignVal();
		assignSuite();
	}
	private void assignVal() {
		value = cardNum % 13 + 1;
		int cardNumber = cardNum % 13 + 1;
		if(cardNumber <= 10 && cardNumber > 1)
		cardName = String.valueOf(cardNumber);
		else
		switch(cardNumber){
		case(1):
			cardName = "Ace";
		case(11):
			cardName = "Jack";
			break;
		case(12):
			cardName = "Queen";
			break;
		case(13):
			cardName = "King";
			break;
		default:
			cardName = "NotACard";
			break;
		}
	}
	private void assignSuite() {
		if(cardNum < 26)
			if(cardNum < 13) 
			suite += " of Clubs";
		else
			suite += " of Hearts";
		else if(cardNum < 39)
			suite += " of Spades";
		else
			suite += " of Diamonds";
	}
	public String value(){
		return cardName;
	}
	
	@Override
	public String toString() {
		return "[" + cardName +  suite + "]";
	}
	public String suite(){
		return suite;
	}
	
}
