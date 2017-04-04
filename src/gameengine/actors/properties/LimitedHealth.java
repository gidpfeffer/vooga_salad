package gameengine.actors.properties;

public class LimitedHealth implements Health {

	private double myHealth;

	public LimitedHealth(double health) {
		myHealth = health;
	}

	@Override
	public void apply(double damage) {
		myHealth -= damage;
	}

	@Override
	public boolean isAlive() {
		return myHealth>0;
	}

}
