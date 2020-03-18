//package nl.workingtalent.blackjack

import java.util.ArrayList;

public class Player{
	
	private String playerName;
	private int playerNumber;
	private int bet;
	private int totalScore;
	private int sumOfPoints = 0;
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	//private Card[] playerHand = Card[12];
	private boolean blackjack;
	
	//
	
	public Player(String playerName, int playerNumber){
		//super();
		this.playerNumber = playerNumber;
		if (playerName.equals("") == true){
			this.playerName = "Player" + playerNumber;
		}
		else{
			this.playerName = playerName;
		}
		this.totalScore = 10;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void askCardFromDeck(Card newCard){
		playerHand.add(newCard);
		sumCardPoints();
		//System.out.println("Sum of cards: " + sumOfPoints);
	}
	
	private void sumCardPoints(){
		sumOfPoints = 0;
		int numberAce = 0;
		for (int cardNumber = 0; cardNumber < playerHand.size(); ++cardNumber){
			sumOfPoints = sumOfPoints + playerHand.get(cardNumber).getCardPoints();
			if (playerHand.get(cardNumber).getFace() == 'A'){
				numberAce += 1;
			}
		}
		//The value of A is 11 or 1. If due to A=11 the sum of points > 21, becomes A=1.
		for (int i = 0; i < numberAce; i++){
			if (sumOfPoints > 21){
				sumOfPoints -= 10;
			}
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
	
	public int getSumOfPoints(){
		return sumOfPoints;
	}
	public void setTotalScore(char win){
		if (win == 'w' && this.blackjack == true){ //Win by blackjack
			this.totalScore += (int)(1.5*this.bet);
		}
		else if(win == 'w' && this.blackjack == false){ //Win by beating dealer
			this.totalScore += (this.bet);
		}
		else if(win == 't'){ //Tie with dealer
			this.totalScore = this.totalScore;
		}
		else if(win == 'l'){ //Lose to dealer
			this.totalScore -= this.bet;			
		}
		else{
			System.out.println("Error: input to setTotalScore is not one of the following: 'w', 't', 'l'.");
		}
	}
	
	public void setBlackjack(boolean blackjack){
		this.blackjack = blackjack;
	}
	
	public boolean getBlackjack(){
		return blackjack;
	}
	
	public int getTotalScore(){
		return totalScore;
	}
	
	public void setBet(int bet){
		this.bet = bet;
		this.totalScore -= bet;
	}
	
	public int getBet(){
		return bet;
	}
	public void resetHand(){
		this.playerHand.clear();
	}
}