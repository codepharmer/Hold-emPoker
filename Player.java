package Poker_game;
import java.util.*;
public class Player {
	public static void Player(){}
	
	private static int chipCount = 0;
	private static Stack<Integer> holeCards;
	public void setHoleCards(Stack<Integer> holeCardsIn) {
		//set hole cards for the player
	};
	public Stack<Integer> getHoleCards() {
		//returns stack of two cards
		return holeCards;
	};
	
	public void setBestFive() {
		//set est five cards 
	};
	
	public void setChipCount(int chipCountIn) {
		chipCount = chipCountIn;
	};
	
	public int getChipCount() {
		return chipCount;
	};
	}

