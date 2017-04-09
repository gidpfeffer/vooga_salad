package gamedata;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gameengine.grid.interfaces.Identifiers.Grid2D;

/**
 * Class to represent all Paths created in the authoring 
 * environment, mapped to integers for use in actor creation.
 * 
 * @author maddiebriere
 * @author Anh
 *
 */

public class PathData {
	private Map <Integer, List<Grid2D>> myPaths; // the map of all user-defined paths in the game, indexed. 
	private int numOptions;
	
	public PathData(Map <Integer, List<Grid2D>> paths){
		myPaths = paths;
		numOptions = paths.size(); 
	}
	
	/**
	 * provide a sub-list of paths given the path indexes assigned for a type of enemy in a particular wave
	 * @param options the list of path indexes for a type of enemy defined in the authoring environment 
	 * @return a list of paths (have not account for speed yet) 
	 */
	public List<List<Grid2D>> getAssignedPaths(List<Integer> options){
		List<List<Grid2D>> myPathList = new ArrayList<List<Grid2D>>(); 
		options.forEach(i -> myPathList.add(myPaths.get(i)));
		return myPathList;
	}
	
	//Add methods for communications with front end
	public List<Grid2D> getPathByIndex(Integer index){
		return myPaths.get(index);
	}
	
	public void addPath(List<Grid2D> newPath){
		myPaths.put(numOptions++, newPath);
	}

	public Map<Integer, List<Grid2D>> getMyPaths() {
		return myPaths;
	}

	public void setMyPaths(Map<Integer, List<Grid2D>> myPaths) {
		this.myPaths = myPaths;
	}

}
