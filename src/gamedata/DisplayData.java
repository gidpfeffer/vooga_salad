package gamedata;

import java.util.List;

/**
 * Display information
 * 
 * @author maddiebriere
 *
 */

public class DisplayData {
	
	private static final String DEFAULT_POSITION= "Left";
	private static final int DEFAULT_WIDTH=80;
	private static final int DEFAULT_HEIGHT=400;
	private int width;
	private int height;
	private String location;
	private List<ActorData>actorOrder;
	
	public DisplayData(){
		this(DEFAULT_WIDTH,DEFAULT_HEIGHT,DEFAULT_POSITION);
	}
	
	public DisplayData(int width, int height, String loc) {
		super();
		this.width=width;
		this.height=height;
		this.location=loc;
	}
	public void setActorOrder(List<ActorData>list){
		actorOrder=list;
	}
	public List<ActorData> getActorOrder(){
		return actorOrder;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String toString(){
		return "width:"+this.width+" height:"+this.height+"loc:"+this.location+"order:"+actorOrder.toString();
	}
	
	
}
