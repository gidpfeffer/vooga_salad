package ui.authoring.level;

import java.util.Collection;
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
	private LevelData myInfo; //Essentially the model
	private PopViewDelegate myDelegate;
	private Collection<ActorData> enemies;
	//private PathData myPathData;
	
	public LevelEditorView(PopViewDelegate delegate, Collection<ActorData> enemies){
		super();
		this.setAlignment(Pos.CENTER);
		this.enemies=enemies;
		myDelegate=delegate;
		level=1;
		myInfo = new LevelData();

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
		StackPane nextLevel= UIHelper.buttonStack(e->launchWaveChooser(),  
				Optional.of(labelForStackButton(String.format("Level %d",level))), 
				Optional.ofNullable(null),Pos.CENTER_RIGHT, true);
		level++;
		nextLevel.setPrefHeight(56);
		VBox.setMargin(nextLevel, new Insets(8));
		return nextLevel;
	}
	
	private void launchWaveChooser(){
		LevelEditorMenu wcm  = new LevelEditorMenu(myDelegate,enemies, myInfo);
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

}
