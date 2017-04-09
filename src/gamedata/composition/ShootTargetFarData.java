package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetFarData extends ShootData{
	
	public ShootTargetFarData (double myRange, int fireRate, BasicActorType type){
		super(myRange, fireRate, type);
	}
	
}
