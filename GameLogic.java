package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;

public class GameLogic {
	public static int bigBlind;
	public static int biggestBet;
	public static List<Integer> chipPots = new Vector<Integer>();
	private static int totalChipsBet = 0;
	private static boolean betHasBeenMadeThisRound = false;
	private Vector<Integer> betsMade = new Vector<Integer>();
	public GameLogic(){}
	
	public int takePreflopBets(Player [] gamePlayers) {
		//'unfold any players who folded out last round'
		int numPlayers = gamePlayers.length;
		for (Player p : gamePlayers)
			p.setFold(false);
		int smallBlind = bigBlind / 2; 
		//biggestBet = bigBlind;
		int i = getSmallIndex(gamePlayers);
		//when playing heads up (two players), we don't want to take a small 
		//and big blind from one player so we need to check that that the small blind isn't also the big blind
		if (!gamePlayers[i].isBig()) {
			gamePlayers[i].betChips(smallBlind);
			gamePlayers[(i+1) % numPlayers].betChips(bigBlind);
			placeChips(gamePlayers[(i+1) % numPlayers], bigBlind);
			totalChipsBet += bigBlind;
		}
		else {
			gamePlayers[i].betChips(bigBlind);
		}
		for(int j = 0; (j + i) % gamePlayers.length < gamePlayers.length -1; j++) { 
			playerBet(gamePlayers[(j + i) 
			                      % gamePlayers.length]);
		}
		Table.chipPots.get(0).addChips(totalChipsBet);
		return totalChipsBet;
	}
	
	public int takeBets(Player [] gamePlayers) {
		int smallBlind = bigBlind / 2;
		int firstToBet = getSmallIndex(gamePlayers);
		for(int i = 0; (i + firstToBet) < gamePlayers.length; i++) {
			
			playerBet(gamePlayers[(firstToBet + i) 
			                     % gamePlayers.length]);
		}
		addChipsToPot(totalChipsBet);
		return totalChipsBet;
	}
	
	/*
	 * private void addChipsToPot(int totalChipsBet2) {
	 * Table.chipPots.get(Table.chipPots.size() -1 ).addChips(totalChipsBet2); }
	 */

	public int determineDealer(Player [] gamePlayers) {
		//random number between 0 and gamplayersIn.length
		Random randNum = new Random();
		int dealerIndex = randNum.nextInt(gamePlayers.length);
		gamePlayers[dealerIndex].makeDealer();
		return dealerIndex;
	}
	
	private int getSmallIndex(Player [] gamePlayers) {
		int smallBlindIndex = -2;
		
		int n = 0;
		while (smallBlindIndex == -2)
			if (gamePlayers[n % gamePlayers.length].isSmall() && 
					!gamePlayers[n % gamePlayers.length].isFolded())
				smallBlindIndex = (n % gamePlayers.length);
			else n++;
		//for(int i = 0; i < gamePlayers.length; i++) { 
		//	playerBet(gamePlayers[(smallBlindIndex + i) 
		//	                     % gamePlayers.length]);
		//}
		return smallBlindIndex;
	}
		
	private void playerBet(Player player) {
		checkBetFold(player);
		if(player.getCurrentMove() == 'c') {
			//c means call, so we're just calling to the largest bet
			player.betChips(biggestBet);
			placeChips(player, biggestBet);
		}
		else 
			if (player.getCurrentMove() == 'r') {
				System.out.printf("How much would you like to raise?"
					+ "It must be eat least double %d. %n", bigBlind);
				Scanner scanner = new Scanner(System.in);
				int betAmount = -1;
				
				while (betAmount < 2 * biggestBet && 
						player.getChipCount() >= (2 * biggestBet) ||
						betAmount > player.getChipCount())
				{
					if(betAmount > player.getChipCount())
						System.out.printf("%d is more than your total chip count of %d"
								+ ". \n Please enter a number less than %d but greater than %d. %n", betAmount, 
								player.getChipCount(), betAmount, player.getChipCount());
						String  userInput =  scanner.nextLine();
						betAmount = Integer.parseInt(userInput);
				}
				
				player.betChips(betAmount);
				totalChipsBet += betAmount;
				betHasBeenMadeThisRound = true;
		}
	}
	
	private void placeChips(Player player, int playerBet) {
		boolean donePlacingChips = false;
		
		while(!donePlacingChips) {
			for(BettingPot pot: Table.chipPots) {
				if(player.getTotalHandBets() + playerBet <= pot.getMaxBet()){
					pot.addChips(playerBet);
					donePlacingChips = true;
						break;
				}
				else if(player.getTotalHandBets() + playerBet  > pot.getMaxBet()) {
					int addTheseChips = playerBet;
					playerBet -= pot.getMaxBet() - player.getTotalHandBets();
					pot.addChips(addTheseChips);
				}
						
			}
		}
	}

	/*
	 * private boolean sidePotIsNeeded(Player player) { return(player.getChipCount()
	 * < biggestBet); }
	 */
	/*
	 * private void addPot(Player playerToExclude) { int newPotVal =
	 * determineNewPotVal(playerToExclude); Vector<String> newPotPlayers =
	 * playersInNewPot(playerToExclude); removeExtraChipsFromMainPot(newPotVal -
	 * playerToExclude.getChipCount()); Table.chipPots.add(new BettingPot(newPotVal,
	 * newPotPlayers)); }
	 */

	/*
	 * private void removeExtraChipsFromMainPot(int chipsToRemove) {
	 * Table.chipPots.get(chipPots.size() -1).setchipCount(
	 * Table.chipPots.get(chipPots.size() -1).getChipCount() - chipsToRemove); }
	 */

	/*
	 * private int determineNewPotVal(Player playerToExclude) { return
	 * playerToExclude.getChipCount() * (betsMade.size() + 1); }
	 */

	/*
	 * private Vector playersInNewPot(Player playerToExclude) { Vector<String>
	 * players = new Vector<String>(); for(String p:
	 * Table.chipPots.get((chipPots.size() -1)).getPlayersInvolved()) { if (p !=
	 * playerToExclude.getName()) players.add(p); } //BettingPot newPot = new
	 * BettingPot('f',players); return players; }
	 */

	private void checkBetFold(Player player) {
		char playerMove = '\0';
		final int ASCII_ENTER_VAL = 10;
		if (player.isHuman() && !player.isFolded()) {
			Scanner scanner = new Scanner(System.in);
			boolean validInput = false;
			String playerPrompt = determinePlayerOptions(player);
			do {
				System.out.println(playerPrompt);
				String  userInput =  scanner.nextLine();
				if (userInput != "")
					playerMove = userInput.charAt(0);
				System.out.println("input val is" + (int)playerMove);
				if (playerMove == 'c' || playerMove == 'r' 
				|| playerMove == 'f' || playerMove == 't')
					validInput = true;
			}
			while (!validInput);
				player.setCurrentMove(playerMove);
				scanner.close();
		}
		else {
			char enter = (char)ASCII_ENTER_VAL;
			player.setCurrentMove('t');
		}
		if (player.getCurrentMove() == 'f')
			player.setFold(true);
	}

	private String determinePlayerOptions(Player player) {
		//if( player.getChipCount() > biggestBet * 2 &&) 
			return "Please hit 'c', 'r', or 'f' to call, raise, or fold"
					+ "or 't' to tap the table to check.";
	}

	public void determineSmallBig(Player[] players) {
		int dealerIndex = -2;
		int i = 0;
		while(dealerIndex == -2){
			if (players[i++].isDealer())
				dealerIndex = i - 1;
		}
		players[(dealerIndex + 1) % players.length].makeSmall();
		players[(dealerIndex + 2) % players.length].makeBig();
	}


	public void makeDealer(Player player) {
		player.makeDealer();
	}

	public String determineWinner(Player[] gamePlayers) {
		return "Great hand! The winner is sdfklj";
	}

	public void setBigBlind(int bigBlindIn) {
		bigBlind = bigBlindIn;
	}
	
	public void resetChipPots(Player[] players) {
		// at the beginning of each hand create set holding each players chipCount
		Set<Integer> potValsSet = new HashSet<Integer>();
		Vector<Integer> potVals = new Vector<Integer>();
		for(Player p: players)
			potValsSet.add(p.getChipCount());
		for(Integer i : potValsSet)
			potVals.add(i);
		Collections.sort(potVals);
		// x0,x1,x2 ... represent each players chip count
		// create a new pot for each n with each pot allowing upto xn chips from each
		createChipsPots(players, potVals);
		//player before moving chip to a different pot
			
		// playersInvolved will include any players who have chipCount >= xn
		//at the end of the hand we'll determine which players are entitled to which hands. I there
		/*
		 * Vector<String> gamePlayers = new Vector<String>(); for(Player p : players)
		 * gamePlayers.add(p.getName()); int emptyPotVal = 0; Table.chipPots.add(new
		 * BettingPot(emptyPotVal, gamePlayers));
		 */
	}

	private void createChipsPots(Player[] players, Vector<Integer> potVals) {
		Table.chipPots = new Vector<BettingPot>();
		for(Integer i: potVals) {
			Vector<String> playersInPot = new Vector<String>();
			for(Player p : players) {
				if(p.getChipCount() >= i) {
					playersInPot.add(p.getName());
				}
			Table.chipPots.add(new BettingPot(i, playersInPot));
			}
		}
	}
}
