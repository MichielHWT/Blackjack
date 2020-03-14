//package nl.workingtalent.blackjack

import java.util.Scanner;

public class Blackjack{

	public static void main(String[] args){
		
		Scanner textInput = new Scanner(System.in);
		int numberOfPlayers = 1;
		int roundNumber = 1;
		boolean dealerPass = false;
		int quitCounter = 0;
		String choiceInputString;
		
		while(true){
			System.out.println("With how many players do you want to play Blackjack? (1/2)");
			String numberOfPlayersString = textInput.nextLine();
			numberOfPlayers = Integer.parseInt(numberOfPlayersString);
			if (numberOfPlayers > 0 && numberOfPlayers <= 2){
				System.out.println(numberOfPlayers);
				break;
			}
			else{
				System.out.println("Please chose one of the following number of Players: 1");
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
	
		//Declare array with cards
		Deck deck = new Deck();
		while(true){
			System.out.println("With how many stacks (52 cards per stack) do you want to play? (1/2/3/4)");
			int numberOfStacks = Integer.parseInt(textInput.nextLine());
			if(numberOfStacks > 0 && numberOfStacks < 5){
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
		//deck.showDeck();
		
		while(quitCounter < numberOfPlayers){
			//Start of the game everyone gets two cards
			System.out.println("Start of round " + roundNumber);
			System.out.println("All players have set their bet");
			System.out.println("The dealer hands out two cards to each player");
			System.out.println("The first card is handed by the dealer face up");
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
				System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
			}
			System.out.println("The dealer hands itself one card face up");
			dealerPass = dealer.askCardFromDeck(deck.getCardFromDeck());
			System.out.println("Dealer: " + dealer.showDealerHand());
			System.out.println("The second card is handed by the dealer face up");
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
				System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
			}
			System.out.println("The dealer hands itself one card face down");
			dealerPass = dealer.askCardFromDeck(deck.getCardFromDeck());
			System.out.println("Dealer: " + dealer.showDealerHand());
			
			//Turn of all players to ask for new cards
			for (int playerNumber = 0; playerNumber < (playerArray.length); ++playerNumber){
				while(true){
					System.out.println("Next turn to decide for " + playerArray[playerNumber].getPlayerName() + ".  (c/p/q)");
					choiceInputString = textInput.nextLine();
					if(choiceInputString.equals("c") == true){
						//Take new card
						playerArray[playerNumber].askCardFromDeck(deck.getCardFromDeck());
						System.out.println(playerArray[playerNumber].getPlayerName() + ": " + playerArray[playerNumber].showPlayerHand());
					}
					else if(choiceInputString.equals("p") == true){
						//Turn to next player
						break;
					}
					else if(choiceInputString.equals("q") == true){
						//Quit game only for this player
						playerArray[playerNumber] = null;
						++quitCounter;
						break;
					}
					else{
						System.out.println("Please enter one of the following: c, p or q");
					}
				}	
				//Turn to the dealer
				while(dealerPass == false){
					dealerPass = dealer.askCardFromDeck(deck.getCardFromDeck());
				}
				//Get player and dealer points and decide who wins
				
				//Everyone loses cards at the end of the round
				//New shuffle needed when deck < 50%
			}		
			++roundNumber;
		}		
	}
}