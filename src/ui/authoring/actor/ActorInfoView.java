package ui.authoring.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import builders.DataGenerator;
import builders.OptionGenerator;
import gamedata.ActorData;
import gamedata.FieldData;
import gamedata.compositiongen.Data;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ui.general.CustomColors;
import ui.general.UIHelper;

/**
 * 
 * @author TNK
 *
 */
public class ActorInfoView extends AnchorPane implements DataViewDelegate, OptionPickerDelegate{
	
	private GridPane myGridPane;
	private static final int GRID_X_DIM = 3;
	private HBox myUpgradePickerView;
	private ActorData myActorData;
	private List<DataView> myDataViews = new ArrayList<DataView>();
	private ImageView myActorImageView;
	private ActorOptionPicker myOptionPickerView;


	
	public ActorInfoView(){
		super();
		setupViews();
	}
	
	private void setupViews(){
		setupLayout();
		this.setupAddNewClassButton();
	}

	
private void setupImageView(Image img) {
		myActorImageView = new ImageView(img);
		myActorImageView.setFitHeight(myUpgradePickerView.getPrefHeight() - 32);
		myActorImageView.setPreserveRatio(true);
		StackPane button = UIHelper.buttonStack(e -> {}, Optional.ofNullable(null), Optional.of(myActorImageView), Pos.CENTER, true);
		button.heightProperty().addListener(e -> {
		});
		HBox.setMargin(button, new Insets(8));
		this.myUpgradePickerView.getChildren().add(button);
	}

//	private void setupGridPane() {
//		int count = 0;
//		for(Entry<String, List<FieldData>> e: OPTIONS.entrySet()){
//			
//		}
//		
//	}

	private void setupOptions() {
		// TODO Auto-generated method stub
		
	}
	private void setupLayout() {
		myGridPane = new GridPane();
		myUpgradePickerView = new HBox(8.0);
		double inset = 10.0;
		AnchorPane.setLeftAnchor(myUpgradePickerView, inset);
		AnchorPane.setRightAnchor(myUpgradePickerView, inset + 2);
		AnchorPane.setTopAnchor(myUpgradePickerView, inset);
		myUpgradePickerView.setPrefHeight(80);
		AnchorPane.setLeftAnchor(myGridPane, inset);
		AnchorPane.setRightAnchor(myGridPane, inset + 2);
		AnchorPane.setBottomAnchor(myGridPane, inset);
		AnchorPane.setTopAnchor(myGridPane, (myUpgradePickerView.getPrefHeight() + 2*inset));
		//myGridPane.prefHeightProperty().bind(this.heightProperty().add();
		
		UIHelper.setBackgroundColor(myGridPane, CustomColors.BLUE_200);
		UIHelper.setBackgroundColor(myUpgradePickerView, CustomColors.BLUE_200);
		UIHelper.setDropShadow(myGridPane);
		UIHelper.setDropShadow(myUpgradePickerView);
		this.getChildren().addAll(myGridPane, myUpgradePickerView);
	} 
	public void setActorData(ActorData actorData){
		System.out.println(actorData.getName() + " : size=" + actorData.getMyData().size());
		myActorData = actorData;
		myDataViews.clear();
		myGridPane.getChildren().clear();
		if(myActorImageView == null)
			setupImageView(new Image(actorData.getImagePath()));
		myActorImageView.setImage(new Image(actorData.getImagePath()));
		for(Data d: actorData.getMyData()){
			addDataView(d);
		}
	}
	private void addDataView(Data data){
		DataView view = new DataView(data, this);
		int col = myDataViews.size()%GRID_X_DIM;
		int row = myDataViews.size() - col;
		myDataViews.add(view);
		System.out.println("col:"+col+" \trow:"+row);
		double inset = 12;
		view.prefWidthProperty().bind(this.widthProperty().divide(3).add(-inset*(GRID_X_DIM + 1)/GRID_X_DIM));
		view.prefHeightProperty().bind(view.prefWidthProperty());
		GridPane.setMargin(view, new Insets(8));
		myGridPane.add(view, col, row);
		
	}
	
	private void setupAddNewClassButton(){
		ImageView img = new ImageView(new Image("add_icon_w.png"));
		img.setFitHeight(36);
		img.setFitWidth(36);
		StackPane button = UIHelper.buttonStack(e -> didClickNewClassButton(), Optional.ofNullable(null), Optional.of(img), Pos.CENTER, true);
		AnchorPane.setBottomAnchor(button, 16.0);
		AnchorPane.setRightAnchor(button, 19.0);
		button.setPrefHeight(56);
		button.setPrefWidth(56);
		UIHelper.setBackgroundColor(button, Color.rgb(0, 0, 0, 0.4));
		this.getChildren().add(button);
	}
	
	private void didClickNewClassButton(){
		if(myOptionPickerView == null)
			myOptionPickerView = new ActorOptionPicker(this);
		AnchorPane.setBottomAnchor(myOptionPickerView, 8.0);
		AnchorPane.setTopAnchor(myOptionPickerView, 8.0);
		AnchorPane.setRightAnchor(myOptionPickerView, 8.0);
		AnchorPane.setLeftAnchor(myOptionPickerView, 8.0);
		UIHelper.addNodeToPaneWithAnimation(this, myOptionPickerView);
	}
	
	private void addActorDataClass(Data actorData, String name, FieldData... fields){
		//TODO
	}
	
	public void addActorUpgrade(){
		//TODO
	}

	/**
	 * MARK: -DataViewDelegate
	 */
	
	@Override
	public void setData(Data oldData, Data newData) {
		System.out.println("setData called from DataView");
		myActorData.getMyData().remove(oldData);
		myActorData.getMyData().add(newData);
		for(Data d: myActorData.getMyData()){
			if(d==null){
				System.out.println("found null value");
				myActorData.getMyData().remove(d);
			}
		}
	}

	@Override
	public void didClickDelete(DataView dataView) {

		ScaleTransition sc = new ScaleTransition(Duration.seconds(0.3));
		sc.setNode(dataView);
		sc.setToX(0);
		sc.setToY(0);
		sc.play();
		sc.setOnFinished(e -> {
			this.myGridPane.getChildren().remove(dataView);
			this.myActorData.getMyData().remove(dataView.getData());
			this.myDataViews.remove(dataView);
			});
	}

	/**
	 * MARK: -OptionPickerDelegate
	 */
	
	@Override
	public void didPickOptionWithData(String dataName) {
		Data d = DataGenerator.makeData(dataName+"Data");
		this.myActorData.getMyData().add(d);
		addDataView(d);
	}

	@Override
	public void didClickClose() { 
		UIHelper.removeNodeFromPaneWithAnimation(this, myOptionPickerView);
	}

}
