package Games;

import Boards.TicTacToeBoard;
import Strategies.Alternative.AlternativePlayerType;
import Strategies.Alternative.TicTacToeAlternativePlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import PlayerTypes.PlayerType;
import Strategies.Minimax.TicTacToeMinimaxPlayerType;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 1: Authored by Lina
 *
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Games
 * -removed redundant code
 * -cleaned up some code
 */

public class TicTacToe extends Game{

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }

    /**
     * Play a game of Tic Tac Toe
     */
    public TicTacToe(){
        super(new TicTacToeBoard());
    }

    /**
     * Will specify the specifics of each strategy, avoid specialization in superclass
     */
    protected void getPlayerInfo(){
        super.getPlayerInfo();
        PlayerType[] players = getPlayers();
        for(int i = 0; i< players.length; i++){
            if (players[i] instanceof AlternativePlayerType){
                players[i] = new TicTacToeAlternativePlayerType((AlternativePlayerType)players[i]);
            }
            if (players[i] instanceof MinimaxPlayerType){
                players[i] = new TicTacToeMinimaxPlayerType((MinimaxPlayerType)players[i]);
            }
        }
    }
}