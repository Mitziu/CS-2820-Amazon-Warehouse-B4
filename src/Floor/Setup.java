package Floor;

import java.util.ArrayList;
import java.util.HashMap;
public class Setup implements FloorPositions {
	floor f1;
	Station picker ;
	Station packager ;
	ChargingStation charge ;
	m_belt f_belt ; 
	ArrayList<Shelf> SA ;
	ArrayList<Robots> RB ;
	ArrayList<route> Routes;
                     ArrayList<Point> TotalPath = new ArrayList<Point>();
	public Setup(){	
	}
        @Override
	public void Initialize(){
		f1 = new floor();
		picker = new Station(1,10);
		packager =new Station(1,1);
		charge = new ChargingStation(2,1);
		f_belt = new m_belt();
		RB = new ArrayList<Robots>();
		SA = new ArrayList<Shelf>(); 
		Routes = new ArrayList<route>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		index.add(1);
		index.add(4);
		index.add(7);
		index.add(10);
		for(int i = 0; i< index.size();i++){
			for(int j = 0; j<7;j++){
				int y = index.get(i);
                                                                                String s = String.valueOf( 4+j )+ String.valueOf(i);
				Shelf temp = new Shelf(4+j,y,Integer.valueOf(s));
				SA.add(temp);
			}
		}
		route r_temp = new route(2,1,2,1,1);
		Routes.add(r_temp);
		r_temp = new route(2,2,9,1,2);
		Routes.add(r_temp);
		r_temp = new route(2,3,9,1,3);
		Routes.add(r_temp);
		r_temp = new route(2,4,2,1,4);
		Routes.add(r_temp);
		r_temp = new route(2,5,9,1,5);
		Routes.add(r_temp);
		r_temp = new route(2,6,9,1,6);
		Routes.add(r_temp);
		r_temp = new route(2,7,2,1,7);
		Routes.add(r_temp);
		r_temp = new route(2,8,9,1,8);
		Routes.add(r_temp);
		r_temp = new route(2,9,9,1,9);
		Routes.add(r_temp);
		Robots r1 = new Robots(2,10,1);
		Robots r2 = new Robots(3,10,1);
		RB.add(r1);
		RB.add(r2);
	}
        @Override
	public ArrayList<Shelf> getShelves(){
		return SA;
	}
        @Override
	public ArrayList<route> getRoutes(){
		return Routes;
	}
        @Override
	public Station getPicker(){
		return picker;
	}
        @Override
	public Station GetPackager(){
		return packager;
	} 
        @Override
	public ArrayList<Robots> getRobots(){
		return RB;
	}
        @Override
	public ChargingStation getChargingStation(){
		return charge;
	}
        @Override
	public m_belt getBelt(){
		return f_belt;
	}
        @Override
	public HashMap<String, Point> getAllPositions(){
                                        HashMap<String, Point> m_map = new HashMap<String, Point>();
                                        Point f = new Point(f1.getx(),f1.gety());
                                        m_map.put("floor", f);
                                        f = new Point(picker.getx(),picker.gety());
                                        m_map.put("picker", f) ;
                                        f = new Point(packager.getx(),packager.gety());
                                        m_map.put("packager", f) ;
                                        f = new Point(charge.getx(),charge.gety());
                                        m_map.put("charge", f) ;
                                        f = new Point(f_belt.getx(),f_belt.gety());
                                        m_map.put("belt", f) ;
                                        for(int i = 0;i<SA.size();i++){
                                                  f = new Point(SA.get(i).getx(),SA.get(i).gety());
                                                  m_map.put(SA.get(i).name, f) ;
                                         }
		return m_map;
	}
	public ArrayList<Point> RouteFinding(Point target, Point RobotLoc){
		ArrayList<route> r = Routes;
		ArrayList<Point> allpoint = new ArrayList<Point>();
		ArrayList<Point> temp = new ArrayList<Point>();
		ArrayList<Point> finalPoints= new ArrayList<Point>();
		HashMap<String, Integer> m_map = new HashMap<String, Integer>();
		for(int i = 0; i< r.size();i++){
			temp = r.get(i).range();
			for(int j = 0; j< temp.size();j++){
				allpoint.add(temp.get(j));
			}
		}
		for(int i = 0; i< allpoint.size();i++){
                                                                String m_str = allpoint.get(i).GetX()+","+allpoint.get(i).GetY();
			m_map.put(m_str,1);
		}
		pathTraveling(RobotLoc,target,m_map);
		return TotalPath;
	}
	public void pathTraveling(Point s, Point e,HashMap<String, Integer>  m_map){
		
		if(s.GetX() == e.GetX()-1&&s.GetY() == e.GetY()){
			return ;
		}else if(s.GetX() == e.GetX()+1&&s.GetY() == e.GetY()) {
			return ;
		} else if(s.GetX() == e.GetX()&&s.GetY() == e.GetY()-1) {
			return ;
		}else if(s.GetX() == e.GetX()&&s.GetY() == e.GetY()+1) {
			return ;
		}
                
                                          else{
			Point tempP = new Point(s.GetX()+1,s.GetY());
                                                                String str = s.GetX()+1+","+s.GetY();
			if(m_map.containsKey(str)){
				TotalPath.add(tempP);
				//RouteFinding(e,tempP);
				
			}
			Point tempP2 = new Point(s.GetX()-1,s.GetY());
                                                                str = s.GetX()-1+","+s.GetY();
			if(m_map.containsKey(str)){
				TotalPath.add(tempP2);
				//RouteFinding(e,tempP2);
			}
			Point tempP3 = new Point(s.GetX(),s.GetY()-1);
                                                                str = s.GetX()+","+(s.GetY()-1);
			if(m_map.containsKey(str)){
				TotalPath.add(tempP3);
				//RouteFinding(e,tempP3);
				
				
			}
                                                                str = s.GetX()+","+(s.GetY()+1);
			Point tempP4 = new Point(s.GetX(),s.GetY()+1);
			if(m_map.containsKey(str)){
				TotalPath.add(tempP4);
				//RouteFinding(e,tempP4);
				
				
			}
                        if(TotalPath.get(TotalPath.size()-1).GetX()==2||TotalPath.get(TotalPath.size()-1).GetX()==3){}else{
                                                    GoHorizontally(TotalPath.get(TotalPath.size()-1),new Point(2,TotalPath.get(TotalPath.size()-1).GetY()));
                        }
                        if(e.GetY()==10){ GoVertically(new Point(TotalPath.get(TotalPath.size()-1).GetX(),e.GetY()-1),TotalPath.get(TotalPath.size()-1));}else{
                                                    GoVertically(new Point(TotalPath.get(TotalPath.size()-1).GetX(),e.GetY()+1),TotalPath.get(TotalPath.size()-1));
                        }
                                                    GoHorizontally(TotalPath.get(TotalPath.size()-1),e);
		}
		return ;
           }
        public void GoVertically(Point e,Point s){
                        if(s.GetY()!=e.GetY()){
                            int diff = e.GetY()-s.GetY();
                            int sign;
                            if (diff>0){sign  =1;}else{sign = -1;}
                            Point newP = new Point(s.GetX(),s.GetY()+sign);
                            TotalPath.add(newP);
                            GoVertically(e,newP);
                        }
           }
           public void GoHorizontally(Point s,Point e){
                        if(s.GetX()!=e.GetX()){
                            int diff = e.GetX()-s.GetX();
                            int sign;
                            if (diff>0){sign  =1;}else{sign = -1;}
                            Point newP = new Point(s.GetX()+sign,s.GetY());
                            TotalPath.add(newP);
                            GoHorizontally(newP,e);
                        }
           }
                 /*public static void main(String [ ] args)
                     {
                                Setup s = new Setup();
                                s.Initialize();
                                Point rob_loc = s.getRobots().get(0).getLoc();
                                Point target = s.getShelves().get(0).getLoc();
                                ArrayList<Point> path = new ArrayList<Point>();
                                path = s.RouteFinding(target,rob_loc);
                                for(int i =0 ;i<path.size();i++){
                                           System.out.println(path.get(i).GetX()+","+path.get(i).GetY());
                                }
                     }*/
}
