package edu.cuny.csi.csc330.holdemPoker;

import java.util.*;

public class DetHand {

	private static Card [] bestHand = new Card[5];
	public static String determineHand(Card[] hand) {
		boolean group1 = false;
		String itIs = null;
		int key = 0;
		Vector<Card> cardsToSort = new Vector<>();
		for(Card c : hand) {
			cardsToSort.add(c);
		}
		Collections.sort(cardsToSort);
		for(int i = 0; i < hand.length; i++) {
			hand[i] = cardsToSort.get(i);
		}
		int cardVal1 = hand[0].val();
		int cardVal2 = hand[1].val();
		int cardVal3 = hand[2].val();
		int cardVal4 = hand[3].val();
		int cardVal5 = hand[4].val();

		String suitVal1 = hand[0].suite();
		String suitVal2 = hand[1].suite();
		String suitVal3 = hand[2].suite();
		String suitVal4 = hand[3].suite();
		String suitVal5 = hand[4].suite();

//		for (Card c : hand)
//			System.out.println(c.suite());

		// determine a pair
		if (cardVal1 == cardVal2 ||
				cardVal2 == cardVal3 || 
				cardVal3 == cardVal4 ||
				cardVal4 == cardVal5) {
			group1 = true;
			key = 1;
			// determine two pair
		}
		if (group1) {

			if (cardVal1 == cardVal2 && cardVal3 == cardVal4 || cardVal1 == cardVal2 && cardVal4 == cardVal5
					|| cardVal2 == cardVal3 && cardVal4 == cardVal5) {
				key = 2;
			}
			// Three of a kind
			if (cardVal1 == cardVal2 && cardVal1 == cardVal3 || cardVal2 == cardVal3 && cardVal2 == cardVal4
					|| cardVal3 == cardVal4 && cardVal3 == cardVal5) {
				key = 3;
			}
			// determine a fullhouse
			if (cardVal1 == cardVal2 && cardVal3 == cardVal4 && cardVal3 == cardVal5
					|| cardVal1 == cardVal2 && cardVal1 == cardVal3 && cardVal4 == cardVal5) {
				key = 6;
			}
			// determine four of a kind
			if (cardVal1 == cardVal2 && cardVal1 == cardVal3 && cardVal1 == cardVal4
					|| cardVal2 == cardVal3 && cardVal2 == cardVal4 && cardVal2 == cardVal5) {
				key = 7;
			}
			// group1 = false;
		}
		// check suits
		if (!group1) {
			// determine a straight
			if ((cardVal1 == (cardVal2 - 1) && cardVal2 == (cardVal3 - 1) && cardVal3 == (cardVal4 - 1)
					&& ((cardVal4 == (cardVal5 - 1))))
					|| (cardVal1 == 0 && cardVal2 == 9 && cardVal3 == 10 && cardVal4 == 11 && cardVal5 == 12)) {
				key = 4;
			}
			// determine straight flush
//		System.out.println(key == 4);
//		System.out.println(suitVal1 == suitVal2);
//		System.out.println(suitVal1 == suitVal3);
//		System.out.println(suitVal1 == suitVal4);
//		System.out.println(suitVal1 == suitVal5);

			if (key == 4 && suitVal1 == suitVal2 && suitVal1 == suitVal3 && suitVal1 == suitVal4
					&& suitVal1 == suitVal5) {
//			System.out.println("straight flush");
				key = 8;
			}
			// determine a royal flush
			if (key == 8 && cardVal5 == 12)// Royal Flush
			{
				key = 9;
			} // end of royalflush if
				// determine flush
			else if (!(key == 8 || key == 9) && suitVal1 == suitVal2 && suitVal1 == suitVal3 && suitVal1 == suitVal4
					&& suitVal1 == suitVal5) {
				key = 5;
			}
		}
		// display what player has
		switch (key) {
		case 0:
			System.out.println("PLAYER has ");
			break;

		case 1:
			System.out.println("CPU has ");
			break;
		}
		switch (key) {
		case 0:
			itIs = "A HIGH CARD";
			break;
		case 1:
			itIs = "A PAIR";
			break;
		case 2:
			itIs = "TWO PAIR";
			break;
		case 3:
			itIs = "THREE OF A KIND";
			break;
		case 4:
			itIs = "A STRAIGHT";
			break;
		case 5:
			itIs = "A FLUSH";
			break;
		case 6:
			itIs = "A FULL HOUSE";
			break;
		case 7:
			itIs = "FOUR OF A KIND";
			break;
		case 8:
			itIs = "STRAIGHT FLUSH";
			break;
		case 9:
			itIs = "ROYAL FLUSH";
			break;

		}

		return itIs;
	}
	public static Card[] getBestHand(Card[] cardArrForDetermineHandMethod) {
		//fix this algo later!
		Card[] bestFive = new Card[5];
		for(int i = 0; i < cardArrForDetermineHandMethod.length
				&& i < 5; i++) {
			bestFive[i] = cardArrForDetermineHandMethod[i];
			//System.out.println(bestFive[i].toString());
		}
		return bestFive;
	}
	public static String compareHands(Vector<String> handsToCompare) {
		// TODO Auto-generated method stub
		return new String();
	}

//	public static Card[] detBestHand(Card[] cards) {
//		int n = 0;
//		if (cards.length == 2 &&
//				(cards[0] == cards[1] ||
//				cards[1] == cards[2] || 
//				cards[2] == cards[3] ||
//				cards[3] == cards[4] ||
//				cards[4] == cards[5]))
//		
//		if (cards.length == 5)
//			DetHand.determineHand(cards);
//		if (cards.length == 7)
//			
//			for (int i = 0; i < cards.length; i++) {
//				for (int j = i + 1; j < cards.length; j++) {
//					while(n<8){
//							if (n!=i && i!=j)
//								bestHand[i] = cards[n];
//							n++;
//						}
//					}
//				}
//					return hand1;
//	}
	// todo
	// sortCards
	//private static Card[] sortCards(Card[] cards){
		
		
	//} 
	// create method for two cards

}