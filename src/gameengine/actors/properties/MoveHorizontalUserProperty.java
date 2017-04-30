package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveHorizontalUserProperty<G extends ReadAndMoveGrid> extends MoveAxisUserProperty<G>{

	public MoveHorizontalUserProperty(MoveHorizontalUserData myData) {
		super(myData.getRight(),myData.getLeft(),myData.getSensitivity());
	}

	@Override
	protected void move(G grid, Integer actorID) {
		grid.move(actorID, grid.getLocationOf(actorID).getX()+getKeyMovement(grid), grid.getLocationOf(actorID).getY());
	}

}
