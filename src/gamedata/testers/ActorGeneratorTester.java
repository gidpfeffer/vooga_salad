package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.ActorGenerator;
import gamedata.ActorData;
import gamedata.BasicData;
import gameengine.actors.MainActor;
import types.BasicActorType;

public class ActorGeneratorTester {

	//TODO: Fix
	@Test
	public void basicGenerate() {
		ActorData toTest = new ActorData(BasicActorType.Tower, new BasicData("Bob", "imagePath"));
		MainActor actor = ActorGenerator.makeActor(1, toTest);
		int ID = actor.getID();
		BasicActorType type = actor.getType();
		assertEquals(type, BasicActorType.Tower);
		assertEquals(ID, 0);//first item generated, should yield a 0
	}

}
