package gamedata.composition;

import java.util.List;

import types.BasicActorType;

public class ActorDamageableWithExitData extends ActorDamageableData {

	public ActorDamageableWithExitData() {
		super();
	}

	public ActorDamageableWithExitData(Double myHitRadius, BasicActorType... myEnemyTypes) {
		super(myHitRadius, myEnemyTypes);
	}

	public ActorDamageableWithExitData(Double myHitRadius, List<BasicActorType> myEnemyTypes) {
		super(myHitRadius, myEnemyTypes);
	}
	
}
