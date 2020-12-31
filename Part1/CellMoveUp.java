import java.util.List;

/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell is moving up. It also has multiple functions that relate
 * to the cell and it functionality.
 */

import java.util.List;

/**
 * The purpose of this class is to store/initialize information of a cell
 * that is moving up, such as the row, column, and mass while also containing
 * methods that relate to the cell's functionality.
 */
public class CellMoveUp extends Cell implements Movable{

    // variable to store the string representation of the cell moving up
    private static final String CELLMOVEUP_STRING_REPRESENTATION = "^";

    //the condition of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION = 4;

    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellMoveUp(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
    }

    /**
     * constructor that copies the current row, column, and mass of another
     * cell moving up through its parent class, Cell.
     *
     * @param otherCellMoveUp the other cell moving up
     */
    public CellMoveUp(CellMoveUp otherCellMoveUp){
        super(otherCellMoveUp);
    }

    /**
     * method that gets the string representation of the cell moving up
     *
     * @return the string representation of the cell moving up
     */
    public String toString(){
        return CELLMOVEUP_STRING_REPRESENTATION;
    }

    /**
     * method checks whether the cell moving up should initiate apoptosis
     * given certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell moving up
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell> neighbors){
        //checks if the amount of neighbor of the cell is not exactly four
        return neighbors.size() != CHECKAPOPTOSIS_CONDITION;
    }

    /**
     * The method creates a deep copy of the calling CellMoveUp object.
     *
     * @return A Cell that contains the deep copy of the calling object.
     */
    public Cell newCellCopy(){
        return new CellMoveUp(this.currRow,this.currCol,this.mass);
    }

    /**
     * The method retrieves the intended position of cells that are capable
     * of moving.
     *
     * @return an array containing the row and column of the
     * intended position.
     */
    public int[] getMove(){
        return new int[]{this.currRow-1,this.currCol};
    }
}
