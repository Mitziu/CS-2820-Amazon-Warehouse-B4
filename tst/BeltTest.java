/**
 * Created by Eduardo on 11/13/2016.
 */
import Belt.BeltImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;


public class BeltTest
{
    /**
     * verify existence of order at belt
     * @author Eduardo
     */
    @Test
    public void Test_Picked_Order_Is_Placed_On_Belt()
    {
        Integer orderId = 1111111;
        BeltImpl test01Belt = new BeltImpl(10);

        test01Belt.pick(orderId);
        List<Integer> testOrdersBelt =  test01Belt.getOrdersOnBelt();

        Assert.assertTrue(testOrdersBelt.size() == 10);
        Assert.assertTrue(testOrdersBelt.contains(orderId));
    }

    /**
     * tests if order is shipped once reaches end of belt
     * @author Eduardo
     */
    @Test
    public void Test_Order_Is_Shipped_When_Reaches_End_Of_Belt()
    {
        Integer orderId = 222222;
        BeltImpl Test02 = new BeltImpl(1);

        Test02.pick(orderId);
        Test02.update(null, null);
        List<Integer> shippedOrdersList = Test02.getShippedOrders();

        Assert.assertTrue(shippedOrdersList.size() == 1);
        Assert.assertTrue(shippedOrdersList.contains(orderId));
    }
}