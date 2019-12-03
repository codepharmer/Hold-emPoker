package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;
public class Player {
	
	private final int DEFAULT_CHIP_COUNT = 120;
	private int chipCount = 0;
	private int totalHandBets = 0;
	boolean human = false;
	boolean dealer = false;
	boolean small = false;
	boolean big = false;
	boolean folded = false;
	String playerName = "player";
	char currentMove;
	private Vector<String> holeCards = new Vector<String>();
	
	
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
	
	public void setBestFive(Vector<String> holeCardsIn) {
		//set est five cards 
		holeCards = holeCardsIn;
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
	public String getName() {
		// TODO Auto-generated method stub
		return playerName;
	}
	public int getTotalHandBets() {
		return totalHandBets;
	}
	public void setTotalHandBets(int totalHandBets) {
		this.totalHandBets = totalHandBets;
	}
	}

