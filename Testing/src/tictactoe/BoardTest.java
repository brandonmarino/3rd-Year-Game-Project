package Testing;
import Boards.Board;
import Boards.Board.GAME_STATE;
import Boards.Board.PLAYER;
import Boards.TicTacToeBoard;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicTacToeBoardTest {

	@Test
	public void ConstructorTest() {
		TicTacToeBoard b = new TicTacToeBoard();
		assertEquals(Board.GAME_STATE.PLAYING,b.getCurrentState());
		assertEquals(Board.PLAYER.PLAYER1,b.getCurrentPlayer());
	}
	
	
	@Test
	public void BasicSetterTesting()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		b.setcurrentPlayer(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2,b.getCurrentPlayer());
		b.setCurrentState(GAME_STATE.DRAW);
		assertEquals(GAME_STATE.DRAW,b.getCurrentState());
		assertNotEquals(GAME_STATE.PLAYER1_WON,b.getCurrentState());
	}
	
	
	@Test
	public void TicTacToeBoardTestAndDraw()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		
		b.attemptMove(0, 0);
		b.attemptMove(0, 1);
		b.attemptMove(0, 2);
		
		assertTrue(b.isHorizontal());
		assertFalse(b.isVertical());
		assertFalse(b.isDiagonal());
		
		b.attemptMove(0, 0);
		b.attemptMove(1, 0);
		b.attemptMove(2, 0);
		
		assertTrue(b.isVertical());
		assertFalse(b.isHorizontal());
		assertFalse(b.isDiagonal());
		
		b.attemptMove(0, 0);
		b.attemptMove(1, 1);
		b.attemptMove(2, 2);
		
		assertTrue(b.isDiagonal());
		assertFalse(b.isHorizontal());
		assertFalse(b.isVertical());
	}

	@Test
	public void TicTacToeBoardTestAndHasWon()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		b.setcurrentPlayer(PLAYER.PLAYER1);
		//Test 1 - horizontal
		b.attemptMove(0, 0);
		b.attemptMove(0, 1);
		b.attemptMove(0, 2);
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
		assertTrue(b.getCurrentState() == GAME_STATE.PLAYER1_WON);
		
		b = new TicTacToeBoard();
		b.setcurrentPlayer(PLAYER.PLAYER1);
		//Test 5 - vertical
		b.attemptMove(0, 0 );
		b.attemptMove(1, 0 );
		b.attemptMove(2, 0);
		
		b.updateGame();
		assertTrue(b.isVertical());
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
		//Test 8 - Diagonal
		b.attemptMove(0, 0);
		b.attemptMove(1, 1);
		b.attemptMove(2, 2);
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
	
	}

}
