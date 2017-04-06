package ui.authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

/**
 * Provides the user the ability to add new types of towers and 
 * customize their properties 
 * @author TNK
 *
 */
public class TowerEditorView extends AnchorPane{
	private static final Map<String, Image> DEFAULT_TOWERS;
	static {
		String path = "Pokemon Icons/";
		HashMap<String, Image> map = new HashMap<String, Image>();
		map.put("Pikachu", new Image(path + "pikachu.png"));
		map.put("Bullbasaur", new Image(path + "bullbasaur.png"));
		map.put("Charmander", new Image(path + "charmander.png"));
		map.put("Snorlax", new Image(path + "snorlax.png"));
		map.put("Jigglypuff", new Image(path + "jigglypuff.png"));
		DEFAULT_TOWERS = map;
	}

	private PopViewDelegate myDelegate;
	private VBox myTowersView;
	
	//TODO get projectile data first
	public TowerEditorView(PopViewDelegate delegate){
		super();
		myDelegate = delegate;
		setupViews();

	}


	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30.,30.));
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
		setupBackButton();
		
	}
	
	private void setupAddTowerButton() {
		this.addTower(new Image("add_icon.png"), "Add New Tower");
	}


	private void setupSides(ScrollPane leftSide, ScrollPane rightSide) {
		double inset = 12.0;
		
		AnchorPane.setBottomAnchor(rightSide, inset);
		AnchorPane.setBottomAnchor(leftSide, inset);
		AnchorPane.setTopAnchor(rightSide, 48.0);
		AnchorPane.setTopAnchor(leftSide, 48.0);
		AnchorPane.setRightAnchor(rightSide, inset);
		AnchorPane.setLeftAnchor(leftSide, inset);
		
		rightSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200)+";");
		rightSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200)+";");
		leftSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200)+";");
		leftSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200)+";");

		UIHelper.setDropShadow(rightSide);
		UIHelper.setDropShadow(leftSide);
		rightSide.prefWidthProperty().bind(this.widthProperty().divide(2.0).subtract(inset*3/2 ));
		leftSide.prefWidthProperty().bind(this.widthProperty().divide(2.0).subtract(inset*3/2));
		
		
		this.getChildren().addAll(leftSide,rightSide);

	}

	private void setupVBox(ScrollPane pane){
		myTowersView = new VBox();
		myTowersView.setAlignment(Pos.CENTER);
		myTowersView.prefWidthProperty().bind(pane.widthProperty());
		pane.setContent(myTowersView);
	}



	private void setupDefaultTowers() {
		
		for(Entry<String, Image> entry: DEFAULT_TOWERS.entrySet()){
			addTower(entry.getValue(), entry.getKey());
		}
	}
	
	private void addTower(Image img, String name){
		StackPane view;
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(48);
		imageView.setPreserveRatio(true);
		StackPane lblWrapper = new StackPane();
		Node lbl;
		int index;
		if(myTowersView.getChildren().size() == 0){
			lbl = new Label(name);
			((Label) lbl).setFont(Preferences.FONT_MEDIUM);
			((Label) lbl).setTextFill(CustomColors.GREEN_100);
			index = 0;
		}
		else{
			lbl = new TextField(name);
			((TextField) lbl).setFont(Preferences.FONT_MEDIUM);
			((TextField) lbl).setAlignment(Pos.CENTER);
			((TextField) lbl).setBackground(UIHelper.backgroundForColor(CustomColors.GREEN));
			((TextField) lbl).setStyle("-fx-text-fill-color: #FFFFFF");
			((TextField) lbl).setStyle("-fx-background-color: transparent;");

			index = myTowersView.getChildren().size() - 1;

		}
		StackPane.setMargin(lbl, new Insets(8,8,8,64));
		lblWrapper.getChildren().add(lbl);
		view = UIHelper.buttonStack(e -> {}, 
				Optional.of(lblWrapper), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(64);
		UIHelper.setBackgroundColor(view, CustomColors.GREEN);
		VBox.setMargin(view, new Insets(8,24,8,8));
		this.myTowersView.getChildren().add(index, view);		
	}
	
	public void getTowerData(){
		//TODO
	}
	
}
