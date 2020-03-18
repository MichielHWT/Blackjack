//package nl.workingtalent.blackjack

import java.util.Scanner;

public class Blackjack{

	public static void main(String[] args){
		
		Scanner textInput = new Scanner(System.in);
		int numberOfPlayers = 1;
		int roundNumber = 1;
		int quitCounter = 0;
		int numberOfStacks= 1;
		String choiceInputString;
		boolean choiceInputNumeric;
		String gameString = "";
		int sumOfCards = 0;
		int bet;
		boolean dealerBlackjack = false;
		
		while(true){
			System.out.println("With how many players do you want to play Blackjack? (1/2/3/4)");
			String numberOfPlayersString = textInput.nextLine();
			if (numberOfPlayersString.equals("1") ||
				numberOfPlayersString.equals("2") ||
				numberOfPlayersString.equals("3") ||
				numberOfPlayersString.equals("4")){
				numberOfPlayers = Integer.parseInt(numberOfPlayersString);
				System.out.println(numberOfPlayers);
				break;
			}
			else{
				System.out.println("\rPlease chose one of the following number of Players: 1, 2, 3, 4.");
			}
		}
		
		//Create Dealer object
		Dealer dealer = new Dealer();
		//Create new player objects
		Player[] playerArray = new Player[numberOfPlayers];
		for (int playerNumber = 1; playerNumber < (playerArray.length + 1); ++playerNumber){
			System.out.println("Please insert the name of Player " + playerNumber + ".");
			String playerName = textInput.nextLine();
			playerArray[playerNumber - 1] = new Player(playerName, playerNumber);
		}
	
		//Create deck of cards
		Deck deck = new Deck();
		while(true){
			System.out.println("With how many stacks (52 cards per stack) do you want to play? (1/2/3/4)");
			String numberOfStacksString = textInput.nextLine();
			if(numberOfStacksString.equals("1") ||
				numberOfStacksString.equals("2") ||
				numberOfStacksString.equals("3") ||
				numberOfStacksString.equals("4")){
				numberOfStacks = Integer.parseInt(numberOfStacksString);
				deck.createNewDeck(numberOfStacks); //1: one stack of 52 cards
				break;
			}
			else{
				System.out.println("Please enter one of the following numbers: 1, 2, 3 or 4");
			}
		}
		System.out.println("These are all the cards in the deck:");
		deck.showDeck();
		System.out.println("Now the deck will be shuffled.");
		deck.shuffle();
		//deck.showDeck(); //See the result of the shuffle
		
		while(quitCounter < numberOfPlayers){
			System.out.println("Start of round " + roundNumber);
			System.out.println((numberOfPlayers - quitCounter) + " players are in the game.");
			dealerBlackjack = false;
			
			//Set bets in for-loop. 
			//Also possible for player to quit game.
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				
				if(playerArray[playerNumber] == null){
					//Players who quit are skipped
					continue;
				}
				while(true){
					System.out.println(playerArray[playerNumber].getPlayerName() + ", please set your bet.");
					System.out.println("Minimum bet: 1. Maximum bet: " + playerArray[playerNumber].getTotalScore() + ". Enter 'q' to quit game.");
					choiceInputString = textInput.nextLine();
					//Check if the input string is only digits or also characters
					for (char c : choiceInputString.toCharArray()){
						if (Character.isDigit(c)){
							choiceInputNumeric = true;
						}
						else{
							choiceInputNumeric = false;
							break;
						}
					}
						if(choiceInputString.equals("q") == true){
							System.out.println(playerArray[playerNumber].getPlayerName() + " left the game.");
							//Quit game only for this player
							playerArray[playerNumber] = null;
							++quitCounter;
							break;
						}
						else if (choiceInputNumeric = true){
							if(Integer.parseInt(choiceInputString) >= 1 && Integer.parseInt(choiceInputString) <= playerArray[playerNumber].getTotalScore()){
								System.out.println(playerArray[playerNumber].getPlayerName() + " set a bet of " + Integer.parseInt(choiceInputString) + ".");
								playerArray[playerNumber].setBet(Integer.parseInt(choiceInputString));
								break;
							}
							else{
								System.out.println("Please enter a number in between the minimum and maximum bet, or enter 'q' to quit game.");
							}
						}
						else{
							System.out.println("Please enter a number in between the minimum and maximum bet, or enter 'q' to quit game.");
						}
				}
			}
			
			//Dealer hands out cards. Check dealer hand for blackjack (21 points). 
			System.out.println("All players have set their bet."); 
			System.out.println("The dealer hands out two cards to each player.");
			System.out.println("The first card is handed by the dealer face up.");
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				if(playerArray[playerNumber] == null){
					//Players who quit are skipped
					continue;
				}
				playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
				System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
			}
			
			if(quitCounter < numberOfPlayers){
				System.out.println("The dealer hands itself one card face up.");
				dealer.askCardFromDeck(deck.getCardFromDeck());
				System.out.println("Dealer: " + dealer.showDealerHand());
				System.out.println("The second card is handed by the dealer face up");
				for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
					if(playerArray[playerNumber] == null){
						//Players who quit are skipped
						continue;
					}
					playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
					System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
				}
				System.out.println("The dealer hands itself one card face down.");
				dealer.askCardFromDeck(deck.getCardFromDeck());
				System.out.println("Dealer: " + dealer.showDealerHand());
				//Check if Dealer has BlackJack
				if (dealer.getSumOfPoints() == 21){
					System.out.println("Dealer has Blackjack!");
					dealerBlackjack = true;
				}
			}
			
			//Players turn to beat the dealer
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				//Print all hand, scores, names etc.
				
				if(playerArray[playerNumber] == null){
					//Players who quit are skipped
					continue;
				}
				if (playerArray[playerNumber].getSumOfPoints() == 21){
					playerArray[playerNumber].setBlackjack(true);
					//Check if player already has Blackjack. If true, turn to next player.
					System.out.println(playerArray[playerNumber].getPlayerName() + "has Blackjack!");
					//Check if Dealer has blackjack. If false, win get 1.5x the bet.
					if(dealerBlackjack == false){
						System.out.println("The dealer has no blackjack. You win 1.5x your bet!");
						playerArray[playerNumber].setTotalScore('w'); //win = 'w' (win)
					}
					else{
						System.out.println("The dealer also has Blackjack. It is a tie. You get back your bet.");
						playerArray[playerNumber].setTotalScore('t'); //win = 't' (tie)
					}
					continue;
				}
				else{
					//No blackjack for this player
					playerArray[playerNumber].setBlackjack(false);
				}
				while(true){
					System.out.println("Next turn to decide for " + playerArray[playerNumber].getPlayerName() + ".");
					System.out.println("c : Ask for new card.\np: Pass.\nq: Quit game.");
					choiceInputString = textInput.nextLine();
					if(choiceInputString.equals("c") == true){
						//Take new card
						System.out.println("The dealer hands a new card to " + playerArray[playerNumber].getPlayerName() + ".");
						playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
						System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
						
						//Check sum of points
						if (playerArray[playerNumber].getSumOfPoints() > 21){
							System.out.println("The sum of your hand exceeds 21. You busted!");
							System.out.println(playerArray[playerNumber].getPlayerName() + " lost the bet.");
							playerArray[playerNumber].setTotalScore('l');//win = 'l' (lose)
							break;
						}
					}
					else if(choiceInputString.equals("p") == true){
						//Turn to next player
						System.out.println(playerArray[playerNumber].getPlayerName() + " passes.");
						break;
					}
					else if(choiceInputString.equals("q") == true){
						System.out.println(playerArray[playerNumber].getPlayerName() + " left the game.");
						//Quit game only for this player
						playerArray[playerNumber] = null;
						++quitCounter;
						break;
					}
					else{
						System.out.println("Please enter one of the following: c, p or q");
					}					
				}	
			}	
			
			//Print all hand, scores, names etc. 
			//System.out.println();
			
			if (quitCounter < numberOfPlayers){
				//Turn to the dealer. If dealer busts, all player who are < 21 and not blackjack win. 
				while(true){
					if (dealer.getSumOfPoints() < 17){
						dealer.askCardFromDeck(deck.getCardFromDeck());
					}
					else if (dealer.getSumOfPoints() > 21){
						System.out.println("The dealer busts!");
					}
					else{
						break;
					}
				}
			}			
			
			//Get player and dealer points and decide who wins
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){				
				if(playerArray[playerNumber] == null){
					//Players who quit are skipped
					continue;
				}
				if (playerArray[playerNumber].getBlackjack() == false){
					//If player had Blackjack, already paid
					if (playerArray[playerNumber].getSumOfPoints() <= 21 && dealer.getSumOfPoints() > 21){
						System.out.println(playerArray[playerNumber].getPlayerName() + " wins from the busted dealer!");
						System.out.println(playerArray[playerNumber].getPlayerName() + " wins 1x the bet!");
						playerArray[playerNumber].setTotalScore('w'); //win = 'w' (win)
					}
					else if (playerArray[playerNumber].getSumOfPoints() > dealer.getSumOfPoints()){
						System.out.println(playerArray[playerNumber].getPlayerName() + " wins from the dealer!");
						System.out.println(playerArray[playerNumber].getPlayerName() + " wins 1x the bet!");
						playerArray[playerNumber].setTotalScore('w'); //win = 'w' (win)
					}
					else if (playerArray[playerNumber].getSumOfPoints() == dealer.getSumOfPoints()){
						System.out.println(playerArray[playerNumber].getPlayerName() + " ties with the dealer. It's a push!");
						System.out.println(playerArray[playerNumber].getPlayerName() + " wins 1x the bet!");
						playerArray[playerNumber].setTotalScore('t'); //win = 't' (tie)
					}
					else if (playerArray[playerNumber].getSumOfPoints() < dealer.getSumOfPoints()){
						System.out.println(playerArray[playerNumber].getPlayerName() + " loses to the dealer. Bad luck!");
						System.out.println(playerArray[playerNumber].getPlayerName() + " loses the bet!");
						playerArray[playerNumber].setTotalScore('l'); //win = 'l' (lose)
					}
				}
			}
			
			//Everyone loses cards at the end of the round
			dealer.resetHand();
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				if(playerArray[playerNumber] != null){
					playerArray[playerNumber].resetHand();		
				}
			}
			
			//New deck+shuffle needed when deck < 50%, only when playing with more than 1 stack
			if (numberOfStacks == 1){
				deck.createNewDeck(1);
				deck.shuffle();
			}
			else{
				if (deck.getDeckTop() < (0.5*deck.getNumberOfCardsInDeck())){
					deck.createNewDeck(numberOfStacks);
					deck.shuffle();
				}
			}	
			++roundNumber;
		}		
	}
}