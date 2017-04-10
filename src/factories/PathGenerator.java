package factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gamedata.GameData;
import gamedata.PathData;
import gamedata.composition.MoveWithSetPathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class PathGenerator {
	
	/**
	 * Get all of the current paths available in the game data,
	 * indexed by Integers. 
	 * 
	 * @param data The current GameData
	 * @return a List of Integers representing possible paths
	 */
	public static List<Integer> getCurrentPaths(GameData data){
		return new ArrayList<Integer>(data.getMyPaths().getMyPaths().keySet());
	}
	
	/**
	 * Get all of the current paths available in the game data,
	 * matched between integers (indices) and the list of points
	 * representing the path.
	 * 
	 * @param data The current GameData
	 * @return a Map of Integers representing possible paths to Paths
	 */
	public static Map<Integer,List<Grid2D>> getCurrentPathDetails(PathData data){
		return data.getMyPaths();
	}
	
	/**
	 * Add a path to the list of available paths in your GameData
	 * 
	 * @param data Current GameData
	 * @param newPath List of Grid2Ds representing the new path
	 */
	public static void addPath(PathData data, List<Grid2D> newPath){
		data.addPath(newPath);
	}
	
	/**
	 * Generate a MoveWithSetPathData given a list of Integer options
	 * (chosen by the user) that map to Paths and a speed for the
	 * movement.
	 * 
	 * @param data Current GameData
	 * @param pathChoices List of Integers mapped to path choices
	 * @param speed Speed of movement
	 * @return Generate MoveWithSetPathData
	 */
	public static MoveWithSetPathData
		generateMoveData(PathData data, List<Integer> pathChoices, double speed){
		List<List<Grid2D>> paths = new ArrayList<List<Grid2D>>();
		for(Integer choice: pathChoices){
			paths.add(data.getPathByIndex(choice));
		}
		return new MoveWithSetPathData(paths, speed);
	}
}
