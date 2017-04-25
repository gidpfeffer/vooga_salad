package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.Observable;
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
		myVBox = new VBox();		
		StackPane.setAlignment(myVBox, Pos.CENTER);
		StackPane.setMargin(myVBox, new Insets(8.0));
		
		for(BasicActorType actorType : actorTypes){
			addSwitchForActor(actorType);
		}
		
		this.getChildren().add(myVBox);

	}
	private void addSwitchForActor(BasicActorType actorType) {
		Label label = new Label(actorType.getType());
		label.setAlignment(Pos.CENTER);
		label.setTextFill(CustomColors.BLACK_GRAY);
		setLabelUnselected(label);
		StackPane button = UIHelper.buttonStack(e -> {
			//determine if it is selected
			if(!mySelectedTypes.get().contains(actorType)){
				mySelectedTypes.get().add(actorType);
				setLabelSelected(label);
				UIHelper.setBackgroundColor((StackPane)e.getSource(), CustomColors.GREEN_200);
			}else{
				mySelectedTypes.get().remove(actorType);
				setLabelUnselected(label);
				UIHelper.setBackgroundColor((StackPane)e.getSource(), Color.TRANSPARENT);
			}
			System.out.println(mySelectedTypes.get().size());
			List<BasicActorType> copy = new ArrayList<>(mySelectedTypes.get());
			mySelectedTypes.set(copy);
		}, Optional.of(label), Optional.ofNullable(null), Pos.CENTER, false);
		button.setPrefHeight(32);
		UIHelper.setBackgroundColor(button, Color.TRANSPARENT);
		VBox.setMargin(button, new Insets(4.0));
		//LOOK HERE: Next thing to do is to add this to DataView as an input field for TYPE List
		myVBox.getChildren().add(button);
	}
	private void setLabelSelected(Label label){
		label.setFont(Preferences.FONT_SMALL_BOLD);		
	}
	private void setLabelUnselected(Label label){
		label.setFont(Preferences.FONT_SMALL);
	}
	public ObjectProperty<List<BasicActorType>> getBasicActorTypeList() {
		return this.mySelectedTypes;
	}
	
	
	
	
}
