package Boards;

import java.util.ArrayList;

/**
 * *********************************************************************************************************************************************************
 * Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of a Generic Game
 * ***********************************************************************************************************************************************************
 *
 * Adapted from Board source of TIC TAC TOE Authored by Lina in Milestone 1
 *
 * Milestone 2:
 *  Adapting Author: Brandon Marino
 *  -Implemented Sub/Superclass inheritance model in order to merge TicTacToe and Othello and reduce code replication
 *  -This Class is of a Generic of a size provided by a programmer/the user and will provide all of the functions required of some generic board game
 */

public abstract class Board{
    //Fields for generic 2 player board game
    public enum GAME_STATE {PLAYING, DRAW, PLAYER1_WON, PLAYER2_WON}
    public enum PLAYER {EMPTY, PLAYER1, PLAYER2}

    private char chip1 = '1';
    private char chip2 = '2';
    protected int scores[] = new int[]{0,0};

    //Max row and columns, must be set be the generic game
    public final int DIMENSIONS;

    private PLAYER[][] board;
    private GAME_STATE currentState;
    private PLAYER currentPlayer;

    /**
     * Define a Generic Board
     *
     * @param DIMENSIONS    the amount of Dimensions of this board
     */
    public Board(final int DIMENSIONS) {
        this.DIMENSIONS = DIMENSIONS;
        board = new PLAYER[DIMENSIONS][DIMENSIONS];
        for (int row = 0; row < DIMENSIONS; row++)
        {
            for (int col = 0; col < DIMENSIONS; col++)
            {
                board[row][col] = PLAYER.EMPTY;     // all cells empty
            }
        }
        currentState = GAME_STATE.PLAYING; // ready to play
        currentPlayer = PLAYER.PLAYER1;    // player1  plays first
    }

    /******************************************************************************************************************************************************************
     * 												Getter Methods for variables and constants defined above
     * ****************************************************************************************************************************************************************/

    /**
     * Return the current board to the player
     *
     * @return a board
     */
    public PLAYER[][] getBoard() {
        return board;
    }

    /**
     * Returns to external caller the person who's turn it is
     *
     * @return the current player
     */
    public PLAYER getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Give external caller the current state of the game
     *
     * @return the current Game state
     */
    public GAME_STATE getCurrentState() {
        return currentState;
    }

    /**
     * Get contents of some cell
     * @param row of cell
     * @param column of cell
     * @return contents of cell
     */
    public PLAYER getCell(int row, int column) {
        return board[row][column];
    }

    public int getDimensions(){
        return DIMENSIONS;
    }

    protected int[] getScores(){
        return new int[]{scores[0],scores[1]};
    }
    /**
     * Return all possible moves of the current board
     * @return return a list of all possible moves
     */
    public abstract ArrayList<Integer[]> getPossibleMoves();

    /**
     * Will scan the current board and find all of the current empty places
     *
     * @return a list of all empty spaces on the board
     */
    protected ArrayList<Integer[]> getEmptySpaces() {
        ArrayList<Integer[]> emptySpaces = new ArrayList<Integer[]>();
        for (int currentRow = 0; currentRow < DIMENSIONS; currentRow++) {
            for (int currentColumn = 0; currentColumn < DIMENSIONS; currentColumn++) {
                if (getBoard()[currentRow][currentColumn] == PLAYER.EMPTY) {
                    Integer[] ret = {currentRow, currentColumn};
                    emptySpaces.add(ret);
                }
            }
        }
        return emptySpaces;
    }

    /**
     * Extend cloning function to both internally handle exceptions and cast Board
     * @param clonedBoard provided by as a subclass of board
     * @return a cloned generic board
     */
    protected Board getClone(Board clonedBoard){
        clonedBoard.setBoard(this.getBoard());
        clonedBoard.setcurrentPlayer(this.getCurrentPlayer());
        clonedBoard.setCurrentState(this.getCurrentState());
        clonedBoard.setPlayerTiles(chip1,chip2);
        clonedBoard.setScores( this.getScores() );
        return clonedBoard;
    }

    public abstract Board getClone();

    /******************************************************************************************************************************************************************
     * 												Setter Methods for variables and constants defined above
     * ****************************************************************************************************************************************************************/

    /**
     * Set the currentPlayer
     *
     * @param newPlayer some player of the enum PLAYER
     */
    public void setcurrentPlayer(PLAYER newPlayer) {
        currentPlayer = newPlayer;
    }

    /**
     * Set the current game state
     * @param state the state to be set
     */
    public void setCurrentState(GAME_STATE state) {
        currentState = state;
    }

    protected void setScores ( int[] scores){
        this.scores[0] = scores[0];
        this.scores[1] = scores[1];
    }
    /**
     * Set the current board (to be used with clone)
     *
     */
    protected void setBoard( PLAYER[][] originalBoard) {
        PLAYER[][] cloneBoard = new PLAYER[DIMENSIONS][DIMENSIONS];
        for(int row = 0; row<DIMENSIONS; row++) {
            for (int column = 0; column < DIMENSIONS; column++) {
                cloneBoard[row][column] = originalBoard[row][column];
            }
        }
    }
    /**
     * Set an individual cell to a value
     *
     * @param newPiece the value to set
     * @param row      the row of the cell
     * @param column   the column of the cell
     */
    protected void setCell(PLAYER newPiece, int row, int column) {
        board[row][column] = newPiece;
    }

    /**
     * Set the player representations
     *
     * @param chip1 player 1 char representation
     * @param chip2 player 2 char representation
     */
    public void setPlayerTiles(char chip1, char chip2) {
        this.chip1 = chip1;
        this.chip2 = chip2;
    }

    /**
     * ***************************************************************************************************************************************************************
     * Methods to Make PlayerTypes
     * ***************************************************************************************************************************************************************
     */
    /**
     * Game will attempt a move, non generic MUST EXIST
     * @param row row of empty cell
     * @param column column of possible move
     * @return if the move was made
     */
    public abstract boolean attemptMove(int row, int column);

    /******************************************************************************************************************************************************************
     * 												Printing Methods for Board Game and Cells Within
     * ****************************************************************************************************************************************************************/

    /**
     * Will print the entire board of any square bound game given
     */
    public void printBoard() {
        for (int row = 0; row < DIMENSIONS; ++row) {
            for (int col = 0; col < DIMENSIONS; ++col) {
                printCell(board[row][col]); // print each of the cells
                if (col != DIMENSIONS - 1)
                    System.out.print("|");   // print vertical partition
            }
            System.out.println();
            if (row != DIMENSIONS - 1)
                for (int dashes = 0; dashes < 4 * DIMENSIONS; dashes++)
                    System.out.print("-"); // print horizontal partition
            System.out.print("\n");
        }
    }

    /**
     * Print one individual cell to the screen
     *
     * @param content player/empty space to be printed
     */
    private void printCell(PLAYER content) {
        if (content == PLAYER.EMPTY)
            System.out.print("   ");

        else if (content == PLAYER.PLAYER1)
            System.out.print(" " + chip1 + " ");            //Black players by a B

        else if (content == PLAYER.PLAYER2)
            System.out.print(" " + chip2 + " ");            //White player will be identified by a W

    }

    /******************************************************************************************************************************************************************
     * 												Internal functions for use of Othello Logic
     * ****************************************************************************************************************************************************************/

    /**
     * Check if either player has won, cannot be generalized but is required
     *
     * @return a player if somebody has won, else null
     */
    protected abstract PLAYER hasBeenWon();

    /**
     * Check if a current cell is within the bounds of the current board
     *
     * @param row    the row of the cell
     * @param column the column of the cell
     * @return if the move is allowed
     */
    public boolean isWithinBounds(int row, int column) {
        if (row >= 0 && column >= 0)
            if (row < DIMENSIONS && column < DIMENSIONS)
                return true;
        return false;
    }

    /**
     * Return the alternative player
     *
     * @return the opposite of the current player
     */
    protected PLAYER getEnemy() {
        switch (currentPlayer) {
            case PLAYER1:
                return PLAYER.PLAYER2;
            case PLAYER2:
                return PLAYER.PLAYER1;
        }
        return PLAYER.EMPTY;
    }

    /******************************************************************************************************************************************************************
     * 												Update Method To Change State of Game to Win or Draw
     *****************************************************************************************************************************************************************/

    /**
     * Update the game state if somebody has won
     */
    public void updateGame() {
        PLAYER winner = hasBeenWon();
        if (winner != null)
        {
            if (winner == PLAYER.PLAYER1)
                setCurrentState(GAME_STATE.PLAYER1_WON);
            else if (winner == PLAYER.PLAYER2)
                setCurrentState(GAME_STATE.PLAYER2_WON);
            else
                setCurrentState(GAME_STATE.DRAW);
        }
    }
}
