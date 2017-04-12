package ui.authoring.actor;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import factories.DataGenerator;
import gamedata.FieldData;
import gamedata.StringToFieldFactory;
import gamedata.compositiongen.Data;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.authoring.AuthoringView;
import ui.general.CustomColors;
import ui.general.UIHelper;

/**
 * This dataView allows the user to input the numerical values of specific properties
 * @author TNK
 *
 */
public class NumericalDataView extends DataView{
	
	private HashMap<FieldData, Number> fieldToValue;	
	private VBox vbox;
	
	public NumericalDataView(String dataClassName, List<FieldData> fields) throws Exception{
		super();
		UIHelper.setBackgroundColor(this, CustomColors.AMBER_200);
		
		myDataClassName = dataClassName;
		myFields = fields;
		if(fields.size() == 0)
			myData = DataGenerator.makeData(dataClassName, new Object[] {});
		else
			checkFieldValidity(fields);


		setupViews();
	}
	
	private void checkFieldValidity(List<FieldData> fields) throws Exception {
		for(FieldData d : fields){
			Class<?> inputType = d.getMyType();
			if(!(
					inputType == double.class ||
					inputType == int.class ||
					inputType == Integer.class ||
					inputType == Double.class
					))
			{
				throw new Exception("Type="+inputType.getName()
						+ "\nCannot Instantiate NumericalDataView "
						+ "for "+ myDataClassName +". FieldData[] contains an element whos"
						+ " getMyType method returns a non-numerical class.");
			}
		}
		
	}

	private void setupViews() {
		Label name = new Label(myDataClassName);
		name.setTextFill(Color.WHITE);
		name.setFont(Preferences.FONT_SMALL_BOLD);
		name.setPrefHeight(24);
		AnchorPane.setTopAnchor(name, 4.0);
		AnchorPane.setLeftAnchor(name, 4.0);
		AnchorPane.setRightAnchor(name, 4.0);
		
		vbox = new VBox(4.0);
		AnchorPane.setTopAnchor(vbox, 8.0 + name.getPrefHeight());
		AnchorPane.setLeftAnchor(vbox, 4.0);
		AnchorPane.setRightAnchor(vbox, 4.0);
		AnchorPane.setBottomAnchor(vbox, 4.0);
		
		setupFieldPane();
		
	}

	private void setupFieldPane(){
		for(FieldData d: myFields){
			addField(d);
		}
	}
	
	private void addField(FieldData d){
		AnchorPane content = new AnchorPane();
		
		Label fieldName = new Label(d.getMyName());
		fieldName.setTextFill(Color.WHITE);
		AnchorPane.setLeftAnchor(fieldName, 4.0);
		AnchorPane.setTopAnchor(fieldName, 4.0);
		AnchorPane.setBottomAnchor(fieldName, 4.0);
		fieldName.setMaxWidth(80);;
		content.getChildren().add(fieldName);
		
		TextField input = new TextField();
		//IMPORTANT: makes field number only and also updates the value of field
		input.textProperty().addListener((e,oldVal,newValue) -> didEditField(d,input, newValue));
		AnchorPane.setRightAnchor(input, 4.0);
		AnchorPane.setTopAnchor(input, 4.0);
		AnchorPane.setBottomAnchor(input, 4.0);
		content.getChildren().add(input);
		vbox.getChildren().add(content);
		System.out.println("DataView is set up");
	}
	
	private void setupFieldValueMap(){
		fieldToValue = new HashMap<FieldData, Number>();
		for(FieldData d: myFields){
			fieldToValue.put(d, 0.0);
		}
		
	}
	
	private void didEditField(FieldData fieldData, TextField field, String newValue){
		if (!newValue.matches("\\d*")) {
			field.setText(newValue.replaceAll("[^\\d]", ""));
        }
		fieldToValue.put(fieldData, (Number) StringToFieldFactory.getObject(newValue, fieldData.getMyType()));
	}

}
