/**
 * Name: Samuel Wu
 * ID: A16306765
 * EMAIL: saw003@ucsd.edu
 * <p>
 * Sources used: None
 *
 * The purpose of this file is to create a program that implements
 * required methods by other files. These files will be able to
 * implement this file to obtain the required methods in this file.
 */

/**
 * The purpose of this interface is to create a method that all files
 * that implement this file need, which is the the method to determine
 * the location the Movable objects should move to.
 */
public interface Movable {

    /**
     * The method retrieves the intended position of cells that are capable
     * of moving.
     *
     * @return an array containing the row and column of the
     * intended position.
     */
    public abstract int[] getMove();
}
