package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gamedata.ActorData;
import gamedata.FieldData;
import gamedata.composition.Data;
import gamedata.util.OptionGenerator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class ActorInfoView extends AnchorPane{
	
	
	private VBox myEditorView;
	private HBox myUpgradePickerView;
	private List<ActorData> myData;
	private static Map<String, List<FieldData>> OPTIONS = OptionGenerator.getPropertyTypesWithArgs();
	public ActorInfoView(){
		this(new ArrayList<ActorData>());
	}
	public ActorInfoView(List<ActorData> data){
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
	
	private void setupViews(){
		setupLayout();
	}

	private void setupLayout() {
		myEditorView = new VBox();
		myUpgradePickerView = new HBox(8.0);
		double inset = 10.0;
		AnchorPane.setLeftAnchor(myUpgradePickerView, inset);
		AnchorPane.setRightAnchor(myUpgradePickerView, inset);
		AnchorPane.setTopAnchor(myUpgradePickerView, inset);
		myUpgradePickerView.setPrefHeight(80);
		AnchorPane.setLeftAnchor(myEditorView, inset);
		AnchorPane.setRightAnchor(myEditorView, inset);
		AnchorPane.setBottomAnchor(myEditorView, inset);
		AnchorPane.setTopAnchor(myEditorView, myUpgradePickerView.getPrefHeight() + inset);
		
		UIHelper.setBackgroundColor(myEditorView, CustomColors.RED);
		UIHelper.setBackgroundColor(myUpgradePickerView, CustomColors.RED);
		UIHelper.setDropShadow(myEditorView);
		UIHelper.setDropShadow(myUpgradePickerView);
		this.getChildren().addAll(myEditorView, myUpgradePickerView);
	}
	public void setActorData(List<ActorData> data){
		myData = data;
		ActorData actor = data.get(0);
		System.out.println("Selecting tower named: " + actor.getName());
		for(Data d: actor.getMyData()){
			System.out.println(d);
		}
	}
	
	private void setupAddNewFieldButton(){
		//TODO
	}
	
	private void didClickNewFieldButton(){
		//TODO
	}
	
	private void addActorDataField(String name, Class type){
		//TODO
	}
	
	public void addActorUpgrade(){
		//TODO
	}
}
