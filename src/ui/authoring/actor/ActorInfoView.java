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

/**
 * 
 * @author TNK
 *
 */
public class ActorInfoView extends AnchorPane{
	
	private GridPane myGridPane;
	private static final int GRID_X_DIM = 3;
	private HBox myUpgradePickerView;
	private ActorData myActorData;
	private List<DataView> myDataViews = new ArrayList<DataView>();
	private static Map<String, List<FieldData>> OPTIONS = OptionGenerator.getPropertyTypesWithArgs();

	public ActorInfoView(ActorData data){
		super();
		myActorData = data;
		setupViews();
	}
	
	public ActorInfoView(){
		super();
		setupViews();
	}
	
	private void setupViews(){
		setupLayout();
		setupGridPane();
	}

	private void setupGridPane() {
		int count = 0;
		for(Entry<String, List<FieldData>> e: OPTIONS.entrySet()){
			try {
				NumericalDataView view = new NumericalDataView(e.getKey(), e.getValue());
				myDataViews.add(view);
				int columnIndex = count%GRID_X_DIM;
				int rowIndex = count - columnIndex;
				double inset = 12;
				view.prefWidthProperty().bind(this.widthProperty().divide(3).add(-inset*(GRID_X_DIM + 1)/GRID_X_DIM));
				view.prefHeightProperty().bind(view.prefWidthProperty());
				myGridPane.add(view, columnIndex, rowIndex);
				System.out.println("added new DataView");
			} catch (Exception error) {
			}
		}
		
	}

	private void setupOptions() {
		// TODO Auto-generated method stub
		
	}
	private void setupLayout() {
		myGridPane = new GridPane();
		myGridPane.setVgap(12.0);
		myGridPane.setHgap(12.0);
		myUpgradePickerView = new HBox(8.0);
		double inset = 10.0;
		AnchorPane.setLeftAnchor(myUpgradePickerView, inset);
		AnchorPane.setRightAnchor(myUpgradePickerView, inset + 2);
		AnchorPane.setTopAnchor(myUpgradePickerView, inset);
		myUpgradePickerView.setPrefHeight(80);
		AnchorPane.setLeftAnchor(myGridPane, inset);
		AnchorPane.setRightAnchor(myGridPane, inset + 2);
		AnchorPane.setBottomAnchor(myGridPane, inset);
		myGridPane.prefHeightProperty().bind(this.heightProperty().add(-1*(myUpgradePickerView.getPrefHeight() + 3*inset)));
		
		UIHelper.setBackgroundColor(myGridPane, CustomColors.AMBER);
		UIHelper.setBackgroundColor(myUpgradePickerView, CustomColors.AMBER);
		UIHelper.setDropShadow(myGridPane);
		UIHelper.setDropShadow(myUpgradePickerView);
		this.getChildren().addAll(myGridPane, myUpgradePickerView);
	} 
	public void setActorData(ActorData actorData){
		if(myActorData == null){
			setupOptions();
		}
		else{
			//update 
		}
		myActorData = actorData;
		System.out.println("Selecting tower named: " + actorData.getName());
		for(Data d: actorData.getMyData()){
			
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
