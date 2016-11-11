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
	public ArrayList<Point> RouteFinding(Point target, Point RobotLoc, ArrayList<route> r){
		ArrayList<Point> allpoint = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		ArrayList<Point> finalPoints= new ArrayList<Point>();
		HashMap<Point, Integer> m_map = new HashMap<Point, Integer>();
		for(int i = 0; i< r.size();i++){
			temp = r.get(i).range();
			for(int j = 0; j< temp.size();j++){
				allpoint.add(temp.get(i));
			}
		}
		for(int i = 0; i< allpoint.size();i++){
			m_map.put(allpoint.get(i),1);
		}
		finalPoints = pathTraveling(RobotLoc,target,m_map);
		return finalPoints;
	}
	public ArrayList<Point> pathTraveling(Point s, Point e,HashMap<Point, Integer> m_map){
		ArrayList<Point> result = new ArrayList<Point>();
		if(s.GetX() == e.GetX()&&s.GetY() == e.GetY()){
			return result;
		}else{
			Point temp = new Point(s.GetX()+1,s.GetY());
			if(m_map.containsKey(temp)){
				result.add(temp);
				ArrayList<Point> tempAL = new ArrayList<Point>();
				tempAL  = pathTraveling(temp,e,m_map);
				for(int i = 0;i<tempAL.size();i++){
					result.add(tempAL.get(i));
				}
				return result;
			}
			temp = new Point(s.GetX()-1,s.GetY());
			if(m_map.containsKey(temp)){
				result.add(temp);
				ArrayList<Point> tempAL = new ArrayList<Point>();
				tempAL  = pathTraveling(temp,e,m_map);
				for(int i = 0;i<tempAL.size();i++){
					result.add(tempAL.get(i));
				}
				return result;
			}
			temp = new Point(s.GetX(),s.GetY()-1);
			if(m_map.containsKey(temp)){
				result.add(temp);
				ArrayList<Point> tempAL = new ArrayList<Point>();
				tempAL  = pathTraveling(temp,e,m_map);
				for(int i = 0;i<tempAL.size();i++){
					result.add(tempAL.get(i));
				}
				return result;
			}
			temp = new Point(s.GetX(),s.GetY()+1);
			if(m_map.containsKey(temp)){
				result.add(temp);
				ArrayList<Point> tempAL = new ArrayList<Point>();
				tempAL  = pathTraveling(temp,e,m_map);
				for(int i = 0;i<tempAL.size();i++){
					result.add(tempAL.get(i));
				}
				return result;
			}
		}
		return null;
	}
}
