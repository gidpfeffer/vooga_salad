package ui.authoring.map;

import java.util.HashMap;
import java.util.Map;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.general.CustomColors;
import util.Location;
import util.Tuple;

public class Point extends Circle implements Grid2D{
	
	@SuppressWarnings("serial")
	Map<PointType, Color> pointTypeToColor = new HashMap<PointType, Color>() {{
        put(PointType.ENTRY, CustomColors.INDIGO);
        put(PointType.PATH, CustomColors.GREEN_900);
        put(PointType.EXIT, CustomColors.AMBER);

        //etc
    }};
    
    private Location myCompressedLocation;
    private Color myColor = CustomColors.GREEN_900;
    private boolean isValid=false;
    
    /**
     * 
     * @param gridLocation - the location of the mouse that was captured on click
     * @param size - the size of the parent node that holds the image
     * @param insets - use ImageViewPane's getInsets method to provide this
     */
	public Point(Location gridLocation,  Tuple<Double, Double> size, Tuple<Double, Double> insets){
		super();
		myCompressedLocation = compressLocation(gridLocation, insets,getImageSize(size, insets));
		isBetweenOneAndZero(myCompressedLocation);
		updateLocation(getImageSize(size, insets), insets);
		setColor(myColor);
		setRadius(7);
	}

	
	private Tuple<Double, Double> getImageSize(Tuple<Double, Double> size, Tuple<Double, Double> insets){
		return new Tuple<Double, Double>(size.x - 2*insets.x, size.y - 2*insets.y);
	}
	
	private void updateLocation(Tuple<Double, Double> imageSize, Tuple<Double, Double> insets){
		this.setCenterX(myCompressedLocation.getX()*imageSize.x + insets.x);
		this.setCenterY(myCompressedLocation.getY()*imageSize.y + + insets.y);
	}
	private Location compressLocation(Location location,Tuple<Double, Double> insets, Tuple<Double, Double> imageSize){
		 return new Location((location.getX() - insets.x)/imageSize.x, (location.getY() - insets.y)/imageSize.y);
	}
	
	public void updateSize(Tuple<Double,Double> size, Tuple<Double,Double> insets){
		updateLocation(getImageSize(size, insets),insets);
	}
	

	
	private void isBetweenOneAndZero(Location l){
		isValid =  !(l.getX()>1 || l.getY() > 1 || l.getX() < 0 || l.getY() < 0);
	}


	@Override
	public double getX() {
		return myCompressedLocation.getX();
	}

	@Override
	public double getY() {
		return myCompressedLocation.getY();
	}

	@Override
	public String toString() {
		return getX() + " : " + getY();
	}
	
	public boolean isValid(){
		return isValid;
	}
	
	public void setPointType(PointType pointType){
		setColor(pointTypeToColor.get(pointType));
	}
	
	public void setColor(Color c){
		myColor = c;
		setFill(c);
	}


}
