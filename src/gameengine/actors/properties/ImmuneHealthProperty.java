package gameengine.actors.properties;

import gameengine.actors.Projectile;

public class ImmuneHealthProperty<G extends Projectile> extends LimitedHealthProperty{
	public ImmuneHealthProperty(double health) {
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
