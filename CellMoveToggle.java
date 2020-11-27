/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell's moves are either toggled or not. It also has functions
 * that provide information on its string representation and apoptosis
 */

import java.util.List;

/**
 * The purpose of this class is to instantiate and store the information
 * of a cell whose moves are either toggled or not while also creating methods
 * that get the string representation and check the apoptosis of the cell
 */
public class CellMoveToggle extends CellMoveUp{

    /* variable to store the string representation of the cell whose moves are
    toggled and untoggled*/
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
}
