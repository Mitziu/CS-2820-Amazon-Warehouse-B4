//package RobotScheduler;
//
//import java.util.*;
//
//public class RobotScheduler {
//	String name;
//	ArrayList<Location> locationList = new ArrayList<Location>();
//
//	public RobotScheduler(String name) {
//		this.name = name;
//	}
//
//	public void PickShelf(Robots r, Shelf s) {
//		if (r.HasShelf() == false && r.AllowToMove() == true) {
//			r.move(s.getLoc());
//			r.CarryUp(s);
//			r.HasShelf() = true;
//		}
//	}
//
//	public void Recharge(Robots r, ChargingStation c) {
//		if (r.HasShelf() == false && r.AllowToMove() == false) {
//			r.move(c.getx(), c.gety());
//		}
//	}
//
//	public void SetPriority(Robots r1, Robots r2) {
//		if (r1.next == r2.next && r1.getPriority() == r2.getPriority()) {
//			r1.priority ++;
//		}
//	}
//
//}
