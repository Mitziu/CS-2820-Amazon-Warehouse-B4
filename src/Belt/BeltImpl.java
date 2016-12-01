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
	private Belt2 beltUnit;


	public BeltImpl (Integer sizeOfPickerBelt , Integer sizeOfPackerBelt)
	{
		this.sizeOfPackerBelt = sizeOfPackerBelt;
		this.sizeOfPickerBelt = sizeOfPickerBelt;
		pickerQueue = new LinkedList<>();//use methods to fill this list
		packerQueue = new LinkedList<>();//use methods to fill this list
		beltUnit = new Belt2();


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
	
	//Picker receives item from Robot, and sends to belt to reach packer
	public void pick(Integer orderID)
	{
		pickerQueue.getFirst().addItem(orderID);
		beltUnit.addItemBelt(orderID);//add order ID to beltUnit for report purposes
	}

	public List<Integer> reportContent()
	{
		return pickerQueue.getFirst().getAllItems();
	}
	
	//ships out of warehouse   and store in list of shipped orders
	private void ship(Bin bin)
	{
		shippedItemIDList.addAll(bin.getAllItems());
	}

	//method that finish process, Item gets packed and out of Belt, also takes order ID out from public list beltUnit
	private void packer(Bin bin) {
		packerQueue.getFirst().addAllItems(bin);
		for (Integer OrderID : bin.getAllItems())
		{
			beltUnit.removeItemBelt(OrderID);
		}

	}
	
	public String onSite_Pick(int x, int y)// happens when robot reaches picker station
	{
		Integer orderID = 0;
		if(x_loc_pick ==  x && y_loc_pick ==  y)
		{
			pick(orderID);
            //print msg : "order at picker station"
            return ("order at picker station");
		}
		return ("NOT THERE YET");
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
	 * Gets list of shipped orders
	 * @author Mitziu
	 * @author Eduardo
	 * @author Max
	 * @return
	 */
	public List<Integer> getShippedItems() {
		List<Integer> listToReturn = (List<Integer>) shippedItemIDList.clone();
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
		shippedItemIDList.clear();
	}

	/**
	 * Private class for belt to contain items inside bin
	 * Author: Mitziu
	 */
	private class Bin {

		private List<Integer> itemsInside;

	public Bin() {
		itemsInside = new ArrayList<>();// array that has items identified by order ID
	}

	public void addItem(Integer orderID) {
		itemsInside.add(orderID);//include items inside array "itemsInside"
	}

	public List<Integer> getAllItems() {
		return itemsInside;
	}

	public void addAllItems(Bin itemsToAdd) {
		itemsInside.addAll(itemsToAdd.getAllItems());
	}
}

    private class Belt2
    {
        private List<Integer> itemsOnBeltList;
        public Belt2()
        {
            itemsOnBeltList = new ArrayList<>();// creation of array that has items identified by order ID
        }

        public void addItemBelt(Integer orderID)
        {
            itemsOnBeltList.add(orderID);//include items inside array "itemsOnBeltList"
        }
        public List<Integer> getAllItems()
        {
            return itemsOnBeltList;//retrieve all items present on Belt
        }
		public void removeItemBelt(Integer orderID)
        {
            itemsOnBeltList.remove(orderID);
        }
    }
// create new class that extends private belt2

}
