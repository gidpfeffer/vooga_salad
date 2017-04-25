package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

/**
 * STUB: need to implement when grid has access to modification of game status
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddExperienceProperty<G extends ReadableGrid> implements IActProperty<G> {

	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}
