package ui.authoring.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.sun.prism.paint.Color;

import gamedata.DisplayData;
import gamedata.GameData;
import gamedata.LineageData;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.authoring.delegates.DisplayDelegate;
import ui.authoring.delegates.MenuDelegate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gamedata.ActorData;

public class DisplayMenu {
	private DisplayData myData;
	private MenuDelegate myDelegate;
	private List<ActorData> myActors;
	private Map<ActorData,ImageView>actorMap;
	private List<ImageView>imageData;
	private ListView<ImageView> displayList;
	private GameData myGameData;
	public DisplayMenu(DisplayData data,DisplayDelegate d,GameData gameData){
		myData=data;
		//setupBackButton();
		myGameData=gameData;
		displayList=new ListView<ImageView>();
		displayList.setMaxWidth(100);
		displayList.setMaxHeight(400);
		Collection<LineageData> lineage=gameData.getLineageData();
		myActors=new ArrayList<ActorData>();
		actorMap=new HashMap<ActorData,ImageView>();
		for(LineageData lineageData:lineage){
			ActorData actor=lineageData.getProgenitor();
			if (actor.getType().isPlaceable()){
			myActors.add(actor);
			addActor(actor);
			

			}
			}
		
	}
	private void addActor(ActorData actor){
		actorMap.put(actor,new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(actor.getImagePath()))));
		displayList.getItems().add(actorMap.get(actor));
	}
	public void updateDisplayList(){
		//TODO:cleanup
		
		addPlaceable();
		removeNonPlaceable();
		
	}
	private void addPlaceable(){
		
		for(LineageData lineage:myGameData.getLineageData()){
			if(lineage.getProgenitor().getType().isPlaceable()&&!actorMap.containsKey(lineage.getProgenitor())){
			addActor(lineage.getProgenitor());
			
			}
		
		}
		
	}
	private void removeNonPlaceable(){
		Collection<ActorData>actors=new HashSet<ActorData>(actorMap.keySet());
		for(ActorData actor:actors){
			System.out.println(actor.getName());
			if(!actor.getType().isPlaceable()){
				System.out.println(actor.getName());
				removeActor(actor);	
			}
		
		}
		System.out.println(displayList.getItems());
	}
	private void removeActor(ActorData actor){
		System.out.println(displayList.getItems().size());
		displayList.getItems().remove(actorMap.get(actor));
		System.out.println(displayList.getItems().size());
		actorMap.remove(actor);
	}
	public ListView<ImageView>getNode(){
		return displayList;
	}
	public void setWidth(double d){
		displayList.setMaxWidth(d);
	}
	public void setHeight(double d){
		displayList.setMaxHeight(d);
	}
	
}
