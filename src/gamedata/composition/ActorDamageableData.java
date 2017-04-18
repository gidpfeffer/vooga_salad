package gamedata.composition;
import java.util.ArrayList;
import java.util.List;
import gamedata.compositiongen.Data;
import types.BasicActorType;

/**
 * Actor is able to be damaged.
 * 
 * @author maddiebriere
 * 
 */

public class ActorDamageableData implements Data {
	
	private double myHitRadius;
	private List<BasicActorType> myEnemyTypes;
	
	public ActorDamageableData(){
		this(0.0, new BasicActorType[0]);
	}
	
	public ActorDamageableData(Double myHitRadius, BasicActorType... myEnemyTypes) {
		this.myHitRadius = myHitRadius;
		this.myEnemyTypes = new ArrayList<>();
		for (BasicActorType e : myEnemyTypes) {
			this.myEnemyTypes.add(e);
		}
	}
	
	public double getMyHitRadius() {
		return myHitRadius;
	}
	
	public void setMyHitRadius(double myHitRadius) {
		this.myHitRadius = myHitRadius;
	}
	
	public List<BasicActorType> getMyEnemyTypes() {
		return myEnemyTypes;
	}
	
	public void setMyEnemyTypes(List<BasicActorType> myEnemyTypes) {
		this.myEnemyTypes = myEnemyTypes;
	}

	@Override
	public String getCategory() {
		return "Able to be damaged";
	}
	
	
}