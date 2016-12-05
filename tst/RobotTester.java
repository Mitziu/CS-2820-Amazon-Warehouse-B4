import RobotScheduler.*;
import Clock.*;
import Floor.*;
import Inventory.*;
import Visualizer.*;
import Belt.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matt
 * Created by Matt on 12/4/2016.
 * Still a work in progress.
 */
public class RobotTester {
    List<MattsRobot> robots;
    Floor.Point[][] points;
    FloorImpl floor;
    Shelf_Manager shelfManager;
    RobotScheduler robotScheduler;
    PickerImpl picker;
    BeltImpl belt;


    @Before
    public void setup () {
        robots = new LinkedList<>();
        points = new Floor.Point[10][10];
        makePoints();
        shelfManager = new Shelf_Manager();
        belt = new BeltImpl(5);
        picker = new PickerImpl(belt, shelfManager);
        robotScheduler = new MattsRobotScheduler(picker, shelfManager);
        floor = new FloorImpl(shelfManager, robotScheduler, picker, belt);
        robots.add(new MattsRobot(points[1][1], 101, shelfManager, picker));
        robots.add(new MattsRobot(points[1][2], 102, shelfManager, picker));
    }

    @Test
    public void test () {

    }

    private void makePoints () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                points[i][j] = new Floor.Point(i, j);
            }
        }
    }

}
