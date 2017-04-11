package gamedata.composition;

import gamedata.compositiongen.Data;
import types.BasicActorType;

public class BaseDamageData implements Data {

	private double myRadius;
	private BasicActorType myTarget;

	public BaseDamageData(Double radius, BasicActorType type) {
		myRadius = radius;
		myTarget = type;
	}

	public double getMyRadius() {
		return myRadius;
	}

	public void setMyRadius(double myRadius) {
		this.myRadius = myRadius;
	}

	public BasicActorType getMyTarget() {
		return myTarget;
	}

	public void setMyTarget(BasicActorType myTarget) {
		this.myTarget = myTarget;
	}
	
	
	
}
