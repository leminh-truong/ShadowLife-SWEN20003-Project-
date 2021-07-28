import java.lang.Exception;

/** This class represents the exception for when an invalid
 * line is encountered (e.g. invalid actor type, second two
 * parts are not numbers, not enough number of fields) when
 * reading the world file using the GameInfo class.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 1
 *
 * */
public class InvalidLineException extends Exception{

    /** This is the constructor of the class, which changes
     * the return message when an invalid line is encountered
     * while reading the world file with the GameInfo class.
     *
     * @param fileName This is the name of the world file.
     * @param lineNum This is the line number of the invalid
     *                line.
     */
    public InvalidLineException(String fileName, int lineNum){
        super("error: in file \"" + fileName + "\" at line " + lineNum);
    }
}
