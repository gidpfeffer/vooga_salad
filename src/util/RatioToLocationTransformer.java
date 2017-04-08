package util;

public class RatioToLocationTransformer {
public static Location getLocation(double xRatio,double yRatio,double width, double height) {	
	return new Location(xRatio*width,yRatio*height);
}
}
