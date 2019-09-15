package Poker_game;
import java.util.*;
public class Table {
	
	private static final int DEFAULT_PLAYER_COUNT = 4;
	private static int playerCount;
	
	public static void Table() {
		setPlayerCount(DEFAULT_PLAYER_COUNT);
	};
	public static void Table(int playerCountIn){
		setPlayerCount(playerCountIn);
	};
	

	public void createPlayers() {
		int playerNums = getPlayerCount();
	};
	
	public static void setPlayerCount(int playerCountIn) {
		//including human player, create playerCount Player objects 
		playerCount = playerCountIn;
	};
	private static int getPlayerCount() {
		//including human player, create playerCount Player objects 
		return playerCount;
	};
	public static void updateCurrentPot() {};
	public static void getCurrentPot() {};


	public void dealHands() {
		// TODO Auto-generated method stub
		
	};


	public void dealFlop() {
		// TODO Auto-generated method stub
		
	};


	public void dealTurn() {
		// TODO Auto-generated method stub
		
	};


	public void dealRiver() {
		// TODO Auto-generated method stub
		
	};


	public void set() {
		// TODO Auto-generated method stub
		
	};
	
}
