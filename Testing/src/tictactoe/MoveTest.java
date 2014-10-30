package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class MoveTest {

	@Test
	public void Constructortest() {
		Move m = new Move();
		assertEquals(0, m.getcurrentRow());
		assertEquals(0, m.getcurrentColumn());
	}
	


}
