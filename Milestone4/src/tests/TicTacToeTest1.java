package tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Boards.Board;
import Boards.Board.GAME_STATE;
import Boards.Board.PLAYER;
import Games.Othello;
import Games.TicTacToe;

public class TicTacToeTest1 {
    TicTacToe ticTacToe;
    @Before
    public void setUp() throws UnsupportedEncodingException{
		//Testing tictactoe by simulating two players inputs and make them play
		//then check for winning player 
		InputStream old = System.in;
		try {
			//Testing tictactoe by simulating two players inputs and make them play
			//then check for winning player 
			String data = "1 John 1 Joe 1 2 3 4 5";
			InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		
			System.setIn(testInput);
			ticTacToe = new TicTacToe();
			
		} finally {
			System.setIn(old);
		}
	}
	
	@Test
	public void test() {
		
		Board board = ticTacToe.getBoard();
		assertEquals(board.getCurrentState(), GAME_STATE.PLAYING);
		assertFalse(board.getCurrentState() == GAME_STATE.PLAYER1_WON);
	}
}
