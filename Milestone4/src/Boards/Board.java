package Boards;

import java.util.ArrayList;
import common.Move;
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
 * 
 * Milestone 4:
 *  -Undo/redo is only allowed if a human player is facing off against some type of robot. Allowing Human v. Human undo would ruin the point of the game.
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

    //Stacks for undo/redo
    private static ArrayList<Board> undoStack = new ArrayList<Board>();
    private static ArrayList<Board> redoStack = new ArrayList<Board>();

    /**
     * Define a Generic Board
     *
     * @param DIMENSIONS    the amount of Dimensions of this board
     */
    public Board(final int DIMENSIONS) {
        this.DIMENSIONS = DIMENSIONS;
        board = new PLAYER[DIMENSIONS][DIMENSIONS];
        for (int row = 0; row < DIMENSIONS; row++)
            for (int col = 0; col < DIMENSIONS; col++)
                board[row][col] = PLAYER.EMPTY;     // all cells empty
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
     * @param move the co-ordinates
     * @return contents of cell
     */
    public PLAYER getCell(Move move) {
        return board[move.getRow()][move.getColumn()];
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
    public abstract ArrayList<Move> getPossibleMoves();
    
    /**
     * Count all spaces currently occupied by a specific player
     * @param somePlayer the player
     * @return some amount of spaces occupied
     */
    public int countSpaces(PLAYER somePlayer){
        int spaces = 0;
        for (int currentRow = 0; currentRow < DIMENSIONS; currentRow++) {
            for (int currentColumn = 0; currentColumn < DIMENSIONS; currentColumn++) {
                if (getCell( new Move(currentRow, currentColumn) ) == somePlayer) {
                    spaces++;
                }
            }
        }
        return spaces;
    }
    
    
    /**
     * Will scan the current board and find all of the current empty places
     *
     * @return a list of all empty spaces on the board
     */
    protected ArrayList<Move> getEmptySpaces() {
        ArrayList<Move> emptySpaces = new ArrayList<Move>();
        PLAYER[][] board = getBoard();
        for (int currentRow = 0; currentRow < DIMENSIONS; currentRow++) {
            for (int currentColumn = 0; currentColumn < DIMENSIONS; currentColumn++) {
                if (board[currentRow][currentColumn] == PLAYER.EMPTY) {
                    Move ret = new Move(currentRow, currentColumn);
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
        PLAYER[][] cloneBoard = new PLAYER[DIMENSIONS][DIMENSIONS];
        for(int row = 0; row<DIMENSIONS; row++){
            for(int column = 0; column<DIMENSIONS; column++){
                cloneBoard[row][column] = board[row][column];
            }
        }
        clonedBoard.setBoard(cloneBoard);
        clonedBoard.setcurrentPlayer(this.getCurrentPlayer());
        clonedBoard.setCurrentState(this.getCurrentState());
        clonedBoard.setPlayerTiles(chip1,chip2);
        clonedBoard.setScores( this.getScores() );
        return clonedBoard;
    }

    /**
     * Get a carbon copy of the subclass's board
     * @return  the sub classes board
     */
    public abstract Board getClone();


    /**
     * Get the last saved state of the game off the undo stack
     * @return a saved state (last time a human user was allowed to move)
     */
    public static Board popUndo(PLAYER CurrentPlayer){
        //make sure we are pulling the current player's state
        if (undoStack.size() > 1) {
            while (undoStack.size() > 1 && undoStack.get(1).getCurrentPlayer() != CurrentPlayer)
                undoStack.remove(1);
            return undoStack.remove(1);
        }
        return null;
    }

    /**
     * Get the last saved state of the game off the redo stack
     * @return a saved state (last time a human user assked to undo a move)
     */
    public static Board popRedo(){
        if (redoStack.size() > 0)
            return redoStack.remove(0);
        return null;
    }

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
        this.board = originalBoard;
    }
    /**
     * Set an individual cell to a value
     *
     * @param newPiece the value to set
     * @param move which is being set to the new player
     */
    public void setCell(PLAYER newPiece, Move move) {
        board[move.getRow()][move.getColumn()] = newPiece;
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
     * Switch the current player with it's enemy
     */
    public void switchcurrentPlayer(){
        this.currentPlayer = getEnemy();
    }

    /**
     * Will store one board state in the static undo Stack
     * @param board - some board state
     */
    public static void storeUndo (Board board){
        undoStack.add(0,board.getClone());
    }
    /**
     * Will store one board state in the static undo Stack
     * @param board - some board state
     */
    public static void storeRedo (Board board){
        redoStack.add(0,board.getClone());
    }

    /**
     * Dump the undo stack
     */
    public static void dumpUndoStack (){
        undoStack = new ArrayList<Board>();
    }

    /**
     * Dump all entries into the redo stack, must be completed every turn.
     */
    public static void dumpRedoStack (){
        redoStack = new ArrayList<Board>();
    }

    /**
     * ***************************************************************************************************************************************************************
     * Methods to Make PlayerTypes
     * ***************************************************************************************************************************************************************
     */
    /**
     * Game will attempt a move, non generic MUST EXIST
     * @param move: a move pointing to an instance of an empty cell
     * @return if the move was made
     */
    public abstract boolean attemptMove(Move move);

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
     * @param move    the move in question
     * @return if the move is allowed
     */
    public boolean isWithinBounds(Move move) {
        if (move.getRow() >= 0 && move.getColumn() >= 0)
            if (move.getRow() < DIMENSIONS && move.getColumn() < DIMENSIONS)
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
            case EMPTY:
            	return PLAYER.EMPTY;
        }
        return PLAYER.EMPTY;
    }

    /**
     * Find all adjacent enemies to a particular empty space
     * @param originalMove    move object pointing to the empty space
     * @return a list of the enemies around an empty space
     */
    public ArrayList<Move> findadjacentEnemies(Move originalMove) {
        ArrayList<Move> adjacentEnemies = new ArrayList<Move>();
        PLAYER enemy = getEnemy();
        for (int currentRow = originalMove.getRow() - 1; currentRow <= (originalMove.getRow() + 1); currentRow++)                    //span the spaces connected to the move location
            for (int currentColumn = originalMove.getColumn() - 1; currentColumn <= (originalMove.getColumn() + 1); currentColumn++) {
                Move currentMove = new Move(currentRow,currentColumn);
                if (isWithinBounds(currentMove)) {                              //if it is not the center location and that it's not off the board
                    if (getCell(currentMove) == enemy) {                      //if this spot has the potential of being surrounded
                        adjacentEnemies.add(currentMove);
                    }
                }
            }
        return adjacentEnemies;
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