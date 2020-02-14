import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents a door tile which requires a number of tokens to open.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.4
 */

public class TokenDoor extends Tile {

	/*
	 * The number of tokens required to open the door.
	 */
	public final int NUM_OF_TOKENS = 3;

	/*
	 * The image of the door when it is open.
	 */
	private String openDoorImageLink = "Ground.jpg";

	/*
	 * The status of the door if it is opened or not
	 */
	private boolean doorOpened;

	/**
	 * Creates a token door tile and sets its default properties.
	 * 
	 * @throws FileNotFoundException if image is not found
	 */
	public TokenDoor() throws FileNotFoundException {
		setRefID('J');
		setName("Token Door");
		setPlayerWalkable(false);
		setEnemyWalkable(false);
		setImageLink("TokenDoor.PNG");
		this.doorOpened = false;
	}

	/**
	 * Changes the token door tile's properties when it is opened.
	 * 
	 * @throws IOException if image is not found
	 */
	public void openDoor() throws IOException {
		Ground ground = new Ground();
		setPlayerWalkable(true);
		setEnemyWalkable(true);
		setImageLink("Ground.jpg");
		this.doorOpened = true;
	}

	/**
	 * This method gets the status of the door if it is open
	 * 
	 * @return true if the door is open. Otherwise, false
	 */
	public boolean isOpened() {
		return this.doorOpened;
	}

	/**
	 * This method gets the number of dynamites required to open the token door
	 * 
	 * @return the number of dynamites required
	 */
	public int getRequiredDynamite() {
		return this.NUM_OF_TOKENS;
	}

}