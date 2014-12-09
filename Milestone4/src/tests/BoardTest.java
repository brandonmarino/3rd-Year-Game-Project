package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import common.Move;

import Boards.Board;
import Boards.Board.PLAYER;

/**
* *********************************************************************************************************************************************************
* 									BoardTest 
* ***********************************************************************************************************************************************************
*
* Author: Osama Buhamad 
* -test the Board Class 
* 
* Edited by: Lina El Sadek
* for Milestone 3
* 
*/

public class BoardTest {
	
	 private Board board;
	 private Move move;

	 //setting uo the Board for testing 
	 	@Before
	    public void setUp() {
		    board = new Board(4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return null;
	            }

	            @Override
	            public ArrayList<Move> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(Move move) {
	                return false;
	            }
	            
	            @Override
	            public Board getClone()
	            {
	            	return this;
	            }
	            
	        };
	        
	        move = new Move();
	 
	 }
	 
	 	//test if the board has a proper dimensions and palyers
	 	@Test
	    public void testProperties() {
	 		assertEquals(4, board.DIMENSIONS);
	        PLAYER[][] players = board.getBoard();
	        assertEquals(4, players.length);
	        for (PLAYER[] player : players) {
	            for (PLAYER player2 : player) {
	                assertEquals(Board.PLAYER.EMPTY, player2);
	            }
	        }
	        assertEquals(Board.GAME_STATE.PLAYING, board.getCurrentState());
	        assertEquals(Board.PLAYER.PLAYER1, board.getCurrentPlayer());
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 4; j++) {
	            	move.setColumn(j);
	            	move.setRow(i);
	                assertEquals(Board.PLAYER.EMPTY, board.getCell(move));
	            }
	        }
		 
	 }
	 

//	    @SuppressWarnings("deprecation")
//	    @Test
//	    public void testGetEmptySpaces() {
//	        ArrayList<Move> emptySpaces = board.getEmptySpaces();
//	        ArrayList<Move> expectedSpaces = new ArrayList<Move>();
//	        for (int i = 0; i < 4; i++) {
//	            for (int j = 0; j < 4; j++) {
//	                Move ret = new Move( i, j );
//	                expectedSpaces.add(ret);
//	            }
//	        }
//	        ArrayList<Move> actualValue = new ArrayList<Move>();
//	        for (int i = 0; i < emptySpaces.size(); i++) {
//	            
//	            actualValue.add(emptySpaces.get(i));
//	            assertEquals(i+1, actualValue.size());
//	            assertEquals(expectedSpaces.get(i), actualValue.get(i));
//	        }
//	    }

	 	// testing set/get methods in the board 
	    @Test
	    public void testGetSetMethod() {
	        // test set/get current player
	        board.setcurrentPlayer(Board.PLAYER.PLAYER2);
	        assertEquals(Board.PLAYER.PLAYER2, board.getCurrentPlayer());

	        // test set/get current game state
	        board.setCurrentState(Board.GAME_STATE.PLAYER2_WON);
	        assertEquals(Board.GAME_STATE.PLAYER2_WON, board.getCurrentState());

	        // test set/get cell
	        move.setColumn(1);
        	move.setRow(1);
	        board.setCell(Board.PLAYER.PLAYER1, move);
	        assertEquals(Board.PLAYER.PLAYER1, board.getCell(move));
	        
	        move.setColumn(1);
        	move.setRow(3);
	        board.setCell(Board.PLAYER.PLAYER2, move);
	        assertEquals(Board.PLAYER.PLAYER2, board.getCell(move));
	    }
	    
	    //testing that there are not any moves out of the board size 
	    @Test
	    public void testIsWithInBounds() {
	        // test return false 1
	    	move.setColumn(-1);
        	move.setRow(-1);
	        assertFalse(board.isWithinBounds(move));
	        
	        move.setColumn(-1);
        	move.setRow(1);
	        assertFalse(board.isWithinBounds(move));
	        
	        move.setColumn(-1);
        	move.setRow(-1);
	        assertFalse(board.isWithinBounds(move));
	        
	        // test return false 2
	        move.setColumn(5);
        	move.setRow(1);
	        assertFalse(board.isWithinBounds(move));
	        
	        move.setColumn(2);
        	move.setRow(5);
	        assertFalse(board.isWithinBounds(move));
	        
	        move.setColumn(5);
        	move.setRow(5);
	        assertFalse(board.isWithinBounds(move));

	        // test return true
	        move.setColumn(0);
        	move.setRow(0);
	        assertTrue(board.isWithinBounds(move));
	        
	        move.setColumn(1);
        	move.setRow(1);
	        assertTrue(board.isWithinBounds(move));
	        
	        move.setColumn(2);
        	move.setRow(2);
	        assertTrue(board.isWithinBounds(move));
	        
	        move.setColumn(3);
        	move.setRow(3);
	        assertTrue(board.isWithinBounds(move));
	    }


//	 @Test
//	    public void testGetEnemy() {
//		 	// test default (when initialize the game)
//	        assertEquals(Board.PLAYER.PLAYER2, board.getEnemy());
//	        // test return Player 1
//	        board.setcurrentPlayer(Board.PLAYER.PLAYER2);
//	        assertEquals(Board.PLAYER.PLAYER1, board.getEnemy());
//	        // test return Empty
//	        board.setcurrentPlayer(Board.PLAYER.EMPTY);
//	        assertEquals(Board.PLAYER.EMPTY, board.getEnemy());
//		 
//	 }
	    
	 //Testing the updateGame by getting the current state of the board 
	 @Test
	    public void testUpdateGame() {
	        // test game is running
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYING, board.getCurrentState());

	        // test for player 1 won
	        board = new Board(4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.PLAYER1;
	            }

	            @Override
	            public ArrayList<Move> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(Move move) {
	                return false;
	            }
	            
	            @Override
	            public Board getClone()
	            {
	            	return this;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYER1_WON, board.getCurrentState());

	     // test for player 2 won
	        board = new Board(4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.PLAYER2;
	            }

	            @Override
	            public ArrayList<Move> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(Move move) {
	                return false;
	            }
	            
	            @Override
	            public Board getClone()
	            {
	            	return this;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYER2_WON, board.getCurrentState());

	        // test for game state is in DRAW
	        board = new Board(4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.EMPTY;
	            }

	            @Override
	            public ArrayList<Move> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(Move move) {
	                return false;
	            }
	            
	            @Override
	            public Board getClone()
	            {
	            	return this;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.DRAW, board.getCurrentState());
	    }

}