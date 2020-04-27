package application;

public class Card {
	Rank myRank;
	Suit mySuit;

	public Card(Rank myRank, Suit mySuit) {
		this.myRank = myRank;
		this.mySuit = mySuit;
	}
	public Rank getRank() {
		return myRank;
	}
	public Suit getSuit() {
		return mySuit;
	}
	
	public String toString() {
		return "\nSuit:" + mySuit + "\nRank:" + myRank + "\n";
	}
}
