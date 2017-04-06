package gamedata.composition;

public class LimitedHealthData implements HealthData{
	public double startHealth;
	
	public LimitedHealthData(double health){
		startHealth = health;
	}

	public double getStartHealth() {
		return startHealth;
	}

	public void setStartHealth(double startHealth) {
		this.startHealth = startHealth;
	}
	
	
	
}
