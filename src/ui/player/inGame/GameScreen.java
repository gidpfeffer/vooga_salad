package ui.player.inGame;

import java.util.HashMap;
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
import ui.Preferences;
import ui.general.ImageViewPane;
import ui.handlers.UIHandler;
import util.observerobservable.VoogaObserver;

public class GameScreen implements VoogaObserver<Map<Integer,FrontEndInformation>>{

	private AnchorPane anchorPaneRoot;
	private ImageViewPane ivp;
	
	@SuppressWarnings("unused")
	private Stage myStage;
	private Scene myScene;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private String backgroundImagePath = "default_map_background_0.jpg";
	private Map<Integer, Actor> actorsMap;
	

	public Scene getScene() {
		return myScene;
	}
	
	public ScreenSize getWindow() {
		return new ScreenSize(ivp.getImageInsets().y, ivp.getHeight() - ivp.getImageInsets().y,
				ivp.getImageInsets().x, ivp.getWidth() - ivp.getImageInsets().x);
	}
	
	public GameScreen(Stage stage, UIHandler uihandler){
		stage.setHeight(Preferences.SCREEN_HEIGHT);
		stage.setWidth(Preferences.SCREEN_WIDTH);
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		this.actorsMap = new HashMap<Integer, Actor>();
		hud = new SimpleHUD();
		this.uihandler = uihandler;
		myStage = stage;
		
		setup(uihandler.getShotOptions(), uihandler.getTowerOptions(), 
				uihandler.getTroopOptions(), uihandler.getBaseOptions());
	}

	private void setup(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
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
	
	
	private void setupRight(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		SidePanel sidePanel = new SidePanel(uihandler, actorsMap, anchorPaneRoot, towers, shots, troops, bases, ivp);
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 10.0);
		anchorPaneRoot.getChildren().add(sidePanel.getSidePane());
		sidePanel.addInternalPanesToRoot();
	}
	
	private void setupLeft() {
		SettingsPane settingsPane = new SettingsPane(myStage);
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
		actorsMap.keySet().removeIf(id -> {
			if(arg.containsKey(id)) {
				return false;
			}
			anchorPaneRoot.getChildren().remove(actorsMap.get(id).getActor());
			return true;
		});
		arg.keySet().stream().forEach(id -> {
			Integer actorOption = arg.get(id).getActorOption();
			if(!actorsMap.containsKey(id)) {
				Actor newActor = new Actor(ivp, uihandler, actorsMap, actorOption, uihandler.getOptions().get(actorOption).getName(),
						uihandler.getOptions().get(actorOption).getImagePath());
				actorsMap.put(id, newActor);
				anchorPaneRoot.getChildren().add(newActor.getActor());
			}
			Actor actor = actorsMap.get(id);
			double xCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getX(),myScene.getWidth());
			double yCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getY(), myScene.getHeight());
			actor.getActor().setLayoutX(xCoor);
			actor.getActor().setLayoutY(yCoor);
			System.out.println("Layout: " + actor.getActor().getLayoutX() + " " + xCoor + " " + actor.getActor().getLayoutY() + " " + yCoor);
		});
	}
	
}