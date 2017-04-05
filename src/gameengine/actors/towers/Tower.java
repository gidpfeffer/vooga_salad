package gameengine.actors.towers;

import gameengine.actors.AbstractActor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class Tower extends AbstractActor<ReadShootMoveGrid> {
	
	public Tower(Integer id, HealthProperty health, IActProperty<? extends ReadableGrid>... properties) {
		super(id, health);
	}

}