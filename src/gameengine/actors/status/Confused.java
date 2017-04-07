package gameengine.actors.status;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

/**
 * @author sarahzhou
 * Status in which the afflicted actor shoots at (damages) their own side a certain percentage of the time 
 */
public class Confused implements IStatus {
	
	private static final double CONFUSED_PERCENTAGE = 0.5;

	@Override
	public void afflict(Integer id) {
		
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
