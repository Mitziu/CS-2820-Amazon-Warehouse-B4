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

        String result = test01Belt.onSite_Pick(1, 10);

        System.out.println("on site and tested");//onSite_Pick should print  "order at picke station"

        assertEquals("order at picker station", result);
    }
        @Test
        public void testBelt2() {
            Integer orderId = 1;
            BeltImpl Test02 = new BeltImpl(1,1);
            Test02.pack(orderId);
            List<Integer> itemsOnBelt2List = Test02.reportContent();
            List<Integer> itemsOnBeltList = new ArrayList<>();
            itemsOnBeltList.add(orderId);

            assertEquals(itemsOnBeltList, itemsOnBelt2List);
        }

    @Test
    public void Beltpackager()//need review by master
    {
        BeltTest test01Belt = new BeltTest();
        String result = test01Belt.onSite_Pick(1, 10);
        System.out.println("and tested");//onSite_Pick should print  "order at picke station"

        assertEquals("order at picker station", result);
    }
}