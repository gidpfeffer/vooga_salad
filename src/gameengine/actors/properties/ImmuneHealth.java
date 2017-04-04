package gameengine.actors.properties;

import gameengine.actors.Projectile;

public interface ImmuneHealth<G extends Projectile> extends Health{
	public void apply(double damage, G projectile);
}
