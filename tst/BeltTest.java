/**
 * Created by Eduardo on 11/13/2016.
 */
import Belt.BeltImpl;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;


public class BeltTest {

    @Test
    public void BeltPick()//insert robot location to verify it is at picker station
    {
        BeltTest test01Belt = new BeltTest();

        String result = test01Belt.onSite_Pick(1, 10);

        System.out.println("and tested");//onSite_Pick should print  "order at picke station"

        assertEquals("order at picker station", result);
    }

    public class BeltTest02 {
        @Test
        public void testBelt2() {
            Belt2 Test02 = new Belt2();
            List<Integer> itemsOnBelt2List = Test02.getAllItems();
            assertEquals(itemsOnBeltList, itemsOnBelt2List);
        }
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