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
		assertNotEquals(DataGenerator.makeData("LimitedHealthData"), null);
		System.out.println(DataGenerator.makeData("LimitedHealthData"));
		assertNotEquals(DataGenerator.makeData("MoveWithSetPathData"), null);
		DataGenerator.makeData("LimitedHealthData", 10.0);
		DataGenerator.makeData("ShootTargetNearData", 10.0, 10, BasicActorType.Tower, 10, 10.0);
		DataGenerator.makeData("ShootTargetFarData", 10.0, 10, BasicActorType.Tower, 10, 10.0);
	}
	
	public void testSavedData(){
		Data data = DataGenerator.makeData("LimitedHealthData", 10.0);
		LimitedHealthData health = (LimitedHealthData)data;
		assertEquals(10.0, health.getStartHealth());
		
		Data data1 = DataGenerator.makeData("AfflictStatusData");
		AfflictStatusData status = (AfflictStatusData)data1;
		assertNotEquals(status, null);
	}

}
