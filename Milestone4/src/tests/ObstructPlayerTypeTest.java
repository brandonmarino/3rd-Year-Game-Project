package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import Games.Game;
import org.junit.*;

import common.Move;
import Strategies.Alternative.ObstructPlayerType;
import Boards.Board;

/**
 * ObstructPlayerType.java
 * @author Lina El Sadek
 *
 */
public class ObstructPlayerTypeTest {
	private Game game;
    private Board board;
	private ObstructPlayerType OPT;
	
	@Before
	public void setUp()
	{
		OPT = new ObstructPlayerType(board, 0);
	}
	
	@Test
	public void constructorTest()
	{
		assertNull(board);
		assertNotNull(OPT);	
	}
	
	@Test
	public void getMoveTest()
	{
		Move m = OPT.getMove(game);
		assertNull(m);
		ArrayList<Move> move = new ArrayList<Move>();
		move = OPT.getAvailableMoves();
		assertNotNull(move);
	}
	
	
	
}
