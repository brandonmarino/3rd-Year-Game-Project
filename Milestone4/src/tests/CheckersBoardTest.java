package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.CheckersMove;
import common.Move;
import Boards.Board;
import Boards.CheckersBoard;
import Boards.TicTacToeBoard;
import Boards.Board.GAME_STATE;
import Boards.Board.PLAYER;

public class CheckersBoardTest {
	
	//Testing the Constructor of the CheckersBoard 
	@Test
	public void ConstructorTest() {
		CheckersBoard b = new CheckersBoard();
		assertEquals(Board.GAME_STATE.PLAYING, b.getCurrentState());
		assertEquals(Board.PLAYER.PLAYER1, b.getCurrentPlayer());
	}

	//Testing setting the two players on the CheckersBoard and setting the game state to DRAW 
	@Test
	public void BasicSetterTesting() {
		CheckersBoard b = new CheckersBoard();
		b.setcurrentPlayer(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2, b.getCurrentPlayer());
		b.setCurrentState(GAME_STATE.DRAW);
		assertEquals(GAME_STATE.DRAW, b.getCurrentState());
		
	}
	
	//A test for counting the spaces for each player then moving the player to test the attempt move 
	@Test
	public void countSpacesTestAndAtamptMove() {
		CheckersBoard b = new CheckersBoard();
		assertEquals(b.countSpaces(PLAYER.PLAYER2), b.countSpaces(PLAYER.PLAYER1));
		try{
			 b.setcurrentPlayer(PLAYER.PLAYER1);
			 
			 b.attemptMove(new CheckersMove(new Move(2, 7), new Move(9, 10)));
    		fail("Out of bound index");
		}catch (IndexOutOfBoundsException e)
		{
			assertEquals("8", e.getMessage());	
		}
		b.setcurrentPlayer(PLAYER.PLAYER1);
		b.attemptMove(new CheckersMove(new Move(2, 5), new Move(2, 7)));
		assertTrue(b.countSpaces(PLAYER.PLAYER1) != b.countSpaces(PLAYER.PLAYER2));
	}	

}
