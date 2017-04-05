package gameengine.actors.management;

public interface Actor <G>{

	public void act(G grid);
		
	public boolean isActive();

	public void applyDamage(double health);

}
