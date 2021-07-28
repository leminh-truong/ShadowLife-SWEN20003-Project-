import bagel.Image;

/** This class represents the actor "Sign", with each
 * instance representing a single sign of any type.
 * It contains the coordinates of a single sign and
 * a method to draw a single sign of any type onto
 * the simulation window, as well as a method to return
 * the direction the sign is pointing towards.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Sign extends Actor{
    private String direction;
    private Image sign;

    /** This is the constructor of the class. It creates a single
     * sign of any type using the coordinates read from the world file,
     * the direction which the sign points towards and the image file
     * that corresponds to the type of sign.
     * @param x This is the x-coordinate of the sign from the
     *          world file.
     * @param y This is the y-coordinate of the sign from the
     *          world file.
     * @param direction This is the general direction which the sign
     *                  points towards.
     * @param filename This is the name of the image file that
     *                 corresponds to the type of sign being
     *                 instantiated.
     */
    public Sign(double x, double y, String direction, String filename){
        super(x,y);
        this.direction = direction;
        this.sign = new Image(filename);
    }

    /** This method is used to draw a single sign of any
     * type onto the simulation window using the sign's
     * coordinates.
     */
    @Override
    public void drawActor() {
        this.sign.drawFromTopLeft(getXPos(),getYPos());
    }

    /** This method is used to return the direction the sign
     * is pointing towards.
     * @return String This returns the direction the sign is
     * pointing towards.
     */
    public String getDirection(){
        return direction;
    }
}
