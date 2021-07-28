import bagel.Font;
import bagel.Image;

/** This class represents the actor "Hoard", with each
 * instance representing a single hoard. It contains the
 * coordinates of a single hoard and a method to draw
 * a single hoard as well as its current number of fruits
 * onto the simulation window.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Hoard extends Actor{
    private int fruits = 0;
    private static Image hoard = new Image("res/images/hoard.png");

    /** This is the constructor of the class. It creates a single
     * hoard using the coordinates read from the world file.
     * @param x This is the x-coordinate of the hoard from the
     *          world file.
     * @param y This is the y-coordinate of the hoard from the
     *          world file.
     */
    public Hoard(double x, double y){
        super(x,y);
    }

    /** This method is used to change a hoard's current
     * number of fruits whether a gatherer or a thief is
     * giving fruits to or stealing fruits from a hoard.
     * @param steal This is the parameter that determines
     *              whether a gatherer or a thief is stealing
     *              fruits from or giving fruits to a hoard.
     */
    public void changeFruits(boolean steal){
        if(steal){
            this.fruits -= 1;
        }
        else{
            this.fruits += 1;
        }
    }


    /** This method is used to draw a single hoard as
     * well as its current number of fruits onto the
     * simulation window using the hoard's coordinates
     * and its current number of fruits.
     */
    @Override
    public void drawActor(){
        Font hoardFruits = new Font("res/VeraMono.ttf", 15);
        hoard.drawFromTopLeft(getXPos(),getYPos());
        hoardFruits.drawString(Integer.toString(fruits), getXPos(),getYPos());
    }

    /** This method is used to return the hoard's current
     * number of fruits.
     * @return int This returns the hoard's current number
     * of fruits.
     */
    public int getFruits(){return fruits;}
}
