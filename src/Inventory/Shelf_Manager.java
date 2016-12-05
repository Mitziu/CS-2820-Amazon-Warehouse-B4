package Inventory;
import java.util.*;

/**
 * Main Class for the Shelf Manager
 * @author William Anderson
 * @author Mitziu
 */
public class Shelf_Manager implements S_Manager{
	

	public Map<Integer, Shelf> shelves;
	
	public Shelf_Manager(){
		shelves = new HashMap<>();
	}

	/**
	 * Returns a LinkedList of all the shelf IDs currently recognized
	 * by the Shelf_Manager
	 * @author William Anderson
	 * @author Mitziu
	 * @return LinkedList of all the Shelf_IDs in Shelf_Manager
	 */
	public List<Integer> Get_All_Shelves(){
		return new ArrayList<>(shelves.keySet());
	}

	public List<Shelf> listShelves() {
		List<Shelf> listOfShelves = new ArrayList<>();
		shelves.entrySet()
				.forEach(entry -> listOfShelves.add(entry.getValue()));

		return listOfShelves;
	}

	/**
	 * Provides a list of items the shelf holds
	 * @param Shelf_ID ID number for the shelf
	 * @return LinkedList of items contained in the shelf
	 * @author William Anderson
	 * @author Mitziu
	 */
	public List<Integer> Item_List(int Shelf_ID){
		return new ArrayList<>(shelves.get(Shelf_ID).items.keySet());

	}
	
	/**
	 * Returns the Number of a specific Item in a specific Container
	 * @param Item_ID ID number for the Item
	 * @param Shelf_ID	ID number for the Container
	 * @return Number of Items in that container
	 * @author William Anderson
	 * @author Mitziu
	 */
	public int Container_Count(int Item_ID, int Shelf_ID){
		if (shelves.get(Shelf_ID).items.containsKey(Item_ID)) {
			return shelves.get(Shelf_ID).items.get(Item_ID);
		} else {
			return 0;
		}
	}
	
	/**
	 * Adds an Item to the container by either adding that item and the amount contained to the 
	 * Shelf's HashMap of items or updates the value for that item in the HashMap
	 * @param Item_ID Item ID number
	 * @param Shelf_ID Container ID number
	 * @param Qty Quantity to be added
	 * @author William Anderson
	 * @author Mitziu
	 */
	public void Put_Container(int Item_ID, int Shelf_ID, int Qty){
		if (shelves.containsKey(Shelf_ID)) {
			if (shelves.get(Shelf_ID).items.containsKey(Item_ID)) {
				Integer currentQty = shelves.get(Shelf_ID).items.get(Item_ID);
				currentQty += Qty;
				shelves.get(Shelf_ID).items.put(Item_ID, currentQty);
			} else {
				shelves.get(Shelf_ID).items.put(Item_ID, Qty);
			}
		} else {
			shelves.put(Shelf_ID, new Shelf(Shelf_ID, new HashMap<Integer, Integer>()));
			shelves.get(Shelf_ID).items.put(Item_ID, Qty);
		}


	}
	
	/**
	 * Takes an item out of a container by editing the quantity for that item in the Shelf's HashMap
	 * Also it will add more of that specific item to that shelf if the quantity drops too low
	 * @param Item_ID Item ID number
	 * @param Shelf_ID Container ID number
	 * @param Qty Quantity to be taken
	 * @author William Anderson
	 * @author Mitziu
	 */
	public void Take_Container(int Item_ID, int Shelf_ID, int Qty){
		shelves.get(Shelf_ID).items.put(Item_ID, shelves.get(Shelf_ID).items.get(Item_ID) - Qty);

		if (shelves.get(Shelf_ID).items.get(Item_ID) <= 0) {
			shelves.get(Shelf_ID).items.remove(Item_ID);
		}
	}
	
	/**
	 * Returns a LinkedList containing all of the ID numbers of the containers that are currently
	 * holding this Item.  If no containers currently hold this item then it returns a single 
	 * element list with the value 0 in it.
	 * @param Item_ID ID of the item that each shelf will be checked for.  
	 * @return LinkedList of Shelf IDs that contain the item.
	 * @author William Anderson
	 * @uathor Mitziu
	 */
	public List<Integer> Contained_In(int Item_ID){
		List<Integer> shelfIDS = new ArrayList<>();

		shelves.entrySet().stream()
				.filter(keyValue -> keyValue.getValue().items.containsKey(Item_ID))
				.forEach(key -> shelfIDS.add(key.getKey()));

		return shelfIDS;
	}

	@Override
	public Shelf getShelf(int Shelf_ID) {
		if (shelves.containsKey(Shelf_ID)) {
			return shelves.get(Shelf_ID);
		} else {
			return null;
		}
	}

	/**
	 * Takes a list of ID number for the shelves and creates a reference HashMap to check quantity
	 * of items on a shelf by shelf basis. As well as places items already in the Main Inventory on shelves.
	 * @param Shelf_IDs ID number for the shelves to be generated
	 * @author William Anderson
	 * @author Mitziu
	 */
	//This assumes that the Inventory has been generated and Initialized before this method
	//is called.
	public void Shelf_Manager_Init(LinkedList<Integer> Shelf_IDs, LinkedList<Integer> Inv, LinkedList<Integer> Qty){
		for (int i = 0; i < Shelf_IDs.size(); i++) {
			shelves.put(i, new Shelf(Shelf_IDs.get(i), new HashMap<>()));
		}

		for (int i = 0; i < Inv.size(); i++) {
			shelves.get(i % Shelf_IDs.size()).items.put(Inv.get(i), Qty.get(i));
		}
	}


}