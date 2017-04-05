package gameengine.actors;

import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;

public abstract class AbstractActor implements Actor {
	
	private Integer myID;
	private HealthProperty myHealth;
	
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