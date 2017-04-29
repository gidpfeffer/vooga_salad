package gameengine.actors.propertygen;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import util.Delay;

public abstract class CycleProperty<G extends ReadableGrid> implements IActProperty<G>{

	private Delay myDelay;
	
	public CycleProperty(Integer myCycle) {
		myDelay = new Delay(myCycle);
	}
	
	@Override
	public boolean isOn() {
		return myDelay.delayAction();
	}

}
