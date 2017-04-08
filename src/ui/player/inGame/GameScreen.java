package ui.player.inGame;

import java.util.HashMap;
import java.util.Map;

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

public class GameScreen {

	private AnchorPane anchorPaneRoot;
	private Stage myStage;
	private Scene myScene;
	//private BorderPane borderPane;
	private UIHandler uihandler;
	private Map<String, String> iconImages;
	private SimpleHUD hud;
	private double screenSizeWidth;
	private double scrrenSizeHeight;
	private String backgroundImagePath = "default_map_background_0.jpg";
	

	public Scene getScene() {
		return myScene;
	}
	
	public GameScreen(Stage stage, UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases){
		this.anchorPaneRoot = new AnchorPane();
		this.myScene = new Scene(anchorPaneRoot);
		//this.borderPane = new BorderPane();
		this.uihandler = uihandler;
		myStage = stage;
		screenSizeWidth = stage.getWidth();
		scrrenSizeHeight = stage.getHeight();
		iconImages = new HashMap<>();
		iconImages.put("towers", "tower_icon");
		iconImages.put("bases", "undo_icon");
		iconImages.put("troops", "enemy_icon");
		iconImages.put("shots", "splash_icon");
		
		hud = new SimpleHUD();
		
		setup(uihandler, shots, towers, troops, bases);
	}
	
	public void setup(UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		//setupBorderPane();
		setupBackground();
		setupRight(uihandler, shots, towers, troops, bases);
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

		    //pane.setCenter(img);
	}
	
	private void setupRight(UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		SidePanelPanes optionsPane = new SidePanelPanes(uihandler, shots, towers, troops, bases);
		for (PaneController controller : optionsPane.getListOfControllers()) {
			controller.getPane().setLayoutX(screenSizeWidth);
			anchorPaneRoot.getChildren().add(controller.getPane());
		}
		SidePanelPanes mainPane = new SidePanelPanes(uihandler, iconImages, optionsPane.getListOfControllers());
		anchorPaneRoot.getChildren().add(mainPane.getMainPane());
		AnchorPane.setRightAnchor(mainPane.getMainPane(), 0.0);
		//borderPane.setRight(mainPane.getMainPane());
	}
	
	private void setupLeft() {
		// TODO Auto-generated method stub
		SettingsPane settingsPane = new SettingsPane(uihandler);
		Button helpButton = settingsPane.getHelpButton();
		AnchorPane.setLeftAnchor(helpButton, 10.);
		AnchorPane.setTopAnchor(helpButton, 10.0);
		anchorPaneRoot.getChildren().addAll(helpButton, settingsPane.getHelpPane());
		//borderPane.setLeft(settingsPane.getHelpPane());
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setRightAnchor(hud.getGrid(), 10.);
		anchorPaneRoot.getChildren().add(hud.getGrid());
	}
	
}
