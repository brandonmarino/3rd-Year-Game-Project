package tests;
import Boards.Board;
import Boards.Board.GAME_STATE;
import Boards.Board.PLAYER;
import Boards.TicTacToeBoard;
import static org.junit.Assert.*;

import org.junit.Test;

import common.Move;

/**
* *********************************************************************************************************************************************************
* 									TicTacToeBoardTEST 
* ***********************************************************************************************************************************************************
*
* Author: Lina El Sadek
*
*/

public class TicTacToeBoardTest {
	
	//Testing the Constructor of the TicTacToe board 		
	@Test
	public void ConstructorTest() {
		TicTacToeBoard b = new TicTacToeBoard();
		assertEquals(Board.GAME_STATE.PLAYING,b.getCurrentState());
		assertEquals(Board.PLAYER.PLAYER1,b.getCurrentPlayer());
	}
	
	//Setting players on the board 
	@Test
	public void BasicSetterTesting()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		b.setcurrentPlayer(PLAYER.PLAYER2);
		assertEquals(PLAYER.PLAYER2,b.getCurrentPlayer());
		b.setCurrentState(GAME_STATE.DRAW);
		assertEquals(GAME_STATE.DRAW,b.getCurrentState());
		assertNotEquals(GAME_STATE.PLAYER1_WON,b.getCurrentState());
	}
	
	//Making some moves and testing attemptMove
	@Test
	public void TicTacToeBoardTestAndDraw()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		Move move = new Move();
		//Test if out of bound is prohibited
        try
        {
        	move.setColumn(9);
        	move.setRow(9);
        	b.attemptMove(move);
        	fail("Index out of bound");
        }
        catch (IndexOutOfBoundsException e)
        {
        	assertEquals("9", e.getMessage());
        	
        }
        
      //Test if out of bound is prohibited
        try
        {
        	move.setColumn(4);
        	move.setRow(3);
        	b.attemptMove(move);
        	fail("Index out of bound");
        }
        catch (IndexOutOfBoundsException e)
        {
        	assertEquals("3", e.getMessage());
        	
        }
		//Making different moves by setting different values to rows and columns 
		
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(1);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(2);
        move.setRow(0);
		b.attemptMove(move);
		
		assertTrue(b.isHorizontal());
		assertFalse(b.isVertical());
		assertEquals(0,b.isDiagonal());	//if it's a zero then it's false
		
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(0);
        move.setRow(1);
		b.attemptMove(move);
		
		move.setColumn(0);
        move.setRow(2);
		b.attemptMove(move);
		
		assertTrue(b.isVertical());
		assertFalse(b.isHorizontal());
		assertEquals(0,b.isDiagonal());
		
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(1);
        move.setRow(1);
		b.attemptMove(move);
		
		move.setColumn(2);
        move.setRow(2);
		b.attemptMove(move);
		
		assertEquals(1,b.isDiagonal()); //it returns 1 if it's true
		assertFalse(b.isHorizontal());
		assertFalse(b.isVertical());
	}

	@Test
	public void TicTacToeBoardTestAndHasWon()
	{
		TicTacToeBoard b = new TicTacToeBoard();
		Move move = new Move();
		
		//Test if out of bound is prohibited
		 try
		 {
        		move.setColumn(6);
        		move.setRow(3);
        		b.attemptMove(move);
        		fail("Index out of bound");
        	}
        	catch (IndexOutOfBoundsException e)
        	{
        		assertEquals("3", e.getMessage());
        	}
        
        
		b.setcurrentPlayer(PLAYER.PLAYER1);
		//Test 1 - horizontal
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(1);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(2);
        move.setRow(0);
		b.attemptMove(move);
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
		assertTrue(b.getCurrentState() == GAME_STATE.PLAYER1_WON);
		
		b = new TicTacToeBoard();
		b.setcurrentPlayer(PLAYER.PLAYER1);
		//Test 5 - vertical
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(0);
        move.setRow(1);
		b.attemptMove(move);
		
		move.setColumn(0);
        move.setRow(2);
		b.attemptMove(move);
		
		b.updateGame();
		assertTrue(b.isVertical());
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
		//Test 8 - Diagonal
		move.setColumn(0);
        move.setRow(0);
		b.attemptMove(move);
		
		move.setColumn(1);
        move.setRow(1);
		b.attemptMove(move);
		
		move.setColumn(2);
        move.setRow(2);
		b.attemptMove(move);
		
		b.updateGame();
		assertTrue(b.getCurrentState() != GAME_STATE.PLAYING);
	
	}

}
