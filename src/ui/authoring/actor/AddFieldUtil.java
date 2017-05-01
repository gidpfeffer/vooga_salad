package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import builders.objectgen.DataGenerator;
import gamedata.ActorData;
import gamedata.StringToFieldFactory;
import gamedata.compositiongen.Data;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import types.BasicActorType;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class AddFieldUtil {

	public static void addField(Double field){
		
	}
	
	public static void addField(Integer field){
		
	}
	
	private <T extends Object> void addCategoryList(List<T> list, String nameKey) {
		AnchorPane content = new AnchorPane();
		addLabel(content, nameKey);
		
		ListSelectionView<T> input = 
				new ListSelectionView<T>(list);
		addSelectionView(content, input);
		input.getTypeList().addListener(e -> {
			System.out.println("toggled field input thing");
			didEditList(input.getTypeList().get(), nameKey);
		});
		
		format(content);
	}
	
	private void addIntegerList(List<Integer> list, String nameKey) {
		AnchorPane content = new AnchorPane();
		addLabel(content, nameKey);
		
		ListSelectionView<Integer> input = 
				new ListSelectionView<Integer>(list);
		addSelectionView(content, input);
		input.getTypeList().addListener(e -> {
			System.out.println("toggled integer field input thing");
			didEditIntegerList(input.getTypeList().get(), nameKey);
		});
		
		format(content);
	}

	
	private void addClickableTypeField(String nameKey, BasicActorType value){
		BasicPicker<BasicActorType> input = addClickableField(nameKey, value, this.myActorTypes);
		input.getTypeProperty().addListener(e -> {
			didEditClickable(input.getTypeProperty().get(),nameKey);
		});
	}
	
	private void addClickableActorField(String nameKey, Integer option){
		ActorData myActor = myGame.getOption(option);
		BasicPicker<ActorData> input = addClickableField(nameKey, myActor, 
				new ArrayList<>(myGame.getOptions().values()));
		input.getTypeProperty().addListener(e -> {
			didEditProjectileClickable(input.getTypeProperty().get(), nameKey);
		});
	}

	private <T extends Object> BasicPicker<T> addClickableField(String nameKey, T value, List<T> types
			VBox vbox) {
		AnchorPane content = new AnchorPane();

		Label fieldName = new Label(nameKey + ":");
		fieldName.setTextFill(CustomColors.BLUE_800);
		fieldName.setTextAlignment(TextAlignment.CENTER);
		AnchorPane.setLeftAnchor(fieldName, 4.0);
		AnchorPane.setTopAnchor(fieldName, 4.0);
		AnchorPane.setBottomAnchor(fieldName, 4.0);
		fieldName.setMaxWidth(80);
		content.getChildren().add(fieldName);
		
		BasicPicker <T> input = 
				new BasicPicker<T>(value, types, true);
		AnchorPane.setRightAnchor(input, 4.0);
		AnchorPane.setTopAnchor(input, 4.0);
		AnchorPane.setBottomAnchor(input, 4.0);
		AnchorPane.setLeftAnchor(input, fieldName.getMaxWidth());
		
		UIHelper.setBackgroundColor(content, CustomColors.BLUE_200);
		content.getChildren().add(input);
		VBox.setMargin(content, new Insets(8.0));
		vbox.getChildren().add(content);
		
		return input;
	}

	private void addTextField(String nameKey, Object value, VBox vbox){
		if(value.getClass().equals(String.class)){
			addStringField(nameKey, value,  vbox);
		} else if(value.getClass().equals(Double.class) || value.getClass().equals(Integer.class)){
			addNumberField(nameKey, value, vbox);
		}
	}
	
	private TextField generateField(String nameKey, Object value, VBox vbox){
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
		
		AnchorPane.setRightAnchor(field, 4.0);
		AnchorPane.setTopAnchor(field, 4.0);
		AnchorPane.setBottomAnchor(field, 4.0);
		AnchorPane.setLeftAnchor(field, fieldName.getMaxWidth());
		
		UIHelper.setBackgroundColor(content, CustomColors.BLUE_200);
		content.getChildren().add(field);
		VBox.setMargin(content, new Insets(8.0));
		vbox.getChildren().add(content);
		
		return field;
	}
	
	private void addStringField(String nameKey, Object value, VBox vbox){
		TextField field = generateField(nameKey, value, vbox);
		field.textProperty().addListener((e,oldVal,newValue) -> didEditField(field, nameKey, oldVal, newValue));
	}
	
	private void addNumberField(String nameKey, Object value, VBox vbox){
		TextField field = generateField(nameKey, value, vbox);
		field.textProperty().addListener((e,oldVal,newValue) -> didEditNumberField(field, nameKey, oldVal, newValue));
		
	}
	
}
