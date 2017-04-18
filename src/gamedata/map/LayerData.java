package gamedata.map;

import java.util.List;

public class LayerData {
	
	private List<PolygonData> myPolygons;
	
	public LayerData(List<PolygonData> polygons){
		myPolygons = polygons;
	}
	
	public List<PolygonData> getMyPolygons(){
		return myPolygons;
	}
	
}
