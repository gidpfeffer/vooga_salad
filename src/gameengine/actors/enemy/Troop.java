package gameengine.actors.enemy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;


/**
 * @author Anh
 *
 */

public class Troop extends Troop<ReadableGrid> {

	List<IActProperty<ReadableGrid>> myProperties; 
	private int myID;
	public Troop(HealthProperty h, List<IActProperty<ReadableGrid>> properties) {
		super(h, properties);
		myProperties = properties;
		// TODO Auto-generated constructor stub
	}



	@Override
	public void act(ReadableGrid grid) {
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
