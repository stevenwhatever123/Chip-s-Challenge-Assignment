import java.io.IOException;

/**
 * This class represents a teleporter tile.
 * 
 * @author Nikko Pang,Steven Ho
 * @version 1.4
 */

public class Teleporter extends Tile {

	private Teleporter pair;
	private int posX;
	private int posY;
	private int[] coordinates = new int[2];

	/**
	 * Creates a teleporter tile and sets its properties with no linked teleporter.
	 * 
	 * @param posX The x-axis coordinate of the teleporter.
	 * @param posY The y-axis coordinate of the teleporter.
	 * @throws IOException if image is not found.
	 */
	public Teleporter(int posX, int posY) throws IOException {
		setCoordinates(posX, posY);
		setImageLink("Teleporter.PNG");
		setRefID('C');
		setName("Teleporter");
		this.pair = null;
	}

	/**
	 * Creates a teleporter tile and sets its properties with a linked teleporter.
	 * 
	 * @param posX The x-axis coordinate of the teleporter.
	 * @param posY The y-axis coordinate of the teleporter.
	 * @throws IOException if image is not found.
	 */
	public Teleporter(int posX, int posY, Teleporter pair) throws IOException {
		setCoordinates(posX, posY);
		setImageLink("Teleporter.png");
		setRefID('C');
		setName("Teleporter");
		setPair(pair);
	}

	/**
	 * Set the x component.
	 * 
	 * @param posX The new x coordinate.
	 */
	private void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Set the y component.
	 * 
	 * @param posY The new y coordinate.
	 */
	private void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Gets the x component.
	 * 
	 * @return The x coordinate.
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Gets the y component.
	 * 
	 * @return The y coordinate.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets the coordinate with a specified x-coordinate and y-coordinate.
	 * 
	 * @param posX The x coordinate.
	 * @param posY The y coordinate.
	 */
	private void setCoordinates(int posX, int posY) {
		setPosX(posX);
		setPosY(posY);
		coordinates[0] = posX;
		coordinates[1] = posY;
	}

	/**
	 * Gets the coordinates of the teleporter.
	 * 
	 * @return The coordinates (x,y) of the teleporter.
	 */
	public int[] getCoordinates() {
		return coordinates;
	}

	/**
	 * Gets the linked teleporter.
	 * 
	 * @return pair The linked teleporter.
	 */
	public Teleporter getPair() {
		return pair;
	}

	/**
	 * Sets a link between another teleporter and this one.
	 * 
	 * @param t The to-be-linked teleporter.
	 */
	public void setPair(Teleporter t) {
		this.pair = t;
	}

	/**
	 * Teleports the player to the right of the linked teleporter.
	 * 
	 * @param p    The player.
	 * @param pair The linked teleporter.
	 */
	public void teleport(Player p, Teleporter pair) {
		p.teleportPlayer(pair);
	}
}