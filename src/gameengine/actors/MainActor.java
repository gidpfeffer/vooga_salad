package gameengine.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public class MainActor implements Actor {

	protected Integer myID;
	protected HealthProperty myHealth;
	private BasicActorType myType;
	private List<IActProperty<MasterGrid>> myProperties;

	@SuppressWarnings("unchecked")
	public MainActor(BasicActorType type, Integer id, HealthProperty health, IActProperty<MasterGrid>... properties) {
		myType = type;
		myID = id;
		myHealth = health;
		myProperties = new ArrayList<>();
		for (IActProperty<MasterGrid> p : properties) {
			myProperties.add(p);
		}
	}

	@Override
	public void act(MasterGrid grid) {
		myProperties.stream().filter(prop -> prop.isOn()).forEach(prop -> prop.action(grid, myID));
	}

	@Override
	public boolean isActive() {
		return myHealth.isAlive();
	}

	@Override
	public Consumer<Double> applyDamage() {
		return (damage) -> {
			myHealth.apply(damage);
		};
	}

	@Override
	public Integer getID() {
		return myID;
	}

	@Override
	public Consumer<IActProperty<MasterGrid>> addProperty() {
		return (property) -> {
			myProperties.add(property);
		};
	}

	@Override
	public BasicActorType getType() {
		return myType;
	}

}