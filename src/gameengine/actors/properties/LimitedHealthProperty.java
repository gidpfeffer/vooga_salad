package gameengine.actors.properties;

import gamedata.composition.LimitedHealthData;

public class LimitedHealthProperty implements HealthProperty {

	private double myHealth;

	public LimitedHealthProperty(LimitedHealthData data) {
		myHealth = data.getStartHealth();
	}

	@Override
	public void apply(double damage) {
		myHealth -= damage;
	}

	@Override
	public boolean isAlive() {
		return myHealth > 0;
	}

}
