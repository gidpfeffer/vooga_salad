package gamedata.composition;

import java.util.List;
import java.util.Map;
import java.util.Random;

import gameengine.grid.classes.Coordinates;
import gameengine.path.PathFinder;

public class MoveData {
	Map<Integer,List<Coordinates>> allPaths;
	private double mySpeed;
	
	public void addPath(List<Coordinates> newPath){
		int index = allPaths.size();
		allPaths.put(index, newPath);
	}
	
	public List<Coordinates> getRandomSteps(){
		PathFinder myPath = new PathFinder(allPaths);
		int index = (new Random()).nextInt(allPaths.size());
		return 
	}
}
