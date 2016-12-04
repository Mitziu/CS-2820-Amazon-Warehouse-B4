package RobotScheduler;

import Ordering.Order;

import java.util.List;
import java.util.Queue;

/**
 * Created by Mitziu on 12/4/16.
 */
public interface RobotScheduler {

    public List<MattsRobot> listRobots();
    public void takeOrder (Order newOrder);
    public void addRobot(MattsRobot newRobot);

}
