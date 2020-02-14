import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * This class represents a tile on a map.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 2.0
 */

public class Tile {

	protected boolean playerWalkable;
	protected boolean enemyWalkable;
	protected Image imageLink;
	protected char refID;
	protected String name;

	/**
	 * Changes the state of walkability of the player on the tile.
	 * 
	 * @param playerWalkable The state of walkability of the player.
	 */
	protected void setPlayerWalkable(boolean playerWalkable) {
		this.playerWalkable = playerWalkable;
	}

	/**
	 * Gets the state of walkability of the player on the tile.
	 * 
	 * @return The state of walkability of the player.
	 */
	public boolean isPlayerWalkable() {
		return this.playerWalkable;
	}

	/**
	 * Changes the state of walkability of the enemy on the tile.
	 * 
	 * @return The state of walkability of the enemy.
	 */

	protected void setEnemyWalkable(boolean bool) {
		this.enemyWalkable = bool;
	}

	/**
	 * Gets the state of walkability of the enemy on the tile.
	 * 
	 * @return The state of walkability of the enemy.
	 */
	public boolean isEnemyWalkable() {
		return this.enemyWalkable;
	}

	/**
	 * Reads and set the image from the file and set the image variable.
	 * 
	 * @param fileName The name of of the image in the file.
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	protected void setImageLink(String fileName) throws FileNotFoundException {
		this.imageLink = new Image(ImageReader.readImage(fileName));
	}

	/**
	 * Gets the image link of the tile.
	 * 
	 * @return The image link of the tile
	 */
	public Image getImageLink() {
		return this.imageLink;
	}

	/**
	 * Sets the reference id of each class to indicate its type.
	 * 
	 * @param refID The reference id of the tile
	 */
	protected void setRefID(char refID) {
		this.refID = refID;
	}

	/**
	 * Gets the reference id of the tile.
	 * 
	 * @return The reference id of the tile.
	 */
	public char getRefID() {
		return this.refID;
	}

	/**
	 * Changes the name of the class.
	 * 
	 * @param name The name of the tile.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the class.
	 * 
	 * @return The name of the class.
	 */
	public String getName() {
		return this.name;
	}

}