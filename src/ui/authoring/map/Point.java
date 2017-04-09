package ui.authoring.map;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.general.CustomColors;
import util.Location;

public class Point extends Circle{
	
	@SuppressWarnings("serial")
	Map<PointType, Color> pointTypeToColor = new HashMap<PointType, Color>() {{
        put(PointType.ENTRY, CustomColors.INDIGO);
        put(PointType.PATH, CustomColors.GREEN_900);
        put(PointType.EXIT, CustomColors.AMBER);

        //etc
    }};
    
    private Location myGridLocation;
    
    /**
     * 
     * @param gridLocation this Location object should be between 1 and 0. It 
     * represents the ratio on the view 
     * @param pointType determines what kinds of point it is. Entry, Exit, Path, etc
     */
	public Point(Location gridLocation, PointType pointType, double viewWidth, double viewHeight){
		super();
		myGridLocation = gridLocation;
		setFill(pointTypeToColor.get(pointType));
		updateLocation(viewWidth, viewHeight);
		this.setRadius(8);
	}
	
	public void updateLocation(double viewWidth, double viewHeight){
		Location loc = new Location(myGridLocation.getX()*viewWidth, myGridLocation.getY()*viewHeight);
		this.setCenterX(loc.getX());
		this.setCenterY(loc.getY());
	}

	public Location getLocation() {
		// TODO Auto-generated method stub
		return myGridLocation;
	}

}
