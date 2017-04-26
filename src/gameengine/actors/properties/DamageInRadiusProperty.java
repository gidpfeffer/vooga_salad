package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

public class DamageInRadiusProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {

	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return true;
	}

}
