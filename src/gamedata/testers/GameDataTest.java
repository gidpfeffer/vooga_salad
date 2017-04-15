package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.GameDataGenerator;
import gamedata.GameData;
import types.BasicActorType;

public class GameDataTest {

	@Test
	public void testOptions() {
		GameData game = GameDataGenerator.getSampleGame();
		//There are multiple towers
		boolean check = game.getAllOfType(new BasicActorType("Tower")).size() == 0;
		assertNotEquals(check, true);
	}
	
	@Test
	public void testComplexSample(){
		GameData game = GameDataGenerator.getComplexSampleGame();
	}

}
