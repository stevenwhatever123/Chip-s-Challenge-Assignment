import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;

/**
 * A player class which carries the data while playing the game.
 * 
 * @author Nikko Pang,Sebastian Fletcher.
 * @version 1.6
 */

public class Player extends Tile {

	private Inventory inventory;
	private Profile profile;
	private int[] coordinates = new int[2];
	private int posX;
	private int posY;
	private boolean isAlive = true;
	private Tile underPlayer;
	private String playerState = "forward";

	/**
	 * Sets the default position of the player and creates and empty inventory.
	 * 
	 * @param posX The x-axis coordinate of the player on the map.
	 * @param posY The y-axis coordinate of the player on the map.
	 * @throws IOException If the image file is not found.
	 */
	public Player(int posX, int posY) throws IOException {
		Ground ground = new Ground();
		this.name = "Player";
		setCoordinates(posX, posY);
		setImageLink("PlayerFront.PNG");
		setUnderPlayer(ground);
		inventory = new Inventory();
		setRefID('P');
		setEnemyWalkable(true);

	}

	/**
	 * Sets the coordinates of the player.
	 * 
	 * @param posX The x-axis coordinate of the player on the map.
	 * @param posY The y-axis coordinate of the player on the map.
	 */
	private void setCoordinates(int posX, int posY) {
		setPosX(posX);
		setPosY(posY);
		coordinates[0] = posX;
		coordinates[1] = posY;
	}

	/**
	 * Shows the exact coordinates of the player at the time.
	 * 
	 * @return coordinates The x and y coordinates of the player.
	 */
	public int[] getCoordinates() {
		return coordinates;
	}

	/**
	 * Set the x-axis coordinate of the player.
	 * 
	 * @param posX The x-axis coordinate.
	 */
	private void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Returns the x-axis coordinate of the player.
	 * 
	 * @return posX The x-axis of the player.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Sets the y-axis coordinate of the player.
	 * 
	 * @param posY The y-axis coordinate of the player.
	 */
	private void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Returns the y-axis coordinate of the player.
	 * 
	 * @return posY The y-axis coordinate of the player.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Moves the player next to connected teleporter on the map. It will always land
	 * on the ((x+1), y) coordinate of the linked teleporter.
	 * 
	 * @param p The teleporter linked to the one that it stepped on.
	 */
	public void teleportPlayer(Teleporter p) {
		int newX = p.getPosX() + 1;
		int newY = p.getPosY();
		this.setCoordinates(newX, newY);
	}

	/**
	 * Adds an item into the player's inventory.
	 * 
	 * @param cIndexNum The index number of the object added.
	 */
	public void addItem(char refID) {
		int charToNum = Character.getNumericValue(refID);
		Integer cRefID = (Integer) charToNum;
		inventory.addItem(cRefID);
	}

	/**
	 * Removes an item from the inventory.
	 * 
	 * @param refID The reference id of the item.
	 */
	public void removeItem(char refID) {
		int charToNum = Character.getNumericValue(refID);
		Integer cRefID = (Integer) charToNum;
		inventory.removeItem(cRefID);
	}

	/**
	 * Checks the inventory whether it has the item based on the index number.
	 * 
	 * @param itemID The index number of the item.
	 * @return True if the item is found.
	 * @return false if the item is not found.
	 */
	public boolean checkInventory(char refID) {
		if (inventory.haveItem(refID)) {
			return true;
		}
		return false;
	}

	/**
	 * It saves the current game so that it can be loaded again in the future.
	 * 
	 * @param levelNum       The level of difficulty of the game.
	 * @param time           The time which the player has played the game so far.
	 * @param savedMap       The current condition of the map.
	 * @param savedInventory The player's current inventory.
	 */
	public void save(int levelNum, int time, String savedMap, Inventory savedInventory) {
		profile.saveLevel(levelNum, time, savedMap, savedInventory);
	}

	/**
	 * Shows whether the player is still alive and playing the game.
	 * 
	 * @return isAlive Condition of the player within the game.
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Sets whether the player is alive or not within the game.
	 * 
	 * @param isAlive Checks if the player is alive or not.
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * Updates the player's coordinates accordingly to the user's input based on the
	 * arrow keys.
	 * 
	 * @param direction The direction of the tile which the player is moving to.
	 */
	public void movePlayer(String direction) {
		int newX = getPosX();
		int newY = getPosY();

		switch (direction) {
		case "Up":
			newY--;
			break;
		case "Down":
			newY++;
			break;
		case "Right":
			newX++;
			break;
		case "Left":
			newX--;
			break;
		}

		setPosX(newX);
		setPosY(newY);
		setCoordinates(newX, newY);
	}

	/**
	 * This method sets the image of the player facing different directions.
	 * 
	 * @param image Image of the player.
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public void setImageLink(Image image) throws FileNotFoundException {
		if (playerState.equals("front")) {
			setImageLink("PlayerFront.PNG");
		} else if (playerState.equals("back")) {
			setImageLink("PlayerBack.PNG");
		} else if (playerState.equals("left")) {
			setImageLink("PlayerLeft.PNG");
		} else if (playerState.equals("right")) {
			setImageLink("PlayerRight.PNG");
		}
	}

	/**
	 * This method turns the player to left.
	 * 
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public void turnLeft() throws FileNotFoundException {
		playerState = "left";
		setImageLink("PlayerLeft.PNG");
	}

	/**
	 * This method turns the player to right.
	 * 
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public void turnRight() throws FileNotFoundException {
		playerState = "right";
		setImageLink("PlayerRight.PNG");
	}

	/**
	 * This method turns the player forwards.
	 * 
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public void turnForward() throws FileNotFoundException {
		playerState = "forward";
		setImageLink("PlayerFront.PNG");
	}

	/**
	 * This method turns the player backwards.
	 * 
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public void turnBack() throws FileNotFoundException {
		playerState = "back";
		setImageLink("PlayerBack.PNG");
	}

	/**
	 * This method sets the tile underneath the player.
	 * 
	 * @param tile Represents the tile being checked.
	 */
	public void setUnderPlayer(Tile tile) {
		this.underPlayer = tile;
	}

	/**
	 * This method returns the tile underneath the player.
	 * 
	 * @return tile under the player.
	 */
	public Tile getUnderPlayer() {
		return this.underPlayer;
	}

	/**
	 * This method returns the player's inventory.
	 * 
	 * @return player's inventory.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * This method sets the player's inventory.
	 */
	public void setInventory(Inventory inv) {
		this.inventory = inv;
	}

}