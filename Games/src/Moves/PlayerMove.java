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

    private String name = "";

    /**
     * Create a human player
     * @param name the name of the human..
     */
    public PlayerMove(String name){
        this.name = name;
    }
    /**
     * Get the move the user wants to do
     * Steps:
     *  1 - Print moves to screen
     *  2 - Prompt user for choice
     *  3 - Choice validity check (by exception)
     *  4 - Provide move choice to Board and Game
     * @return the user's choice
     */
    public Integer[] getMove(){
        ArrayList<Integer[]> moves = getAvailableMoves();

        if (moves.isEmpty())   //player cannot move
            return null;

        System.out.println(name.toUpperCase()+"'S TURN");
        printMoves(moves);
        int choice = getChoice(moves.size());
        return popMove(choice);
    }

    /**
     * Print the available legal moves and provide a numerical choice for each move
     * @param moves a list of available moves
     */
    private void printMoves(ArrayList<Integer[]> moves){
        System.out.println("Currently Available Moves: ");
        int choice = 0;
        for(Integer[] move: moves){
            choice++;
        System.out.println(choice + ": Row " + (move[0]+1) +", Column "+ (move[1]+1) );
    }
}

    /**
     * Prompt the user for a choice, if the input is not valid, throw an exception
     * @param options the number of options that the user has
     * @return the option chosen
     * @throws Exception
     */
    private int getChoice(int options){
        int choice = 0;
        while(true){
            try{
                System.out.println("Please choose one of the options by number:");
                Scanner user_choice = new Scanner(System.in);
                String input = user_choice.next();
                choice = Integer.parseInt(input);
                if (choice > options)
                    throw new NumberFormatException();
                break;
            }catch(NumberFormatException e){
                System.out.println("Not a valid option, try again!");
            }
        }
        return choice -1;
    }
}
