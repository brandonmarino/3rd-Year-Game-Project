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

public class MinimaxPlayerTypeTest {
	
	private MinimaxPlayerType MPT;
	private PlayerType PT;
	private Board board;
	
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
	
	@Test
	public void constructorTest()
	{
		assertNotNull(MPT);
		assertNotNull(PT);	
	}
	
	@Test
	public void  getMoveTest()
	{
		assertNull(MPT.getMove(null));
	}

}
