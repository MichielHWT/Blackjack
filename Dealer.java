import java.util.ArrayList;

public class Dealer{
	/*
		The Dealer takes cards just like Player, but will never take another card when the sum of cards equals or exceeds 17.
		The dealer hand is closed the first round.
	*/
	
	private int sumOfPoints;
	private ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	public Dealer(){
		
	}
	
	public boolean askCardFromDeck(Card newCard){
		boolean dealerPass = false;
		if(sumOfPoints < 18){
			dealerHand.add(newCard);
		}
		else{
			dealerPass = true;
		}
		sumCardPoints();
		System.out.println("Sum of cards: " + sumOfPoints);
		return dealerPass;
	}
	
	private void sumCardPoints(){
		sumOfPoints = 0;
		for (int cardNumber = 0; cardNumber < dealerHand.size(); ++cardNumber){
			sumOfPoints = sumOfPoints + dealerHand.get(cardNumber).getCardPoints();
		}
		if (sumOfPoints > 21){
			System.out.println("Your points exceed 21, you are out!");
		}
		else if (sumOfPoints == 21){
			System.out.println("BLACKJACK!");
		}
	}
	
	public String showDealerHand(){
		String dealerHandString = "";
		for (int cardNumber = 0; cardNumber < dealerHand.size(); ++cardNumber)
		{
			if(dealerHand.size() == 2){
				dealerHandString = dealerHandString + dealerHand.get(0).getCardName() + "\t" + "*face down*";
				break;
			}
			else{
				dealerHandString = dealerHandString + dealerHand.get(cardNumber).getCardName() + "\t";
			}
		}
		return dealerHandString;
	}
	
	public void endGame(){
		//Print when points are > 21 or leaves table
		//update totalScore
	}
}