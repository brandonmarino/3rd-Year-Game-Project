package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

import Boards.Board;
import Boards.Board.GAME_STATE;
import Boards.Board.PLAYER;
import Boards.OthelloBoard;


/**
 * *********************************************************************************************************************************************************
 * 									OthelloBoardTest 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test the methods in the OthelloBoard Class 
 * 
 */

public class OthelloBoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new OthelloBoard();
    }

    @Test
    public void testProperties() {
        assertEquals(4, board.DIMENSIONS);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(1, 2));
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(2, 1));
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(1, 1));
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(2, 2));
    }

    @Test
    public void testattemptMove() {
        // test negative case when current player is EMPTY
        board.setcurrentPlayer(PLAYER.EMPTY);
        assertFalse(board.attemptMove(0, 0));
        assertFalse(board.attemptMove(1, 0));
        assertFalse(board.attemptMove(3, 1));
        
        //test attempt Integer[] return false because player Integer[] to wrong tile
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
        
        //test attempt Integer[] return true and Integer[] until there are no possible way to Integer[]
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
        
        //end game
        board.updateGame();
        board.printBoard();
        assertTrue(board.getCurrentState() == GAME_STATE.PLAYING);
        
        
    
    }
}
