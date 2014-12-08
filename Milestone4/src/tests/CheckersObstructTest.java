package Testing;

import static org.junit.Assert.*;

import org.junit.*;

import Boards.Board;
import Strategies.Alternative.CheckersObstruct;
import Strategies.Alternative.ObstructPlayerType;

/**
 * CheckersObstructTest.java
 * @author Lina El Sadek
 *
 */
public class CheckersObstructTest {
	private ObstructPlayerType OPT;
	private CheckersObstruct CO;
	private Board board;
	
	@Before
	public void setUp()
	{
		OPT = new ObstructPlayerType(board, 0);
		CO = new CheckersObstruct(OPT);
		
		
	}
	
	@Test
	public void constructorTest()
	{
		assertNotNull(OPT);
		assertNotNull(CO);
		
	}
	
}
