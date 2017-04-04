package gameengine.actors;


import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class Base <T extends ReadableGrid> extends AbstractActor<T>{

	public Base(HealthProperty h, List<IActProperty> properties){
		//TODO: Complete
	}
}
