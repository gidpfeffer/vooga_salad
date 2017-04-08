package gamedata;

/**
 * Information about the field in a data class. 
 * Knows name and type for use in authoring environment. 
 * Example: 
 * Name = speed
 * Type = double
 * 
 * @author maddiebriere
 *
 */

public class FieldData {
	private String myName;
	private Class myType;
	
	public FieldData(String myName, Class myType) {
		super();
		this.myName = myName;
		this.myType = myType;
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public Class getMyType() {
		return myType;
	}
	public void setMyType(Class myType) {
		this.myType = myType;
	}
	
	
}
