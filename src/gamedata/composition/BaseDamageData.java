package gamedata.composition;

import gamedata.compositiongen.Data;
import types.BasicActorType;

public class BaseDamageData implements Data {
	private static final double RADIUS = 0.0;
	private static final BasicActorType TYPE  = BasicActorType.Tower;

	private double myRadius;
	private BasicActorType myTarget;

	public BaseDamageData(){
		this(RADIUS, TYPE);
	}
	
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
