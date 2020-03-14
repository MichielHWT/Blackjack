//package nl.workingtalent.blackjack

import java.util.ArrayList;

public class Player{
	
	private String playerName;
	private int playerNumber;
	private int totalScore;
	private int sumOfPoints = 0;
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	
	//
	
	public Player(String playerName, int playerNumber){
		super();
		this.playerNumber = playerNumber;
		if (playerName.equals("") == true){
			this.playerName = "Player" + playerNumber;
		}
		else{
			this.playerName = playerName;
		}
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void askCardFromDeck(Card newCard){
		playerHand.add(newCard);
		sumCardPoints();
		System.out.println("Sum of cards: " + sumOfPoints);
	}
	
	private void sumCardPoints(){
		sumOfPoints = 0;
		for (int cardNumber = 0; cardNumber < playerHand.size(); ++cardNumber){
			sumOfPoints = sumOfPoints + playerHand.get(cardNumber).getCardPoints();
		}
		if (sumOfPoints > 21){
			System.out.println("Your points exceed 21, you are out!");
		}
		else if (sumOfPoints == 21){
			System.out.println("BLACKJACK!");
		}
	}
	
	public String showPlayerHand(){
		String playerHandString = "";
		for (int cardNumber = 0; cardNumber < playerHand.size(); ++cardNumber)
		{
			playerHandString = playerHandString + playerHand.get(cardNumber).getCardName() + "\t";
		}
		return playerHandString;
	}
	
	public void endGame(){
		//Print when points are > 21 or leaves table
		//update totalScore
		//for (int cardNumber = 0; cardNumber < playerHand.size(); ++cardNumber){
		//	playerHand.get(cardNumber) = null;
		//}
	}
	
}