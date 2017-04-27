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

public class BasicPicker <A> extends StackPane{
	
	private int pos = 0;
	private List<A> myTypes;
	private Label myLabel;
	private ObjectProperty<A> myType;
	
	public BasicPicker(A actorType, List<A> actorTypes){
		super();
		this.myTypes = actorTypes;
		this.myType = new SimpleObjectProperty<A>(actorType);
		myLabel = new Label(actorType.toString());
		myLabel.setFont(Preferences.FONT_SMALL);
		myLabel.setAlignment(Pos.CENTER);
		myLabel.setTextAlignment(TextAlignment.CENTER);
		pos = actorTypes.indexOf(actorType);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> toggle());
		this.getChildren().add(myLabel);
	}
	public void toggle(){
		pos++;
		if(pos==myTypes.size())
			pos = 0;
		if(myTypes.size()==0){
			return;
		}
		myType.set(myTypes.get(pos));
		myLabel.setText(myType.get().toString());
	}
	public ObjectProperty<A> getTypeProperty(){
		return myType;
	}
	
}