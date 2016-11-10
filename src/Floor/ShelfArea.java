package Floor;

public class ShelfArea extends Thing{
	public String name;
	public ShelfArea(int x ,int y, int id){
		super(x,y,7,1);
		name = "Great Shelf NO:" + id;
		
	}
}
