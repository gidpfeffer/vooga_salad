package gameengine.actors.enemy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gameengine.actors.Troop;
import gameengine.actors.properties.Health;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;


/**
 * take in constructor a collection of coordinates, turn that into a queue, and poll a coordinate to move to at every
 * step. 
 * @author Anh
 *
 */

public class Enemy extends Troop<ReadAndMoveGrid> {

	List<IActProperty> myProperties; 
	private int myID;
	public Enemy(Health h, List<IActProperty> properties) {
		super(h, properties);
		myProperties = properties;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void act(ReadAndMoveGrid grid) {
		myProperties.forEach(e -> e.action(grid,myID));
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
