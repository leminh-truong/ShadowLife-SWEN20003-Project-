import bagel.Image;

/** This class represents the actor "Gatherer", with each
 * instance representing an individual gatherer. It contains
 * the constructor and the methods of the actor "Gatherer",
 * which are used to instantiate and implement the
 * gatherer's reaction to other actors, respectively.
 * This class inherits from the abstract class Actor.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 */
public class Gatherer extends Actor{
    private double prevX;
    private double prevY;
    private static Image gatherer =  new Image("res/images/gatherer.png");
    private double xDirection = -1;
    private double yDirection = 0;
    private String directions = "LEFT";
    private boolean carrying = false;
    private boolean active = true;

    private static final int PIXEL = 64;
    private static final double POSITIVE = 1.0;
    private static final double NEGATIVE = -1.0;
    private static final double UNCHANGED = 0.0;

    /** This is the constructor of the class. It creates an
     * individual gatherer from the coordinates obtained from
     * the world file.
     * @param x This is the x-coordinate of the gatherer from
     *          the world file
     * @param y This is the y-coordinate of the gatherer from
     *          the world file
     */
    public Gatherer(double x, double y){
        super(x,y);
    }

    /** This method is used to rotate the direction of a gatherer
     * by 90 degrees counterclockwise.
     */
   public void rotateLeft(){
        if(directions.equals("LEFT")) setDirections("DOWN");
        else if(directions.equals("DOWN")) setDirections("RIGHT");
        else if(directions.equals("RIGHT")) setDirections("UP");
        else if(directions.equals("UP")) setDirections("LEFT");
   }

    /** This method is used to rotate the direction of a gatherer
     * by 90 degrees clockwise.
     */
   public void rotateRight(){
        if(directions.equals("LEFT")) setDirections("UP");
        else if(directions.equals("UP")) setDirections("RIGHT");
        else if(directions.equals("RIGHT")) setDirections("DOWN");
        else if(directions.equals("DOWN")) setDirections("LEFT");
   }

    /** This method is used to reset the position of a gatherer
     * in the previous tick.
     */
   public void setPrevPos(){
        prevX = getXPos();
        prevY = getYPos();
   }

    /** This method is used to check whether the gatherer steps
     * on another actor after it moves. The method checks
     * the gatherer's coordinates and the actor's coordinates
     * using coordinates equality.
     * @param xPos The x-coordinate of another actor.
     * @param yPos The y-coordinate of another actor.
     * @return boolean This returns the state of whether the
     * gatherer is stepping on the other actor or not.
     */
   public boolean isOverlap(double xPos, double yPos){
        if(this.getXPos() == xPos & this.getYPos() == yPos){
            return true;
        }
        return false;
   }

    /** This method is used to move the gatherer one tile
     * towards its current direction.
     */
    public void moveGatherer(){
        double newX = getXPos() + xDirection * PIXEL;
        double newY = getYPos() + yDirection * PIXEL;
        setXPos(newX);
        setYPos(newY);
    }

    /** This method is used to set the gatherer's current active
     * state to the desired active state.
     * @param active This is the desired active state.
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /** This method is used to set the gatherer's current carrying
     * state to the desired carrying state.
     * @param carrying This is the desired active state.
     */
    public void setCarrying(boolean carrying){
        this.carrying = carrying;
    }

    /** This method is used to draw a gatherer onto the window of the
     * simulation using the gatherer's coordinates.
     */
    @Override
    public void drawActor(){ gatherer.drawFromTopLeft(getXPos(), getYPos());}

    /** This method implements the gatherer's reaction to an actor
     * "Fence" when it steps on a fence, based on the algorithm
     * outlined in the specification.
     */
    public void reactFence(){
        setActive(false);
        setXPos(prevX);
        setYPos(prevY);
    }

    /** This method implements the gatherer's reaction to an actor
     * "Sign" when it steps on a sign, based on the algorithm
     * outlined in the specification.
     * @param sign This is the actor "Sign"
     */
    public void reactSign(Sign sign){
        setDirections(sign.getDirection());
    }

    /** This method implements the gatherer's reaction to an actor
     * "GoldenTree" when it steps on a golden tree, based on the
     * algorithm outlined in the specification.
     */
    public void reactGoldenTree(){
        if(!carrying){
            setCarrying(true);
            rotateLeft();
            rotateLeft();
        }
    }

    /** This method implements the gatherer's reaction to an actor
     * "Tree" when it steps on a tree, based on the algorithm
     * outlined in the specification.
     * @param tree This is the actor "Tree"
     */
    public void reactTree(Tree tree){
        if(!carrying){
            if(tree.getFruits() >= 1){
                tree.changeFruits(true);
                setCarrying(true);
                rotateLeft();
                rotateLeft();
            }
        }
    }

    /** This method implements the gatherer's reaction to an actor
     * "Hoard" when it steps on a hoard, based on the algorithm
     * outlined in the specification.
     * @param hoard This is the actor "Hoard"
     */
    public void reactHoard(Hoard hoard) {
        if (carrying) {
            setCarrying(false);
            hoard.changeFruits(false);
        }
        rotateLeft();
        rotateLeft();
    }

    /** This method implements the gatherer's reaction to an actor
     * "Stockpile" when it steps on a stockpile, based on the
     * algorithm outlined in the specification.
     * @param stockpile This is the actor "Stockpile"
     */
    public void reactStockpile(Stockpile stockpile){
        if (carrying) {
            setCarrying(false);
            stockpile.changeFruits(false);
        }
        rotateLeft();
        rotateLeft();
    }


    /** This method sets the x-direction so the gatherer can move
     * towards the desired direction on the simulation when the
     * method moveGatherer() is called.
     * @param direction This is the desired x - direction.
     */
    private void setXDirection(double direction){
        this.xDirection = direction;
    }

    /** This method sets the y-direction so the gatherer can move
     * towards the desired direction on the simulation when the
     * method moveGatherer() is called.
     * @param direction This is the desired y - direction.
     */
    private void setYDirection(double direction){
        this.yDirection = direction;
    }

    /** This method sets the general direction of the gatherer
     * so the gatherer can move towards the desired direction
     * on the simulation when the method moveGatherer() is called.
     * @param direction This is the desired general direction.
     */
    public void setDirections(String direction){
        this.directions = direction;

        // Move the gatherer to the left
        if(direction.equals("LEFT")){
            setXDirection(NEGATIVE);
            setYDirection(UNCHANGED);
        }

        // Move the gatherer to the right
        else if(direction.equals("RIGHT")){
            setXDirection(POSITIVE);
            setYDirection(UNCHANGED);
        }

        // Move the gatherer upwards
        else if(direction.equals("UP")){
            setXDirection(UNCHANGED);
            setYDirection(NEGATIVE);
        }

        // Move the gatherer downwards
        else if(direction.equals("DOWN")){
            setXDirection(UNCHANGED);
            setYDirection(POSITIVE);
        }
    }

    /** This method returns the current general direction
     * of a gatherer.
     * @return String This returns the gatherer's general
     * direction.
     */
    public String getDirections(){return directions;}

    /** This method returns the current active state
     * of a gatherer.
     * @return boolean This returns the gatherer's active
     * state.
     */
    public boolean getActive(){
        return active;
    }
}
