import java.io.FileNotFoundException;

/**
 * This class represents an enemy tile which moves by following a wall.
 * 
 * @author Nikko Pang
 * @version 1.6
 */
public class WallFollowingEnemy extends Tile {

	Tile[][] map;
	private int posX;
	private int posY;
	private int[] coordinates = new int[2];
	private String orientation;
	private boolean visited;

	/**
	 * Creates an enemy tile and sets its properties.
	 * 
	 * @param x The x component of the coordinate.
	 * @param y The y component of the coordinate.
	 * @throws FileNotFoundException when the file path for the specified filename
	 *                               does not exist.
	 */
	public WallFollowingEnemy(int x, int y, String orientation) throws FileNotFoundException {
		setCoordinates(x, y);
		setOrientation(orientation);
		setPlayerWalkable(true);
		setEnemyWalkable(false);
		setRefID('M');
		setName("WallFollowingEnemy");
		setImageLink("EnemyWallSmart.PNG");
	}

	/**
	 * Creates an enemy tile and sets its properties.
	 * 
	 * @param x The x component of the coordinate.
	 * @param y The y component of the coordinate.
	 */
	private boolean isHorizontalMovValid(int x, int y) {

		Tile tUp = map[y - 1][x];
		Tile tDown = map[y + 1][x];

		if (isWallOrCollectable(tUp) || isWallOrCollectable(tDown)) {
			return true;
		}
		return false;
	}

	/**
	 * Creates an enemy can move vertically.
	 * 
	 * @param x The x component of the coordinate.
	 * @param y The y component of the coordinate.
	 */
	private boolean isVerticalMovValid(int x, int y) {
		Tile tLeft = map[y][x - 1];
		Tile tRight = map[y][x + 1];

		if (isWallOrCollectable(tLeft) || isWallOrCollectable(tRight)) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the enemy is next to the wall.
	 * 
	 * @param x The x component of the coordinate.
	 * @param y The y component of the coordinate.
	 */
	private boolean isNextToWall(int x, int y) {
		Tile tLeft = map[y][x - 1];
		Tile tRight = map[y][x + 1];
		Tile tUp = map[y - 1][x];
		Tile tDown = map[y + 1][x];

		if (isWallOrCollectable(tLeft) || isWallOrCollectable(tRight) || isWallOrCollectable(tUp)
				|| isWallOrCollectable(tDown)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates an whether the tile is a collectable or a wall.
	 * 
	 * @param t The tile.
	 */
	private boolean isWallOrCollectable(Tile t) {
		char temp = t.getRefID();
		char[] listOfCollectables = { '0', '1', '2', '3', '4', '5' };

		if (t.getRefID() == 'B') {
			return true;
		}

		for (int i = 0; i < listOfCollectables.length; i++) {
			if (listOfCollectables[i] == temp) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if the tile is walkable.
	 * 
	 * @param x           The x component of the coordinate.
	 * @param y           The y component of the coordinate.
	 * @param orientation The orientation of the tile.
	 */
	private void checkNextTileWalkable(String orientation, int x, int y) {
		String newOrientation = orientation;

		if (orientation.equalsIgnoreCase("Upwards")) {
			if (isHorizontalMovValid(x, y)) {
				newOrientation = "Rightwards";
			}
		} else if (orientation.equalsIgnoreCase("Downwards")) {
			if (isHorizontalMovValid(x, y)) {
				newOrientation = "Leftwards";
			}
		} else if (orientation.equalsIgnoreCase("Rightwards")) {
			if (isVerticalMovValid(x, y)) {
				newOrientation = "Downwards";
			}
		} else if (orientation.equalsIgnoreCase("Leftwards")) {
			if (isVerticalMovValid(x, y)) {
				newOrientation = "Upwards";
			}
		}

		setOrientation(newOrientation);
		moveValid(newOrientation);
	}

	/**
	 * Checks if the move is valid.
	 * 
	 * @param orientation The orientation of the tile.
	 * 
	 */
	private void moveValid(String orientation) {
		Tile tLeft = map[getPosY()][getPosX() - 1];
		Tile tRight = map[getPosY()][getPosX() + 1];
		Tile tUp = map[getPosY() - 1][getPosX()];
		Tile tDown = map[getPosY() + 1][getPosX()];
		String tempOrientation = orientation;

		if (!isNextToWall(getPosX(), getPosY())) {
			if (!isWallOrCollectable(tLeft) && isNextToWall(getPosX() - 1, getPosY())) {
				tempOrientation = "Leftwards";
				moveEnemy("Left");
			} else if (!isWallOrCollectable(tUp) && isNextToWall(getPosX() + 1, getPosY())) {
				tempOrientation = "Rightwards";
				moveEnemy("Right");
			} else if (!isWallOrCollectable(tUp) && isNextToWall(getPosX(), getPosY() - 1)) {
				tempOrientation = "Upwards";
				moveEnemy("Up");
			} else if (!isWallOrCollectable(tDown) && isNextToWall(getPosX(), getPosY() + 1)) {
				tempOrientation = "Downwards";
				moveEnemy("Down");
			}
		}

		if (tempOrientation.equalsIgnoreCase("Upwards")) {
			if (!isWallOrCollectable(tUp) && isVerticalMovValid(getPosX(), getPosY())) {
				moveEnemy("Up");
			} else {
				checkNextTileWalkable(tempOrientation, getPosX(), getPosY());
			}
		} else if (tempOrientation.equalsIgnoreCase("Downwards")) {
			if (!isWallOrCollectable(tDown) && isVerticalMovValid(getPosX(), getPosY())) {
				moveEnemy("Down");
			} else {
				checkNextTileWalkable(tempOrientation, getPosX(), getPosY());
			}
		} else if (tempOrientation.equalsIgnoreCase("Leftwards")) {
			if (!isWallOrCollectable(tLeft) && isHorizontalMovValid(getPosX(), getPosY())) {
				moveEnemy("Left");
			} else {
				checkNextTileWalkable(tempOrientation, getPosX(), getPosY());
			}
		} else if (tempOrientation.equalsIgnoreCase("Rightwards")) {
			if (!isWallOrCollectable(tRight) && isHorizontalMovValid(getPosX(), getPosY())) {
				moveEnemy("Right");
			} else {
				checkNextTileWalkable(tempOrientation, getPosX(), getPosY());
			}
		}

	}

	/**
	 * Updates the enemies new coordinates.
	 * 
	 * @param direction The direction which the enemy wants to move.
	 */
	public void moveEnemy(String direction) {
		int newX = getPosX();
		int newY = getPosY();

		switch (direction) {
		case "Up":
			newY--;
			break;
		case "Down":
			newY++;
			break;
		case "Right":
			newX++;
			break;
		case "Left":
			newX--;
			break;
		}

		setCoordinates(newX, newY);
	}

	/**
	 * Calls the enemy to make the next move.
	 */
	public void nextMove() {
		String orientation = getOrientation();
		moveValid(orientation);
		System.out.println("Orientation is " + orientation + ". Coordinates x y is " + getPosX() + " " + getPosY());
	}

	/**
	 * Changes the orientation of the enemy.
	 * 
	 * @param orientation The direction that the enemy is facing.
	 */
	private void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	/**
	 * Gets the orientation of the enemy.
	 * 
	 * @return The direction that the enemy is facing.
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Sets the coordinates of the player.
	 * 
	 * @param posX The x-axis coordinate of the player on the map.
	 * @param posY The y-axis coordinate of the player on the map.
	 */
	private void setCoordinates(int posX, int posY) {
		setPosX(posX);
		setPosY(posY);
		coordinates[0] = posX;
		coordinates[1] = posY;
	}

	/**
	 * Gets the int coordinate pair (x,y) of the enemy.
	 * 
	 * @return The coordinates of the enemy on the map.
	 */
	public int[] getCoordinates() {
		String s = "Coordinates are x = " + getPosX() + ", y = " + getPosY();
		System.out.println(s);
		return coordinates;
	}

	/**
	 * Set the x-axis coordinate of the player.
	 * 
	 * @param posX The x-axis coordinate
	 */
	private void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Returns the x-axis coordinate of the player
	 * 
	 * @return posX The x-axis of the player
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Sets the y-axis coordinate of the player.
	 * 
	 * @param posY The y-axis coordinate of the player.
	 */
	private void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Returns the y-axis coordinate of the player.
	 * 
	 * @return posY The y-axis coordinate of the player.
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets the map that the enemy is on.
	 * 
	 * @param map The map that enemy is on.
	 */
	public void setMap(Tile[][] map) {
		this.map = map;
	}

	/**
	 * Gets the current map which the enemy is on.
	 * 
	 * @return The current map which the enemy is on.
	 */
	private Tile[][] getMap() {
		return map;
	}

	/**
	 * Sets the tile to visited.
	 * 
	 * @param visited sets the tile to visited.
	 */
	public void setVisitedThisTurn(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Checks whether the tile has been visited.
	 * 
	 * @param visited checks whether the tile is visited.
	 */
	public boolean isVisitedThisTurn() {
		return visited;
	}

}