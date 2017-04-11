package ui.authoring;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import types.ActorType;
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
	
	private PopViewDelegate myDelegate;
	private VBox myLeftPaneFront; //contains the buttons
	private StackPane myLeftPaneBack; //contains the views for buttons 
	private ActorEditorView myTowerView;
	private ActorEditorView myEnemyView;
	
	
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
		setupTowerView();
		setupEnemyView();
		myLeftPaneBack.setScaleX(0);
		this.getChildren().add(myLeftPaneBack);
		this.getChildren().add(myLeftPaneFront);
	}
	
	private void setupLeftPaneButtons() {
		StackPane enemy = UIHelper.buttonStack(e -> myDelegate.openView(myEnemyView), 
				Optional.of(labelForStackButton("Enemy Editor")), 
				Optional.of(imageForStackButton("enemy_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane tower = UIHelper.buttonStack(e -> myDelegate.openView(myTowerView), 
				Optional.of(labelForStackButton("Tower Editor")), 
				Optional.of(imageForStackButton("tower_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane splash = UIHelper.buttonStack(e -> System.out.println(e), 
				Optional.of(labelForStackButton("Splash Editor")), 
				Optional.of(imageForStackButton("splash_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane projectile = UIHelper.buttonStack(e -> System.out.println(e), 
				Optional.of(labelForStackButton("Projectile Editor")), 
				Optional.of(imageForStackButton("projectile_icon.png")), 
				Pos.CENTER_RIGHT, true);
		
		enemy.setPrefHeight(56);
		tower.setPrefHeight(56);
		splash.setPrefHeight(56);
		projectile.setPrefHeight(56);
		
		myLeftPaneFront.getChildren().addAll(tower, enemy, projectile, splash);
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

	private void setupTowerView() {
		myTowerView = new ActorEditorView(myDelegate, ActorType.TOWER);
		myTowerView.setupDefaultTowers(DEFAULT_TOWERS);
		UIHelper.setBackgroundColor(myTowerView, CustomColors.GREEN_100);
	}

	
	private void setupEnemyView() {
		myEnemyView = new ActorEditorView(myDelegate, ActorType.ENEMY);
		myEnemyView.setupDefaultTowers(DEFAULT_ENEMIES);
		UIHelper.setBackgroundColor(myEnemyView, CustomColors.GREEN_100);
	}
	
	
	
}
