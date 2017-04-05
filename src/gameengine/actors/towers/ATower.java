package gameengine.actors.towers;

import gameengine.actors.AbstractActor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class Tower <T extends ReadableGrid> extends AbstractActor<T>{
	
	public Tower(HealthProperty health, IActProperty<? extends ReadableGrid>... properties) {

	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#isActive()
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

}
