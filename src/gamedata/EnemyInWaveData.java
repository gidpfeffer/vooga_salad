package gamedata;

import java.util.List;

public class EnemyInWaveData {
	
	//Corresponds to a number in the Map.
	private ActorData myActor;
	private int myNumber;
	
	public EnemyInWaveData(ActorData myActor, int myNumber) {
		this.myActor = myActor;
		this.myNumber = myNumber;
	}

	public ActorData getMyActor() {
		return myActor;
	}

	public void setMyActor(ActorData myActor) {
		this.myActor = myActor;
	}


	public int getOption() {
		return myNumber;
	}
	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}


	public String toString(){
		//TODO: NEED?
		return null;
		//return  "ActorData:" +myData.toString()+" Number: "+myNumber+"  Paths:  "+myPaths.toString();
	}
}
