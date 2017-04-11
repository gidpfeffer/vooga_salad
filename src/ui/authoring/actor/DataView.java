package ui.authoring.actor;

import gamedata.FieldData;
import gamedata.compositiongen.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public abstract class DataView extends AnchorPane {
	protected Data myData;
	protected String myDataClassName;
	protected FieldData[] myFields;
	protected Label nameLabel;



	
}
