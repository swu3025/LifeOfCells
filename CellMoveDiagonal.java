/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 *  * the purpose of this file is to create a program that provides information
 *  * when the cell moves diagonally. It also has functions that provide
 *  * information on its string representation and apoptosis
 */

import java.util.List;

/**
 * The purpose of this class is to instantiate and store information of a cell
 * that is moving diagonally while also containing methods that get the string
 * representation and check the apoptosis of the cell
 */
public class CellMoveDiagonal extends CellMoveUp{

    /* variable to store the string representation of the cell
    moving diagonally if its oriented right or left*/
    private static final String CELLMOVEDIAGONAL_STRING_REPRESENTATION_RIGHT
            = "/";
    private static final String CELLMOVEDIAGONAL_STRING_REPRESENTATION_LEFT
            = "\\";

    // condition for the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION = 3;

    //true if the cell is oriented to the right, false otherwise
    public boolean orientedRight;

    //the number of moves made by the cell
    public int diagonalMoves;

    /**
     * constructor to store/initialize the current row, column and mass
     * through its parent class, Cell.
     *
     * @param currRow the current row of the cell
     * @param currCol the current column of the cell
     * @param mass the mass of the cell
     */
    public CellMoveDiagonal(int currRow, int currCol, int mass){
        super(currRow,currCol,mass);
        this.orientedRight = true;
        this.diagonalMoves = 0;
    }

    /**
     * constructor that copies the current row, column, mass of another
     * cell moving diagonally through its parent class, Cell.
     *
     * @param otherCellMoveDiagonal the other cell moving diagonally
     */
    public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal){
        super(otherCellMoveDiagonal);
        this.orientedRight = otherCellMoveDiagonal.orientedRight;
        this.diagonalMoves= otherCellMoveDiagonal.diagonalMoves;
    }

    /**
     * method that gets the string representation of the cell moving
     * diagonally that is determined by whether the cell is oriented
     * right or not
     *
     * @return the string representation of the cell moving diagonally
     */
    public String toString(){
        if(orientedRight){
            return CELLMOVEDIAGONAL_STRING_REPRESENTATION_RIGHT;
        }
        else{
            return CELLMOVEDIAGONAL_STRING_REPRESENTATION_LEFT;
        }
    }

    /**
     * method checks whether the cell moving diagonally should
     * initiate apoptosis given certain conditions provided.
     *
     * @param neighbors the neighbor cells of the current cell
     *                  moving diagonally
     * @return true if the conditions are met, false otherwise
     */
    public boolean checkApoptosis(List<Cell> neighbors){
        /* checks whether the amount of neighbors of the cell is greater
        than 3 */
        return neighbors.size() > CHECKAPOPTOSIS_CONDITION;
    }
}
