package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import common.Move;

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
 * Edited by: Lina El Sadek
 * for Milestone 3
 */

public class OthelloBoardTest {

    private Board board;
    private Move move;

    //Setting up the Othello Board for testing 
    @Before
    public void setUp() {
        board = new OthelloBoard();
        move = new Move();
    }

  //test if the board has a proper dimensions and palyers by making some moves and check the value of the move 
    @Test
    public void testProperties() {
        assertEquals(4, board.DIMENSIONS);
        move.setColumn(2);
        move.setRow(1);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
        move.setColumn(1);
        move.setRow(2);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
        move.setColumn(1);
        move.setRow(1);
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(move));
        move.setColumn(2);
        move.setRow(2);
        assertEquals(Board.PLAYER.PLAYER2, board.getCell(move));
        
        //Error handling 
        try{
    		move.setColumn(9);
    		move.setRow(9);
    		board.setCell(Board.PLAYER.PLAYER1, move);
    		fail("Out of bound index, cannot place cell");
    }catch (IndexOutOfBoundsException e)
    {
    	assertEquals("9", e.getMessage());
    	
    }
    }

    @Test
    public void testattemptMove() {
        // test negative case when current player is EMPTY
        board.setcurrentPlayer(PLAYER.EMPTY);
        move.setColumn(0);
        move.setRow(0);
        assertFalse(board.attemptMove(move));
        move.setColumn(0);
        move.setRow(1);
        assertFalse(board.attemptMove(move));
        move.setColumn(1);
        move.setRow(3);
        assertFalse(board.attemptMove(move));
        
    
       //Test if out of bound is prohibited
        try
        {
        	move.setColumn(9);
        	move.setRow(9);
        	assertFalse(board.attemptMove(move));
        }
        catch (IndexOutOfBoundsException e)
        {
        	assertEquals("9", e.getMessage());
        	
        }
        
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
        move.setColumn(1);
        move.setRow(3);
        assertFalse(board.attemptMove(move));
        move.setColumn(0);
        move.setRow(0);
        assertFalse(board.attemptMove(move));
        move.setColumn(0);
        move.setRow(3);
        assertFalse(board.attemptMove(move));
        move.setColumn(2);
        move.setRow(1);
        assertFalse(board.attemptMove(move));
        
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
        
        move.setColumn(0);
        move.setRow(0);
        board.setCell(Board.PLAYER.PLAYER1, move);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
        
        move.setColumn(2);
        move.setRow(0);
        board.setCell(Board.PLAYER.PLAYER1, move);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
        
        move.setColumn(0);
        move.setRow(1);
        board.setCell(Board.PLAYER.PLAYER1, move);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
        
        move.setColumn(3);
        move.setRow(1);
        board.setCell(Board.PLAYER.PLAYER1, move);
        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
       
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
    	
    	move.setColumn(2);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER1, move);
        move.setColumn(0);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(1);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(2);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(3);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(1);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(1);
        move.setRow(2);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(1);
        move.setRow(3);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(2);
        move.setRow(3);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(3);
        move.setRow(0);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(3);
        move.setRow(1);
        board.setCell(PLAYER.PLAYER2, move);
        move.setColumn(3);
        move.setRow(2);
        board.setCell(PLAYER.PLAYER2, move);
        //Test if out of bound is prohibited
        try
        {
        	move.setColumn(9);
        	move.setRow(9);
        	 board.setCell(PLAYER.PLAYER1, move);
        	 fail("Index out of bound");
        }
        catch (IndexOutOfBoundsException e)
        {
        	assertEquals("9", e.getMessage());
        	
        }
        move.setColumn(3);
        move.setRow(3);
        board.setCell(PLAYER.PLAYER2, move);
        
        //end game
        board.updateGame();
        board.printBoard();
        assertTrue(board.getCurrentState() == GAME_STATE.PLAYING);
        
        
    
    }
}
