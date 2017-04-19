package gamedata.composition;

import gamedata.compositiongen.ShootData;
import types.BasicActorType;

/**
 * 
 * Accommodates multiple shots at once
 * 
 * @author maddiebriere
 *
 */

public class ShootMultiData extends ShootData {

	private Integer numShots;
	
	public ShootMultiData(Double range, Integer rate, BasicActorType type, 
			Integer projectile, Double speed, Integer quantity){
		super(range,rate,type,projectile,speed);
		this.numShots = quantity;
	}

	public Integer getNumShots() {
		return numShots;
	}

	public void setNumShots(Integer numShots) {
		this.numShots = numShots;
	}
}
