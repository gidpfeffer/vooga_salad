package builders;

import java.util.ArrayList;
import java.util.List;

import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.GameData;
import gamedata.composition.MoveWithSetPathData;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;
/**
 * Generates test GameData for use in GamePlayer
 * 
 * @author maddiebriere
 */

public class GameDataGenerator {

	public static GameData getSampleGame(){
		GameData game = new GameData();
		BasicData b1 = new BasicData("Tower1", "tower_icon.png");
		BasicData b2 = new BasicData("Projectile1", "projectile_icon.png");
		BasicData b3 = new BasicData("Troop1", "enemy_icon.png");
		
		ActorData a1 = new ActorData(BasicActorType.Tower, b1);
		ActorData a2 = new ActorData(BasicActorType.Shot, b2);
		ActorData a3 = new ActorData(BasicActorType.Troop, b3);
		
		game.add(a1);
		game.add(a2);
		game.add(a3);
		
		return game;
	}
	
	public static GameData getComplexSampleGame(){
		GameData game = new GameData();
		
		List<Grid2D> samplePath = new ArrayList<Grid2D>();
		samplePath.add(new Coordinates(0,0.5));
		samplePath.add(new Coordinates(0.25, 0.5));
		samplePath.add(new Coordinates(0.5, 0.5));
		samplePath.add(new Coordinates(0.75, 0.5));
		samplePath.add(new Coordinates(1.0, 0.5));
		PathGenerator.addPath(game.getMyPaths(), samplePath);
		
		List<Integer> possiblePaths = new ArrayList<Integer>();
		possiblePaths.add(0);
		
		MoveWithSetPathData pathData = PathGenerator.generateMoveData
				(game.getMyPaths(), possiblePaths, .005);
		MoveWithSetPathData pathData2 = PathGenerator.generateMoveData
				(game.getMyPaths(), possiblePaths, .001);
		//Path now added at index 0
		
		BasicData b1 = new BasicData("Tower1", "tower_icon.png");
		BasicData b2 = new BasicData("Projectile1", "projectile_icon.png");
		BasicData b3 = new BasicData("Troop1", "enemy_icon.png");
		BasicData b4 = new BasicData("Base1", "grass.png");
		
		ActorData a1 = new ActorData(BasicActorType.Tower, b1);
		ActorData a2 = new ActorData(BasicActorType.Shot, b2);
		a2.addData(pathData2);
		ActorData a3 = new ActorData(BasicActorType.Troop, b3);
		a3.addData(pathData);
		ActorData a4 = new ActorData(BasicActorType.Base, b4);
		
		game.add(a1);
		game.add(a2);
		game.add(a3);
		game.add(a4);
		
		return game;
	}
}
