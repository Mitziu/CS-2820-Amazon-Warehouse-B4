/**
 * Created by Max on 11/29/2016.
 * @author Max Riley
 *
 *
 */

import Belt.Belt;
import Floor.FloorPositions;
import Floor.Setup;
import Ordering.OrderingSystem;
import Ordering.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Belt.BeltImpl;
import Inventory.Inventory;
import Floor.RobotManager;
import Belt.PickerImpl;
import Inventory.Shelf_Manager;
import Floor.GoldenManager;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;

public class OrderTest {

    /*public Inventory testInvent = new Inventory();
    public RobotScheduler testRobot = new RobotScheduler("TestRobot");
    public BeltImpl testBelt = new BeltImpl(10,10);
    public PickerImpl testPicker = new PickerImpl(testBelt);

    public OrderingSystem testOrderSystem = new OrderingSystem(testInvent, testBelt, testRobot, testPicker);

    public String HolderAddress = "123 Main Street";

    public Map<Integer,Integer> testItemList = new HashMap<>();

    */



        //Need PlaceOrder test
        //Need isIteminStock test
        //Need finishOrder test
        //Need addToHistory test
        //Need getHistory test

    public static void main(String[] args){
        Shelf_Manager testShelf = new Shelf_Manager();
        FloorPositions floor = new Setup();
        Inventory testInvent = new Inventory(testShelf);
        GoldenManager testRobot = new GoldenManager(testShelf, floor);
        BeltImpl testBelt = new BeltImpl(10,10);
        PickerImpl testPicker = new PickerImpl(testBelt, testShelf);

        OrderingSystem testOrderSystem = new OrderingSystem(testInvent, testBelt, testRobot, testPicker);

        String HolderAddress = "123 Main Street";

        Map<Integer,Integer> testItemList = new HashMap<>();

        //PlaceOrder test
        for(int x = 0; x < 10; x++) {
            Boolean tester = testOrderSystem.placeOrder(testItemList, HolderAddress);

            System.out.println(tester);
        }

        //Get current Orders
        System.out.println(testOrderSystem.getCurrentOrders());

        //Finish Order test
        testOrderSystem.finishOrder(100000);
        testOrderSystem.finishOrder(100003);
        testOrderSystem.finishOrder(100005);
        testOrderSystem.finishOrder(100007);

        System.out.println(testOrderSystem.getHistory());



    }





}
