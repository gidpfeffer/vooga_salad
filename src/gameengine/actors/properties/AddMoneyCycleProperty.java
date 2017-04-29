package gameengine.actors.properties;

import gamedata.composition.AddMoneyCycleData;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import util.Delay;

public class AddMoneyCycleProperty<G extends ReadAndDamageGrid> extends AddMoneyProperty<G> {
	
	private Delay myDelay;
	
	public AddMoneyCycleProperty(AddMoneyCycleData myData) {
		super(myData);
		myDelay = new Delay(myData.getFrequency());
	}
	
	@Override
	public boolean isOn() {
		return myDelay.delayAction();
	}

}
