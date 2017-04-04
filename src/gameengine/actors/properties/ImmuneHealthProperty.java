package gameengine.actors.properties;

import gamedata.composition.LimitedHealthData;
import gameengine.actors.Projectile;

public class ImmuneHealthProperty<G extends Projectile> extends LimitedHealthProperty{
	
	public ImmuneHealthProperty(LimitedHealthData data) {
		super(data);
	}

	public void apply(double damage) {
		
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

}
