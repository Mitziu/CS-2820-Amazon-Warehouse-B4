package RobotScheduler;
import Belt.Picker;
import Ordering.*;
import java.util.*;
import java.util.stream.Collectors;

//TODO: get out of the way to avoid blocking picker
//TODO: call newOrder method when robot has reached picker; I SHOULDN'T NEED TO, RIGHT? NEW ORDERS ASSIGNED EVERY TICK.
//TODO: figure out which shelf to go to--look at shelf manager, call containedIn method;
//TODO: figure out which shelf a robot should go to via robotScheduler;
//TODO: add Queue of shelveIDs to go to to fulfill order
//TODO: isIdle and isDone redundant?

/**
 * Created by Matt on 12/4/2016.
 * @author Matt
 * @author Mitziu
 */
public class MattsRobotScheduler implements Observer {
    List<MattsRobot> robots;
    Queue<Order> pendingOrders;
    Picker picker;


    /**
     * constructor
     * @author Matt
     * @author Mitziu
     */
    public MattsRobotScheduler (Picker picker) {
        this.picker = picker;
        robots = new LinkedList<MattsRobot>();
        pendingOrders = new LinkedList<Order>();
    }

    /**
     * @author Matt
     * @author Mitziu
     * @param robots
     * constructor Starts with robots and/or orders already
     */
    public MattsRobotScheduler (Picker picker, List<MattsRobot> robots, Queue<Order> pendingOrders) {
        this.picker = picker;
        this.robots = robots;
        this.pendingOrders = pendingOrders;
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
     * @author Mitziu
     * @author Matt
     * @param o
     * @param e
     * Moves robots, etc.
     */
    public void update (Observable o, Object e) {
        //Make list of tasks
        if (!pendingOrders.isEmpty()) {

        }

        //Assign idle robots task to fulfill
        if (!pendingOrders.isEmpty()) {
            robots.stream().
                    filter(myRobot -> myRobot.isIdle()).forEach(myRobot -> {
                    if (!pendingOrders.isEmpty()) {
                        myRobot.setCurrentTask(pendingOrders.poll());
                    }
            });
        }

        //Load or unload, as needed


        //move robots
        robots.forEach(myRobot -> moveRobot(myRobot));

    }

}
