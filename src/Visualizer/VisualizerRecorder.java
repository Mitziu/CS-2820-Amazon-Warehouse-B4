package Visualizer;

import java.util.*;
import Floor.*;

/**
 * Created by Matt on 11/14/2016.
 * @author Matt McCan
 *
 * README: To use this, create a new VisualizerRecorder, with initial FloorPositions, which can be empty if you like.
 * Once you've created this object, add it as a subscriber to the clock.
 * Finally, once the simulation is over, call simulationEnded() to display the visualizer.
 * Note: visualizer is text only here, and prints to the console.
 */
public class VisualizerRecorder implements Observer {
    private Visualizer visualizer;
    private FloorPositions floorPositions;
    private List warehouseHistory;
    private boolean done = false;

    /**
     * @author Matt
     */
    public VisualizerRecorder(FloorPositions floorPositions) {
        this.floorPositions = floorPositions;
        visualizer = new Visualizer();
        warehouseHistory = new LinkedList<HashMap<String, Point>>();
    }

    /**
     * @author Matt
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        //TODO: Get latest map from Floor
        //Map tempMap = new HashMap<>(floorPositions.getAllPositions());
        Map<String, Point> tempMap = floorPositions.getAllPositions();
        displayInformation(tempMap);
        storePositions(tempMap);
    }

    private void displayInformation(Map<String, Point> mapOfFloor) {
        mapOfFloor.entrySet()
                .forEach(entry -> System.out.println("ID: " + entry.getKey() +
                        " Location: (" + entry.getValue().GetX() + " , " + entry.getValue().GetY() + " )"));
    }

    /**
     * @author Matt
     * @param newMap
     * unused at the moment
     */ /*
    public void givePositions (Map<String, Point> newMap) {
        //Currently uses dummy values
        visualizer.updateFloor(newMap);
    }*/

    /**
     * @author Matt
     * @param newMap
     * Stores positions in warehouseHistory
     */
    public void storePositions (Map<String, Point> newMap) {
        if (!done) warehouseHistory.add(newMap);
        else visualizer.run((LinkedList<HashMap<String, Point>>)warehouseHistory);
    }

    public void simulationEnded () {
        done = true;
    }


}
