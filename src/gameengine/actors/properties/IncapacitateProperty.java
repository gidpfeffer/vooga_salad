package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class IncapacitateProperty<G extends ReadableGrid> implements IActProperty<G>{

	@Override
	public void action(ReadableGrid grid, Integer actorID) {

	}


	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}


}
