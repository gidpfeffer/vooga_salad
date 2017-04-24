package gameengine.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

// NOT USING THIS, USE UTIL.PATHUTIL instead


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
			coordinatesToReturn.addAll(PathUtil.getIncrementPoints(pathChosen.get(i),pathChosen.get(i+1),increment));
			coordinatesToReturn.remove(coordinatesToReturn.size()-1);
		}
		coordinatesToReturn.add(new Coordinates(pathChosen.get(pathChosen.size()-1))); // for the ending point
		return coordinatesToReturn;
	}
	
	
	
	public static void main(String[] args){
		Map<Integer,List<Grid2D>> map = new HashMap<Integer, List<Grid2D>>();
		List<Grid2D> wayPoints = new ArrayList<Grid2D>(); 
		wayPoints.add(new Coordinates(0,0));
		wayPoints.add(new Coordinates(5,0));
		wayPoints.add(new Coordinates(10,0));
		wayPoints.add(new Coordinates(13,0));
		
		map.put(0, wayPoints);
		
		PathFinder test = new PathFinder(map); 
		List<Grid2D> path = test.getPathCoordinates(0,2);
		
		System.out.println("**********");
		
		//path.forEach(e -> System.out.println(e.asString()));
		
	}

}

