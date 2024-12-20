package hutchison.grant;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Purpose: Shut The Box Game With Two Player Gameplay
 * @author Turner Aucoin
 * @date December 20th, 2024
 */
public class GUIDriver extends Application {

	Die d1 = new Die();
	Die d2 = new Die();
	private Game game = new Game(5);
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);
		
		// Create and display the title
		Label title = new Label("Shut The Box");
		Label roundNum = new Label("Round: " + game.getCurrentRound());
		Label playerTurn = new Label("It's Player #" + game.getCurrentPlayer() + "'s Turn!");
		vbox.getChildren().addAll(title,roundNum,playerTurn);
		HBox tileBox = new HBox(10);
		
		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];
		
		for (int i=0; i<tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i+1));
			tiles[i] = new Tile(i+1);
			tileBox.getChildren().add(tileBtns[i]);
			tileBtns[i].setStyle("-fx-background-color: lightgrey");
		}
		
		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);
		
		Button btnRoll1 = new Button("ROLL 1 DICE");
		btnRoll1.setDisable(true);
		Button btnRoll2 = new Button("ROLL 2 DICE");
		Button btnLock = new Button("LOCK IN");
		Button btnDone = new Button("DONE");
		Label result = new Label("Result");
		Label lblValue = new Label(); // output of results
		vbox.getChildren().addAll(btnRoll1,btnRoll2,result,lblValue,btnLock,btnDone);
		vbox.setAlignment(Pos.CENTER);
		
		
		// Player Score Labels
		Label lblScore1 = new Label("Total Player Score 1: ");
		Label valueScore1 = new Label(String.valueOf(game.getPlayer1Score()));
		Label lblScore2 = new Label("Total Player Score 2: ");
		Label valueScore2 = new Label(String.valueOf(game.getPlayer2Score()));
		Label gameResult = new Label("");
		Button resetGame = new Button("RESET GAME");
		resetGame.setDisable(true);
		vbox.getChildren().addAll(lblScore1, valueScore1, lblScore2, valueScore2, gameResult, resetGame);
		
		
		btnRoll1.setOnAction(e -> {
			lblValue.setText(String.valueOf(d1.roll()));
			btnLock.setDisable(false);
			btnRoll1.setDisable(true);
		});
		
		
		btnRoll2.setOnAction(e -> {
			lblValue.setText(String.valueOf(d1.roll()+ d2.roll()));
			btnRoll2.setDisable(true);
			btnLock.setDisable(false);
		});
		
		btnLock.setOnAction(e -> {
			int count = 0;
			for (Button b : tileBtns) {
				if (b.getStyle().equals("-fx-background-color:#32CD32")) {
					count += Integer.valueOf(b.getText());
				}
				
			}
			int diceRoll = Integer.valueOf(lblValue.getText());
			if (count == diceRoll) {
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().equals("-fx-background-color:#32CD32")) {
							tileBtns[i].setDisable(true);
							tileBtns[i].setStyle("-fx-background-color:black");
					}
				}
				btnRoll2.setDisable(false);
				btnLock.setDisable(true);
				
			}
			else {
				System.out.println("Invalid");
			}
			
			if (tileBtns[6].isDisable() == true && tileBtns[7].isDisable() == true && tileBtns[8].isDisable() == true) {
				btnRoll2.setDisable(true);
				btnRoll1.setDisable(false);
			}
			
		});
		
		btnDone.setOnAction(e ->{
			int nums1 = 0;
			for (Button b : tileBtns) {
				if (!b.getStyle().equals("-fx-background-color:black")) {
					nums1 += Integer.valueOf(b.getText());
				}
				b.setDisable(false);
				b.setStyle("-fx-background-color: lightgrey");
			}
			lblValue.setText(String.valueOf("Score: " + nums1));
			btnRoll1.setDisable(true);
			btnRoll2.setDisable(false);
			
			game.addScore(nums1);
			game.nextTurn();
			
			valueScore1.setText(String.valueOf(game.getPlayer1Score()));
			valueScore2.setText(String.valueOf(game.getPlayer2Score()));
			roundNum.setText("Round: " + game.getCurrentRound());
			playerTurn.setText("It's Player #" + game.getCurrentPlayer() + "'s Turn!");
			
			if (game.isGameOver() == true) {
				resetGame.setDisable(false);
				btnLock.setDisable(true);
				btnRoll1.setDisable(true);
				btnRoll2.setDisable(true);
				btnDone.setDisable(true);
				if (game.getPlayer1Score() > game.getPlayer2Score()) {
					gameResult.setText("Player 2 Wins! Congratulations!");
				}
				else if (game.getPlayer1Score() < game.getPlayer2Score()) {
					gameResult.setText("Player 1 Wins! Congratulations!");
				}
				else {
					gameResult.setText("It's A Tie!");
				}
			}
			
		});
		
		resetGame.setOnAction(e -> {
			btnRoll2.setDisable(false);
			btnLock.setDisable(false);
			btnDone.setDisable(false);
			game.resetGame();
			
			valueScore1.setText(String.valueOf(game.getPlayer1Score()));
			valueScore2.setText(String.valueOf(game.getPlayer2Score()));
			roundNum.setText("Round: " + game.getCurrentRound());
			playerTurn.setText("It's Player #" + game.getCurrentPlayer() + "'s Turn!");
			gameResult.setText("");
			
			resetGame.setDisable(true);
		});
		
		// Select Code
		for (Button b : tileBtns) {
			b.setOnAction(e -> {
				if (b.getStyle().equals("-fx-background-color:#32CD32")) {
					b.setStyle("-fx-background-color:lightgrey");
				}
				else {
					b.setStyle("-fx-background-color:#32CD32");
				}
			});
		}
		
		Scene scene = new Scene(vbox,500,500);
		stage.setScene(scene);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
