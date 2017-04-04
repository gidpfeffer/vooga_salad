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
	public Point(Location loc, PointType pointType){
		super(loc.getX(), loc.getY(), 8);
		setFill(pointTypeToColor.get(pointType));
	}
	
}
