package Boards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

import Boards.Board.PLAYER;


/**
 * *********************************************************************************************************************************************************
 * 									OthelloBoardTest 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test the methods in the OthelloBoard Class 
 * 
 */

public class OthelloBoardTEST {

    private Board board;

    @Before
    public void setUp() {
        board = new OthelloBoard();
    }

    @Test
    public void testProperties() {
        assertEquals(4, board.ROWS());
        assertEquals(4, board.COLUMNS());
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(1, 2));
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(2, 1));
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(1, 1));
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(2, 2));
    }

    @Test
    public void testGetPossibleMoves() {
        // test with: current player 1 -> next move for player 2
        ArrayList<Integer[]> possibleMoves = board.getPossibleMoves();
        // there are 4 possible ways for player 2
        assertEquals(4, possibleMoves.size());
        boolean isTestFalse = false;
        for (Integer[] moves : possibleMoves) {
            if (moves[0] != 0 && moves[1] != 1 || moves[0] != 1 && moves[1] != 0 || moves[0] != 2 && moves[1] != 3 || moves[0] != 3
                    && moves[1] != 2) {
                isTestFalse = true;
            }
        }
        assertTrue(isTestFalse);

        // current player is PLAYER 2
        board.setcurrentPlayer(Board.PLAYER.PLAYER2);
        // player 2 move to [1,0] and [2,0]
        board.setCell(Board.PLAYER.PLAYER2, 1, 0);
        board.setCell(Board.PLAYER.PLAYER2, 2, 0);
        // next move return to player 1
        possibleMoves = board.getPossibleMoves();
        isTestFalse = false;
        // there are 3 possible ways for PLAYER 1
        for (Integer[] moves : possibleMoves) {
            if (moves[0] != 0 && moves[1] != 2 || moves[0] != 1 && moves[1] != 3 || moves[0] != 3 && moves[1] != 1) {
                isTestFalse = true;
            }
        }
        assertTrue(isTestFalse);
    }

    @Test
    public void testAttemptMove() {
        // test negative case when current player is EMPTY
        board.setcurrentPlayer(PLAYER.EMPTY);
        assertFalse(board.attemptMove(0, 0));
        assertFalse(board.attemptMove(1, 0));
        assertFalse(board.attemptMove(3, 1));
        
        //test attempt move return false because player move to wrong tile
        //in this test, we assume PLAYER 1 and PLAYER 2 has been played like
        /*
         * 
         *      |   |   |  
         *   ----------------
         *      | W | B |  
         *   ----------------
         *      | W | B |  
         *   ----------------
         *      |   |   |  
         * 
         */
        board.setcurrentPlayer(PLAYER.PLAYER1);
        assertFalse(board.attemptMove(3, 1));
        assertFalse(board.attemptMove(0, 0));
        assertFalse(board.attemptMove(3, 0));
        assertFalse(board.attemptMove(1, 2));
        
        //test attempt move return true and move until there are no possible way to move
        //in this test, we assume PLAYER 1 and PLAYER 2 has been played like
        /*
         * 
         *    W |   | W |  
         *   -----------------
         *    W | W | B |  W
         *   -----------------
         *      | W | B |  
         *   -----------------
         *      |   |   |  
         * 
         */
        board.setCell(Board.PLAYER.PLAYER1, 0, 0);
        board.setCell(Board.PLAYER.PLAYER1, 0, 2);
        board.setCell(Board.PLAYER.PLAYER1, 1, 0);
        board.setCell(Board.PLAYER.PLAYER1, 1, 3);
        assertTrue(board.attemptMove(0, 1));
        assertTrue(board.attemptMove(2, 3));
        assertFalse(board.attemptMove(3, 2));
        assertFalse(board.attemptMove(1, 3));
    }
    
    @Test
    public void testHasBeenWon() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException  {
    	//in this test, we assume PLAYER 1 and PLAYER 2 has been played like
        /*
         * 
         *    W | W | B | W 
         *   ----------------
         *    W | B | W | W 
         *   ----------------
         *    W | W | W | W 
         *   ----------------
         *    W | W | W | W 
         * 
         */
         // PLAYER 1 is 'B', PLAYER 2 is 'W'
        //set value to all tiles
        board.setCell(PLAYER.PLAYER1, 0, 2);
        board.setCell(PLAYER.PLAYER2, 0, 0);
        board.setCell(PLAYER.PLAYER2, 0, 1);
        board.setCell(PLAYER.PLAYER2, 0, 2);
        board.setCell(PLAYER.PLAYER2, 0, 3);
        board.setCell(PLAYER.PLAYER2, 0, 1);
        board.setCell(PLAYER.PLAYER2, 2, 1);
        board.setCell(PLAYER.PLAYER2, 3, 1);
        board.setCell(PLAYER.PLAYER2, 3, 2);
        board.setCell(PLAYER.PLAYER2, 0, 3);
        board.setCell(PLAYER.PLAYER2, 1, 3);
        board.setCell(PLAYER.PLAYER2, 2, 3);
        board.setCell(PLAYER.PLAYER2, 3, 3);
        
      
      //use java reflection to set variables to set end game (to access private fields) 
        Field blackDiscsField = OthelloBoard.class.getDeclaredField("blackDiscs");
        blackDiscsField.setAccessible(true); //making private variable accessible using reflection
        blackDiscsField.setInt(board, 1); //setting the value of the field with this method
        Field whiteDiscsField = OthelloBoard.class.getDeclaredField("whiteDiscs");
        whiteDiscsField.setAccessible(true);
        whiteDiscsField.setInt(board, 0);
        Field blackMovedField = OthelloBoard.class.getDeclaredField("blackMoved");
        blackMovedField.setAccessible(true);
        blackMovedField.setBoolean(board, false);
        Field whiteMovedField = OthelloBoard.class.getDeclaredField("whiteMoved");
        whiteMovedField.setAccessible(true);
        whiteMovedField.setBoolean(board, true);
        
        //end game
        PLAYER playerHasBeenWon = board.hasBeenWon(); //making a PLAYER that is calling hasBeenWon to compare with PLAYER2 
        assertEquals(PLAYER.PLAYER2, playerHasBeenWon);
    
    }
}
