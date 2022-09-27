package cpsc2150.extendedConnectX.models;

/**
 * An interface for GameBoard that contains default methods and method definitions that
 * will be implemented in GameBoard
 *
 * @author Skylar Hubbarth
 * @version 3.0
 *
 * @invariant
 * 0 <= row <= MAX_ROW
 * 0 <= col <= MAX_COL
 *
 */

public interface IGameBoard {

    public static final int MAX_COL = 100; //column
    public static final int MAX_ROW = 100; //row
    public static final int MAX_COUNT = 25; //number needed to win

    public static final int MIN_COL = 3;
    public static final int MIN_ROW = 3;
    public static final int MIN_COUNT = 3;

    public static final int MAX_PLAYERS = 10; //number of players
    public static final int MIN_PLAYERS = 2;

    //functions implemented in GameBoard - primary methods
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
    public void placeToken(char p, int c);

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
    public char whatsAtPos(BoardPosition pos);

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
    public int getNumRows();

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
    public int getNumColumns();

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
    public int getNumToWin();


    //default/secondary methods
    /**
     * Method to check if the column can accept another token.
     *
     * @return returns true if the column can accept another token, false otherwise
     *
     * @param c, column chosen
     *
     * @pre
     * 0 <= c <= getNumColumns()
     *
     * @post
     * checkIfFree == true iff [column can accept another token]
     * [else,] checkIfFree == false
     * c = #c
     *
     */
    public default boolean checkIfFree(int c) {
        BoardPosition temp = new BoardPosition(getNumRows()-1, c);
        return whatsAtPos(temp) == ' ';
    }

    /**
     * Method to check to see if the last token placed in column c resulted in the player
     * winning the game.
     *
     * @param c, column chosen
     *
     * @return returns true if the last token resulted in a win, otherwise false
     *
     * @pre 0 <= c <= getNumColumns()
     * [pos is the position of the most recent play]
     *
     * @post checkForWin == true iff (checkHorizWin == true OR checkVertiWin == true OR
     * checkDiagWin == true)
     * [else,] checkForWin == false
     * c = #c
     */
    public default boolean checkForWin(int c) {

        for(int x = 0; x < getNumRows(); x++) {
            BoardPosition pos = new BoardPosition(x, c);

            if(whatsAtPos(pos) != ' ') {
                if(checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos)) ||
                        checkDiagWin(pos, whatsAtPos(pos))) {
                    return true;
                }
            }
        }
        return false;

    }

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
    public default boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to check to see if the game has resulted in a tie. A game is tied if there are no
     * free board positions remaining.
     *
     * @return returns true if the game is tied, and false otherwise
     *
     * @pre
     * checkForWin != true
     *
     * @post
     * checkTie == true iff [Board[][] is full]
     * [else,] checkTie == false
     *
     */
    public default boolean checkTie() {
        BoardPosition temp;

        for(int y = 0; y < getNumColumns(); y++) {
            for(int x = 0; x < getNumRows(); x++) {
                temp = new BoardPosition(x, y);

                //check to see if there are any empty spaces in the board
                if(whatsAtPos(temp) == ' ') {
                    return false;
                }
            }
        }
        //if no empty spaces are found, board is full and game is tied
        return true;
    }

    /**
     * Method to see if the last token placed (which was placed in position pos by player p)
     * resulted in 5 in a row horizontally.
     *
     * @return returns true if the last token placed resulted in a 5 in a row horizontally,
     * otherwise false
     *
     * @param pos, the position [row][col]
     * @param p, the player
     *
     * @pre
     * [pos is valid]
     * pos.getRow < getNumRow() && pos.getRow > -1
     * pos.getCol < getNumColumn() && pos.getCol > -1
     * [p is valid]
     * 0 <= lastPos.getRow() < MAX_ROW_NUM
     * 0 <= lastPos.getColumn() < MAX_COLUMN_NUM
     * [lastPos was the location where the last marker was placed in]
     * [pos is a position on the latest play]
     *
     * @post
     * checkHorizWin == true iff [p placed at pos results in 5 in a row horizontally]
     * [else,] checkHorizWin == false
     * pos = #pos
     * p = #p
     *
     */
    public default boolean checkHorizWin(BoardPosition pos, char p) {
        boolean win = true;
        int width = pos.getRow();
        int height = pos.getColumn();

        int right = Math.min(getNumColumns() - 1, height + (getNumToWin() - 1));
        int left = Math.max(0, height - (getNumToWin() - 1));

        for(int x = left; x + getNumToWin() - 1 <= right; x++) {
            for(int y = 0; y < getNumToWin(); y++) {
                BoardPosition check = new BoardPosition(width, x + y);

                if (whatsAtPos(check) != p) {
                    win = false;
                }
            }

            if(win) {
                return true;
            }

            win = true;

        }

        return false;
    }

    /**
     * Method to see if the last token placed (which was placed in position pos by player p)
     * resulted in 5 in a row vertically. Returns true if it does, otherwise false.
     *
     * @return returns true if the last token placed resulted in a 5 in a row vertically,
     * otherwise false
     *
     * @param pos, the position [row][col]
     * @param p, the player
     *
     * @pre
     * [pos is valid]
     * pos.getRow < getNumRow() && pos.getRow > -1
     * pos.getCol < getNumColumn() && pos.getCol > -1
     * [p is valid]
     * 0 <= lastPos.getRow() < MAX_ROW_NUM
     * 0 <= lastPos.getColumn() < MAX_COLUMN_NUM
     * [lastPos was the location where the last marker was placed in]
     * [pos is a position on the latest play]
     *
     * @post
     * checkVertiWin == true iff [p placed at pos results in 5 in a row vertically]
     * [else,] checkVertiWin == false
     * pos = #pos
     * p = #p
     *
     */
    public default boolean checkVertWin(BoardPosition pos, char p) {
        boolean win = true;
        int width = pos.getRow();
        int height = pos.getColumn();

        if(width - (getNumToWin() - 1) > -1) {
            for(int x = 0; x < getNumToWin(); x++) {
                BoardPosition check = new BoardPosition(width - x, height);

                if(whatsAtPos(check) != p) {
                    win = false;
                }
            }
            if(win) {
                return true;
            }

            win = true;
        }

        return false;

    }

    /**
     * Method to see if the last token placed (which was placed in position pos by player p)
     * resulted in 5 in a row diagonally. Returns true if it does, otherwise false.
     *
     * @return returns true if the last token placed resulted in a 5 in a row diagonally,
     * otherwise false
     *
     * @param pos, the position [row][col]
     * @param p, the player
     *
     * @pre
     * [pos is valid]
     * pos.getRow < getNumRow() && pos.getRow > -1
     * pos.getCol < getNumColumn() && pos.getCol > -1
     * [p is valid]
     * 0 <= lastPos.getRow() < MAX_ROW_NUM
     * 0 <= lastPos.getColumn() < MAX_COLUMN_NUM
     * [lastPos was the location where the last marker was placed in]
     * [pos is a position on the latest play]
     *
     * @post
     * checkDiagWin == true iff [p placed at pos results in 5 in a row diagonally]
     * [else,] checkDiagWin == false
     * pos = #pos
     * p = #p
     *
     */
    public default boolean checkDiagWin(BoardPosition pos, char p) {
        boolean win = true;
        int width = pos.getRow();
        int height = pos.getColumn();
        int count = 1;

        //right to left from top
        for (int i = width + 1, j = height + 1; i < getNumRows() && j < getNumColumns(); i++, j++) {
            BoardPosition checkPos = new BoardPosition(i, j);
            char check = whatsAtPos(checkPos);

            if (check == p) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        //left to right from top
        for (int i = width - 1, j = height - 1; i >= 0 && j >= 0; i--, j--) {
            BoardPosition checkPos = new BoardPosition(i, j);
            char check = whatsAtPos(checkPos);

            if (check == p) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        count = 1;

        //left to right from top
        for (int i = width + 1, j = height - 1; i < getNumRows() && j >= 0; i++, j--) {
            BoardPosition checkPos = new BoardPosition(i, j);
            char check = whatsAtPos(checkPos);

            if (check == p) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        //left to right from bottom
        for (int i = width - 1, j = height + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            BoardPosition checkPos = new BoardPosition(i, j);
            char check = whatsAtPos(checkPos);

            if (check == p) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        return false;
    }

}



