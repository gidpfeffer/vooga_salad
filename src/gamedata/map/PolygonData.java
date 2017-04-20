package gamedata.map;

import java.util.ArrayList;
import java.util.List;

import ui.authoring.map.Point;

/**
 * @author TNK
 * @author Anh
 *
 */
public class PolygonData {
	private List<Point> myPoints;
	public PolygonData(List<Point> points){
		myPoints = points;
	}
	
	/**
	 * default polygon: the whole square screen 
	 */
	public PolygonData(){
		myPoints = new ArrayList<Point>(); 
		myPoints.add(new Point(0,0));
		myPoints.add(new Point(0,1));
		myPoints.add(new Point(1,1));
		myPoints.add(new Point(1,0));
	}
	
	public List<Point> getMyPoints(){
		return myPoints;
	}
}
