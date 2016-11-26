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
public class Visualizer{
    Map<String, Point> floorMap;

    public Visualizer () {
        System.out.println("Warehouse is up and running!");
    }

    public void updateFloor(Map<String, Point> newMap) {
        floorMap = newMap;
        textOutput();
    }

    private void textOutput() {
        floorMap.forEach((k,v) -> System.out.println("Name: "+k+" Position:"+v));
    }

}
