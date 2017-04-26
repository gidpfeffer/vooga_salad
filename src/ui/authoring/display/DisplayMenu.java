package ui.authoring.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.prism.paint.Color;

import gamedata.DisplayData;
import gamedata.GameData;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import ui.authoring.delegates.DisplayDelegate;
import ui.authoring.delegates.MenuDelegate;
import javafx.scene.image.ImageView;
import gamedata.ActorData;

public class DisplayMenu extends ListView<ImageView>{
	private DisplayData myData;
	private MenuDelegate myDelegate;
	private List<ActorData> myActors;
	private ListView<ImageView> displayList;
	public DisplayMenu(DisplayData data,DisplayDelegate d,GameData gameData){
		myData=data;
		//setupBackButton();
		this.setWidth(100);
		this.setHeight(400);
	
		this.getItems().addAll(new ArrayList<ImageView>(Arrays.asList(new ImageView[]{new ImageView("Dsad"),new ImageView("num2")})));
		
	}
	
}
