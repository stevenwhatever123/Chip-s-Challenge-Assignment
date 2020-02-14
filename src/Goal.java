import java.io.IOException;

/**
 * Represents as the goal tile of the game. Player wins if arrive on tile.
 * 
 * @author Nikko Pang
 * @version 1.1
 */
public class Goal extends Tile {

	/**
	 * Creates a goal object.
	 * 
	 * @throws IOException
	 */
	public Goal() throws IOException {
		setRefID('D');
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setImageLink("Goal.PNG");
		setName("Goal");
	}

}