import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is the controller for Output.fxml This class runs the game and
 * generates the screen of the game.
 * 
 * @author Steven Ho, Nikko Pang, Hugo Green, Ethan Young, Sebastian Fletcher
 *
 */
public class OutputController {

	@FXML
	private AnchorPane rootpane;

	@FXML
	private Label levelNum;

	@FXML
	private Label timerBox;

	@FXML
	private GridPane gridBox;

	@FXML
	private Button pauseButton;

	@FXML
	private Label userName;

	private int currentLevelNum;

	private int time = -1;

	private long lastUpdate = 0;

	private Player player;

	private Tile[][] map;

	private Tile[][] screenView = new Tile[5][5];

	private Level[] levels;

	private Profile currentProfile;

	private boolean isPause;

	@FXML
	private Button saveButton;

	/**
	 * This method sets the current profile.
	 * 
	 * @param profile The user's profile
	 */
	public void setCurrentProfile(Profile profile) {
		this.currentProfile = profile;
	}

	/**
	 * This method sets the level by reading from the profile.
	 * 
	 * @throws IOException if the level data does not exist
	 */
	public void setLevels() throws IOException {
		this.levels = FileInputOutput
				.readLevelData(String.format("%sLevelData.txt", this.currentProfile.getUserName()));
	}

	/**
	 * The timer for the game which updates every 1 second
	 */
	private AnimationTimer timer = new AnimationTimer() {

		public void handle(long now) {
			if (now - lastUpdate >= 1e+9) {
				time++;
				String s = String.valueOf(time);
				timerBox.setText(s);
				lastUpdate = now;
			}
		}
	};

	/**
	 * This method sets the current player.
	 * 
	 * @param player current player object
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * This method gets the current player
	 * 
	 * @return current player object
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * This method sets the zoomed view of the map.
	 * 
	 * @param map current map
	 */
	public void setScreenView(Tile[][] map) {
		int playerPosX = this.player.getPosX();
		int playerPosY = this.player.getPosY();
		for (int y = -2; y < 3; y++) {
			for (int x = -2; x < 3; x++) {
				screenView[y + 2][x + 2] = map[playerPosY + y][playerPosX + x];
			}
		}
	}

	/**
	 * This method refreshes the whole screen after the player movement
	 */
	public void updateMap() {
		setScreenView(this.map);
		gridBox.getChildren().clear();
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				ImageView imageToSet = new ImageView();
				imageToSet.setImage(screenView[y][x].getImageLink());
				imageToSet.setPreserveRatio(false);
				imageToSet.setFitHeight(110);
				imageToSet.setFitWidth(120);

				gridBox.add(imageToSet, x, y);
			}
		}
	}

	/**
	 * This method read in the map and store it in a copy.
	 * 
	 * @throws IOException if the required map does not exist
	 */
	public void printMap() throws IOException {
		System.out.print(this.levels[currentLevelNum - 1].getSavedMap());
		this.map = FileInputOutput.loadMap(this.levels[currentLevelNum - 1].getSavedMap());
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				if (map[y][x].getName().equals("Player")) {
					setPlayer((Player) map[y][x]);
				}
			}
		}
		setScreenView(this.map);
	}

	/**
	 * This method handles all process after the player makes his/her move. All
	 * exceptions and cases are processed in this method.
	 * 
	 * @param point the player facing direction
	 * @throws IOException if the fxml files cannot be read
	 */
	public void movementProcess(String point) throws IOException {

		int playerNewX = player.getPosX();
		int playerNewY = player.getPosY();
		// Rotating the player and save the next tile that the player is going
		// to step on
		if (point.equals("Up")) {
			player.turnBack();
			playerNewX = player.getPosX();
			playerNewY = player.getPosY() - 1;
		} else if (point.equals("Left")) {
			player.turnLeft();
			playerNewX = player.getPosX() - 1;
			playerNewY = player.getPosY();

		} else if (point.equals("Right")) {
			player.turnRight();
			playerNewX = player.getPosX() + 1;
			playerNewY = player.getPosY();

		} else if (point.equals("Down")) {
			player.turnForward();
			playerNewX = player.getPosX();
			playerNewY = player.getPosY() + 1;
		}

		// Change the image that the player is originally standing
		map[player.getPosY()][player.getPosX()] = player.getUnderPlayer();
		// updateMap();

		/*
		 * Draw player's new position and draw the image of the tile that the player is
		 * originally stepping on. If the tile that the player is going to step is
		 * originally a player tile, pretend the tile is a ground object
		 */

		/*
		 * Block Tile handling
		 */
		if (this.map[playerNewY][playerNewX].getRefID() == 'B') {
			map[player.getPosY()][player.getPosX()] = player;
			updateMap();
		} else if (this.map[playerNewY][playerNewX].getRefID() == 'G'
				|| this.map[playerNewY][playerNewX].getRefID() == 'H'
				|| this.map[playerNewY][playerNewX].getRefID() == 'I') {
			/*
			 * ColouredDoor handling
			 */
			ColouredDoor door = (ColouredDoor) this.map[playerNewY][playerNewX];
			/**
			 * Check if the door is open
			 */
			if (door.isOpened()) {
				player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				player.movePlayer(point);
				map[player.getPosY()][player.getPosX()] = player;
				updateMap();
			} else {
				if (player.checkInventory(door.getRequiredRefId())) {
					door.openDoor();
					System.out.println("Door opened");
					player.removeItem(door.getRequiredRefId());
					System.out.println("Key removed");
					Ground ground = new Ground();
					this.map[playerNewY][playerNewX] = ground;
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
					updateMap();
				} else {
					map[player.getPosY()][player.getPosX()] = player;
				}
			}
		} else {
			/*
			 * Check if the tile we are going is a player spawn point
			 */
			if (this.map[playerNewY][playerNewX] == player) {
				Ground ground = new Ground();
				player.setUnderPlayer(ground);
			} else if (this.map[playerNewY][playerNewX].getRefID() == '0'
					|| this.map[playerNewY][playerNewX].getRefID() == '1'
					|| this.map[playerNewY][playerNewX].getRefID() == '2') {

				/*
				 * ColouredKey handling
				 */

				ColouredKey key = (ColouredKey) this.map[playerNewY][playerNewX];
				if (key.isPickedUp()) {
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
				} else {
					key.pickUpKey(player);
					player.movePlayer(point);
					System.out.println("Key added");
					Ground ground = new Ground();
					this.map[playerNewY][playerNewX] = ground;
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				}

			} else if (this.map[playerNewY][playerNewX].getRefID() == '3') {
				/*
				 * Flipper handling
				 */
				Flipper flipper = new Flipper();
				if (flipper.isPickedUp()) {
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
				} else {
					flipper.pickUpFlipper(player);
					player.movePlayer(point);
					System.out.println("Key added");
					Ground ground = new Ground();
					this.map[playerNewY][playerNewX] = ground;
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				}
			} else if (this.map[playerNewY][playerNewX].getRefID() == 'F') {
				/*
				 * Water handling
				 */
				Water water = (Water) this.map[playerNewY][playerNewX];
				if (player.checkInventory(water.getRequiredRefId())) {
					player.movePlayer(point);
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				} else {
					initData();
					printMap();
				}

			} else if (this.map[playerNewY][playerNewX].getRefID() == '4') {
				/*
				 * RubberBoot handling
				 */
				RubberBoot rubberBoot = new RubberBoot();
				if (rubberBoot.isPickedUp()) {
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
				} else {
					rubberBoot.pickUpBoot(player);
					player.movePlayer(point);
					System.out.println("Key added");
					Ground ground = new Ground();
					this.map[playerNewY][playerNewX] = ground;
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				}
			} else if (this.map[playerNewY][playerNewX].getRefID() == 'E') {
				/*
				 * Electricity handling
				 */
				Electricity electricity = (Electricity) this.map[playerNewY][playerNewX];
				if (player.checkInventory(electricity.getRequiredRefId())) {
					player.movePlayer(point);
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				} else {
					initData();
					printMap();
				}
			} else if (this.map[playerNewY][playerNewX].getRefID() == '5') {
				/*
				 * Dynamite handling
				 */
				Dynamite dynamite = new Dynamite();
				if (dynamite.isPickedUp()) {
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
				} else {
					dynamite.pickUpDynamite(player);
					player.movePlayer(point);
					System.out.println("Dynamite added");
					Ground ground = new Ground();
					this.map[playerNewY][playerNewX] = ground;
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
				}
			} else if (this.map[playerNewY][playerNewX].getRefID() == 'J') {
				/*
				 * TokenDoor handling
				 */
				TokenDoor door = (TokenDoor) this.map[playerNewY][playerNewX];
				if (door.isOpened()) {
					player.setUnderPlayer(this.map[playerNewY][playerNewX]);
					player.movePlayer(point);
					map[player.getPosY()][player.getPosX()] = player;
				} else {
					if (player.getInventory().getNumOfDynamites() >= door.getRequiredDynamite()) {
						door.openDoor();
						player.getInventory().removeItem(5);
						player.getInventory().removeItem(5);
						player.getInventory().removeItem(5);
						Ground ground = new Ground();
						this.map[playerNewY][playerNewX] = ground;
						player.setUnderPlayer(this.map[playerNewY][playerNewX]);
						player.movePlayer(point);
						map[player.getPosY()][player.getPosX()] = player;
						updateMap();
					} else {
						map[player.getPosY()][player.getPosX()] = player;

					}
				}
			} else if (this.map[playerNewY][playerNewX].getRefID() == 'C') {
				/*
				 * Teleporter handling
				 */
				Teleporter tele = (Teleporter) map[playerNewY][playerNewX];
				Ground ground = new Ground();
				map[player.getPosY()][player.getPosX()] = ground;
				player.teleportPlayer(tele.getPair());
				map[tele.getPair().getPosY()][tele.getPair().getPosX() + 1] = player;

			} else if (this.map[playerNewY][playerNewX].getRefID() == 'D') {
				/*
				 * Goal handling
				 */
				timer.stop();
				LeaderboardRecord newRecord = new LeaderboardRecord(this.currentProfile.getUserName(), time);
				FileInputOutput.updateLeaderboard(String.format("Leaderboard%d.txt", currentLevelNum), newRecord);
				Inventory tempInv = new Inventory();
				this.player.setInventory(tempInv);
				Level tempLevel = new Level(currentLevelNum, true, 0, String.format("map%d.txt", currentLevelNum),
						tempInv);
				FileInputOutput.updateLevel(String.format("%sLevelData.txt", currentProfile.getUserName()), tempLevel);
				// Something should happen here
				this.currentLevelNum++;
				MapLoadData.setLevelNum(this.currentLevelNum);
				levelNum.setText(Integer.toString(currentLevelNum));
				/*
				 * If we finished all the map, we will return back to the main menu. Otherwise,
				 * we will play the next map.
				 */
				if (this.currentLevelNum > 3) {
					moveToMenu();
				} else {
					printMap();
					updateMap();
					time = -1;
					timer.start();
				}

			} else if (this.map[playerNewY][playerNewX].getRefID() == 'K'
					|| this.map[playerNewY][playerNewX].getRefID() == 'L'
					|| this.map[playerNewY][playerNewX].getRefID() == 'M'
					|| this.map[playerNewY][playerNewX].getRefID() == 'N') {
				/*
				 * The enemies handling. When player collides with enemy, it dies and restarts
				 * the level.
				 */

				player.setAlive(false);
				initData();
				printMap();

			} else {
				player.movePlayer(point);
				player.setUnderPlayer(this.map[playerNewY][playerNewX]);
			}
			map[player.getPosY()][player.getPosX()] = player;

			/*
			 * If the player is alive, then call enemy to move and update the map.
			 */
			if (player.isAlive()) {
				moveEnemys();
				updateMap();
			}
		}
	}

	/**
	 * This method handles the key event when W,A,S,D is pressed
	 * 
	 * @param event The key input
	 * @throws IOException
	 */
	@FXML
	void keyPressed(KeyEvent event) throws IOException {
		if (isPause == false) {
			if (event.getCode() == KeyCode.W) {
				System.out.println("Up Key pressed");
				movementProcess("Up");

			} else if (event.getCode() == KeyCode.A) {
				System.out.println("Left Key pressed");
				movementProcess("Left");

			} else if (event.getCode() == KeyCode.D) {
				System.out.println("Right Key pressed");
				movementProcess("Right");

			} else if (event.getCode() == KeyCode.S) {
				System.out.println("Down Key pressed");
				movementProcess("Down");

			} else if (event.getCode() == KeyCode.F) {
				updateMap();
			} else {
				System.out.println("Game paused.");
			}
		}
	}

	/**
	 * This method handles the event when the pause button is clicked. It will pause
	 * and resume the game if the button is clicked.
	 * 
	 * @param event The mouse click
	 */
	@FXML
	void pauseButtonClicked(MouseEvent event) {
		if (isPause == false) { // The game is running but player wants to pause.
			setIsPause(true);
			pauseButton.setText("Resume");
			timer.stop();
		} else { // The game is paused but player wants to resume.
			setIsPause(false);
			pauseButton.setText("Pause");
			timer.start();
		}
	}

	/**
	 * This method handles the event when the inventory button is clicked
	 * 
	 * @param event The mouse click
	 */
	@FXML
	void inventoryButtonClicked(MouseEvent event) {
		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryScreen.fxml"));
			root = loader.load();

			InventoryScreenController inventoryScreen = loader.getController();
			inventoryScreen.setRedKeyNum(this.player.getInventory().getNumOfRedKey());
			inventoryScreen.setYellowKeyNum(this.player.getInventory().getNumOfYellowKey());
			inventoryScreen.setGreenKeyNum(this.player.getInventory().getNumOfGreenKey());
			inventoryScreen.setDynamiteNum(this.player.getInventory().getNumOfDynamites());
			inventoryScreen.setFlipperNum(this.player.checkInventory('3'));
			inventoryScreen.setRubberBootNum(this.player.checkInventory('4'));
			Stage stage = new Stage();
			stage.setTitle("Inventory");
			stage.setScene(new Scene(root));
			stage.requestFocus();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The x and y coordinates of the player.
	 */
	int xPlayerLocForEnemies;
	int yPlayerLocForEnemies;

	/**
	 * Loops through the map for each coordinate. If the tile is an enemy, call its
	 * move method.
	 */
	private void resetEnemiesVisited() {
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {

				if (this.map[x][y] instanceof Player) {
					this.xPlayerLocForEnemies = y;
					this.yPlayerLocForEnemies = x;
				}

				if (this.map[x][y] instanceof DumbEnemy) {
					((DumbEnemy) map[x][y]).setVisitedThisTurn(false);

				} else if (this.map[x][y] instanceof SmartEnemy) {
					((SmartEnemy) map[x][y]).setVisitedThisTurn(false);

				} else if (this.map[x][y] instanceof WallFollowingEnemy) {
					((WallFollowingEnemy) map[x][y]).setVisitedThisTurn(false);

				} else if (this.map[x][y] instanceof StraightLineEnemy) {
					((StraightLineEnemy) map[x][y]).setVisitedThisTurn(false);

				}

			}

		}
	}

	/**
	 * Calls the enemies to make a move based on their respective algorithms when it
	 * is their turn. After it has made a move, it will be replaced with the a
	 * Ground tile.
	 * 
	 * @throws IOException
	 */
	private void moveEnemys() throws IOException {
		resetEnemiesVisited();

		Coordinate nextPos;
		Ground ground = new Ground();

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {

				if (this.map[x][y] instanceof DumbEnemy) {

					if (!((DumbEnemy) map[x][y]).isVisitedThisTurn()) {
						((DumbEnemy) map[x][y])
								.setPlayerLocation(new Coordinate(xPlayerLocForEnemies, yPlayerLocForEnemies));
						((DumbEnemy) map[x][y]).setMap(map);
						nextPos = ((DumbEnemy) map[x][y]).moveEnemy();

						DumbEnemy currentEnemyState = new DumbEnemy(nextPos.getX(), nextPos.getY());
						currentEnemyState.setVisitedThisTurn(true);

						if (nextPos.getX() == xPlayerLocForEnemies && nextPos.getY() == yPlayerLocForEnemies) {
							/*
							 * Handles enemy landing on player. If they collide, it restarts the level.
							 */
							initData();
							printMap();
						} else {
							map[x][y] = ground;
							map[nextPos.getY()][nextPos.getX()] = currentEnemyState;
						}
					}

				} else if (this.map[x][y] instanceof SmartEnemy) {

					if (!((SmartEnemy) map[x][y]).isVisitedThisTurn()) {

						((SmartEnemy) map[x][y]).setMap(map);
						System.out.println("player pos is " + this.player.getPosX() + " " + this.player.getPosY());
						nextPos = ((SmartEnemy) map[x][y]).moveEnemy();
						System.out.println("player pos is " + this.player.getPosX() + " " + this.player.getPosY());

						SmartEnemy currentEnemyState = new SmartEnemy(nextPos.getX(), nextPos.getY());
						currentEnemyState.setVisitedThisTurn(true);

						if (nextPos.getX() == xPlayerLocForEnemies && nextPos.getY() == yPlayerLocForEnemies) {
							/*
							 * Handles enemy landing on player. If they collide, it restarts the level.
							 */
							initData();
							printMap();
						} else {
							map[x][y] = ground;
							map[nextPos.getY()][nextPos.getX()] = currentEnemyState;
						}

					}

				} else if (this.map[x][y] instanceof WallFollowingEnemy) {

					if (!((WallFollowingEnemy) map[x][y]).isVisitedThisTurn()) {
						((WallFollowingEnemy) map[x][y]).setMap(map);
						((WallFollowingEnemy) map[x][y]).nextMove();

						int newX = ((WallFollowingEnemy) map[x][y]).getPosX();
						int newY = ((WallFollowingEnemy) map[x][y]).getPosY();
						String newOrientation = ((WallFollowingEnemy) map[x][y]).getOrientation();

						WallFollowingEnemy currentEnemyState = new WallFollowingEnemy(newX, newY, newOrientation);
						currentEnemyState.setVisitedThisTurn(true);

						if (newX == xPlayerLocForEnemies && newY == yPlayerLocForEnemies) {
							/*
							 * Handles enemy landing on player. If they collide, it restarts the level.
							 */
							initData();
							printMap();
						} else {
							map[x][y] = ground;
							map[newY][newX] = currentEnemyState;
						}
					}
				} else if (this.map[x][y] instanceof StraightLineEnemy) {

					if (!((StraightLineEnemy) map[x][y]).isVisitedThisTurn()) {
						((StraightLineEnemy) map[x][y]).setMap(map);

						nextPos = ((StraightLineEnemy) map[x][y]).moveEnemy();

						StraightLineEnemy currentEnemyState = (StraightLineEnemy) map[x][y];
						currentEnemyState.setVisitedThisTurn(true);
						if (nextPos.getX() == xPlayerLocForEnemies && nextPos.getY() == yPlayerLocForEnemies) {
							/*
							 * Handles enemy landing on player. If they collide, it restarts the level.
							 */
							initData();
							printMap();
						} else {
							map[x][y] = ground;
							map[nextPos.getY()][nextPos.getX()] = currentEnemyState;
						}

					}
				}
			}
		}
	}

	/**
	 * This method initialise all the screen to be displayed after starting the
	 * game.
	 * 
	 * @throws IOException if fxml file does not exist
	 */
	public void initialize() throws IOException {

		initData();

		setLevels();

		printMap();
		
		this.player.setInventory(this.levels[currentLevelNum - 1].getSavedInventory());

		updateMap();

		timerBox.setAlignment(Pos.CENTER);

		timer.start();
	}

	/**
	 * This method changes the scene back to the main menu.
	 * 
	 * @throws IOException
	 */
	public void moveToMenu() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) timerBox.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		scene.getRoot().requestFocus();
		stage.show();
	}

	/**
	 * Changes the state of the game whether it is paused or not.
	 * 
	 * @param paused The state of the game whether it is paused or not.
	 */
	private void setIsPause(boolean paused) {
		this.isPause = paused;
	}

	/**
	 * Gets the state of the game whether it is paused or not.
	 * 
	 * @return The state of the game whether it is paused or not.
	 */
	public boolean getPaused() {
		return isPause;
	}

	/**
	 * This button handles the event when save button is clicked.
	 * 
	 * @param event mouse click
	 * @throws IOException if fxml file does not exist
	 */
	@FXML
	void saveButtonClicked(MouseEvent event) throws IOException {
		timer.stop();
		FileInputOutput.saveMap(String.format("%smap%d.txt", this.currentProfile.getUserName(), currentLevelNum),
				this.map);
		Level tempLevel = new Level(currentLevelNum, false, time,
				String.format("%smap%d.txt", this.currentProfile.getUserName(), currentLevelNum),
				this.player.getInventory());
		FileInputOutput.updateLevel(String.format("%sLevelData.txt", this.currentProfile.getUserName()), tempLevel);
		timer.start();
	}

	/**
	 * This method sets the current level number
	 * 
	 * @param i current level number
	 */
	public void setCurrentLevelNum(int i) {
		this.currentLevelNum = i;
	}

	/**
	 * This method initialise the data we are going to use through out the whole
	 * level.
	 */
	public void initData() {
		
		this.currentLevelNum = MapLoadData.getLevelNum();

		this.levelNum.setText(Integer.toString(currentLevelNum));

		this.currentProfile = MapLoadData.getProfile();

		this.userName.setText("username: " + this.currentProfile.getUserName());

		this.userName.setWrapText(true);

		this.time = MapLoadData.getTime();
	}

}
