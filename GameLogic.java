package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;
import java.util.Random;

public class GameLogic {
	private static int biggestBet = 0;
	private GameLogic(){}
	
	public static void takePreflopBets(Player [] gamePlayers, int smallBlind) {
		//'unfold any players who folded out last round'
		for (Player p : gamePlayers)
			p.setFold(false);
		int bigBlind = 2 * smallBlind; 
		biggestBet = bigBlind;
		int i = getSmallIndex(gamePlayers);
		if (!gamePlayers[i].isBig()) {
			gamePlayers[i].betChips(smallBlind);
			gamePlayers[i+1].betChips(bigBlind);
		}
		else 
			gamePlayers[i].betChips(bigBlind);
		for(int j = 0; (j + i) % gamePlayers.length < gamePlayers.length -1; j++) { 
			playerBet(gamePlayers[(j + i) 
			                      % gamePlayers.length]);
		}
	}
	
	public static void takeBets(Player [] gamePlayers, int smallBlind) {
		biggestBet = 2 * smallBlind;
		int firstToBet = getSmallIndex(gamePlayers);
		for(int i = 0; (i + firstToBet) < gamePlayers.length; i++) {
			
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
	
	private static int getSmallIndex(Player [] gamePlayers) {
		int smallBlindIndex = -1;
		
		int n = 0;
		while (smallBlindIndex == -1)
			if (gamePlayers[n++].isSmall() && 
					!gamePlayers[n-1].isFolded())
				smallBlindIndex = n;
		for(int i = 0; i < gamePlayers.length; i++) { 
			playerBet(gamePlayers[(smallBlindIndex + i) 
			                     % gamePlayers.length]);
		}
		return smallBlindIndex;
	}
		
	private static void playerBet(Player player) {
		checkBetFold(player);
		if(player.getCurrentMove() == 'c')
			player.betChips(biggestBet);
		else 
			if (player.getCurrentMove() == 'r') {
				System.out.printf("How much would you like to raise?"
					+ "It must be eat least double %d. %n", biggestBet);
				Scanner scanner = new Scanner(System.in);
				int betAmount = -1;
				while (betAmount < 2 * biggestBet) {
					String  userInput =  scanner.nextLine();
					betAmount = Integer.parseInt(userInput);
					if (betAmount >= 2 * biggestBet)
						player.betChips(betAmount);
					else
						System.out.println("Please enter an integer at least twic the current bet");
			}
		}
	}
	
	private static void checkBetFold(Player player) {
		char playerMove;
		if (player.isHuman()) {
			Scanner scanner = new Scanner(System.in);
			boolean validInput = false;
			do {
				System.out.println("Please enter 'c', 'r', 'f' to call raise,"
					+ " or fold or hit enter to check.");
				String  userInput =  scanner.nextLine();
				playerMove = userInput.charAt(0);
			if (playerMove == 'c' || playerMove == 'r' 
				|| playerMove == 'f' || playerMove == '\r' || 
				playerMove == '\n' || playerMove == '\0')
				validInput = true;
			}
			while (!validInput);
				player.setCurrentMove(playerMove);
		}
		else {
			player.setCurrentMove('\0');
		}
		if (player.getCurrentMove() == 'f')
			player.setFold(true);
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

	public static void determineWinner(Player[] gamePlayers) {
		System.out.println("Great hand! The winner is sdfklj");
	}
	
}
