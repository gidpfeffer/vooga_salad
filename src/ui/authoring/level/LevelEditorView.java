package ui.authoring.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.Frame;
import ui.general.UIHelper;

public class LevelEditorView extends VBox{
	//# of Enemies
	//TODO: remove duplicated code from LeftPaneView, potentially by making methods static 
	private int level;
	public LevelEditorView(){
		super();
		this.setSpacing(10);
		level=1;
		//TODO:move text to resource file
		StackPane levelOne=nextLevel();
		this.getChildren().add(levelOne);
		StackPane newLevel=UIHelper.buttonStack(e->addNewLevel(), Optional.of(labelForStackButton("Add Wave")), Optional.of(imageForStackButton("add_icon.png")), Pos.CENTER_RIGHT, true);
		newLevel.setPrefHeight(56);
		this.getChildren().add(newLevel);
	}
	private void addNewLevel(){
		
		this.getChildren().add(this.getChildren().size()-1, nextLevel());
	}
	private StackPane nextLevel(){
		StackPane nextLevel= UIHelper.buttonStack(e->launchWaveEditor(),  Optional.of(labelForStackButton(String.format("Wave %d",level))), Optional.ofNullable(null),Pos.CENTER, true);
		level++;
		nextLevel.setPrefHeight(56);
		return nextLevel;
	}
	private void launchWaveEditor(){
		LevelEditorMenu lem=new LevelEditorMenu();
		lem.showAndWait()
	      .filter(response -> response == ButtonType.APPLY)
	      .ifPresent(response ->editWaveData());
	}
	private void editWaveData(){
		
	}
	private ImageView imageForStackButton(String imagePath){
		ImageView iv = new ImageView(new Image(imagePath));
		iv.setFitWidth(24);
		iv.setPreserveRatio(true);
		return iv;
	}
	private Label labelForStackButton(String title){
		Label lbl = new Label(title);
		lbl.setTextFill(CustomColors.GREEN_100);
		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		return lbl;
	}

}
