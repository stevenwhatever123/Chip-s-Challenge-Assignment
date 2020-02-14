/**
 * This class handles the data within each record of the leaderboard.
 * 
 * @author Ethan Young
 * @version 1.0
 */
public class LeaderboardRecord {

	private String userName = null;

	private int time;

	/**
	 * Creates a Leaderboard record.
	 * 
	 * @param userName The name of the player.
	 * @param time     The time taken to complete the level.
	 */
	public LeaderboardRecord(String userName, int time) {
		this.userName = userName;
		this.time = time;
	}

	/**
	 * Gets the username.
	 * 
	 * @return The username of the player.
	 */
	public String getUsername() {
		return this.userName;
	}

	/**
	 * Gets the time.
	 * 
	 * @return The time taken to complete the level.
	 */
	public int getTime() {
		return this.time;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username The username of the player.
	 */
	public void setUsername(String userName) {
		this.userName = userName;
	}

	/**
	 * Sets the time taken.
	 * 
	 * @param time The time taken to complete the level.
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * 
	 * Prints the username and time as a record.
	 * 
	 * @return The username of the player and the time taken by the player.
	 */
	@Override
	public String toString() {
		return this.userName + " " + this.time;
	}
}
