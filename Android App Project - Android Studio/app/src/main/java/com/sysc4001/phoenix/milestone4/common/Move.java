package com.sysc4001.phoenix.milestone4.common;

import java.io.Serializable;

/**
 * Object that will contain a move and will rank that move
 */
public class Move implements Cloneable, Serializable{

    private int row;
    private int column;
    private int worth; // -- for use with move algorithms such as mini-max

    /**
     * Some empty move
     */
    public Move(){
        this.row = -1;
        this.column = -1;
    }

    /**
     * Move with some co-ordinates
     * @param row: co-ordinate
     * @param column: co-ordinate
     */
    public Move(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * Move with some co-ordinates
     * @param row: co-ordinate
     * @param column: co-ordinate
     * @param worth: worth of said move
     */
    public Move(int row, int column, int worth){
        this.row = row;
        this.column = column;
    }

    /*********************************************************************************
     *         Getter Methods for variables and constants defined above
     *********************************************************************************/

    /**
     * Get the internal worth
     * @return worth: the worth
     */
    public int getWorth() {
        return worth;
    }
    /**
     * Get the row of the move
     * @return row: the row
     */
    public int getRow() {
        return row;
    }
    /**
     * Get the column of the move
     * @return column: the column
     */
    public int getColumn() {
        return column;
    }
    /*********************************************************************************
     *          Setter Methods for variables and constants defined above
     *********************************************************************************/

    /**
     * Set the internal worth
     * @param worth: the worth of the move
     */
    public void setWorth(int worth) {
        this.worth = worth;
    }

    /**
     * set current row
     * @param row: some row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * set current column
     * @param column: some column
     */
    public void setColumn(int column) {
        this.column = column;
    }
    
    
    @Override
    public boolean equals(Object aMove)
    {
        if (aMove instanceof Move){
    	    Move otherMove = (Move)aMove;
    	    return (row == otherMove.row && column == otherMove.column);
        }
        return false;
    }
}
