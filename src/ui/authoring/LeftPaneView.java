package ui.authoring;

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
import javafx.util.Duration;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class LeftPaneView extends StackPane{
	private VBox myLeftPaneFront; //contains the buttons
	private StackPane myLeftPaneBack; //contains the views for buttons 
	private PathEditorView myPathView;
	private TowerEditorView myTowerView;
	private EnemyEditorView myEnemyView;
	
	public LeftPaneView(){
		super();
		setupViews();
	}

	private void setupViews() {
		myLeftPaneFront = new VBox(16);
		myLeftPaneBack = new StackPane();
		myLeftPaneFront.setAlignment(Pos.CENTER);
		StackPane.setMargin(myLeftPaneFront, new Insets(10));
		setupLeftPaneButtons();
		setupPathView();
		setupTowerView();
		setupEnemyView();
		myLeftPaneBack.setScaleX(0);
		this.getChildren().add(myLeftPaneBack);
		this.getChildren().add(myLeftPaneFront);
	}
	
	private void setupLeftPaneButtons() {
		StackPane enemy = UIHelper.buttonStack(e -> System.out.println(e), 
				Optional.of(labelForStackButton("Enemy Editor")), 
				Optional.of(imageForStackButton("enemy_icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane tower = UIHelper.buttonStack(e -> System.out.println(e), 
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
		
		myLeftPaneFront.getChildren().addAll(enemy, tower, projectile, splash);
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
		myTowerView = new TowerEditorView();
		addBackButtonToView(myTowerView);
	}

	private void setupPathView() {
		myPathView = new PathEditorView();
		addBackButtonToView(myPathView);
	}
	
	private void setupEnemyView() {
		myEnemyView = new EnemyEditorView();
		addBackButtonToView(myEnemyView);
	}
	
	private void addBackButtonToView(Pane view){
		//TODO
	}
	
	private void flipFromNodeToNode(Node fromNode, Node toNode){
		ScaleTransition s1 = new ScaleTransition(Duration.millis(500), fromNode);
		s1.setFromX(1);
		s1.setToX(0);
		ScaleTransition s2 = new ScaleTransition(Duration.millis(500), toNode);
		s2.setFromX(0);
		s2.setToX(1);
		s1.setOnFinished(e -> {
			s2.play();
			});
		s1.play();
	}
	
	
}
