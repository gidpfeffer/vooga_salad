package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public abstract class MoveWithRangeProperty<G extends MasterGrid> implements IActProperty<G> {

	private double myRange;
	private double mySpeed;
	private double distanceTraveled;

	public MoveWithRangeProperty(double range, double speed) {
		myRange = range;
		mySpeed = speed;
		distanceTraveled = 0;
	}

	@Override
	public void action(G grid, Integer actorID) {
		moveProj(grid, actorID, mySpeed);
		if ((distanceTraveled += mySpeed) > myRange) {
			grid.getMyDamageable(actorID).accept(Double.POSITIVE_INFINITY);
		}
	}

	abstract protected void moveProj(G grid, Integer actorID, double speed);

	@Override
	public boolean isOn() {
		return true;
	}
}
