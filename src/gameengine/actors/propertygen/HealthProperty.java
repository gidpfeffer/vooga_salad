package gameengine.actors.propertygen;

public interface HealthProperty extends Property{

	public void apply(double damage);
	
	public boolean isAlive();
	
	public double getPercent();
	
	public double getRemaining();
}
