package cpsc2150.extendedConnectX.models;

/**
 * A class to keep track of an individual cell for a board.
 *
 * @author Skylar Hubbarth
 * @version 3.0
 *
 * @invariant
 * 0 <= row <= MAX_ROW
 * 0 <= col <= MAX_COL
 *
 */

public class BoardPosition {

    private final int row;
    private final int col;

    /**
     * Constructor to initialize the objects of the BoardPosition class.
     *
     * @param r, row
     * @param c, col
     *
     * @pre
     * 0 <= r <= 5
     * 0 <= c <= 8
     *
     * @post
     * row = r
     * col = c
     */
    public BoardPosition(int r, int c) {
        row = r;
        col = c;
    }

    /**
     * Method to get and return the row.
     *
     * @return integer value of the row
     *
     * @pre
     *
     * @post
     * row = #row
     * col = #col
     * getRow = row
     *
     */
    public int getRow() {
        return row;
    }

    /**
     * Method to get and return the column.
     *
     * @return integer value of the column
     *
     * @pre
     *
     * @post
     * row = #row
     * col = #col
     * getColumn = col
     *
     */
    public int getColumn() {
        return col;
    }

    /**
     * Method to create a string that shows the BoardPosition.
     *
     * @return returns a string in the format "<row>,<column>"
     *
     * @pre
     *
     * @post
     * row = #row
     * col = #col
     *
     */
    @Override
    public String toString() {
        String myString = row + "," + col;
        return myString;
    }

    /**
     * Method to check if two BoardPositions are equal.
     *
     * @return returns true if BoardPosition has the same row and column
     *
     * @param pos, position [row][col]
     *
     * @pre
     *
     * @post
     * row = #row
     * col = #col
     *
     */
    public boolean equals(Object pos) {
        if(pos == this) {
            return true;
        }

        if(pos instanceof BoardPosition) {
            BoardPosition pos2 = (BoardPosition) pos;
            if (row == pos2.getRow() && col == pos2.getColumn()) {
                return true;
            }
        }
        return false;
    }

}

