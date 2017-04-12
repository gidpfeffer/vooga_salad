package builders;

import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.GameData;
import types.BasicActorType;
/**
 * Generates test GameData for use in GamePlayer
 * 
 * @author maddiebriere
 */

public class GameDataGenerator {

	public static GameData getSampleGame(){
		GameData game = new GameData();
		BasicData b1 = new BasicData("Tower1", "images/tower_icon.png");
		BasicData b2 = new BasicData("Projectile1", "images/projectile_icon.png");
		BasicData b3 = new BasicData("Troop1", "images/enemy_icon.png");
		
		ActorData a1 = new ActorData(BasicActorType.Tower, b1);
		ActorData a2 = new ActorData(BasicActorType.Shot, b2);
		ActorData a3 = new ActorData(BasicActorType.Troop, b3);
		
		game.add(a1);
		game.add(a2);
		game.add(a3);
		
		return game;
	}
}
