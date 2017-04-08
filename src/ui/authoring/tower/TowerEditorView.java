package ui.authoring.tower;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.ActorData;
import gamedata.composition.BasicData;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
public class TowerEditorView extends AnchorPane {
	private static final Map<String, String> DEFAULT_TOWERS;
	static {
		String path = "Pokemon Icons/";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Pikachu", path + "pikachu.png");
		map.put("Bullbasaur", path + "bullbasaur.png");
		map.put("Charmander", path + "charmander.png");
		map.put("Snorlax", path + "snorlax.png");
		map.put("Jigglypuff", path + "jigglypuff.png");
		DEFAULT_TOWERS = map;
	}
	
	private HashMap<StackPane, List<ActorData>> myTowers;
	private PopViewDelegate myDelegate;
	private VBox myTowersView;
	private TowerInfoView myTowerInfoView;

	// TODO get projectile data first
	public TowerEditorView(PopViewDelegate delegate) {
		super();
		myDelegate = delegate;
		myTowers = new HashMap<StackPane, List<ActorData>>();
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
		setupDefaultTowers();
		setupInfoView(rightSide);
		setupBackButton();
		
	}
	
	private void setupInfoView(ScrollPane scroll){
		if(!this.myTowers.isEmpty())
			myTowerInfoView = new TowerInfoView(myTowers.get(myTowers.keySet().iterator().next()));
		else
			myTowerInfoView = new TowerInfoView();
		scroll.setContent(myTowerInfoView);
	}
	
	private void setupAddTowerButton() {
		//this.addTower(new Image("add_icon.png"), "Add New Tower");
		Label label = new Label("Add New Tower");
		label.setFont(Preferences.FONT_MEDIUM);
		label.setTextFill(CustomColors.GREEN_100);
		ImageView imageView = new ImageView(new Image("add_icon_w.png"));
		imageView.setFitHeight(40);
		imageView.setPreserveRatio(true);
		StackPane view = UIHelper.buttonStack(e -> addNewTower(), 
				Optional.of(label), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(64);
		UIHelper.setBackgroundColor(view, CustomColors.GREEN);
		VBox.setMargin(view, new Insets(8,24,8,8));
		this.myTowersView.getChildren().add( view);

	}

	private void setupSides(ScrollPane leftSide, ScrollPane rightSide) {
		double inset = 12.0;
		AnchorPane.setBottomAnchor(rightSide, inset);
		AnchorPane.setBottomAnchor(leftSide, inset);
		AnchorPane.setTopAnchor(rightSide, 48.0);
		AnchorPane.setTopAnchor(leftSide, 48.0);
		AnchorPane.setRightAnchor(rightSide, inset);
		AnchorPane.setLeftAnchor(leftSide, inset);

		rightSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		rightSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		leftSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		leftSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		leftSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		rightSide.setHbarPolicy(ScrollBarPolicy.NEVER);

		UIHelper.setDropShadow(rightSide);
		UIHelper.setDropShadow(leftSide);
		rightSide.prefWidthProperty().bind(this.widthProperty().divide(3.0/2).subtract(inset * 3 / 2));
		leftSide.prefWidthProperty().bind(this.widthProperty().divide(3.0).subtract(inset * 3 / 2));

		this.getChildren().addAll(leftSide, rightSide);

	}

	private void setupVBox(ScrollPane pane) {
		myTowersView = new VBox();
		myTowersView.setAlignment(Pos.CENTER);
		myTowersView.prefWidthProperty().bind(pane.widthProperty());
		pane.setContent(myTowersView);
	}

	private void setupDefaultTowers() {
		for (Entry<String, String> entry : DEFAULT_TOWERS.entrySet()) 
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
		field.setBackground(UIHelper.backgroundForColor(CustomColors.GREEN));
		field.setStyle("-fx-text-fill-color: #FFFFFF");
		field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		StackPane.setMargin(field, new Insets(8,8,8,64));
		lblWrapper.getChildren().add(field);
		
		StackPane view = UIHelper.buttonStack(e -> {}, 
				Optional.of(field), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(64);
		view.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectTower(view));
		UIHelper.setBackgroundColor(view, CustomColors.GREEN);
		VBox.setMargin(view, new Insets(8,24,8,8));
		myTowers.put(view, Arrays.asList(new ActorData[] { new ActorData(BasicActorType.Tower, new BasicData(name, imgPath))}));
		this.myTowersView.getChildren().add(myTowersView.getChildren().size() - 1, view);		
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
		// addTower();
	}
	
	private void selectTower(StackPane stackButton){
		List<ActorData> data = this.myTowers.get(stackButton);
		myTowerInfoView.setActorData(data);
	}

	public void getTowerData() {
		// TODO
	}

}
