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
	
	@Test
	public void ConstructorTest() {
		CheckersBoard b = new CheckersBoard();
		assertEquals(Board.GAME_STATE.PLAYING, b.getCurrentState());
		assertEquals(Board.PLAYER.PLAYER1, b.getCurrentPlayer());
	}

	@Test
	public void BasicSetterTesting() {
		CheckersBoard b = new CheckersBoard();
		b.setcurrentPlayer(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2, b.getCurrentPlayer());
		b.setCurrentState(GAME_STATE.DRAW);
		assertEquals(GAME_STATE.DRAW, b.getCurrentState());
		
	}
	
	@Test
	public void countSpacesTestAndAtamptMove() {
		CheckersBoard b = new CheckersBoard();
		assertEquals(b.countSpaces(PLAYER.PLAYER2), b.countSpaces(PLAYER.PLAYER1));
		b.setcurrentPlayer(PLAYER.PLAYER1);
		b.attemptMove(new CheckersMove(new Move(2, 5), new Move(2, 7)));
		assertTrue(b.countSpaces(PLAYER.PLAYER1) != b.countSpaces(PLAYER.PLAYER2));
	}	

}