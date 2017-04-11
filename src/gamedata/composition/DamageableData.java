package gamedata.composition;

import java.util.ArrayList;
import java.util.List;

import gamedata.compositiongen.Data;
import types.BasicActorType;

public class DamageableData implements Data {
	private double myPower;
	private double myHitRadius;
	private List<BasicActorType> myEnemyTypes;
	
	public DamageableData(double myPower, double myHitRadius, BasicActorType... myEnemyTypes) {
		this.myPower = myPower;
		this.myHitRadius = myHitRadius;
		this.myEnemyTypes = new ArrayList<>();
		for (BasicActorType e : myEnemyTypes) {
			this.myEnemyTypes.add(e);
		}
	}
	public double getMyPower() {
		return myPower;
	}
	public void setMyPower(double myPower) {
		this.myPower = myPower;
	}
	public double getMyHitRadius() {
		return myHitRadius;
	}
	public void setMyHitRadius(double myHitRadius) {
		this.myHitRadius = myHitRadius;
	}
	public List<BasicActorType> getMyEnemyTypes() {
		return myEnemyTypes;
	}
	public void setMyEnemyTypes(List<BasicActorType> myEnemyTypes) {
		this.myEnemyTypes = myEnemyTypes;
	}
	
	
}
