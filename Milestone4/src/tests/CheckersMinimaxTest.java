package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.*;

import common.Move;

import Boards.Board;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import Strategies.Minimax.CheckersMinimax;
import Strategies.Minimax.MinimaxPlayerType;

/**
 * CheckersMinimax
 * @author Lina El Sadek
 *
 */
public class CheckersMinimaxTest {
	private Board board;
	private PlayerType PT;
	private  MinimaxPlayerType MPT;
	private CheckersMinimax CMT;
	
	
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
		CMT = new CheckersMinimax(MPT);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(PT);
		assertNotNull(MPT);
		assertNull(board);
		assertNotNull(CMT);
		
	}
	
	/*@Test
	public void evaluateTest()
	{
		ArrayList<Move> move = new ArrayList<Move>();
		Move m1 = new Move(0,0);
		Move m2 = new Move(3,2);
		move.add(m1);
		move.add(m2);
		CMT.evaluate(PT.getBoard(),move);
		
	}*/

}
