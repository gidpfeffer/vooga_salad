package ui.authoring.actor;

import java.util.List;

import gamedata.FieldData;
import gamedata.compositiongen.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public abstract class DataView extends AnchorPane {
	protected Data myData;
	protected String myDataClassName;
	protected List<FieldData> myFields;
	protected Label nameLabel;



	
}
