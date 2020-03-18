import java.lang.Math; //import Math.random()

public class Deck{
	/*
		A deck in Black Jack consists of one stack of 52 unique cards or multiple stacks of N*52 cards.
		The deck of 52 unique cards is created from suits which each 3 face-cards, 9 pip-cards and one Ace-card
		- 4 suits: Hearts (H), Clubs (C), Spades (S) and Diamonds (D)
		- 3 face: King (K), Queen (Q), Jack (J)
		- 9 pip: number 2 t/m 10
		- 1 ace: Ace (A)
	*/
	
	private Card[] deckArray;
	static final int numberUniqueCardsInStack = 52;
	private int numberOfCardsInDeck;
	private int deckTop = 0;
	private char[] suitArray = {'H', 'C', 'S', 'D'};
	private char[] faceArray = {'J', 'Q', 'K', 'A'};

	public Deck(){
		
	}
	
	public void createNewDeck(int numberOfStacks){
		this.numberOfCardsInDeck = numberOfStacks*numberUniqueCardsInStack;
		this.deckArray = new Card[numberOfCardsInDeck];
		//Create new deck with 52 unique cards in each stack
		int cardNumber = 0;
		for (int stack = 0; stack < numberOfStacks; ++stack){
			cardNumber = stack*numberUniqueCardsInStack;
				for (char suit : suitArray){
					for (int pip = 2; pip < 11; ++pip){
						this.deckArray[cardNumber] = new Card(suit, pip);
						cardNumber = cardNumber + 1;
					}						
					for (char face : faceArray){
						this.deckArray[cardNumber] = new Card(suit, face);
						cardNumber = cardNumber + 1;
					}
				}
		}
	}
	
	public void shuffle(){
		double randomNumber = 0;
		int randomNumberInt = 0;
		Card[] shuffledDeckArray = new Card[numberOfCardsInDeck];
		for (int i = 0; i < numberOfCardsInDeck; ++i){
			boolean indexUsed = false;
			while(indexUsed == false){
				randomNumber = Math.random()*numberOfCardsInDeck;
				randomNumberInt = (int)randomNumber;
				if(deckArray[randomNumberInt] != null){
					shuffledDeckArray[i] = deckArray[randomNumberInt];
					deckArray[randomNumberInt] = null;
					indexUsed = true;
				}
			}
		}
		for (int i = 0; i < numberOfCardsInDeck; ++i){
			deckArray[i] = shuffledDeckArray[i];
		}	
		this.deckTop = 0;
	}
	
	public Card getCardFromDeck(){
		Card topCard = deckArray[deckTop];
		deckArray[deckTop] = null;
		++deckTop;
		return topCard;
	}
	
	public void showDeck(){
		String deckString = "";
		for (int i = deckTop; i < numberOfCardsInDeck; ++i){
			if(deckArray[i] != null){
				deckString = deckString + deckArray[i].getCardName() + "\t";
			}
		}
		System.out.println(deckString);
	}
	
	public int getDeckTop(){
		return deckTop;
	}
	
	public int getNumberOfCardsInDeck(){
		return numberOfCardsInDeck;
	}
}