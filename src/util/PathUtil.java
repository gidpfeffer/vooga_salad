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
		System.out.println(point1.getX() + " " + point1.getY());
		System.out.println(point2.getX() + " " + point2.getY());
		return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) 
				+ Math.pow(point2.getY() - point1.getY(), 2));
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
		double angle = getAngle(start,end);
		int numIncrements = (int) (distance % increment == 0 ? (distance/increment)-1: (distance/increment));
	
		for (int i = 0; i <= numIncrements; i++){
			double newX = start.getX() + increment* i * Math.cos(angle);
			double newY = start.getY() + increment* i * Math.sin(angle);
			result.add(new Coordinates(newX,newY));
		}
		result.add(new Coordinates(end));
		return result; 
	}
	
	public static List<Grid2D> getPathCoordinates(List<Grid2D> pathChosen , double increment){
		
		List<Grid2D> coordinatesToReturn = new ArrayList<Grid2D>(); 
		for (int i = 0; i< pathChosen.size()-1; i++ ){
			coordinatesToReturn.addAll(getIncrementPoints(pathChosen.get(i),pathChosen.get(i+1),increment));
			coordinatesToReturn.remove(coordinatesToReturn.size()-1);
		}
		coordinatesToReturn.add(new Coordinates(pathChosen.get(pathChosen.size()-1))); // for the ending point
		return coordinatesToReturn;
	}
	
}
