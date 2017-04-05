package gameengine.actors.towers;

import gameengine.actors.AbstractActor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class Tower<G extends ReadableGrid> extends AbstractActor<G> {
	
	public Tower(Integer id, HealthProperty health, IActProperty<? extends ReadableGrid>... properties) {
		super(id, health);
	}

	@Override
	public void act(G grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}

}