package application;

public class GameEngine {
	private Deck d;
	private int playerScore;
	private int dealerScore;
	private boolean playerTurnOver;
	private String dealerHistory;
	private String playerHistory;
	private boolean gameOver;

	public GameEngine() {
		this.d = new Deck();
		d.shuffleDeck();
		playerScore = 0;
		dealerScore = 0;
		playerTurnOver = false;
		gameOver = false;
		dealerHistory = "";
		playerHistory = "";
		addPlayerScore();
		d.removeTopCard();
		addPlayerScore();
		d.removeTopCard();
		addDealerScore();
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public String getDealerHistory() {
		return dealerHistory;
	}

	public String getPlayerHistory() {
		return playerHistory;
	}

	public boolean getPlayerTurnOver() {
		return playerTurnOver;
	}

	public void setPlayerTurnOver(boolean playerTurnOver) {
		this.playerTurnOver = playerTurnOver;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getDealerScore() {
		return dealerScore;
	}

	public Deck getDeck() {
		return (d);
	}

	public String getTopCardString() {
		return (d.getTopCard().toString());
	}

	public void addPlayerScore() {
		playerHistory += "  " + d.getTopCard().toString();

		playerScore += d.getTopCard().getRank().getRankValue();
	}

	public void addDealerScore() {
		dealerHistory += "  " + d.getTopCard().toString();
		dealerScore += d.getTopCard().getRank().getRankValue();
		d.removeTopCard();
	}

	public boolean checkIfPlayerLost() {
		return (playerScore > 21 ? true : false);

	}

	public boolean checkIfDealerLost() {
		return (dealerScore > 21 ? true : false);
	}

	public String findWinner() {
		gameOver = true;
		if (checkIfPlayerLost()) {
			return ("Dealer Wins");
		}

		if (checkIfDealerLost()) {
			return ("Player Wins");
		}

		if (dealerScore > playerScore)
			return ("Dealer Wins");

		if (dealerScore < playerScore)
			return ("Player Wins");

		return ("It's a tie");
	}

	public void dealersTurn() {
		while (checkIfDealerLost() == false) {
			if (dealerScore >= 17) {
				break;
			} else {
				addDealerScore();
			}
		}
	}

}
