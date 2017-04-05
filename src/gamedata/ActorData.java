package gamedata;

/**
 * 
 * @author maddiebriere
 *
 */

public abstract class ActorData {
	private boolean isActive;
	private String imagePath;
	private double heading; //angle
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
