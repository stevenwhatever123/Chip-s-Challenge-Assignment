import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * This class acts as a token for the token door.
 * 
 * @author Pavithraa Paramasivarasa, Luke. Nikko Pang
 * @version 1.4
 */

public class Dynamite extends Tile {

	private boolean pickedUp;

	/**
	 * Creates a dynamite object.
	 * 
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public Dynamite() throws IOException {
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setRefID('5');
		setName("Dynamite");
		setImageLink("Dynamite.PNG");
	}

	/**
	 * Set the image to the ground when player picks up the dynamite.
	 * 
	 * @param p The player object.
	 */
	public void pickUpDynamite(Player p) throws FileNotFoundException {
		setImageLink("Ground.jpg");
		setEnemyWalkable(true);
		this.pickedUp = true;
		p.addItem(getRefID());
	}

	/**
	 * Gets the state of the dynamite of whether it's been picked up.
	 * 
	 * @return The state of the dynamite of whether it's been picked up.
	 */
	public boolean isPickedUp() {
		return this.pickedUp;
	}

}