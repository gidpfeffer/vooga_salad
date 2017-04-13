package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.DataGenerator;
import gamedata.composition.AfflictStatusData;
import gamedata.composition.LimitedHealthData;
import gamedata.compositiongen.Data;
import types.BasicActorType;

public class DataGeneratorTest {

	@Test
	public void noErrors() {
		assertNotEquals(DataGenerator.makeData("LimitedHealth"), null);
		System.out.println(DataGenerator.makeData("LimitedHealth"));
		assertNotEquals(DataGenerator.makeData("MoveWithSetPath"), null);
		DataGenerator.makeData("LimitedHealth", 10.0);
		DataGenerator.makeData("ShootTargetNear", 10.0, 10, BasicActorType.Tower, 10, 10.0);
		DataGenerator.makeData("ShootTargetFar", 10.0, 10, BasicActorType.Tower, 10, 10.0);
	}
	
	public void testSavedData(){
		Data data = DataGenerator.makeData("LimitedHealth", 10.0);
		LimitedHealthData health = (LimitedHealthData)data;
		assertEquals(10.0, health.getStartHealth());
		
		Data data1 = DataGenerator.makeData("AfflictStatus");
		AfflictStatusData status = (AfflictStatusData)data1;
		assertNotEquals(status, null);
	}

}
