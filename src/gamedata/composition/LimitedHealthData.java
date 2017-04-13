package gamedata.composition;

import gamedata.compositiongen.HealthData;

public class LimitedHealthData extends HealthData{
	private static final double HEALTH = 0.0;
	
	private double startHealth;
	
	public LimitedHealthData(){
		this(HEALTH);
	}
	
	public LimitedHealthData(Double health){
		startHealth = health;
	}

	public double getStartHealth() {
		return startHealth;
	}

	public void setStartHealth(double startHealth) {
		this.startHealth = startHealth;
	}
	
	
	
}
