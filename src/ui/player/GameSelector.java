package ui.player;
import java.util.ResourceBundle;

import ui.authoring.AuthoringView;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

public class GameSelector extends Application{

	private Group myRoot;
	private Scene myScene;
	private int size = 600;
	private int sizeX = size + 200;
	ResourceBundle resource;
	private String DEFAULT_RESOURCE_PACKAGE = "resource/";
	private String DEFAULT_CSS_PACKAGE = "css/";

	
	public Scene getScene(){
		return myScene;
	}
	
	public GameSelector(){
		
	}
	
	public GameSelector(String lang, String css){
		myRoot = new Group();
		myScene = new Scene(myRoot, sizeX, size);
		myScene.getStylesheets().add(css);
		resource = ResourceBundle.getBundle(lang);
		setup();
	}
	
	@Override
	public void start(Stage s) throws Exception {
		//GameSelector gameSelector = new GameSelector("English", "mainScreen.css");
		myRoot = new Group();
		myScene = new Scene(myRoot, sizeX, size);
		myScene.getStylesheets().add("mainScreen.css");
		resource = ResourceBundle.getBundle("English");
		setup();
		s.setScene(getScene());
		s.setTitle(resource.getString("GameSelector"));
		//s.setFullScreen(true);
		//s.setMaximized(true);
		//s.minWidthProperty().bind(myScene.heightProperty().multiply(2));
	    //s.minHeightProperty().bind(myScene.widthProperty().divide(2));
		//s.setResizable(false);
		s.show();
	}
	
	
	private void setup() {
		GridPane mainGrid = new GridPane();
		ScrollPane gameMenu = new ScrollPane();
		gameMenu.setPrefHeight((size * .9));
		Button plants = getOptionsButton(resource.getString("Plants"));
		Button bloons = getOptionsButton(resource.getString("Bloons"));
		
		VBox vBox = new VBox(16);
		vBox.getChildren().addAll(plants, bloons);
		gameMenu.setContent(vBox);
		mainGrid.add(gameMenu, 0, 0);
		
		
		//authButton.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> launchAuthorEnvView());
		
		
/*		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setLayoutX(size/4.0);
		vBox.setLayoutY(size/4.0);*/
		
		myRoot.getChildren().addAll(mainGrid);

	}



	private Button getOptionsButton(String title){
		Button button = new Button(title);
		button.setPrefSize(sizeX/4, 60);
		return button;
	}

	private void launchAuthorEnvView() {
		AuthoringView view = new AuthoringView();
		Stage s = (Stage) myScene.getWindow();
		s.setScene(new Scene(view, size, size, Color.WHITE));
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
