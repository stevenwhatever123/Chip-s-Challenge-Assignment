import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class represents as the controller for MainMenu.fxml. This class
 * generates a menu scene when starting the game.
 * 
 * @author Steven Ho
 * @version 1.1
 */
public class MainMenuController {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private ImageView menuPhoto;

	@FXML
	private Label gameName;

	@FXML
	private Button newGame;

	@FXML
	private Button loadGame;

	@FXML
	private Button leaderboard;

	@FXML
	private Button quit;

	@FXML
	private Label messageOfTheDay;

	/**
	 * Handles the mouse event when the leaderboard button is clicked.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If LeaderBoard.fxml does not exist.
	 */
	@FXML
	void leaderboardClicked(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) gameName.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the event when load game button is clicked.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If LoadGame.fxml does not exist.
	 */
	@FXML
	void loadGameClicked(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) gameName.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the event when the new game button is clicked.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If NewGame.fxml does not exist.
	 */
	@FXML
	void newGameClicked(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) gameName.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the event when the quit button is clicked. It will close the current
	 * game window.
	 * 
	 * @param event The mouse click.
	 */
	@FXML
	void quitClicked(MouseEvent event) {
		Stage stage = (Stage) quit.getScene().getWindow();
		stage.close();
	}

	/**
	 * Initialises the window when the game is opened.
	 * 
	 * @throws IOException If files cannot be read.
	 */
	public void initialize() throws IOException {
		gameName.setAlignment(Pos.CENTER);
		gameName.setText("BOMBERMAN");
		messageOfTheDay.setWrapText(true);
		messageOfTheDay.setText(MessageOfTheDay.GetMessageOfTheDay());
	}

	/**
	 * Creates a main menu controller which will throw an IOException
	 * 
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public MainMenuController() throws IOException {
	}

}
