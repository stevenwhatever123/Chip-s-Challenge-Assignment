import java.io.IOException;

/**
 * This class creates a electric tile.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.3
 */

public class Electricity extends Tile {

	private boolean killsPlayer;
	private char refId;

	/**
	 * Creates a electricity tile and sets its default properties.
	 * 
	 * @throws IOException if image is not found.
	 */
	public Electricity() throws IOException {
		setRefID('E');
		setImageLink("Electricity.jpg");
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setName("Electricity");
		setRequiredRefId('4');
		this.killsPlayer = true;
	}

	/**
	 * This methods checks if the player has rubber boots or not, if the player has
	 * it, it changes killsPlayer to false.
	 */
	public void hasRubberBoots() {
		setKillsPlayer(false);
	}

	/**
	 * Sets whether the player will die they walk on the tile. True indicating that
	 * the game will end if it steps on it. Otherwise, false.
	 * 
	 * @param killsPlayer The state of the tile whether it would kill the player if
	 *                    collided.
	 */
	private void setKillsPlayer(boolean killsPlayer) {
		this.killsPlayer = killsPlayer;
	}

	/**
	 * Returns whether the player die from the tile.
	 * 
	 * @return The state of the tile whether it would kill the player if collided.
	 */
	public boolean getKillsPlayer() {
		return this.killsPlayer;
	}

	/**
	 * This method sets the required reference id for electricity if the player want
	 * to walk through.
	 * 
	 * @param refId The reference id of the token
	 */
	public void setRequiredRefId(char refId) {
		this.refId = refId;
	}

	/**
	 * This method gets the required reference id for electricity if the player want
	 * to walk through
	 * 
	 * @return the reference id
	 */
	public char getRequiredRefId() {
		return this.refId;
	}
}
