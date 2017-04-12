package gamedata.testers;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.GameDataGenerator;
import gamedata.GameData;

public class GameDataTest {

	@Test
	public void testOptions() {
		GameData game = GameDataGenerator.getSampleGame();
		boolean check = game.getTowerOptions().size() == 0;
		assertNotEquals(check, true);
	}

}
