package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetNearData extends ShootData{
	
	public ShootTargetNearData(){
		super();
	}
	
	public ShootTargetNearData(Double myRange, Integer fireRate, BasicActorType type, Integer projectile, Double speed){
		super(myRange, fireRate, type, projectile, speed);
	}
}
