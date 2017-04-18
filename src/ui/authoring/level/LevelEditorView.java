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

/**
 * Represents the right hand bar in the authoring environment.
 * Dictates the list of Levels and add level button. 
 * Allows access to WaveChooserMenu and LevelEditorMenu
 * by clicking on the Level buttons.
 * 
 * @author maddiebriere
 * @author alex blumenstock
 */

public class LevelEditorView extends VBox{
	//# of Enemies
	//TODO: remove duplicated code from LeftPaneView, potentially by making methods static 
	private int level;
	private Map<Integer, LevelData> myInfo; //Essentially the model
	private PopViewDelegate myDelegate;
	private Collection<ActorData> enemies;
	
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
				Optional.of(LevelUtil.labelForStackButton("Add Level")), 
				Optional.of(LevelUtil.imageForStackButton("add_icon.png")),
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
		ImageView img = LevelUtil.imageForStackButton("gear.png");
		UIHelper.setDropShadow(img);
		img.setFitWidth(32);
		img.setFitHeight(32);
		Label label = LevelUtil.labelForStackButton(String.format("Level %d",level));
		int localLevel = level;
		img.setOnMousePressed(e->launchLevelEditor());
		label.setOnMousePressed(e->launchWaveChooser(localLevel));
		StackPane nextLevel= UIHelper.buttonStack(e->{},  
				Optional.of(label), 
				Optional.ofNullable(img),Pos.CENTER_RIGHT, true);
		level++;
		nextLevel.setPrefHeight(56);
		VBox.setMargin(nextLevel, new Insets(8));
		return nextLevel;
	}
	
	public void update(Collection<ActorData> updated){
		//TODO: Make more sophisticated -- removal
		//TODO: CALL
		enemies = new ArrayList<ActorData>(updated);
	}
	
	private void launchLevelEditor(){
		LevelData current = myInfo.get(level);
		LevelEditorMenu lem  = new LevelEditorMenu(myDelegate, current);
		myDelegate.openViewWithSize(lem, PopupSize.SMALL);
	}
	
	private void launchWaveChooser(int level){
		LevelData current = myInfo.get(level);
		WaveChooserMenu wcm  = new WaveChooserMenu(myDelegate, enemies, current);
		myDelegate.openViewWithSize(wcm, PopupSize.MEDIUM);
	}
	
	public Map<Integer, LevelData> getLevels(){
		return myInfo;
	}

}
