import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This RubberBoots class creates a rubber boots object with Tile as it's
 * superclass.
 * 
 * @author Steven Ho
 * @version 1.3
 */

public class RubberBoot extends Tile {

	private boolean pickenUp;

	/**
	 * Creates a rubber boot object.
	 * 
	 * @throws IOException if image is not found.
	 */
	public RubberBoot() throws IOException {
		setRefID('4');
		setName("Rubber Boot");
		setImageLink("RubberBoots.png");
		setPlayerWalkable(true);
		setEnemyWalkable(false);
	}

	/**
	 * This methods changes the RubberBoots tile to a ground tile when it is picked
	 * up and add rubber boot in the player's inventory.
	 * 
	 * @param the player
	 * @throws FileNotFoundException if image is not found.
	 */
	public void pickUpBoot(Player p) throws FileNotFoundException {
		setImageLink("Ground.jpg");
		setEnemyWalkable(true);
		p.addItem(getRefID());
		this.pickenUp = true;
	}

	/**
	 * Gets the boolean value which shows if the rubber boot has been picked up.
	 * 
	 * @return pickenUp.
	 */
	public boolean isPickedUp() {
		return this.pickenUp;
	}

}