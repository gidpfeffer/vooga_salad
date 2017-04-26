package gamedata.composition;

import java.util.Arrays;
import java.util.List;

import gamedata.compositiongen.Data;
import types.BasicActorType;

public class BaseDamageData implements Data {

	private double myRadius;
	private List<BasicActorType> myTargets;

	public BaseDamageData(){
		this(0.0, new BasicActorType("Troop"));
	}
	
	public BaseDamageData(Double radius, BasicActorType... types) {
		myRadius = radius;
		myTargets = Arrays.asList(types);
	}

	public double getMyRadius() {
		return myRadius;
	}

	public void setMyRadius(double myRadius) {
		this.myRadius = myRadius;
	}

	public List<BasicActorType> getMyTargets() {
		return myTargets;
	}

	public void setMyTargets(List<BasicActorType> myTargets) {
		this.myTargets = myTargets;
	}

	
}
