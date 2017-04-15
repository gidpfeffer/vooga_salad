package ui.authoring;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import gamedata.ActorData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.actor.ActorEditorView;

import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class LeftPaneView extends StackPane{
	
	private final Color[] COLOR_ROTATION = {
			CustomColors.BLUE_800,
			CustomColors.INDIGO,
			CustomColors.GREEN_900
	};
	
	private static final Map<String, String> DEFAULT_TOWERS;
	static {
		String path = "Pokemon Icons/";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Pikachu", path + "pikachu.png");
		map.put("Bullbasaur", path + "bullbasaur.png");
		map.put("Charmander", path + "charmander.png");
		map.put("Snorlax", path + "snorlax.png");
		map.put("Jigglypuff", path + "jigglypuff.png");
		DEFAULT_TOWERS = map;
	}
	private static final Map<String, String> DEFAULT_ENEMIES;
	static {
		String path = "balloons/";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Red", path + "red.png");
		map.put("Green", path + "green.png");
		map.put("Blue", path + "blue.png");

		DEFAULT_ENEMIES = map;
	}
	private static final Map<String, String> DEFAULT_PROJECTILES;
	static {
		String path = "projectiles/";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Fire", path + "flame.png");
		map.put("Water", path + "raindrop.png");
		map.put("Ice", path + "snowflake.png");
		map.put("Nature", path + "leaf.png");
		
		DEFAULT_PROJECTILES = map;
	}
	
	private PopViewDelegate myDelegate;
	private VBox myLeftPaneFront; //contains the buttons
//	private ActorEditorView myTowerView;
//	private ActorEditorView myEnemyView;
//	private ActorEditorView myProjectileView;

	private Map<String, ActorEditorView> actorTypeToView;

	//private ProjectileEditorMain myProjectileView;


	
	public LeftPaneView(PopViewDelegate delegate){
		super();
		myDelegate = delegate;
		setupViews();
	}
	
	private void setupViews() {
		myLeftPaneFront = new VBox(16);
		myLeftPaneFront.setAlignment(Pos.CENTER);
		StackPane.setMargin(myLeftPaneFront, new Insets(10));
		this.getChildren().add(myLeftPaneFront);
		setupDefaultActors();

	}
	
	private void setupDefaultActors() {
		addActor("Tower", "tower_icon.png", DEFAULT_TOWERS);
		addActor("Enemy","enemy_icon.png", DEFAULT_ENEMIES);
		addActor("Projectile","projectile_icon.png", DEFAULT_PROJECTILES);
		
	}

//	private void setupLeftPaneButtons() {
//		StackPane enemy = UIHelper.buttonStack(e -> launchEnemyView(), 
//				Optional.of(labelForStackButton("Enemy Editor")), 
//				Optional.of(imageForStackButton("enemy_icon.png")), 
//				Pos.CENTER_RIGHT, true);
//		StackPane tower = UIHelper.buttonStack(e -> launchTowerView(), 
//				Optional.of(labelForStackButton("Tower Editor")), 
//				Optional.of(imageForStackButton("tower_icon.png")), 
//				Pos.CENTER_RIGHT, true);
//		StackPane projectile = UIHelper.buttonStack(e -> launchProjectileView(), 
//				Optional.of(labelForStackButton("Projectile Editor")), 
//				Optional.of(imageForStackButton("projectile_icon.png")), 
//				Pos.CENTER_RIGHT, true);
////		StackPane game = UIHelper.buttonStack(e -> System.out.println("game"), 
////				Optional.of(labelForStackButton("Layout Editor")), 
////				Optional.of(imageForStackButton("layout_icon.png")), 
////				Pos.CENTER_RIGHT, true);
////		StackPane splash = UIHelper.buttonStack(e -> System.out.println(e), 
////				Optional.of(labelForStackButton("Splash Editor")), 
////				Optional.of(imageForStackButton("splash_icon.png")), 
////				Pos.CENTER_RIGHT, true);
//		
//		enemy.setPrefHeight(56);
//		tower.setPrefHeight(56);
//		projectile.setPrefHeight(56);
////		splash.setPrefHeight(56);
////		game.setPrefHeight(56);
//		
//		myLeftPaneFront.getChildren().addAll(tower, enemy, projectile);//, splash, game);
//	}
	private void addActor(String actorType, String imagePath, Map<String,String> defaultActors){
		ActorEditorView view = new ActorEditorView(myDelegate, actorType);
		view.setupDefaultActors(defaultActors);
		UIHelper.setBackgroundColor(view, COLOR_ROTATION[this.actorTypeToView.size()%COLOR_ROTATION.length]);
		this.actorTypeToView.put(actorType, view);
		StackPane button = UIHelper.buttonStack(e -> myDelegate.openView(view), 
				Optional.of(labelForStackButton(actorType)), 
				Optional.of(imageForStackButton(imagePath)), 
				Pos.CENTER_RIGHT, true);
		button.setPrefHeight(56);
		myLeftPaneFront.getChildren().add(button);
		
	}
	private Label labelForStackButton(String title){
		Label lbl = new Label(title);
		lbl.setTextFill(CustomColors.GREEN_100);
		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		return lbl;
	}
	private ImageView imageForStackButton(String imagePath){
		ImageView iv = new ImageView(new Image(imagePath));
		iv.setFitWidth(24);
		iv.setPreserveRatio(true);
		return iv;
	}
	
	private void deleteActorType(String actorType){
		//TODO
		this.actorTypeToView.get(actorType);
	}
	
	public Map<String, Collection<ActorData>> getActors(){
		Map<String, Collection<ActorData>> map = new HashMap<String, Collection<ActorData>>();
		for(Entry<String, ActorEditorView> entry : this.actorTypeToView.entrySet()){
			map.put(entry.getKey(), entry.getValue().getActorData());
		}
		return map;
	}
	
}
