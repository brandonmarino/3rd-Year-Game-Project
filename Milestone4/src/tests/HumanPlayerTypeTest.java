package tests;


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Boards.Board;
import Boards.OthelloBoard;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;

import org.junit.Before;
import org.junit.Test;

import common.Move;

/**
 * *********************************************************************************************************************************************************
 * 									HumanPlayerTypeTEST 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test for HumanPlayerTypeTEST Class
 * 
 * Edited by: Lina El Sadek
 * for Milestone 3&4
 */

public class HumanPlayerTypeTest {
	 private PlayerType humanPlayerMove;
	 private OthelloBoard board;
	    @Before
	    public void setUp() throws UnsupportedEncodingException {
			
			
			InputStream old = System.in;
			try {
				String data = "1 1";
				InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
			
				System.setIn(testInput);
				OthelloBoard board = new OthelloBoard();
	        humanPlayerMove = new HumanPlayerType(board, 0);
	        
			} finally {
				System.setIn(old);
			}
	    }
	    @Test
		public void constructorTest()
		{
			assertNotNull(humanPlayerMove);
			assertNull(board);
			
		}

	    @Test
	    public void testGetMove() {
	        // test with case not enough available moves
	        ArrayList<Move> availableMoves = new ArrayList<Move>();
	        Move m1 = new Move(0,0);
	        Move m2 = new Move(1,1);
	        availableMoves.add(m1);
	        availableMoves.add(m2);
	        
	        humanPlayerMove.setAvailableMoves(availableMoves);
	        assertNotNull(humanPlayerMove);
	        
	    }

}
