package gamedata.composition;

import types.ActorType;

/**
 * 
 * Basic information all actors should have
 * 
 * @author maddiebriere
 *
 */

public class ActorData implements Data {
	private boolean isActive;
	private String imagePath;
	private double heading; //angle
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}
	
	
	
}
