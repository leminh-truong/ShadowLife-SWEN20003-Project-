import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/** This class reads coordinates of actors from a world file
 * (csv file) and returns a hashmap of the type of actor being
 * read and array lists of the actors.
 *
 * Le Minh Truong
 * 1078113
 * SWEN20003
 * Project 1
 *
 * */
public class GameInfo {
    private HashMap<String, ArrayList<Actor>> gameObjects = new HashMap<>();
    static private String [] actors = {"Gatherer", "Thief", "Tree", "Stockpile", "Hoard",
            "GoldenTree","SignUp", "SignDown", "SignLeft", "SignRight",
            "Pool", "Fence", "Pad"};

    /** This is the constructor of the class. It reads the world file and
     * extracts coordinates of actors, then creates and puts new actors into
     * array lists of their corresponding actor types using the extracted coordinates.
     * This constructor also throws exceptions corresponding to the type
     * of errors it finds while reading the world files.
     * */
    public GameInfo(String fileName){
        try(BufferedReader br =
                    new BufferedReader(new FileReader(fileName))){
            String line;
            String[] coordinates;
            int lineNum = 1;

            while((line = br.readLine()) != null){
                coordinates = line.split(",");

                //Check if each csv line has 2 commas and the second two parts are valid numbers
                if((coordinates.length - 1) != 2 || !isNumeric(coordinates[1]) ||
                        !isNumeric(coordinates[2])){
                    throw new InvalidLineException(fileName, lineNum);
                }

                //Check if each csv line has a valid actor type for the first part
                boolean found = false;
                for(String type : actors){
                    if(coordinates[0].equals(type)){
                        found = true;
                        break;
                    }
                }

                if(!found){
                    throw new InvalidLineException(fileName, lineNum);
                }

                /* Check the type of actor in each line of the world file
                * and add the actor's coordinates to an array list of its
                * corresponding actor type in the hashmap.
                * */
                if(coordinates[0].equals("Gatherer")){
                    if(gameObjects.containsKey("Gatherer")){
                        gameObjects.get("Gatherer").add(new Gatherer(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> listGatherer = new ArrayList<>();
                        listGatherer.add(new Gatherer(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Gatherer", listGatherer);
                    }
                }

                if(coordinates[0].equals("Thief")){
                    if(gameObjects.containsKey("Thief")){
                        gameObjects.get("Thief").add(new Thief(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> listThief = new ArrayList<>();
                        listThief.add(new Thief(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Thief", listThief);
                    }
                }

                if(coordinates[0].equals("Tree")){
                    if(gameObjects.containsKey("Tree")){
                        gameObjects.get("Tree").add(new Tree(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> treeList = new ArrayList<>();
                        treeList.add(new Tree(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Tree", treeList);
                    }
                }

                if(coordinates[0].equals("Stockpile")){
                    if(gameObjects.containsKey("Stockpile")){
                        gameObjects.get("Stockpile").add(new Stockpile(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> stockpileList = new ArrayList<>();
                        stockpileList.add(new Stockpile(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Stockpile", stockpileList);
                    }
                }

                if(coordinates[0].equals("Hoard")){
                    if(gameObjects.containsKey("Hoard")){
                        gameObjects.get("Hoard").add(new Hoard(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> hoardList = new ArrayList<>();
                        hoardList.add(new Hoard(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Hoard", hoardList);
                    }
                }

                if(coordinates[0].equals("GoldenTree")){
                    if(gameObjects.containsKey("GoldenTree")){
                        gameObjects.get("GoldenTree").add(new GoldenTree(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> goldList = new ArrayList<>();
                        goldList.add(new GoldenTree(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("GoldenTree", goldList);
                    }
                }

                if(coordinates[0].equals("SignUp")){
                    if(gameObjects.containsKey("Sign")){
                        gameObjects.get("Sign").add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "UP", "res/images/up.png"));
                    }
                    else{
                        ArrayList<Actor> signList = new ArrayList<>();
                        signList.add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "UP", "res/images/up.png"));
                        gameObjects.put("Sign", signList);
                    }
                }

                if(coordinates[0].equals("SignDown")){
                    if(gameObjects.containsKey("Sign")){
                        gameObjects.get("Sign").add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "DOWN", "res/images/down.png"));
                    }
                    else{
                        ArrayList<Actor> signList = new ArrayList<>();
                        signList.add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "DOWN", "res/images/down.png"));
                        gameObjects.put("Sign", signList);
                    }
                }

                if(coordinates[0].equals("SignLeft")){
                    if(gameObjects.containsKey("Sign")){
                        gameObjects.get("Sign").add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "LEFT", "res/images/left.png"));
                    }
                    else{
                        ArrayList<Actor> signList = new ArrayList<>();
                        signList.add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "LEFT", "res/images/left.png"));
                        gameObjects.put("Sign", signList);
                    }
                }

                if(coordinates[0].equals("SignRight")){
                    if(gameObjects.containsKey("Sign")){
                        gameObjects.get("Sign").add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "RIGHT", "res/images/right.png"));
                    }
                    else{
                        ArrayList<Actor> signList = new ArrayList<>();
                        signList.add(new Sign(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2]),
                                "RIGHT", "res/images/right.png"));
                        gameObjects.put("Sign", signList);
                    }
                }

                if(coordinates[0].equals("Pool")){
                    if(gameObjects.containsKey("Pool")){
                        gameObjects.get("Pool").add(new MitosisPool(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> poolList = new ArrayList<>();
                        poolList.add(new MitosisPool(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Pool", poolList);
                    }
                }

                if(coordinates[0].equals("Fence")){
                    if(gameObjects.containsKey("Fence")){
                        gameObjects.get("Fence").add(new Fence(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> fenceList = new ArrayList<>();
                        fenceList.add(new Fence(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Fence", fenceList);
                    }
                }

                if(coordinates[0].equals("Pad")){
                    if(gameObjects.containsKey("Pad")){
                        gameObjects.get("Pad").add(new Pad(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                    }
                    else{
                        ArrayList<Actor> padList = new ArrayList<>();
                        padList.add(new Pad(
                                Double.parseDouble(coordinates[1]),
                                Double.parseDouble(coordinates[2])));
                        gameObjects.put("Pad", padList);
                    }
                }

                lineNum += 1;
            }
        } catch(FileNotFoundException e){
            System.out.println("error: file \"" + fileName + "\" not found");
            System.exit(-1);
        }catch(IOException e){
            System.out.println("error: file \"" + fileName + "\" not found");
            System.exit(-1);
        } catch(InvalidLineException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** This method is used to verify whether a string is a number.
     * @param str This is the parameter to the isNumeric method.
     * @return boolean This returns the verification of whether
     * the string is a number or not.
     */
    private boolean isNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /** This method is used to return the hashmap of actors that is
     * created in the constructor of this class to be used in the
     * main class ShadowLife.
     * @return HashMap<String, ArrayList<Actor>> This method returns
     * the hashmap of actor types and their array lists of actors.
     */
    public HashMap<String, ArrayList<Actor>> getGameObjects(){
        return gameObjects;
    }
}
