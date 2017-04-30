package gamedata.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gamedata.compositiongen.ActData;
import gamedata.compositiongen.Data;
import types.BasicActorType;

public class BaseDamageData extends ActData {

	private double myRadius;
	private List<BasicActorType> myTargets;

	public BaseDamageData(){
		this(0.0, new BasicActorType[0]);
	}
	
	public BaseDamageData(Double radius, BasicActorType... types) {
		this(radius, Arrays.asList(types));
	}
	
	public BaseDamageData(Double radius, List<BasicActorType> types) {
		myRadius = radius;
		myTargets = types;
		this.myTargets = new ArrayList<>();
		for (BasicActorType e: types) {
			this.myTargets.add(e);
		}
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
