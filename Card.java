/**
 * @author Nosson Weissman
 * 
 */
package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;

public class Card implements Comparable<Card>{
	private int cardNum;
	private int value = 0;
	private String suite = "";
	private String cardName;
	
	//public Card(){
		//cardNum = Randomizer.random(52);
		//assignVal();
	///	assignSuite();
	//}
	public Card(Integer cardNumIn){
		cardNum = cardNumIn.intValue();
		assignVal();
		assignSuite();
	}
	private void assignVal() {
		value = cardNum % 13;
		//int cardNumber = cardNum % 13 + 1;
		valToString(value);
	}
	
	private void valToString(int cardNumber) {
		cardNumber++;
		if(cardNumber <= 10 && cardNumber > 1)
			cardName = String.valueOf(cardNumber);
			else
			switch(cardNumber){
			case(1):
				cardName = "Ace";
				break;
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
			suite = " of Clubs";
		else
			suite = " of Hearts";
		else if(cardNum < 39)
			suite = " of Spades";
		else
			suite = " of Diamonds";
	}
	public String value(){
		return cardName;
	}
	
	public int intValue() {
		return value;
	}
	
	public int val() {
		return value;
	}
	
	@Override
	public String toString() {
		return cardName +  suite;
	}
	public String suite(){
		return suite;
	}
	@Override
	public int compareTo(Card otherCard) {
		Integer last = new Integer(this.value).compareTo(new Integer(otherCard.value));
        return last == 0 ? this.suite.compareTo(otherCard.suite) : last;
	}
	
	}
