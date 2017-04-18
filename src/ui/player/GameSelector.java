package ui.player;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import ui.Preferences;
import ui.general.UIHelper;
import ui.player.login.BorderedAnchorPane;
import ui.player.login.Login.Game;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameSelector extends BorderedAnchorPane{

	private Scene myScene;
	private List<Game> gamesList; 
	private double height = Preferences.SCREEN_HEIGHT;
	private double width = Preferences.SCREEN_WIDTH;
	private ResourceBundle resource;
	private ScrollPane gameMenu;
	
	public Scene getScene() {
		return myScene;
	}
	
	public GameSelector(String lang, String css, List<Game> gamesList){
		this.gamesList = gamesList;
		gameMenu = new ScrollPane();
		myScene = new Scene(root, width, height);
		myScene.getStylesheets().add(css);
		resource = ResourceBundle.getBundle(lang);
		setup();
	}
	
	private void setup() {
		setupLayout();
		setupTitle();
		setupScrollPane();
		setupSideArrows();
		setupBottom();
	}

	private void setupLayout(){
		//root.setId("towerBackground");
		gameMenu.getStyleClass().add("scroll-pane");
		root.getStyleClass().add("anchor-pane");
	}
	
	private void setupTitle() {
		Label title = new Label(resource.getString("gameselector"));
		title.setId("title");
		BorderPane.setAlignment(title, Pos.CENTER);
		borderPane.setTop(title);
	}

	private void setupScrollPane(){
		HBox gamesHBox = new HBox(50);
		gamesHBox.setPadding(new Insets(0., 50., 0., 50.));
		//gamesHBox.setStyle("-fx-background-color: blue;");
		gamesList.forEach(game -> {
			VBox vbox = new VBox(40);
			StackPane g = new StackPane();
			g.getStyleClass().add("image-stack-pane");
			g.getChildren().add(new ImageView(new Image(game.getImagePath(), 300, 250, false, true)));
			g.setOnMouseClicked(game.getClicked());
			Text name = new Text(game.getName());
			name.setId("text");
			vbox.getChildren().addAll(name, g);
			vbox.setAlignment(Pos.CENTER);
			gamesHBox.getChildren().add(vbox);
		});
		gameMenu.setContent(gamesHBox);
		gamesHBox.setAlignment(Pos.CENTER);
		gameMenu.setHbarPolicy(ScrollBarPolicy.NEVER);
		gameMenu.setVbarPolicy(ScrollBarPolicy.NEVER);
		gameMenu.setBackground(Background.EMPTY);
		//gameMenu.setStyle("-fx-background-color: green");
		gameMenu.setPadding(new Insets(15, 12, 15, 12));
		borderPane.setCenter(gameMenu);
		BorderPane.setAlignment(gamesHBox, Pos.CENTER);
	}
	
	private void setupSideArrows(){
         ImageView left = new ImageView(new Image("left_arrow.png", 50, 50, true, true));
         ImageView right = new ImageView(new Image("right_arrow.png", 50, 50, true, true));
         borderPane.setLeft(left);
         borderPane.setRight(right);
         right.setOnMousePressed(event -> moveScrollPane(.1));
         left.setOnMousePressed(event -> moveScrollPane(-.1));
         BorderPane.setAlignment(left, Pos.CENTER);
         BorderPane.setAlignment(right, Pos.CENTER);
	}
	
	private void moveScrollPane(double value) {
		final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(gameMenu.hvalueProperty(), gameMenu.getHvalue() + value);
        final KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
	}
	
	private void setupBottom(){
		StackPane profile = simpleImageStackPane("Profile", "profile_icon.png", e -> {});
		StackPane help = simpleImageStackPane("Help", "splash_icon.png", e -> {});
		HBox hbox = new HBox(100);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.getChildren().addAll(profile, help);
		hbox.setAlignment(Pos.CENTER);
		borderPane.setBottom(hbox);
	}
	
	private StackPane simpleImageStackPane(String name, String image, EventHandler<MouseEvent> event){
		StackPane sp = UIHelper.buttonStack(event, 
				Optional.of(new Label(name)), 
				Optional.of(imageForStackButton(image)), 
				Pos.CENTER_RIGHT, true);
		sp.getStyleClass().add("stack-pane");
		return sp;
	}
	
	private ImageView imageForStackButton(String imagePath){
		ImageView iv = new ImageView(new Image(imagePath));
		iv.setFitWidth(30);
		iv.setPreserveRatio(true);
		return iv;
	}
}