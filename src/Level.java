/**
 * This class stores the necessary data of each level for a player. This
 * includes the time, map and whether they've completed it in the past or not.
 * 
 * @author Nikko Pang
 * @version 1.0
 */
public class Level {

	private int levelNum;

	private boolean completed;

	private int savedTime;

	private String savedMap;

	private Inventory savedInventory;

	/**
	 * Creates an new level object which stores the date for that particular level.
	 * 
	 * @param savedTime The initial time of the game.
	 * @param savedMap  The default map of that level.
	 */
	public Level(int levelNum, boolean isComplete, int savedTime, String savedMap, Inventory inv) {
		this.levelNum = levelNum;
		setCompleted(isComplete);
		setSavedTime(savedTime);
		setSavedMap(savedMap);
		setSavedInventory(inv);
	}

	/**
	 * Shows the level number of the data which is being looked at.
	 * 
	 * @return levelNum The level of difficulty of the game.
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * Shows whether the player has completed level 1.
	 * 
	 * @return completed The state of completion for player in Level 1.
	 */
	public boolean completed() {
		return completed;
	}

	/**
	 * Sets the state of whether the player has completed the level.
	 * 
	 * @param completed The state of completion for the level.
	 */
	private void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * Saves the player's inventory with all the items they possess.
	 * 
	 * @param savedInventory The player's list of items
	 */
	public void setSavedInventory(Inventory savedInventory) {
		this.savedInventory = savedInventory;
	}

	/**
	 * Shows the player's inventory from the last saved point.
	 * 
	 * @return savedInventory The player's inventory.
	 */
	public Inventory getSavedInventory() {
		return savedInventory;
	}

	/**
	 * Shows the last saved time for the player.
	 * 
	 * @return The time taken by the player while they were the game.
	 */
	public int getSavedTime() {
		return savedTime;
	}

	/**
	 * Updates the saved time of the player of that level.
	 * 
	 * @param savedTime The time taken by the player while they were the game.
	 */
	public void setSavedTime(int savedTime) {
		this.savedTime = savedTime;
	}

	/**
	 * Returns the last saved map of that level with all the changes made by the
	 * player.
	 * 
	 * @return The condition of the last saved map.
	 */
	public String getSavedMap() {
		return savedMap;
	}

	/**
	 * Updates the map condition of when the player saves the game.
	 * 
	 * @param savedMap The map of the player which they are currently playing during
	 *                 the save time.
	 */
	public void setSavedMap(String savedMap) {
		this.savedMap = savedMap;
	}

	@Override
	public String toString() {
		return "Level: " + this.levelNum;
	}

}
