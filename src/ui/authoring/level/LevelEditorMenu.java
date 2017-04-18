package ui.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gamedata.ActorData;
import gamedata.LevelData;
import gamedata.WaveData;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

/**
 * Allows user to set level-specific variables
 * (e.g., health multiplier)
 * 
 * @author maddiebriere
 */

public class LevelEditorMenu extends AnchorPane {
	
	PopViewDelegate myDelegate;
	private LevelData myData;
	ScrollPane settings;
	
	public LevelEditorMenu(PopViewDelegate delegate, LevelData level) {
		super();
		myDelegate = delegate;
		myData = level;
		setupViews();
		populateViews();
	}
	
	private void populateViews(){
		setupBackButton();
		setupFields();
	}
	
	private void setupFields(){
		TextField difficulty = addField("Difficulty (0.0-1.0)");
		difficulty.setMinWidth(180);
		TextField duration = addField("Duration (seconds)");
		duration.setMinWidth(180);
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		VBox.setMargin(duration, new Insets(20));
		VBox.setMargin(difficulty, new Insets(20));
		
		root.getChildren().addAll(difficulty, duration);
		settings.setContent(root);
	}

	private void setupViews() {
		//TODO: Fix scroll bar
		settings = new ScrollPane();
		settings.setHbarPolicy(ScrollBarPolicy.NEVER);
		setupBack(settings);
	}
	
	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}
	
	private void setupBack(ScrollPane pane){
		double inset = 12.0;
		setVerticalAnchors(inset, pane);
		setupBar(inset, 1.65, pane);
		this.getChildren().add(pane);
	}
	
	private void setVerticalAnchors(double inset, ScrollPane pane){
		AnchorPane.setTopAnchor(pane, inset);
		AnchorPane.setBottomAnchor(pane, inset);
	}
	
	private void setupBar(double inset, double size, ScrollPane pane){
		AnchorPane.setLeftAnchor(pane, inset);
		
		AnchorPane.setRightAnchor(pane, 48.0);
		
		pane.setBackground(new Background(new BackgroundFill(CustomColors.BLUE_50,null,null)));
		pane.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.BLUE_50) + ";");

		UIHelper.setDropShadow(pane);
		pane.prefHeightProperty().bind(this.heightProperty().divide(size).subtract(inset * 3 / 2));
	}
	
	private TextField addField(String value){
		StackPane lblWrapper = new StackPane();
		TextField field = new TextField(value);
		field.setPrefWidth(150);
		field.setFont(Preferences.FONT_MEDIUM);
		field.setAlignment(Pos.CENTER);
		field.setBackground(UIHelper.backgroundForColor(CustomColors.BLUE_200));
		field.setStyle("-fx-text-fill-color: #FFFFFF");
		field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.BLUE_200) + ";");
		lblWrapper.getChildren().add(field);
		return field;
	}

}
