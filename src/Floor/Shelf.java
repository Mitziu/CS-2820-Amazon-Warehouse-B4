package Floor;
public class Shelf extends Thing{
	public String name;
	//public  HashMap<Integer, String> items;
                      public int id;
	public Shelf(int x ,int y, int id){
		super(x,y,1,1);
		name = "Great Shelf NO:" + id;
                                           this.id = id;
		//items.put();
	}
}
