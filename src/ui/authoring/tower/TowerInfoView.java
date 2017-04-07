package ui.authoring.tower;

import java.util.ArrayList;
import java.util.List;

import gamedata.ActorData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.general.CustomColors;
import ui.general.UIHelper;

public class TowerInfoView extends AnchorPane{
	
	
	private VBox myEditorView;
	private HBox myUpgradePickerView;
	private List<ActorData> myData;
	
	public TowerInfoView(){
		this(new ArrayList<ActorData>());
	}
	public TowerInfoView(List<ActorData> data){
		super();
		myData = data;
		setupViews();
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
		
		UIHelper.setBackgroundColor(myEditorView, CustomColors.GREEN);
		UIHelper.setBackgroundColor(myUpgradePickerView, CustomColors.GREEN);
		UIHelper.setDropShadow(myEditorView);
		UIHelper.setDropShadow(myUpgradePickerView);
		this.getChildren().addAll(myEditorView, myUpgradePickerView);
	}
	public void setActorData(List<ActorData> data){
		//TODO
		myData = data;
		System.out.println("Selecting tower named: " + data.get(0).getName());
	}
}
