package ui.player;
import java.util.Optional;
import java.util.ResourceBundle;

import ui.Preferences;
import ui.general.CustomColors;
import ui.general.UIHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSelector {

	private AnchorPane root;
	private Scene myScene;
	private BorderPane myBorderPane = new BorderPane();
	private double height = Preferences.SCREEN_HEIGHT;
	private double width = Preferences.SCREEN_WIDTH;
	ResourceBundle resource;
	
	public Scene getScene() {
		return myScene;
	}
	
	public GameSelector(String lang, String css){
		root = new AnchorPane();
		myBorderPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE
				, CornerRadii.EMPTY, Insets.EMPTY)));
		myScene = new Scene(myBorderPane, width, height);
		myScene.getStylesheets().add(css);
		resource = ResourceBundle.getBundle(lang);
		setup();
	}
	
	private void setup() {
		//setupBorderPane();
		setupTitle();
		setupScrollPane();
		setupPreview();
		setupBottom();
	}
	
	private void setupBorderPane() {
		AnchorPane.setBottomAnchor(myBorderPane, 0.0);
		AnchorPane.setTopAnchor(myBorderPane, 0.0);
		AnchorPane.setLeftAnchor(myBorderPane, 0.0);
		AnchorPane.setRightAnchor(myBorderPane, 0.0);
		root.getChildren().add(myBorderPane);
		
	}

	private void setupTitle() {
		Label title = new Label(resource.getString("gameselector"));
		title.setFont(Preferences.FONT_BIG);
		BorderPane.setAlignment(title, Pos.CENTER);
		title.setPrefHeight(50);
		myBorderPane.setTop(title);
	}

	private void setupScrollPane(){
		ScrollPane gameMenu = new ScrollPane(new Button("Game"));
		gameMenu.setPadding(new Insets(15, 12, 15, 12));
		myBorderPane.setLeft(gameMenu);
	}
	
	private void setupPreview(){
		 Image image = new Image("default_map_background_0.jpg");
         ImageView view = new ImageView();
         view.setImage(image);
         view.setFitWidth(700);
         view.setPreserveRatio(true);
         view.setSmooth(true);
         view.setCache(true);
         myBorderPane.setRight(view);
	}
	private void setupBottom(){
		StackPane profile = UIHelper.buttonStack(e -> System.out.println("go to profile"), 
				Optional.of(new Label("Profile")), 
				Optional.of(imageForStackButton("profile-icon.png")), 
				Pos.CENTER_RIGHT, true);
		StackPane help = UIHelper.buttonStack(e -> System.out.println("go to help"), 
				Optional.of(new Label("Help")), 
				Optional.of(imageForStackButton("splash_icon.png")), 
				Pos.CENTER_RIGHT, true);
		HBox hbox = new HBox(20);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.getChildren().addAll(profile, help);
		myBorderPane.setBottom(hbox);
	}
	
	private ImageView imageForStackButton(String imagePath){
		ImageView iv = new ImageView(new Image(imagePath));
		iv.setFitWidth(30);
		iv.setPreserveRatio(true);
		return iv;
	}
}