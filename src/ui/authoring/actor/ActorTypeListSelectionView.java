package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import types.BasicActorType;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class ActorTypeListSelectionView extends StackPane{

	private ObjectProperty<List<BasicActorType>> mySelectedTypes;
	private List<BasicActorType> actorTypes;
	private VBox myVBox;
	public ActorTypeListSelectionView(List<BasicActorType> actorTypes){
		mySelectedTypes = new SimpleObjectProperty<>(new ArrayList<BasicActorType>());
		this.actorTypes = actorTypes;
		setupViews();
		
	}
	private void setupViews() {
		UIHelper.setBackgroundColor(this, CustomColors.BLUE_200);
		myVBox = new VBox(8.0);		
		StackPane.setAlignment(myVBox, Pos.CENTER);
		StackPane.setMargin(myVBox, new Insets(8.0));
		this.getChildren().add(myVBox);
		
		for(BasicActorType actorType : actorTypes){
			addSwitchForActor(actorType);
		}
	}
	private void addSwitchForActor(BasicActorType actorType) {
		Label label = new Label(actorType.getType());
		label.setAlignment(Pos.CENTER);
		setLabelUnselected(label);
		StackPane button = UIHelper.buttonStack(e -> {
			//determine if it is selected
			if(!mySelectedTypes.get().contains(actorType)){
				mySelectedTypes.get().add(actorType);
				setLabelSelected(label);
			}else{
				mySelectedTypes.get().remove(actorType);
				setLabelUnselected(label);
			}
			setLabelSelected(label);
		}, Optional.of(label), Optional.ofNullable(null), Pos.CENTER, false);
		button.setPrefHeight(24);
		UIHelper.setBackgroundColor(button, Color.TRANSPARENT);
		VBox.setMargin(button, new Insets(8.0));
		//LOOK HERE: Next thing to do is to add this to DataView as an input field for TYPE List
		myVBox.getChildren().add(button);
	}
	private void setLabelSelected(Label label){
		label.setFont(Preferences.FONT_SMALL_BOLD);
		label.setTextFill(CustomColors.GREEN_50);
		UIHelper.setBackgroundColor(label, CustomColors.GREEN);
		
	}
	private void setLabelUnselected(Label label){
		label.setFont(Preferences.FONT_SMALL);
		label.setTextFill(CustomColors.AMBER_700);
		UIHelper.setBackgroundColor(label, Color.TRANSPARENT);
	}
	
	
	
	
}
