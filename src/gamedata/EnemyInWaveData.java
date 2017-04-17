package gamedata;

import java.util.List;

public class EnemyInWaveData {
	
	//Corresponds to a number in the Map.3
	private ActorData myActor;
	private int myNumber;
	//private List<Integer> myPaths;
	
	//TODO: Need paths?
	public EnemyInWaveData(ActorData myActor, int myNumber) {
		this.myActor = myActor;
		this.myNumber = myNumber;
		//this.myPaths = myPaths;
	}
	
	

	public ActorData getMyActor() {
		return myActor;
	}

	public void setMyActor(ActorData myActor) {
		this.myActor = myActor;
	}


	public int getMyNumber() {
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
