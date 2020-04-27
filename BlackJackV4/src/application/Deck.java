package application;
import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> fullDeck;
	
	
	public Deck(){
    fullDeck = new ArrayList<Card>();
	for(int s= 0; s<4; ++s) {
		for(int r = 0; r<13; ++r) {
			Card card = new Card(Rank.values()[r], Suit.values()[s]);
			fullDeck.add(card);
		}
	}
	}
	
	public void shuffleDeck(){ // shuffles the deck
        Card temp;
        int tempIndex;
        int tempIndex2;
        for(int i = 0; i < fullDeck.size(); ++i){
            tempIndex = ((int)(Math.random()*fullDeck.size()));
            tempIndex2 = ((int)(Math.random()* fullDeck.size()));
            temp = fullDeck.get(tempIndex);
            fullDeck.set(tempIndex, fullDeck.get(tempIndex2));
            fullDeck.set(tempIndex2, temp);
        }
    }
	
	
	public Card getTopCard() {
		return fullDeck.get(0);
	}
	
	public void removeTopCard() {
		fullDeck.remove(0);
	}
	
	
	public String toString() {
		String results = "";
		for(Card c: fullDeck ) {
			results += c.toString();
		}
		return results;
	}

}

