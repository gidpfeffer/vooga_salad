package gamedata;

/**
 * 
 * @author maddiebriere
 *
 */

public abstract class ActorData {
	private boolean isActive;
	private double health;
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	
}
