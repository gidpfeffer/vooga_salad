package builders;

import java.util.ArrayList;
import java.util.List;

import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.GameData;
import gamedata.ProjectileData;
import gamedata.ProjectileType;
import gamedata.composition.MoveWithSetPathData;
import gamedata.composition.ShootTargetFarData;
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
		
		BasicActorType tower = game.addType("Tower");
		BasicActorType shot = game.addType("Shot");
		BasicActorType troop = game.addType("Troop");
		
		
		BasicData b1 = new BasicData("Tower1", "tower_icon.png");
		BasicData b2 = new BasicData("Projectile1", "projectile_icon.png");
		BasicData b3 = new BasicData("Troop1", "enemy_icon.png");
		
		ActorData a1 = new ActorData(tower, b1);
		ActorData a2 = new ActorData(shot, b2);
		ActorData a3 = new ActorData(troop, b3);
		
		game.add(a1);
		game.add(a2);
		game.add(a3);
		
		return game;
	}
	
	public static GameData getComplexSampleGame(){
		GameData game = new GameData();
		
		//BasicActorType tower = game.addType("Tower");
		//BasicActorType shot = game.addType("Shot");
		//BasicActorType troop = game.addType("Troop");
		BasicActorType base = game.addType("Base");
		
		List<Grid2D> samplePath = new ArrayList<Grid2D>();
		
		samplePath.add(new Coordinates(0.1574074074074074, 0.18724279835390947));
		samplePath.add(new Coordinates(0.1537037037037037, 0.4045267489711934));
		samplePath.add(new Coordinates(0.06851851851851852, 0.5757201646090535));
		samplePath.add(new Coordinates(0.15, 0.8292181069958847));
		samplePath.add(new Coordinates(0.418518518518518, 0.8358024691358025));
		samplePath.add(new Coordinates(0.4648148148148148, 0.6382716049382716));
		samplePath.add(new Coordinates(0.35555555555555557, 0.27613168724279835));
		samplePath.add(new Coordinates(0.42962962962962964, 0.13786008230452676));
		samplePath.add(new Coordinates(0.6148148148148148, 0.17407407407407408));
		samplePath.add(new Coordinates(0.5833333333333334, 0.7139917695473251));
		samplePath.add(new Coordinates(0.6981481481481482, 0.8950617283950617));
		samplePath.add(new Coordinates(0.6981481481481482, 0.8950617283950617));
		samplePath.add(new Coordinates(0.8888888888888888, 0.7633744855967078));
		samplePath.add(new Coordinates(0.8018518518518518, 0.22016460905349794));
		samplePath.add(new Coordinates(0.9944444444444445, 0.2366255144032922));
		
		PathGenerator.addPath(game.getMyPaths(), samplePath);
		
		List<Integer> possiblePaths = new ArrayList<Integer>();
		possiblePaths.add(0);
		
		MoveWithSetPathData pathData = PathGenerator.generateMoveData
				(game.getMyPaths(), possiblePaths, .005);
		ShootTargetFarData shoot = 
				new ShootTargetFarData(0.5, 10, new BasicActorType("Troop"), 1, .1);
		//Shoots with Actor at index 1 (shot)
		
		BasicData b1 = new BasicData("Tower", "tower_icon.png");
		BasicData b2 = new BasicData("Shoot", "projectile_icon.png");
		
		BasicData b3 = new BasicData("Bob", "enemy_icon.png");
		BasicData b4 = new BasicData("Jiggly", "Pokemon Icons/jigglypuff.png");
		BasicData b5 = new BasicData("Pika", "Pokemon Icons/pikachu.png");
		
		BasicData b6 = new BasicData("Grass", "grass.png");
		
		
		ActorData a1 = new ActorData(new BasicActorType("Tower"), b1); //0
		a1.addData(shoot); //tower has shooting capabilities
		ActorData a2 = new ActorData(new BasicActorType("Projectile"), b2);//1
		
		ActorData a3 = new ActorData(new BasicActorType("Troop"), b3);//2
		a3.addData(pathData);
		
		ActorData a4 = new ActorData(new BasicActorType("Troop"), b4);//3
		a4.addData(pathData);
		
		ActorData a5 = new ActorData(new BasicActorType("Troop"), b5);//4
		a4.addData(pathData);
		
		ActorData a6 = new ActorData(base, b6);//5
		
		game.add(a1);
		game.add(a2);
		game.add(a3);
		game.add(a4);
		game.add(a5);
		game.add(a6);
		
		return game;
	}
}
