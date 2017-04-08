package gameengine.actors.status;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

/**
 * @author sarahzhou
 * Afflicted actor loses a set amount of HP at a specified time interval
 */
public class Poisoned implements IStatus{
	
	public final static int HP_LOST = 10;
	public final static int TIME_INTERVAL = 5;

	@Override
	public void afflict(Integer id) {
		// TODO Auto-generated method stub
		
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
