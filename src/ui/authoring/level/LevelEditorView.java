package ui.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import gamedata.ActorData;
import gamedata.LevelData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Preferences;
import ui.authoring.PopupSize;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class LevelEditorView extends VBox{
	//# of Enemies
	//TODO: remove duplicated code from LeftPaneView, potentially by making methods static 
	private int level;
	private Map<Integer, LevelData> myInfo; //Essentially the model
	private PopViewDelegate myDelegate;
	private Collection<ActorData> enemies;
	//private PathData myPathData;
	
	public LevelEditorView(PopViewDelegate delegate, Collection<ActorData> enemies){
		super();
		this.setAlignment(Pos.CENTER);
		this.enemies=enemies;
		myDelegate=delegate;
		level=1;
		myInfo = new HashMap<Integer, LevelData>();

		//TODO:move text to resource file
		StackPane levelOne = nextLevel();
		this.getChildren().add(levelOne);
		StackPane newLevel = UIHelper.buttonStack(e->addNewLevel(), 
				Optional.of(labelForStackButton("Add Level")), Optional.of(imageForStackButton("add_icon.png")),
				Pos.CENTER_RIGHT, true);
		newLevel.setPrefHeight(56);
		VBox.setMargin(newLevel, new Insets(8));

		this.getChildren().add(newLevel);
	}
	
	private void addNewLevel(){
		this.getChildren().add(this.getChildren().size()-1, nextLevel());
	}
	
	private StackPane nextLevel(){
		LevelData newLevel = new LevelData();
		myInfo.put(level, newLevel);
		ImageView img = imageForStackButton("splash_icon.png");
		int localLevel = level;
		img.setOnMousePressed(e->launchLevelEditor());
		StackPane nextLevel= UIHelper.buttonStack(e->launchWaveChooser(localLevel),  
				Optional.of(labelForStackButton(String.format("Level %d",level))), 
				Optional.ofNullable(img),Pos.CENTER_RIGHT, true);
		level++;
		nextLevel.setPrefHeight(56);
		VBox.setMargin(nextLevel, new Insets(8));
		return nextLevel;
	}
	
	private void launchLevelEditor(){
		
	}
	
	private void launchWaveChooser(int level){
		LevelData current = myInfo.get(level);
		LevelEditorMenu wcm  = new LevelEditorMenu(myDelegate, enemies, current);
		myDelegate.openViewWithSize(wcm, PopupSize.MEDIUM);
	}
	
	private ImageView imageForStackButton(String imagePath){
		Image img = new Image(imagePath);
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		return imageView;
	}
	
	private Label labelForStackButton(String title){
		Label lbl = new Label(title);
		lbl.setTextFill(CustomColors.GREEN_100);
		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		return lbl;
	}
	
	public Map<Integer, LevelData> getLevels(){
		return myInfo;
	}

}
