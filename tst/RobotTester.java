//import Belt.*;
//import Clock.*;
//import Floor.*;
//import Inventory.*;
//import Ordering.Order;
//import RobotScheduler.*;
//import Visualizer.*;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//
///**
// * @author Matt
// * Created by Matt on 12/4/2016.
// * Still a work in progress.
// */
//public class RobotTester {
//    List<MattsRobot> robots;
//    Floor.Point[][] points;
//    FloorImpl floor;
//    Shelf_Manager shelfManager;
//    RobotScheduler robotScheduler;
//    Queue<Order> pendingOrders;
//    PickerImpl picker;
//    BeltImpl belt;
//    RouteFinder routeFinder;
//
//    /**
//     * @author Matt
//     * Created by Matt on 12/4/2016
//     * @author Wayne Lei aka Virocider
//     * Updated by Wayne on 12/6/2016
//     * Updated by Wayne on 12/7/2016
//     */
//    @Before
//    public void setup () {
//        robots = new LinkedList<>();
//        points = new Floor.Point[10][10];
//        makePoints();
//        shelfManager = new Shelf_Manager();
//        LinkedList inventory = new LinkedList<Integer>();
//        inventory.add(81820);
//        inventory.add(81821); // Create an inventory of an item with the ID 81820
//        LinkedList quantity = new LinkedList<Integer>();
//        quantity.add(10);
//        quantity.add(20); // Initialize the quantity of the item to be 10
//        belt = new BeltImpl(5);
//        picker = new PickerImpl(belt, shelfManager);
//        robotScheduler = new MattsRobotScheduler(picker, shelfManager, robots, pendingOrders);
//        floor = new FloorImpl(shelfManager, robotScheduler, picker, belt);
//        routeFinder = new RouteFinder(picker);
//        robots.add(new MattsRobot(new Point(1, 1), 101, shelfManager, routeFinder));
//        robots.add(new MattsRobot(new Point(1, 2), 102, shelfManager, routeFinder));
//    }
//
//    /**
//     * @author Wayne Lei aka Virocider
//     * Created by Wayne on 12/6/2016
//     * Updated by Wayne on 12/7/2016
//     */
//    @Test
//    public void testForRobots() {
//
//        /**
//         * Initialize Robot r1 and Robot r2, set and check their attributes.
//         */
//        MattsRobot r1 = robots.get(0);
//        Assert.assertEquals(101, r1.getID());
//        MattsRobot r2 = robots.get(1);
//        Assert.assertEquals(102, r2.getID());
//        Assert.assertTrue(r1.getLocation() == Point(1, 1));
//        Assert.assertTrue(r2.getLocation() == Point(1, 2));
//        r1.setIdle(false);
//        Assert.assertEquals(false, r1.isIdle());
//        r1.setIdle(true);
//        r2.setIdle(false);
//        Assert.assertEquals(false, r2.isIdle());
//        r2.setIdle(true);
//        Assert.assertEquals(false, r1.isLoaded);
//        Assert.assertEquals(false, r2.isLoaded);
//
//        /**
//         * Initialize Shelf s1 and Shelf s2, and assign locations to both shelves.
//         * Set the current task for r1 to go to s1, and r2 to go to s2.
//         * Instruct r1 to pick s1 and r2 to pick s2.
//         * Construct Path p1 from r1 to s1 and Path p2 from r2 to s2.
//         * Check if r1 arrives at the location of s1 and r2 arrives at the location of s2.
//         */
//        shelfManager.Shelf_Manager_Init(2, inventory, quantity);
//        Shelf s1 = shelfManager.getShelf(0);
//        s1.setOriginalLocation(new Point(3, 3));
//        Shelf s2 = shelfManager.getShelf(1);
//        s2.setOriginalLocation(new Point(3, 4));
//        Assert.assertEquals(" ", r1.getCurrentTask());
//        r1.setCurrentTask("No task at present");
//        Assert.assertEquals(" ", r2.getCurrentTask());
//        r2.setCurrentTask("No task at present");
//        r1.setCurrentTask("Goto Shelf");
//        r2.setCurrentTask("Goto Shelf");
//        path p1 = routeFinder.robotToShelf(r1.getLocation(), s1.getOriginalLocation());
//        path p2 = routeFinder.robotToShelf(r2.getLocation(), s2.getOriginalLocation());
//        r1.setPath(p1);
//        r1.move(s1.getOriginalLocation());
//        r2.setPath(p2);
//        r2.move(s2.getOriginalLocation());
//
//        /**
//         * Check if the current task for r1 is to go to s1, and for r2 to go to s2;
//         * and if r1 reaches s1, and r2 reaches s2.
//         * If both true, then set the current task for r1 to deliver s1, and r2 to deliver s2.
//         * Instruct r1 to load s1 and r2 to load s2.
//         * Check if r1 loads s1 and r2 loads s2.
//         * Check if s1 and s2 remain still.
//         */
//        Assert.assertEquals("Goto Shelf", r1.getCurrentTask());
//        Assert.assertEquals("Goto Shelf", r2.getCurrentTask());
//        Assert.assertEquals(r1.getLocation(), );
//        Assert.assertEquals(s1, r1.getLoadedShelf());
//        Assert.assertEquals(0, r1.getShelfID());
//        r1.loadShelf(s1);
//        Assert.assertEquals(true, r1.isLoaded);
//        r1.setCurrentTask("Deliver Shelf");
//        Assert.assertEquals(r2.getLocation(), s2.getOriginalLocation());
//        Assert.assertEquals(s2, r2.getLoadedShelf());
//        Assert.assertEquals(1, r2.getShelfID());
//        r2.loadShelf(s1);
//        Assert.assertEquals(true, r2.isLoaded);
//        r2.setCurrentTask("Deliver Shelf");
//        Assert.assertEquals(s1.getOriginalLocation(), s1.getLocation());
//        Assert.assertEquals(s2.getOriginalLocation(), s2.getLocation());
//
//        /**
//         * Check if the current task for r1 is to deliver s1 to the picker, and r2 to deliver s2 to the picker.
//         * If true, then construct path p3 from s1 to picker and path p4 from s2 to picker.
//         * Set p3 and p4 as the respective path for r1 and r2.
//         * Instruct r1 to move s1 to picker, then r2 to move s2 to picker.
//         * Check if r1 and s1, and r2 and s2 arrive to the location of picker.
//         * Inform the picker that s1 has arrived, and then that s2 has arrived.
//         * Instruct the picker to pick items with ID 81820 from s1, and then pick items with ID 81821 from s2.
//         * Set the current task for r1 to return s1, and then r2 to return s2.
//         */
//        Assert.assertEquals("Deliver Shelf", r1.getCurrentTask());
//        Assert.assertEquals("Deliver Shelf", r2.getCurrentTask());
//        path p3 = routeFinder.shelfToPicker(s1.getLocation());
//        path p4 = routeFinder.shelfToPicker(s2.getLocation());
//        r1.setPath(p3);
//        r1.move();
//        r2.setPath(p4);
//        r2.move();
//        Assert.assertEquals(picker.getLocation(), r1.getLocation());
//        Assert.assertEquals(picker.getLocation(), s1.getLocation());
//        picker.shelfArrived(0);
//        picker.pickItemsFromShelf(81820);
//        r1.setCurrentTask("Return Shelf");
//        Assert.assertEquals(picker.getLocation(), r2.getLocation());
//        Assert.assertEquals(picker.getLocation(), s2.getLocation());
//        picker.shelfArrived(1);
//        picker.pickItemsFromShelf(81821);
//        r2.setCurrentTask("Return Shelf");
//
//        /**
//         * After the picker has finished picking items from both s1 and s2,
//         * check if the current task for r1 is to return s1, and r2 to return s2.
//         * If true, then construct path p5 from picker to the original location of s1 and p6 from picker to the original location of s2.
//         * Instruct r1 to return s1 and r2 to return s2.
//         * Check if r1 and s1 return to the original location of s1, and r2 and s2 return to the original location of s2.
//         * If true, then instruct r1 to unload s1 and r2 to unload s2.
//         * Set the current tasks for s1 and s2 to go back to their original locations.
//         */
//        Assert.assertEquals("Return Shelf", r1.getCurrentTask());
//        Assert.assertEquals("Return Shelf", r2.getCurrentTask());
//        path p5 = routeFinder.returnShelf(picker.getLocation(), s1.getOriginalLocation());
//        path p6 = routeFinder.returnShelf(picker.getLocation(), s2.getOriginalLocation());
//        r1.setPath(p5);
//        r1.move();
//        r2.setPath(p6);
//        r2.move();
//        Assert.assertEquals(r1.getLocation(), s1.getOriginalLocation());
//        Assert.assertEquals(s1.getLocation(), s1.getOriginalLocation());
//        r1.unloadShelf();
//        r1.setCurrentTask("Resting Position");
//        Assert.assertEquals(r2.getLocation(), s2.getOriginalLocation());
//        Assert.assertEquals(s2.getLocation(), s1.getOriginalLocation());
//        r2.unloadShelf();
//        r2.setCurrentTask("Resting Position");
//
//        /**
//         * Check if the current tasks for r1 and r2 are to go to their resting positions.
//         * If true, then instruct r1 and r2 to go back to their original locations.
//         * Check if r1 and r2 return to their original locations respectively.
//         */
//        Assert.assertEquals("Resting Position", r1.getCurrentTask());
//        Assert.assertEquals("Resting Position", r2.getCurrentTask());
//        r1.move(r1.getOriginalLocation().GetX(), r1.getOriginalLocation().GetY());
//        r2.move(r2.getOriginalLocation().GetX(), r2.getOriginalLocation().GetY());
//        Assert.assertEquals(r1.getLocation(), r1.getOriginalLocation());
//        r1.setCurrentTask("No task at present");
//        Assert.assertEquals(r2.getLocation(), s2.getOriginalLocation());
//        r2.setCurrentTask("No task at present");
//    }
//
//    /**
//     * @author Wayne Lei aka Virocider
//     * Created by Wayne on 12/6/2016
//     * Updated by Wayne on 12/7/2016
//     */
//    @Test
//    public void testForRobotScheduler() {
//
//        /**
//         * Add 3 more robots to robots by using method addRobot(MattsRobot) of robotScheduler.
//         * List all robots created.
//         * Check if the method listRobots() has the same contents as robots.
//         * If trun, then reference 3 variables to those three recently added robots.
//         */
//        robotScheduler.addRobot(new MattsRobot(new Point(1, 3), 103, shelfManager, routeFinder));
//        robotScheduler.addRobot(new MattsRobot(new Point(1, 4), 104, shelfManager, routeFinder));
//        robotScheduler.addRobot(new MattsRobot(new Point(1, 5), 105, shelfManager, routeFinder));
//        robotScheduler.listRobots();
//        Assert.assertEquals(robots, robotScheduler.listRobots());
//        MattsRobot r3 = robots.get(2);
//        MattsRobot r4 = robots.get(3);
//        MattsRobot r5 = robots.get(4);
//
//        /**
//         * Initialize a sample order with order ID as 0.
//         * Instruct robotScheduler to take one order.
//         */
//        Map<Integer, List<Integer>> map1 = new Map<Integer, Integer>();
//        Integer itemID = 76409;
//        List<Integer> qtyList1 = new List<Integer>();
//        List.add(5);
//        List.add(10);
//        List.add(15);
//        map1.put(76409, qtyList1);
//        String address1 = "177a Bleecker Street, New York City, NY";
//        Order o1 = new Order(0, map1, address1);
//        robotScheduler.takeOrder(o1);
//
//        //To be continue... TODO: Add testing codes for method update(o, e) once it is refactored.
//    }
//
//    private void makePoints () {
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                points[i][j] = new Floor.Point(i, j);
//            }
//        }
//    }
//
//}
