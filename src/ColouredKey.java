import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * This class represents a coloured key which opens a door of the same colour.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.4
 */

public class ColouredKey extends Tile {

	private String colour;
	private boolean pickedUp;

	/**
	 * Creates a coloured key object which can be used to open the door of the same
	 * colour.
	 * 
	 * @param colour The colour of the key.
	 * @throws FileNotFoundException , IOException If image is not found
	 */
	public ColouredKey(String colour) throws FileNotFoundException, IOException {
		setColour(colour);
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setKeyType(colour);
		this.pickedUp = false;
	}

	/**
	 * Sets the index number, name and image link of the key based on its colour.
	 * 
	 * @param The colour of the key
	 * @throws FileNotFoundException if image is not found
	 */
	private void setKeyType(String colour) throws FileNotFoundException {
		if (colour.equalsIgnoreCase("Red")) {
			setRefID('0');
			setName("Red Key");
			setImageLink("RedKey.PNG");
		} else if (colour.equalsIgnoreCase("Yellow")) {
			setRefID('1');
			setName("Yellow Key");
			setImageLink("YellowKey.PNG");
		} else {
			setRefID('2');
			setName("Green Key");
			setImageLink("GreenKey.PNG");
		}
	}

	/**
	 * Sets the colour of the key.
	 * 
	 * @param The colour of the key.
	 */
	private void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Gets the colour of the key.
	 * 
	 * @return The colour of the key.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * This methods changes the key tile to a ground tile when it is picked up.
	 * 
	 * @throws FileNotFoundException If image is not found.
	 */
	public void pickUpKey(Player p) throws FileNotFoundException {
		setImageLink("Ground.jpg");
		setEnemyWalkable(true);
		this.pickedUp = true;
		p.addItem(getRefID());
	}

	/**
	 * Gets the boolean value which shows if the key has been picked up.
	 * 
	 * @return The state of whether the key has been picked up.
	 */
	public boolean isPickedUp() {
		return this.pickedUp;
	}

}