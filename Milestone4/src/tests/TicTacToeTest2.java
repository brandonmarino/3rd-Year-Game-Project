package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import Boards.Board;
import Boards.Board.GAME_STATE;
import Games.TicTacToe;

public class TicTacToeTest2 {
	TicTacToe ticTacToe;

	@Before
    public void setUp() throws UnsupportedEncodingException{
		
		InputStream old = System.in;
		try {
			String data = "1 John 1 Joe 2 1 1 2 3 4";
			InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		
			System.setIn(testInput);
			ticTacToe = new TicTacToe();
			
			
		} finally {
			System.setIn(old);
		}
	}
	
	@Test
	public void test1() {
		ticTacToe.play();
		Board board = ticTacToe.getBoard();
		assertEquals(board.getCurrentState(), GAME_STATE.PLAYER2_WON);
		assertFalse(board.getCurrentState() == GAME_STATE.PLAYER1_WON);
	}
}