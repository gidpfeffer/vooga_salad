package gamedata.compositiongen;

import gamedata.ActorData;

public interface Data{
	//define the classification of this data type
	//public boolean validate();
	//public boolean autofill();
	public void addData(ActorData data);
}