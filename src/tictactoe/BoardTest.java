package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void ConstructorTest() {
		Board b = new Board();
		assertEquals(0,b.getCurrentState());
		assertEquals(1,b.getcurrentPlayer());
	}
	
	@Test
	public void GetterTesting()
	{
		Board b = new Board();
		assertEquals(1,b.getCROSS());
		assertEquals(2,b.getCROSSWon());
		assertEquals(0,b.getcurrentColumn());
		assertEquals(1,b.getcurrentPlayer());
		assertEquals(0,b.getcurrentRow());
		assertEquals(0,b.getCurrentState());
		assertEquals(1,b.getDRAW());
		assertEquals(0,b.getEMPTY());
		assertEquals(0,b.getMoveOnBoard(1, 2));
		assertEquals(2,b.getNOUGHT());
		assertEquals(3,b.getNOUGHTWon());
		assertEquals(0,b.getPLAYING());
	}
	
	@Test
	public void BasicSetterTesting()
	{
		Board b = new Board();
		b.setcurrentColumn(2);
		assertEquals(2,b.getcurrentColumn());
		
		b.setcurrentPlayer(2);
		assertEquals(2,b.getcurrentPlayer());
		
		b.setcurrentRow(1);
		assertEquals(1,b.getcurrentRow());
		
		b.setCurrentState(3);
		assertEquals(3,b.getCurrentState());
	}
	
	
	@Test
	public void BoardTestAndDraw()
	{
		Board b = new Board();
		
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 2);
		b.setMoveOnBoard(0, 2, 1);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 1);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 1);
		b.setMoveOnBoard(2, 1, 2);
		b.setMoveOnBoard(2, 2, 1);
		
		assertTrue(b.isDraw());
		
		b.setMoveOnBoard(0, 0, 2);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 2);
		
		b.setMoveOnBoard(1, 0, 1);
		b.setMoveOnBoard(1, 1, 2);
		b.setMoveOnBoard(1, 2, 1);
		
		b.setMoveOnBoard(2, 0, 2);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isDraw());
		
		b.setMoveOnBoard(2, 2, 0);
		assertFalse(b.isDraw());
		
		b.setMoveOnBoard(2, 2, 2);
		b.setMoveOnBoard(1, 1, 0);
		assertFalse(b.isDraw());
		
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 2);
		b.setMoveOnBoard(0, 2, 2);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 1);
		b.setMoveOnBoard(1, 2, 1);
		
		b.setMoveOnBoard(2, 0, 1);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isDraw());
	
	}

	@Test
	public void BoardTestAndHasWon()
	{
		Board b = new Board();
		
		//Test 1 - horizontal
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 1);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 1);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 0);
		b.setMoveOnBoard(2, 1, 0);
		b.setMoveOnBoard(2, 2, 0);
		
		assertTrue(b.isHorizontal(1, 0, 0));
		assertTrue(b.hasWon(1,0,0));
		
		//Test 2 - horizontal
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 0);
		b.setMoveOnBoard(0, 2, 1);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 0);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 1);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 1);
		
		assertTrue(b.isHorizontal(1, 2, 0));
		assertTrue(b.hasWon(1,2,0));
		
		//Test 3 - horizontal
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 0);
		b.setMoveOnBoard(0, 2, 1);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 2);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 0);
		b.setMoveOnBoard(2, 1, 0);
		b.setMoveOnBoard(2, 2, 1);
		
		assertTrue(b.isHorizontal(2, 1, 0));
		assertTrue(b.hasWon(2,1,0));		
		
		//Test 4 - vertical
		b.setMoveOnBoard(0, 0, 0);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 2);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 0);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 2);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isVertical(2, 0, 2));
		assertTrue(b.hasWon(2,0,2));
		
		//Test 5 - vertical
		b.setMoveOnBoard(0, 0, 1);
		b.setMoveOnBoard(0, 1, 0);
		b.setMoveOnBoard(0, 2, 0);
		
		b.setMoveOnBoard(1, 0, 1);
		b.setMoveOnBoard(1, 1, 0);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 1);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isVertical(1, 0, 0));
		assertTrue(b.hasWon(1, 0, 0));
		
		//Test 6 - vertical
		b.setMoveOnBoard(0, 0, 2);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 0);
		
		b.setMoveOnBoard(1, 0, 1);
		b.setMoveOnBoard(1, 1, 1);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 1);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isVertical(1, 0, 1));
		assertTrue(b.hasWon(1, 0, 1));
		
		//Test 7 - Opposite Diagonal
		b.setMoveOnBoard(0, 0, 0);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 2);
		
		b.setMoveOnBoard(1, 0, 2);
		b.setMoveOnBoard(1, 1, 2);
		b.setMoveOnBoard(1, 2, 2);
		
		b.setMoveOnBoard(2, 0, 2);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 0);
		
		assertTrue(b.isDiagonal(2, 0, 2));
		assertTrue(b.hasWon(2,0,2));
		
		//Test 8 - Diagonal
		b.setMoveOnBoard(0, 0, 2);
		b.setMoveOnBoard(0, 1, 1);
		b.setMoveOnBoard(0, 2, 0);
				
		b.setMoveOnBoard(1, 0, 0);
		b.setMoveOnBoard(1, 1, 2);
		b.setMoveOnBoard(1, 2, 0);
				
		b.setMoveOnBoard(2, 0, 0);
		b.setMoveOnBoard(2, 1, 1);
		b.setMoveOnBoard(2, 2, 2);
		
		assertTrue(b.isDiagonal(2, 0, 0));
		assertTrue(b.hasWon(2,0,0));
	
	}

}
