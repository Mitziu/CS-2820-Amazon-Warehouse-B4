package Belt;

import Floor.Shelf;
import Ordering.Order;

/**
 * Created by Mitziu on 11/17/16.
 * @author Mitziu
 */
public interface Picker {

    /**
     * Informs the Picker of a new order that will need to be fulfilled
     * @author Mitziu
     * @author Max
     * @author Eduardo
     * @param newOrder Order to be fulfilled
     */
    public void newOrder (Order newOrder);


    /**
     * Informs the Picker that a new shelf has arrived
     * @author Mitziu
     * @author Max
     * @author Eduardo
     * @param shelf Shelf has arrived
     */
    public void shelfArrived (Shelf shelf);
    //TODO: Need to create a single shelf class

}
