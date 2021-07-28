import bagel.*;
import java.util.*;

/** This is the main class of Project 2, which utilises
 * other classes to create a game according to the
 * criteria set by Project 2. This class inherits from
 * the abstract class AbstractGame to create and run a
 * simulation with actors obtained from the GameInfo class.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 2
 *
 * */
public class ShadowLife extends AbstractGame {
    private static GameInfo info;
    private static int tickRate;
    private static int maxTick;
    private Image background = new Image("res/images/background.png");
    private static HashMap<String, ArrayList<Actor>> actors;

    private int tickCount = 0;
    private long currTime;
    private long pastTime = System.currentTimeMillis();
    private static final int GAMEWIDTH = 1024;
    private static final int GAMEHEIGHT = 768;


    /** This is the constructor of the main class ShadowLife.
     * This constructor inherits from the abstract class AbstractGame
     * from the 'bagel' library to establish the height and width of
     * the simulation's window.
     * */
    public ShadowLife() {
        super(GAMEWIDTH, GAMEHEIGHT);
    }

    /** This is the main method of this class. It is borrowed from the
     * Java class BagelTest obtained from Workshop 5, and is modified
     * for the purpose of Project 2. This method takes 3 arguments from
     * the standard input - tick rate, max ticks and world file - to
     * initialise the conditions to run the simulation.
     * */
    public static void main(String[] args) {

        /* Check if there are 3 arguments from the standard input and
        * the arguments are valid.
        * */
        if (args.length != 3 || Integer.parseInt(args[0]) < 0
                || Integer.parseInt(args[1]) < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        ShadowLife game = new ShadowLife();
        tickRate = Integer.parseInt(args[0]);
        maxTick = Integer.parseInt(args[1]);
        info = new GameInfo(args[2]);
        actors = info.getGameObjects();
        game.run();
    }


    /** This method updates the simulation window every tick based on
     * the arguments obtained in the main method and the algorithms
     * of the actors obtained from the GameInfo class. This method
     * terminates the simulation when the conditions outlined in
     * the specification of Project 2 are met.
     */
    @Override
    protected void update(Input input) {
        int inactiveGatherer = 0;
        int inactiveThief = 0;

        currTime = System.currentTimeMillis();
        background.drawFromTopLeft(0.0, 0.0);

        /* Check to see if a single tick has passed before updating the
        * simulation. The tick rate is determined by the standard input.
        * */
        if (currTime - pastTime >= tickRate) {

            /* Check to see if the world file includes gatherers before implementing
            * their algorithms as outlined in the specification.
            * */
            if (actors.containsKey("Gatherer")) {

                /* Use an iterator to implement the reaction of every gatherer
                * in the hashmap to other actors based on their algorithms.
                * */
                ListIterator<Actor> gatherer = actors.get("Gatherer").listIterator();
                while (gatherer.hasNext()) {
                    Gatherer aGatherer = (Gatherer)gatherer.next();
                    if (aGatherer.getActive()) {
                        aGatherer.moveGatherer();
                    } else {
                        /* Count the number of inactive gatherers to determine
                        * when to terminate the simulation.
                        * */
                        inactiveGatherer += 1;
                    }

                    /* Check whether mitosis pools exist in the world file,
                    * and implement the algorithm for the gatherer's
                    * reaction when it steps on a mitosis pool.
                    * */
                    if(actors.containsKey("Pool") && aGatherer.getActive()){
                        for (Actor pool : actors.get("Pool")){
                            if(aGatherer.isOverlap(pool.getXPos(), pool.getYPos())){
                                Gatherer gatherer1 = new Gatherer(aGatherer.getXPos(),
                                        aGatherer.getYPos());
                                Gatherer gatherer2 = new Gatherer(aGatherer.getXPos(),
                                        aGatherer.getYPos());

                                gatherer1.setDirections(aGatherer.getDirections());
                                gatherer1.rotateLeft();
                                gatherer1.moveGatherer();

                                gatherer2.setDirections(aGatherer.getDirections());
                                gatherer2.rotateRight();
                                gatherer2.moveGatherer();

                                gatherer.remove();
                                gatherer.add(gatherer1);
                                gatherer.add(gatherer2);
                            }
                        }
                    }

                    /* Check whether fences exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a fence.
                     * */
                    if (actors.containsKey("Fence") && aGatherer.getActive()) {
                        for (Actor fence : actors.get("Fence")) {
                            if (aGatherer.isOverlap(fence.getXPos(), fence.getYPos())) {
                                aGatherer.reactFence();
                                break;
                            }
                        }
                    }

                    /* Check whether signs exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a sign.
                     * */
                    if (actors.containsKey("Sign") && aGatherer.getActive()) {
                        for (Actor sign : actors.get("Sign")) {
                            if (aGatherer.isOverlap(sign.getXPos(), sign.getYPos())) {
                                aGatherer.reactSign((Sign) sign);
                                break;
                            }
                        }
                    }

                    /* Check whether golden trees exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a golden tree.
                     * */
                    if (actors.containsKey("GoldenTree") && aGatherer.getActive()) {
                        for (Actor gold : actors.get("GoldenTree")) {
                            if (aGatherer.isOverlap(gold.getXPos(), gold.getYPos())) {
                                aGatherer.reactGoldenTree();
                                break;
                            }
                        }
                    }

                    /* Check whether trees exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a tree.
                     * */
                    if (actors.containsKey("Tree") && aGatherer.getActive()) {
                        for (Actor tree : actors.get("Tree")) {
                            if (aGatherer.isOverlap(tree.getXPos(), tree.getYPos())) {
                                aGatherer.reactTree((Tree) tree);
                                break;
                            }
                        }
                    }

                    /* Check whether hoards exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a hoard.
                     * */
                    if (actors.containsKey("Hoard") && aGatherer.getActive()) {
                        for (Actor hoard : actors.get("Hoard")) {
                            if (aGatherer.isOverlap(hoard.getXPos(), hoard.getYPos())) {
                                aGatherer.reactHoard((Hoard) hoard);
                                break;
                            }
                        }
                    }

                    /* Check whether stockpiles exist in the world file,
                     * and implement the algorithm for the gatherer's
                     * reaction when it steps on a stockpile.
                     * */
                    if (actors.containsKey("Stockpile") && aGatherer.getActive()) {
                        for (Actor stockpile : actors.get("Stockpile")) {
                            if (aGatherer.isOverlap(stockpile.getXPos(), stockpile.getYPos())) {
                                aGatherer.reactStockpile((Stockpile) stockpile);
                            }
                        }
                    }

                    /* Reset the position of the gatherer in the previous tick
                    * to facilitate the algorithm for a gatherer's reaction to
                    * stepping onto a fence.
                    * */
                    aGatherer.setPrevPos();
                }
            }

            /* Check to see if the world file includes thieves before implementing
             * their algorithms as outlined in the specification.
             * */
            if (actors.containsKey("Thief")) {

                /* Use an iterator to implement the reaction of every thief
                 * in the hashmap to other actors based on their algorithms.
                 * */
                ListIterator<Actor> thief = actors.get("Thief").listIterator();
                while (thief.hasNext()) {
                    Thief aThief = (Thief) thief.next();
                    if (aThief.getActive()) {
                        aThief.moveThief();
                    } else {
                        /* Count the number of inactive thieves to determine
                         * when to terminate the simulation.
                         * */
                        inactiveThief += 1;
                    }

                    /* Check whether mitosis pools exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a mitosis pool.
                     * */
                    if(actors.containsKey("Pool") && aThief.getActive()){
                        for (Actor pool : actors.get("Pool")){
                            if(aThief.isOverlap(pool.getXPos(), pool.getYPos())){
                                Thief thief1 = new Thief(aThief.getXPos(),
                                        aThief.getYPos());
                                Thief thief2 = new Thief(aThief.getXPos(),
                                        aThief.getYPos());

                                thief1.setDirections(aThief.getDirections());
                                thief1.rotateLeft();
                                thief1.moveThief();

                                thief2.setDirections(aThief.getDirections());
                                thief2.rotateRight();
                                thief2.moveThief();

                                thief.remove();
                                thief.add(thief1);
                                thief.add(thief2);
                            }
                        }
                    }

                    /* Check whether fences exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a fence.
                     * */
                    if (actors.containsKey("Fence") && aThief.getActive()) {
                        for (Actor fence : actors.get("Fence")) {
                            if (aThief.isOverlap(fence.getXPos(), fence.getYPos())) {
                                aThief.reactFence();
                                break;
                            }
                        }
                    }

                    /* Check whether signs exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a sign.
                     * */
                    if (actors.containsKey("Sign") && aThief.getActive()) {
                        for (Actor sign : actors.get("Sign")) {
                            if (aThief.isOverlap(sign.getXPos(), sign.getYPos())) {
                                aThief.reactSign((Sign) sign);
                                break;
                            }
                        }
                    }

                    /* Check whether pads exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a pad.
                     * */
                    if (actors.containsKey("Pad") && aThief.getActive()) {
                        for (Actor pad : actors.get("Pad")) {
                            if (aThief.isOverlap(pad.getXPos(), pad.getYPos())) {
                                aThief.reactPad();
                            }
                        }
                    }

                    /* Check whether gatherers exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a gatherer.
                     * */
                    if(actors.containsKey("Gatherer") && aThief.getActive()){
                        for(Actor gatherer : actors.get("Gatherer")){
                            if(aThief.isOverlap(gatherer.getXPos(), gatherer.getYPos())){
                                aThief.reactGatherer();
                                break;
                            }
                        }
                    }

                    /* Check whether golden trees exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a golden tree.
                     * */
                    if (actors.containsKey("GoldenTree") && aThief.getActive()) {
                        for (Actor gold : actors.get("GoldenTree")) {
                            if (aThief.isOverlap(gold.getXPos(), gold.getYPos())) {
                                aThief.reactGoldenTree();
                                break;
                            }
                        }
                    }

                    /* Check whether trees exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a tree.
                     * */
                    if (actors.containsKey("Tree") && aThief.getActive()) {
                        for (Actor tree : actors.get("Tree")) {
                            if (aThief.isOverlap(tree.getXPos(), tree.getYPos())) {
                                aThief.reactTree((Tree) tree);
                                break;
                            }
                        }
                    }

                    /* Check whether hoards exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a hoard.
                     * */
                    if (actors.containsKey("Hoard") && aThief.getActive()) {
                        for (Actor hoard : actors.get("Hoard")) {
                            if (aThief.isOverlap(hoard.getXPos(), hoard.getYPos())) {
                                aThief.reactHoard((Hoard) hoard);
                                break;
                            }
                        }
                    }

                    /* Check whether stockpiles exist in the world file,
                     * and implement the algorithm for the thief's
                     * reaction when it steps on a stockpile.
                     * */
                    if (actors.containsKey("Stockpile") && aThief.getActive()) {
                        for (Actor stockpile : actors.get("Stockpile")) {
                            if (aThief.isOverlap(stockpile.getXPos(), stockpile.getYPos())) {
                                aThief.reactStockpile((Stockpile) stockpile);
                            }
                        }
                    }

                    /* Reset the position of the thief in the previous tick
                     * to facilitate the algorithm for a thief's reaction to
                     * stepping onto a fence.
                     * */
                    aThief.setPrevPos();
                }
            }

            /* Check if the current number of ticks has exceeded the
            * maximum number of ticks given by the standard input.
            * */
            if (tickCount > maxTick) {
                System.out.println("Timed out");
                System.exit(-1);
            }

            /* Check if every actors and thieves have become inactive. If they
            * have, terminate the simulation.
            * */
            if (actors.containsKey("Gatherer") && actors.containsKey("Thief")) {
                if (inactiveGatherer == actors.get("Gatherer").size() &&
                        inactiveThief == actors.get("Thief").size()) {
                    System.out.println(tickCount + " ticks");
                    if (actors.containsKey("Stockpile")) {
                        for (Actor stockpile : actors.get("Stockpile")) {
                            System.out.println(((Stockpile) stockpile).getFruits());
                        }
                    }
                    if (actors.containsKey("Hoard")) {
                        for (Actor hoard : actors.get("Hoard")) {
                            System.out.println(((Hoard) hoard).getFruits());
                        }
                    }
                    Window.close();
                }
            }

            else if(actors.containsKey("Gatherer")){
                if(inactiveGatherer == actors.get("Gatherer").size()){
                    System.out.println(tickCount + " ticks");
                    if(actors.containsKey("Stockpile")){
                        for(Actor stockpile : actors.get("Stockpile")){
                            System.out.println(((Stockpile)stockpile).getFruits());
                        }
                    }
                    if(actors.containsKey("Hoard")){
                        for(Actor hoard : actors.get("Hoard")){
                            System.out.println(((Hoard)hoard).getFruits());
                        }
                    }
                    Window.close();
                }
            }

            else{
                if(inactiveThief == actors.get("Thief").size()){
                    System.out.println(tickCount + " ticks");
                    if(actors.containsKey("Stockpile")){
                        for(Actor stockpile : actors.get("Stockpile")){
                            System.out.println(((Stockpile)stockpile).getFruits());
                        }
                    }
                    if(actors.containsKey("Hoard")){
                        for(Actor hoard : actors.get("Hoard")){
                            System.out.println(((Hoard)hoard).getFruits());
                        }
                    }
                    Window.close();
                }
            }
            tickCount += 1;
            pastTime = currTime;
        }

        /* Draw every actor in the hashmap onto the simulation window
         * other than Gatherers and Thieves.
         */
        for (String name : actors.keySet()) {
           if(!name.equals("Gatherer") && !name.equals("Thief")){
               for (Actor actor : actors.get(name)) {
                   actor.drawActor();
               }
           }
        }

        //Draw all Gatherers and Thieves.
        for (String name : actors.keySet()) {
            if(name.equals("Gatherer") || name.equals("Thief")){
                for (Actor actor : actors.get(name)) {
                    actor.drawActor();
                }
            }
        }
    }
}