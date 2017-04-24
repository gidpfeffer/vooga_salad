package gamedata.composition;


import java.util.ArrayList;
import java.util.List;

import gamedata.backend_generated_data.Path;
import gamedata.compositiongen.MoveData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
/**
 * @author maddie
 * @author Anh
 *
 */
public class MoveWithSetPathData extends MoveData {

	List<Path>myPaths;
	
	public MoveWithSetPathData(){
		this(new ArrayList<Path>(), 0.0);
	}
	
	/**
	 * this data object gets a list of assigned paths, pick a random one and call PathFinder to calculate the full 
	 * path with regards to speed. 
	 * 
	 * @param myAssignedPaths  get this from PathData.getAssignedPaths the list of path options without taking into
	 * account the speed
	 * @param speed
	 */
	
	public MoveWithSetPathData(List<Path> myAssignedPaths, Double speed){
		super(speed);
		myPaths = myAssignedPaths;
	}

	public List<List<Grid2D>> getMyPaths() {
		List toRet = new ArrayList<List<Grid2D>>();
		for(Path p: myPaths){
			toRet.add(p.getPath());
		}
		return toRet;
	}

	public void setMyPaths(List<List<Grid2D>> myPaths) {
		List toRet = new ArrayList<Path>();
		for(List<Grid2D> p: myPaths){
			toRet.add(new Path(p));
		}
		myPaths = toRet;
	}

	
}
