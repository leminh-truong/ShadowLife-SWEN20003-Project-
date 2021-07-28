/** This abstract class is the parent class of all
 * actors in the world file, and it represents an
 * actor regardless of that actor's type.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */

public abstract class Actor {
    private double xPos;
    private double yPos;

    /** This is the constructor of the class. It represents
     * a single actor by containing that actor's coordinates,
     * which are obtained from the world file.
     * @param x This is an actor's x-coordinate from the
     *          world file.
     * @param y This is an actor's y-coordinate from the
     *          world file.
     */
    public Actor(double x, double y) {
        xPos = x;
        yPos = y;
    }

    /** This method is used to draw the actor onto the
     * simulation window using that actor's coordinates
     */
    public abstract void drawActor();

    /** This method is used to set an actor's current
     * x-coordinate to a new desired value.
     * @param x This is the new desired x-coordinate.
     */
    public void setXPos(double x){
        xPos = x;
    }

    /** This method is used to retrieve an actor's current
     * x-coordinate.
     * @return double This returns an actor's current
     * x-coordinate.
     */
    public double getXPos(){
        return xPos;
    }

    /** This method is used to set an actor's current
     * y-coordinate to a new desired value.
     * @param y This is the new desired y-coordinate.
     */
    public void setYPos(double y){
        yPos = y;
    }

    /** This method is used to retrieve an actor's current
     * y-coordinate.
     * @return double This returns an actor's current
     * y-coordinate.
     */
    public double getYPos(){
        return yPos;
    }
}
