package Poker_game;
import java.util.*;
import java.util.Random;

public class GameLogic {
	

	
	public static void takeBets(Player [] gamePlayers) {
		int firstToBet = determineFirstPlayer(gamePlayers);
		for(int i = 0; i < gamePlayers.length; i++) { 
			playerBet(gamePlayers[(firstToBet + i) 
			                     % gamePlayers.length]);
		}
	}
	
	public static int determineDealer(Player [] gamePlayers) {
		//random number between 0 and gamplayersIn.length
		Random randNum = new Random();
		int dealerIndex = randNum.nextInt(gamePlayers.length);
		gamePlayers[dealerIndex].makeDealer();
		return dealerIndex;
	}
	
	private static int determineFirstPlayer(Player [] gamePlayersIn) {
		return 0;
		}
		
	private static void playerBet(Player player) {
		
	}
	
	private void checkBetFold(Player player) {
		if (player.isHuman()) {
			Scanner scanner = new Scanner(System.in);
			char playerMove;
			boolean validInput = false;
			do {
			System.out.println("Please enter 'c', 'r', 'f' to call raise,"
					+ " or fold or hit enter to check.");
			String  userInput =  scanner.nextLine();
			playerMove = userInput.charAt(0);
			if (playerMove == 'c' || playerMove == 'r' 
				|| playerMove == 'f' || playerMove == '\r' || 
				playerMove == '\n')
				validInput = true;
			}
			while (!validInput);
			player.setCurrentMove(playerMove);
		}
		else {
			player.setCurrentMove('\0');
		}
	}

	public static void determineSmallBig(Player[] players) {
		int dealerIndex = -1;
		int i = 0;
		while(dealerIndex == -1){
			if (players[i++].isDealer())
				dealerIndex = i;
		}
		players[(dealerIndex + 1) % players.length].makeSmall();
		players[(dealerIndex + 2) % players.length].makeBig();
	}


	public static void makeDealer(Player player) {
		player.makeDealer();
	}
	
}
