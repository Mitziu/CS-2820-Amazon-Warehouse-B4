package Belt;

import Ordering.Order;
import Floor.Point;

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

    public Point getPosition ();


    /**
     * Informs the Picker that a new shelf has arrived
     * @author Mitziu
     * @author Max
     * @author Eduardo
     * @param shelfID ID of the shelf at the picker station
     */
    public void shelfArrived (Integer shelfID);

}
