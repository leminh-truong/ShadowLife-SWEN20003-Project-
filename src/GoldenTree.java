import bagel.Image;

/** This class represents the actor "GoldenTree", with each
 * instance representing a single golden tree. It contains the
 * coordinates of a single golden tree and a method to draw
 * a single golden tree onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class GoldenTree extends Actor{
    private static Image goldTree = new Image("res/images/gold-tree.png");

    /** This is the constructor of the class. It creates a single
     * golden tree using the coordinates read from the world file.
     * @param x This is the x-coordinate of the golden tree from the
     *          world file.
     * @param y This is the y-coordinate of the golden tree from the
     *          world file.
     */
    public GoldenTree(double x, double y){
        super(x,y);
    }

    /** This method is used to draw a single golden tree
     * onto the simulation window using the golden tree's
     * coordinates.
     */
    @Override
    public void drawActor() {
        goldTree.drawFromTopLeft(getXPos(),getYPos());
    }
}
