package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public abstract class MoveAxisUserProperty<G extends ReadAndMoveGrid> implements IActProperty<G>{
	
	private String posButton;
	private String negButton;
	private Integer mySensitivity;
	
	public MoveAxisUserProperty(String pos, String neg, Integer sensitivity) {
		posButton = pos;
		negButton = neg;
		mySensitivity = sensitivity;
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		move(posButton, negButton, mySensitivity, grid, actorID);
	}
	
	protected abstract void move(G grid, Integer actorID);
	
	protected double getKeyMovement(G grid) {
		return (grid.getEventQueue().queryKey(posButton) ? mySensitivity:0) + (grid.getEventQueue().queryKey(negButton) ? -1*mySensitivity:0);
	}
	
	@Override
	public boolean isOn() {
		return true;
	}

}
