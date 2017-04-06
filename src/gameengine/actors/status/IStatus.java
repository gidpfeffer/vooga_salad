package gameengine.actors.status;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface IStatus<G extends ReadableGrid> extends IActProperty<G>{
	
	public void afflict(Integer id);

}
