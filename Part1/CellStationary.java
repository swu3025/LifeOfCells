/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell is stationary. It also has functions that provide
 * information on its string representation and apoptosis
 */

import java.util.List;

/**
 * The purpose of this class is be able to store/initialize information
 * about the cell that is stationary, such as the current row, column,
 * and mass while also having methods that provide the string representation
 * and check the apoptosis of the cell.
 */
public class CellStationary extends Cell {

    // variable to store the string representation of the cell in stationary
    private static final String CELLSTATIONARY_STRING_REPRESENTATION = ".";

    //the conditions of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION_1 = 7;
    private static final int CHECKAPOPTOSIS_CONDITON_2 = 3;

    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellStationary(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
    }

    /**
     * constructor that copies the current row, column, and mass of another
     * cell in stationary through its parent class, Cell.
     *
     * @param otherCellStationary the other cell in stationary
     */
    public CellStationary(CellStationary otherCellStationary){
        super(otherCellStationary);
    }

    /**
     * method that gets the string representation of the cell in stationary
     *
     * @return the string representation of the cell in stationary
     */
    public String toString(){
        return CELLSTATIONARY_STRING_REPRESENTATION;
    }

    /**
     * method checks whether the cell in stationary should initiate apoptosis
     * given the certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell in stationary
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell>neighbors){

        /* checks if the amount of neighbors the cell in stationary has is
        less than or equal to 7 and bigger than or equal to 3 */
        return neighbors.size() <= CHECKAPOPTOSIS_CONDITION_1 &&
                neighbors.size() >= CHECKAPOPTOSIS_CONDITON_2;
    }
}
