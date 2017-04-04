package gameengine.actors.enemy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import gameengine.actors.Troop;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;


/**
 * take in constructor a collection of coordinates, turn that into a queue, and poll a coordinate to move to at every
 * step. 
 * @author Anh
 *
 */
public class Enemy extends Troop<ReadAndMoveGrid> {


	/** 
	 * @param pathCoordinates
	 */


	public Enemy(Health h, Property p...){
		
	}
	
	@Override
	public void act(ReadAndMoveGrid grid) {
		p.action(grid);		
		// more actions
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
