package gamedata;

import java.util.ArrayList;
import java.util.List;

import gamedata.composition.BasicData;
import gamedata.composition.Data;
import gamedata.composition.HealthData;
import gamedata.composition.LimitedHealthData;

public class ActorData {
	private final static double HEALTH = 100;
	
	private List<Data> myData;
	private String name;
	
	public ActorData(BasicData data){
		this(data, new LimitedHealthData(HEALTH));
	}
	
	public ActorData(BasicData data, HealthData health){
		myData = new ArrayList<Data>();
		myData.add(data);
		myData.add(health);
		name = data.getName();
	}

	public List<Data> getMyData() {
		return myData;
	}

	public void setMyData(List<Data> myData) {
		this.myData = myData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
