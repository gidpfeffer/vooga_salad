package gameengine.actors.properties;

import gamedata.composition.BaseDamageData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

public class BaseDamageProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {

	private double myRadius;
	private BasicActorType myTarget;

	public BaseDamageProperty(BaseDamageData data) {
		myRadius = data.getMyRadius();
		myTarget = data.getMyTarget();
	}

	@Override
	public void action(G grid, Integer actorID) {
		grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), myRadius, myTarget).stream().forEach(damageable -> {
			double remaining = 0;
				});
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
