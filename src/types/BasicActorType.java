package types;

/**
 * Refactored from enum list to allow
 * for categories
 * 
 * @author maddiebriere
 *
 */

public class BasicActorType implements Comparable {
	private String type;
	
	public BasicActorType(String type){
		this.type=type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(!(o instanceof BasicActorType)){
			return false;
		}
		BasicActorType b = (BasicActorType)o;
		return b.getType().equals(this.getType());
	}
	
	public String toString(){
		return this.getType();
	}

	@Override
	public int compareTo(Object o) {
		if(o == null){
			return -1;
		}
		if(!(o instanceof BasicActorType)){
			return -1;
		}
		return this.getType().compareTo(((BasicActorType)o).getType());
	}
	
}
