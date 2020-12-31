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
 * that implement this file need, which is the method to determine the
 * location where Divisible objects should divide to.
 */
public interface Divisible {

    /**
     * retrieves the new position of where the cell is divided from
     * based on the cell's direction.
     *
     * @return the intended position of the spawned cell
     */
    public abstract int [] getDivision();
}
