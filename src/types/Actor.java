package types;
//http://stackoverflow.com/questions/19680418/how-to-use-enum-with-grouping-and-subgrouping-hierarchy-nesting
public enum Actor {
	ENEMY(Type.ENEMY),
	TOWER(Type.TOWER),
	PROJECTILE(Type.PROJECTILE);
	
	private Type type;
	
	Actor(Type type) {
		this.type = type;
	}
	
	public boolean isType(Type type) {
		return this.type ==type;
	}
	
	public enum Type {
		ENEMY,
		PROJECTILE,
		TOWER;
		
	}
	
	
}