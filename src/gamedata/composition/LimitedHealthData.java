package gamedata.composition;

import gamedata.compositiongen.HealthData;

public class LimitedHealthData extends HealthData{
	public double startHealth;
	
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
