package ui.authoring.actor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.ActorData;
import gamedata.BasicData;
import gamedata.composition.LimitedHealthData;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import types.ActorType;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

/**
 * Provides the user the ability to add new types of towers and customize their
 * properties
 * 
 * @author TNK
 *
 */
public class ActorEditorView extends AnchorPane {
	private static final double BUTTON_HEIGHT = 72;
	
	
	private HashMap<StackPane, ActorData> myActors;
	private PopViewDelegate myDelegate;
	private VBox myActorsView;
	private ActorInfoView myActorInfoView;
	private BasicActorType myActorType;

	// TODO get projectile data first
	public ActorEditorView(PopViewDelegate delegate, BasicActorType type) {
		super();
		myDelegate = delegate;
		myActorType = type;
		myActors = new HashMap<StackPane, ActorData>();
		UIHelper.setBackgroundColor(this, CustomColors.BLUE_800);
		setupViews();

	}

	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}

	private void setupViews() {
		ScrollPane leftSide = new ScrollPane();
		ScrollPane rightSide = new ScrollPane();
		setupSides(leftSide, rightSide);
		setupVBox(leftSide);
		setupAddTowerButton();
		setupInfoView(rightSide);
		setupBackButton();
		
	}
	
	private void setupInfoView(ScrollPane scroll){
		myActorInfoView = new ActorInfoView();
		myActorInfoView.prefWidthProperty().bind(scroll.widthProperty());
		myActorInfoView.minHeightProperty().bind(scroll.heightProperty());
		scroll.setContent(myActorInfoView);
	}
	
	private void setupAddTowerButton() {
		Label label = new Label("Add New");
		label.setFont(Preferences.FONT_MEDIUM_BOLD);
		label.setTextFill(CustomColors.BLUE_50);
		ImageView imageView = new ImageView(new Image("add_icon_w.png"));
		imageView.setFitHeight(40);
		imageView.setPreserveRatio(true);
		StackPane view = UIHelper.buttonStack(e -> addNewTower(), 
				Optional.of(label), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(BUTTON_HEIGHT);
		UIHelper.setBackgroundColor(view, CustomColors.BLUE_200);
		VBox.setMargin(view, new Insets(8));
		this.myActorsView.getChildren().add( view);

	}

	private void setupSides(ScrollPane leftSide, ScrollPane rightSide) {
		double inset = 12.0;
		AnchorPane.setBottomAnchor(rightSide, inset);
		AnchorPane.setBottomAnchor(leftSide, inset);
		AnchorPane.setTopAnchor(rightSide, 48.0);
		AnchorPane.setTopAnchor(leftSide, 48.0);
		AnchorPane.setRightAnchor(rightSide, inset);
		AnchorPane.setLeftAnchor(leftSide, inset);

		rightSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.BLUE_50) + ";");
		rightSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.BLUE_50) + ";");
		leftSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.BLUE_50) + ";");
		leftSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.BLUE_50) + ";");
	
		leftSide.setBorder(new Border(new BorderStroke[] {new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT) }));
		rightSide.setBorder(Border.EMPTY);
		

		leftSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		rightSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		leftSide.setVbarPolicy(ScrollBarPolicy.NEVER);
		rightSide.setVbarPolicy(ScrollBarPolicy.NEVER);

		UIHelper.setDropShadow(rightSide);
		UIHelper.setDropShadow(leftSide);
		rightSide.prefWidthProperty().bind(this.widthProperty().divide(3.0/2).subtract(inset * 3 / 2));
		leftSide.prefWidthProperty().bind(this.widthProperty().divide(3.0).subtract(inset * 3 / 2));

		this.getChildren().addAll(leftSide, rightSide);

	}

	private void setupVBox(ScrollPane pane) {
		myActorsView = new VBox();
		myActorsView.setAlignment(Pos.CENTER);
		myActorsView.prefWidthProperty().bind(pane.widthProperty().add(-2));
		pane.setContent(myActorsView);
	}

	public void setupDefaultTowers(Map<String,String> mapOfNameToImagePath) {
		for (Entry<String, String> entry : mapOfNameToImagePath.entrySet()) 
			addTower(entry.getValue(), entry.getKey());
	}

	/**
	 * This method adds a StackButton to the Vbox with the tower image
	 * and name. It also creates an ActorData and stores it in the 
	 * myTowers map, binding the ActorData to the StackButton
	 * 
	 * @param imgPath the fil path of the image
	 * @param name the name of the tower, can be changed later.
	 */
	private void addTower(String imgPath, String name){
		Image img = new Image(imgPath);
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		StackPane lblWrapper = new StackPane();
		TextField field = new TextField(name);
		field.setFont(Preferences.FONT_MEDIUM);
		field.setAlignment(Pos.CENTER);
		field.setBackground(UIHelper.backgroundForColor(CustomColors.BLUE_200));
		field.setStyle("-fx-text-fill-color: #FFFFFF");
		field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.BLUE_200) + ";");
		StackPane.setMargin(field, new Insets(8,32,8,32));
		lblWrapper.getChildren().add(field);
		
		StackPane view = UIHelper.buttonStack(e -> {}, 
				Optional.of(field), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(BUTTON_HEIGHT);
		view.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectTower(view));
		field.textProperty().addListener((o,oldText,newText) -> this.updateTowerName(view, newText));
		UIHelper.setBackgroundColor(view, CustomColors.BLUE_200);
		VBox.setMargin(view, new Insets(8));
		myActors.put(view, new ActorData(myActorType, new BasicData(name, imgPath), new LimitedHealthData()));
		myActorsView.getChildren().add(myActorsView.getChildren().size() - 1, view);		
	}

	/**
	 * the action when the plus button is pressed on the bottom of the screen
	 * prompts user to select an image and adds a new tower with default values
	 */
	private void addNewTower() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectc Image File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
		if(selectedFile!= null){
			String s = selectedFile.getName();
			addTower(s,s.substring(0, s.indexOf(".")) );
		}
	}
	
	private void selectTower(StackPane stackButton){
		myActorInfoView.setActorData(this.myActors.get(stackButton));
	}
	
	private void updateTowerName(StackPane pane, String text){
		this.myActors.get(pane).getBasic().setName(text);	
	}
	
	public void getTowerData() {
		// TODO
	}
	
	

}
