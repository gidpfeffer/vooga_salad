package gameengine.actors.management;

import gameengine.ActorResult;

public interface IActor <O>{

	public ActorResult act(O out);
	
	public void deactivate();
	
	public boolean isActive();
	
	public double getHealth();
	
	public void setHealth(double health);

}
