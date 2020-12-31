/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 *
 * Sources used: None
 *
 * The purpose of this file is to create a program that simulates a board
 * that contains all of the cells and create functions such as moving the
 * objects. The location of these cells are determined by a 2 x 2 array
 * board that is given to program.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to create a board for the game that contains
 * all of the cells that is determined by a board provided while also
 * creating functions that moves and divides that objects and updates the
 * board.
 */
public class PetriDish {

    private static final String EMPTY_SPACE = "null";
    private static final String SPACE = " ";
    private static final String CELLSTATIONARY = "CellStationary";
    private static final String CELLMOVEUP = "CellMoveUp";
    private static final String CELLDIVIDE = "CellDivide";
    private static final String CELLMOVETOGGLE = "CellMoveToggle";
    private static final String CELLMOVEDIAGONAL = "CellMoveDiagonal";

    //The lower and upper conditions of the update method()
    private static final int LOWER_CONDITITION_OF_UPDATE = 2;
    private static final int UPPER_CONDITITION_OF_UPDATE = 3;

    //The lower and upper neighbors of a cell
    private static final int LOWER_NEIHGBORS = -1;
    private static final int UPPER_NEIGHBORS = 2;

    //The board/dish that will be storing all the cells
    public Cell[][] dish;

    //a list of all of the Movable and Divisible objects in the petri dish
    public List<Movable>movables;
    public List<Divisible>divisibles;

    /**
     * Constructor to store all of the reference to the objects of the
     * cell type into a 2D array called dish. It also stores all of the
     * Movable and Divisible objects in their respective spots in the
     * movables and divisibles lists.
     *
     * @param board the board that holds the type and location of the cells
     */
    public PetriDish(String[][] board){

        //initialize the dish, movables, and divisibles
        this.dish = new Cell[board.length][board[0].length];
        this.movables = new ArrayList<>();
        this.divisibles = new ArrayList<>();

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
                    location and adds the cells to movables or divisibles
                     if it is a Movable or Divisible cell*/
                    switch (cellType) {
                        case CELLSTATIONARY:
                            this.dish[row][col] = new CellStationary
                                    (row, col, mass);
                            break;
                        case CELLMOVEUP:
                            this.dish[row][col] = new CellMoveUp
                                    (row, col, mass);
                            movables.add((CellMoveUp)this.dish[row][col]);
                            break;
                        case CELLDIVIDE:
                            this.dish[row][col] = new CellDivide
                                    (row, col, mass);
                            divisibles.add((CellDivide)this.dish[row][col]);
                            break;
                        case CELLMOVETOGGLE:
                            this.dish[row][col] = new CellMoveToggle
                                    (row, col, mass);
                            movables.add((CellMoveToggle)this.dish[row][col]);
                            break;
                        case CELLMOVEDIAGONAL:
                            this.dish[row][col] = new CellMoveDiagonal
                                    (row, col, mass);
                            movables.add((CellMoveDiagonal)this.
                                    dish[row][col]);
                            break;
                        default:
                            (this.dish)[row][col] = new CellMoveToggleChild
                                    (row, col, mass);
                            movables.add((CellMoveToggleChild)this.
                                    dish[row][col]);
                    }
                }
            }
        }
    }

    /**
     * The method finds the amount of neighbors of a given row and
     * column of the petri dish.
     *
     * @param row the row of the petri dish
     * @param col the column of the petri dish
     * @return a list of all the cells that are neighbors to the given row
     * and column
     */
    public List<Cell>getNeighborsOf(int row, int col){
        //check if the row and column are out of bounds
        if(row < 0 || col < 0 || row >= dish.length ||
                col >= dish[0].length){
            return null;
        }

        //list of all the neighbors of the cell at row and col
        List<Cell> neighbors = new ArrayList<>();

        /*iterate through all of the possible neighbor locations of the spot
        and add any neighbors to the list */
        for(int i = LOWER_NEIHGBORS; i < UPPER_NEIGHBORS; i++){
            for(int j = LOWER_NEIHGBORS; j< UPPER_NEIGHBORS; j++){
                //the row and column of the cell's neighbor
                int neighborRow = row + i;
                int neighborCol = col + j;

                if(i == 0 && j == 0){
                    continue;
                }

                //wrapping for any out of bounds neighbors
                if(neighborRow < 0){
                    neighborRow %= dish.length;
                    neighborRow += dish.length;
                }

                else if(neighborRow > dish.length){
                    neighborRow %= dish.length;
                }

                else if(neighborRow == dish.length){
                    neighborRow = 0;
                }

                if(neighborCol < 0){
                    neighborCol %= dish[neighborRow].length;
                    neighborCol += dish[neighborRow].length;
                }
                else if(neighborCol > dish[neighborRow].length){
                    neighborCol %= dish.length;
                }
                else if(neighborCol == dish[neighborRow].length){
                    neighborCol = 0;
                }

                //check if the position is empty
                if(dish[neighborRow][neighborCol] == null){
                    continue;
                }
                neighbors.add(dish[neighborRow][neighborCol]);
            }
        }

        return neighbors;

    }

    /**
     * The method moves all of the Movable cells in the petri dish
     * by calling their getMove() method, which determines their
     * intended position.
     */
    public void move(){

        /* 2d array to store the biggest masses of the cells at each position
        in the petri dish*/
        int [][] biggestMass = new int[dish.length][dish[0].length];

        //list of Movable and Divisible objects that have initiated apoptosis
        List<Movable> apoptosisMovableCells = new ArrayList<>();
        List<Divisible> apoptosisDivisibleCells = new ArrayList<>();



        /*iterates through each of the Movable cells and updates its position
        based on certain conditions*/
        for(Movable movableCell : movables) {
            //if movableCell is alive
            if(((Cell) movableCell).currRow >= 0 &&
                    ((Cell) movableCell).currCol >= 0){
                //create a Cell object of movableCell
                Cell cellObject = dish[((Cell) movableCell).currRow]
                        [((Cell) movableCell).currCol];

                //retrieves the intended position and wraps the row and column
                int[] position = movableCell.getMove();
                int row = position[0];
                int col = position[1];

                /*check that the cell's move position is not the same as the
                position the cell is currently at*/
                if (!(row == cellObject.currRow && col == cellObject.currCol)){
                    //wrapping the row
                    if (row < 0) {
                        row %= dish.length;
                        row += dish.length;
                    } else if (row > dish.length) {
                        row %= dish.length;
                    } else if (row == dish.length) {
                        row = 0;
                    }

                    //wrapping the col
                    if (col < 0) {
                        col %= dish[row].length;
                        col += dish[row].length;
                    } else if (col > dish[row].length) {
                        col %= dish.length;
                    } else if (col == dish[row].length) {
                        col = 0;
                    }

                    if (dish[row][col] != null) {
                        /* if the dish is occupied with a non Movable cell,
                        call the cell's apoptosis method and move the
                        cellObject to that position on the petri dish*/
                        if (!(dish[row][col] instanceof Movable)) {
                            dish[cellObject.currRow][cellObject.currCol] =
                                    null;
                            dish[row][col].apoptosis();
                            cellObject.currRow = row;
                            cellObject.currCol = col;
                            dish[row][col] = cellObject;
                        }
                        // this is if the dish is occupied with a Movable cell
                        else {
                            /* if the cellObject's mass is bigger than that of
                            the cell at dish[row][col], then call the apoptosis
                            method of the cell at dish[row][col] and  move the
                            cellObject to that position on the petri dish*/
                            if (cellObject.compareTo(dish[row][col]) > 0) {
                                dish[cellObject.currRow][cellObject.currCol] =
                                        null;
                                dish[row][col].apoptosis();
                                cellObject.currRow = row;
                                cellObject.currCol = col;
                                dish[row][col] = cellObject;

                                /*set biggestMass[row][col] to cellObject's
                                mass */
                                biggestMass[row][col] = cellObject.mass;
                            }
                            /* if the cellObject's mass is smaller than that of
                            the cell at dish[row][col], then call the apoptosis
                            method of cellObject*/
                            else if (cellObject.compareTo(dish[row][col]) < 0){
                                dish[cellObject.currRow][cellObject.currCol] =
                                        null;
                                cellObject.apoptosis();
                            }
                            /* if the mass of cellObject and the cell at
                            dish[row][col] are equal, call their apoptosis
                            method*/
                            else {
                                /* if their masses are bigger than the current
                                biggest mass of the biggestMass[row][col],then
                                set biggestMass[row][col] to their masses*/
                                if (dish[row][col].mass >
                                        biggestMass[row][col]){
                                    biggestMass[row][col] =
                                            dish[row][col].mass;
                                }

                                dish[row][col].apoptosis();
                                dish[row][col] = null;
                                dish[cellObject.currRow][cellObject.currCol] =
                                        null;
                                cellObject.apoptosis();
                            }
                        }

                    }

                    // if the position on the petri dish is null and empty
                    else {
                        /* if the mass of cellObject is less than the mass at
                        biggest[row][col], then call cellObject's apoptosis
                        method*/
                        if (cellObject.mass < biggestMass[row][col]) {
                            dish[cellObject.currRow][cellObject.currCol] =
                                    null;
                            cellObject.apoptosis();
                        }
                        /*  move the cellObject to that position on the petri
                        dish and set the biggest mass to be the mass of the
                        cellObject*/
                        else {
                            dish[cellObject.currRow][cellObject.currCol] =
                                    null;
                            cellObject.currRow = row;
                            cellObject.currCol = col;
                            dish[row][col] = cellObject;
                            biggestMass[row][col] = cellObject.mass;
                        }
                    }
                }
            }
        }
        /*find all of the Movable objects that have initiated apoptosis
        from the petri dish*/
        for(Movable cells: movables){
            if(((Cell)cells).mass == -1){
                apoptosisMovableCells.add(cells);
            }
        }
        /*remove all of the Movable objects that have initiated apoptosis
        from the petri dish*/
        for(Movable deadCells : apoptosisMovableCells){
            movables.remove(deadCells);
        }
        /*find all of the Divisible objects that have initiated apoptosis
        from the petri dish*/
        for(Divisible cells: divisibles){
            if(((Cell)cells).mass == -1){
                apoptosisDivisibleCells.add(cells);
            }
        }
        /*remove all of the Divisible objects that have initiated apoptosis
        from the petri dish*/
        for(Divisible deadCells : apoptosisDivisibleCells){
            divisibles.remove(deadCells);
        }

    }

    /**
     * Move or "Divide" all of the Divisible cells in the petri dish by
     * calling their getDivision() method to get the intended position
     */
    public void divide(){

        Cell [][] copyOfDish = new Cell[dish.length][dish[0].length];

        //create a copy of the petri dish
        for(int row = 0; row < dish.length; row++){
            for(int col = 0; col < dish[row].length; col++){
                copyOfDish[row][col] = dish[row][col];
            }
        }

        /* 2d array to store the biggest masses of the cells at each position
        in the petri dish*/
        int [][] biggestMass = new int[dish.length][dish[0].length];

        //list of Divisible objects that have initiated apoptosis
        List<Divisible> apoptosisDivisibleCells = new ArrayList<>();

        //list of new Divisible cells to be added to divisible list
        List<Divisible> newDivisibleCells = new ArrayList<>();

        /*iterates through each of the Divisible cells and updates its position
        based on certain conditions*/
        for(Divisible divisibleCell : divisibles){

            //create a Cell object of divisibleCell
            Cell cellObject = dish[((Cell) divisibleCell).currRow]
                    [((Cell) divisibleCell).currCol];

            //retrieves the intended position and wraps the row and column
            int[] position = divisibleCell.getDivision();

            //Create a copy of cellObject
            Cell copyOfCell = cellObject.newCellCopy();

            int row = position[0];
            int col = position[1];

            //wrapping the row
            if (row < 0) {
                row %= dish.length;
                row += dish.length;
            } else if (row > dish.length) {
                row %= dish.length;
            } else if (row == dish.length) {
                row = 0;
            }

            //wrapping the col
            if (col < 0) {
                col %= dish[row].length;
                col += dish[row].length;
            } else if (col > dish[row].length) {
                col %= dish.length;
            } else if (col == dish[row].length) {
                col = 0;
            }

                /* if the position is already occupied before this method call,
                then there will be no division */
            if (copyOfDish[row][col] != null) {
                continue;
            }

            // if the position is occupied during this method call
            if (dish[row][col] != null) {
                /* if the cellObject's mass is bigger than that of the
                the cell at dish[row][col], then call the apoptosis method
                of the cell at dish[row][col] and move the cellObject to that
                position on the petri dish*/
                if (cellObject.compareTo(dish[row][col]) > 0) {
                    dish[row][col].apoptosis();
                    copyOfCell.currRow = row;
                    copyOfCell.currCol = col;
                    dish[row][col] = copyOfCell;

                    //add copyOfCell to divisible list
                    newDivisibleCells.add((Divisible) copyOfCell);

                    //set biggestMass[row][col] to cellObject's masse
                    biggestMass[row][col] = copyOfCell.mass;
                }

                /* if the mass of cellObject and the cell at dish[row][col]
                are equal, call the apoptosis method of the cells at that
                position*/
                else if(cellObject.compareTo(dish[row][col]) == 0) {
                    /* if their masses are bigger than the current biggest
                    mass of the biggestMass[row][col],then set
                    biggestMass[row][col] to their masses
                     */
                    if (dish[row][col].mass > biggestMass[row][col]) {
                        biggestMass[row][col] = dish[row][col].mass;
                    }

                    dish[row][col].apoptosis();
                    dish[row][col] = null;

                }
            }
            // if the position is null and empty during this method call
            else {

                /* if their masses are bigger than the current biggest
                mass of the biggestMass[row][col],then set
                biggestMass[row][col] to their masses and move the cellObject
                to that position on the petri dish*/
                if(cellObject.mass > biggestMass[row][col]){
                    copyOfCell.currRow = row;
                    copyOfCell.currCol = col;
                    dish[row][col] = copyOfCell;
                    biggestMass[row][col] = copyOfCell.mass;

                    //add copyOfCell to divisible list
                    newDivisibleCells.add((Divisible) copyOfCell);
                }
            }
        }

        /*find all of the Divisible objects that have initiated apoptosis
        from the petri dish*/
        for(Divisible cells: divisibles){
            if(((Cell)cells).mass == -1){
                apoptosisDivisibleCells.add(cells);
            }
        }
        /*remove all of the Divisible objects that have initiated apoptosis
        from the petri dish*/
        for(Divisible deadCells : apoptosisDivisibleCells){
            divisibles.remove(deadCells);
        }

        //add all of the new Divisible cells in to divisibles
        divisibles.addAll(newDivisibleCells);
    }

    /**
     * updates the petri dish board by creating new cell for spaces that are
     * acceptable and removing cells that called the apoptosis method
     */
    public void update(){

        Cell [][] copyOfDish = new Cell[dish.length][dish[0].length];

        //create a copy of the petri dish
        for(int row = 0; row < dish.length; row++){
            for(int col = 0; col < dish[row].length; col++){
                copyOfDish[row][col] = dish[row][col];
            }
        }

        // iterate to each position in the petri dish and update it
        for(int row = 0; row < dish.length; row++){
            for(int col = 0; col < dish[row].length; col++){
                Cell cellObject = dish[row][col];

                List<Cell> neighbors = getNeighborsOf(row, col);
                //if the position is an empty space
                if (dish[row][col] == null) {

                    /* if the amount of neighbors around the position is
                    between 2 to 3 then create a deep copy of the first
                    cell at that position in the list attained from
                    getNeighborsOf()*/
                    if (neighbors.size() >= LOWER_CONDITITION_OF_UPDATE &&
                            neighbors.size() <= UPPER_CONDITITION_OF_UPDATE) {
                        Cell newCell = neighbors.get(0).newCellCopy();
                        copyOfDish[row][col] = newCell;
                        newCell.currRow = row;
                        newCell.currCol = col;

                        /*add to divisible list if new cell is of
                        type Divisible*/
                        if(copyOfDish[row][col] instanceof Divisible){
                            divisibles.add((Divisible)copyOfDish[row][col]);
                        }
                        /*add to divisible list if new cell is of
                        type Movable*/
                        else if(copyOfDish[row][col] instanceof Movable){
                            movables.add((Movable)copyOfDish[row][col]);
                        }
                    }
                }
                else if(cellObject.checkApoptosis(neighbors)){
                    if(cellObject instanceof Divisible){
                        divisibles.remove(cellObject);
                    }
                    else if(cellObject instanceof Movable){
                        movables.remove(cellObject);
                    }
                    copyOfDish[row][col] = null;
                }

            }
        }

        //Copy the updated petri dish back to the original dish
        for(int row = 0; row < dish.length; row++){
            for(int col = 0; col < dish[row].length; col++){
                dish[row][col] = copyOfDish[row][col];
            }
        }
    }

    /**
     * simulates one iteration of the petri dish that calls the move, divide,
     * and update method
     */
    public void iterate(){
        move();
        divide();
        update();
    }
}
