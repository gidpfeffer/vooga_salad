package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import javafx.scene.input.KeyCode;

public abstract class MoveAxisUserProperty<G extends ReadAndMoveGrid> implements IActProperty<G>{
	
	private KeyCode posButton, negButton;
	private Double mySensitivity;
	
	public MoveAxisUserProperty(String pos, String neg, Integer sensitivity) {
		posButton = KeyCode.RIGHT;
		negButton = KeyCode.LEFT;
		mySensitivity = .001;
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		move(grid, actorID);
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
