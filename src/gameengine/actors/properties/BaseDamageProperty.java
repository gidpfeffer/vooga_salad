package gameengine.actors.properties;

import gamedata.composition.BaseDamageData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

/**
 * 
 * @author Moses Wayne
 *
 * @param <G> grid implementation that extends the ReadAndDamageGrid
 */
public class BaseDamageProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {

	private double myRadius;
	private BasicActorType myTarget;

	public BaseDamageProperty(BaseDamageData data) {
		myRadius = data.getMyRadius();
		myTarget = data.getMyTarget();
	}

	@Override
	public void action(G grid, Integer actorID) {
		grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), myRadius, myTarget).forEach((remaining,damage) -> {
			damage.accept(remaining);
			grid.getMyDamageable(actorID).accept(remaining);
				});
	}

	@Override
	public boolean isOn() {
		return true;
	}

}
