package util;

import java.util.ArrayList;
import java.util.List;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;

/**
 * 
 * Util class for path calculations
 *
 * @author Anh
 * @author maddiebriere
 *
 */

public class PathUtil {

	public static double getDistance(Grid2D point1,Grid2D point2){
		return Math.sqrt(Math.pow(point2.getX() - point1.getX(),2) 
				+ Math.pow(point2.getY() - point1.getY(),2));
	}
	
	public static double getAngle(Grid2D point1,Grid2D point2){
		return Math.atan2(point2.getY() - point1.getY(), point2.getX() - point1.getX());
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @param increment
	 * @return a list of coordinates from start to end, separated by increment distance
	 */
	public static List<Grid2D> getIncrementPoints(Grid2D start,Grid2D end, double increment){
		List<Grid2D> result = new ArrayList<Grid2D>();
		double distance = getDistance(start, end); 
		System.out.println("distance between " + start.asString() + " and " + end.asString() + " is: " + distance);
		
		double angle = getAngle(start,end);
		System.out.println("angle between " + start.asString() + " and " + end.asString() + " is: " + angle);
		
		int numIncrements = (int) (distance % increment == 0 ? (distance/increment)-1: (distance/increment));
		System.out.println("Num of increments " + numIncrements);
		
		for (int i = 0; i <= numIncrements; i++){
			double newX = start.getX() + increment* i * Math.cos(angle);
			double newY = start.getY() + increment* i * Math.sin(angle);
			result.add(new Coordinates(newX,newY));
		}
		result.add(new Coordinates(end));
		return result; 
	}
	
}
