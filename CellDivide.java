/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell is being divided. It also has functions that provide
 * information on its string representation and apoptosis
 */

import java.util.List;

/**
 * The purpose of this class is to store/initialize the information of a
 * cell that is being divided, such as its rows, columns, mass, and the
 * direction the cell will divide into while also being able to get the
 * string representation and check the apoptosis of the cell
 */
public class CellDivide extends Cell{

    // variable to store the string representation of the cell being divided
    private static final String CELLDIVIDE_STRING_REPRESENTATION = "+";

    //the condition of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION = 3;

    //the direction the cell will divide to
    public int direction;

    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellDivide(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
        this.direction = 1;
    }

    /**
     * constructor that copies the current row, column,mass, and direction
     * of another cell being divided through its parent class, Cell.
     *
     * @param otherCellDivide the other cell being divided
     */
    public CellDivide(CellDivide otherCellDivide){
        super(otherCellDivide);
        this.direction = otherCellDivide.direction;
    }

    /**
     * method that gets the string representation of the cell being divided
     *
     * @return the string representation of the cell being divided
     */
    public String toString(){
        return CELLDIVIDE_STRING_REPRESENTATION;
    }

    /**
     * method checks whether the cell being divided should initiate apoptosis
     * given certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell being divided
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell> neighbors){

        //checks if the cell has exactly three neighbors
        return neighbors.size() == CHECKAPOPTOSIS_CONDITION;
    }
}
