import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents as the controller for LeaderBoard.fxml. It produce the
 * screen of the leaderboard menu.
 * 
 * @author Steven Ho
 * @version 1.1
 */
public class LeaderBoardController {

	@FXML
	private Button level1Button;

	@FXML
	private Button level2button;

	@FXML
	private Button level3Button;

	@FXML
	private Button menuButton;

	/**
	 * Handles the mouse event if the user clicked into the level 1 button.
	 * 
	 * @param event The mouse click.
	 * @throws IOException if there is no files to read in.
	 */
	@FXML
	void handleLevel1Action(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");
		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) menuButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Level1LeaderBoard.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the mouse event if the user clicked into the level 2 button.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If there is no files to read in.
	 */
	@FXML
	void handleLevel2Action(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");
		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) menuButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Level2LeaderBoard.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the mouse event if the user clicked into the level 3 button.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If there is no files to read in.
	 */
	@FXML
	void handleLevel3Action(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");
		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) menuButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("Level3LeaderBoard.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the mouse event if the user clicked into the menu button.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If there is no files to read in.
	 */
	@FXML
	void handleMenuAction(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");
		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) menuButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}
}
