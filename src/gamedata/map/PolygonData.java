package gamedata.map;

import java.util.List;

import ui.authoring.map.Point;

public class PolygonData {
	private List<Point> myPoints;
	public PolygonData(List<Point> points){
		myPoints = points;
	}
	
	public List<Point> getMyPoints(){
		return myPoints;
	}
}
