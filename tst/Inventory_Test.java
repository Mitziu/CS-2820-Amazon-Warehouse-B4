import Floor.Setup;
import Inventory.Inventory;
import Inventory.Shelf_Manager;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

public class Inventory_Test {
	
	//Before//
	Shelf_Manager SM = new Shelf_Manager();
	Inventory Inv = new Inventory(SM);
	Setup setup = new Setup();

	
	@Test
	public void testconstructor(){
		Inv.Add_Inventory(75450, "Baseball", 0);
		assertEquals(75450, Inv.Get_Inventory_ID("Baseball"));
		assertEquals("Baseball", Inv.Get_Item_Name(75450));
		assertEquals(0, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Before anythings loaded
	 */
	@Test
	public void testContained_In1(){
		Inv.Add_Inventory(75450, "Baseball", 1);
		int testvar = SM.Contained_In(75450).get(0);
		assertEquals(0, testvar);
	}
	
	/**
	 * After loading
	 */
	@Test
	public void testContained_In2()throws ParseException, IOException{
        setup.Initialize();
        Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);
	    Inv.Add_Inventory(75450, "Baseball", 13);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0), 13);

		assertEquals(SM.Get_All_Shelves().get(0).intValue(), SM.Contained_In(75450).get(0).intValue());
		assertEquals(13, Inv.Get_Item_Qty(75450));
	}
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testContained_In3()throws ParseException, IOException{
        setup.Initialize();
        Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);

	    Inv.Add_Inventory(75450, "Baseball", 13);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0), 13);
		Inv.Add_Inventory(75451, "Softball", 27);
		Inv.Add_Inventory(75452, "Ballcap", 38);
		SM.Put_Container(75451, SM.Get_All_Shelves().get(1), 27);
		SM.Put_Container(75452, SM.Get_All_Shelves().get(2), 38);
		
		//Testing that the Contained_In list matches where the items were placed
		assertEquals(SM.Get_All_Shelves().get(0).intValue(), SM.Contained_In(75450).get(0).intValue());
		assertEquals(SM.Get_All_Shelves().get(1).intValue(), SM.Contained_In(75451).get(0).intValue());
		assertEquals(SM.Get_All_Shelves().get(2).intValue(), SM.Contained_In(75452).get(0).intValue());

		//Tesing that their quantities stay the same
        assertEquals(13, Inv.Get_Item_Qty(75450));
		assertEquals(27, Inv.Get_Item_Qty(75451));
		assertEquals(38, Inv.Get_Item_Qty(75452));

        assertEquals(13, SM.Container_Count(75450, SM.Contained_In(75450).get(0)));
        assertEquals(27, SM.Container_Count(75451, SM.Contained_In(75451).get(0)));
        assertEquals(38, SM.Container_Count(75452, SM.Contained_In(75452).get(0)));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testContained_in4()throws ParseException, IOException{
        setup.Initialize();
        Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);

	    Inv.Add_Inventory(75450, "Baseball", 100);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0), 13);
		
		SM.Put_Container(75450, SM.Get_All_Shelves().get(1), 17);
		assertEquals(SM.Get_All_Shelves().get(1).intValue(), SM.Contained_In(75450).get(1).intValue());
		assertEquals(100, Inv.Get_Item_Qty(75450));
		
		SM.Put_Container(75450, SM.Get_All_Shelves().get(4), 27);
		assertTrue(SM.Contained_In(75450).get(0) == SM.Get_All_Shelves().get(0));
		assertTrue(SM.Contained_In(75450).get(1) == SM.Get_All_Shelves().get(1));
		assertTrue(SM.Contained_In(75450).get(2) == SM.Get_All_Shelves().get(4));
		assertEquals(100, Inv.Get_Item_Qty(75450));
		assertEquals(27, SM.Container_Count(75450, SM.Get_All_Shelves().get(4)));
	}
	/**
	 * Testing after one item is loaded
	 */
	@Test
	public void testTake_Container1()throws ParseException, IOException{

		setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);
		Inv.Add_Inventory(75450, "Baseball", 100);
		SM.Put_Container(75450, 40, 100);
		Inv.Order_Claim(75450, 7);
		SM.Take_Container(75450, 40, 7);
		assertEquals(93, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testTake_Container2()throws ParseException, IOException{
		setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);

		Inv.Add_Inventory(75450, "Baseball", 13);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0) , 13);
		Inv.Add_Inventory(75451, "Softball", 27);
		Inv.Add_Inventory(75452, "Ballcap", 38);
		SM.Put_Container(75451, SM.Get_All_Shelves().get(1), 27);
		SM.Put_Container(75452, SM.Get_All_Shelves().get(2), 38);
		SM.Take_Container(75450, SM.Get_All_Shelves().get(0), 7);
		SM.Take_Container(75451, SM.Get_All_Shelves().get(1), 7);
		SM.Take_Container(75452, SM.Get_All_Shelves().get(2), 7);

		//Checking that the items are all in the Shelves they are supposed to be
		assertEquals(SM.Get_All_Shelves().get(0).intValue(), SM.Contained_In(75450).get(0).intValue());
		assertEquals(SM.Get_All_Shelves().get(1).intValue(), SM.Contained_In(75451).get(0).intValue());
		assertEquals(SM.Get_All_Shelves().get(2).intValue(), SM.Contained_In(75452).get(0).intValue());

		//Checking that their quantities in the Main_Inventory haven't changed
		assertEquals(13, Inv.Get_Item_Qty(75450));
		assertEquals(27, Inv.Get_Item_Qty(75451));
		assertEquals(38, Inv.Get_Item_Qty(75452));

		//Checking that their Shelf quantities have changed
		assertEquals(6, SM.Container_Count(75450, SM.Get_All_Shelves().get(0)));
		assertEquals(20, SM.Container_Count(75451, SM.Get_All_Shelves().get(1)));
		assertEquals(31, SM.Container_Count(75452, SM.Get_All_Shelves().get(2)));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testTake_Container3()throws ParseException, IOException{
		setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);

		Inv.Add_Inventory(75450, "Baseball", 50);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0), 13);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(1), 17);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(2), 15);
		SM.Take_Container(75450, SM.Get_All_Shelves().get(1), 15);
		SM.Take_Container(75450, SM.Get_All_Shelves().get(2), 10);

		//Testing that it will load an item correctly into multiple different shelves and
		assertEquals(SM.Get_All_Shelves().get(0).intValue(), SM.Contained_In(75450).get(0).intValue());
		assertEquals(SM.Get_All_Shelves().get(1).intValue(), SM.Contained_In(75450).get(1).intValue());
		assertEquals(SM.Get_All_Shelves().get(2).intValue(), SM.Contained_In(75450).get(2).intValue());

		//Testing that the Take_Container method doesn't change the Main_Inventory Quantity
		//But does change the Shelf Quantity
		assertEquals(50, Inv.Get_Item_Qty(75450));
		assertEquals(2, SM.Container_Count(75450, SM.Get_All_Shelves().get(1)));
		assertEquals(5, SM.Container_Count(75450, SM.Get_All_Shelves().get(2)));

		SM.Put_Container(75450, SM.Get_All_Shelves().get(4), 27);
		SM.Take_Container(75450, SM.Get_All_Shelves().get(4), 15);

		assertEquals(50, Inv.Get_Item_Qty(75450));
		assertEquals(12, SM.Container_Count(75450, SM.Get_All_Shelves().get(4)));
	}
	/**
	 * tests that the container is removed from the list if it is emptied of an item
	 */
	@Test
	public void testEmpty_Container()throws ParseException, IOException{
		setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);

		Inv.Add_Inventory(75450, "Baseball", 30);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(0), 13);
		SM.Put_Container(75450, SM.Get_All_Shelves().get(1), 17);
		
		assertTrue(SM.Contained_In(75450).get(0) == SM.Get_All_Shelves().get(0));
		assertTrue(SM.Contained_In(75450).get(1) == SM.Get_All_Shelves().get(1));
		assertTrue(SM.Contained_In(75450).size() == 2);
		SM.Take_Container(75450, SM.Get_All_Shelves().get(0), 13);
		
		assertTrue(SM.Contained_In(75450).get(0) == SM.Get_All_Shelves().get(1));
		assertTrue(SM.Contained_In(75450).size() == 1);
		
		SM.Take_Container(75450, SM.Get_All_Shelves().get(1), 17);
		
		assertTrue(SM.Contained_In(75450).get(0) == 0);
		
		assertEquals(30, Inv.Get_Item_Qty(75450));
	}
	
	@Test
	public void testInitializer() throws ParseException, IOException{
		//Inv.Inventory_Initialize("Inventory_CSV", MockShelf_IDs);
        setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);
		assertEquals("Cat Food", Inv.Get_Item_Name(7));
		assertEquals("Poster1", Inv.Get_Item_Name(9));
		assertEquals(7, Inv.Get_Inventory_ID("Cat Food"));
		assertEquals(1, Inv.Get_Inventory_ID("Baseball"));

		SM.Put_Container(6, SM.Get_All_Shelves().get(0), 50);
		SM.Put_Container(10, SM.Get_All_Shelves().get(10), 50);
		assertEquals(100, Inv.Get_Item_Qty(6));
		assertEquals(100, Inv.Get_Item_Qty(10));
		assertEquals(SM.Get_All_Shelves().get(0), SM.Contained_In(6).get(0));
		assertEquals(SM.Get_All_Shelves().get(10), SM.Contained_In(10).get(0));

		SM.Take_Container(6, SM.Get_All_Shelves().get(0), 25);
		SM.Take_Container(10, SM.Get_All_Shelves().get(10), 25);
		assertEquals(25, SM.Container_Count(6, SM.Get_All_Shelves().get(0)));
		assertEquals(25, SM.Container_Count(10, SM.Get_All_Shelves().get(10)));
		assertEquals(SM.Get_All_Shelves().get(0), SM.Contained_In(6).get(0));
		assertEquals(SM.Get_All_Shelves().get(10), SM.Contained_In(10).get(0));

		SM.Take_Container(6, SM.Get_All_Shelves().get(0), 25);
		SM.Take_Container(10, SM.Get_All_Shelves().get(10), 25);
		assertEquals(0, SM.Container_Count(6, SM.Get_All_Shelves().get(0)));
		assertEquals(0, SM.Container_Count(10, SM.Get_All_Shelves().get(10)));
		assertEquals(SM.Get_All_Shelves().get(6), SM.Contained_In(6).get(0));
		assertEquals(SM.Get_All_Shelves().get(9), SM.Contained_In(10).get(0));

        SM.Take_Container(6, SM.Get_All_Shelves().get(5), 100);
        SM.Take_Container(9, SM.Get_All_Shelves().get(9), 100);
		assertEquals(0, SM.Contained_In(6));
        assertEquals(0, SM.Contained_In(10));
	}
	@Test
	public void testContainer_Count() throws ParseException, IOException{
		//Inv.Inventory_Initialize("Inventory_CSV", MockShelf_IDs);
        setup.Initialize();
		Inv.Inventory_Initialize("CSVFiles/Inventory_CSV", setup);
		SM.Put_Container(6, SM.Get_All_Shelves().get(0), 100);
		SM.Put_Container(6, SM.Get_All_Shelves().get(10), 200);
		assertEquals(100, SM.Container_Count(6, SM.Get_All_Shelves().get(0)));
		assertEquals(200, SM.Container_Count(6, SM.Get_All_Shelves().get(10)));
		assertEquals(100, Inv.Get_Item_Qty(6));

		SM.Take_Container(6, SM.Get_All_Shelves().get(0), 50);
		SM.Take_Container(6, SM.Get_All_Shelves().get(10), 100);
		assertEquals(50, SM.Container_Count(6, SM.Get_All_Shelves().get(0)));
		assertEquals(100, SM.Container_Count(6, SM.Get_All_Shelves().get(10)));
		assertEquals(100, Inv.Get_Item_Qty(6));

		SM.Take_Container(6, SM.Get_All_Shelves().get(10), 100);
		assertEquals(0, SM.Container_Count(6, SM.Get_All_Shelves().get(10)));
	}
	
	@Test (expected = IOException.class)
	public void initializerExceptionThrown() throws IOException, ParseException{
		//Inv.Inventory_Intialize("Fake_CSV", setup);
		Inv.Inventory_Initialize("Fake_CSV", setup);
	}
	
}

