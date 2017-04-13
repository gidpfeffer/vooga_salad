package gameengine.actors.properties;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import gamedata.composition.DamageableData;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

public class DamageableSingleTargetWeakProperty<G extends ReadAndDamageGrid> extends DamageableProperty<G> {

	public DamageableSingleTargetWeakProperty(DamageableData data) {
		super(data);
	}

	@Override
	protected Collection<Consumer<Double>> getEnemiesToHit(Collection<BasicActorType> myTypes, G grid, Integer actorID,
			Double radius) {
		Map<Double, Consumer<Double>> damageables = new HashMap<>();
		myTypes.stream().forEach(type -> grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(),grid.getLocationOf(actorID).getX(), radius, type).forEach((health,damage) -> damageables.put(health,damage)));
		return Arrays.asList(damageables.get(damageables.keySet().stream().min(Comparator.comparingDouble(key -> key)).get()));
	}

}
