package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import factories.DataGenerator;
import gamedata.composition.LimitedHealthData;
import gamedata.compositiongen.Data;

public class DataGeneratorTest {

	@Test
	public void noErrors() {
		DataGenerator.makeData("LimitedHealth", 10.0);
	}
	
	public void testSavedData(){
		Data data = DataGenerator.makeData("LimitedHealth", 10.0);
		LimitedHealthData health = (LimitedHealthData)data;
		assertEquals(10.0, health.getStartHealth());
	}

}
