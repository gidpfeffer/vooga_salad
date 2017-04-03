package gameengine.actors.towers;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ReadableGrid;

public abstract class ATower<G extends ReadableGrid> implements Actor <G> {
	
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
