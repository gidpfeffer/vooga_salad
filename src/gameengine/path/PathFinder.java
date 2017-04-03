package gameengine.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.grid.Grid2D;

/**
 * return a path as a list of all coordinates for an enemy to go for each step. It should know the path index and the
 * increment (amount of pixel it moves per step/ speed of the enemy). The factory instantiate this PathFinder class, 
 * and uses it to get a list of path coordinates to be passed into each enemy object (through their constructors)
 * @author Anh
 *
 */
public class PathFinder  {
	
	private Map<Integer,List<Grid2D>> myPaths; 
	
	public PathFinder(Map<Integer,List<Grid2D>> allPaths ){
		myPaths = allPaths;	
	}
	
	public List<Grid2D> getPathCoordinates(Integer pathNo, double increment){
		List<Grid2D> coordinatesToReturn = new ArrayList<Grid2D>(); 
		List<Grid2D> pathChosen = myPaths.get(pathNo); 
		
		for (int i = 0; i< pathChosen.size()-1; i++ ){
			
			coordinatesToReturn.addAll(getIncrementPoints(pathChosen.get(i),pathChosen.get(i+1),increment));
			coordinatesToReturn.add(new Grid2D(pathChosen.get(i+1)));
		}
		
		return coordinatesToReturn;
	}
	

	
	private List<Grid2D> getIncrementPoints(Grid2D start,Grid2D end, double increment){
		List<Grid2D> result = new ArrayList<Grid2D>();
		double distance = getDistance(start, end); 
		double angle = getAngle(start,end);
		int numIncrements = (int) (distance % increment == 0 ? (distance/increment)-1: (distance/increment));
		
		for (int i = 0; i <= numIncrements; i++){
			double newX = start.getX() + distance* i * Math.cos(angle);
			double newY = start.getY() + distance* i * Math.sin(angle);
			result.add(new Grid2D(newX,newY));
		}
		return result; 
	}
	
	private double getDistance(Grid2D point1,Grid2D point2){
		return Math.sqrt(Math.pow(point2.getX() - point1.getX(),2) 
				+ Math.pow(point2.getY() - point1.getY(),2));
	}
	
	private double getAngle(Grid2D point1,Grid2D point2){
		return Math.atan2(point2.getY() - point1.getY(), point2.getX() - point1.getX());
	}
	
	
	
	
	
	public static void main(String[] args){
		Map<Integer,List<Grid2D>> map = new HashMap<Integer, List<Grid2D>>();
		List<Grid2D> wayPoints = new ArrayList<Grid2D>(); 
		wayPoints.add(new Grid2D(0,0));
		wayPoints.add(new Grid2D(5,0));
		wayPoints.add(new Grid2D(10,0));
		wayPoints.add(new Grid2D(13,0));
		
		
		
		map.put(0, wayPoints);
		
		PathFinder test = new PathFinder(map); 
		List<Grid2D> path = test.getPathCoordinates(0,2);
		
		for (Grid2D point :path){
			System.out.println(point.getX() + " " + point.getY());
		}
	}

}

