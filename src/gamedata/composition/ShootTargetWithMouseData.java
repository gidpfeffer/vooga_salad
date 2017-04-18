package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetWithMouseData extends ShootData{

	public ShootTargetWithMouseData(){
		super();
	}
	
	public ShootTargetWithMouseData(Double range, Integer rate, BasicActorType type, Integer projectile, Double speed) {
		super(range, rate, type, projectile, speed);
	}
	
}
