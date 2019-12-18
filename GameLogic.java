package edu.cuny.csi.csc330.holdemPoker;
import java.lang.reflect.Array;
import java.util.*;

public class GameLogic {
	private static final int MAX_PLAYERS_IN_GAME = 10;
	public static int bigBlind;
	public static int biggestBet;
	public static List<Integer> chipPots = new Vector<Integer>();
	private static int totalChipsBet = 0;
	private static boolean betHasBeenMadeThisRound = false;
	private Vector<Integer> betsMade = new Vector<Integer>();
	private Card [] cardArrForDetermineHandMethod = new Card[7];
	private static String winningHandStrength ="";
	public GameLogic() {
	}

	public int takePreflopBets(Player[] gamePlayers) {
		// 'unfold any players who folded out last round'
		int numPlayers = gamePlayers.length;
		resetPlayerProps(gamePlayers);
		int smallBlind = bigBlind / 2;
		biggestBet = bigBlind;
		//System.out.println(this.biggestBet);
		int smallIndex = getSmallIndex(gamePlayers);
		// when playing heads up (two players), we don't want to take a small
		// and big blind from one player so we need to check that that the small blind
		// isn't also the big blind
		if (!gamePlayers[smallIndex].isBig()) {
			gamePlayers[smallIndex].betChips(smallBlind);
			placeChips(gamePlayers[smallIndex], smallBlind);
			gamePlayers[(smallIndex + 1) % numPlayers].betChips(bigBlind);
			placeChips(gamePlayers[(smallIndex + 1) % numPlayers], bigBlind);
			totalChipsBet += bigBlind;
		} else {
			gamePlayers[smallIndex].betChips(bigBlind);
		}
		for (int j = 0; (j + smallIndex) % gamePlayers.length < gamePlayers.length - 1; j++) {
			playerBet(gamePlayers[(j + smallIndex) % gamePlayers.length]);
			System.out.printf("plyer %d %n", ((j + smallIndex) % gamePlayers.length) );
		}
		biggestBet = 0;
		//Table.chipPots.get(0).addChips(totalChipsBet);
		return totalChipsBet;
	}

	private void resetPlayerProps(Player[] gamePlayers) {
		for (Player p : gamePlayers)
			p.setFold(false);
		for (Player p : gamePlayers)
			p.setTotalChipsBet(0);
	}

	public int takeBets(Player[] gamePlayers) {
		biggestBet = 0;
		int smallBlind = bigBlind / 2;
		int firstToBet = getSmallIndex(gamePlayers);
		for (int i = 0; (i + firstToBet) < gamePlayers.length; i++) {
			if (!(gamePlayers[(firstToBet + i) % gamePlayers.length].isFolded())) {
				Player currPlayer = gamePlayers[(firstToBet + i) % gamePlayers.length];
				//System.out.printf("%s is still in this round so we'll be taking bets %n", 
						currPlayer.getName();
				playerBet(currPlayer);
			}
		}
		// addChipsToPot(totalChipsBet);
		return totalChipsBet;
	}

	/*
	 * private void addChipsToPot(int totalChipsBet2) {
	 * Table.chipPots.get(Table.chipPots.size() -1 ).addChips(totalChipsBet2); }
	 */

	public int determineDealer(Player[] gamePlayers) {
		// random number between 0 and gamplayersIn.length
		Random randNum = new Random();
		int dealerIndex = randNum.nextInt(gamePlayers.length);
		gamePlayers[dealerIndex].makeDealer();
		return dealerIndex;
	}

	private int getSmallIndex(Player[] gamePlayers) {
		int smallBlindIndex = -2;

		int n = 0;
		while (smallBlindIndex == -2)
			if (gamePlayers[n % gamePlayers.length].isSmall() && !gamePlayers[n % gamePlayers.length].isFolded())
				smallBlindIndex = (n % gamePlayers.length);
			else
				n++;
		// for(int i = 0; i < gamePlayers.length; i++) {
		// playerBet(gamePlayers[(smallBlindIndex + i)
		// % gamePlayers.length]);
		// }
		return smallBlindIndex;
	}

	private void playerBet(Player player) {
		checkBetFold(player);
		if (player.getCurrentMove() == 'c') {
			// c means call, so we're just calling to the largest bet
			player.betChips(biggestBet);
			placeChips(player, biggestBet);
		} else if (player.getCurrentMove() == 'r') {
			
			Scanner scanner = new Scanner(System.in);
			int betAmount = -1;

			while (betAmount < 2 * biggestBet && player.getChipCount() >= (2 * biggestBet)
					|| betAmount > player.getChipCount()) {
				if(biggestBet > 0) {
				System.out.printf("How much would you like to raise?" + 
					"It must be eat least double %d. %n", biggestBet);
				}
				else {
					System.out.printf("How much would you like to raise?" + 
							"It must be eat least %d. %n", bigBlind);
				}
				if (betAmount > player.getChipCount())
					System.out.printf(
							"%d is more than your total chip count of %d"
									+ ". \n Please enter a number less than %d but greater than %d. %n",
							betAmount, player.getChipCount(), betAmount, player.getChipCount());
				String userInput = scanner.nextLine();
				betAmount = Integer.parseInt(userInput);
				//System.out.println("Parsed integr input");
			}

			player.betChips(betAmount);
			System.out.println("placed player bet");
			totalChipsBet += betAmount;
			betHasBeenMadeThisRound = true;
		}
	}

	private void placeChips(Player player, int playerBet) {
		boolean donePlacingChips = false;
		//System.out.printf("player bet %d %n", playerBet);
		while (!donePlacingChips) {
			for (BettingPot pot : Table.chipPots) {
				System.out.printf("%s bet %d %n",player.getName(), playerBet);
				if (player.getTotalChipsBet() + playerBet <= pot.getMaxBet()) {
					pot.addChips(playerBet);
					donePlacingChips = true;
					System.out.printf("added %d chips to the pot. %n", playerBet, 
							Table.chipPots.indexOf(pot));
					break;
				} else if (player.getTotalChipsBet() + playerBet > pot.getMaxBet()) {
							int addTheseChips = playerBet;
							playerBet -= (pot.getMaxBet() - player.getTotalChipsBet());
							pot.addChips(addTheseChips);
				}

			}
		}
	}

	

	private void checkBetFold(Player player) {
		char playerMove = '\0';
		final int ASCII_ENTER_VAL = 10;
		if (player.isHuman() && !player.isFolded()) {
			Scanner scanner = new Scanner(System.in);
			boolean validInput = false;
			String playerPrompt = determinePlayerOptions(player);
			do {
				System.out.println(playerPrompt);
				String userInput = scanner.nextLine();
				if (userInput != "")
					playerMove = userInput.charAt(0);
				//System.out.println("input val is" + (int) playerMove);
				if (playerMove == 'c' || playerMove == 'r' || playerMove == 'f' || playerMove == 't')
					validInput = true;
			} while (!validInput);
			player.setCurrentMove(playerMove);
			/*
			 * System.out.println("Closing scanner"); scanner.close();
			 */
		} else {
			char enter = (char) ASCII_ENTER_VAL;
			player.setCurrentMove('c');
		}
		if (player.getCurrentMove() == 'f')
			player.setFold(true);
	}

	private String determinePlayerOptions(Player player) {
		// if( player.getChipCount() > biggestBet * 2 &&)
		return "Please hit 'c', 'r', or 'f' to call, raise, or fold"
				+ " or 't' to tap the table to check.";
	}

	public void determineSmallBig(Player[] players) {
		int dealerIndex = -2;
		int i = 0;
		while (dealerIndex == -2) {
			if (players[i++].isDealer())
				dealerIndex = i - 1;
		}
		players[(dealerIndex + 1) % players.length].makeSmall();
		players[(dealerIndex + 2) % players.length].makeBig();
	}

	public void makeDealer(Player player) {
		player.makeDealer();
	}

	public String determineWinner(Player[] gamePlayers, Card [] communityCards) {
		Vector<Player> winningPlayers = getPlayerWithBestHand(gamePlayers);
		String playerHand = "";
		Card[] tempCards = new Card[5];
		//tempCards = winningPlayers.getCurrBestHand();
		for(Card c: tempCards) {
			//playerHand += c.toString();
		//	playerHand += ", ";
		}
		String functionOutput = "Winner is " + winningPlayers.get(0).getName() 
		+ "! With a hand of " + playerHand +"\n"
		+  winningPlayers.get(0).getName()  +" has a "
		+ DetHand.determineHand(winningPlayers.get(0).getCurrBestHand());
		return functionOutput;
	}

	public void setBigBlind(int bigBlindIn) {
		bigBlind = bigBlindIn;
	}

	public void resetChipPots(Player[] players) {
		// at the beginning of each hand create set holding each players chipCount.
		if(!Table.chipPots.isEmpty())
			Table.chipPots.clear();
		Set<Integer> potValsSet = new HashSet<Integer>();
		Vector<Integer> potVals = new Vector<Integer>();
		for (Player p : players)
			potValsSet.add(p.getChipCount());
		for (Integer i : potValsSet)
			potVals.add(i);
		Collections.sort(potVals);
		System.out.printf("pot vals tostring: %s %n", potVals.toString());
		createChipsPots(players, potVals);
		
		// x0,x1,x2 ... represent each players chip count
		// create a new pot for each n with each pot allowing upto xn chips from each
		// player before moving chip to a different pot

		// playersInvolved will include any players who have chipCount >= xn
		// at the end of the hand we'll determine which players are entitled to which
		// hands. I there
		/*
		 * Vector<String> gamePlayers = new Vector<String>(); for(Player p : players)
		 * gamePlayers.add(p.getName()); int emptyPotVal = 0; Table.chipPots.add(new
		 * BettingPot(emptyPotVal, gamePlayers));
		 */
	}

	private void createChipsPots(Player[] players, Vector<Integer> potVals) {
		Table.chipPots = new Vector<BettingPot>();
		for (Integer i : potVals) {
			Vector<String> playersInPot = new Vector<String>();
			for (Player p : players) {
				if (p.getChipCount() >= i) {
					playersInPot.add(p.getName());
				}
			}
				Table.chipPots.add(new BettingPot(0, playersInPot, i));
				//System.out.printf("Adding pot with %d maxBet playersInvolved are %s %n", i, playersInPot.toString());
			
		}
	}

	public void awardWinners(Player[] players) {
		// access pots array (vector)
		//for each pot we'l award the chips to the player with the best hand
		for (BettingPot pot : Table.chipPots) {
			Player[] playersInPot = new Player[MAX_PLAYERS_IN_GAME];
			// check players involved and compare their hands
			int i = 0;
			for (String plyrName : pot.getPlayersInvolved()) {
				playersInPot[i++] = fetchPlayer(players, plyrName);
			}
			updateStrongestHand(players);
			Vector<Player> winningPlayers = new Vector<>();
			winningPlayers = getPlayerWithBestHand(players);
			int howManyWinners = winningPlayers.size();
			for(Player p : winningPlayers) {
				if(howManyWinners == 0)
					System.out.println("Error in awardWinners");
				else {
					p.addChips(pot.getChipCount() / howManyWinners);
				}
			}
		}
		Table.chipPots.clear();
	}

	public void setPlayerHands(Player[] players, Card[] communityCards) {
		int numHoleCards = 2;
		//communityCards += playerCards
		for (Player p : players) {
			for(int i = 0; i< numHoleCards; i++) {
				 cardArrForDetermineHandMethod[i] =
				 p.getPlayerCards().get(i);
			}
			for (int i = 0; i < 
					(communityCards.length ); i++) {
				cardArrForDetermineHandMethod[i + numHoleCards] = 
						communityCards[i];
			}
			// something like this:
			p.setCurrBestHand(DetHand.getBestHand(cardArrForDetermineHandMethod));
			
			updateStrongestHand(players);
		//	for( Card c : cardArrForDetermineHandMethod)
		//		System.out.println(c.toString());
		//		System.out.println();
			//String playerHand = DetHand(communityCards);
			//p.setBestHand(playerHand);
			cardArrForDetermineHandMethod = new Card[7];
		}
	}
	
	private void updateStrongestHand(Player[] players) {
		Vector<String> compareHands = new Vector<>();
		for(Player p : players)
			compareHands.add(p.getHandStrength());
		winningHandStrength = DetHand.compareHands(compareHands);
	}

	private Player fetchPlayer(Player[] players, String plyrName) {
		for (Player p : players) {
			if (p.getName() == plyrName)
				return p;
		}
		System.out.println("Error in findThisPlayer");
		System.exit(1);
		return new Player();
	}
	
	private Vector<Player> getPlayerWithBestHand(Player [] players) {
		// at this pont we assume that there are 5 cards in player.getBestHand()
		// we return the player whose current best hand matches this hand
		Vector<Player> winningPlayers = new Vector<>();
		for (Player p : players) {
			if (p.getHandStrength() == winningHandStrength)
				winningPlayers.add(p);
		}
		Vector<Player> temp = new Vector<>();
		temp.add(players[0]);
		return temp;
		//return winningPlayers;
	}

	public int updatePotVal(Player [] players) {
		int potVal = 0;
		for(Player p : players) {
			potVal += p.getTotalChipsBet();
		}
		return potVal;
	}

}
