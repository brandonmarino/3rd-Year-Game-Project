package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.*;

import Boards.Board;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import Strategies.Minimax.TicTacToeMinimax;

/**
 * TicTacToeMinimaxTest
 * @author Lina El Sadek
 *
 */
public class TicTacToeMinimaxTest {

	private TicTacToeMinimax TTTM;
	private MinimaxPlayerType MPT;
	private PlayerType PT;
	private Board board;
	//Setting up the checkers class to test the minimax by making a human player and minimax player 
	@Before
	public void setUp()
	{
		try
		{
		String data = "John";
		InputStream testInput;
		testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		System.setIn(testInput);
		PT = new HumanPlayerType(board, 1);
		MPT = new MinimaxPlayerType(PT.getBoard(), 1);
		TTTM = new TicTacToeMinimax(MPT);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	//testing the constructor by comparing to null 
	@Test
	public void constructorTest()
	{
		assertNotNull(MPT);
		assertNotNull(PT);
		assertNotNull(TTTM);
	}
	

}
