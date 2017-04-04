package gameengine.actors.properties;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface IActProperty<G extends ReadableGrid> {

	public void action(G grid, Integer actorID);
	
}
