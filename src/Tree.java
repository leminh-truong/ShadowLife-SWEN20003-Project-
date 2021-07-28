import bagel.Image;
import bagel.Font;

/** This class represents the actor "Tree", with each
 * instance representing a single tree. It contains the
 * coordinates of a single tree and a method to draw
 * a single tree as well as its current number of fruits
 * onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Tree extends Actor{
    private int fruits = 3;
    private static Image tree = new Image("res/images/tree.png");

    /** This is the constructor of the class. It creates a single
     * tree using the coordinates read from the world file.
     * @param x This is the x-coordinate of the tree from the
     *          world file.
     * @param y This is the y-coordinate of the tree from the
     *          world file.
     */
    public Tree(double x, double y){
        super(x,y);
    }

    /** This method is used to change a tree's current
     * number of fruits when a gatherer or a thief is
     * stealing fruits from a tree.
     * @param steal This is the parameter that determines
     *              whether a gatherer or a thief is stealing
     *              fruits from a tree.
     */
    public void changeFruits(boolean steal){
        if(steal){
            this.fruits -= 1;
        }
    }

    /** This method is used to draw a single tree as
     * well as its current number of fruits onto the
     * simulation window using the tree's coordinates
     * and its current number of fruits.
     */
    @Override
    public void drawActor(){
        Font treeFruits = new Font("res/VeraMono.ttf", 15);
        tree.drawFromTopLeft(getXPos(),getYPos());
        treeFruits.drawString(Integer.toString(fruits), getXPos(), getYPos());
    }

    /** This method is used to return the tree's current
     * number of fruits.
     * @return int This returns the tree's current number
     * of fruits.
     */
    public int getFruits(){return fruits;}
}
