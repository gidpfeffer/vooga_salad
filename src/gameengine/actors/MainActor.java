package gameengine.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.propertygen.DamageProperty;
import gameengine.actors.propertygen.HealthProperty;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public class MainActor implements Actor {

	protected Integer myID;
	protected Integer myOption;
	protected HealthProperty myHealth;
	private BasicActorType myType;
	private DamageProperty myDamageToPlayer; //damage of this actor to base/player (0 for every actor except Troop )
	private List<IActProperty<MasterGrid>> myProperties;

	@SuppressWarnings("unchecked")
	public MainActor(BasicActorType type, Integer option, 
			Integer id, HealthProperty health, DamageProperty damageToPlayer) {
		myType = type;
		myID = id;
		myHealth = health;
		myProperties = new ArrayList<>();
		myDamageToPlayer = damageToPlayer;
	}
	
	@SuppressWarnings("unchecked")
	public MainActor(BasicActorType type, Integer option, 
			Integer id, HealthProperty health,DamageProperty damageToPlayer, IActProperty<MasterGrid>... properties) {
		myType = type;
		myID = id;
		myHealth = health;
		myProperties = new ArrayList<>();
		myDamageToPlayer = damageToPlayer;
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

	@Override
	public Integer getMyOption() {
		return myOption;
	}
	
	public double getMyDamageToPlayer(){
		return myDamageToPlayer.getDamageToPlayer();
	}
	
	

}