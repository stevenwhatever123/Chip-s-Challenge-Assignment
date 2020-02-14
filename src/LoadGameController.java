import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
 * This class represents as the controller for LoadGame.fxml. This class
 * generate a scene that the user can choose the profile he/she wants to load
 * and select the level to play
 * 
 * @author Steven Ho, Ethan Young
 * @version 1.2
 */
public class LoadGameController {

	@FXML
	private Button loadButton;

	@FXML
	private Button exitButton;

	@FXML
	private ListView<Profile> data;

	@FXML
	private ListView<Level> level;

	private static Profile p;

	public ObservableList<Profile> list;

	public ObservableList<Level> levelData;

	@FXML
	private Button deleteButton;

	@FXML
	private OutputController output;

	/**
	 * Handles the mouse event when the exit button is clicked.
	 * 
	 * @param event The mouse click
	 * @throws IOException If MainMenu.fxml does not exist.
	 */
	@FXML
	void exitButtonClicked(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		/*
		 * Setting up new stage to switch from
		 */
		Stage stage;
		Parent root;
		stage = (Stage) loadButton.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Handles the mouse event and load the game when the load button is clicked.
	 * 
	 * @param event The mouse event
	 * @throws IOException If Output.fxml does not exist.
	 */
	@FXML
	void loadButtonClicked(MouseEvent event) throws IOException {
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
		OutputController output = loader.getController();
		Parent root = loader.load();
		loader.setController(output);
		stage.setScene(new Scene(root));
		stage.requestFocus();
		stage.show();
	}

	/**
	 * Initialise the list view of all the profiles when load game is read.
	 */
	public void initialize() {
		list = FXCollections.observableArrayList(FileInputOutput.readProfile("Profiles.txt"));
		data.setItems(list);

	}

	/**
	 * Returns the profile that is read by the reader.
	 * 
	 * @return The current player's profile.
	 */
	public Profile getProfile() {
		return p;
	}

	/**
	 * Handles the mouse event when the list view of profiles is clicked by the
	 * user.
	 * 
	 * @param event The mouse click.
	 */
	@FXML
	void dataBoxClicked(MouseEvent event) {
		String name = "";
		try {
			name = data.getSelectionModel().getSelectedItem().getUserName();
			Level[] tempLevelList = FileInputOutput.readLevelData(String.format("%sLevelData.txt", name));

			ArrayList<Level> tempLevels = new ArrayList<Level>();
			for (int i = 0; i < tempLevelList.length; i++) {
				if (tempLevelList[i].completed() == true
						|| !tempLevelList[i].getSavedMap().equalsIgnoreCase(String.format("map%d.txt", i + 1))) {
					tempLevels.add(tempLevelList[i]);
				}
			}

			levelData = FXCollections.observableArrayList(tempLevels);
			level.setItems(levelData);
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Handles the mouse event when the list view of level is clicked by the user.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	@FXML
	void levelClicked(MouseEvent event) throws IOException {
		int levelNum = 0;
		try {
			levelNum = level.getSelectionModel().getSelectedItem().getLevelNum();
			MapLoadData.setLevelNum(levelNum);
			MapLoadData.setProfile(data.getSelectionModel().getSelectedItem());
			MapLoadData.setTime(level.getSelectionModel().getSelectedItem().getSavedTime() - 1);
		} catch (NullPointerException e) {
			System.out.println("Error");
		}

	}

	/**
	 * Handles the mouse event and delete the profile selected when the delete
	 * button is clicked by the user.
	 * 
	 * @param event The mouse click.
	 * @throws IOException When Profiles.txt does not exist.
	 */
	@FXML
	void deleteButtonClicked(MouseEvent event) throws IOException {
		String userName = data.getSelectionModel().getSelectedItem().getUserName();
		FileInputOutput.deleteProfile("Profiles.txt", userName);

		list = FXCollections.observableArrayList(FileInputOutput.readProfile("Profiles.txt"));
		data.setItems(list);

		level.setItems(null);

	}

}
