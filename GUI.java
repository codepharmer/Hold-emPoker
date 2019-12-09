package edu.cuny.csi.csc330.holdemPoker;

import java.awt.*;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;



public class GUI {	
	
	public final static String IMAGE_FOLDER_LOCATION = "images" + File.separator;
	public final static String POKER_TABLE_IMAGE = IMAGE_FOLDER_LOCATION + "poker_table.png";
	
	private static JFrame mainFrame;
	private static JPanel tablePanel;
	private static JPanel cardPanel;
	private static JPanel buttonPanel;

	private static JLabel messageLabel;
	private static JLabel balanceLabel = new JLabel("Balance: $0.00");
	private static JLabel timeLeftLabel = new JLabel("Time Left: 0:00");
	private static HashMap<Integer, String> cardMap;
	private static Table currTable;
	private static CardDeck theDeck;
	
	public static void initGUI(){
		mainFrame = new JFrame("HoldEm Poker"); 
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setLocation(100, 100);
		mainFrame.setVisible(true);
		cardMap = new HashMap<Integer, String>();
		
		for (int i = 1; i <= theDeck.getDeck().size(); i++) {
			cardMap.put(i, IMAGE_FOLDER_LOCATION + i + ".png");
		}
		
		tablePanel = new JPanel();
		JLabel table = new JLabel(new ImageIcon(POKER_TABLE_IMAGE));
//		table.setBounds(rect(450,250, table.getPreferredSize()));
		tablePanel.add(table);
		mainFrame.add(tablePanel);
		
		cardPanel = new JPanel();
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton button = new JButton("Raise");
		button.addActionListener(new Raise());
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(button, c);

		button = new JButton("Call");
		button.addActionListener(new Call());
		c.gridx = 1;
		buttonPanel.add(button, c);
		
		button = new JButton("Fold");
		button.addActionListener(new Fold());
		c.gridx = 2;
		buttonPanel.add(button, c);
		
		JButton button2 = new JButton("New Game");
		button2.setBounds(rect(500, 300, button.getPreferredSize()));
		button2.addActionListener(new NewGame());
		mainFrame.add(button2);
		
		buttonPanel.setBounds(rect(500, 200, buttonPanel.getPreferredSize()));
		mainFrame.add(buttonPanel);
		messageLabel = new JLabel(newLines(4));
		messageLabel.setBounds(rect(450,250, messageLabel.getPreferredSize()));
		messageLabel.setText("");
		
		balanceLabel = new JLabel("Balance: $999999.99");
		balanceLabel.setBounds(rect(475, 150, balanceLabel.getPreferredSize()));
		balanceLabel.setText("Balance: $0.00");
		
		timeLeftLabel = new JLabel("Time Left: 999:99");
//		
		
		balanceLabel.setVisible(true);
		messageLabel.setVisible(true);
		
		balanceLabel.repaint();
		messageLabel.repaint();
		
		mainFrame.add(balanceLabel);
		mainFrame.add(messageLabel);
		
		
		mainFrame.repaint();
		mainFrame.validate();
	}
	
	
	public static final String newLines(int numLines) {
		StringBuilder sb = new StringBuilder(numLines);
		for (int i = 0; i < numLines; i++) {
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	private static Rectangle rect(int x, int y, Dimension size) {
		Rectangle result = new Rectangle(new Point(x,y), size);
		return result;
	}

	private static JLabel iconizeCard(Vector<String> vector){
		JLabel label = new JLabel(new ImageIcon(cardMap.get(vector)));
		
		return label;
	}

	public static void fillTable(){
		currTable.createPlayers();
		if(currTable.getPlayerCount() == 1){
			gameOver();
		}
		
		for(Player player : currPlayers){
			balanceLabel.setText("Balance: $" + centsToDollars(player.getChipCount()));
		}
		
//		tablePanel.setLayout(new GridBagLayout());
		
		JPanel cardPanel = new JPanel();
		for(Player player : currTable.createPlayers()){
			cardPanel.add(iconizeCard(Player.getHoleCards()));
			cardPanel.add(iconizeCard(Player.getHoleCards()));
		}
		
		cardPanel.validate();
		mainFrame.add(cardPanel);
		mainFrame.validate();
		
	}
	
	private static String centsToDollars(int balance) {
		int cents = balance % 100;
		boolean shouldAddExtraZero = false;
		if (cents < 10) {
			shouldAddExtraZero = true;
		}
		int dollars = (balance - cents) / 100;
		String answer = dollars +"." + cents;
		if (shouldAddExtraZero) {
			answer += "0";
		}
		return answer;
	}

	
	private static final Vector<String> makeSinglePlayerHand(Player p) {
		return Player.getHoleCards();
	}

	// method that creates an entire table
	
	// method that creates five cards in the middle of the table dealt by the dealer
	
	// method that creates a big blind and a small blind 
	
	private static class Raise implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO ask for how much to raise
			
		}
		
	}
	
	private static class Call implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class Fold implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private static class NewGame implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			currTable = new Table();
			buttonPanel.setVisible(true);
			currTable.CardDeck();
			currTable.createPlayers();
			mainFrame.validate();
			fillTable();
		}
		
	}
	
	private static void gameOver(){
		buttonPanel.setVisible(false);
		messageLabel.setText("Game Over");
		mainFrame.validate();
		mainFrame.repaint();
	}
}
