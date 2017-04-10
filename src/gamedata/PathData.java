package gamedata;

import java.util.List;
import java.util.Map;

import gameengine.grid.interfaces.Identifiers.Grid2D;

/**
 * Class to represent all Paths created in the authoring 
 * environment, mapped to integers for use in actor g
 * 
 * @author maddiebriere
 * @author Ahn
 *
 */

public class PathData {
	private Map <Integer, List<Grid2D>> myPaths;
	private int numOptions;
	
	public PathData(Map <Integer, List<Grid2D>> paths){
		myPaths = paths;
	}
	
	//ADD METHOD FOR RETURNING SUB-MAPS
	
	//@Ahn methods
	
	//Add methods for communications with front end
	public List<Grid2D> getPathByIndex(Integer index){
		return null;
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
