/**
 * The profile which stores the data for each player. If the player reenters the
 * game and wants to continue where they left off, they will reference back to
 * this profile. Each profile has a unique username.
 * 
 * @author Nikko Pang
 * @version 1.2
 */
public class Profile {

	private String userName;
	private int highestLevel;
	private Level[] levelData = new Level[3];
	Level lvl1;
	Level lvl2;
	Level lvl3;

	/**
	 * Creates a profile which stores all the data for the player.
	 * 
	 * @param userName
	 */
	public Profile(String userName, int highestLevel) {
		lvl1 = new Level(1, false, 0, "map1.txt", null);
		lvl2 = new Level(2, false, 0, "map2.txt", null);
		lvl3 = new Level(3, false, 0, "map3.txt", null);

		this.userName = userName;
		this.highestLevel = highestLevel;
		levelData[0] = lvl1;
		levelData[1] = lvl2;
		levelData[2] = lvl3;
	}

	/**
	 * Saves current time, map and inventory to the relevant level data.
	 * 
	 * @param levelNum       the number of the level being saved
	 * @param time           the value on the timer
	 * @param savedMap       the current state of the map
	 * @param savedInventory the current state of the inventory
	 */
	public void saveLevel(int levelNum, int time, String savedMap, Inventory savedInventory) {
		if (levelNum == 1) {
			lvl1.setSavedTime(time);
			lvl1.setSavedMap(savedMap);
			lvl1.setSavedInventory(savedInventory);
		} else if (levelNum == 2) {
			lvl2.setSavedTime(time);
			lvl2.setSavedMap(savedMap);
			lvl2.setSavedInventory(savedInventory);
		} else {
			lvl3.setSavedTime(time);
			lvl3.setSavedMap(savedMap);
			lvl3.setSavedInventory(savedInventory);
		}
	}

	/**
	 * Shows the name of the player.
	 * 
	 * @return userName the player's name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the player's name and makes sure that there is no existing profile with
	 * the same name.
	 * 
	 * @param userName The name of the player.
	 */
	private void setUserName(String userName) throws IllegalArgumentException {
		Profile obj = (Profile) new Object();

		if (obj.getUserName() == this.userName) {
			throw new IllegalArgumentException("This username is taken.");
		}
		this.userName = userName;
	}

	/**
	 * The highest level which the player has completed. It will be saved
	 * automatically when the player completes that level.
	 * 
	 * @return highestLevel The highest
	 */
	public int getHighestLevel() {
		return highestLevel;
	}

	/**
	 * Sets the highest level which has been completed by the player disregarding of
	 * the fact whether the player has played all the lower levels beforehand.
	 * 
	 * @param highestLevel The highest level which the player has completed.
	 */
	private void setHighestLevel(int highestLevel) {
		this.highestLevel = highestLevel;
	}

	@Override
	public String toString() {
		return this.userName;
	}

}
