package gamedata.composition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.path.PathFinder;

public class MoveWithSetPathData implements Data{

	Map<Integer,List<Grid2D>> allPaths;
	private double mySpeed;
	
	/**
	 * Add a path to the list of paths available for movement --
	 * full path not taking into account speed
	 * 
	 * @param newPath Path to add
	 */
	
	public MoveWithSetPathData() {
		allPaths = new HashMap<>();
		mySpeed = 0.3;
	}
	
	public void addFullPath(List<Grid2D> newPath){
		int index = allPaths.size();
		allPaths.put(index, newPath);
	}
	
	/**
	 * Get a random path of the available options, taking
	 * into account how speed will affect step size.
	 * 
	 * @return Random path, accommodating speed
	 */
	public List<Grid2D> getRandomSteps(){
		PathFinder myPath = new PathFinder(allPaths);
		int index = (new Random()).nextInt(allPaths.size());
		return myPath.getPathCoordinates(index, mySpeed);
	}

	public Map<Integer, List<Grid2D>> getAllPaths() {
		return allPaths;
	}

	public void setAllPaths(Map<Integer, List<Grid2D>> allPaths) {
		this.allPaths = allPaths;
	}
	
	
}
