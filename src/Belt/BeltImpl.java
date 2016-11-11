package Belt;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.*;

/**
 * Created by Eduardo   on 11/3/16.
 * Group B4
 */
///needtorun = true  robot parked at picker station

public class BeltImpl implements Observer, Belt
{
	public ArrayList<Integer> shippedItemIDList;
	public int x_loc_pick;
	public int y_loc_pick;
	private Deque<Integer> pickerQueue;
	private Deque<Integer> packerQueue;
	private Integer sizeOfPickerBelt;
	private Integer sizeOfPackerBelt;

	public BeltImpl (Integer sizeOfPickerBelt , Integer sizeOfPackerBelt)
	{
		this.sizeOfPackerBelt = sizeOfPackerBelt;
		this.sizeOfPickerBelt = sizeOfPickerBelt;
		pickerQueue = new LinkedList<>();//use methods to fill this list
		packerQueue = new LinkedList<>();//use methods to fill this list
	}
	
	//receives item from Robot, and sends to belt to reach packer
	public void pack(Integer orderID)  //receive from picker and send to packer
	{

		pickerQueue.addFirst(orderID);

	}
	
	//ships out of warehouse   and store in list of shipped orders
	private void ship(Integer orderID)
	{
		shippedItemIDList.add(orderID);
	}
	
	public void onSite_Pick(int x, int y)
	{
		Integer orderID = 0;
		if(x_loc_pick ==  x && y_loc_pick ==  y)
		{
			pack(orderID);
		}
	}
	
	@Override
    public void update(Observable o, Object arg)
	{
		pickerQueue.addFirst(-1);
		packerQueue.addFirst(-1);

		if (pickerQueue.size() == sizeOfPickerBelt + 1) {
			packerQueue.addFirst(pickerQueue.removeLast());
		}

		if (packerQueue.size() == sizeOfPackerBelt + 1) {
			shippedItemIDList.add(packerQueue.removeLast());
		}

	}

}	