/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * The purpose of this file is to create a program that's main purpose
 * is to store the conditions of the cells whose moves are either
 * toggled or not. It determines whether the instances of the cell
 * type is alive and stores that information.
 */

import java.util.List;

/**
 * The purpose of this class is to initialize/store information of the
 * cells whose moves are either toggled or not, such as the row, column,
 * mass, and the number of instances of the cell type is alive.
 */
public class CellMoveToggleChild extends CellMoveToggle{

    //The condition of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION = 10;

    //number of instances of the cell type that are alive
    public static int numAlive;


    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellMoveToggleChild(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
        numAlive += 1;
    }

    /**
     * constructor that copies the current row, column, and mass of another
     * cell whose moves are either toggled or not through its parent class,
     * Cell.
     *
     * @param otherCellMoveToggleChild the other cell whose moves are either
     *                                 toggled not
     */
    public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild){
        super(otherCellMoveToggleChild);
        numAlive += 1;
    }

    /**
     * method for when apoptosis happens to a cell, thus reducing the
     * number of cells alive by one
     * @Override overrides the apoptosis method of the parent class, Cell
     */
    public void apoptosis(){
        super.apoptosis();
        numAlive -= 1;
    }

    /**
     * Creates a deep copy of the calling  object
     *
     * @return a deep copy of the calling  object
     */
    public Cell newCellCopy(){
        CellMoveToggleChild copyOfCell = new CellMoveToggleChild(
                this.currRow,this.currCol,this.mass);
        copyOfCell.toggled = this.toggled;
        return copyOfCell;
    }

    /**
     * method checks whether the cell should initiate apoptosis
     * given certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell> neighbors){
        /* Checks whether the conditions in CellMoveToggle's checkApoptosis
        method is satisfied and if the number of cells alive is less than 10*/
        return (super.checkApoptosis(neighbors)) &&
                (numAlive < CHECKAPOPTOSIS_CONDITION);

    }

}
