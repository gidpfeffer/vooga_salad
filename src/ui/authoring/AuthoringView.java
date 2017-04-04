package ui.authoring;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.level.LevelEditorView;
import ui.authoring.map.MapEditorView;
import ui.general.CustomColors;
import ui.general.Frame;
import ui.general.ImageButton;
import ui.general.UIHelper;
import ui.general.UIView;

public class AuthoringView extends BorderPane {

	private LevelEditorView myLevelView;
	private MapEditorView myMapView;
	private StackPane myLeftPane; //purpose of this pane is to flip animate 
	private StackPane myLeftPaneFront; //contains the buttons
	private StackPane myLeftPaneBack; //contains the views for buttons 
	private PathEditorView myPathView;
	private TowerEditorView myTowerView;
	private EnemyEditorView myEnemyView;


	public AuthoringView() {
		//this.setBackgroundColor(CustomColors.GREEN_200); TODO
	
		setupViews();
	}

	private void setupViews() {
		setupTitle();
		setupMapView();
		setupLevelView();
		setupLeftPane();
		setupBottomPane();
	}

	private void setupTitle() {
		Label title = new Label("Game Authoring Environment");
		title.setFont(Preferences.FONT_BIG);
		title.setPrefWidth(Preferences.SCREEN_WIDTH);
		title.setTextFill(Color.rgb(0, 0, 0, 0.75));
		title.setAlignment(Pos.CENTER);
		title.setPrefHeight(60);
		this.setTop(title);
	}

	private void setupMapView() {
		//this calculation assumes that height < width
		double inset = 60;
		double height = Math.round(Preferences.SCREEN_HEIGHT - 2*inset);
		int dim = 11;
		height = height - dim%2;
		myMapView = new MapEditorView();
		this.setCenter(myMapView);
	}

	private void setupLevelView() {
		myLevelView = new LevelEditorView();
		myLevelView.setMinWidth(100);
		this.setRight(myLevelView);
		
	}
	
	private StackPane stackWithButton(){
		//TODO
		return null;
	}


	
	private void setupLeftPane(){
		myLeftPane = new StackPane();
		myLeftPaneFront = new StackPane();
		myLeftPaneBack = new StackPane();
		myLeftPaneBack.setMinWidth(100);
		AnchorPane.setBottomAnchor(myLeftPane, 12.0);
		AnchorPane.setTopAnchor(myLeftPane, 12.0);
		AnchorPane.setLeftAnchor(myLeftPane, 12.0);
		
		setupLeftPaneButtons();
		setupPathView();
		setupTowerView();
		setupEnemyView();
		myLeftPaneBack.setScaleX(0);
		myLeftPane.getChildren().add(myLeftPaneBack);
		myLeftPane.getChildren().add(myLeftPaneFront);
		UIHelper.setBackgroundColor(myLeftPane,CustomColors.GREEN);
		this.setLeft(myLeftPane);
	}

	private void setupLeftPaneButtons() {
		//TODO
	}
	
	private void setupBottomPane() {
		Pane pane = new Pane();
		pane.setPrefHeight(60);
		this.setBottom(pane);
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
