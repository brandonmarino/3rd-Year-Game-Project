package Games;

import Boards.Board;
import Boards.OthelloBoard;

public class GameNotAbs extends Game{
	Board board;
	public GameNotAbs(GameNotAbs board)
	{
		super(board.getBoardGame());
	}
}
