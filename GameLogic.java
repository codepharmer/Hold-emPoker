package Poker_game;

import java.util.Random;

public class GameLogic {
	

	
	public static void startBetting(Player [] gamePlayers) {
		int firstToBet = determineFirstPlayer(gamePlayers);
		for(int i = 0; i < gamePlayers.length; i++) { 
			takeBets(gamePlayers[(firstToBet + i) 
			                     % gamePlayers.length]);
		}
	}
	
	public static void determineDealer(Player [] gamePlayers) {
		//random number between 0 and gamplayersIn.length
		Random randNum = new Random();
		gamePlayers[randNum.nextInt(gamePlayers.length)].makeDealer();
	}
	private static int determineFirstPlayer(Player [] gamePlayersIn) {
		return 0;
		}
		
	private static void takeBets(Player gamePlayersIn) {
		
	}

	public static void determineSmallBig(Player[] gamePlayers) {
		// TODO Auto-generated method stub
		
	}

	
	
}
