package gameengine.actors.enemy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gameengine.grid.Grid2D;
import gameengine.grid.interfaces.*;


/**
 * take in constructor a collection of coordinates, turn that into a queue, and poll a coordinate to move to at every
 * step. 
 * @author Anh
 *
 */
public class WeakEnemy extends Enemy<ReadAndMoveGrid> {
	private Queue<Grid2D> myPathCoordinates;
	private double myID; 

	/** 
	 * In the constructor, the pathCoordinates list comes from 
	 * PathFinder.getPathCoordinates(Integer enemy Path Index,  double increment) 
	 * @param pathCoordinates
	 */
	public WeakEnemy (Collection<Grid2D> pathCoordinates){ 
		myPathCoordinates = new LinkedList<>(pathCoordinates);	
	}
	
	@Override
	public void act(ReadAndMoveGrid grid) {
		move(grid);		
		// more actions
	}
	

	private void move(ReadAndMoveGrid grid) {
		if (!myPathCoordinates.isEmpty()){
			// poll a coordinate from myPathCoordinates to set the enemy location to
			Grid2D newLoc = myPathCoordinates.poll();
			grid.move(myID, newLoc.getX(), newLoc.getY());
		}
	}
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}


	
}
