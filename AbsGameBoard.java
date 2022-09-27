package cpsc2150.extendedConnectX.models;

/**
 * An abstract class that contains and overrides the toString method
 *
 * @author Skylar Hubbarth
 * @version 3.0
 *
 * @invariant
 * 0 <= row <= MAX_ROW
 * 0 <= col <= MAX_COL
 *
 */

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Method to return one string that shows the entire game board
     *
     * @return returns a string that shows the entire game board
     *
     * @post
     * row = #row
     * col = #col
     * toString = [string representation of game board state]
     *
     */
    @Override
    public String toString() {
        int width = getNumColumns();
        int height = getNumRows();

        String boardString = "";

        for(int i = 0; i < width; i++) {
            if(i <= 9) {
                boardString += "| " + i;
            }
            else {
                boardString += "|" + i;
            }
        }

        boardString += "|\n";

        for(int x = height-1; x > -1; x--) {
            boardString += "|";
            for(int y = 0; y < width; y++) {
                BoardPosition pos = new BoardPosition(x,y);
                boardString += whatsAtPos(pos) + " |";
            }
            boardString += "\n";
        }

        return boardString;
    }
}
