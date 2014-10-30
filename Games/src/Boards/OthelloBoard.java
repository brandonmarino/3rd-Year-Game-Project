package Boards;

import java.util.ArrayList;

/**
 * *********************************************************************************************************************************************************
 * Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Othello
 * ***********************************************************************************************************************************************************
 * <p/>
 * * Adapted from Board source of TIC TAC TOE Authored by Lina
 * Milestone 1, Adapting Author: Brandon Marino
 * <p/>
 * Much of the code is similar however i've changed the method to use implicit enum values opposed to constants which were set to an integer value
 * Also changed the intended game to Othello, not tic tac toe
 */
public class OthelloBoard extends Board {
    private int blackdiscCount;
    private int whitediscCount;

    /**
     * Define the parameters of the othello board
     */
    public OthelloBoard() {
        super(4, 4);    //ask for a 4x4 board
        setPlayerTiles('B', 'W');
        setCell(PLAYER.PLAYER1, ROWS() / 2 - 1, COLUMNS() / 2);
        setCell(PLAYER.PLAYER1, ROWS() / 2, COLUMNS() / 2 - 1);
        setCell(PLAYER.PLAYER2, ROWS() / 2 - 1, COLUMNS() / 2 - 1);    //place 4 tiles in middle of board
        setCell(PLAYER.PLAYER2, ROWS() / 2, COLUMNS() / 2);
        blackdiscCount = ROWS() * COLUMNS() / 2 - 2;
        whitediscCount = ROWS() * COLUMNS() / 2 - 2;
    }

    /*****************************************************************************************************************************************************************
     *             Getter Methods for variables and constants defined above
     * ****************************************************************************************************************************************************************/

    /**
     * Return all possible moves of the othello board
     * @return
     */
    public ArrayList<Integer[]> getPossibleMoves(){
        return getEmptySpaces();
    }

    /**
     * Will scan the current board and find all of the current empty places
     *
     * @return a list of all empty spaces on the board
     */
    private ArrayList<Integer[]> getEmptySpaces() {
        ArrayList<Integer[]> emptySpaces = new ArrayList<Integer[]>();
        for (int currentRow = 0; currentRow < ROWS(); currentRow++) {
            for (int currentColumn = 0; currentColumn < ROWS(); currentColumn++) {
                if (getBoard()[currentRow][currentColumn] == PLAYER.EMPTY) {
                    Integer[] ret = {currentRow, currentColumn};
                    emptySpaces.add(ret);
                }
            }
        }
        return emptySpaces;
    }

    private int getPlayerDiscCount(){
        switch (getcurrentPlayer()) {
            case PLAYER1:
                return blackdiscCount;
            case PLAYER2:
                return whitediscCount;
        }
        return 0;
    }

    /*****************************************************************************************************************************************************************
     *             Setter Methods for variables and constants defined above
     *****************************************************************************************************************************************************************/

    public void printBoard() {
        switch(getcurrentPlayer()){
            case PLAYER1:
                System.out.println("BLACK'S TURN");
                break;
            case PLAYER2:
                System.out.println("WHITE'S TURN");
        }
        super.printBoard();
        System.out.print("Black Discs Left: " + blackdiscCount + "\nWhite Discs Left: " + whitediscCount + "\n\n");
    }

    /*****************************************************************************************************************************************************************
     *              Conditional Methods to test for Winning Conditions or Draw
     *****************************************************************************************************************************************************************/
    public PLAYER hasBeenWon() {
        if (blackdiscCount <= 0 && whitediscCount <= 0)      //both players cannot move anymore
            return countWinner();
        return null;        //equivalent to returning false
    }

    /**
     * Method to count the tiles on the board once either both players cannot make a move or the board is full
     * Player with the most tiles facing their colour wins the game
     * <p/>
     * Returns: Enum representation of the Player
     */
    private PLAYER countWinner() {
        int blackSpaces = 0;
        int whiteSpaces = 0;
        for (int currentRow = 0; currentRow < ROWS(); currentRow++)
            for (int currentColumn = 0; currentColumn < ROWS(); currentColumn++)
                if (getCell(currentRow, currentColumn) == PLAYER.PLAYER1)
                    blackSpaces++;
                else if (getCell(currentRow, currentColumn) == PLAYER.PLAYER2)
                    whiteSpaces++;
        if (blackSpaces > whiteSpaces)
            return PLAYER.PLAYER1;
        else if (whiteSpaces > blackSpaces)
            return PLAYER.PLAYER2;
        else
            return PLAYER.EMPTY;
    }

    /*****************************************************************************************************************************************************************
     *              Methods to Make Moves
     *****************************************************************************************************************************************************************/

    public boolean attemptMove(int row, int column) {
        if (getPlayerDiscCount() <= 0){
            return false;
        }
        PLAYER thePlayer = getcurrentPlayer();
        PLAYER enemy = theEnemy(thePlayer);
        int flankedchainCount = 0;          //for counting all flanked chains of enemies
        if (userhasDiscs(thePlayer)) {
            ArrayList<Integer[]> adjacent = findadjacentEnemies(enemy, row, column);
            if (!adjacent.isEmpty())              //adjacent enemies were found
                for (Integer[] ret : adjacent)     //for all adjacent enemies
                    if (flipChain(enemy, row, column, ret[0] - row, ret[1] - column))
                        flankedchainCount++;        //may flip multiple chains, mark how many are flipped
        }
        if (flankedchainCount > 0) {
            decrementdiscCount(thePlayer);
            return true;
        }
        return false;             //return if one has been flipped
    }

    /**
     * Flip a chain chain of enemy pieces and place your own in the empty space
     *
     * @param enemy          enemy player
     * @param originalRow    the row of the original empty space
     * @param originalColumn the column of the original empyy space
     * @param slopeRow       the offset of the adjacent enemy to the empty space
     * @param slopeColumn    the offset of the adjacent enemy to the empty space
     * @return if the flip completed properly
     */
    private boolean flipChain(PLAYER enemy, int originalRow, int originalColumn, int slopeRow, int slopeColumn) {
        int currentRow;     //first element in the chain
        int currentColumn;
        Integer chainLength = isChain(enemy, originalRow, originalColumn, slopeRow, slopeColumn);
        if (chainLength != null) {
            //flip chain
            for (int slopeIncrement = 0; slopeIncrement < chainLength; slopeIncrement++) {  //traverse the chain
                currentRow = originalRow + (slopeRow * slopeIncrement);          //find next item
                currentColumn = originalColumn + (slopeColumn * slopeIncrement); //find next item
                setCell(getcurrentPlayer(), currentRow, currentColumn);
            }
            return true;
        }
        return false;   //this is not a chain
    }

    /**
     * Find if a chain can be made out of an empty space, adjacent enemy and then a possible chain of enemies leading
     * to a space owned by the current player
     *
     * @param enemy          the enemy player
     * @param originalRow    the row of the original empty space
     * @param originalColumn the column of the original empyy space
     * @param slopeRow       the offset of the adjacent enemy to the empty space
     * @param slopeColumn    the offset of the adjacent enemy to the empty space
     * @return the length of the chain, if not chain-able
     */
    private Integer isChain(PLAYER enemy, int originalRow, int originalColumn, int slopeRow, int slopeColumn) {
        int currentRow;  //first element in the chain return null
        int currentColumn;
        for (int incrementChain = 1; incrementChain < ROWS(); incrementChain++) {
            currentRow = originalRow + (slopeRow * incrementChain);          //find next item
            currentColumn = originalColumn + (slopeColumn * incrementChain); //find next item
            if (isWithinBounds(currentRow, currentColumn)) {
                if (getCell(currentRow, currentColumn) == PLAYER.EMPTY || !isWithinBounds(currentRow, currentColumn)) {
                    return null;
                } else if (getCell(currentRow, currentColumn) == getcurrentPlayer()) { //found a flank-able trail
                    return incrementChain;
                }
            }
        }
        return null;
    }

    /**
     * Find all adjacent enemies to a particular empty space
     * @param enemy          the enemy player
     * @param originalRow    the empty space row
     * @param originalColumn the empty space column
     * @return a list of the enemies around an empty space
     */
    private ArrayList<Integer[]> findadjacentEnemies(PLAYER enemy, int originalRow, int originalColumn) {
        ArrayList<Integer[]> adjacentEnemies = new ArrayList<Integer[]>();
        for (int currentRow = originalRow - 1; currentRow <= (originalRow + 1); currentRow++)                    //span the spaces connected to the move location
            for (int currentColumn = originalColumn - 1; currentColumn <= (originalColumn + 1); currentColumn++) {
                if (isWithinBounds(currentRow, currentColumn)) {                              //if it is not the center location and that it's not off the board
                    if (getCell(currentRow, currentColumn) == enemy) {                      //if this spot has the potential of being surrounded
                        Integer[] ret = {currentRow, currentColumn};
                        adjacentEnemies.add(ret);
                    }
                }
            }
        return adjacentEnemies;
    }

    /******************************************************************************************************************************************************************
     * 				Internal functions for use of Othello Logic
     *****************************************************************************************************************************************************************/

    /**
     * Find if a user has any disks left
     *
     * @param thePlayer the player in question
     * @return if the player has any disks left
     */
    private boolean userhasDiscs(PLAYER thePlayer) {
        switch (thePlayer) {
            case PLAYER1:
                if (blackdiscCount <= 0)
                    return false;
            case PLAYER2:
                if (whitediscCount <= 0)
                    return false;
        }
        return true;
    }

    /**
     * lower a player's disk count by 1
     *
     * @param thePlayer the player who's disk count is to decremented
     */
    private void decrementdiscCount(PLAYER thePlayer) {
        if (thePlayer == PLAYER.PLAYER1)
            blackdiscCount--;
        else if (thePlayer == PLAYER.PLAYER2)
            whitediscCount--;
    }
}