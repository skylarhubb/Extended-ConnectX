package cpsc2150.extendedConnectX.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class that contains the main function and controls the game's flow.
 *
 * @author Skylar Hubbarth
 * @version 3.0
 *
 */
public class GameScreen {

    public static void main(String[] args) {
        int round = 1;
        boolean gameOver = false;

        Scanner S = new Scanner(System.in);

        boolean playing = true;

        do {

            //Getting number of players.
            System.out.println("How many players?");
            int numPlayers = Integer.parseInt(S.nextLine());

            while(numPlayers < GameBoard.MIN_PLAYERS || numPlayers > GameBoard.MAX_PLAYERS) {
                if (numPlayers < GameBoard.MIN_PLAYERS) {
                    System.out.println("Must be at least 2 players");
                }
                else {
                    System.out.println("Must be 10 players or fewer");
                }

                System.out.println("How many players?");
                numPlayers = Integer.parseInt(S.nextLine());
            }

            //Getting the characters to represent each player.
            List<Character> playerChar = new ArrayList<>();

            for(int i = 1; i <= numPlayers; i++) {
                System.out.println("Enter the character to represent player " + i);

                String ch = S.nextLine();
                char player = ch.charAt(0);

                //check if the character is already being used by another player
                while(playerChar.contains(player)) {
                    System.out.println(player + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + i);

                    ch = S.nextLine();
                    player = ch.charAt(0);
                }
                player = Character.toUpperCase(player);
                playerChar.add(player);
            }

            //Getting the number of rows for the board.
            System.out.println("How many rows should be on the board?");

            int rows = Integer.parseInt(S.nextLine());

            while(rows < GameBoard.MIN_ROW || rows > GameBoard.MAX_ROW) {
                if(rows < GameBoard.MIN_ROW) {
                    System.out.println("Number of rows should not be less than " + GameBoard.MIN_ROW);
                }
                else {
                    System.out.println("Number of rows should not be more than " + GameBoard.MAX_ROW);
                }

                System.out.println("How many rows should be on the board?");

                rows = Integer.parseInt(S.nextLine());
            }

            //Getting the number of columns for the board.
            System.out.println("How many columns should be on the board?");

            int cols = Integer.parseInt(S.nextLine());

            while(cols < GameBoard.MIN_COL|| cols > GameBoard.MAX_COL) {
                if(cols < GameBoard.MIN_COL) {
                    System.out.println("Number of columns should not be less than " + GameBoard.MIN_COL);
                }
                else {
                    System.out.println("Number of columns should not be more than " + GameBoard.MAX_COL);
                }

                System.out.println("How many columns should be on the board?");

                cols = Integer.parseInt(S.nextLine());
            }

            //Getting the number to win for the board.
            System.out.println("How many in a row to win?");

            int numToWin = Integer.parseInt(S.nextLine());

            while(numToWin < GameBoard.MIN_COUNT || numToWin > GameBoard.MAX_COUNT) {
                if(numToWin < GameBoard.MIN_COUNT) {
                    System.out.println("Number in a row to win should not be less than " + GameBoard.MIN_COUNT);
                }
                else {
                    System.out.println("Number in a row to win should not be more than " + GameBoard.MAX_COUNT);
                }

                System.out.println("How many in a row to win?");

                numToWin = Integer.parseInt(S.nextLine());
            }

            //Deciding between fast or memory efficient game
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            String gameType = S.nextLine();
            char dec = gameType.charAt(0);

            while (dec != 'F' && dec != 'f' && dec != 'M' && dec != 'm') {
                System.out.println("Please enter F or M.");

                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameType = S.nextLine();
                dec = gameType.charAt(0);
            }

            //Creating the board
            IGameBoard gameBoard;

            //Initializing the board to the desired choice
            if(dec == 'f' || dec == 'F') {
                gameBoard = new GameBoard(rows, cols, numToWin);
            }
            else {
                gameBoard = new GameBoardMem(rows, cols, numToWin);
            }

            System.out.println(gameBoard.toString());


            //Let's play this game!
            int colChoice = -1;
            int whoseTurn = -1;
            do {
                whoseTurn++;

                System.out.println("Player " + playerChar.get(whoseTurn % numPlayers) + ", what column do you" +
                        " want to put your marker in?");

                colChoice = Integer.parseInt(S.nextLine());

                //Check that colChoice is valid
                while (0 > colChoice || colChoice >= gameBoard.getNumColumns() || !gameBoard.checkIfFree(colChoice)) {
                    if (0 > colChoice) {
                        System.out.println("Column cannot be less than 0.");
                    }
                    else if (colChoice >= gameBoard.getNumColumns()) {
                        System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                    }
                    else if (!gameBoard.checkIfFree(colChoice)) {
                        System.out.println("Column is full");
                    }

                    System.out.println("Player " + playerChar.get(whoseTurn % numPlayers) + ", what column do you" +
                            " want to put your marker in?");

                    colChoice = Integer.parseInt(S.nextLine());
                }

                gameBoard.placeToken(playerChar.get(whoseTurn % numPlayers), colChoice);
                System.out.println(gameBoard);

            }while(!gameBoard.checkForWin(colChoice) && !gameBoard.checkTie());

            if(gameBoard.checkForWin(colChoice)) {
                System.out.println("Player " + playerChar.get(whoseTurn % numPlayers) + " won!");
            }
            else if(gameBoard.checkTie()) {
                System.out.println("The game has ended in a tie!");
            }

            String choice = " ";

            while(!choice.equals("Y") && !choice.equals("y") && !choice.equals("N") && !choice.equals("n")) {
                System.out.println("Would you like to play again? Y/N");
                choice = S.nextLine();
            }

            if(choice.equals("N") || choice.equals("n")) {
                playing = false;
            }

        } while (playing);
    }

}
