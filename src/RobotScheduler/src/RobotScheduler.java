import java.util.*;

class Location{
	private int first;
	private int second;
	
	public Location(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public int getFirst() {return first;}
	public int getSecond() {return second;}
}

class Shelf {
	String ID = "";
	Location loc = new Location(10, 0);
	
	public Shelf(String ID, Location loc) {
		this.ID = ID;
		this.loc = loc;
	}
	
	public Location getLocation() {return loc;}
}

class Charger {
	String ID = "";
	Location loc = new Location(5, 0);
	
	public Charger(String ID, Location loc) {
		this.ID = ID;
		this.loc = loc;
	}
	
	public Location getLocation() {return loc;}
}

class Robot {
	String ID = "";
	Location curr = new Location(0, 0);
	Location next = null;
	Shelf passenger = null;
	private boolean hasShelf = false;
	private boolean charged = true;
	int priority = 0;
	
	public Robot(String ID, Location curr, Location next, Shelf passenger, boolean hasShelf, boolean charged, int priority) {
		this.ID = ID;
		this.curr = curr;
		this.next = next;
		this.passenger = passenger;
		this.hasShelf = hasShelf;
		this.charged = charged;
		this.priority = priority;
	}
	
	public void goTo(Location l) {
		curr = l;
		next = null;
	}
	
	public boolean getHasShelf() {return hasShelf;}
	public boolean getCharged() {return charged;}
	public int getPriority() {return priority;}
	
	public void setHasShelf() {hasShelf = true;}
	public void setCharged() {charged = false;}
}

class RobotScheduler {
	String name;
	ArrayList<Location> locationList = new ArrayList<Location>();
	
	public RobotScheduler(String name) {
		this.name = name;
	}
	
	public void pickShelf(Robot r, Shelf s) {
		if (r.getHasShelf() == false && r.getCharged() == true) {
			r.goTo(s.getLocation());
			r.setHasShelf();
		}
	}
	
	public void recharge(Robot r, Charger c) {
		if (r.getHasShelf() == false && r.getCharged() == false) {
			r.goTo(c.getLocation());
		}
	}
	
	public void setPriority(Robot r1, Robot r2) {
		if (r1.next == r2.next && r1.getPriority() == r2.getPriority()) {
			r1.priority ++;
		}
	}
	
}
