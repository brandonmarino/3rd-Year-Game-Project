package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.*;

import Boards.Board;
import Games.Othello;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import Strategies.Minimax.MinimaxPlayerType;

public class MinimaxPlayerTypeTest {
	
	private MinimaxPlayerType MPT;
	private PlayerType PT;
	private Board board;
	//Setting up the MinimaxPlayerType test 
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
	}
	
	//Creating Othello game and getting a Minimax Move from it  
	@Test
	public void  getMoveTest()
	{
		Othello Oth = new Othello();
		assertNull(MPT.getMove(Oth));
	}

}
