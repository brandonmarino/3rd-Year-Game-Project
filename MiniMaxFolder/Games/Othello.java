package Games;

import Boards.OthelloBoard;
import Moves.OthelloAIMove;
import Moves.Move;

/***************************************************************************************************************************************************************
 * 										Othello Class Plays Tic Tac Toe by Implementing Methods from RandomMove and OthelloBoard Class
 ***************************************************************************************************************************************************************
 ** Adapted from Othello source of TIC TAC TOE Authored by Lina
 * Milestone 1, Adapting Author: Brandon Marino
 *
 * Much of the code is similar however i've changed the method to use implicit enum values opposed to constants which were set to an interger value
 * Also changed the intended game to Othello, not tic tac toe
 */
public class Othello extends Game
{
	private int boardDim = 4;
	private int totalDiskNum = boardDim * boardDim;
	//private OthelloBoard OB;
	private OthelloAIMove OAIM;
	
	/**
     * Main run function
     */
    public static void main(String[] args) {
Othello game = new Othello();
        game.Smartplay();
        //game.Smartplay(game, 3, game.getOAIM());
    }
    
    /**
     * Play a game of othello
     */
    public Othello(){
    	super(new OthelloBoard());
    	OAIM = new OthelloAIMove(4,4);
    	//OB= new OthelloBoard();
    }
    
    public Othello(Othello o){
        super( o.getBoardGame());
        //Create variables, and initialize them
       
    }
    
    
    public OthelloBoard getBoard()
    {
    	return (OthelloBoard)this.getBoardGame();
    }
    
    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(Move move){
        boolean takeTurn = super.takeTurn(move);
        ((OthelloBoard)this.getBoardGame()).setMoved(takeTurn);
        return takeTurn;
    }

	public OthelloAIMove getOAIM() {
		return OAIM;
	}

	public void setOAIM(OthelloAIMove oAIM) {
		OAIM = oAIM;
	}

	
    public int getBoardDim() {
		return boardDim;
	}
}
