package Floor;

import java.util.ArrayList;
public class Robots extends Thing{ //contains robots and shelves
	public static int w= 1;
	public static int l=1;
	public static int id;
	public static ArrayList<String> items;
	public Robots(int x_loc, int y_loc, int s, ArrayList<String> items){
		super(x_loc,y_loc,w,l);
		super.AllowToMove();
		id = s;
		this.items = items;
	}
	public Robots(int x_loc, int y_loc, int s){
		super(x_loc,y_loc,w,l);
		super.AllowToMove();
		id = s;
	}
}
