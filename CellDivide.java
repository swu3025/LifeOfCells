/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * the purpose of this file is to create a program that provides information
 * when the cell is being divided. It also has multiple functions that relate
 * to the cell and it functionality.
 */

import java.util.List;

/**
 * The purpose of this class is to store/initialize the information of a
 * cell that is being divided, such as its rows, columns, mass, and the
 * direction the cell will divide into while also having functions that
 * relate to the cells functionality.
 */
public class CellDivide extends Cell implements Divisible{

    // variable to store the string representation of the cell being divided
    private static final String CELLDIVIDE_STRING_REPRESENTATION = "+";

    //the condition of the checkApoptosis method
    private static final int CHECKAPOPTOSIS_CONDITION = 3;

    /*the position of the cell in the method GetDivision() based on
    the direction*/
    private static final int DOWN = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

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

    /**
     * The method creates a deep copy of the calling CellDivide object
     *
     * @return a Cell that is the deep copy of the CellDivide object
     */
    public Cell newCellCopy(){
        CellDivide copyOfCell =  new CellDivide(
                this.currRow,this.currCol,this.mass);
        copyOfCell.direction = this.direction;
        return copyOfCell;
    }

    /**
     * retrieves the new position of where the cell is divided from
     * based on the cell's direction.
     *
     * @return the intended position of the spawned cell
     */
    public int[] getDivision(){
        switch (this.direction){
            case DOWN:
                direction = UP;
                return new int[]{this.currRow+1,this.currCol};

            case UP:
                direction = LEFT;
                return new int[]{this.currRow-1,this.currCol};

            case LEFT:
                direction = RIGHT;
                return new int[]{this.currRow,this.currCol-1};

            default:
                direction = DOWN;
                return new int[]{this.currRow+1,this.currCol};

        }
    }
}
