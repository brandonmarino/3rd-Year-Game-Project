package Games;

import Boards.OthelloBoard;
import PlayerTypes.PlayerType;
import Strategies.Alternative.AlternativePlayerType;
import Strategies.Alternative.OthelloAlternativePlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import Strategies.Minimax.OthelloMinimaxPlayerType;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 1: A Game of Othello, adapted from code for tic tac toe provided by Lina
 *
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Games
 * -removed redundant code
 * -fixed glitches
 * -cleaned up some code
 */
public class Othello extends Game
{
    /**
     * Main run function
     */
    public static void main(String[] args) {
        Othello game = new Othello();
        game.play();
    }

    /**
     * Play a game of othello
     */
    public Othello(){
        super(new OthelloBoard());
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move){
        boolean takeTurn = super.takeTurn(move);
        ((OthelloBoard)boardGame).setMoved(takeTurn);
        return takeTurn;
    }
    /**
     * Will specify the specifics of each strategy, avoid specialization in superclass
     */
    protected void getPlayerInfo(){
        super.getPlayerInfo();
        PlayerType[] players = getPlayers();
        for(int i = 0; i< players.length; i++){
            if (players[i] instanceof AlternativePlayerType){
                players[i] = new OthelloAlternativePlayerType((AlternativePlayerType)players[i]);
            }
            if (players[i] instanceof MinimaxPlayerType){
                players[i] = new OthelloMinimaxPlayerType((MinimaxPlayerType)players[i]);
            }
        }
    }
}