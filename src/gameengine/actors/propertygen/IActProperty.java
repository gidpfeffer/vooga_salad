package gameengine.actors.propertygen;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface IActProperty<G extends ReadableGrid> extends Property{

	public void action(G grid, Integer actorID);
	
	public boolean isOn();
	
}
