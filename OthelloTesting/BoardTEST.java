package Boards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
*/

public class BoardTEST {
	
	 private Board board;

	 	@Before
	    public void setUp() {
		    board = new Board(4, 4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return null;
	            }

	            @Override
	            public ArrayList<Integer[]> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(int row, int column) {
	                return false;
	            }
	        };
	 
	 }
	 
	 	@Test
	    public void testProperties() {
	 		assertEquals(4, board.ROWS());
	        assertEquals(4, board.COLUMNS());
	        PLAYER[][] players = board.getBoard();
	        assertEquals(4, players.length);
	        for (PLAYER[] player : players) {
	            for (PLAYER player2 : player) {
	                assertEquals(Board.PLAYER.EMPTY, player2);
	            }
	        }
	        assertEquals(Board.GAME_STATE.PLAYING, board.getCurrentState());
	        assertEquals(Board.PLAYER.PLAYER1, board.getcurrentPlayer());
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 4; j++) {
	                assertEquals(Board.PLAYER.EMPTY, board.getCell(i, j));
	            }
	        }
		 
	 }
	 

	    @SuppressWarnings("deprecation")
	    @Test
	    public void testGetEmptySpaces() {
	        ArrayList<Integer[]> emptySpaces = board.getEmptySpaces();
	        ArrayList<Integer[]> expectedSpaces = new ArrayList<Integer[]>();
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 4; j++) {
	                Integer[] ret = { i, j };
	                expectedSpaces.add(ret);
	            }
	        }
	        for (int i = 0; i < emptySpaces.size(); i++) {
	            Integer[] actualValue = emptySpaces.get(i);
	            assertEquals(2, actualValue.length);
	            assertEquals(expectedSpaces.get(i), actualValue);
	        }
	    }

	    @Test
	    public void testGetSetMethod() {
	        // test set/get current player
	        board.setcurrentPlayer(Board.PLAYER.PLAYER2);
	        assertEquals(Board.PLAYER.PLAYER2, board.getcurrentPlayer());

	        // test set/get current game state
	        board.setCurrentState(Board.GAME_STATE.PLAYER2_WON);
	        assertEquals(Board.GAME_STATE.PLAYER2_WON, board.getCurrentState());

	        // test set/get cell
	        board.setCell(Board.PLAYER.PLAYER1, 1, 1);
	        board.setCell(Board.PLAYER.PLAYER2, 3, 1);
	        assertEquals(Board.PLAYER.PLAYER1, board.getCell(1, 1));
	        assertEquals(Board.PLAYER.PLAYER2, board.getCell(3, 1));
	    }
	    
	    @Test
	    public void testIsWithInBounds() {
	        // test return false 1
	        assertFalse(board.isWithinBounds(-1, 1));
	        assertFalse(board.isWithinBounds(1, -1));
	        assertFalse(board.isWithinBounds(-1, -1));
	    }


	 @Test
	    public void testGetEnemy() {
		 	// test default (when initialize the game)
	        assertEquals(Board.PLAYER.PLAYER2, board.getEnemy());
	        // test return Player 1
	        board.setcurrentPlayer(Board.PLAYER.PLAYER2);
	        assertEquals(Board.PLAYER.PLAYER1, board.getEnemy());
	        // test return Empty
	        board.setcurrentPlayer(Board.PLAYER.EMPTY);
	        assertEquals(Board.PLAYER.EMPTY, board.getEnemy());
		 
	 }
	 
	 @Test
	    public void testUpdateGame() {
	        // test game is running
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYING, board.getCurrentState());

	        // test for player 1 won
	        board = new Board(4, 4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.PLAYER1;
	            }

	            @Override
	            public ArrayList<Integer[]> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(int row, int column) {
	                return false;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYER1_WON, board.getCurrentState());

	     // test for player 2 won
	        board = new Board(4, 4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.PLAYER2;
	            }

	            @Override
	            public ArrayList<Integer[]> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(int row, int column) {
	                return false;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.PLAYER2_WON, board.getCurrentState());

	        // test for game state is in DRAW
	        board = new Board(4, 4) {

	            @Override
	            protected PLAYER hasBeenWon() {
	                return PLAYER.EMPTY;
	            }

	            @Override
	            public ArrayList<Integer[]> getPossibleMoves() {
	                return null;
	            }

	            @Override
	            public boolean attemptMove(int row, int column) {
	                return false;
	            }
	        };
	        board.updateGame();
	        assertEquals(Board.GAME_STATE.DRAW, board.getCurrentState());
	    }

}
