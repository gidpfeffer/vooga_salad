package gameengine.actors;

import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class Enemy extends AbstractActor<ReadShootMoveGrid> {

	public Enemy(Integer id, HealthProperty health, List<IActProperty<ReadableGrid>> properties){	
		super(id, health);
	}

}

