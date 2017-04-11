package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

public class ShootTargetWithMouseData extends ShootData{

	public ShootTargetWithMouseData(Double range, Integer rate, BasicActorType type, Integer projectile) {
		super(range, rate, type, projectile);
	}
	
}
