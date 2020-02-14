import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;

/**
 * This class handles all File IO for the map, profiles, levelData and
 * leaderboards.
 * 
 * @author Ethan Young
 * @version 1.2
 */
public class FileInputOutput {

	/**
	 * Reads the SmartEnemy object in the file.
	 * 
	 * @param in The scanner object.
	 * @return The object of the smartEnemy tile object.
	 * @throws FileNotFoundException If the scanner can't read any file.
	 */
	private static SmartEnemy smartEnemyTile(Scanner in) throws FileNotFoundException {
		int xCoord = in.nextInt();
		int yCoord = in.nextInt();
		SmartEnemy enemy = new SmartEnemy(xCoord, yCoord);
		return enemy;
	}

	/**
	 * Reads the dumbEnemy object in the file.
	 * 
	 * @param in The scanner object.
	 * @return The object of the dumbEnemy tile object.
	 * @throws FileNotFoundException If the scanner can't read any file.
	 */
	private static DumbEnemy dumbEnemyTile(Scanner in) throws FileNotFoundException {
		int xCoord = in.nextInt();
		int yCoord = in.nextInt();
		DumbEnemy enemy = new DumbEnemy(xCoord, yCoord);
		return enemy;
	}

	/**
	 * Reads the WallFollowEnemy object in the file.
	 * 
	 * @param in The scanner object.
	 * @return The Object of the wallFollowEnemy tile object.
	 * @throws FileNotFoundException If the scanner can't read any file.
	 */
	private static WallFollowingEnemy wallFollowEnemyTile(Scanner in) throws FileNotFoundException {
		int xCoord = in.nextInt();
		int yCoord = in.nextInt();
		String orientation = in.next();
		WallFollowingEnemy enemy = new WallFollowingEnemy(xCoord, yCoord, orientation);
		return enemy;
	}

	/**
	 * Reads the StraightLineEnemy object in the file.
	 * 
	 * @param in The scanner object.
	 * @return The object of the straightLineEnemy tile object.
	 * @throws FileNotFoundException If the scanner can't read any file.
	 */
	private static StraightLineEnemy straightLineEnemyTile(Scanner in) throws FileNotFoundException {
		int xCoord = in.nextInt();
		int yCoord = in.nextInt();
		String direction = in.next();
		StraightLineEnemy enemy = new StraightLineEnemy(xCoord, yCoord, direction);
		return enemy;
	}

	/**
	 * This method reads the Teleporter object in the file.
	 * 
	 * @param in The scanner object.
	 * @return The object of the teleporter tile object.
	 * @throws FileNotFoundException If the scanner can't read any file.
	 */
	private static Teleporter teleporter(Scanner in) throws IOException {
		int xCoord = in.nextInt();
		int yCoord = in.nextInt();
		Teleporter t = new Teleporter(xCoord, yCoord);
		return t;
	}

	/**
	 * Loads the file.
	 * 
	 * @param filename String of the name of the file (map) you wish to be read in.
	 * @return A 2d array of the map read in from file.
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public static Tile[][] loadMap(String filename) throws IOException {
		File inputFile = new File(filename);
		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Open " + inputFile);
			System.exit(0);
		}
		// Reads in the map size.
		int mapWidth = in.nextInt();
		int mapHeight = in.nextInt();
		Tile map[][] = new Tile[mapHeight][mapWidth];
		// Reads in the map with basic tiles.
		for (int i = 0; i < mapHeight; i++) {
			String nextLine = in.next() + in.nextLine();
			for (int j = 0; j < mapWidth; j++) {
				char tile = nextLine.charAt(j);
				switch (tile) {
				case '0':
					map[i][j] = new ColouredKey("Red");
					break;
				case '1':
					map[i][j] = new ColouredKey("Yellow");
					break;
				case '2':
					map[i][j] = new ColouredKey("Green");
					break;
				case '3':
					map[i][j] = new Flipper();
					break;
				case '4':
					map[i][j] = new RubberBoot();
					break;
				case '5':
					map[i][j] = new Dynamite();
					break;
				case 'A':
					map[i][j] = new Ground();
					break;
				case 'B':
					map[i][j] = new Wall();
					break;
				case 'D':
					map[i][j] = new Goal();
					break;
				case 'E':
					map[i][j] = new Electricity();
					break;
				case 'F':
					map[i][j] = new Water();
					break;
				case 'G':
					map[i][j] = new ColouredDoor("Red");
					break;
				case 'H':
					map[i][j] = new ColouredDoor("Yellow");
					break;
				case 'I':
					map[i][j] = new ColouredDoor("Green");
					break;
				case 'J':
					map[i][j] = new TokenDoor();
					break;
				case 'P':
					map[i][j] = new Player(j, i);
					break;
				default:
					map[i][j] = new Ground();
					break;
				}
			}
		}
		// Reads in the special tiles that follow under the map layout in
		// the txt file.
		while (in.hasNext()) {
			String specialTile = in.next();
			switch (specialTile) {
			case "smart":
				SmartEnemy sEnemy = smartEnemyTile(in);
				map[sEnemy.getYcoord()][sEnemy.getXcoord()] = sEnemy;
				break;
			case "dumb":
				DumbEnemy dEnemy = dumbEnemyTile(in);
				map[dEnemy.getYcoord()][dEnemy.getXcoord()] = dEnemy;
				break;
			case "wallFollow":
				WallFollowingEnemy WFEnemy = wallFollowEnemyTile(in);
				map[WFEnemy.getPosY()][WFEnemy.getPosX()] = WFEnemy;
				break;
			case "straightLine":
				StraightLineEnemy SLEnemy = straightLineEnemyTile(in);
				map[SLEnemy.getYcoord()][SLEnemy.getXcoord()] = SLEnemy;
				break;
			case "teleporter":
				Teleporter tele1 = teleporter(in);
				Teleporter tele2 = teleporter(in);
				tele1.setPair(tele2);
				tele2.setPair(tele1);
				map[tele1.getPosY()][tele1.getPosX()] = tele1;
				map[tele2.getPosY()][tele2.getPosX()] = tele2;
				break;
			}
		}
		in.close();
		return map;
	}

	/**
	 * Saves the map data to a file.
	 * 
	 * @param filename String of the name of the file where the data would be saved.
	 * @param map      A tile map with the size of X and Y.
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public static void saveMap(String filename, Tile[][] map) throws IOException {
		File inputFile = new File(filename);
		if (!inputFile.exists()) {
			inputFile.createNewFile();
		} else {
			inputFile.delete();
		}
		Formatter f = new Formatter(inputFile);
		ArrayList<Tile> specialTiles = new ArrayList<Tile>();
		String grid = "";

		int mapHeight = map.length;
		int mapWidth = map[0].length;
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				char tile = map[y][x].getRefID();
				/*
				 * If it's a special tile, add a ground tile as special tiles are saved
				 * underneath with extra information.
				 */
				switch (tile) {
				case 'C':
				case 'K':
				case 'L':
				case 'M':
				case 'N':
					specialTiles.add(map[y][x]);
					grid = grid + "A";
					break;
				default:
					grid = grid + tile;
					break;
				}
			}
			grid = grid + "\n";
		}
		// Write the size of the map.
		f.format("%d %d\n", mapWidth, mapHeight);
		// Write the map with basic tiles only.
		f.format("%s", grid);
		// Write all special tiles defined underneath.
		for (int i = 0; i < specialTiles.size(); i++) {
			char tile = specialTiles.get(i).getRefID();
			switch (tile) {
			case 'C':
				Teleporter t = (Teleporter) specialTiles.get(i);
				f.format("%s %d %d %d %d\n", "teleporter", t.getPosX(), t.getPosY(), t.getPair().getPosX(),
						t.getPair().getPosY());
				break;
			case 'K':
				SmartEnemy sEnemy = (SmartEnemy) specialTiles.get(i);
				f.format("%s %d %d\n", "smart", sEnemy.getXcoord(), sEnemy.getYcoord());
				break;
			case 'L':
				DumbEnemy dEnemy = (DumbEnemy) specialTiles.get(i);
				f.format("%s %d %d\n", "dumb", dEnemy.getXcoord(), dEnemy.getYcoord());
				break;
			case 'M':
				WallFollowingEnemy WFEnemy = (WallFollowingEnemy) specialTiles.get(i);
				f.format("%s %d %d %s\n", "wallFollow", WFEnemy.getPosX(), WFEnemy.getPosY(), WFEnemy.getOrientation());
				break;
			case 'N':
				StraightLineEnemy SLEnemy = (StraightLineEnemy) specialTiles.get(i);
				f.format("%s %d %d %s\n", "straightLine", SLEnemy.getXcoord(), SLEnemy.getYcoord(),
						SLEnemy.getOrientation());
				break;
			default:
				break;
			}
		}
		f.close();
	}

	/**
	 * Reads in a file with the Leaderboard records.
	 * 
	 * @param filename String of the name of the file (leaderboard) you wish to be
	 *                 read in.
	 * @return An array of the leaderboard records read in from file.
	 */
	public static LeaderboardRecord[] readLeaderboard(String filename) {
		LeaderboardRecord records[] = new LeaderboardRecord[3];
		File inputFile = new File(filename);
		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Open " + inputFile);
			System.exit(0);
		}
		int count = 0;
		// read in each line from file and populate the list with a new record.
		while (in.hasNext()) {
			records[count] = new LeaderboardRecord(in.next(), in.nextInt());
			count++;
		}
		return records;
	}

	/**
	 * This method updates a record in a leaderboards txt file.
	 * 
	 * @param filename    String of the name of the file of the leaderboard.
	 * @param recordToAdd Leaderboard Record to update in file.
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public static void updateLeaderboard(String filename, LeaderboardRecord recordToAdd) throws IOException {
		LeaderboardRecord[] records = readLeaderboard(filename);
		FileWriter fr = new FileWriter(filename, false);
		String str = "";
		// checks if the record to add is indeed going to be added (a time less than the
		// 3rd record)
		if (recordToAdd.getTime() < records[records.length - 1].getTime()) {
			records[records.length - 1] = recordToAdd;
		} else if (records[records.length - 1].getTime() == 0) {
			records[records.length - 1] = recordToAdd;
		}
		// only 3 records so the forced sort here is like a bubble sort (not implemented
		// fully).
		for (int i = 1; i < records.length; i++) {
			if (records[records.length - i].getTime() < records[records.length - (i + 1)].getTime()
					|| records[records.length - (i + 1)].getTime() == 0) {
				LeaderboardRecord temp = records[records.length - (i + 1)];
				records[records.length - (i + 1)] = records[records.length - i];
				records[records.length - i] = temp;
			}
		}
		// Writes the records back to file
		for (int j = 0; j < records.length; j++) {
			str = str + String.format("%s %d\n", records[j].getUsername(), records[j].getTime());
		}
		fr.write(str);
		fr.close();
	}

	/**
	 * Reads all profiles from file.
	 * 
	 * @param filename String of the name of the file.
	 * @return An array of profiles from file.
	 */
	public static Profile[] readProfile(String filename) {
		File inputFile = new File(filename);
		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Open " + inputFile);
			System.exit(0);
		}
		ArrayList<Profile> listOfProfiles = new ArrayList<Profile>();
		// Adds a profile to an arrayList.
		while (in.hasNext()) {
			String userName = in.next();
			int level = in.nextInt();
			listOfProfiles.add(new Profile(userName, level));
			in.nextLine();
		}
		in.close();
		// Converts Arraylist to an Array.
		Profile[] profilesToReadIn = new Profile[listOfProfiles.size()];
		for (int i = 0; i < listOfProfiles.size(); i++) {
			profilesToReadIn[i] = listOfProfiles.get(i);
		}
		return profilesToReadIn;
	}

	/**
	 * This method adds a profile to file.
	 * 
	 * @param filename      String of the name of the file.
	 * @param profilesToAdd the Profile to be added to the file.
	 */
	public static void addProfile(String filename, Profile profileToAdd) throws IOException {
		initiliseLevelData(String.format("%sLevelData.txt", profileToAdd.getUserName()));
		File inputFile = new File(filename);
		if (!inputFile.exists()) {
			inputFile.createNewFile();
		}
		// Appends the Profile to the end of the txt File.
		FileWriter fr = new FileWriter(inputFile, true);
		String str = String.format("%s %d\n", profileToAdd.getUserName(), profileToAdd.getHighestLevel());
		fr.write(str);
		fr.close();
	}

	/**
	 * Deletes a profile from file.
	 * 
	 * @param filename String of the name of the file.
	 * @param userName String of the username to be deleted.
	 */
	public static void deleteProfile(String filename, String userName) throws IOException {
		File inputFile = new File(filename);
		File tempFile = new File("tempFile.txt");
		File deleteLevels = new File(String.format("%sLevelData.txt", userName));
		// Deletes the Profiles corresponding LevelData txt File.
		if (deleteLevels.exists()) {
			deleteLevels.delete();
		}
		// Creates a temporary txt file to write to that one, which we rename back.
		if (tempFile.exists()) {
			System.out.print("This file exists at: " + tempFile.getPath());
		} else {
			tempFile.createNewFile();
		}
		Scanner in = new Scanner(inputFile);
		FileWriter fr = new FileWriter(tempFile, true);
		// Reads in and writes only the records that we want to keep.
		while (in.hasNext()) {
			String name = in.next();
			if (name.equalsIgnoreCase(userName)) {
				in.nextLine();
			} else {
				fr.write(String.format("%s %d\n", name, in.nextInt()));
			}
		}
		in.close();
		fr.close();
		inputFile.delete();
		// renames the tempFile to actual Profiles.txt name.
		Path source = Paths.get(tempFile.getAbsolutePath());
		Files.move(source, source.resolveSibling("Profiles.txt"));
	}

	/**
	 * Reads the data of the Levels (per profile).
	 * 
	 * @param filename String of the name of the file.
	 * @return An array of the levels that are read from the file.
	 */
	public static Level[] readLevelData(String filename) {
		File inputFile = new File(filename);
		Scanner in = null;
		try {
			in = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Open " + inputFile);
			System.exit(0);
		}
		// Reads, creates and adds a Level to the arrayList.
		ArrayList<Level> listOfLevels = new ArrayList<Level>();
		while (in.hasNext()) {
			Inventory inv = new Inventory();
			int levelNum = in.nextInt();
			boolean isCompleted = in.nextBoolean();
			int time = in.nextInt();
			String fileString = in.next();
			if (!isCompleted) {
				while (in.hasNextInt()) {
					inv.addItem(in.nextInt());
				}
			}
			listOfLevels.add(new Level(levelNum, isCompleted, time, fileString, inv));
			in.nextLine();
		}
		// Converts ArrayList to an Array.
		Level levels[] = new Level[listOfLevels.size()];
		for (int i = 0; i < listOfLevels.size(); i++) {
			levels[i] = listOfLevels.get(i);
		}
		return levels;
	}

	/**
	 * This method initialises the data of the Levels (per profile).
	 * 
	 * @param filename String of the name of the file.
	 * @throws IOException If the stream to the file cannot be closed or written to.
	 */
	public static void initiliseLevelData(String filename) throws IOException {
		Inventory inv = new Inventory();
		File inputFile = new File(filename);
		FileWriter fr = new FileWriter(inputFile, false);
		if (!inputFile.exists()) {
			inputFile.createNewFile();
		}
		// Sets the data of the Levels to default values, and writes to file.
		for (int i = 0; i < 3; i++) {
			Level newLevel = new Level(i + 1, false, 0, String.format("map%d.txt", i + 1), inv);
			String str = String.format("%d %b %d %s", newLevel.getLevelNum(), newLevel.completed(),
					newLevel.getSavedTime(), newLevel.getSavedMap());
			for (int j = 0; j < newLevel.getSavedInventory().getNumOfItems(); j++) {
				str = str + String.format(" %d", newLevel.getSavedInventory().getItemID(j));
			}
			str = str + " end\n";
			fr.write(str);
		}
		fr.close();
	}

	/**
	 * Updates a Level in the Levels (per profile).
	 * 
	 * @param filename    String of the name of the file.
	 * @param lvlToUpdate the level to update in the file.
	 */
	public static void updateLevel(String filename, Level lvlToUpdate) throws IOException {
		Level[] listOfLevels = readLevelData(filename);
		File inputFile = new File(filename);
		FileWriter fr = new FileWriter(inputFile, false);
		String str = "";
		// Reads and writes back with the updated level.
		for (int i = 0; i < listOfLevels.length; i++) {
			if (lvlToUpdate.getLevelNum() == listOfLevels[i].getLevelNum()) {
				listOfLevels[i] = lvlToUpdate;
			}
			str = str + String.format("%d %b %d %s", listOfLevels[i].getLevelNum(), listOfLevels[i].completed(),
					listOfLevels[i].getSavedTime(), listOfLevels[i].getSavedMap());
			for (int j = 0; j < listOfLevels[i].getSavedInventory().getNumOfItems(); j++) {
				str = str + String.format(" %d", listOfLevels[i].getSavedInventory().getItemID(j));
			}
			str = str + " end\n";
		}
		fr.write(str);
		fr.close();
	}

}
