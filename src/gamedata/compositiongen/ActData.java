package gamedata.compositiongen;

import gamedata.ActorData;

public abstract class ActData implements Data{
	@Override
	public void addData(ActorData actor){
		actor.addData(this);
	}
}
