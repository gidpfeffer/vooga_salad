package gameengine.actors.status;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import types.BasicActorType;

public abstract class IStatus<G extends ReadableGrid> implements IActProperty<G>{

	private BasicActorType myTarget;
	private double myDamage;
	private StatusDuration myLife;
	
	public IStatus(StatusData data) {
		myTarget = data.targetType();
		myDamage = data.getDamage();
		myLife = new StatusDuration(data.getLength());
	}
	
	protected BasicActorType getMyTarget() {
		return myTarget;
	}
	
	@Override
	public boolean isOn() {
		return myLife.stillOn();
	}
	
}
