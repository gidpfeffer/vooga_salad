package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import gamedata.composition.ActorDamageableData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

public class ActorDamageableProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {

	private Collection<BasicActorType> myTypes;
	private double hitBox;

	public ActorDamageableProperty(ActorDamageableData data) {
		hitBox = data.getMyHitRadius();
		myTypes = new ArrayList<>();
		for (BasicActorType t : data.getMyEnemyTypes()) {
			myTypes.add(t);
		}
	}

	public void action(G grid, Integer actorID) {
		myTypes.stream().forEach(type -> grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(),grid.getLocationOf(actorID).getY(), hitBox, type)
							.forEach((damage, health) -> damageActor(grid, actorID, damage, health)));
	}

	protected void damageActor(G grid, Integer actorID, Consumer<Double> damage, Double health) {
		grid.getMyDamageable(actorID).accept(health);
		damage.accept(health);
	}

	@Override
	public boolean isOn() {
		return true;
	}
}