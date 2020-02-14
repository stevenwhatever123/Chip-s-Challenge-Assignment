import java.io.IOException;

/**
 * This class represents a water tile which may kill a player.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.5
 */

public class Water extends Tile {

	private boolean killsPlayer;
	private char refId;

	/**
	 * Creates a water tile and sets its default properties.
	 * 
	 * @throws IOException If image is not found.
	 */
	public Water() throws IOException {
		setRefID('F');
		setImageLink("Water.jpg");
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setName("Water");
		setRequiredRefId('3');
		this.killsPlayer = true;
	}

	/**
	 * Changes the state of whether it would kill a player once it's stepped on.
	 * 
	 * @param killsPlayer The state of danger towards the player.
	 */
	private void setKillsPlayer(boolean killsPlayer) {
		this.killsPlayer = killsPlayer;
	}

	/**
	 * Gets the state of the tile of whether it would kill a player once it's
	 * stepped on.
	 * 
	 * @return The state of danger towards the player.
	 */
	public boolean getKillsPlayer() {
		return this.killsPlayer;
	}

	/**
	 * Changes the state of the tile to safe for the player to walk on.
	 */
	public void hasFlippers() {
		setKillsPlayer(false);
	}

	/**
	 * Sets the reference id that the player need in order to walk through the water
	 * tile
	 * 
	 * @param refId The reference id of flipper.
	 */
	public void setRequiredRefId(char refId) {
		this.refId = refId;
	}

	/**
	 * This method returns the reference id that the player need in order to walk
	 * through the water tile
	 * 
	 * @return refId The reference id of flipper.
	 */
	public char getRequiredRefId() {
		return this.refId;
	}
}