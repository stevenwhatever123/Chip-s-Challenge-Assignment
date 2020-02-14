import javafx.scene.image.Image;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * This class represents a door which requires a key of the same colour to open.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.4
 */

public class ColouredDoor extends Tile {

	private String colour;
	private String openDoorImageLink;
	private boolean doorOpened;
	private char refId;

	/**
	 * Creates a ColouredDoor with a set colour.
	 * 
	 * @param colour The colour of the door.
	 * @throws FileNotFoundException, IOException. If the image can not be found
	 */
	public ColouredDoor(String colour) throws FileNotFoundException, IOException {
		setColour(colour);
		setPlayerWalkable(false);
		setEnemyWalkable(false);
		setDoorType(colour);
		this.doorOpened = false;
	}

	/**
	 * Sets the properties of the door based on its colour. The properties includes
	 * its reference id, name, the key's reference id and the image link of the door
	 * of when it is closed and open.
	 * 
	 * @param colour The colour of the door.
	 * @throws FileNotFoundException If image is not found.
	 */
	private void setDoorType(String colour) throws FileNotFoundException {
		if (colour.equalsIgnoreCase("Red")) {
			setRefID('G');
			setName("Red Door");
			setImageLink("RedDoor.PNG");
			setRequiredRefId('0');
			setOpenDoorImageLink("Ground.jpg");
		} else if (colour.equalsIgnoreCase("Yellow")) {
			setRefID('H');
			setName("Yellow Door");
			setImageLink("YellowDoor.PNG");
			setRequiredRefId('1');
			setOpenDoorImageLink("Ground.jpg");
		} else {
			setRefID('I');
			setName("Green Door");
			setImageLink("GreenDoor.PNG");
			setRequiredRefId('2');
			setOpenDoorImageLink("Ground.jpg");
		}
	}

	/**
	 * Changes the image link of the door when it is open.
	 * 
	 * @param openDoorImageLink The image file name of the open door.
	 */
	private void setOpenDoorImageLink(String openDoorImageLink) {
		this.openDoorImageLink = openDoorImageLink;
	}

	/**
	 * Gets the image link of the open door.
	 * 
	 * @return The image file name of the open door.
	 */
	public String getOpenDoorImageLink() {
		return this.openDoorImageLink;
	}

	/**
	 * Changes the colour of the door.
	 * 
	 * @param colour The colour of the door.
	 */
	private void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * Gets the colour of the door.
	 * 
	 * @return The colour of the door.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Modifies the properties and image of the door when it becomes open. Both
	 * player and enemy can walk on it.
	 * 
	 * @throws FileNotFoundException If image is not found
	 */
	public void openDoor() throws FileNotFoundException {
		setPlayerWalkable(true);
		setEnemyWalkable(true);
		setImageLink(openDoorImageLink);
		this.doorOpened = true;
	}

	/**
	 * Gets the state of door, checking whether it is open or not.
	 * 
	 * @return The state of the door of whether it is open.
	 */
	public boolean isOpened() {
		return this.doorOpened;
	}

	/**
	 * This method sets the reference id of the required key that can open the door.
	 * 
	 * @param refId The key's reference id.
	 */
	public void setRequiredRefId(char refId) {
		this.refId = refId;
	}

	/**
	 * Gets the reference id of the required key that can open the door.
	 * 
	 * @return The key's reference id.
	 */
	public char getRequiredRefId() {
		return this.refId;
	}

}
