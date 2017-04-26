package gameengine.actors.properties.health;

import gamedata.composition.LimitedHealthData;

public class ImmuneHealthProperty extends LimitedHealthProperty{
	
	public ImmuneHealthProperty(LimitedHealthData data) {
		super(data);
	}

	@Override
	public void apply(double damage) {
		
	}
	
}
