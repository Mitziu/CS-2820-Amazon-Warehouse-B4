package RobotScheduler;
import Belt.Picker;
import Belt.PickerImpl;
import Floor.Point;
import Ordering.*;
import Inventory.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Matt on 12/4/2016.
 * @author Matt
 * @author Mitziu
 * @author zuoyuan
 */
public class MattsRobotScheduler implements Observer, RobotScheduler {
    private List<MattsRobot> robots;
    private Shelf_Manager shelfManager;
    private Queue<Order> pendingOrders;
    private Picker picker;
    private List<Integer> shelvesNeeded;
    private RouteFinder routeFinder;

    /**
     * constructor
     * @author Matt
     * @author Mitziu
     */
    public MattsRobotScheduler (PickerImpl picker, Shelf_Manager shelfManager) {
        this.picker = picker;
        this.shelfManager = shelfManager;
        robots = new LinkedList<MattsRobot>();
        pendingOrders = new LinkedList<>();
        shelvesNeeded = new LinkedList<>();
        routeFinder = new RouteFinder(picker);
    }

    /**
     * @author Matt
     * @author Mitziu
     * @param robots
     * constructor Starts with robots and/or orders already
     */
    public MattsRobotScheduler (Picker picker, Shelf_Manager shelfManager, List<MattsRobot> robots, Queue<Order> pendingOrders) {
        this.shelfManager = shelfManager;
        this.picker = picker;
        this.robots = robots;
        this.pendingOrders = pendingOrders;
        shelvesNeeded = new LinkedList<>();
        routeFinder = new RouteFinder((PickerImpl) this.picker);
    }

    private void createRobots(Integer numOfRobots) {
        for (int i = 0; i < numOfRobots; i++) {
            MattsRobot robot = new MattsRobot(new Point(0,0), i, shelfManager, routeFinder);
            addRobot(robot);
        }
    }

    /**
     * @author Matt
     * @param newRobot
     */
    public void addRobot(MattsRobot newRobot) {
        robots.add(newRobot);
    }

    /**
     * @author Matt
     * @param newOrder
     */
    public void takeOrder (Order newOrder) {
        pendingOrders.add(newOrder);
    }

    /**
     * @author Matt
     * @return a list of robots
     */
    public List<MattsRobot> listRobots () {
        return robots;
    }

    /**
     * @author Mitziu
     * @author Matt
     * moves robots, preventing collisions
     */
    private void moveRobot (MattsRobot thisRobot) {
        int occupied = robots.stream(). //How many robots are at the next location? If n = 0, then move robot.
                filter(myRobot -> myRobot.getLocation().GetX() == thisRobot.nextLocation().GetX()).
                filter(myRobot -> myRobot.getLocation().GetY() == thisRobot.nextLocation().GetY()).
                collect(Collectors.toList()).size();

        if (occupied == 0) thisRobot.move();
    }

    /**
     * @author Matt
     * @author Mitziu
     * @param itemsNeeded
     */
    private void getShelvesNeeded (Set<Integer> itemsNeeded) {
        Set<Integer> tempSet = new HashSet<>();
        itemsNeeded.forEach(item -> tempSet.add(shelfManager.Contained_In(item).get(0)));
        shelvesNeeded.addAll(tempSet);
    }

    /**
     * @author Mitziu
     * @author Matt
     * @param o
     * @param e
     * Moves robots, etc.
     */
    public void update (Observable o, Object e) {
        Set<Integer> tempItemList = new HashSet<>();

        //Make set of items needed
        if (!pendingOrders.isEmpty()) {
            pendingOrders.forEach(myOrder -> tempItemList.addAll(myOrder.getItemIDMap().keySet()));
        }

        //Make a set of shelves based on items needed
        getShelvesNeeded(tempItemList);

        //Assign idle robots shelves to get
        if (!shelvesNeeded.isEmpty()) {
            robots.stream().
                    filter(myRobot -> myRobot.isIdle()).forEach(myRobot -> {
                    if (!shelvesNeeded.isEmpty()) {
                        myRobot.setShelfID(shelvesNeeded.remove(0));
                        myRobot.setCurrentTask("Get Shelf");
                    }
            });
        }

        //Set task for robot, TODO: Refactor this!! Use an enum.
        robots.forEach( myRobot -> {
            String task = myRobot.getCurrentTask();

            if (task == "Get Shelf") {
                if (myRobot.hasArrived() && !myRobot.isLoaded()) {
                    myRobot.loadShelf(shelfManager.getShelf(myRobot.getShelfID()));
                }
                else if (myRobot.hasArrived() && myRobot.isLoaded()) {
                    picker.shelfArrived(myRobot.getShelfID());
                    myRobot.setCurrentTask("Return Shelf");
                }
            }
            else if (task == "Return Shelf") {
                if (myRobot.hasArrived() && !myRobot.isIdle()) {
                    myRobot.unloadShelf();
                }
            }
        });

        //move robots
        robots.forEach(myRobot -> moveRobot(myRobot));

    }

}
