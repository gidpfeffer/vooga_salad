package gameengine.actors.properties;

import gamedata.composition.LimitedHealthData;

public class MultiLifeHealthProperty extends LimitedHealthProperty{
	
	private Integer myLives;

	public MultiLifeHealthProperty(MultiLifeHealthData data) {
		super(data);
		myLives = MultiLifeHealthData.getLives();
	}
	
	@Override
	public void apply(double damage) {
		double fullHealth = super.getRemaining()/super.getPercent();
		super.apply(damage);
		if(myLives>0 && !super.isAlive()) {
			super.apply(-1*(fullHealth));
			myLives--;
		}
	}

}
