package gamedata.map;

import java.util.ArrayList;
import java.util.List;

import ui.authoring.map.Point;
import util.Location;

/**
 * @author TNK
 * @author Anh
 *
 */
public class PolygonData {
	private List<Location> myPoints;
	public PolygonData(List<Location> points){
		myPoints = points;
	}
	
	/**
	 * default polygon: the whole square screen 
	 */
	public PolygonData(){
		myPoints = new ArrayList<Location>(); 
		myPoints.add(new Location(0.0,0.0));
		myPoints.add(new Location(0.0,1.0));
		myPoints.add(new Location(1.0,1.0));
		myPoints.add(new Location(1.0,0.0));
	}
	
	public List<Location> getMyPoints(){
		return myPoints;
	}
}
