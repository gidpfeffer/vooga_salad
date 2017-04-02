package gameengine.actors.management;

public interface Actor <G>{

	public void act(G grid);
		
	public boolean isActive();
	
	public double getHealth();
	
	public void setHealth(double health);

}
