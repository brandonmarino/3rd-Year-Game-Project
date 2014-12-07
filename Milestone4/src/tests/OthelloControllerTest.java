package tests;


import static org.junit.Assert.*;

import org.junit.*;

import Boards.Board;
import GUI.OthelloController;
import PlayerTypes.HumanPlayerType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * OthelloControllerTest
 * @author Lina El Sadek
 *
 */
public class OthelloControllerTest {
	private HumanPlayerType HP;
	private Board board;
	private OthelloController OC;
	//private OthelloButton OB;
	
	
	@Before
	public void setUp()
	{
		
		try {

			String data = "John";
			InputStream testInput;
			testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
			System.setIn(testInput);

			HP = new HumanPlayerType(board, 0);
			//OB = new OthelloButton(2,3, PLAYER.PLAYER1);
			OC = new OthelloController(HP);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void ConstructorTest()
	{
		assertNotNull(OC);
		
	}
	
	
	/*@Test
	public void actionPerformedTest()
	{
		OB.addActionListener(OC);
		ActionEvent e = new ActionEvent(OB, OB.getX(), OB.toString());
		OC.actionPerformed(e);
		//assertEquals(OB.getX(), OC.g)
		
	}*/
	
	@Test
	public void getMoveTest()
	{
		assertNull(OC.getMove());
		
	}
	

}
