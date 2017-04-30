package gameengine.actors.properties;

import gameengine.actors.propertygen.HealthProperty;

public class UnlimitedHealthProperty implements HealthProperty {

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
