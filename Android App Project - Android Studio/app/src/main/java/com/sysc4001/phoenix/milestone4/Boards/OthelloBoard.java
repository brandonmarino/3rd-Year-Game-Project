package com.sysc4001.phoenix.milestone4.Boards;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.sysc4001.phoenix.milestone4.OthelloActivity;
import com.sysc4001.phoenix.milestone4.R;
import com.sysc4001.phoenix.milestone4.common.Move;
/**
 * *********************************************************************************************************************************************************
 * Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Othello
 * ***********************************************************************************************************************************************************
 *
 * * Adapted from Board source of TIC TAC TOE Authored by Lina
 * Milestone 1:
 * Adapting Author: Brandon Marino
 * Much of the code is similar however i've changed the method to use implicit enum values opposed to constants which were set to an integer value
 * Also changed the intended game to Othello, not tic tac toe
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Board
 * -removed redundant code
 * -fixed glitches
 * -cleaned up some code
 */
public class OthelloBoard extends Board {
    private boolean blackMoved; //pay attention to if the black player made a move on their last attempt
    private boolean whiteMoved; // pay attention to if the white player made a move on their last attempt

    /**
     * Define the parameters of the othello board
     */
    public OthelloBoard() {
        super(4);    //ask for a 4x4 board
        setPlayerTiles('B', 'W');
        setCell( PLAYER.PLAYER1, new Move(DIMENSIONS / 2 - 1, DIMENSIONS / 2) );
        setCell( PLAYER.PLAYER1, new Move(DIMENSIONS / 2, DIMENSIONS / 2 - 1) );
        setCell( PLAYER.PLAYER2, new Move(DIMENSIONS / 2 - 1, DIMENSIONS / 2 - 1) );    //place 4 tiles in middle of board
        setCell( PLAYER.PLAYER2, new Move(DIMENSIONS / 2, DIMENSIONS / 2) );
        scores[0] = DIMENSIONS * DIMENSIONS / 2 - 2;
        scores[1] = DIMENSIONS * DIMENSIONS / 2 - 2;
        blackMoved = true;
        whiteMoved = true;
    }

    /*****************************************************************************************************************************************************************
     *             Getter Methods for variables and constants defined above
     * ****************************************************************************************************************************************************************/

    /**
     * get moved state of black player (Player 1)
     * @return moved state
     */
    private boolean getBlackMoved(){
        return blackMoved;
    }

    /**
     * get moved state of white player (Player w)
     * @return moved state
     */
    private boolean getWhiteMoved(){
        return whiteMoved;
    }

    /**
     * Return all legal moves that this player could make on this othello board
     * @return a list of possible moves
     */
    public ArrayList<Move> getPossibleMoves(){
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        boolean isLegal = false;
        for (Move possibleMove: getEmptySpaces()){
            ArrayList<Move> adjEnemies = findadjacentEnemies(possibleMove);
            isLegal = false;
            for (Move ret : adjEnemies){
                Move slopeMove = new Move( ret.getRow() - possibleMove.getRow(), ret.getColumn() - possibleMove.getColumn() );
                if (canFlank(possibleMove, slopeMove) > 0){
                    isLegal = true;
                }

            }
            if (isLegal)
                legalMoves.add(possibleMove);
        }
        return legalMoves;
    }

    /**
     * Get the current player's disc count
     * @return the number of discs the current user has
     */
    private int getPlayerDiscs(){
        switch (getCurrentPlayer()) {
            case PLAYER1:
                return scores[0];
            case PLAYER2:
                return scores[1];
        }
        return 0;
    }

    /**
     * Create and populate a cloned of the othello board with the current board's values
     * @return a cloned othello
     */
    public Board getClone() {
        OthelloBoard clonedBoard = new OthelloBoard();
        clonedBoard = (OthelloBoard)super.getClone(clonedBoard);
        clonedBoard.setBlackMoved( this.getBlackMoved() );
        clonedBoard.setWhiteMoved( this.getWhiteMoved() );
        return clonedBoard;
    }

    /*****************************************************************************************************************************************************************
     *             Setter Methods for variables and constants defined above
     *****************************************************************************************************************************************************************/

    public void setMoved(Boolean set){
        switch(getCurrentPlayer()){
            case PLAYER1:
                blackMoved = set;
                break;
            case PLAYER2:
                whiteMoved = set;
                break;
        }
    }

    protected void setBlackMoved(boolean set){
        blackMoved = set;
    }

    protected void setWhiteMoved(boolean set){
        whiteMoved = set;
    }

    /*****************************************************************************************************************************************************************
     *              Functions to print Othello Board
     *****************************************************************************************************************************************************************/
    /**
     * Print Othello Board
     */
    public void printBoard(Activity act, String Player1Name, String Player2Name) {
        ImageView Box = (ImageView)act.findViewById(R.id.b0x0);
        int image = R.drawable.empty_small;

        for (int row = 0; row < DIMENSIONS; row++) {
            for (int column = 0; column < DIMENSIONS; column++) {
                //also bad, also dont care
                if (row == 0 && column == 0)
                    Box = (ImageView) act.findViewById(R.id.b0x0);
                else if (row == 0 && column == 1)
                    Box = (ImageView) act.findViewById(R.id.b0x1);
                else if (row == 0 && column == 2)
                    Box = (ImageView) act.findViewById(R.id.b0x2);
                else if (row == 0 && column == 3)
                    Box = (ImageView) act.findViewById(R.id.b0x3);
                else if (row == 1 && column == 0)
                    Box = (ImageView) act.findViewById(R.id.b1x0);
                else if (row == 1 && column == 1)
                    Box = (ImageView) act.findViewById(R.id.b1x1);
                else if (row == 1 && column == 2)
                    Box = (ImageView) act.findViewById(R.id.b1x2);
                else if (row == 1 && column == 3)
                    Box = (ImageView) act.findViewById(R.id.b1x3);
                else if (row == 2 && column == 0)
                    Box = (ImageView) act.findViewById(R.id.b2x0);
                else if (row == 2 && column == 1)
                    Box = (ImageView) act.findViewById(R.id.b2x1);
                else if (row == 2 && column == 2)
                    Box = (ImageView) act.findViewById(R.id.b2x2);
                else if (row == 2 && column == 3)
                    Box = (ImageView) act.findViewById(R.id.b2x3);
                else if (row == 3 && column == 0)
                    Box = (ImageView) act.findViewById(R.id.b3x0);
                else if (row == 3 && column == 1)
                    Box = (ImageView) act.findViewById(R.id.b3x1);
                else if (row == 3 && column == 2)
                    Box = (ImageView) act.findViewById(R.id.b3x2);
                else if (row == 3 && column == 3)
                    Box = (ImageView) act.findViewById(R.id.b3x3);

                switch (getBoard()[row][column]) {
                    case PLAYER1:
                        image = R.drawable.player1_small;
                        break;
                    case PLAYER2:
                        image = R.drawable.player2_small;
                        break;
                }
                Box.setImageResource(image);
            }
        }
        String output = "";
        if (getCurrentPlayer() == PLAYER.EMPTY)
            output = "The game has been won!";
        else
            output = "Disks Left: "+getPlayerDiscs();

        TextView discs = (TextView)act.findViewById(R.id.available_disks);
        discs.setText(output);

        TextView curPlayer =(TextView)act.findViewById(R.id.current_player);
        if (getCurrentState() != GAME_STATE.PLAYING) {
            if (getCurrentState() != GAME_STATE.WAITING) {
                if (getCurrentState() == GAME_STATE.PLAYER1_WON)
                    output = Player1Name + " Won!";
                else if (getCurrentState() == GAME_STATE.PLAYER2_WON)
                    output = Player2Name + " Won!";
                else if (getCurrentState() == GAME_STATE.DRAW)
                    output = "It was a Draw!";
                Button replay = (Button)act.findViewById(R.id.but_replay);
                replay.setVisibility(View.VISIBLE);
            }
        }else{
            if (getCurrentPlayer() == PLAYER.PLAYER1)
                output = "Current Player: "+Player1Name;
            else if (getCurrentPlayer() == PLAYER.PLAYER2)
                output = "Current Player: "+Player2Name;
        }
        curPlayer.setText(output);

    }

    /*****************************************************************************************************************************************************************
     *              Conditional Methods to test for Winning Conditions or Draw
     *****************************************************************************************************************************************************************/

    /**
     * Find if the game has been one by a player
     * @return either the winning player or null if no one has won
     */
    public PLAYER hasBeenWon() {
        if ((scores[0] <= 0 || !blackMoved) && (scores[1] <= 0 || !whiteMoved))      //both players cannot move anymore
            return countWinner();
        return null;        //equivalent to returning false
    }

    /**
     * Method to count the tiles on the board once either both players cannot make a move or the board is full
     * Players with the most tiles facing their colour wins the game
     * @Return Enum representation of the Players
     */
    private PLAYER countWinner() {
        int blackSpaces = countSpaces(PLAYER.PLAYER1);
        int whiteSpaces = countSpaces(PLAYER.PLAYER2);

        if (blackSpaces > whiteSpaces)
            return PLAYER.PLAYER1;
        else if (whiteSpaces > blackSpaces)
            return PLAYER.PLAYER2;
        else
            return PLAYER.EMPTY;
    }

    /*****************************************************************************************************************************************************************
     *              Methods to Make PlayerTypes
     *****************************************************************************************************************************************************************/
    /**
     * Attempt a move in Othello
     * To make a move in othello, an empty space must be found that can be used to 'flank' the other team's players
     * @param move pointing to empty cell
     * @return
     */
    public boolean attemptMove(Move move){
        if (getPlayerDiscs() <=0)
            return false;
        ArrayList<Move> adjEnemies = findadjacentEnemies(move);
        boolean madeMove = false;
        for (Move ret : adjEnemies){       //for all adjacent enemies
            Move slope = new Move (ret.getRow() - move.getRow(), ret.getColumn() - move.getColumn());
            int chainLength = canFlank(move, slope);
            if (chainLength != 0){
                madeMove = true;                //if any chain is found, this move was made
                flankChain(chainLength, move, slope);
            }
        }
        if (madeMove)
            decrementDiscs();   //remove a disc from player's pile
        return madeMove;    //if a move was successfully made, it will be reported
    }

    /**
     * Flip a chain chain of enemy pieces and place your own in the empty space
     *
     * @param originalMove the original Move attempted
     * @param slope       the offsets of the adjacent enemy to the empty space
     */
    private void flankChain(int length, Move originalMove, Move slope) {
        Move currentMove = new Move (0,0);
        for (int slopeIncrement = 0; slopeIncrement < length; slopeIncrement++) {  //traverse the chain
            currentMove.setRow( originalMove.getRow() + (slope.getRow() * slopeIncrement) );          //find next item
            currentMove.setColumn( originalMove.getColumn() + (slope.getColumn() * slopeIncrement) ); //find next item
            setCell(getCurrentPlayer(), currentMove);
        }
    }

    /**
     * Find if a chain can be made out of an empty space, adjacent enemy and then a possible chain of enemies leading
     * to a space owned by the current player
     *
     * @param originalMove the original Move attempted
     * @param slope       the offsets of the adjacent enemy to the empty space
     * @return the length of the chain, null if not chain-able
     */
    public int canFlank(Move originalMove, Move slope) {
        int currentRow;  //first element in the chain return null
        int currentColumn;
        Move currentMove = new Move();
        for (int incrementChain = 1; incrementChain < DIMENSIONS*2; incrementChain++) {
            currentMove.setRow(originalMove.getRow() + (slope.getRow() * incrementChain));          //find next item
            currentMove.setColumn( originalMove.getColumn() + (slope.getColumn() * incrementChain) ); //find next item

            if (isWithinBounds(currentMove))
                if (getCell(currentMove) == PLAYER.EMPTY || !isWithinBounds(currentMove))
                    return 0;
                 else if (getCell(currentMove) == getCurrentPlayer())  //found a flank-able trail
                    return incrementChain;
        }
        return 0;
    }

    /********************************************************************************
     * 				Internal functions for use of Othello Logic
     ********************************************************************************
    /**
     * lower the current player's disk count by 1
     */
    private void decrementDiscs() {
        if (getCurrentPlayer() == PLAYER.PLAYER1)
            scores[0]--;
        else if (getCurrentPlayer() == PLAYER.PLAYER2)
            scores[1]--;
    }
}