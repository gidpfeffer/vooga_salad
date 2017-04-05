package gameengine.actors;

import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class AbstractActor <T extends ReadableGrid> implements Actor<T>{
	
	protected Integer myID;
	protected HealthProperty myHealth;
	
	public AbstractActor(Integer id, HealthProperty health) {
		myID = id;
		myHealth = health;
	}
		
	public boolean isActive() {
		return myHealth.isAlive();
	}
	
	public Consumer<Double> applyDamage() {
		return (damage) -> {
			myHealth.apply(damage);
		};
	}
	
	protected Integer getID() {
		return myID;
	}

}