package Inventory;

import java.util.HashMap;
import java.util.LinkedList;

public interface Inventory_Interface {

	class Item_Details{

	}

	public HashMap <Integer, Item_Details> Main_Inventory = new HashMap<Integer, Item_Details>();
	public HashMap <String, Integer> Converter = new HashMap<String, Integer>();

	public int Get_Item_Qty(int ID);

	public String Get_Item_Name(int ID);

	public int Get_Inventory_ID(String name);

	public LinkedList<String> Contained_In(int ID);

	public void Add_Inventory(int ID, String Name, int Qty);
}
