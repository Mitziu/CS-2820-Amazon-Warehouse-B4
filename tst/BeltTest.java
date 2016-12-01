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
    @Test
    public void BeltPick()//insert robot location to verify it is at picker station
    {
        BeltImpl test01Belt = new BeltImpl(10,10);

        String result = test01Belt.onSite_Pick(0, 0);

        System.out.println("on site and tested");//onSite_Pick should print  "order at picker station"

        Assert.assertTrue(result.equals("order at picker station"));
    }
        @Test
        public void testBelt2() {
            Integer orderId = 1;
            BeltImpl Test02 = new BeltImpl(1,1);
            Test02.pick(orderId);
            List<Integer> actualItemsOnBeltList = Test02.reportContent();
            List<Integer> expectedItemsOnBeltList = new ArrayList<>();
            expectedItemsOnBeltList.add(orderId);

            Assert.assertTrue(actualItemsOnBeltList.size()==expectedItemsOnBeltList.size());

            Assert.assertTrue(actualItemsOnBeltList.get(0) == expectedItemsOnBeltList.get(0));
        }
}