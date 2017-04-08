package gamedata.composition;


import java.util.List;
import java.util.Random;

import gamedata.compositiongen.MoveData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;
/**
 * @author maddie
 * @author Anh
 *
 */
public class MoveWithSetPathData extends MoveData {

	List<Grid2D>myPath;
	
	
	
	/**
	 * this data object gets a list of assigned paths, pick a random one and call PathFinder to calculate the full 
	 * path with regards to speed. 
	 * 
	 * @param myAssignedPaths  get this from PathData.getAssignedPaths the list of path options without taking into
	 * account the speed
	 * @param speed
	 */
	public MoveWithSetPathData(List<List<Grid2D>> myAssignedPaths, double speed){
		super(speed);
		myPath = getRandomSteps(myAssignedPaths);
	}

	/**
	 * Get a random path of the available options, taking
	 * into account how speed will affect step size.
	 * 
	 * @param paths list of assigned paths 
	 * @return Random path, accommodating speed
	 */
	
	public List<Grid2D> getRandomSteps(List<List<Grid2D>> paths){

		int rand = (new Random()).nextInt(paths.size());
		return PathUtil.getPathCoordinates(paths.get(rand), getMySpeed());
	}

	public List<Grid2D> getPath() {
		return myPath;
	}

	public void setPathList(List<Grid2D> path) {
		myPath = path;
	}
	
	
}
