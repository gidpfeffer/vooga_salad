package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.ActorGenerator;
import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.composition.BaseDamageData;
import gamedata.composition.LimitedHealthData;
import gamedata.composition.ShootTargetFarData;
import gameengine.actors.MainActor;
import types.BasicActorType;

public class ActorGeneratorTest {

	//TODO: Fix
	@Test
	public void basicGenerate() {
		System.out.println("\n"
				+ "Generate 1");
		ActorData toTest = new ActorData(BasicActorType.Tower, new BasicData("Bob", "imagePath"));
		MainActor actor = ActorGenerator.makeActor(1, toTest);
		int ID = actor.getID();
		//int option = actor.getMyOption();
		BasicActorType type = actor.getType();
		assertEquals(type, BasicActorType.Tower);
		//assertEquals(ID, 0);//first item generated, should yield a 0
		//assertEquals(option, 1);
	}
	
	@Test
	public void noErrors(){
		System.out.println("\nGenerate 2");
		ActorData toTest2 = new ActorData(BasicActorType.Tower, new BasicData("Bob", "imagePath"));
		//toTest2.addData(new ShootTargetFarData(100, 50, BasicActorType.Base, 1));
		ActorGenerator.makeActor(1, toTest2);
		
		System.out.println("\nGenerate 3");
		ActorData toTest3 = new ActorData(BasicActorType.Troop, new BasicData("Billy", "imagePath"));
		//toTest3.addData(new BaseDamageData(90, BasicActorType.Troop));
		ActorGenerator.makeActor(2, toTest3);
	}

}
