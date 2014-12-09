package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import common.CheckersMove;
import common.Move;

public class CheckersMoveTest {
	
	// Testing the move of checkers by making a move then comparing to a CheckersMove 
	@Test
	public void ConstructorTest() {
		Move move = new Move(3, 3);
		CheckersMove checkersMove = new CheckersMove(new Move(2, 2), move);
		assertFalse(!checkersMove.getDest().equals(move));
	}
	
}
