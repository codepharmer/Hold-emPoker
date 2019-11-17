package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;
public class Table {
	public static CardDeck cardDeck = new CardDeck();
	private Dealer dealerJoe;
	private static final int DEFAULT_PLAYER_COUNT = 4;
	private static final int MAX_COMMUNITY_CARDS = 5;
	private static final int HUMAN_PLAYER_INDEX = 2;
	public static int playerCount = DEFAULT_PLAYER_COUNT;
	public static Card [] communityCards = new Card[MAX_COMMUNITY_CARDS];
	private Player [] gamePlayers = new Player [0];
	private static int dealerIndex = 0;
	private int totalPotVal = 0;
	private final int DEFAULT_SMALL_BLIND = 2;
	private int smallBlind = DEFAULT_SMALL_BLIND;
	private int bigBlind = 2 * smallBlind;
	boolean continuePlaying = false;
	
	public Table(){
		setPlayerCount(DEFAULT_PLAYER_COUNT);
		createPlayers();
		dealerJoe = new Dealer(cardDeck);
		dealerIndex = GameLogic.determineDealer(gamePlayers);
		startGame();
	}
	
	public Table(int playerCountIn){
		setPlayerCount(playerCountIn);
		createPlayers();
		dealerJoe = new Dealer(cardDeck);
		dealerIndex = GameLogic.determineDealer(gamePlayers);
		startGame();
	}
	public int getSmallBlind() {
		return smallBlind;
	}

	public void setSmallBlind(int smallBlind) {
		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {
		return bigBlind;
	}

	public void setBigBlind(int bigBlind) {
		this.bigBlind = bigBlind;
	}

	private void startGame() {
		nextHand();
	}
	public void nextHand() {
		cardDeck = dealerJoe.resetDeck();
		cardDeck = dealerJoe.shuffleCards();
		GameLogic.makeDealer(gamePlayers[dealerIndex++]);
		GameLogic.determineSmallBig(gamePlayers);
		System.out.println("Dealing new hand");
		dealHoleCards();
		startAntiing();
		dealFlop();
		showTable();
		flopBet();
		dealTurn();
		turnBet();
		showTable();
		GameLogic.determineWinner(gamePlayers);
		if (continuePlaying == true)
		nextHand();
	}
	private void showTable() {
		System.out.printf("Current total pot %s %n", totalPotVal);
		printCommunityCards();
		for (Player p : gamePlayers)
			System.out.printf("Chip count for %s %d %n", p.getName(), p.getChipCount());
	}

	private void printCommunityCards() {
		String printCards = "";
		for (Card c : communityCards)
			if (c != null)
			printCards += c + " -- ";
		System.out.printf("Community cards: %s %n", printCards);
	}

	public void startAntiing() {
		GameLogic.takePreflopBets(gamePlayers, smallBlind);
	}
	
	public void dealFlop() {
		Card [] flopCards = new Card[3];
		flopCards = dealerJoe.dealFlop();
		for (int i = 0; i < flopCards.length; i++)
			communityCards[i] = flopCards[i]; 
	}
	
	public void flopBet() {
		GameLogic.takeBets(gamePlayers, smallBlind);
	}

	public void dealTurn() {
		int TURN_CARD_INDEX = 3;
		communityCards[TURN_CARD_INDEX] = dealerJoe.dealTurn();
	}
	
	public void turnBet() {
		GameLogic.takeBets(gamePlayers, smallBlind);
	}
	
	public void dealRiver() {
		int RIVER_CARD_INDEX = 4;
		communityCards[RIVER_CARD_INDEX] = dealerJoe.dealRiver();
	}
	public void riverBet() {
		GameLogic.takeBets(gamePlayers, smallBlind);
	}
	
	public void createPlayers() {
		 gamePlayers = new Player[getPlayerCount()];
		 for (int i = 0; i < playerCount; i++) {
		 gamePlayers[i] = new Player(i);
		 if (i == HUMAN_PLAYER_INDEX)
			 gamePlayers[i].setHuman();
		 }
	}
	
	public Vector<String> getPlayerCards(int player) {
		return gamePlayers[player].getHoleCards();
	}
	
	public void getPlayerInfo(int player) {
		System.out.println(gamePlayers[player].getChipCount());
		System.out.println(gamePlayers[player].getHoleCards());
	}
	public void setPlayerCount(int playerCountIn) {
		//including human player, create playerCount Player objects 
		playerCount = playerCountIn;
	}
	public int getPlayerCount() {
		//including human player, create playerCount Player objects 
		return playerCount;
	}
	public void updateCurrentPot(int updatedChipCount){
		totalPotVal = updatedChipCount;
	}
	public int getCurrentPot() {
		return totalPotVal;
	}

	private void dealHoleCards() {
		//deal hole cardDeck to each plyer
		/* int playersDealt = 0; */
		for (int i = 0; i < playerCount; i++) {
			String tempCard = dealerJoe.dealCard().toString();
			gamePlayers[i].setHoleCards(tempCard);
			tempCard = dealerJoe.dealCard().toString();
			gamePlayers[i].setHoleCards(tempCard);
		}
		//System.out.println(gamePlayers[0].getHoleCards());
	}
	
	public void checkTopCard() {
		System.out.println(cardDeck.peek());
	}


	


	public void set() {
		// TODO Auto-generated method stub
	}
	
}
