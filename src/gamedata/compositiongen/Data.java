package gamedata.compositiongen;

import gamedata.ActorData;
public abstract class Data{
	/**
	 * Define how to add this data object to the given
	 * ActorData
	 * 
	 * @param data ActorData to which to add this Data object
	 */
	public abstract void addData(ActorData data);
	
	/**
	 * Compare classnames for equals
	 */
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof Data)){
			return false;
		}
		Data data = (Data)obj;
		return data.getClass().equals(this.getClass());
	}
}