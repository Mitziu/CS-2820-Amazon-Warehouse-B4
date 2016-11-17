package Ordering;

import java.util.Map;

/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public class MockOrderingSystem implements OrderInterface {

    @Override
    public void getItem(Order order) {
    }

    @Override
    public void startBelt(Integer orderID) {
    }

    @Override
    public void getItemLocation(int itemid) {
    }

    @Override
    public boolean isItemInStock(Integer itemid) {
        return false;
    }

    @Override
    public Boolean placeOrder(Map<Integer, Integer> newItemList, String address) {
        return true;
    }
}
