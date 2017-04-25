package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

/**
 * STUB: implement as soon as grid has ability to access game status
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddMoneyProperty<G extends ReadableGrid> implements IActProperty<G>{

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
