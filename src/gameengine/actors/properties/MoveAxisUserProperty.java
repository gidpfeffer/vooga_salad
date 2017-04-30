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
	
	protected abstract void move(String posKey, String negKey, Integer sensitivity, G grid, Integer actorID);
	
	
	@Override
	public boolean isOn() {
		return true;
	}

}
