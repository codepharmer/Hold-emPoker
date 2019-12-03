package edu.cuny.csi.csc330.holdemPoker;
import java.util.*;
public class BettingPot {
	List<String> playersInvolved = new Vector<String>();
	int chipCount;
	int maxBet;
	
	BettingPot(){}
	BettingPot(int potVal, Vector<String> playersInvolved){
		chipCount = potVal;
		this.playersInvolved = playersInvolved;
	}
	BettingPot(int potVal, Vector<String> playersInvolved, int maxBet){
		chipCount = potVal;
		this.playersInvolved = playersInvolved;
		this.maxBet = maxBet;
	}
	public List<String> getPlayersInvolved() {
		return playersInvolved;
	}
	public void setPlayersInvolved(List<String> playersInvolved) {
		this.playersInvolved = playersInvolved;
	}
	public int getChipCount() {
		return chipCount;
	}
	public void addChips(int chipsToAdd){
		this.chipCount += chipsToAdd;
	}
	public void setchipCount(int potValue) {
		this.chipCount = potValue;
	}
	public int getMaxBet() {
		return maxBet;
	}
	public void setMaxBet(int maxBetAllowedInPot) {
		this.maxBet = maxBetAllowedInPot;
	}
}
