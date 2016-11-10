package Belt;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Eduardo   on 11/3/16.
 * Group B4
 */
///needtorun = true  robot parked at picker station

public class Belt implements Observer
{
	public ArrayList<Integer> shippedItemIDList;
	public int x_loc_pick;
	public int y_loc_pick;
	
	//receives item from Robot, and sends to belt to reach packer
	public void pack(Integer OrderID)  //receive from picker and send to packer
	{
		ship(OrderID);
	}
	
	//ships out of warehouse   and store in list of shipped orders
	public void ship(Integer orderID)
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


	}
	
}	