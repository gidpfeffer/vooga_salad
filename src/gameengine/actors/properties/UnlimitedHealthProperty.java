package gameengine.actors.properties;

import gamedata.composition.UnlimitedHealthData;
import gameengine.actors.propertygen.HealthProperty;

public class UnlimitedHealthProperty implements HealthProperty {

	public UnlimitedHealthProperty(UnlimitedHealthData unlimited){
		//TODO: Any implementation
	}
	
	@Override
	public void apply(double damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public double getPercent() {
		return 1;
	}

	@Override
	public double getRemaining() {
		return 0;
	}

}
