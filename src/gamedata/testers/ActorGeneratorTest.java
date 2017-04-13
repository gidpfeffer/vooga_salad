package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.ActorGenerator;
import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.composition.BaseDamageData;
import gamedata.composition.ShootTargetFarData;
import gamedata.compositiongen.Data;
import gamedata.compositiongen.HealthData;
import gameengine.actors.MainActor;
import gameengine.actors.management.Actor;
import gameengine.actors.propertygen.HealthProperty;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public class ActorGeneratorTest {

	//TODO: Fix
	@Test
	public void basicGenerate() {
		System.out.println("\n"
				+ "Generate 1");
		ActorData toTest = new ActorData(BasicActorType.Tower, new BasicData("Bob", "imagePath"));
		MainActor actor = ActorGenerator.makeActor(1, toTest);
		assertNotEquals(actor, null);
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
		toTest2.addData(new ShootTargetFarData(100.0, 50, BasicActorType.Base, 10, 10.0));
		MainActor actor1 = ActorGenerator.makeActor(1, toTest2);
		System.out.println(actor1.getMyProperties().size());
		
		System.out.println("\nGenerate 3");
		ActorData toTest3 = new ActorData(BasicActorType.Troop, new BasicData("Billy", "imagePath"));
		toTest3.addData(new BaseDamageData(90.0, BasicActorType.Troop));
		MainActor actor2 = ActorGenerator.makeActor(2, toTest3);
		System.out.println(actor2.getMyProperties().size());
	}
	
	@Test
	public void testConstructors(){
		Class clzz = null;
		Class clzz2 = null;
		try {
			clzz = Class.forName("gamedata.ActorData");
			clzz2 = Class.forName("gameengine.actors.MainActor");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class fail");
		}
		
		try {
			clzz.getDeclaredConstructor(BasicActorType.class, BasicData.class, HealthData.class, Data[].class);
			clzz2.getDeclaredConstructor(BasicActorType.class, Integer.class, 
			Integer.class, HealthProperty.class, IActProperty[].class);
			System.out.println("NICE");
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("You fucked up");
		}
		
	}

}
