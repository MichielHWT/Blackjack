public class Card{
	/*
		A card has a suit and one of the following three: face, pip, ace.
		Each card has a value in points awarded to it dependent on the face, pip or ace
		- face = 10 points
		- pip = points is equal to the number of pips
		- ace = 11 points (or 1 point, but for the first version this is fixed to 11)
	*/

	//Which cards are in the game
	//What are the points assigned to the cards
	
	private int points;
	private char suit;
	private int pip = 0;
	private char face;

	
	public Card(char suit, int pip){
		this.suit = suit;
		this.pip = pip;
		this.points = pip;
	}
	
	public Card(char suit, char face){
		this.suit = suit;
		this.face = face;
		if (face == 'A'){
			this.points = 11;
		}
		else{
			this.points = 10;
		}
	}
	
	public String getCardName(){
		String cardName;
		if(this.pip == 0){
			cardName = "" + suit + face + "";
		}
		else{
			cardName = "" + suit + pip + "";
		}
		return cardName;
	}
	
	public int getCardPoints(){
		return points;
	}
	
	public char getFace(){
		return face;
	}
}