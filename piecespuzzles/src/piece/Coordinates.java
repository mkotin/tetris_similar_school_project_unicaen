package piece;

/**
 * Represents the coordinates of a cell in a two-dimensional space.
 * Coordinates consist of an x-coordinate and a y-coordinate.
 */
public class Coordinates {
    private int x, y; // BP: On préfère déclarer une variables par ligne

    /**
     * Constructs a new set of coordinates with the specified x and y values.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     */
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the cell.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the cell.
     *
     * @param x The new x-coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the cell.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the cell.
     *
     * @param y The new y-coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a string representation of the coordinates.
     *
     * @return A string representation in the format "Coordinates (x,y)".
     */
    @Override
    public String toString() {
        return "Coordinates (" + x + "," + y + ")";
    }
}
