/**
 * Created by Eduardo on 11/13/2016.
 */
import Belt.BeltImpl;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.HashMap;
        import java.util.Map;

public class BeltTest {

    @Test
    public void BeltPick()//insert robot location to verify it is at picker station
    {
        BeltTest test01Belt = new BeltTest();

        String result  = test01Belt.onSite_Pick(1,10);

        System.out.println("and tested");//onSite_Pick should print  "order at picke station"

        assertEquals("order at picker station", result);
    }

    public class BeltTest02
    {
        @Test
        public void testBelt2()
        {
            Belt2 Test02 = new Belt2();
            List<Integer> itemsOnBelt2List = Test02. getAllItems();
            assertEquals(itemsOnBeltList, itemsOnBelt2List);
        }
    }

    @Test
    public void Beltpackager()
    {
        BeltTest test01Belt = new BeltTest();

        String result  = test01Belt.onSite_Pick(1,10);

        System.out.println("and tested");//onSite_Pick should print  "order at picke station"

        assertEquals("order at picker station", result);
    }

    UpcomingOrder upcomingOrder;
    Map<Integer, Integer> items;

    final private String fakeAddress = "100 Testing Address Iowa City IA";
    final private Integer ID = 10;
    final private Integer quantity = 1;
    final private Integer timeToOrder = 0;

    final private Integer x_loc_pick = 0;
    final private Integer y_loc_pick = 10;

    @Before
    public void setup() {
        items = new HashMap<>();
    }

    @Test
    public void correctParameters() {
        items.put(ID,1);

        upcomingOrder = new UpcomingOrder(timeToOrder, items, fakeAddress);

        Assert.assertTrue(upcomingOrder.getTimeToOrder() == timeToOrder);

        Assert.assertTrue(upcomingOrder.getItems().size() == 1);
        Assert.assertTrue(upcomingOrder.getItems().get(ID) == quantity);

        Assert.assertTrue(upcomingOrder.getAddress().equals(fakeAddress));
    }

    @Test (expected = IllegalStateException.class)
    public void emptyListException() {
        upcomingOrder = new UpcomingOrder(timeToOrder, items, "Testing Address");
    }

    @Test (expected = IllegalStateException.class)
    public void negativeTimeException() {
        items.put(ID , quantity);
        upcomingOrder = new UpcomingOrder(-1, items, "Testing Address");
    }

    @Test (expected = IllegalArgumentException.class)
    public void emptyAddressException() {
        items.put(ID , quantity);
        upcomingOrder = new UpcomingOrder(timeToOrder, items, "");
    }

}

