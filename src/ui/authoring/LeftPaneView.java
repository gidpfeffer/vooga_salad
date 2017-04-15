package ui.authoring;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.actor.ActorEditorView;

import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class LeftPaneView extends StackPane{
	
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
	private StackPane myLeftPaneBack; //contains the views for buttons 
	private ActorEditorView myTowerView;
	private ActorEditorView myEnemyView;

	//private ProjectileEditorMain myProjectileView;

	private ActorEditorView myProjectileView;

	
	public LeftPaneView(PopViewDelegate delegate){
		super();
		myDelegate = delegate;
		setupViews();
	}
	
	private void setupViews() {
		myLeftPaneFront = new VBox(16);
		myLeftPaneBack = new StackPane();
		myLeftPaneFront.setAlignment(Pos.CENTER);
		StackPane.setMargin(myLeftPaneFront, new Insets(10));
		setupLeftPaneButtons();
		myLeftPaneBack.setScaleX(0);
		this.getChildren().add(myLeftPaneBack);
		this.getChildren().add(myLeftPaneFront);
		initializeEnemyView();
		
	}
	
	private void setupLeftPaneButtons() {
		StackPane enemy = UIHelper.buttonStack(e -> launchEnemyView(), 
				Optional.of(labelForStackButton("Enemy Editor")), 
				Optional.of(imageForStackButton("enemy_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane tower = UIHelper.buttonStack(e -> launchTowerView(), 
				Optional.of(labelForStackButton("Tower Editor")), 
				Optional.of(imageForStackButton("tower_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane splash = UIHelper.buttonStack(e -> System.out.println(e), 
				Optional.of(labelForStackButton("Splash Editor")), 
				Optional.of(imageForStackButton("splash_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane projectile = UIHelper.buttonStack(e -> launchProjectileView(), 
				Optional.of(labelForStackButton("Projectile Editor")), 
				Optional.of(imageForStackButton("projectile_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane game = UIHelper.buttonStack(e -> System.out.println("game"), 
				Optional.of(labelForStackButton("Layout Editor")), 
				Optional.of(imageForStackButton("layout_icon.png")), 
				Pos.CENTER_RIGHT, true);
		
		enemy.setPrefHeight(56);
		tower.setPrefHeight(56);
		splash.setPrefHeight(56);
		projectile.setPrefHeight(56);
		game.setPrefHeight(56);
		
		myLeftPaneFront.getChildren().addAll(tower, enemy, projectile, splash, game);
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

	private void launchTowerView() {
		if(myTowerView == null){
			myTowerView = new ActorEditorView(myDelegate, BasicActorType.Tower);
			myTowerView.setupDefaultTowers(DEFAULT_TOWERS);
		}
		myDelegate.openView(myTowerView);
	}

	
	private void launchEnemyView() {
		myDelegate.openView(myEnemyView);
	}
	
	private void initializeEnemyView(){
		myEnemyView = new ActorEditorView(myDelegate, BasicActorType.Troop);
		myEnemyView.setupDefaultTowers(DEFAULT_ENEMIES);
		UIHelper.setBackgroundColor(myEnemyView, CustomColors.INDIGO);
	}


	private void launchProjectileView(){
		if(myProjectileView == null){
			myProjectileView = new ActorEditorView(myDelegate, BasicActorType.Shot);
			myProjectileView.setupDefaultTowers(DEFAULT_PROJECTILES);
			UIHelper.setBackgroundColor(myProjectileView, CustomColors.GREEN);

		}
		myDelegate.openView(myProjectileView);
	}

	public Collection<ActorData> getEnemyData(){
		return myEnemyView.getActorData();
	}
	
	public Collection<ActorData> getTowerData(){
		return myTowerView.getActorData();
	}
	
	public Collection<ActorData> getProjectileData(){
		return myProjectileView.getActorData();
	}
	
}
