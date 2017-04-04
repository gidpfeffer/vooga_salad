package gameengine.actors.properties;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class MoveWithSetPath {

	/**
	 * this property needs the path as list of coordinates. The pathCoordinates list comes from 
	 * PathFinder.getPathCoordinates(Integer enemy Path Index,  double increment) 
	 * @param pathCoordinates
	 */
	
	private Queue<Grid2D> myPathCoordinates;
	public MoveWithSetPath(List<Grid2D> pathCoordinates){
		myPathCoordinates = new LinkedList<>(pathCoordinates); 
	}
	
	public void action(ReadAndMoveGrid g){
		move(g);
	}
	
	private void move(ReadAndMoveGrid g) {
		if (!myPathCoordinates.isEmpty()){
			// poll a coordinate from myPathCoordinates to set the enemy location to
			Grid2D newLoc = myPathCoordinates.poll();
			g.move(myID, newLoc.getX(), newLoc.getY()); // where do i get this id ?
		}
	}
}
