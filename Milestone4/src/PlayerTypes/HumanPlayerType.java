package PlayerTypes;

import java.util.Scanner;
import java.util.ArrayList;

import Boards.Board;
import common.Move;
import common.GameInputException;

/***********************************************************************************************************************************************************
 * 							HumanPlayerType Class creates Indexes for Row and Column to be Used in Board Classes
 ***********************************************************************************************************************************************************

 * Milestone 2, Author: Brandon Marino
 *  - Subclass of PlayerTypes
 *  This will print the available moves to the user and then prompt for the user to choose a move
 */
public class HumanPlayerType extends PlayerType {

    private boolean undoFlag;
   /**
     * Create a human player
     * @param playernum the name of the human..
     * @param boardGame the current gamestate
     */

    public HumanPlayerType(Board boardGame, int playernum){
        super(boardGame,playernum);
        System.out.println("Please enter Players "+playernum+"'s name:");
        Scanner user_input = new Scanner(System.in);
        setName(user_input.next() );
        undoFlag = true;
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
    public Move getMove(){
        ArrayList<Move> moves = getAvailableMoves();

        if (moves.isEmpty())   //player cannot move
            return null;

        System.out.println(getName().toUpperCase()+"'S TURN");
        printMoves(moves);
        int choice = getChoice(moves.size());
        return popMove(choice);
    }

    /**
     * Make sure that the user's redoStack is flushed with each move
     * @param availableMoves a list of all of the moves possible on this board by this player
     */
    @Override
    public void setAvailableMoves(ArrayList<Move> availableMoves){
        //store the current state before performing any action
        Board.storeUndo(boardGame);
        Board.dumpRedoStack();
        super.setAvailableMoves(availableMoves);
    }

    /**
     * Will tell the player if they are playing against a computer and therefore can undo/redo
     * @param state if the player can undo
     */
    public void canUndo(boolean state){
        undoFlag = state;
    }

    /**
     * Print the available legal moves and provide a numerical choice for each move
     * @param moves a list of available moves
     */
    private void printMoves(ArrayList<Move> moves){
        System.out.println("Currently Available Moves: ");
        int choice = 0;
        for(Move move: moves){
            choice++;
            System.out.println(choice + ": Row " + (move.getRow()+1) +", Column "+ (move.getColumn()+1) );
        }
        if(undoFlag)
            System.out.println("undo: Undo your last move\nredo: Redo your last undo");
        System.out.println("Please choose one of the options:");
    }

    /**
     * Prompt the user for a choice, if the input is not valid, throw an exception
     * @param options the number of options that the user has
     * @return the option chosen
     */
    private int getChoice(int options){
        int choice = 0;
        while(true){
            try{
                Scanner user_choice = new Scanner(System.in);
                String input = user_choice.next();
                if(undoFlag) {
                    if (input.toLowerCase().contains("undo")) {
                        Board undoBoard = Board.popUndo(boardGame.getCurrentPlayer());
                        if (undoBoard != null) {
                            Board.storeRedo(boardGame);
                            boardGame = undoBoard;
                            System.out.println("Board has been Undone!");
                            boardGame.printBoard();
                            printMoves(getAvailableMoves());
                        }else   throw new GameInputException("Sorry, no undos are available for you!");
                    } else if (input.toLowerCase().contains("redo")) {
                        Board redoBoard = Board.popRedo();
                        if (redoBoard != null) {
                            Board.storeUndo(boardGame);
                            boardGame = redoBoard;
                            System.out.println("Board has been Redone!");
                            boardGame.printBoard();
                            printMoves(getAvailableMoves());
                        }else   throw new GameInputException("Sorry, no redos are available for you!");
                    }
                } else if (input.toLowerCase().contains("undo") || input.toLowerCase().contains("redo"))
                    throw new GameInputException("You cannot undo or redo when two humans are playing!");
                choice = Integer.parseInt(input);
                if (choice <= 0 || choice > options)
                    throw new GameInputException("That choice is invalid, not in range!");
                break;
            }catch(GameInputException e){
                System.out.println(e.getMessage());
                System.out.println("Please choose one of the options:");
            }catch(NumberFormatException e){
                if (!e.getMessage().contains("undo") && !e.getMessage().contains("redo") ) {
                    System.out.println("Please choose one of the options:");
                }
            }
        }
        return choice -1;
    }
}