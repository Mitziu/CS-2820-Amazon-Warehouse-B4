package Belt;

import java.util.List;

/**
 * Created by Eduardo on 11/10/2016.
 */
public interface Belt {
    /**
     * created for interface
     * method to start the packing of the order given
     * @param orderID : id of the order or identifier
     */
    public void pick (Integer orderID );

    public List<Integer> getShippedItems();

}
