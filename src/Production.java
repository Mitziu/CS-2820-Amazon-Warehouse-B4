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
 * @author Mitziu
 */
public class Production {

    private static BeltImpl belt;
    private static Clock clock;
    //private static Setup floor;

    private static FloorImpl floor;

    private static Inventory inventory;
    private static Master master;
    private static MattsRobotScheduler robotManager;
    private static Shelf_Manager shelfManager;
    private static OrderingSystem orderingSystem;
    private static PickerImpl picker;
    private static VisualizerRecorder visualizer;

    public static void main(String[] args) {

        clock = new Clock();
        belt = new BeltImpl(25);
//
//        floor = new Setup();
//        floor.Initialize();

        shelfManager = new Shelf_Manager();
        inventory = new Inventory(shelfManager);

        try {
            inventory.Inventory_Initialize("CSVFiles/Inventory_CSV");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        picker = new PickerImpl(belt, shelfManager);

        robotManager = new MattsRobotScheduler(picker, shelfManager);
        //GoldenManager

        orderingSystem = new OrderingSystem(inventory, belt, robotManager, picker);
        //OrderingSystem

        floor = new FloorImpl(shelfManager, robotManager, picker, belt);
        visualizer = new VisualizerRecorder(floor);
        master = new Master(orderingSystem);
        //Master

        clock.addObserver(master);
        clock.addObserver(visualizer);
        clock.addObserver(orderingSystem);
        clock.addObserver(belt);
        clock.addObserver(picker);
        clock.addObserver(robotManager);

        while(orderingSystem.finishedOrders != 7) {
            System.out.println("Finished Orders: " + orderingSystem.finishedOrders);
            clock.tick();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        visualizer.simulationEnded();
    }


}
