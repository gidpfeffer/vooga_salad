package gamedata.compositiongen;

import gamedata.ActorData;
public interface Data{
	/**
	 * Define how to add this data object to the given
	 * ActorData
	 * 
	 * @param data ActorData to which to add this Data object
	 */
	public void addData(ActorData data);
}