package Floor;
public class Shelf extends Thing{
	public String name;
	//public  HashMap<Integer, String> items;
	public Shelf(int x ,int y, int id){
		super(x,y,7,1);
		name = "Great Shelf NO:" + id;
		//items.put();
	}
}
