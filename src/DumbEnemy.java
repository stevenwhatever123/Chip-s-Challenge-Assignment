import java.io.FileNotFoundException;

/**
 * This class represents the DumbEnemy that will move around the map following
 * the player doing the move that will make it closer.
 * 
 * @author Hugo Green
 * @version 1.2
 */
public class DumbEnemy extends Tile {

	private int Xposition;
	private int Yposition;
	private int mapheight;
	private int mapwidth;
	private boolean visitedThisTurn = false; // Has it moved this turn
	private Coordinate playerLocation;
	private Tile[][] map;

	/**
	 * @param x The enemy's X position.
	 * @param y The enemy's Y position.
	 * @throws FileNotFoundException If the enemy ImageLink cannot be found.
	 */
	DumbEnemy(int x, int y) throws FileNotFoundException {
		this.Xposition = x;
		this.Yposition = y;

		setPlayerWalkable(false);
		setEnemyWalkable(false);
		setRefID('L');
		setName("DumbEnemy");
		setImageLink("EnemyFollowDumb.PNG");

	}

	/**
	 * Set the current game map and the height and width of the current game map.
	 * 
	 * @param map The current game map.
	 */
	public void setMap(Tile[][] map) {
		this.map = map;
		this.mapheight = map.length;
		this.mapwidth = map[0].length;

	}

	/**
	 * Set the Players current location.
	 * 
	 * @param coordinates The new players current location.
	 */
	public void setPlayerLocation(Coordinate coordinates) {
		this.playerLocation = coordinates;
	}

	/**
	 * Return the enemy's current X position.
	 * 
	 * @return Xposition The enemy;s X position on the map.
	 */
	public int getXcoord() {
		return Xposition;
	}

	/**
	 * Set the new x position of the enemy.
	 * 
	 * @param xposition The new x position of the enemy.
	 */
	public void setXposition(int xposition) {
		Xposition = xposition;
	}

	/**
	 * Get the enemy's Y position.
	 * 
	 * @return Yposition The enemy's Y position on the map.
	 */
	public int getYcoord() {
		return Yposition;
	}

	/**
	 * Get whether the enemy has moves this turn.
	 * 
	 * @return visitedThisTurn True or false whether it has moved this game turn.
	 */
	public boolean isVisitedThisTurn() {
		return visitedThisTurn;
	}

	/**
	 * Set true or false whether this Enemy moved this turn.
	 * 
	 * @param visitedThisTurn New value for whether the enemy moved this turn.
	 */
	public void setVisitedThisTurn(boolean visitedThisTurn) {
		this.visitedThisTurn = visitedThisTurn;
	}

	public void setYposition(int yposition) {
		Yposition = yposition;
	}

	/**
	 * Return the enemy's next position and move it for this turn.
	 * 
	 * @return nextPos The next position the enemy will move to on the map.
	 */
	public Coordinate moveEnemy() {
		visitedThisTurn = true;
		Coordinate nextPos = getNextPos();
		// System.out.println("DumbEnemy is at " + nextPos);
		return nextPos;
	}

	/**
	 * Returns the map coordinates for the enemy's next location by moving it closer
	 * to the player if possible in terms of its x and y position in comparison with
	 * the players.
	 * 
	 * @return nextMove The enemy's next position on the game Map.
	 */
	private Coordinate getNextPos() {
		Coordinate currentPos = new Coordinate(Xposition, Yposition);
		int xDifference = playerLocation.getX() - Xposition;
		int yDifference = playerLocation.getY() - Yposition;
		Coordinate nextMove; // next coordinate on the map the enemy should move to

		boolean xIsLarger = (Math.abs(xDifference) >= Math.abs(yDifference));

		if (xIsLarger) {

			if (moveX(xDifference, currentPos) == null) { // Null if enemy shouldn't move.
				nextMove = moveY(yDifference, currentPos);
			} else {
				nextMove = moveX(xDifference, currentPos);
			}
		} else {// y is Larger
			if (moveY(yDifference, currentPos) == null) { // Null if enemy shouldn't move.

				nextMove = moveX(xDifference, currentPos);
			} else {
				nextMove = moveY(yDifference, currentPos);
			}
		}

		if (nextMove == null) {
			return currentPos;
		} else {
			return nextMove;
		}

	}

	/**
	 * @param xDifference The difference between the enemy's X position and the
	 *                    players.
	 * @param currentPos  The enemy's current x and y coordinates.
	 * @return null If the enemy can't move and the coordinates of the new position
	 *         if it can.
	 */
	private Coordinate moveX(int xDifference, Coordinate currentPos) {
		Coordinate moveLeft = new Coordinate(Xposition - 1, Yposition);
		Coordinate moveRight = new Coordinate(Xposition + 1, Yposition);

		if (xDifference >= 1) { // x difference is positive
			if (isMoveValid(moveRight)) {
				return moveRight;
			} else {
				return null;
			}
		} else if (xDifference <= -1) { // x difference is negative
			if (isMoveValid(moveLeft)) {
				return moveLeft;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	/**
	 * @param yDifference The difference between the enemy's Y position and the
	 *                    players.
	 * @param currentPos  The enemy's current x and y coordinates.
	 * @return null If the enemy can't move and the coordinates of the new position
	 *         if it can.
	 */
	private Coordinate moveY(int yDifference, Coordinate currentPos) {
		Coordinate moveUp = new Coordinate(Xposition, Yposition - 1);
		Coordinate moveDown = new Coordinate(Xposition, Yposition + 1);

		if (yDifference >= 1) {
			if (isMoveValid(moveDown)) {
				return moveDown;
			} else {
				return null;
			}

		} else if (yDifference <= -1) {
			if (isMoveValid(moveUp)) {
				return moveUp;

			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	/**
	 * Check the given coordinates to see if they are inside the map and walkable.
	 * 
	 * @param coords The coordinates being checked.
	 * @return True or False Whether the move is valid or not.
	 */
	private boolean isMoveValid(Coordinate coords) {
		boolean xNotOverBounds = (coords.getX() >= 0 && coords.getX() <= mapwidth);
		boolean yNotOverBounds = (coords.getY() >= 0 && coords.getY() <= mapheight);

		if (xNotOverBounds && yNotOverBounds) {
			if ((map[coords.getY()][coords.getX()].enemyWalkable) == true) {
				return true;
			} else {
				// System.out.println("the enemy cannot walk on " + coords + "
				// it is of type " +
				// map[coords.getY()][coords.getX()].getName());

				return false;
			}

		} else {
			return false;
		}

	}
}
