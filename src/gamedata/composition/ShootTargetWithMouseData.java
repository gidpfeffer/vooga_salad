package gamedata.composition;

import types.BasicActorType;

public class ShootTargetWithMouseData extends ShootTargetFarData{

	public ShootTargetWithMouseData(){
		super();
	}
	
	public ShootTargetWithMouseData(Double range, Integer rate, 
			BasicActorType type, Integer projectile, Double speed) {
		super(range, rate, type, projectile, speed);
	}
	
}
