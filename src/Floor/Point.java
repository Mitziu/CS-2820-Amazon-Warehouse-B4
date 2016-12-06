package Floor;
public class Point {
	public int x;
	public int y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int GetX(){
		return x;
	}
	public int GetY(){
		return y;
	}

	/**
	 * @author Matt
	 * @param p
	 * @return boolean: if this point is some location as other point
	 */
	public boolean isEqual (Point p) {
		if (p.GetX() == x && p.GetY() == y) return true;
		else return false;
	}
}
