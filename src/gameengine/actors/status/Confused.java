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

	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#action(gameengine.grid.interfaces.ActorGrid.ReadableGrid, java.lang.Integer)
	 */
	@Override
	public void action(ReadableGrid grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
