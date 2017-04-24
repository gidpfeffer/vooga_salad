package gameengine.actors.status;

import gamedata.compositiongen.StatusData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import types.BasicActorType;

public abstract class IStatus<G extends ReadableGrid> implements IActProperty<G>{

	private BasicActorType myTarget;
	private StatusDuration myLife;
	
	public IStatus(StatusData data) {
		myTarget = data.getMyTarget();
		myLife = data.getMyLife();
	}
	
	protected BasicActorType getMyTarget() {
		return myTarget;
	}
	
	@Override
	public boolean isOn() {
		return myLife.stillOn();
	}

}
