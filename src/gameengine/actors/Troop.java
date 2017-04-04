package gameengine.actors;


import java.util.List;

import gameengine.actors.properties.Health;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class Troop <T extends ReadableGrid> extends AbstractActor<T> {

	public Troop(Health h, List<IActProperty> properties){	
	}
	
	@Override
	public void act(T out) {
		// TODO Auto-generated method stub
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

