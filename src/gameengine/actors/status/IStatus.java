package gameengine.actors.status;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface IStatus<G extends ReadableGrid> extends IActProperty<G>{
	
	public void afflict(Integer id);

}
