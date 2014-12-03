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
import Boards.OthelloBoard;
import Games.Checkers;
import Games.Othello;
import Games.TicTacToe;

public class CheckersRandomPlayer {
	Checkers checkers;

	@Before
    public void setUp() throws UnsupportedEncodingException{
		
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
	
	@Test
	public void test1() {
		checkers.play();
		Board board = checkers.getBoard();

		assertFalse(!(board.getCurrentState() == GAME_STATE.PLAYER2_WON || board.getCurrentState() == GAME_STATE.PLAYER1_WON));

	}
}
