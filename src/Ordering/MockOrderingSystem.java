package Ordering;

import java.util.Map;

/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public class MockOrderingSystem implements OrderingSystemInterface {

    @Override
    public Boolean placeOrder(Map<Integer, Integer> newItemList, String address) {
        return true;
    }
}
