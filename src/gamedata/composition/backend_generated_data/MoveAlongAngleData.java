package gamedata.composition.backend_generated_data;

import gamedata.compositiongen.MoveData;

public class MoveAlongAngleData extends MoveData {
	private double range;
	private double angle;
	
	/**
	 *  speed comes from ProjectileType.getSpeed(), range from ProjectileType.getRadius()
	 */
	public MoveAlongAngleData(double speed, double range, double angle) {
		super(speed);
		this.range = range;
		this.angle = angle;
	}
	

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	
	
}
