package Visualizer;

import Floor.*;
import java.util.*;

/**
 * Created by Matt on 11/10/2016.
 * @author Matt
 *
 * NOTE: A visualizer using Swing is in progress. The difference between  this
 * and that one is that it will not use text outputs, but will redraw when updateFloor is called.
 */
public class Visualizer {
    Map<String, Point> floorMap;
    LinkedList<HashMap<String, Point>> warehouseHistory;
    boolean done = false;
    int counter = 0;

    public Visualizer () {
        System.out.println("Warehouse is up and running!");
    }

    public void run (LinkedList<HashMap<String, Point>> warehouseHistory) {
        this.warehouseHistory = warehouseHistory;
        while (!done) {
            floorMap = getNextMap();
            textOutput();
        }
    }

    public void updateFloor(Map<String, Point> newMap) {
        floorMap = newMap;
        textOutput();
    }

    /**
     * @author Matt
     * @return the next HashMap in warehouse positions
     */
    private HashMap<String, Point> getNextMap() {
        counter++;
        if (warehouseHistory.size() - 1 >= counter) {
            return warehouseHistory.get(counter - 1);
        }
        else {
            counter--;
            System.out.println("You have reached the end of the simulation!");
            done = true;
            return warehouseHistory.get(counter);
        }

    }

    private void textOutput() {
        floorMap.forEach((k,v) -> System.out.println("Name: "+k+" Position: "+v.GetX() + ", " + v.GetY()));
    }

}
