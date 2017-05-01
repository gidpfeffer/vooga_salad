package gameengine.actors.properties;

import gamedata.composition.MoveHorizontalUserData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveHorizontalUserProperty<G extends ReadAndMoveGrid> extends MoveAxisUserProperty<G>{

	public MoveHorizontalUserProperty(MoveHorizontalUserData myData) {
		super(myData.getPosButton(),myData.getNegButton(),myData.getMySensitivity());
	}

	@Override
	protected void move(G grid, Integer actorID) {
		grid.move(actorID, grid.getLocationOf(actorID).getX()+getKeyMovement(grid), grid.getLocationOf(actorID).getY());
	}

}
