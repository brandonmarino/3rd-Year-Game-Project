package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.*;

import common.Move;
import Strategies.Alternative.ObstructPlayerType;
import Boards.Board;
import Games.Othello;
import Games.TicTacToe;

/**
 * ObstructPlayerType.java
 * @author Lina El Sadek
 *
 */
public class ObstructPlayerTypeTest {
	private Board board;
	private ObstructPlayerType OPT;
	private Othello oth;
	@Before
	public void setUp()throws UnsupportedEncodingException {
		
		
		InputStream old = System.in;
		try {
			String data = "2\r\n2";
			InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		
			System.setIn(testInput);
			
	
		OPT = new ObstructPlayerType(board, 0);
		oth = new Othello();
		} finally {
			System.setIn(old);
		}
	}
	
	@Test
	public void constructorTest()
	{
		assertNull(board);
		assertNotNull(OPT);
		//assertNull(oth);
	}
	
	@Test
	public void getMoveTest()
	{
		Move m = new Move(0,0);
		
		m = OPT.getMove(oth);
		assertNull(m);
		ArrayList<Move> move = new ArrayList<Move>();
		move = OPT.getAvailableMoves();
		assertNotNull(move);
	}
	
	
	
}
