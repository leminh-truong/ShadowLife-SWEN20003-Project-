import bagel.Image;

/** This class represents the actor "Thief", with each
 * instance representing an individual thief. It contains
 * the constructor and the methods of the actor "Thief",
 * which are used to instantiate and implement the
 * thief's reaction to other actors, respectively.
 * This class inherits from the abstract class Actor.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Thief extends Actor{
    private double prevX;
    private double prevY;
    private static Image thief =  new Image("res/images/thief.png");
    private double xDirection = 0;
    private double yDirection = -1;
    private String directions = "UP";
    private boolean carrying = false;
    private boolean active = true;
    private boolean consuming = false;

    private static final int PIXEL = 64;
    private static final double POSITIVE = 1.0;
    private static final double NEGATIVE = -1.0;
    private static final double UNCHANGED = 0.0;

    /** This is the constructor of the class. It creates an
     * individual thief from the coordinates obtained from
     * the world file.
     * @param x This is the x-coordinate of the thief from
     *          the world file
     * @param y This is the y-coordinate of the thief from
     *          the world file
     */
    public Thief(double x, double y){
        super(x,y);
    }

    /** This method is used to rotate the direction of a thief
     * by 90 degrees counterclockwise.
     */
    public void rotateLeft(){
        if(directions.equals("LEFT")) setDirections("DOWN");
        else if(directions.equals("DOWN")) setDirections("RIGHT");
        else if(directions.equals("RIGHT")) setDirections("UP");
        else if(directions.equals("UP")) setDirections("LEFT");
    }

    /** This method is used to rotate the direction of a thief
     * by 90 degrees clockwise.
     */
    public void rotateRight(){
        if(directions.equals("LEFT")) setDirections("UP");
        else if(directions.equals("UP")) setDirections("RIGHT");
        else if(directions.equals("RIGHT")) setDirections("DOWN");
        else if(directions.equals("DOWN")) setDirections("LEFT");
    }

    /** This method is used to reset the position of a thief
     * in the previous tick.
     */
    public void setPrevPos(){
        prevX = getXPos();
        prevY = getYPos();
    }

    /** This method is used to check whether the thief steps
     * on another actor after it moves. The method checks
     * the thief's coordinates and the actor's coordinates
     * using coordinates equality.
     * @param xPos The x-coordinate of another actor.
     * @param yPos The y-coordinate of another actor.
     * @return boolean This returns the state of whether the
     * thief is stepping on the other actor or not.
     */
    public boolean isOverlap(double xPos, double yPos){
        if(this.getXPos() == xPos & this.getYPos() == yPos){
            return true;
        }
        return false;
    }

    /** This method is used to move the thief one tile
     * towards its current direction.
     */
    public void moveThief(){
        double newX = getXPos() + xDirection * PIXEL;
        double newY = getYPos() + yDirection * PIXEL;
        setXPos(newX);
        setYPos(newY);
    }

    /** This method is used to set the thief's current active
     * state to the desired active state.
     * @param active This is the desired active state.
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /** This method is used to set the thief's current carrying
     * state to the desired carrying state.
     * @param carrying This is the desired active state.
     */
    public void setCarrying(boolean carrying){
        this.carrying = carrying;
    }

    /** This method is used to set the thief's current consuming
     * state to the desired consuming state.
     * @param consuming This is the desired active state.
     */
    public void setConsuming(boolean consuming){this.consuming = consuming;}

    /** This method is used to draw a thief onto the window of the
     * simulation using the thief's coordinates.
     */
    @Override
    public void drawActor(){ thief.drawFromTopLeft(getXPos(), getYPos());}

    /** This method implements the thief's reaction to an actor
     * "Fence" when it steps on a fence, based on the algorithm
     * outlined in the specification.
     */
    public void reactFence(){
        setActive(false);
        setXPos(prevX);
        setYPos(prevY);
    }

    /** This method implements the thief's reaction to an actor
     * "Sign" when it steps on a sign, based on the algorithm
     * outlined in the specification.
     * @param sign This is the actor "Sign"
     */
    public void reactSign(Sign sign){
        setDirections(sign.getDirection());
    }

    /** This method implements the thief's reaction to an actor
     * "Pad" when it steps on a pad, based on the algorithm
     * outlined in the specification.
     */
    public void reactPad(){
        setConsuming(true);
    }

    /** This method implements the thief's reaction to an actor
     * "Gatherer" when it steps on a gatherer, based on the
     * algorithm outlined in the specification.
     */
    public void reactGatherer(){
        rotateRight();
        rotateRight();
        rotateRight();
    }

    /** This method implements the thief's reaction to an actor
     * "GoldenTree" when it steps on a golden tree, based on the
     * algorithm outlined in the specification.
     */
    public void reactGoldenTree(){
        if(!carrying){
            setCarrying(true);
        }
    }

    /** This method implements the thief's reaction to an actor
     * "Tree" when it steps on a tree, based on the algorithm
     * outlined in the specification.
     * @param tree This is the actor "Tree"
     */
    public void reactTree(Tree tree){
        if(!carrying){
            if(tree.getFruits() >= 1){
                tree.changeFruits(true);
                setCarrying(true);
            }
        }
    }

    /** This method implements the thief's reaction to an actor
     * "Hoard" when it steps on a hoard, based on the algorithm
     * outlined in the specification.
     * @param hoard This is the actor "Hoard"
     */
    public void reactHoard(Hoard hoard) {
        if(consuming){
            setConsuming(false);
            if(!carrying){
                if(hoard.getFruits() >= 1){
                    setCarrying(true);
                    hoard.changeFruits(true);
                }
                else{
                    rotateRight();
                }
            }
        }
        else if(carrying){
            setCarrying(false);
            hoard.changeFruits(false);
            rotateRight();
        }
    }

    /** This method implements the thief's reaction to an actor
     * "Stockpile" when it steps on a stockpile, based on the
     * algorithm outlined in the specification.
     * @param stockpile This is the actor "Stockpile"
     */
    public void reactStockpile(Stockpile stockpile){
        if(!carrying){
            if(stockpile.getFruits() >= 1){
                setCarrying(true);
                setConsuming(false);
                stockpile.changeFruits(true);
                rotateRight();
            }
        }
        else{
            rotateRight();
        }
    }

    /** This method sets the x-direction so the thief can move
     * towards the desired direction on the simulation when the
     * method moveThief() is called.
     * @param direction This is the desired x - direction.
     */
    private void setXDirection(double direction){
        this.xDirection = direction;
    }

    /** This method sets the y-direction so the thief can move
     * towards the desired direction on the simulation when the
     * method moveThief() is called.
     * @param direction This is the desired y - direction.
     */
    private void setYDirection(double direction){
        this.yDirection = direction;
    }

    /** This method sets the general direction of the thief
     * so the thief can move towards the desired direction
     * on the simulation when the method moveThief() is called.
     * @param direction This is the desired general direction.
     */
    public void setDirections(String direction){
        this.directions = direction;

        // Move the thief to the left
        if(direction.equals("LEFT")){
            setXDirection(NEGATIVE);
            setYDirection(UNCHANGED);
        }

        // Move the thief to the right
        else if(direction.equals("RIGHT")){
            setXDirection(POSITIVE);
            setYDirection(UNCHANGED);
        }

        // Move the thief upwards
        else if(direction.equals("UP")){
            setXDirection(UNCHANGED);
            setYDirection(NEGATIVE);
        }

        // Move the thief downwards
        else if(direction.equals("DOWN")){
            setXDirection(UNCHANGED);
            setYDirection(POSITIVE);
        }
    }

    /** This method returns the current general direction
     * of a thief.
     * @return String This returns the thief's general
     * direction.
     */
    public String getDirections(){return directions;}

    /** This method returns the current active state
     * of a thief.
     * @return boolean This returns the thief's active
     * state.
     */
    public boolean getActive(){
        return active;
    }
}
