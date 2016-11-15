package Inventory;
import java.util.*;

/**
 * @author [your name here, mr. inventory]
 * @author Matt McCan
 */
public class Inventory {

	//We should make this a seperate class outside of inventory so we have a baseline Item class
	//That way Orders, Inventory, and Floor can all just use one item class together
	//@Max

	class Item_Details{
		HashMap <Integer, Integer> Containers;
		int Item_ID;
		String Item_Name;
		int Item_Quantity;

		Item_Details(int Item_ID, String Item_Name, int Item_Quantity){
			this.Item_ID = Item_ID;
			this.Item_Name = Item_Name;
			this.Item_Quantity = Item_Quantity;

		}

		public void Qty_Update(){
			for (int value : Containers.values()) {
			    Item_Quantity += value;
			}
		}
	}

	public HashMap <Integer, Item_Details> Main_Inventory = new HashMap<Integer, Item_Details>();
	public HashMap <String, Integer> Converter = new HashMap<String, Integer>();

	public int Get_Item_Qty(int ID){
		return Main_Inventory.get(ID).Item_Quantity;
	}

	public String Get_Item_Name(int ID){
		return Main_Inventory.get(ID).Item_Name;
	}

	public int Get_Inventory_ID(String name){
		return Converter.get(name);
	}

	public LinkedList<String> Contained_In(int ID){
		Set<Integer> Tempset = Main_Inventory.get(ID).Containers.keySet();
		LinkedList<String> RetList = null; //new LinkedList<String>(Tempset);
		return RetList;
	}

	public void Add_Inventory(int ID, String Name, int Qty){
		Item_Details New_Item = new Item_Details(ID, Name, Qty);
		Main_Inventory.put(New_Item.Item_ID, New_Item);
	}

	public void Put_Container(int Item_ID, int Container_ID, int Qty){
		if (Main_Inventory.get(Item_ID).Containers.get(Container_ID) != null){
			int tempvar = Main_Inventory.get(Item_ID).Containers.get(Container_ID);
			tempvar +=  Qty;
			Main_Inventory.get(Item_ID).Containers.put(Container_ID, tempvar);
		}

		else {
			Main_Inventory.get(Item_ID).Containers.put(Container_ID, Qty);
		}
	}
}