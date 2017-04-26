package gameengine.actors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.propertygen.HealthProperty;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public class MainActor implements Actor {

	protected Integer myID;
	protected Integer myOption;
	protected HealthProperty myHealth;
	private BasicActorType myType;
	private List<IActProperty<MasterGrid>> myProperties;
	private List<IActProperty<MasterGrid>> myExits;
	
	public MainActor(BasicActorType type, Integer option, 
			Integer id, HealthProperty health) {
		myType = type;
		myID = id;
		myHealth = health;
		myProperties = new ArrayList<>();
		myExits = new ArrayList<>();
		myOption = option;
	}
	
	@SuppressWarnings("unchecked")
	public MainActor(BasicActorType type, Integer option, 
			Integer id, HealthProperty health, IActProperty<MasterGrid>... properties) {
		this(type, option, id, health);
		for (IActProperty<MasterGrid> p : properties) {
			myProperties.add(p);
			System.out.println(p.toString());
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

	@Override
	public Integer getMyOption() {
		return myOption;
	}

	@Override
	public double getPercentHealth() {
		return myHealth.getPercent();
	}
	
	@Override
	public double getRemainingHealth() { 
		return myHealth.getRemaining();
	}

	public List<IActProperty<MasterGrid>> getMyProperties() {
		return myProperties;
	}

	@Override
	public void exit(MasterGrid grid) {
		myExits.stream().forEach(prop -> prop.action(grid, myID));
	}

	@Override
	public void changeExit(Consumer<Collection<IActProperty<MasterGrid>>> action) {
		action.accept(myExits);
	}
	
	
	
	

}