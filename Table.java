package Poker_game;
import java.util.*;
public class Table {
	//create dealer object .. now we can remove the card deck, it's held by dealer
	public static CardDeck cardDeck = new CardDeck();
	private static final int DEFAULT_PLAYER_COUNT = 4;
	public static int playerCount = DEFAULT_PLAYER_COUNT;;
	public Player [] gamePlayers;
	
	public Table(){
		setPlayerCount(DEFAULT_PLAYER_COUNT);
		createPlayers();
		Dealer.shuffle(cardDeck.getDeck());
		dealHoleCards();
	}
	
	public Table(int playerCountIn){
		setPlayerCount(playerCountIn);
		createPlayers();
		dealHoleCards();
	}
	
	
	public void createPlayers() {
		 gamePlayers = new Player[getPlayerCount()];
		 for (int i = 0; i < playerCount; i++)
		 gamePlayers[i] = new Player();
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
	public void updateCurrentPot() {}
	public void getCurrentPot() {}


	private void dealHoleCards() {
		//deal hole cardDeck to each plyer
		/* int playersDealt = 0; */
		for (int i = 0; i < playerCount; i++) {
			String tempCard = cardDeck.dealCard();
			gamePlayers[i].setHoleCards(tempCard);
			tempCard = cardDeck.dealCard();
			gamePlayers[i].setHoleCards(tempCard);
		}
		//System.out.println(gamePlayers[0].getHoleCards());
	}
	
	public void checkTopCard() {
		System.out.println(cardDeck.peek());
	}

	public void dealFlop() {
		// TODO Auto-generated method stub
		
	}


	public void dealTurn() {
		// TODO Auto-generated method stub
		
	}


	public void dealRiver() {
		// TODO Auto-generated method stub
		
	}


	public void set() {
		// TODO Auto-generated method stub
	}
	
}
