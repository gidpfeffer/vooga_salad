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
	public boolean isOn() {
		return true;
	}

}
