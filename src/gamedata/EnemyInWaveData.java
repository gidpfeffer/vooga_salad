package gamedata;

import java.util.List;

public class EnemyInWaveData {

	private ActorData myData;
	private int myNumber;
	private List<Integer> myPaths;
	
	public EnemyInWaveData(ActorData myData, int myNumber, List<Integer> myPaths) {
		this.myData = myData;
		this.myNumber = myNumber;
		this.myPaths = myPaths;
	}
	
	public void addPath(Integer i){
		myPaths.add(i);
	}
	
	public ActorData getMyData() {
		return myData;
	}
	public void setMyData(ActorData myData) {
		this.myData = myData;
	}
	public int getMyNumber() {
		return myNumber;
	}
	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}

	public List<Integer> getMyPaths() {
		return myPaths;
	}

	public void setMyPaths(List<Integer> myPaths) {
		this.myPaths = myPaths;
	}
	
	
	
	
}
