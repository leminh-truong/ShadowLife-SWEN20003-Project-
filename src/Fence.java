import bagel.Image;

/** This class represents the actor "Fence", with each
 * instance representing a single fence. It contains the
 * coordinates of a single fence and a method to draw
 * a single fence onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Fence extends Actor{
    private static Image fence = new Image("res/images/fence.png");

    /** This is the constructor of the class. It creates a single
     * fence using the coordinates read from the world file.
     * @param x This is the x-coordinate of the fence from the
     *          world file.
     * @param y This is the y-coordinate of the fence from the
     *          world file.
     */
    public Fence(double x, double y){
        super(x,y);
    }

    /** This method is used to draw a single fence onto
     * the simulation window using the fence's
     * coordinates.
     */
    @Override
    public void drawActor() {
        fence.drawFromTopLeft(getXPos(), getYPos());
    }
}
