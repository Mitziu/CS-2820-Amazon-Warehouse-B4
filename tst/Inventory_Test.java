import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;

import java.util.*;

public class Inventory_Test {
	
	//Before//
	Inventory Inv = new Inventory();

	@Test
	public void testconstructor(){
		Inv.Add_Inventory(75450, "Baseball");
		assertEquals(75450, Inv.Get_Inventory_ID("Baseball"));
		assertEquals("Baseball", Inv.Get_Item_Name(75450));
		assertEquals(0, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Before anythings loaded
	 */
	@Test
	public void testContained_In1(){
		Inv.Add_Inventory(75450, "Baseball");
		int testvar = Inv.Contained_In(75450).get(0);
		assertEquals(0, testvar);
	}
	
	/**
	 * After loading
	 */
	@Test
	public void testContained_In2(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		assertEquals(11, Inv.Contained_In(75450).get(0).intValue());
		assertEquals(13, Inv.Get_Item_Qty(75450));
	}
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testContained_In3(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		Inv.Add_Inventory(75451, "Softball");
		Inv.Add_Inventory(75452, "Ballcap");
		Inv.Put_Container(75451, 12, 27);
		Inv.Put_Container(75452, 13, 38);
		
		
		assertEquals(11, Inv.Contained_In(75450).get(0).intValue());
		assertEquals(13, Inv.Get_Item_Qty(75450));
		assertEquals(12, Inv.Contained_In(75451).get(0).intValue());
		assertEquals(27, Inv.Get_Item_Qty(75451));
		assertEquals(13, Inv.Contained_In(75452).get(0).intValue());
		assertEquals(38, Inv.Get_Item_Qty(75452));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testContained_in4(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		
		Inv.Put_Container(75450, 12, 17);
		assertEquals(12, Inv.Contained_In(75450).get(1).intValue());
		assertEquals(30, Inv.Get_Item_Qty(75450));
		
		Inv.Put_Container(75450, 33, 27);
		assertTrue(Inv.Contained_In(75450).get(0).intValue() == 11 || Inv.Contained_In(75450).get(0).intValue() == 12 || Inv.Contained_In(75450).get(0).intValue() == 33);
		assertTrue(Inv.Contained_In(75450).get(1).intValue() == 11 || Inv.Contained_In(75450).get(1).intValue() == 12 || Inv.Contained_In(75450).get(1).intValue() == 33);
		assertTrue(Inv.Contained_In(75450).get(2).intValue() == 11 || Inv.Contained_In(75450).get(2).intValue() == 12 || Inv.Contained_In(75450).get(2).intValue() == 33);
		assertEquals(57, Inv.Get_Item_Qty(75450));
	}
	/**
	 * Testing after one item is loaded
	 */
	@Test
	public void testTake_Container1(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		Inv.Take_Container(75450, 11, 7);
		assertEquals(6, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testTake_Container2(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		Inv.Add_Inventory(75451, "Softball");
		Inv.Add_Inventory(75452, "Ballcap");
		Inv.Put_Container(75451, 12, 27);
		Inv.Put_Container(75452, 13, 38);
		Inv.Take_Container(75450, 11, 7);
		Inv.Take_Container(75451, 12, 7);
		Inv.Take_Container(75452, 13, 7);
		
		assertEquals(11, Inv.Contained_In(75450).get(0).intValue());
		assertEquals(6, Inv.Get_Item_Qty(75450));
		assertEquals(12, Inv.Contained_In(75451).get(0).intValue());
		assertEquals(20, Inv.Get_Item_Qty(75451));
		assertEquals(13, Inv.Contained_In(75452).get(0).intValue());
		assertEquals(31, Inv.Get_Item_Qty(75452));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testTake_Container3(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		Inv.Put_Container(75450, 12, 17);
		Inv.Take_Container(75450, 12, 15);
		assertEquals(12, Inv.Contained_In(75450).get(1).intValue());
		assertEquals(15, Inv.Get_Item_Qty(75450));
		
		Inv.Put_Container(75450, 33, 27);
		Inv.Take_Container(75450, 33, 15);
		assertTrue(Inv.Contained_In(75450).get(0).intValue() == 11 || Inv.Contained_In(75450).get(0).intValue() == 12 || Inv.Contained_In(75450).get(0).intValue() == 33);
		assertTrue(Inv.Contained_In(75450).get(1).intValue() == 11 || Inv.Contained_In(75450).get(1).intValue() == 12 || Inv.Contained_In(75450).get(1).intValue() == 33);
		assertTrue(Inv.Contained_In(75450).get(2).intValue() == 11 || Inv.Contained_In(75450).get(2).intValue() == 12 || Inv.Contained_In(75450).get(2).intValue() == 33);
		assertEquals(27, Inv.Get_Item_Qty(75450));
	}
	/**
	 * tests that the container is removed from the list if it is emptied of an item
	 */
	@Test
	public void testEmpty_Container(){
		Inv.Add_Inventory(75450, "Baseball");
		Inv.Put_Container(75450, 11, 13);
		Inv.Put_Container(75450, 12, 17);
		
		assertTrue(Inv.Contained_In(75450).get(0).intValue() == 11 || Inv.Contained_In(75450).get(0).intValue() == 12);
		assertTrue(Inv.Contained_In(75450).get(1).intValue() == 11 || Inv.Contained_In(75450).get(1).intValue() == 12);
		assertTrue(Inv.Contained_In(75450).size() == 2);
		Inv.Take_Container(75450, 11, 13);
		
		assertTrue(Inv.Contained_In(75450).get(0).intValue() == 12);
		assertTrue(Inv.Contained_In(75450).size() == 1);
		
		Inv.Take_Container(75450, 12, 17);
		
		assertTrue(Inv.Contained_In(75450).get(0).intValue() == 0);
		
		assertEquals(0, Inv.Get_Item_Qty(75450));
	}
}

