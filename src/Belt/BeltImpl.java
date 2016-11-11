package Belt;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Eduardo   on 11/3/16.
 * Group B4
 */

public class BeltImpl implements Observer, Belt
{
	public ArrayList<Integer> shippedItemIDList;
	public int x_loc_pick;
	public int y_loc_pick;
	private Deque<Bin> pickerQueue;
	private Deque<Bin> packerQueue;
	private Integer sizeOfPickerBelt;
	private Integer sizeOfPackerBelt;

	public BeltImpl (Integer sizeOfPickerBelt , Integer sizeOfPackerBelt)
	{
		this.sizeOfPackerBelt = sizeOfPackerBelt;
		this.sizeOfPickerBelt = sizeOfPickerBelt;
		pickerQueue = new LinkedList<>();//use methods to fill this list
		packerQueue = new LinkedList<>();//use methods to fill this list

		fillQueues();
	}

	private void fillQueues () {
		fillQueue(pickerQueue, sizeOfPickerBelt);
		fillQueue(packerQueue, sizeOfPackerBelt);
	}

	private void fillQueue (Deque<Bin> queue, Integer sizeOfBelt){
		for (int i = 0; i < sizeOfBelt; i++) {
			queue.addFirst(new Bin());
		}
	}
	
	//receives item from Robot, and sends to belt to reach packer
	public void pack(Integer orderID)  //receive from picker and send to packer
	{
		pickerQueue.getFirst().addItem(orderID);
	}
	
	//ships out of warehouse   and store in list of shipped orders
	private void ship(Bin bin)
	{
		shippedItemIDList.addAll(bin.getAllItems());
	}

	private void packer(Bin bin) {
		packerQueue.getFirst().addAllItems(bin);
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
		pickerQueue.addFirst(new Bin());
		packerQueue.addFirst(new Bin());

		packer(pickerQueue.removeLast());
		ship(packerQueue.removeLast());
	}

	/**
	 * Private class for belt to contain items
	 * Author: Mitziu
	 */
	private class Bin {

		private List<Integer> itemsInside;

		public Bin() {
			itemsInside = new ArrayList<>();
		}

		public void addItem(Integer orderID) {
			itemsInside.add(orderID);
		}

		public List<Integer> getAllItems() {
			return itemsInside;
		}

		public void addAllItems(Bin itemsToAdd) {
			itemsInside.addAll(itemsToAdd.getAllItems());
		}
	}

}
