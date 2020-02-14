import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class represents a Smart Enemy on the map that will find the quickest
 * path to the player.
 * 
 * @author Hugo Green
 * @version 1.2
 *
 */
public class SmartEnemy extends Tile {

	private int mapheight;
	private int mapwidth;
	private int smartEnemyX;
	private int smartEnemyY;
	private boolean visitedThisTurn;
	private Tile[][] map;

	/**
	 * Create a new Smart Enemy with given x and y location.
	 * 
	 * @param x The x location of the enemy on a map.
	 * @param y The y location of the enemy on a map.
	 * @throws FileNotFoundException Thrown if the image representing the Enemy is
	 *                               not found.
	 */
	SmartEnemy(int x, int y) throws FileNotFoundException {
		smartEnemyX = x;
		smartEnemyY = y;

		setPlayerWalkable(false);
		setEnemyWalkable(true);
		setRefID('K');
		setName("SmartEnemy");
		setImageLink("EnemyFollowSmart.PNG");
	}

	/**
	 * Sets the map.
	 * 
	 * @param map The current mapstate of the game.
	 */
	public void setMap(Tile[][] map) {
		this.map = map;
		this.mapheight = map.length;
		this.mapwidth = map[0].length;

	}

	/**
	 * Get visitedThisTurn.
	 * 
	 * @return visitedThisTurn Value that says whether the player has moved this
	 *         turn.
	 */
	public boolean isVisitedThisTurn() {
		return visitedThisTurn;
	}

	/**
	 * Set visitedThisTurn.
	 * 
	 * @param visitedThisTurn New value for visitedThisTurn.
	 */
	public void setVisitedThisTurn(boolean visitedThisTurn) {
		this.visitedThisTurn = visitedThisTurn;
	}

	/**
	 * Get the enemy's X coordinate.
	 * 
	 * @return smartEnemyX The enemy's X coordinate.
	 */
	public int getXcoord() {
		return smartEnemyX;
	}

	/**
	 * Set the enemy's X coordinate.
	 * 
	 * @param smartEnemyX The new X coordinate.
	 */
	public void setsmartEnemyX(int smartEnemyX) {
		this.smartEnemyX = smartEnemyX;
	}

	/**
	 * Get smartEnemyY.
	 * 
	 * @return smartEnemyY The enemy's Y coordinate.
	 */
	public int getYcoord() {
		return smartEnemyY;
	}

	/**
	 * Set smartEnemyY.
	 * 
	 * @param smartEnemyY The new enemy Y coordinate.
	 */
	public void setsmartEnemyY(int smartEnemyY) {
		this.smartEnemyY = smartEnemyY;
	}

	/**
	 * Return the enemy's next position and move it for this turn.
	 * 
	 * @return nextPos The next position the enemy will move to on the map.
	 */
	public Coordinate moveEnemy() {
		visitedThisTurn = true;
		Coordinate nextPos = pathFindToPlayer();
		return nextPos;
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
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * Check if the players location equals the given coordinates.
	 * 
	 * @param coords The coordinates being checked.
	 * @return True or False. Whether coordinate is in the player position.
	 */
	private boolean hasReachedPlayer(Coordinate coords) {
		if (map[coords.getY()][coords.getX()] instanceof Player) {
			System.out.println("Player was found");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return the Coordinate the enemy should go to next in order to reach the
	 * player. Uses a BFS to loop through all possible move paths and add them to a
	 * queue then return the first move of the path that reaches the player first.
	 * Otherwise return random coordinates.
	 * 
	 * @return Coordinate The next position the enemy will move to.
	 */
	private Coordinate pathFindToPlayer() {
		LinkedList<Coordinate> Searchqueue = new LinkedList<Coordinate>();

		Map<String, Coordinate> visitedCoordinate = new HashMap<String, Coordinate>();

		Coordinate enemyCoordinate = new Coordinate(smartEnemyX, smartEnemyY);
		Coordinate currentNode = enemyCoordinate;
		Searchqueue.push(currentNode);
		visitedCoordinate.put(currentNode.toString(), enemyCoordinate);
		String listOfQueue = " ";

		while (Searchqueue.isEmpty() == false && hasReachedPlayer(currentNode) == false) {

			currentNode = Searchqueue.getFirst();
			Searchqueue.poll();
			listOfQueue = listOfQueue + "," + currentNode;

			Coordinate moveUp = new Coordinate(currentNode.getX(), currentNode.getY() - 1);
			Coordinate moveDown = new Coordinate(currentNode.getX(), currentNode.getY() + 1);
			Coordinate moveLeft = new Coordinate(currentNode.getX() - 1, currentNode.getY());
			Coordinate moveRight = new Coordinate(currentNode.getX() + 1, currentNode.getY());

			Coordinate[] moves = { moveRight, moveLeft, moveDown, moveUp };

			for (Coordinate move : moves) {

				if (isMoveValid(move) && !visitedCoordinate.containsKey(move.toString())) {

					if (currentNode.equals(enemyCoordinate)) {

						visitedCoordinate.put(move.toString(), move);
					} else {

						visitedCoordinate.put(move.toString(), visitedCoordinate.get(currentNode.toString()));
					}

					Searchqueue.offer(move);
				}

			}

		}

		if (hasReachedPlayer(currentNode) == true) {
			return visitedCoordinate.get(currentNode.toString());
		} else {
			return generateRandomMove();

		}

	}

	/**
	 * Return a random coordinates for the next move on the map that is valid.
	 * 
	 * @return nextMove Random coordinates up,left,right or down of the enemy.
	 */
	private Coordinate generateRandomMove() {

		Coordinate nextMove;
		Coordinate moveUp = new Coordinate(smartEnemyX, smartEnemyY - 1);
		Coordinate moveDown = new Coordinate(smartEnemyX, smartEnemyY + 1);
		Coordinate moveLeft = new Coordinate(smartEnemyX - 1, smartEnemyY);
		Coordinate moveRight = new Coordinate(smartEnemyX + 1, smartEnemyY);

		Coordinate[] moves = { moveUp, moveDown, moveLeft, moveRight };
		List<Coordinate> possibleMoves = new ArrayList<>();

		for (Coordinate move : moves) {
			if (isMoveValid(move) == true) {
				possibleMoves.add(move);
			}
		}

		if (possibleMoves.size() == 0) {

			nextMove = new Coordinate(smartEnemyX, smartEnemyY);
		} else {
			Random r = new Random();
			nextMove = possibleMoves.get(r.nextInt(possibleMoves.size()));
		}

		return nextMove;
	}
}