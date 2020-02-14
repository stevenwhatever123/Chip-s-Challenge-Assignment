import java.io.IOException;

/**
 * This class represents a ground tile.
 * 
 * @author Steven Ho
 * @version 1.4
 */

public class Ground extends Tile {

	/**
	 * Creates a ground tile.
	 * 
	 * @throws IOException if image is not found
	 */
	public Ground() throws IOException {
		setRefID('A');
		setImageLink("Ground.jpg");
		setPlayerWalkable(true);
		setEnemyWalkable(true);
		setName("Ground");
	}

}
