import bagel.Font;
import bagel.Image;

/** This class represents the actor "Stockpile", with each
 * instance representing a single stockpile. It contains the
 * coordinates of a single stockpile and a method to draw
 * a single stockpile as well as its current number of fruits
 * onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Stockpile extends Actor{
    private int fruits = 0;
    private static Image stockpile = new Image("res/images/cherries.png");

    /** This is the constructor of the class. It creates a single
     * stockpile using the coordinates read from the world file.
     * @param x This is the x-coordinate of the stockpile from the
     *          world file.
     * @param y This is the y-coordinate of the stockpile from the
     *          world file.
     */
    public Stockpile(double x, double y){
        super(x,y);
    }

    /** This method is used to change a stockpile's current
     * number of fruits whether a gatherer or a thief is
     * giving fruits to or stealing fruits from a stockpile.
     * @param steal This is the parameter that determines
     *              whether a gatherer or a thief is stealing
     *              fruits from or giving fruits to a stockpile.
     */
    public void changeFruits(boolean steal){
        if(steal){
            this.fruits -= 1;
        }
        else{
            this.fruits += 1;
        }
    }


    /** This method is used to draw a single stockpile as
     * well as its current number of fruits onto the
     * simulation window using the stockpile's coordinates
     * and its current number of fruits.
     */
    @Override
    public void drawActor(){
        Font stockpileFruits = new Font("res/VeraMono.ttf", 15);
        stockpile.drawFromTopLeft(getXPos(),getYPos());
        stockpileFruits.drawString(Integer.toString(fruits), getXPos(),getYPos());
    }

    /** This method is used to return the stockpile's current
     * number of fruits.
     * @return int This returns the stockpile's current number
     * of fruits.
     */
    public int getFruits(){return fruits;}
}
