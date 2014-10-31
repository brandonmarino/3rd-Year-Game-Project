package Moves;

import java.util.Scanner;
import java.util.ArrayList;

/***********************************************************************************************************************************************************
 * 							PlayerMove Class creates Indexes for Row and Column to be Used in Board Classes
 ***********************************************************************************************************************************************************

 * Milestone 2, Author: Brandon Marino
 *  - Subclass of Move
 *  This will print the available moves to the user and then prompt for the user to choose a move
 */
public class PlayerMove extends Move{

    public Integer[] getMove(){
        ArrayList<Integer[]> moves = getAvailableMoves();
        int choice = 0;
        while(true){
            //loop until some valid move is picked by the user
            //once one has been chosen, an exception won't be thrown by getChoice() and the loop will be broken
            try{
                printMoves(moves);
                choice = getChoice(moves.size());
                break;
            }catch(Exception e){
                System.out.println("\nTHAT WAS NOT A VALID OPTION! Try again!");
            }
        }
        return popMove(choice);
    }
    private void printMoves(ArrayList<Integer[]> moves){
        System.out.println("Currently Available Moves: ");
        int choice = 0;
        for(Integer[] move: moves){
            choice++;
        System.out.println(choice + ": Row " + move[0]+1 +", Column "+ move[1]+1);
    }
}
    private int getChoice(int options) throws Exception{
        System.out.println("Please choose one of the options above by number:");
        int choice = 0;
        Scanner user_choice = new Scanner(System.in);
            String input = user_choice.next();
            choice = Integer.parseInt(input);
            if (choice > options)
                throw new Exception();
        return choice -1;
    }
}
