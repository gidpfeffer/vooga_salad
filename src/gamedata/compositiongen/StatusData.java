package gamedata.compositiongen;

import gameengine.actors.status.StatusDuration;
import types.BasicActorType;

public abstract class StatusData implements Data{
	
	BasicActorType myTarget;
	StatusDuration myLife;

	public StatusData(){
		this(BasicActorType.Tower, new StatusDuration(0));
	}
	
	public StatusData(BasicActorType type, StatusDuration statusDuration) {
		myTarget = type;
		myLife = statusDuration;
	}
	
	public StatusDuration getMyLife() {
		return myLife;
	}

	public void setMyLife(StatusDuration myLife) {
		this.myLife = myLife;
	}

	public BasicActorType getMyTarget() {
		return myTarget;
	}

	public void setMyTarget(BasicActorType myTarget) {
		this.myTarget = myTarget;
	}
	
}
