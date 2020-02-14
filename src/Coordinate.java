
/**
 * This class represents integer Coordinate, (x,y).
 * 
 * @author Hugo Green
 * @version 1.0
 */
public class Coordinate {
	private int X;
	private int Y;

	/**
	 * Create new Coordinate with given x and y positions.
	 * 
	 * @param Xposition The x coordinate.
	 * @param Yposition The y coordinate.
	 */
	Coordinate(int Xposition, int Yposition) {
		this.X = Xposition;
		this.Y = Yposition;

	}

	/**
	 * Set the x coordinate.
	 * 
	 * @param x The new x coordinate.
	 */
	public void setX(int x) {
		this.X = x;
	}

	/**
	 * Set the y coordinate.
	 * 
	 * @param y The new y coordinate.
	 */
	public void setY(int y) {
		this.Y = y;
	}

	/**
	 * Get the x coordinate.
	 * 
	 * @return The x coordinate.
	 */
	public int getX() {
		return X;
	}

	/**
	 * Get the y coordinate.
	 * 
	 * @return The y coordinate.
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Prints out the x and y coordinate.
	 */
	@Override
	public String toString() {
		return "(" + X + "," + Y + ")";
	}

}
