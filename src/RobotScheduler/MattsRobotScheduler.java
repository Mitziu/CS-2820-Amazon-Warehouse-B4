package RobotScheduler;
import Floor.*;
import Ordering.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Matt on 12/4/2016.
 * @author Matt
 */
public class MattsRobotScheduler implements Observer {
    List<MattsRobot> robots;
    Queue<Order> pendingOrders;

    /**
     * constructor
     * @author Matt
     */
    public MattsRobotScheduler () {
        robots = new LinkedList<MattsRobot>();
        pendingOrders = new LinkedList<Order>();
    }

    /**
     * @author Matt
     * @param robots
     * constructor Starts with robots and/or orders already
     */
    public MattsRobotScheduler (List<MattsRobot> robots, Queue<Order> pendingOrders) {
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
     * @author Matt
     * moves robots, preventing collisions
     */
    private void moveRobot (MattsRobot thisRobot) {
        int occupied = robots.stream().
                filter(myRobot -> myRobot.getLocation().GetX() == thisRobot.nextLocation().GetX()).
                filter(myRobot -> myRobot.getLocation().GetY() == thisRobot.nextLocation().GetY()).
                collect(Collectors.toList()).size();

        if (occupied == 0) thisRobot.move();
    }

    /**
     * @author Matt
     * @param o
     * @param e
     * Moves robots, etc.
     */
    public void update (Observable o, Object e) {

        if (!pendingOrders.isEmpty()) { //Assign idle robots orders to fulfill
            robots.stream().filter(myRobot -> myRobot.idle == true).forEach(myRobot -> {
                if (!pendingOrders.isEmpty()) myRobot.currentOrder = pendingOrders.poll();
            });
        }

        robots.forEach(myRobot -> moveRobot(myRobot)); //move robots

    }

}
