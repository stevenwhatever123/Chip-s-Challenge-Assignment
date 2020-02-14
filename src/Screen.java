import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * This class creates the screen.
 * 
 * @author Steven Ho
 * @version 1.4
 */
public class Screen extends Application {

	/**
	 * The main method which calls the other classes.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates the stage and scene of the whole application.
	 */
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		javafx.scene.Scene scene = new javafx.scene.Scene(root);
		primaryStage.setScene(scene);
		scene.getRoot().requestFocus();
		primaryStage.setTitle("Test");
		primaryStage.show();
	}

}