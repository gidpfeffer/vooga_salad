package gameengine.actors.properties;

public interface HealthProperty{

	public void apply(double damage);
	
	public boolean isAlive();
}
