/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * The purpose of this file is to create a program that simulates
 * a single cell. this program is able determine the location and
 * mass of the Cell while also containing functions relating to apoptosis.
 */

import java.util.List;

/**
 * the purpose of this class is to store and obtain the information
 * of the Cell, such as the current row, column and mass of the
 * Cell while also having functions to set and check apoptosis.
 */
public abstract class Cell {

    public int currRow;
    public int currCol;
    public int mass;

    /**
     * Constructor to store/initialize the current row, column and mass
     * of the cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public Cell(int currRow, int currCol, int mass){
        this.currRow = Math.max(currRow, 0);
        this.currCol = Math.max(currCol, 0);
        this.mass = Math.max(mass, 0);
    }

    /**
     * Constructor to copy the current row, column, mass of another cell
     * to this current cell.
     * @param otherCell the other cell
     */
    public Cell(Cell otherCell){
        this.currRow = otherCell.currRow;
        this.currCol = otherCell.currCol;
        this.mass = otherCell.mass;
    }


    /**
     * this method is called when apoptosis occurs for the cell,
     * setting its current row, column, and mass to -1
     */
    public void apoptosis(){
        this.currRow = -1;
        this.currCol = -1;
        this.mass = -1;
    }


    /**
     * The method gets the current row of the cell
     * @return the current row of the cell
     */
    public int getCurrRow(){
        return this.currRow;
    }

    /**
     * The method gets the current column of the cell
     * @return the current column
     */
    public int getCurrCol(){
        return this.currCol;
    }

    /**
     * The method gets the mass of the cell
     * @return the mass of the cell
     */
    public int getMass(){
        return this.mass;
    }

    /**
     * The method checks whether the current cell should initiate apoptosis
     * given a list of neighboring cell and certain conditions.
     *
     * @param neighbors the neighbor cells of the current cell
     * @return true if the given conditions are satisfied, otherwise false
     */
    public abstract boolean checkApoptosis(List<Cell> neighbors);
}
