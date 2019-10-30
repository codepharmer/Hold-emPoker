package Poker_game;
import java.util.*;
public class Player {
	private final int DEFAULT_CHIP_COUNT = 1200;
	private int chipCount = 0;
	boolean dealer = false;
	boolean small = false;
	boolean big = false;
	boolean folded = false; 
	private Vector<String> holeCards = new Vector<String>();
	public Player(){
		setChipCount();
		System.out.println("Creating player...\n");
	};
	private void setChipCount() {
		chipCount = DEFAULT_CHIP_COUNT;
	}
	public void setHoleCards(String holeCardsIn) {
		holeCards.add(holeCardsIn);
	};
	public Vector<String> getHoleCards() {
		//returns stack of two cards
		return holeCards;
	};
	
	public void setBestFive(Vector<String> holeCardsIn) {
		//set est five cards 
		holeCards = holeCardsIn;
	};
	
	public void updateChipCount(int chipCountIn) {
		chipCount = chipCountIn;
	};
	
	public int getChipCount() {
		return chipCount;
	};
	
	public void makeSmall() {
		small = true;
	}
	
	public void makeBig() {
		big = true;
	}
	
	public void makeDealer() {
		dealer = true;
	}
	
	public void foldPlayer() {
		folded = true;
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
	}

