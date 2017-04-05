package gameengine.actors;

import gameengine.actors.properties.HealthProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class Shot<G extends ReadableGrid> extends AbstractActor<G>{

	public Shot(Integer id, HealthProperty health) {
		super(id, health);
		// TODO Auto-generated constructor stub
	}

}
