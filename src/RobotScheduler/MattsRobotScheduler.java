package RobotScheduler;

import Belt.Picker;
import Belt.PickerImpl;
import Floor.Point;
import Inventory.Shelf_Manager;
import Ordering.Order;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Set;
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
        createRobots(4);
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
        createRobots(5);
    }

    /**
     * @author Mitziu
     * @author Matthew
     * @param numOfRobots Number of robots to create
     * Creates the number of robots passed as a parameter
     */

    // Currently robots created by this method share the same location at point (0, 0). This may need to be changed later.
    private void createRobots(Integer numOfRobots) {
        for (int i = 0; i < numOfRobots; i++) {
            MattsRobot robot = new MattsRobot(new Point(0,0), i, shelfManager, routeFinder, (PickerImpl) picker);
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
//        int occupied = robots.stream(). //How many robots are at the next location? If n = 0, then move robot.
//                filter(myRobot -> myRobot.getLocation().GetX() == thisRobot.nextLocation().GetX()).
//                filter(myRobot -> myRobot.getLocation().GetY() == thisRobot.nextLocation().GetY()).
//                collect(Collectors.toList()).size();

        Integer locationUsed = robots.stream()
                .filter(robot -> robot != thisRobot)
                .filter(robot -> thisRobot.nextLocation().isEqual(robot.getLocation()))
                .collect(Collectors.toList()).size();

//        Integer locationUsed = 0;
//        for(MattsRobot robot: robots) {
//            if (robot == thisRobot) continue;;
//
//            if (robot.nextLocation() != null) {
//                if (thisRobot.getLocation().GetX() == robot.nextLocation().GetX() &&
//                        thisRobot.getLocation().GetX() == robot.nextLocation().GetY()) locationUsed++;
//            }
//
//        }

        if (locationUsed == 0) thisRobot.move();
    }

    /**
     * @author Matt
     * @author Mitziu
     * Comments added by Wayne <--javadoc should not detail inner workings --Matt
     * @param itemsNeeded Set of items needed
     * Finds shelves that contain item and add to list of shelves needed
     */
    private void getShelvesNeeded (Set<Integer> itemsNeeded) {
        Set<Integer> tempSet = new HashSet<>();
        itemsNeeded.forEach(itemID -> tempSet.add(shelfManager.Contained_In(itemID).get(0)));
        //shelvesNeeded.addAll(tempSet);

        shelvesNeeded.addAll(tempSet.stream()
                .filter(OrderID -> !shelvesNeeded.contains(OrderID))
                .collect(Collectors.toList()));
    }

    /**
     * @author Mitziu
     * @author Matt
     * Updated by Wayne Lei on 12/7/2016
     * @param o
     * @param e
     * Moves robots, etc.
     */
    public void update (Observable o, Object e) {
        Set<Integer> tempItemList = new HashSet<>();

        //Make set of items needed
//        if (!pendingOrders.isEmpty()) {
//            pendingOrders.forEach(myOrder -> tempItemList.addAll(myOrder.getItemIDMap().keySet()));
//        }

        while (!pendingOrders.isEmpty()) {
            tempItemList.addAll(pendingOrders.poll().getItemIDMap().keySet());
        }

        //Make a set of shelves based on items needed
        getShelvesNeeded(tempItemList);

        //Assign idle robots shelves to get
        if (!shelvesNeeded.isEmpty()) {
            robots.stream().
                    filter(myRobot -> myRobot.isIdle()).forEach(myRobot -> {
                    if (!shelvesNeeded.isEmpty()) {
                        myRobot.setShelfID(shelvesNeeded.remove(0));
                        myRobot.setCurrentTask("Goto Shelf");
                    }
            });
        }

        //Set task for robot, TODO: Refactor this!! Use an enum.
        robots.forEach(myRobot -> {
            String task = myRobot.getCurrentTask();

            System.out.println("DEBUG: Robot-" + myRobot.getID() + ", Task: " + task);
            //if (task == null) task = "No task at present"; myRobot.setIdle(true); myRobot.setCurrentTask(task);

            if (task == " ") {
                task = "No task at present";
                myRobot.setIdle(true);
                myRobot.setCurrentTask(task);
            }

            if (task == "Goto Shelf") {
                if (!myRobot.isLoaded() && myRobot.getLocation().isEqual(shelfManager.getShelf(myRobot.getShelfID()).getLocation())) {
                    task = "Deliver Shelf";
                    myRobot.setCurrentTask(task);
                    myRobot.loadShelf(shelfManager.getShelf(myRobot.getShelfID()));
                } else {
                    if (myRobot.pathEmpty())
                        myRobot.setPath(routeFinder.robotToShelf(myRobot.getLocation(), shelfManager.getShelf(myRobot.getShelfID()).getLocation()));
                }
            }

            if (task == "Deliver Shelf") {
                if (myRobot.getLocation().isEqual(picker.getPosition())) {
                    picker.shelfArrived(myRobot.getShelfID());
                    task = "Return Shelf";
                    myRobot.setCurrentTask(task);
                } else {
                    if (myRobot.pathEmpty())
                        myRobot.setPath(routeFinder.shelfToPicker(myRobot.getLocation()));
                }
            }

            if (task == "Return Shelf") {
                if (myRobot.getLocation().isEqual(myRobot.getLoadedShelf().getOriginalLocation())) {
                    myRobot.unloadShelf();
                    task = "Resting Position";
                    myRobot.setCurrentTask(task);
                } else {
                    if (myRobot.pathEmpty())
                        myRobot.setPath(routeFinder.returnShelf(myRobot.getLocation(), myRobot.getLoadedShelf().getOriginalLocation()));
                }
            }

            if (task == "Resting Position") {
                if (myRobot.getLocation().isEqual(myRobot.originalLocation)) {
                    myRobot.setIdle(true);
                    task = "No task at present";
                    myRobot.setCurrentTask(task);
                } else {
                    if (myRobot.pathEmpty())
                        myRobot.setPath(routeFinder.restingPosition(myRobot.getLocation(), myRobot.originalLocation));
                }
            }

        });

        //move robots
        robots.forEach(myRobot -> moveRobot(myRobot));

    }

}
