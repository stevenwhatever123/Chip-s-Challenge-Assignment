import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This Flipper class creates a flipper object with Tile as it's superclass.
 * 
 * @author Steven Ho & Nikko Pang
 * @version 1.2
 */

public class Flipper extends Tile {

	private boolean pickedUp = false;

	/**
	 * Creates Flipper object.
	 * 
	 * @throws IOException if image is not found.
	 */
	public Flipper() throws IOException {
		setRefID('3');
		setName("Flipper");
		setImageLink("Flippers.PNG");
		setPlayerWalkable(true);
		setEnemyWalkable(false);
	}

	/**
	 * Changes the flippers tile to a ground tile when it is picked up. and add
	 * flipper to the player's inventory.
	 * 
	 * @param p The player
	 * @throws FileNotFoundException If the file of the image can't be found.
	 */
	public void pickUpFlipper(Player p) throws FileNotFoundException {
		setImageLink("Ground.jpg");
		setEnemyWalkable(true);
		p.addItem(this.getRefID());
		this.pickedUp = true;
	}

	/**
	 * Gets the boolean value which shows if the flipper has been picked up.
	 * 
	 * @return The state of the flipper whether it has been picked up.
	 */
	public boolean isPickedUp() {
		return this.pickedUp;
	}

}
