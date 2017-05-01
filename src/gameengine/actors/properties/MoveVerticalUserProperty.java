package gameengine.actors.properties;

import gamedata.composition.MoveVerticalUserData;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveVerticalUserProperty<G extends ReadAndMoveGrid> extends MoveAxisUserProperty<G> {
	
	public MoveVerticalUserProperty(MoveVerticalUserData myData) {
		super(myData.getPosButton(),myData.getNegButton(),myData.getMySensitivity());
	}
	
	@Override
	protected void move(G grid, Integer actorID) {
		grid.move(actorID, grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY()+getKeyMovement(grid));
	}

}
