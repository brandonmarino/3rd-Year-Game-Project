package common;

import Boards.Board;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import PlayerTypes.RandomPlayerType;
import Strategies.Alternative.ObstructPlayerType;
import Strategies.Minimax.MinimaxPlayerType;

import java.util.Scanner;

/**
 * Created by Brandon on 12/8/2014.
 */
public class PlayerRequest {
    /**
     * Prompt user for the player info, names and the player type be-it human or computer
     */
    public PlayerType[] getPlayerInfo(Board boardGame){
        int choice;
        PlayerType[] players = new PlayerType[2];
        for(int playernum = 0; playernum < players.length; playernum++){
            System.out.println("\nPlayers types:\n1: Human\n2: Computer- Random\n3: Computer- MiniMax\n4: Computer- Obstruction");
            for(int i = 0; i < 100; i++){
                try{
                    Scanner user_input  = new Scanner(System.in);
                    System.out.println("Please choose a player type from the options for Player "+ (playernum+1) + " by number:");
                    choice = user_input.nextInt();
                    if (choice == 1)
                        players[playernum] = new HumanPlayerType(boardGame,playernum+1);
                    else if (choice == 2)
                        players[playernum] = new RandomPlayerType(boardGame,playernum+1);
                    else if ( choice == 3 )
                        players[playernum] = new MinimaxPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else if ( choice == 4 )
                        players[playernum] = new ObstructPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else
                        throw new GameInputException("Sorry, that is not an option!"); //This should be changed to a custom Exception!
                    break;
                }catch(GameInputException e){
                    System.out.println(e.getMessage());
                }
            }
            if(players[0] instanceof HumanPlayerType && players[1] instanceof HumanPlayerType) {
                ((HumanPlayerType)players[0]).canUndo(false);
                ((HumanPlayerType)players[1]).canUndo(false);
            }
        }
        return players;
    }
}
