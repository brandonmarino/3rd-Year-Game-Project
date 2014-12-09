package tests;

import static org.junit.Assert.*;

import org.junit.*;

import Strategies.Alternative.ObstructPlayerType;
import Strategies.Alternative.OthelloObstruct;
import Boards.Board;

/**
 * OthelloObstructTest.java
 * @author Lina El Sadek
 *
 */
public class OthelloObstructTest_old {
	private Board board;
	private ObstructPlayerType OPT;
	private OthelloObstruct OO;

	@Before
	public void setUp()
	{
		assertNull(board);
		assertNull(OPT);
		assertNull(OO);
		OPT = new ObstructPlayerType(board, 0);
		OO = new OthelloObstruct(OPT);
	}
	
	@Test
	public void constructorTest()
	{
		
		assertNull(board);
		assertNotNull(OPT);
		assertNotNull(OO);
		
	}
}
