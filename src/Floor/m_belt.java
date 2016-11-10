package Floor;

public class m_belt extends Thing{
	public static int x= 1 ;
	public static int y= 2;
	public static int w= 1;
	public static int l=8;
	public static boolean needtorun = false;
	public m_belt(){
		super(x,y,w,l);
	}
	public void runit(){
		needtorun = true;
	}
	public void stop(){
		needtorun = false;
	}
	
}
