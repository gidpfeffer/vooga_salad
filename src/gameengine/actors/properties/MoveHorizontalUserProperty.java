package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveHorizontalUserProperty<G extends ReadAndMoveGrid> implements IActProperty<G>{

	private String moveLeftButton;
	private String moveRightButton;
	private Integer sensitivity;
	
	public MoveHorizontalUserProperty(MoveHorizontalUserData myData) {
		moveLeftButton = myData.getLeft();
		moveRightButton = myData.getRight();
		sensitivity = myData.getSensitivity();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOn() {
		return true;
	}

}
