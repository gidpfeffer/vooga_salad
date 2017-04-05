package gameengine.actors;

import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;

public abstract class Base extends AbstractActor{
	
	public Base(Integer id, HealthProperty health, List<IActProperty> properties){
		//TODO: Complete
		super(id, health);
	}
}
