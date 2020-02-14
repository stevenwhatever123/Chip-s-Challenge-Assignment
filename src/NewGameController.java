import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class represents as the controller for NewGame.fxml. This class will
 * generate a new profile when the username has been input, and load that
 * profile to OutputController.
 * 
 * @author Steven Ho, Ethan Young
 * @version 1.3
 */
public class NewGameController {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField enterUsername;

	@FXML
	private Button CreateButton;

	@FXML
	private Button exitButton;

	@FXML
	private Label print;

	private static Profile p;

	/**
	 * Handles the mouse event when create button is clicked. It will generate a new
	 * profile based on what the username is and load all data to Output.fxml.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If Profiles.txt does not exist.
	 */
	@FXML
	void handleCreateButtonAction(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");
		if (enterUsername.getText() != null && !enterUsername.getText().isEmpty()) {

			// Creates a new profile.
			p = new Profile(enterUsername.getText(), 1);
			Profile[] listOfProfiles = FileInputOutput.readProfile("Profiles.txt");
			boolean isFound = false;
			for (int i = 0; i < listOfProfiles.length; i++) {
				if (listOfProfiles[i].getUserName().equalsIgnoreCase(p.getUserName())) {
					isFound = true;
					print.setText("This userName has already been Taken!");
				}
			}
			if (!isFound) {
				print.setText("Profile Created!");
				FileInputOutput.addProfile("Profiles.txt", p);
				MapLoadData.setProfile(p);
				MapLoadData.setLevelNum(1);
				MapLoadData.setTime(-1);
	
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
				OutputController output = loader.getController();
				Parent root = loader.load();
				loader.setController(output);
				stage.setScene(new Scene(root));
				stage.requestFocus();
				stage.show();
			}
		}

	}

	/**
	 * Handles the event when exist button is clicked. It will return back to
	 * MainMenu.fxml.
	 * 
	 * @param event The mouse click.
	 * @throws IOException If MainMenu.fxml does not exist.
	 */
	@FXML
	void handleExitButtonAction(MouseEvent event) throws IOException {
		System.out.println("Mouse clicked");

		Stage stage;
		Parent root;
		stage = (Stage) print.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();

	}

	/**
	 * Gets copy of the profile created.
	 * 
	 * @return The profile object.
	 */
	public static Profile getProfile() {
		return p;
	}

	/**
	 * This method initialises nothing.
	 */
	public void initialize() {

	}

	/**
	 * This constructor sets everything to null.
	 * 
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public NewGameController() throws IOException {
	}

}
