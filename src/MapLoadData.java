/**
 * This class act as a receiver for reading profile from NewGame and LoadGame
 * which later pass all data to OutputController.
 * 
 * @author Steven Ho, Ethan Young
 * @version 1.0
 */
public class MapLoadData {

	private static Profile p;

	private static int levelNum;

	private static int time; // Will be represented in seconds.

	/**
	 * Sets the current profile.
	 * 
	 * @param p The player's profile.
	 */
	public static void setProfile(Profile p) {
		MapLoadData.p = p;
	}

	/**
	 * Sets the current level number.
	 * 
	 * @param i The level number.
	 */
	public static void setLevelNum(int i) {
		MapLoadData.levelNum = i;
	}

	/**
	 * Gets the current profile.
	 * 
	 * @return The player's profile.
	 */
	public static Profile getProfile() {
		return MapLoadData.p;
	}

	/**
	 * Gets the current level number.
	 * 
	 * @return The level number.
	 */
	public static int getLevelNum() {
		return MapLoadData.levelNum;
	}

	/**
	 * Sets the game time.
	 * 
	 * @param t The starting time of the game.
	 */
	public static void setTime(int t) {
		MapLoadData.time = t;
	}

	/**
	 * Gets the game time.
	 * 
	 * @return The time of the game.
	 */
	public static int getTime() {
		return MapLoadData.time;
	}

}
