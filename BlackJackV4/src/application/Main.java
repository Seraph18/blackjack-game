package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			GameEngine x = new GameEngine();
			primaryStage.setTitle("BlackJack");
			GridPane game = new GridPane();
			Scene gamePhase = new Scene(game, 1000, 1000);
			gamePhase.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(gamePhase);
			Button hit = new Button();
			Button stay = new Button();
			game.addRow(0, hit, stay);
			Label cardOutput = new Label("Player :" + x.getPlayerHistory());
			Label scoreOutput = new Label("Your Score: " + x.getPlayerScore());
			Label dealerScoreOutput = new Label("Dealer: " + x.getDealerHistory());
			Label winnerOutput = new Label();

			game.addRow(1, cardOutput);
			game.addRow(2, scoreOutput);
			game.addRow(3, dealerScoreOutput);
			game.addRow(4, winnerOutput);

			hit.setStyle("-fx-font-size: 32px;");
			stay.setStyle("-fx-font-size: 32px;");
			hit.setMinHeight(200);
			hit.setMinWidth(200);
			stay.setMinHeight(200);
			stay.setMinWidth(200);
			hit.setText("Hit");
			stay.setText("Stay");
			cardOutput.setMinSize(200, 100);
			cardOutput.setStyle("-fx-font-size: 14px;");
			scoreOutput.setMinSize(200, 100);
			scoreOutput.setStyle("-fx-font-size: 14px;");
			dealerScoreOutput.setMinSize(100, 100);
			dealerScoreOutput.setStyle("-fx-font-size: 14px;");
			winnerOutput.setMinSize(100, 100);
			winnerOutput.setStyle("-fx-font-size: 14px;");

			hit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (x.getPlayerTurnOver() == false) {
						if (x.checkIfPlayerLost() == false) {
							x.addPlayerScore();
							cardOutput.setText("Player: " + x.getPlayerHistory());
							if (x.checkIfPlayerLost() == false) {

								scoreOutput.setText("Your Score: " + x.getPlayerScore());
							} else {
								scoreOutput.setText("You Busted! Score: " + x.getPlayerScore());
								x.setPlayerTurnOver(true);
								winnerOutput.setText(x.findWinner());

							}
							x.getDeck().removeTopCard(); // Remove the used card from the deck
						} else {
							scoreOutput.setText("You Busted! Score: " + x.getPlayerScore());
							x.setPlayerTurnOver(true);
							winnerOutput.setText(x.findWinner());

						}
					}
				}
			});

			stay.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					// tell the dealer to make its moves
					x.setPlayerTurnOver(true);
					if (x.getGameOver() == false) {
					x.dealersTurn();
					if (x.checkIfDealerLost()) 
						dealerScoreOutput.setText("Dealer: " + x.getDealerHistory() + " Dealer Busted! Score: " + x.getDealerScore());
					else {
						dealerScoreOutput.setText("Dealer: " + x.getDealerHistory() + " Dealer Score: " + x.getDealerScore());
					}
					winnerOutput.setText(x.findWinner());
					}
				}
			});

			primaryStage.show();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}