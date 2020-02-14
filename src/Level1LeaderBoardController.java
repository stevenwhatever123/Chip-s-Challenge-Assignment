import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class represents as the controller for Level1LeaderBoard.fxml. This
 * class display the top 3 players who completed level 1
 * 
 * @author Steven Ho
 * @version 1.3
 */
public class Level1LeaderBoardController {

	@FXML
	private ListView<LeaderboardRecord> level1LeaderBoardList;

	public ObservableList<LeaderboardRecord> list;

	@FXML
	private Button backButton;

	/**
	 * Handles the mouse event when the user clicked on the back button.
	 * 
	 * @param event The mouse clicked.
	 * @throws IOException If there is no files to read in.
	 */
	@FXML
	void backButtonClicked(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) backButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Initialise the view of the whole level 1 leaderboard screen.
	 */
	public void initialize() {
		list = FXCollections.observableArrayList(FileInputOutput.readLeaderboard("Leaderboard1.txt"));
		level1LeaderBoardList.setItems(list);
		level1LeaderBoardList.setFixedCellSize(61);
	}

}
