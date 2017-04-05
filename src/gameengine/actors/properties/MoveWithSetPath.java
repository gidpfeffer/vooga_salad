package gameengine.actors.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
/**
 * this property needs the path as list of coordinates. The pathCoordinates list comes 
 * from PathFinder.getPathCoordinates(Integer enemy Path Index,  double increment)  
 * @author Anh
 *
 */
public class MoveWithSetPath implements IActProperty<ReadAndMoveGrid>{

	/**
	 * @param pathCoordinates
	 */
	
	private Queue<Grid2D> myPathCoordinates;
	
	public MoveWithSetPath(List<Grid2D> pathCoordinates){
		myPathCoordinates = new LinkedList<>(pathCoordinates); 
	}
	
	@Override
	public void action(ReadAndMoveGrid grid, Integer actorID) {
		// TODO Auto-generated method stub
		move(grid,actorID);
	}

	private void move(ReadAndMoveGrid g, int id) {
		if (!myPathCoordinates.isEmpty()){
			// poll a coordinate from myPathCoordinates to set the enemy location to
			Grid2D newLoc = myPathCoordinates.poll();
			g.move(id, newLoc.getX(), newLoc.getY()); 
		}
	}
	
	
}
