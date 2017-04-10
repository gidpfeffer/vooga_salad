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
import javafx.scene.layout.BorderPane;
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
	

	public Scene getScene() {
		return myScene;
	}
	
	public List<Double> getWindow() {
		List<Double> screenSize = new ArrayList<>(Arrays.asList(myStage.getWidth(), myStage.getHeight()));
		return screenSize;
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
	
	public void setup(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		//setupBorderPane();
		setupBackground();
		setupRight(shots, towers, troops, bases);
		setupLeft();
		setupHUD();
	}
	
	private void setupBackground() {
		ImageView imv = new ImageView(new Image(backgroundImagePath));  
		anchorPaneRoot.getChildren().add(imv);
		//imv.fitWidthProperty().bind();
		//imv.fitHeightProperty().bind(center.heightProperty());

		imv.fitWidthProperty().bind(myStage.widthProperty()); 
		imv.fitWidthProperty().bind(myStage.heightProperty()); 
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
		settings.setLayoutX(-settings.getWidth());
		
		//borderPane.setLeft(settingsPane.getHelpPane());
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setRightAnchor(hud.getGrid(), 10.);
		anchorPaneRoot.getChildren().add(hud.getGrid());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO oberserve map
		//the listOfActors holds all actors added to the map through dragging onto the screen
		
	}
	
}
