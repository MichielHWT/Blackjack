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
	
	public void askCardFromDeck(Card newCard){
		dealerHand.add(newCard);
		sumCardPoints();
		//System.out.println("Sum of cards: " + sumOfPoints);
	}
	
	private void sumCardPoints(){
		sumOfPoints = 0;
		for (int cardNumber = 0; cardNumber < dealerHand.size(); ++cardNumber){
			sumOfPoints = sumOfPoints + dealerHand.get(cardNumber).getCardPoints();
		}
		/*
		if (sumOfPoints > 21){
			System.out.println("Your points exceed 21, you are out!");
		}
		else if (sumOfPoints == 21){
			System.out.println("BLACKJACK!");
		}
		*/
	}
	
	public String showDealerHand(){
		String dealerHandString = "";
		for (int cardNumber = 0; cardNumber < dealerHand.size(); ++cardNumber)
		{
			if(dealerHand.size() == 2){
				if (sumOfPoints == 21){
					dealerHandString = dealerHandString + dealerHand.get(cardNumber).getCardName() + "\t";
				}
				else{
					dealerHandString = dealerHandString + dealerHand.get(0).getCardName() + "\t" + "*face down*";
				}
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
	
	public int getSumOfPoints(){
		return sumOfPoints;
	}
	public void resetHand(){
		this.dealerHand.clear();
	}
}