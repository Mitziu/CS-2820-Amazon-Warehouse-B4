/**
 * Created by Max on 11/29/2016.
 * @author Max Riley
 *
 *
 */

import Belt.Belt;
import Ordering.OrderingSystem;
import Ordering.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Belt.BeltImpl;
import Inventory.Inventory;
import RobotScheduler.RobotScheduler;
import Belt.PickerImpl;

public class OrderTest {

    Inventory testInvent = new Inventory();
    RobotScheduler testRobot = new RobotScheduler("TestRobot");
    BeltImpl testBelt = new BeltImpl(10,10);
    PickerImpl testPicker = new PickerImpl(testBelt);

    @Test
    public void CreateOrder(){
        OrderingSystem testOrderSystem = new OrderingSystem(testInvent, testBelt, testRobot, testPicker);

        //Need PlaceOrder test
        //Need isIteminStock test
        //Need finishOrder test
        //Need addToHistory test
        //Need getHistory test



    }

}
