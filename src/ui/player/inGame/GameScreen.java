package ui.player.inGame;

import java.util.HashMap;
import java.util.Map;

import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.general.ImageViewPane;
import ui.handlers.UIHandler;
import ui.player.login.BorderedAnchorPane;
import util.observerobservable.VoogaObserver;

public class GameScreen extends BorderedAnchorPane implements VoogaObserver<Map<Integer,FrontEndInformation>>{

	//private AnchorPane anchorPaneRoot;
	//private AnchorPane actorPane;
	private Pane base;
	private ImageViewPane ivp;
	private Pane topPane;
	private SettingsPane settingsPane;
	private Scene myScene;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private String backgroundImagePath = "default_map_background_0.jpg";
	private Map<Integer, Actor> actorsMap;
	
	public void setBackToLoginAction(EventHandler<ActionEvent> value) {
		settingsPane.setBackToLoginAction(value);
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	public ScreenSize getWindow() {
		return new ScreenSize(ivp.getImageInsets().y, ivp.getHeight() - ivp.getImageInsets().y,
				ivp.getImageInsets().x, ivp.getWidth() - ivp.getImageInsets().x);
	}
	
	public UIHandler getUIHandler() {
		return uihandler;
	}
	
	public GameScreen(UIHandler uihandler){
		this.uihandler = uihandler;
		//this.anchorPaneRoot = new AnchorPane();
		//actorPane = new AnchorPane();
		//this.myScene = new Scene(anchorPaneRoot);
		this.myScene = new Scene(root);
		this.actorsMap = new HashMap<Integer, Actor>();
		hud = uihandler.getSimpleHUD();
		setup();
		//FadeTransition ft = new FadeTransition(Duration.millis(1000), anchorPaneRoot);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	private void setup() {
		setupBackground();
		//setupActorPane();
		setupRight();
		setupLeft();
		setupHUD();
	}
	
/*	private void setupActorPane() {
		anchorPaneRoot.getChildren().add(actorPane);
		setNodeInAnchorPane(actorPane);
	}*/
	
	private void setupBackground() {
		//anchorPaneRoot.setStyle("-fx-background-color: mediumseagreen");
		root.setStyle("-fx-background-color: mediumseagreen");
		ivp = new ImageViewPane(new ImageView(new Image(backgroundImagePath)));
		topPane = new Pane();
		base = new AnchorPane(ivp, topPane);
		borderPane.setCenter(base);
		//anchorPaneRoot.getChildren().addAll(ivp);
		//root.getChildren().addAll(base);
		setNodeInAnchorPane(ivp);
		setNodeInAnchorPane(topPane);
		//setNodeInAnchorPane(actorPane);
	}
	
	private void setNodeInAnchorPane(Node node) {
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
	}
	
	private void setupRight() {
		//SidePanel sidePanel = new SidePanel(uihandler, actorsMap, anchorPaneRoot, uihandler.getOptions(), ivp);
		SidePanel sidePanel = new SidePanel(uihandler, actorsMap, (AnchorPane) root, uihandler.getOptions(), ivp);
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 10.0);
		//anchorPaneRoot.getChildren().add(sidePanel.getSidePane());
		//borderPane.setRight(sidePanel.getSidePane());
		root.getChildren().add(sidePanel.getSidePane());
		sidePanel.addInternalPanesToRoot();
	}
	
	private void setupLeft() {
		settingsPane = new SettingsPane();
		Button helpButton = settingsPane.getHelpButton();
		AnchorPane settings = settingsPane.getHelpPane();
		AnchorPane.setLeftAnchor(helpButton, 10.);
		AnchorPane.setTopAnchor(helpButton, 10.);
		//anchorPaneRoot.getChildren().addAll(helpButton, settings);
		root.getChildren().addAll(helpButton, settings);
		settings.setLayoutX(-settings.getPrefWidth());
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setLeftAnchor(hud.getGrid(), 10.);
		//anchorPaneRoot.getChildren().add(hud.getGrid());
		root.getChildren().add(hud.getGrid());
	}

	@Override
	public void update(Map<Integer, FrontEndInformation> arg) {
		//actorPane.getChildren().clear();
		actorsMap.keySet().removeIf(id -> {
			if(arg.containsKey(id)) {
				System.out.println(arg.get(id).getActorOption());
				return false;
			}
			root.getChildren().remove(actorsMap.get(id).getActor());
			return true;
		});
		
		arg.keySet().stream().forEach(id -> {
			Integer actorOption = arg.get(id).getActorOption();
			if(!actorsMap.containsKey(id)) {
				Actor newActor = new Actor(ivp, uihandler, actorsMap, actorOption, uihandler.getOptions().get(actorOption).getName(),
						uihandler.getOptions().get(actorOption).getImagePath());
				actorsMap.put(id, newActor);
				root.getChildren().add(newActor.getActor());
				//actorPane.getChildren().add(newActor.getActor());
			}
			Actor actor = actorsMap.get(id);
			double xCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getX(), (ivp.getWidth() - ivp.getImageInsets().x));
			double yCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getY(), (ivp.getHeight() - ivp.getImageInsets().y));
			actor.getActor().setLayoutX(xCoor);
			actor.getActor().setLayoutY(yCoor);
			//System.out.println("Layout: " + actor.getActor().getLayoutX() + " " + xCoor + " " + actor.getActor().getLayoutY() + " " + yCoor);
		});
	}
	
}