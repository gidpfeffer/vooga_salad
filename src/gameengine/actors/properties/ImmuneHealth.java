package gameengine.actors.properties;

import gameengine.actors.Projectile;

public class ImmuneHealth<G extends Projectile> extends LimitedHealth{
	public ImmuneHealth(double health) {
		super(health);
	}

	public void apply(double damage) {
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}
}
