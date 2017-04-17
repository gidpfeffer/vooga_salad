package ui.authoring.actor;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import types.BasicActorType;
import ui.Preferences;

public class BasicActorPicker extends StackPane{
	
	private int pos = 0;
	private List<BasicActorType> actorTypes;
	private Label myLabel;
	private ObjectProperty<BasicActorType> myType;
	public BasicActorPicker(BasicActorType actorType, List<BasicActorType> actorTypes){
		super();
		this.actorTypes = actorTypes;
		this.myType = new SimpleObjectProperty<BasicActorType>(actorType);
		myLabel = new Label(actorType.getType());
		myLabel.setFont(Preferences.FONT_SMALL);
		myLabel.setAlignment(Pos.CENTER);
		myLabel.setTextAlignment(TextAlignment.CENTER);
		pos = actorTypes.indexOf(actorType);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> toggle());
		this.getChildren().add(myLabel);
	}
	public void toggle(){
		pos++;
		if(pos==actorTypes.size())
			pos = 0;
		myType.set(actorTypes.get(pos));
		myLabel.setText(myType.get().getType());
	}
	public ObjectProperty<BasicActorType> getBasicActorTypeProperty(){
		return myType;
	}
	
}
