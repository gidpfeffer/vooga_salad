package gameengine.actors.properties;

public class MultiLifeHealthProperty extends LimitedHealthProperty{
	
	private Integer myLives;

	public MultiLifeHealthProperty(MultiLifeHealthData data) {
		super(data.getStartHealth());
		myLives = data.getLives();
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
