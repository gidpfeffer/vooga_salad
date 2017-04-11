package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import factories.OptionGenerator;
import gamedata.ActorData;
import gamedata.FieldData;
import gamedata.compositiongen.Data;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class ActorInfoView extends AnchorPane{
	
	/*
	 * Objects for user to input
	 * PathData
	 * BasicActorType
	 * 
	 */
	
	private GridPane myFieldsView;
	private HBox myUpgradePickerView;
	private ActorData myData;
	private static Map<String, List<FieldData>> OPTIONS = OptionGenerator.getPropertyTypesWithArgs();

	public ActorInfoView(ActorData data){
		super();
		myData = data;
		setupViews();
		for(Entry<String, List<FieldData>> e: OPTIONS.entrySet()){
			System.out.println(e.getKey() + ": ");
			for(FieldData d: e.getValue()){
				System.out.println("\t-"+d);
			}
		}
	}
	
	public ActorInfoView(){
		super();
		setupViews();
	}
	
	private void setupViews(){
		setupLayout();
	}

	private void setupOptions() {
		// TODO Auto-generated method stub
		
	}
	private void setupLayout() {
		myFieldsView = new GridPane();
		myUpgradePickerView = new HBox(8.0);
		double inset = 10.0;
		AnchorPane.setLeftAnchor(myUpgradePickerView, inset);
		AnchorPane.setRightAnchor(myUpgradePickerView, inset);
		AnchorPane.setTopAnchor(myUpgradePickerView, inset);
		myUpgradePickerView.setPrefHeight(80);
		AnchorPane.setLeftAnchor(myFieldsView, inset);
		AnchorPane.setRightAnchor(myFieldsView, inset);
		AnchorPane.setBottomAnchor(myFieldsView, inset);
		AnchorPane.setTopAnchor(myFieldsView, myUpgradePickerView.getPrefHeight() + inset);
		
		UIHelper.setBackgroundColor(myFieldsView, CustomColors.RED);
		UIHelper.setBackgroundColor(myUpgradePickerView, CustomColors.RED);
		UIHelper.setDropShadow(myFieldsView);
		UIHelper.setDropShadow(myUpgradePickerView);
		this.getChildren().addAll(myFieldsView, myUpgradePickerView);
	} 
	public void setActorData(ActorData actorData){
		if(myData == null){
			setupOptions();
		}
		else{
			//update 
		}
		myData = actorData;
		System.out.println("Selecting tower named: " + actorData.getName());
		for(Data d: actorData.getMyData()){
			System.out.println("\t-"+d.getClass().getName());
		}
	}
	
	private void setupAddNewClassButton(){
		//TODO
	}
	
	private void didClickNewClassButton(){
		//TODO
	}
	
	private void addActorDataClass(Data actorData, String name, FieldData... fields){
		//TODO
	}
	
	public void addActorUpgrade(){
		//TODO
	}
}
