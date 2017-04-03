package gameengine.actors.towers;

import gameengine.actors.AbstractActor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class ATower <T extends ReadableGrid> extends AbstractActor<T>{
	
	private boolean isActive;
	
	public ATower() {
		isActive = true;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#isActive()
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return isActive;
	}

}
