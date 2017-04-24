package gamedata.composition;

import gamedata.compositiongen.ShootData;
import gamedata.compositiongen.ShootTargetLineData;
import types.BasicActorType;

public class ShootTargetFarData extends ShootTargetLineData{
	
	public ShootTargetFarData(){
		super();
	}
	
	public ShootTargetFarData (Double myRange, Integer fireRate, BasicActorType type, Integer projectile, Double speed){
		super(myRange, fireRate, type, projectile, speed);
	}
	
}
