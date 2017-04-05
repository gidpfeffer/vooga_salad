package gameengine.actors.properties;

public class LimitedHealthProperty implements HealthProperty {

	private double myHealth;

	public LimitedHealthProperty(double health) {
		myHealth = health;
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
