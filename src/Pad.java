import bagel.Image;

/** This class represents the actor "Pad", with each
 * instance representing a single pad. It contains the
 * coordinates of a single pad and a method to draw
 * a single pad onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Pad extends Actor{
    private static Image pad = new Image("res/images/pad.png");

    /** This is the constructor of the class. It creates a single
     * pad using the coordinates read from the world file.
     * @param x This is the x-coordinate of the pad from the
     *          world file.
     * @param y This is the y-coordinate of the pad from the
     *          world file.
     */
    public Pad(double x, double y){
        super(x,y);
    }

    /** This method is used to draw a single pad onto
     * the simulation window using the pad's
     * coordinates.
     */
    @Override
    public void drawActor() {
        pad.drawFromTopLeft(getXPos(),getYPos());
    }
}
