import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents a enemy tile which can only move in a straight line.
 * 
 * @author Hugo Green, Sebastian Fletcher
 * @version 1.2
 */

public class StraightLineEnemy extends Tile {

	private boolean visitedThisTurn = false;
	private int mapheight;
	private int mapwidth;
	private int stepValue = 1;
	private int Xposition;
	private int Yposition;
	private String orientation;
	private final String HORIZONTAL = "HORIZONTAL";
	private final String VERTICAL = "VERTICAL";
	private Tile[][] map;

	/**
	 * Create a straight line enemy tile and sets its Coordinate and orientation.
	 * 
	 * @param x The x-Coordinate of the straight line enemy.
	 * @param y the y=Coordinate of the straight line enemy.
	 * @throws FileNotFound exception if the image is not found.
	 */
	StraightLineEnemy(int x, int y, String orientation) throws FileNotFoundException {
		this.Xposition = x;
		this.Yposition = y;
		this.orientation = orientation;

		setPlayerWalkable(false);
		setEnemyWalkable(false);
		setRefID('N');
		setName("StraightLineEnemy");
		setImageLink("EnemyWallDumb.PNG");
	}

	/**
	 * Builds the map for this instance
	 * 
	 * @param Tile[][] map The map of the level in its current state
	 */
	public void setMap(Tile[][] map) {
		this.map = map;
		this.mapheight = map.length;
		this.mapwidth = map[0].length;
	}

	/**
	 * Gets its X coordinate
	 * 
	 * @return The X position
	 */
	public int getXcoord() {
		return Xposition;
	}

	/**
	 * Gets Y coordinate
	 * 
	 * @return The Y position
	 */
	public int getYcoord() {
		return Yposition;
	}

	/**
	 * Checks if the enemy's moved this turn.
	 * 
	 * @return If the enemy has had a turn.
	 */
	public boolean isVisitedThisTurn() {
		return visitedThisTurn;
	}

	/**
	 * Changes the state of whether the enemy has moved this turn.
	 */
	public void setVisitedThisTurn(boolean visitedThisTurn) {
		this.visitedThisTurn = visitedThisTurn;
	}

	/**
	 * Gets the direction that the enemy is walking.
	 * 
	 * @return The direction that the enemy is walking.
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * Sets the direction that the enemy is walking. Is either Horizontal or
	 * Vertical.
	 * 
	 * @param orientation The direction that the enemy is walking.
	 */
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	/**
	 * Decides the enemy's next move.
	 * 
	 * @return The coordinate position for the next move.
	 */
	public Coordinate moveEnemy() {
		System.out.println("The orientation is :" + orientation);

		visitedThisTurn = true;
		Coordinate nextPos;

		if (orientation.equals(HORIZONTAL)) {
			nextPos = moveHorizontal();

		} else if (orientation.equals(VERTICAL)) {
			nextPos = moveVertical();
		}

		else {
			nextPos = moveVertical();
		}
		this.Xposition = nextPos.getX();
		this.Yposition = nextPos.getY();
		System.out.println(" " + this.Xposition + " " + this.Yposition);
		return nextPos;
	}

	/**
	 * Move the enemy in horizontal direction.
	 * 
	 * @return The coordinate position for the next move.
	 */
	private Coordinate moveHorizontal() {

		Coordinate nextMove = new Coordinate(Xposition + stepValue, Yposition);

		boolean canMoveLeft = (map[Yposition][Xposition - 1]).enemyWalkable;
		boolean canMoveRight = (map[Yposition][Xposition + 1]).enemyWalkable;

		if ((!canMoveLeft) && (!canMoveRight)) {
			System.out.println("can't move");
			nextMove = new Coordinate(Xposition, Yposition);
			return nextMove;
		}

		if (!isMoveValid(nextMove)) {
			System.out.println("turning around");
			stepValue = stepValue * -1;
			nextMove.setX(Xposition + stepValue);
			return nextMove;
		}

		return nextMove;
	}

	/**
	 * Moves in vertical direction.
	 * 
	 * @return Coordinate position for the next move.
	 */
	private Coordinate moveVertical() {

		Coordinate nextMove = new Coordinate(Xposition, Yposition + stepValue);
		boolean canMoveUp = (map[Yposition - 1][Xposition]).enemyWalkable;
		boolean canMoveDown = (map[Yposition + 1][Xposition]).enemyWalkable;

		if ((!canMoveUp) && (!canMoveDown)) {
			System.out.println("can't move");
			nextMove = new Coordinate(Xposition, Yposition);
			return nextMove;
		}

		if (!isMoveValid(nextMove)) {
			System.out.println("turning around");
			stepValue = stepValue * -1;
			nextMove.setY(Yposition + stepValue);
			return nextMove;
		}

		return nextMove;
	}

	/**
	 * Checks to see whether the next move is valid
	 * 
	 * @param coords The coordinates of the next position.
	 * @return Whether the next move is valid
	 */
	private boolean isMoveValid(Coordinate coords) {
		boolean xNotOverBounds = (coords.getX() >= 0 && coords.getX() <= mapwidth);
		boolean yNotOverBounds = (coords.getY() >= 0 && coords.getY() <= mapheight);

		// Checks whether the enemy is within bounds.
		if (xNotOverBounds && yNotOverBounds) {
			System.out.println("Next move lands on: " + (map[coords.getY()][coords.getX()].name));

			if ((map[coords.getY()][coords.getX()].enemyWalkable) == true) {
				return true;
			}

			else {
				System.out.println("Next move is not valid, the move is: " + coords);

				return false;
			}
		} else {
			return false;
		}

	}

}