package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.ActorData;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.general.ImageViewPane;
import ui.handlers.UIHandler;
import util.observerobservable.VoogaObserver;

public class GameScreen implements VoogaObserver<Map<Integer,FrontEndInformation>>{

	private AnchorPane anchorPaneRoot;
	private ImageViewPane ivp;
	private Stage myStage;
	private Scene myScene;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private String backgroundImagePath = "default_map_background_0.jpg";
	private Map<Integer, Actor> actorsMap;
	

	public Scene getScene() {
		return myScene;
	}
	
	public List<Double> getWindow() {
		List<Double> screenSize = new ArrayList<>(Arrays.asList(myStage.getWidth(), myStage.getHeight()));
		return screenSize;
	}
	
/*	//temporary data
	public GameScreen(Stage stage, UIHandler uihandler, TempData tempData) {
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		this.ActorsMap = new HashMap<Integer, Actor>();		
		hud = new SimpleHUD();
		this.tempData = tempData;
		this.myStage = stage;
		this.uihandler = uihandler;
		setup();
	}*/
	
	public GameScreen(Stage stage, UIHandler uihandler){
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		this.actorsMap = new HashMap<Integer, Actor>();
		hud = new SimpleHUD();
		//this.borderPane = new BorderPane();
		this.uihandler = uihandler;
		myStage = stage;
		
		setup(uihandler.getShotOptions(), uihandler.getTowerOptions(), 
				uihandler.getTroopOptions(), uihandler.getBaseOptions());
	}
	
/*	//temp setup
	private void setup() {
		setupBackground();
		setupRight();
		setupLeft();
		setupHUD();
	}*/

	private void setup(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		//setupBorderPane();
		setupBackground();
		setupRight(shots, towers, troops, bases);
		setupLeft();
		setupHUD();
	}
	
	private void setupBackground() {
		anchorPaneRoot.setStyle("-fx-background-color: mediumseagreen");
		ivp = new ImageViewPane(new ImageView(new Image(backgroundImagePath)));
		
		//then you set to your node
		anchorPaneRoot.getChildren().add(ivp);
		AnchorPane.setBottomAnchor(ivp, 0.0);
		AnchorPane.setTopAnchor(ivp, 0.0);
		AnchorPane.setLeftAnchor(ivp, 0.0);
		AnchorPane.setRightAnchor(ivp, 0.0);
	}
	
/*	//temp right
	private void setupRight() {
		SidePanelTemp sidePanelTemp = new SidePanelTemp(uihandler, listOfActors, anchorPaneRoot, tempData);
		AnchorPane.setRightAnchor(sidePanelTemp.getSidePane(), 10.0);
		anchorPaneRoot.getChildren().add(sidePanelTemp.getSidePane());
		sidePanelTemp.addInternalPanesToRoot();
	}*/
	
	
	private void setupRight(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		SidePanel sidePanel = new SidePanel(uihandler, actorsMap, anchorPaneRoot, towers, shots, troops, bases);
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 10.0);
		anchorPaneRoot.getChildren().add(sidePanel.getSidePane());
		sidePanel.addInternalPanesToRoot();
	}
	
	private void setupLeft() {
		SettingsPane settingsPane = new SettingsPane();
		Button helpButton = settingsPane.getHelpButton();
		AnchorPane settings = settingsPane.getHelpPane();
		AnchorPane.setLeftAnchor(helpButton, 10.);
		AnchorPane.setTopAnchor(helpButton, 10.);
		anchorPaneRoot.getChildren().addAll(helpButton, settings);
		settings.setLayoutX(-settings.getPrefWidth());
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setLeftAnchor(hud.getGrid(), 10.);
		anchorPaneRoot.getChildren().add(hud.getGrid());
	}

	@Override
	public void update(Map<Integer, FrontEndInformation> arg) {
		for (Integer i : arg.keySet()) {
			Actor actor;
			if (actorsMap.containsKey(i.toString())) {
				actor = actorsMap.get(i.toString());
			} else {
				actor = new Actor(ivp, uihandler, actorsMap, i, 
						uihandler.getOptions().get(i.toString()).getName(), 
						uihandler.getOptions().get(i.toString()).getImagePath());
				actorsMap.put(i, actor);
			}
			Pane paneActor = actor.getActor();
			double xCoor = util.Transformer.ratioToCoordinate(arg.get(i).getActorLocation().getX(),myScene.getWidth());
			double yCoor = util.Transformer.ratioToCoordinate(arg.get(i).getActorLocation().getY(), myScene.getHeight());
			paneActor.setLayoutX(xCoor);
			paneActor.setLayoutY(yCoor);
		}
		
	}
	
}
