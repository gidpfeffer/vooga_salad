package gamedata.compositiongen;

import gamedata.ActorData;
import gamedata.composition.LimitedHealthData;

public abstract class HealthData extends Data {
	@Override
	public void addData(ActorData actor){
		actor.setHealth(this);
	}
}
