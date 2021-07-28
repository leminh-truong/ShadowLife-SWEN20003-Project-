import bagel.Image;

/** This class represents the actor "MitosisPool", with each
 * instance representing a single mitosis pool. It contains the
 * coordinates of a single mitosis pool and a method to draw
 * a single mitosis pool onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class MitosisPool extends Actor{
    private static Image pool = new Image("res/images/pool.png");

    /** This is the constructor of the class. It creates a single
     * mitosis pool using the coordinates read from the world file.
     * @param x This is the x-coordinate of the mitosis pool from the
     *          world file.
     * @param y This is the y-coordinate of the mitosis pool from the
     *          world file.
     */
    public MitosisPool(double x, double y){
        super(x,y);
    }

    /** This method is used to draw a single mitosis pool onto
     * the simulation window using the mitosis pool's
     * coordinates.
     */
    @Override
    public void drawActor() {
        pool.drawFromTopLeft(getXPos(),getYPos());
    }
}
