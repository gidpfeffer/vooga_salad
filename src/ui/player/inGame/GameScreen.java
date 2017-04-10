package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gamedata.ActorData;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.handlers.UIHandler;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.Bindings;

public class GameScreen implements Observer{

	private AnchorPane anchorPaneRoot;
	private Stage myStage;
	private Scene myScene;
	//private BorderPane borderPane;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private String backgroundImagePath = "default_map_background_0.jpg";
	private List<Actor> listOfActors;
	private TempData tempData;
	

	public Scene getScene() {
		return myScene;
	}
	
	public List<Double> getWindow() {
		List<Double> screenSize = new ArrayList<>(Arrays.asList(myStage.getWidth(), myStage.getHeight()));
		return screenSize;
	}
	
	//temporary data
	public GameScreen(Stage stage, UIHandler uihandler, TempData tempData) {
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		this.listOfActors = new ArrayList<Actor>();		
		hud = new SimpleHUD();
		this.tempData = tempData;
		this.myStage = stage;
		this.uihandler = uihandler;
		setup();
	}
	
	public GameScreen(Stage stage, UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases){
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		this.listOfActors = new ArrayList<Actor>();
		//this.borderPane = new BorderPane();
		this.uihandler = uihandler;
		myStage = stage;
		
		hud = new SimpleHUD();
		
		setup(shots, towers, troops, bases);
	}
	
	//temp setup
	private void setup() {
		setupBackground();
		setupRight();
		setupLeft();
		setupHUD();
	}
	
	//temp right
	private void setupRight() {
		// TODO Auto-generated method stub
		SidePanelTemp sidePanelTemp = new SidePanelTemp(uihandler, listOfActors, anchorPaneRoot, tempData);
		AnchorPane.setRightAnchor(sidePanelTemp.getSidePane(), 0.0);
	}

	private void setup(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		//setupBorderPane();
		setupBackground();
		setupRight(shots, towers, troops, bases);
		setupLeft();
		setupHUD();
	}
	
	private void setupBackground() {
		ImageView imv = new ImageView(new Image(backgroundImagePath));  
		//anchorPaneRoot.getChildren().add(imv);
		//imv.fitWidthProperty().bind();
		//imv.fitHeightProperty().bind(center.heightProperty());
		imv.setPreserveRatio(true);
		imv.fitWidthProperty().bind(myStage.widthProperty()); 
		imv.fitWidthProperty().bind(myStage.heightProperty()); 
		
		StackPane background = new StackPane();
		background.setPrefWidth(myStage.getWidth());
		background.setPrefHeight(myStage.getHeight());
		BackgroundImage myBI= new BackgroundImage(new Image(backgroundImagePath,background.getWidth(),background.getHeight(),true,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
		          BackgroundSize.DEFAULT);
		
		//then you set to your node
		background.setBackground(new Background(myBI));
		anchorPaneRoot.getChildren().add(background);
		AnchorPane.setBottomAnchor(background, 0.0);
		AnchorPane.setTopAnchor(background, 0.0);
		AnchorPane.setLeftAnchor(background, 0.0);
		AnchorPane.setRightAnchor(background, 0.0);
		
	}
	
	private void setupRight(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		SidePanel sidePanel = new SidePanel(uihandler, listOfActors, anchorPaneRoot, towers, shots, troops, bases);
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 0.0);
		//borderPane.setRight(mainPane.getMainPane());
	}
	
	private void setupLeft() {
		// TODO Auto-generated method stub
		SettingsPane settingsPane = new SettingsPane();
		Button helpButton = settingsPane.getHelpButton();
		AnchorPane settings = settingsPane.getHelpPane();
		AnchorPane.setLeftAnchor(helpButton, 10.);
		AnchorPane.setTopAnchor(helpButton, 10.);
		anchorPaneRoot.getChildren().addAll(helpButton, settings);
		settings.setLayoutX(-settings.getPrefWidth());
		
		//borderPane.setLeft(settingsPane.getHelpPane());
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setLeftAnchor(hud.getGrid(), 10.);
		anchorPaneRoot.getChildren().add(hud.getGrid());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO oberserve map
		//the listOfActors holds all actors added to the map through dragging onto the screen
		
	}
	
}
