import java.io.IOException;

/**
 * This class represents a wall tile.
 * 
 * @author Steven Ho, Nikko Pang
 * @version 1.3
 */

public class Wall extends Tile {

	/**
	 * Creates a wall tile along with its properties.
	 * 
	 * @throws IOException if image is not found
	 */
	public Wall() throws IOException {
		setRefID('B');
		setImageLink("Wall1.PNG");
		setPlayerWalkable(false);
		setEnemyWalkable(false);
		setName("Wall");
	}

}