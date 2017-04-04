package gameengine.actors.towers;

import gameengine.actors.properties.Health;
import gameengine.actors.properties.LimitedHealth;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public abstract class HealthTower<T extends ReadableGrid> extends ATower<T> {

	private Health myHealth;
	
	public HealthTower(double health) {
		myHealth = new LimitedHealth(health);
	}
	
	@Override
	public boolean isActive() {
		return myHealth.isAlive();
	}
	
	@Override
	public void applyDamage(double damage) {
		myHealth.apply(damage);
	}
}
