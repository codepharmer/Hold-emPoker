/**
 * @author Nosson Weissman
 * 
 */

package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;
public class Player {
	
	private final int DEFAULT_CHIP_COUNT = 120;
	private int chipCount = 0;
	private int totalHandBets = 0;
	private boolean human = false;
	private boolean dealer = false;
	private boolean small = false;
	private boolean big = false;
	private boolean folded = false;
	private String playerName = "player";
	private char currentMove;
	private Card [] currBestHand = new Card[5];
	private String handStrength = "default-highCard2";
	private Vector<String> holeCards = new Vector<String>();
	private Vector<Card> playerCards = new Vector<Card>();
	
	
	public Player(){
		setChipCount();
		System.out.println("Creating player...\n");
	}
	
	public Player(int index){
		setName(index);
		setChipCount();
		System.out.println("Creating player...\n");
	}
	
	public boolean isHuman() {
		return human;
	}

	public void setHuman() {
		this.human = true;
	}
	
	private void setChipCount() {
		chipCount = DEFAULT_CHIP_COUNT;
	}
	
	public void setHoleCards(String holeCardsIn) {
		holeCards.add(holeCardsIn);
	}
	
	public Vector<String> getHoleCards() {
		//returns stack of two cards
		return holeCards;
	}
	
	public void setPlayerCards(Card holeCardsIn) {
		playerCards .add(holeCardsIn);
	}
	
	public Vector<Card> getPlayerCards() {
		return playerCards;
	}
	public void setCurrBestHand(Card [] currHand) {
		for( int i = 0; i < currHand.length; i++) {
			currBestHand[i] = currHand[i];
		}
	}
	
	public Card[] getCurrBestHand() {
		return currBestHand;
	}
	
	public void addChips(int chipsToAdd) {
		chipCount += chipsToAdd;
	}
	public void betChips(int betAmount) {
		chipCount -= betAmount;
		totalHandBets += betAmount;
	}
	
	public void setChipCount(int chipCountIn) {
		chipCount = chipCountIn;
	}
	
	public int getChipCount() {
		return chipCount;
	}

	public char getCurrentMove() {
		return currentMove;
	}
	public void setCurrentMove(char currentMove) {
		this.currentMove = currentMove;
	}
	public void makeSmall() {
		small = true;
	}
	
	public void makeBig() {
		big = true;
	}
	
	public void makeDealer() {
		dealer = true;
	}

	public void setFold(boolean toFoldOrNotTOFold) {
		folded = toFoldOrNotTOFold;
		if(folded) {
			//holeCards = null;
			currBestHand = null;
		}
	}
	
	public boolean isBig() {
		if (big == true)
			return true;
		else return false;
	}
	
	public boolean isSmall() {
		if (small == true)
			return true;
		else return false;
	}
	
	public boolean isDealer() {
		if (dealer == true)
			return true;
		else return false;
	}
	
	public boolean isFolded() {
		if (folded == true)
			return true;
		else return false;
	}

	public void setName(int i) {
		Integer index = i;
		playerName += index.toString();
	}
	public void setName(String name) {
		playerName = name;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return playerName;
	}
	
	public int getTotalChipsBet() {
		return totalHandBets;
	}
	public void setTotalChipsBet(int totalHandBets) {
		this.totalHandBets = totalHandBets;
	}
	
	@Override
	public String toString() {
		return "Player [DEFAULT_CHIP_COUNT=" + DEFAULT_CHIP_COUNT + ", chipCount=" + chipCount + ", totalHandBets="
				+ totalHandBets + ", human=" + human + ", dealer=" + dealer + ", small=" + small + ", big=" + big
				+ ", folded=" + folded + ", playerName=" + playerName + ", currentMove=" + currentMove
				+ ", currBestHand=" + Arrays.toString(currBestHand) + ", holeCards=" + holeCards + ", playerCards="
				+ playerCards + "]";
	}

	public String getHandStrength() {
		return handStrength;
	}

	public void setHandStrength(String handStrength) {
		this.handStrength = handStrength;
	}
}

