/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * The purpose of this file is to create a program that simulates a board
 * that contains all of the cells. The location of these cells are determined
 * by a 2 x 2 array board that is given to program.
 */

/**
 * The purpose of this class is to create a board for the game that contains
 * all of the cells that is determined by a board provided.
 */
public class PetriDish {

    private static final String EMPTY_SPACE = "null";
    private static final String SPACE = " ";
    private static final String CELLSTATIONARY = "CellStationary";
    private static final String CELLMOVEUP = "CellMoveUp";
    private static final String CELLDIVIDE = "CellDivide";
    private static final String CELLMOVETOGGLE = "CellMoveToggle";
    private static final String CELLMOVEDIAGONAL = "CellMoveDiagonal";

    //The board/dish that will be storing all the cells
    public Cell[][] dish;

    /**
     * Constructor to store all of the reference to the objects of the
     * cell type into a 2D array called dish. The location and type of
     * cell is given by the input 2D array board.
     *
     * @param board the board that holds the type and location of the cells
     */
    public PetriDish(String[][] board){

        this.dish = new Cell[board.length][board[0].length];

        /* go through each row and column of the board, and if it is not null
        check which type of cell it corresponds to and create an object
        with the given mass and add the reference into the 2D array. */
        for(int row = 0; row<board.length; row++){
            for(int col = 0; col<board[row].length; col++){

                //check if the location does not equal to "null"
                if(!board[row][col].equals(EMPTY_SPACE)){

                    /* Separate the String of board[row][col] into
                    a String and an int to obtain the cell type and
                    its mass*/
                    String[] aliveCell = board[row][col].split(SPACE);
                    String cellType = aliveCell[0];
                    int mass = Integer.parseInt(aliveCell[1]);

                    /* checks and initializes the type of cell at the
                    location */
                    switch (cellType) {
                        case CELLSTATIONARY:
                            this.dish[row][col] = new CellStationary
                                    (row, col, mass);
                            break;
                        case CELLMOVEUP:
                            this.dish[row][col] = new CellMoveUp
                                    (row, col, mass);
                            break;
                        case CELLDIVIDE:
                            this.dish[row][col] = new CellDivide
                                    (row, col, mass);
                            break;
                        case CELLMOVETOGGLE:
                            this.dish[row][col] = new CellMoveToggle
                                    (row, col, mass);
                            break;
                        case CELLMOVEDIAGONAL:
                            this.dish[row][col] = new CellMoveDiagonal
                                    (row, col, mass);
                            break;
                        default:
                            (this.dish)[row][col] = new CellMoveToggleChild
                                    (row, col, mass);
                    }
                }
            }
        }
    }




}
