package Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class route extends Thing{
	public String name;
	public int id;
	public route(int x_loc,int y_loc, int w, int l, int id){ //x,y here is the left start point of the Floor.route
		super(x_loc,y_loc,width,length);
		this.id = id;
		name = "Hawk Highway" + id;
	}
	public ArrayList<Point> range(){
		ArrayList<Point> result = new ArrayList<Point>();
		for(int x = x_loc;x<x_loc+width;x++){
			for(int y = y_loc; y<y_loc+length;y++){
				Point temp = new Point(x,y);
				result.add(temp);
			}
		}
		return result;
	}
	
}
