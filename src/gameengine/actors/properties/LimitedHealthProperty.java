package gameengine.actors.properties;

import gamedata.composition.LimitedHealthData;
import gameengine.actors.propertygen.HealthProperty;

public class LimitedHealthProperty implements HealthProperty {

	private double myHealth;
	private double startHealth;

	public LimitedHealthProperty(LimitedHealthData data) {
		myHealth = startHealth = data.getStartHealth();
	}

	@Override
	public void apply(double damage) {
		myHealth -= damage;
	}

	@Override
	public boolean isAlive() {
		return myHealth>0;
	}

	@Override
	public double getPercent() {
		return myHealth/startHealth;
	}

}
