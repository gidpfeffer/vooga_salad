package gameengine.actors.properties;

import gamedata.composition.LimitedHealthData;

public class ImmuneHealthProperty extends LimitedHealthProperty{
	
	public ImmuneHealthProperty(LimitedHealthData data) {
		super(data);
	}

	public void apply(double damage) {
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
