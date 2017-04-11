package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetNearData extends ShootData{
	
	public ShootTargetNearData(double myRange, int fireRate, BasicActorType type){
		super(myRange, fireRate, type);
	}
}
