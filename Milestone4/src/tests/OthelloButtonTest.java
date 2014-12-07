package tests;

import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;
import Boards.Board.PLAYER;
import GUI.OthelloButton;

/**
 * OthelloButtonTest
 * @author Lina El Sadek
 *
 */
public class OthelloButtonTest {
	
	private OthelloButton OB;
	private PLAYER player;
	private Color c;
	
	
	
	@Before
	public void setUp()
	{
		
		player = PLAYER.PLAYER1;
		OB = new OthelloButton(0, 0, player);
		
	}
	
	@Test
	public void ConstructorTest()
	{
		
		
		assertNotNull(OB);
	}
	
	@Test
	public void setIconTest()
	{
		OB.setIcon(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2, OB.getPlayer());
		
		OB.setIcon(PLAYER.PLAYER1);
		assertEquals(PLAYER.PLAYER1, OB.getPlayer());
		
		OB.setIcon(PLAYER.EMPTY);
		assertEquals(PLAYER.EMPTY, OB.getPlayer());

	}
	
	@Test
	public void getPlayerTest()
	{
		assertEquals(PLAYER.PLAYER1, OB.getPlayer());
		assertNotEquals(PLAYER.PLAYER2, OB.getPlayer());
		assertNotEquals(PLAYER.EMPTY, OB.getPlayer());

		
		
	}
	
	@Test
	public void setPlayerTest()
	{
		OB.setPlayer(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2, OB.getPlayer());
		
		OB.setPlayer(PLAYER.PLAYER1);
		assertEquals(PLAYER.PLAYER1, OB.getPlayer());
		
		OB.setPlayer(PLAYER.EMPTY);
		assertEquals(PLAYER.EMPTY, OB.getPlayer());

	}
	
	@Test
	public void flipIconTest()
	{
		OB.flipIcon();
		assertEquals(PLAYER.PLAYER2, OB.getPlayer());
		
		OB.flipIcon();
		assertEquals(PLAYER.PLAYER1, OB.getPlayer());
		
		OB.flipIcon();
		assertNotEquals(PLAYER.EMPTY, OB.getPlayer());
		
		
		
	}
	
	@Test
	public void getXTest()
	{
		assertEquals(0, OB.getX());
		assertNotEquals(-1, OB.getX());
		
	}
	
	@Test
	public void getYTest()
	{
		assertEquals(0, OB.getY());
		assertNotEquals(-1, OB.getY());
		
	}
	
	@Test
	public void toStringTest()
	{
		String s = "X: 0  Y: 0   Player: PLAYER1";
		
		assertEquals(s, OB.toString());

	}
	
	/*@Test
	public void paintComponent()
	{
		c = Color.BLACK;
		assertEquals(c, OB.getColor());
		
		OB.setPlayer(PLAYER.PLAYER2);
		c = Color.WHITE;
		assertEquals(c, OB.getColor());
		
		
	}*/
	
	
}

	
	
