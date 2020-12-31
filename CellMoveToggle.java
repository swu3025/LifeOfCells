/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell's moves are either toggled or not. It also has multiple
 * functions that relate to the cell and it functionality.
 */

import java.util.List;

/**
 * The purpose of this class is to instantiate and store the information
 * of a cell whose moves are either toggled or not while also creating methods
 * that relate to the cell's functionality.
 */
public class CellMoveToggle extends CellMoveUp{

    /* variable to store the string representation of the cell
    whose moves are toggled and untoggled*/
    private static final String
            CELLMOVETOGGLE_STRING_REPRESENTATION_TOGGLED = "T";
    private static final String
            CELLMOVETOGGLE_STRING_REPRESENTATION_NOT_TOGGLED = "t";

    //the conditions of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION_1 = 2;
    private static final int CHECKAPOPTOSIS_CONDITION_2 = 5;

    //if the cell is currently toggled or not
    public boolean toggled;

    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellMoveToggle(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
        this.toggled = true;
    }

    /**
     * constructor that copies the current row, column, and mass of another
     * cell whose moves are either toggled or not through its parent class,
     * Cell.
     *
     * @param otherCellMoveToggle the other cell whose moves are either
     *                            toggled or not
     */
    public CellMoveToggle(CellMoveToggle otherCellMoveToggle){
        super(otherCellMoveToggle);
        this.toggled = otherCellMoveToggle.toggled;
    }

    /**
     * method that gets the string representation of the cell whose moves are
     * toggled or not toggled depending on the boolean variable, toggled.
     *
     * @return the string representation of the cell whose moves are toggled
     * or not toggled
     */
    public String toString(){
        if(toggled){
            return CELLMOVETOGGLE_STRING_REPRESENTATION_TOGGLED;
        }
        else{
            return CELLMOVETOGGLE_STRING_REPRESENTATION_NOT_TOGGLED;
        }
    }

    /**
     * method checks whether the cell whose moves are either toggled or not
     * should initiate apoptosis given certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell whose moves
     *                  are either toggled or not
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell> neighbors){
        /* Checks if the amount of neighbors of the cell is less than 2
        or greater than 5 */
        return neighbors.size() < CHECKAPOPTOSIS_CONDITION_1 ||
                neighbors.size() > CHECKAPOPTOSIS_CONDITION_2;
    }

    /**
     * Creates a deep copy of the calling CellMoveToggle object
     *
     * @return a deep copy of the calling CellMoveToggle object
     */
    public Cell newCellCopy(){
        CellMoveToggle copyOfCell = new CellMoveToggle(
                this.currRow,this.currCol,this.mass);
        copyOfCell.toggled = this.toggled;
        return copyOfCell;
    }

    /**
     * The method retrieves the intended position of the cells that
     * are capable of moving, based on whether the cells are toggled
     * or not.
     *
     * @return an array containing the row and column of the
     * intended position.
     */
    public int[] getMove(){
        //flip the boolean value of toggled
        this.toggled = !this.toggled;

        //if toggled is true
        if(!this.toggled){
            return new int[]{this.currRow-1,this.currCol};
        }
        else{
            return new int[]{this.currRow,this.currCol};
        }
    }
}
