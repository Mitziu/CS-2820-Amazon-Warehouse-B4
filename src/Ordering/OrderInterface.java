import Floor.Shelf;

import java.util.*;

/**
*
*
*@author Max Riley
*
*/

public interface OrderInterface {
    //OrderItem is the class we have/will make for an Item
    //Used to tell robot to get item and put it on belt
    public void getItem(OrderItem item, Shelf shelfLocation);


    //Used to send the belt a notification that a complete order is on the belt and ready to move
    public void startBelt();

    //Get the shelf on which the item is on
    public void getItemLocation(OrderItem item);


    //Check to see if item is in stock
    public boolean isItemInStock(OrderItem item);
}
    


