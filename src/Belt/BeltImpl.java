package Belt;

import Floor.ObjectInWarehouse;
import Floor.Point;

import java.util.*;

/**
 * Created by Eduardo   on 11/3/16.
 * Group B4
 * the belt is in charge of receiving orders from the picker and ship them
 * belt also reports status of order to anyone in the warehouse if requested
 */

public class BeltImpl implements Observer, Belt, ObjectInWarehouse {
	public ArrayList<Integer> shippedOrdersList;
    private Integer[] ordersOnBelt;

	private Point location;
	private Integer size;

    /**
     * Initialize Belt to a certain size
     * @author Eduardo
     */
	public BeltImpl (Integer beltSize)
	{
		shippedOrdersList = new ArrayList<Integer>();
        ordersOnBelt = new Integer[beltSize];
		for(int i = 0; i < beltSize; i++) {
			ordersOnBelt[i] = null;
		}
		this.size = beltSize;
	}

	public Integer getSize () {
		return size;
	}

    /**
     * Picker receives item from Robot, and sends to belt to reach packer
     * @author Eduardo
     */
	public void pick(Integer orderID)
	{
        ordersOnBelt[0] = orderID;
	}

    /**
     * allows EVERYONE in the warehouse to view the items on the belt
     * @author Eduardo
     */
    public List<Integer> getOrdersOnBelt()
    {
        return Arrays.asList(ordersOnBelt);
    }

    /**
     * move the belt
     * @author Eduardo
     */
	@Override
    public void update(Observable o, Object arg)
	{
        //ship the order located at the end of the belt if one exists
        if (ordersOnBelt[ordersOnBelt.length-1] != null)
            ship(ordersOnBelt[ordersOnBelt.length-1]);

        //shift orders one slot down the belt
        for (int i = ordersOnBelt.length-2; i >= 0; --i)
            ordersOnBelt[i+1] = ordersOnBelt[i];
        //clear the starting slot on belt
        ordersOnBelt[0] = null;
	}

    /**
     * ships order out of warehouse and store in list of shipped orders
     * @author Eduardo
     */
    private void ship(Integer orderID)
    {
		System.out.println("DEBUG: Belt, Finished Order # " + orderID);
        shippedOrdersList.add(orderID);
    }

	/**
	 * Gets list of shipped orders
	 * @author Mitziu
	 * @author Eduardo
	 * @author Max
	 * @return
	 */
	public List<Integer> getShippedOrders() {
		List<Integer> listToReturn = (List<Integer>) shippedOrdersList.clone();
		resetShippedList();
		return listToReturn;
	}

	/**
	 * Clears List of shipped Orders
	 * @author Mitziu
	 * @author Eduardo
	 * @author Max
	 */
	private void resetShippedList() {
		shippedOrdersList.clear();
	}


	@Override
	public Point getLocation() {
		return location;
	}

	//UNUSED, BROUGHT FROM IMPLEMENTING OBJECTINWAREHOUSE INTERFACE
	@Override
	public Integer getID() {
		return null;
	}

	@Override
	public void move(Integer x, Integer y) {
		location = new Point(x,y);
	}
}