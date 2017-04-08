package gamedata.composition;

import java.util.List;
import java.util.Map;
import java.util.Random;

import gamedata.compositiongen.MoveData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.path.PathFinder;

public class MoveWithSetPathData extends MoveData{

	List<Grid2D>myPath;
	
	/**
	 * Add a path to the list of paths available for movement --
	 * full path not taking into account speed
	 * 
	 * @param newPath Path to add
	 */

	public MoveWithSetPathData(Map<Integer,List<Grid2D>> allPaths, double speed){
		super(speed);
		myPath = getRandomSteps(allPaths);
	}

	/**
	 * Get a random path of the available options, taking
	 * into account how speed will affect step size.
	 * 
	 * @return Random path, accommodating speed
	 */
	public List<Grid2D> getRandomSteps(Map<Integer,List<Grid2D>> allPaths){
		PathFinder myPath = new PathFinder(allPaths);
		int index = (new Random()).nextInt(allPaths.size());
		return myPath.getPathCoordinates(index, getMySpeed());
	}

	public List<Grid2D> getPath() {
		return myPath;
	}

	public void setPathList(List<Grid2D> path) {
		myPath = path;
	}
	
	
}
