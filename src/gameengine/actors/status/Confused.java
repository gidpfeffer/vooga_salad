package gameengine.actors.status;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

/**
 * 
 * 
 * @author Moses Wayne
 * @author sarahzhou
 */
public class Confused<G extends ReadableGrid> extends IStatus<G> {

	@Override
	public void action(G grid, Integer actorID) {
		//grid.
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
