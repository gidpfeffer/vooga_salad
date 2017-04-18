package ui.authoring.level;

import gamedata.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ui.authoring.delegates.PopViewDelegate;

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
		LevelUtil.setupBackButton(myDelegate, this);
		setupFields();
	}
	
	private void setupFields(){
		TextField difficulty = LevelUtil.addField("");
		difficulty.setMinWidth(180);
		
		TextField duration = LevelUtil.addField("");
		duration.setMinWidth(180);
		
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		VBox.setMargin(duration, new Insets(20));
		VBox.setMargin(difficulty, new Insets(20));
		
		root.getChildren().addAll(difficulty, duration);
		settings.setContent(root);
	}

	private void setupViews() {
		settings = new ScrollPane();
		settings.setHbarPolicy(ScrollBarPolicy.NEVER);
		setupBack(settings);
	}
	
	private void setupBack(ScrollPane pane){
		double inset = 12.0;
		LevelUtil.setVerticalAnchors(inset, pane);
		LevelUtil.setupBar(inset, 1.65, pane, this, 3 / 2);
		this.getChildren().add(pane);
	}

}
