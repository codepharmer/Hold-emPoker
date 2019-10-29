package Poker_game;

public class GameLogic {
	

	
	public static void startBetting(Player [] gamePlayers) {
		int firstToBet = determineFirstPlayer(gamePlayers);
		for(int i = 0; i < gamePlayers.length; i++) { 
			takeBets(gamePlayers[(firstToBet + i) 
			                     % gamePlayers.length]);
		}
	}
	
	private static int determineFirstPlayer(Player [] gamePlayersIn) {
		return 0;
		}
		
	private static void takeBets(Player gamePlayersIn) {
		//allow player to bet
	}
	
}
