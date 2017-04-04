package ui.map;

import javafx.scene.layout.GridPane;
import ui.api_internal.MapViewAPI;
import ui.authoring.map.PathTile;
import ui.data.MapData;
import ui.general.Frame;
import ui.general.Frameable;
import util.Index;

public class MapView extends GridPane implements Frameable, MapViewAPI {
	
	private Frame myFrame;
	
	public MapView(Frame frame, MapData mapData){
		setFrame(frame);
		setMapData(mapData);
	}
	


	//MARK: -Frameable Interface Implementation
	@Override
	public void setFrame(Frame frame) {
		myFrame = frame;
		this.setLayoutX(frame.getX());
		this.setLayoutY(frame.getY());
		this.setPrefWidth(frame.getWidth());
		this.setPrefHeight(frame.getHeight());
	}

	@Override
	public Frame getFrame() {
		return myFrame;
	}
	
	public void setBackgroundColor(){
		//TODO
	}
	
	public void setGridLineVisibility(boolean b){
		//TODO
	}
	
	/**
	 * An Index is not necessary for this method to work because 
	 * PathTile already stores its index
	 *  
	 * @param tile : this PathTile object must have the correct index set
	 */
	public void setPathTile(PathTile tile){
		this.add(tile, tile.getTileData().getIndex().getX(), tile.getTileData().getIndex().getY(), 1, 1);
	}
	
	/*
	 * 
	 */
	private void setMapData(MapData data){
		double tileWidth = this.getFrame().getWidth()/data.getDimensions().x;
		double tileHeight = this.getFrame().getHeight()/data.getDimensions().y;
		for(int i=0; i<data.getDimensions().x;i++){
			for(int j=0; j<data.getDimensions().y;j++){
				data.getTileAtIndex(new Index(i,j)).ifPresent(
						tileData -> fillMapWithTile(new PathTile(tileData, tileWidth, tileHeight)));;
			}
		}
	}
	
	//This is an internal function used to initialize the tiles on the map.
	//Use setTile(PateTile tile) method to change an existing tile on a map
	private void fillMapWithTile(PathTile tile){ 
		this.add(tile, tile.getTileData().getIndex().getX(), tile.getTileData().getIndex().getY(), 1, 1);
	}
	
}
