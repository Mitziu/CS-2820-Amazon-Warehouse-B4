import Belt.*;
import Clock.*;
import Floor.*;
import Inventory.*;
import Master.*;
import Ordering.*;
import RobotScheduler.MattsRobotScheduler;
import Visualizer.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Mitziu on 12/2/16.
 * Class to run simulation of the entire warehouse
 */
public class Production {

    private static BeltImpl belt;
    private static Clock clock;
    private static Setup floor;
    private static Inventory inventory;
    private static Master master;
    private static MattsRobotScheduler robotManager;
    private static Shelf_Manager shelfManager;
    private static OrderingSystem orderingSystem;
    private static PickerImpl picker;
    private static VisualizerRecorder visualizer;

    public static void main(String[] args) {

        clock = new Clock();
        belt = new BeltImpl(10);

        floor = new Setup();
        floor.Initialize();

        shelfManager = new Shelf_Manager();
        inventory = new Inventory(shelfManager);

        try {
            inventory.Inventory_Initialize("CSVFiles/Inventory_CSV", floor);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        picker = new PickerImpl(belt, shelfManager);
        visualizer = new VisualizerRecorder(floor);

        robotManager = new MattsRobotScheduler(picker, shelfManager);
        //GoldenManager

        orderingSystem = new OrderingSystem(inventory, belt, robotManager, picker);
        //OrderingSystem

        master = new Master(orderingSystem);
        //Master

        clock.addObserver(master);
        clock.addObserver(visualizer);
        clock.addObserver(orderingSystem);
        clock.addObserver(belt);
        clock.addObserver(picker);
        clock.addObserver(robotManager);


    }


}
