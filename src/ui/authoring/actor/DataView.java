package ui.authoring.actor;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import builders.DataGenerator;
import builders.OptionGenerator;
import gamedata.FieldData;
import gamedata.PathData;
import gamedata.StringToFieldFactory;
import gamedata.compositiongen.Data;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import types.BasicActorType;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

public class DataView extends AnchorPane {

	private Data myData;
	private String myDataClassName;
	private VBox vbox;
	private Map<String, Object> myFields;
	private List<BasicActorType> myActorTypes;
	private DataViewDelegate myDelegate;
	
	public DataView(Data data, DataViewDelegate delegate,  List<BasicActorType> actorTypes){
		super();
		UIHelper.setBackgroundColor(this, CustomColors.BLUE_50);
		myActorTypes = actorTypes;
		myData = data;
		myDataClassName = data.getClass().getSimpleName();
		myFields = OptionGenerator.getFields(data);
		myDelegate = delegate;
		setupViews();
		addFields();
	}

	private void addFields() {
		for(Entry<String, Object> entry: myFields.entrySet()){
			addField(entry.getKey(), entry.getValue());
		}
		
	}

	private void setupViews() {
		Label name = new Label(myDataClassName.replaceAll("Data", ""));
		name.setTextFill(CustomColors.BLUE_800);
		name.setFont(Preferences.FONT_MEDIUM_BOLD);
		name.setAlignment(Pos.CENTER);
		name.setPrefHeight(24);
		AnchorPane.setTopAnchor(name, 4.0);
		AnchorPane.setLeftAnchor(name, 4.0);
		AnchorPane.setRightAnchor(name, 4.0);
		
		vbox = new VBox();
		AnchorPane.setTopAnchor(vbox, 8.0 + name.getPrefHeight());
		AnchorPane.setLeftAnchor(vbox, 4.0);
		AnchorPane.setRightAnchor(vbox, 4.0);
		AnchorPane.setBottomAnchor(vbox, 4.0);
		
		ImageView img = new ImageView(new Image("clear_icon.png"));
		img.setFitHeight(16);
		img.setFitWidth(16);
		StackPane remove = UIHelper.buttonStack(e -> myDelegate.didClickDelete(this), 
				Optional.ofNullable(null), 
				Optional.of(img), Pos.CENTER, true);
		UIHelper.setBackgroundColor(remove, Color.TRANSPARENT);
		AnchorPane.setTopAnchor(remove, -12.0);
		AnchorPane.setRightAnchor(remove, -12.0);
		
		this.getChildren().addAll(name,vbox,remove);		
	}
	
	private void addField(String nameKey, Object value){
		Class clazz = value.getClass();
		if(clazz == double.class ||clazz == Integer.class ||clazz == Double.class ||clazz == int.class ){
			addNumberField(nameKey, value);
		}else if(clazz == BasicActorType.class){
			addActorTypeField(nameKey, (BasicActorType) value);
		}else{
			
		}
		
	}
	
	private void addActorTypeField(String nameKey, BasicActorType value) {
		AnchorPane content = new AnchorPane();

		Label fieldName = new Label(nameKey + ":");
		fieldName.setTextFill(CustomColors.BLUE_800);
		fieldName.setTextAlignment(TextAlignment.CENTER);
		AnchorPane.setLeftAnchor(fieldName, 4.0);
		AnchorPane.setTopAnchor(fieldName, 4.0);
		AnchorPane.setBottomAnchor(fieldName, 4.0);
		fieldName.setMaxWidth(80);
		content.getChildren().add(fieldName);
		
		BasicActorPicker input = new BasicActorPicker(value, myActorTypes);
		input.getBasicActorTypeProperty().addListener(e -> {
			System.out.println("toggled basic actor field input thing");
			didEditBasicActorType(input.getBasicActorTypeProperty().get(),nameKey);
			
		});
		AnchorPane.setRightAnchor(input, 4.0);
		AnchorPane.setTopAnchor(input, 4.0);
		AnchorPane.setBottomAnchor(input, 4.0);
		AnchorPane.setLeftAnchor(input, fieldName.getMaxWidth());
		
		UIHelper.setBackgroundColor(content, CustomColors.BLUE_200);
		content.getChildren().add(input);
		VBox.setMargin(content, new Insets(8.0));
		vbox.getChildren().add(content);
	}

	private void addNumberField(String nameKey, Object value){
		AnchorPane content = new AnchorPane();
		
		Label fieldName = new Label(nameKey + ":");
		fieldName.setTextFill(CustomColors.BLUE_800);
		fieldName.setTextAlignment(TextAlignment.CENTER);
		AnchorPane.setLeftAnchor(fieldName, 4.0);
		AnchorPane.setTopAnchor(fieldName, 4.0);
		AnchorPane.setBottomAnchor(fieldName, 4.0);
		fieldName.setMaxWidth(80);
		content.getChildren().add(fieldName);

		TextField field = new TextField(value.toString());
		field.setStyle("-fx-text-fill-color: #FFFFFF");
		field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.BLUE_200) + ";");
		field.setAlignment(Pos.CENTER);
		//IMPORTANT: makes field number only and also updates the value of field
		field.textProperty().addListener((e,oldVal,newValue) -> didEditField(field, nameKey, oldVal, newValue));
		AnchorPane.setRightAnchor(field, 4.0);
		AnchorPane.setTopAnchor(field, 4.0);
		AnchorPane.setBottomAnchor(field, 4.0);
		AnchorPane.setLeftAnchor(field, fieldName.getMaxWidth());
		
		UIHelper.setBackgroundColor(content, CustomColors.BLUE_200);
		content.getChildren().add(field);
		VBox.setMargin(content, new Insets(8.0));
		vbox.getChildren().add(content);
	}
	
	private void didEditField(TextField field, String varName,String oldValue, String newValue){
		 if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
			 field.setText(oldValue);
         }else{
        	 System.out.println("\n*\t*\t*\t*\t*\t*\t*\t*\t");
        	 System.out.println("\tADDING NEW DATA TO ACTORDATA");
     		this.myFields.put(varName, 
     				StringToFieldFactory.getObject(newValue, myFields.get(varName).getClass())
     				);
     		
     		Data d = DataGenerator.makeData(myDataClassName, myFields.values().toArray());
     		printMyData();
     		setMyData(d); 
     		System.out.println("*\t*\t*\t*\t*\t*\t*\t*\t\n");
         }
	}
	private void didEditBasicActorType(BasicActorType basicActorType, String varName){
   	 	System.out.println("\n*\t*\t*\t*\t*\t*\t*\t*\t");
   	 	System.out.println("\tADDING NEW DATA TO ACTORDATA");
		this.myFields.put(varName, 
				basicActorType
 				);
		Data d = DataGenerator.makeData(myDataClassName, myFields.values().toArray());
 		printMyData();
 		setMyData(d); 
 		System.out.println("*\t*\t*\t*\t*\t*\t*\t*\t\n");

	}
	
	private void printMyData(){
		System.out.println("className: "+myDataClassName +"\tfieldsClass: ");
 		for(Object o : myFields.values()){
 			System.out.println("\t-value:" + o + "\ttype:"+o.getClass().getSimpleName());
 		}
	}

	public Data getMyData() {
		return myData;
	}

	public void setMyData(Data newData) {
		myDelegate.setData(newData);
	}

	public Data getData() {
		return this.myData;
	}
	
}


