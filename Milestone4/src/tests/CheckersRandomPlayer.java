package tests;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import Boards.Board;
import Boards.Board.GAME_STATE;
import Boards.OthelloBoard;
import Games.Checkers;
import Games.Othello;
import Games.TicTacToe;

import static org.junit.Assert.*;

public class CheckersRandomPlayer {
	Checkers checkers;

	@Before
    public void setUp() throws UnsupportedEncodingException{
		// to simulate players moves
		InputStream old = System.in;
		try {
			String data = "2 3";
			InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		
			System.setIn(testInput);
			checkers = new Checkers();
		} finally {
			System.setIn(old);
		}
	}
	//Testing the board for win based on the moves made 
	@Test
	public void constructorTest()
	{

		assertNotNull(checkers);
        assertNotNull(checkers.getBoard());
		assertNotNull(checkers.getPlayers());
		
	}
	
	@Test
	public void test() {
		Board board = checkers.getBoard();
		assertTrue(!(board.getCurrentState() == GAME_STATE.PLAYER2_WON || board.getCurrentState() == GAME_STATE.PLAYER1_WON));

	}
}
