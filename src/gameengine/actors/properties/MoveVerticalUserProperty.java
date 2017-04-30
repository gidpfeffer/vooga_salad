package gameengine.actors.properties;

import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveVerticalUserProperty<G extends ReadAndMoveGrid> extends MoveAxisUserProperty<G> {
	
	public MoveVerticalUserProperty(MoveVerticlaUserData myData) {
		super(myData.getRight(),myData.getLeft(),myData.getSensitivity());
	}
	
	@Override
	protected void move(G grid, Integer actorID) {
		grid.move(actorID, grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY()+getKeyMovement(grid));
	}

}
