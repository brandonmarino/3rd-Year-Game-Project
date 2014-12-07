package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import common.Move;

/**
 * MoveTest
 * @author Lina El Sadek
 *
 */

public class MoveTest {
	private Move move, anotherMove, thirdMove;
	
	@Before
    public void setUp() {
		
		move = new Move();
		anotherMove = new Move(2, 3);
		thirdMove = new Move(1, 0, 3);
	}
	
	
	@Test
	public void TestConstructor()
	{
		Move test1 = new Move(-1, -1);
		Move test2 = new Move(2, 3);
		Move test3 = new Move(1,0,3);
		Move ErrorMove = new Move(0,0);
		
		assertNotNull(move);
		assertNotNull(anotherMove);
		assertNotNull(thirdMove);
		assertEquals(test1, move);
		assertEquals(test2, anotherMove);
		assertEquals(test3, thirdMove);
		
		assertNotEquals(ErrorMove, move);
		assertNotEquals(ErrorMove, anotherMove);
		assertNotEquals(ErrorMove, thirdMove);
		

	}
	
	@Test
	public void getWorthTest()
	{
		assertEquals(3, thirdMove.getWorth());
		assertNotEquals(0, thirdMove.getWorth());
		assertNotEquals(-3, thirdMove.getWorth());
		
		
	}
    
	@Test
	public void getRowTest()
	{
		assertEquals(-1, move.getRow());
		assertEquals(2, anotherMove.getRow());
		assertEquals(1, thirdMove.getRow());
		
		assertNotEquals(1, move.getRow());
		assertNotEquals(-2, anotherMove.getRow());
		assertNotEquals(-1, thirdMove.getRow());
		
		
	}
   
	@Test
	public void getColumnTest()
	{
		assertEquals(-1, move.getColumn());
		assertEquals(3, anotherMove.getColumn());
		assertEquals(0, thirdMove.getColumn());
		
		assertNotEquals(1, move.getColumn());
		assertNotEquals(-3, anotherMove.getColumn());
		assertNotEquals(9, thirdMove.getColumn());
		
		
	}
    
	@Test
	 public void setWorthTest()
	 {
		move.setWorth(3);
		anotherMove.setWorth(5);
		thirdMove.setWorth(0);
		assertEquals(3, move.getWorth());
		assertEquals(5, anotherMove.getWorth());
		assertEquals(0, thirdMove.getWorth());
		
		assertNotEquals(-3, move.getWorth());
		assertNotEquals(-5, anotherMove.getWorth());
		assertNotEquals(8, thirdMove.getWorth());
		
		
	 }
	
	@Test
	public void setRowTest()
	{
		move.setRow(4);
		anotherMove.setRow(6);
		thirdMove.setRow(0);
		
		assertEquals(4, move.getRow());
		assertEquals(6, anotherMove.getRow());
		assertEquals(0, thirdMove.getRow());
		
		assertNotEquals(-4, move.getRow());
		assertNotEquals(-6, anotherMove.getRow());
		assertNotEquals(-1, thirdMove.getRow());
		
		
	}
   
	@Test
	public void setColumnTest()
	{
		move.setColumn(4);
		anotherMove.setColumn(6);
		thirdMove.setColumn(0);
		
		assertEquals(4, move.getColumn());
		assertEquals(6, anotherMove.getColumn());
		assertEquals(0, thirdMove.getColumn());
		
		assertNotEquals(1, move.getColumn());
		assertNotEquals(-3, anotherMove.getColumn());
		assertNotEquals(9, thirdMove.getColumn());
		
		
	}
    
	
   @Test
   public void equalsTest()
   {
	   Move test1 = new Move(-1, -1);
	   Move test2 = new Move(2, 3);
		Move test3 = new Move(1,0,3);
		
		
	   assertTrue(move.equals(test1));
	   assertFalse(anotherMove.equals(test1));
	   assertFalse(thirdMove.equals(test1));
	   
	   assertFalse(move.equals(test2));
	   assertTrue(anotherMove.equals(test2));
	   assertFalse(thirdMove.equals(test2));
	   
	   assertFalse(move.equals(test3));
	   assertFalse(anotherMove.equals(test3));
	   assertTrue(thirdMove.equals(test3));
	   
	   
   }
    
}
