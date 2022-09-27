package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class GameBoardMem that implements the interface in IGameBoard and extends the abstract class AbsGameBoard
 * and contains functions that would make for a more memory efficient game mode.
 *
 * @author Skylar Hubbarth
 * @version 3.0
 *
 * @invariant [p is valid]
 * @invariant [pos is valid]
 * @invariant winCount >= 0
 * @invariant [GameBoard is valid]
 * @invariant [Board has no gaps between tokens]
 * @invariant MIN_ROW <= numRow <= MAX_ROW
 * @invariant MIN_COL <= numCol <= MAX_COL
 * @invariant MIN_COUNT <= numToWin <= MAX_COUNT
 *
 * @correspondence [number of columns] = numCol
 * @correspondence [number of rows] = numRow
 * @correspondence [number to win] = numToWin
 *
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private final int numRow; //row
    private final int numCol; //column
    private final int numToWin;
    private Map<Character,List<BoardPosition>> Board;

    /**
     * Constructor to initiate the game board.
     *
     * @pre
     *
     * @post
     * [numRow is initialized]
     * [numCol is initialized]
     * [numToWin is initialized]
     * [Board is initialized]
     */
    public GameBoardMem(int row, int col, int numWin) {
        numRow = row;
        numCol = col;
        numToWin = numWin;
        Board = new HashMap<Character,List<BoardPosition>>();
    }

    /**
     * Method to place the character p in column c. The token will be placed in the
     * lowest available row in column c.
     *
     * @param p, player
     * @param c, column
     *
     * @pre
     * [p is valid]
     * 0 <= c <= getNumColumns()
     * checkIfFree == true
     *
     * @post
     * p = #p
     * [position of p = lowest row in column c]
     * c = #c
     *
     */
    public void placeToken(char p, int c) {
        Board.putIfAbsent(p, new ArrayList<>());

        BoardPosition tokenPlace = new BoardPosition(0, c);

        for(int x = 0; whatsAtPos(tokenPlace) != ' '; x++) {
            tokenPlace = new BoardPosition(x, c);
        }

        Board.get(p).add(tokenPlace);
    }

    /**
     * Method to check and return what is in the game board at position pos.
     *
     * @return returns what is in the GameBoard at position pos. If no token is there, it returns
     * a blank space char.
     *
     * @param pos, the position [row][col]
     *
     * @pre
     * pos.getRow < getNumRow() && pos.getRow > -1
     * pos.getCol < getNumColumn() && pos.getCol > -1
     *
     * @post
     * whatsAtPos == [char at position pos, X or O] iff [pos is not empty]
     * [else,] whatsAtPos == ' '
     * pos = #pos
     * p = #p
     *
     */
    public char whatsAtPos(BoardPosition pos) {
        char empty = ' ';

        for(Map.Entry<Character,List<BoardPosition>> val : Board.entrySet()) {
            if(val.getValue().contains(pos)) {
                return val.getKey();
            }
        }

        return empty;
    }

    @Override
    /**
     * Method to check if player is at pos.
     *
     * @param pos, the position [row][col]
     * @param player, the player
     *
     * @return returns true if the player is at pos, otherwise, it returns false
     *
     * @pre
     * pos.getRow < getNumRow() && pos.getRow > -1
     * pos.getCol < getNumColumn() && pos.getCol > -1
     * [pos is valid]
     * [player is valid]
     *
     * @post isPlayerAtPos == true iff [player is at pos]
     * [else,] isPlayerAtPos == false
     * pos = #pos
     * p = #p
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(Board.containsKey(player) != true) {
            return false;
        }

        for(Map.Entry<Character,List<BoardPosition>> val : Board.entrySet()) {
            if((val.getValue().contains(pos)) && (val.getKey().equals(player))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method to get and return the number of rows in GameBoard.
     *
     * @return number of rows
     *
     * @pre
     * MIN_ROW <= [number of rows] <= MAX_ROW
     *
     * @post
     * getNumRows() = [number of rows]
     *
     */
    public int getNumRows() {
        return numRow;
    }

    /**
     * Method to get and return the number of columns in GameBoard.
     *
     * @return number of columns
     *
     * @pre
     * MIN_COL <= [number of columns] <= MAX_COL
     *
     * @post
     * getNumColumns() = [number of columns]
     */
    public int getNumColumns() {
        return numCol;
    }

    /**
     * Method to get and return the number of tokens in a row needed to win the game.
     *
     * @return number of tokens in a row needed to win the game
     *
     * @pre
     * MIN_COUNT <= [number to win] <= MAX_COUNT
     *
     * @post
     * getNumToWin() = [number to win]
     */
    public int getNumToWin() {
        return numToWin;
    }
}
