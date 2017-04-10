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

	//TODO: List of paths
	List<List<Grid2D>>myPaths;
	//ASSUME For the moment that integers passed are valid paths
	
	//TODO: Move path from this class?
	//May be superfluous with pathdata
	
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
		myPaths = myAssignedPaths;
	}

	public List<List<Grid2D>> getMyPaths() {
		return myPaths;
	}

	public void setMyPaths(List<List<Grid2D>> myPaths) {
		this.myPaths = myPaths;
	}

	
}
