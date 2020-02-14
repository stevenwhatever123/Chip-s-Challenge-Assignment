import java.util.ArrayList;

/**
 * This class represents as an collectables inventory for the player.
 * Collectables that can be stored are keys, flippers, rubber boots, and
 * dynamite.
 * 
 * @author Pavithraa Paramasivarasa, Luke, Nikko Pang
 * @version 2.1
 */
public class Inventory {

	private final int MAX_CAPACITY = 20; // The maximum capacity of items for the inventory
	private final int MIN_CAPACITY = 0; // The minimum capacity of items for the inventory
	private ArrayList<Integer> inventory;
	private int numOfItems; // The number of items within the inventory
	private int numOfDynamites; // The number of dynamites stored in the inventory

	/**
	 * Creates a new empty inventory object.
	 */
	public Inventory() {
		inventory = new ArrayList<Integer>(MAX_CAPACITY);
		numOfItems = 0;
		numOfDynamites = 0;
	}

	/**
	 * Adds an item in the inventory by its index number
	 * 
	 * @param cRefID The reference id of the collectable
	 * @throws IllegalStateException    Doesn't allow item to be added when
	 *                                  inventory is full.
	 * @throws IllegalArgumentException Doesn't allow item to be added if it isn't a
	 *                                  collectable.
	 */
	public void addItem(int cRefID) throws IllegalStateException, IllegalArgumentException {
		Integer temp = (Integer) cRefID;
		if (isCollectable(cRefID)) {
			if (numOfItems <= MAX_CAPACITY) {
				inventory.add(temp);

				if (temp == 5) {
					numOfDynamites++;
				}

				numOfItems++;
			} else {
				throw new IllegalStateException("Inventory is full.");
			}
		} else {
			throw new IllegalArgumentException("Item added is not a collectable.");
		}
	}

	/**
	 * Removes the first instance of the item found in the arraylist. If the list is
	 * empty, it prints out an error message.
	 * 
	 * @param cRefID The reference id of the collectable object.
	 * @throws IllegalStateException    Doesn't allow item to be removed when
	 *                                  inventory is empty.
	 * @throws IllegalArgumentException Doesn't allow item to be removed if it isn't
	 *                                  a collectable.
	 */
	public void removeItem(int cRefID) throws IllegalStateException, IllegalArgumentException {
		Integer temp = (Integer) cRefID;
		if (isCollectable(cRefID)) {
			if (numOfItems > MIN_CAPACITY) {
				inventory.remove(temp);

				if (temp == 5) {
					numOfDynamites--;
				}

				numOfItems--;
			} else {
				throw new IllegalStateException("Inventory is empty.");
			}
		} else {
			throw new IllegalArgumentException("Item added is not a collectable.");
		}
	}

	/**
	 * Shows how many items are within the inventory
	 * 
	 * @return numOfItems The number of items in the arraylist
	 */
	public int getNumOfItems() {
		return numOfItems;
	}

	/**
	 * Checks whether the inventory has the specific item.
	 * 
	 * @param cRefID The reference id of the item being searched for.
	 * @return Whether the item has the specific item.
	 */
	public boolean haveItem(char refID) {
		int charToNum = Character.getNumericValue(refID);
		Integer cRefID = (Integer) charToNum;

		if (!isEmpty()) {
			for (int i = 0; i < numOfItems; i++) {
				if (inventory.get(i) == cRefID) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Shows how many dynamites are stored within the inventory.
	 * 
	 * @return numOfDynamites The number of dynamite within the inventory.
	 */
	public int getNumOfDynamites() {
		return numOfDynamites;
	}

	/**
	 * Checks whether the arraylist is empty.
	 * 
	 * @return Whether the inventory is empty.
	 */
	public boolean isEmpty() {
		if (numOfItems == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Checks whether the item added is a collectable.
	 * 
	 * @param cRefID The reference id of the tile.
	 * @return Whether that tile is an item that can be collected.
	 */
	private boolean isCollectable(Integer cRefID) {
		int temp = (int) cRefID;
		int[] listOfCollectables = { 0, 1, 2, 3, 4, 5 };

		for (int i = 0; i < listOfCollectables.length; i++) {
			if (listOfCollectables[i] == temp) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the reference id of the item in that specific location of the
	 * arraylist.
	 * 
	 * @param index The position of the item in the array list.
	 * @return The reference id of the item stored in type int.
	 */
	public int getItemID(int index) {
		int temp = (int) inventory.get(index);
		return temp;
	}

	/**
	 * Shows how many red keys are stored within the inventory.
	 * 
	 * @return The number of red keys
	 */
	public int getNumOfRedKey() {
		int counter = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == 0) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Shows how many yellow keys are stored within the inventory.
	 * 
	 * @return The number of yellow keys
	 */
	public int getNumOfYellowKey() {
		int counter = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == 1) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Shows how many green keys are stored within the inventory.
	 * 
	 * @return The number of green keys
	 */
	public int getNumOfGreenKey() {
		int counter = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == 2) {
				counter++;
			}
		}
		return counter;
	}
}