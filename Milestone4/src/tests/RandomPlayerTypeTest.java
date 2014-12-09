package tests;


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Boards.Board;
import Games.Othello;
import PlayerTypes.PlayerType;
import PlayerTypes.RandomPlayerType;

import org.junit.Before;
import org.junit.Test;

import common.Move;

/**
 * *********************************************************************************************************************************************************
 * 									RandomPlayerTypeTEST 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test for RandomPlayerTypeTEST Class
 * 
 * Edited by: Lina El Sadek
 * for Milestone 3 & 4 
 */

public class RandomPlayerTypeTest {
	private PlayerType randomPlayer; 
	private Othello Oth;
	private Board board;
	 	@Before
	    public void setUp() throws UnsupportedEncodingException {
			
			
			InputStream old = System.in;
			try {
				String data = "2\r\n2";
				InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
			
				System.setIn(testInput);
				
		 randomPlayer = new RandomPlayerType(board,0);
		 Oth = new Othello();
		 } finally {
				System.setIn(old);
			}
			
	    }

	 	@Test
	    public void testGetMove() {
	 		
	        // test with case not enough available moves
	        ArrayList<Move> availableMoves = new ArrayList<Move>();
	        randomPlayer.setAvailableMoves(availableMoves);
	        assertNull(randomPlayer.getMove(Oth));
	        
	        // test with case have an available moves
	        availableMoves.add(new Move( 1,2 ));
	        randomPlayer.setAvailableMoves(availableMoves);
	        Move move = randomPlayer.getMove(Oth);
	        Move temp = new Move(1,2);
	        assertEquals(temp, move);
	        //assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	        
	        // test another case
	        availableMoves.add(new Move (1,2));
	        availableMoves.add(new Move(2,1));
	        randomPlayer.setAvailableMoves(availableMoves);
	        randomPlayer.getMove(Oth);
	       // assertEquals(1, randomPlayer.getAvailableMoves().size());
	        randomPlayer.getMove(Oth);
	       // assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	        
	        availableMoves.add(new Move(2,3));
	        randomPlayer.setAvailableMoves(availableMoves);
	       Move move1 = randomPlayer.getMove(Oth);
	        assertNotEquals(temp, move1);	//testing for an invalid value
	        temp = null;
	        assertNull(temp);
	        assertNotEquals(temp, move1);
	        temp = move1;
	        assertEquals(temp, move1);
	        assertNotNull(temp);
	        
	       Move anotherMove = new Move();
	       temp.setRow(0);
	       temp.setColumn(0);
	       assertNotEquals(temp, anotherMove);
	       temp.setRow(-1);
	       temp.setColumn(-1);
	       assertEquals(temp, anotherMove);
	 }

}
