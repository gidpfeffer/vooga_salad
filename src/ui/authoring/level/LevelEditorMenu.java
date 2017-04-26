package ui.authoring.level;


import gamedata.LevelData;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
	private int levelNum;
	private LevelData myData;
	ScrollPane settings;
	
	public LevelEditorMenu(PopViewDelegate delegate, LevelData level, int levelNum) {
		super();
		this.levelNum = levelNum;
		myDelegate = delegate;
		myData = level;
		setupViews();
		populateViews();
	}
	
	private void populateViews(){

		LevelUtil.setupBackButton(myDelegate, this);
		setupFields();
	}
	
	private String fieldCheck(double field){
		if(field==-1){
			return ""+1.0; 
		}
		else{
			return ""+field;
		}
	}
	
	private void setupFields(){
		//TODO: Resource file
		Label title = LevelUtil.labelForTitle("Level " + levelNum);
		title.setMinWidth(50);
		
		String dur = fieldCheck(myData.getDuration());
		String att = fieldCheck(myData.getAttackMultiplier());
		String spe = fieldCheck(myData.getSpeedMultiplier());
		String hea = fieldCheck(myData.getHealthMultiplier());
		
		
		HBox duration = generateEntry("             Duration", dur, (o,oldText,newText) -> 
			this.updateDuration((String)newText));
		HBox attack= generateEntry("Attack Multiplier", att, (o,oldText,newText) -> 
			this.updateAttack((String)newText));
		HBox health = generateEntry("Health Multiplier", hea, (o,oldText,newText) -> 
			this.updateHealth((String)newText));
		HBox speed = generateEntry("Speed Multiplier", spe, (o,oldText,newText) -> 
		this.updateSpeed((String)newText));
		
		VBox root = new VBox();
		//VBox.setMargin(root)
		
		AnchorPane.setBottomAnchor(root, 0.0);
		AnchorPane.setTopAnchor(root, 36.);
		AnchorPane.setLeftAnchor(root, 0.0);
		AnchorPane.setRightAnchor(root, 0.0);
		
		root.getChildren().addAll(title, duration, attack, health, speed);
		root.setAlignment(Pos.CENTER);
		settings.setContent(root);
	}
	
	private void updateDuration(String newText){
		try{
			double duration = Double.parseDouble(newText);
			myData.setDuration(duration);
		}catch(Exception e){
			//TODO: Error handling
		}
	}
	
	private void updateAttack(String newText){
		try{
			double attack = Double.parseDouble(newText);
			myData.setAttackMultiplier(attack);
		}catch(Exception e){
			//TODO: Error handling
		}
	}
	
	private void updateHealth(String newText){
		try{
			double health = Double.parseDouble(newText);
			myData.setHealthMultiplier(health);
		}catch(Exception e){
			//TODO: Error handling
		}
	}
	
	private void updateSpeed(String newText){
		try{
			double speed = Double.parseDouble(newText);
			myData.setSpeedMultiplier(speed);
		}catch(Exception e){
			//TODO: Error handling
		}
	}
	
	private HBox generateEntry(String name, String value, ChangeListener listen){
		TextField field = LevelUtil.addField(value);
		field.textProperty().addListener(listen);
		field.setPrefWidth(116);
		HBox box = LevelUtil.generateHBox();
		box.getChildren().addAll(LevelUtil.labelForStackButtonBlue(name), field);
		VBox.setMargin(box, new Insets(18));
		return box;

	}

	private void setupViews() {
		settings = new ScrollPane();
		settings.setHbarPolicy(ScrollBarPolicy.NEVER);
		settings.setVbarPolicy(ScrollBarPolicy.NEVER);
		setupBack(settings);
	}
	
	private void setupBack(ScrollPane pane){
		double inset = 12.0;
		LevelUtil.setVerticalAnchors(inset, pane);
		LevelUtil.setupBar(inset, 1.65, pane, this, 3 / 2);
		this.getChildren().add(pane);
	}

}
