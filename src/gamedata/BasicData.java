package gamedata;

import gamedata.compositiongen.Data;
import types.ActorType;

/**
 * 
 * Basic information all actors should have
 * 
 * @author maddiebriere
 *
 */

public class BasicData implements Data {
	
	private static final double HEADING = 0.0;
	
	private String name;
	private boolean isActive;
	private String imagePath;
	private double heading; //angle
	
	public BasicData(String name, String imagePath){
		this.name=name;
		this.imagePath = imagePath;
		heading = HEADING;
		isActive = true;
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
