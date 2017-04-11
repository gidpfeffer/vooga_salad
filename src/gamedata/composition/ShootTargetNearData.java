package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetNearData extends ShootData{
	
	public ShootTargetNearData(double myRange, int fireRate, BasicActorType type, Integer projectile){
		super(myRange, fireRate, type, projectile);
	}
}