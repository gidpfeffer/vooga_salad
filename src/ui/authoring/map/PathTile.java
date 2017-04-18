package ui.authoring.map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import ui.data.TileData;
import util.Index;
import util.Tuple;

public class PathTile extends StackPane{
	
	
	private ImageView myImageView;
	private Tuple<Double,Double> myTileSize;
	private TileData myTileData;
	
	//TODO create default init
	
	public PathTile(TileData tileData, double width, double height){
		super();
		myImageView = new ImageView(new Image(tileData.getImagePath()));
		myImageView.setFitWidth(width);
		myImageView.setFitHeight(height);
		myTileData = tileData;
		myTileSize = new Tuple<Double,Double>(width, height);
		this.getChildren().add(myImageView);
	}

	
	public void setImageWithPath(String path) {
		myTileData.setImagePath(path);
		myImageView.setImage(new Image(path));
	}
	
	public Tuple<Double,Double> getTileSize(){
		return myTileSize;
	}
	
	/**
	 * returns a clone of whatever the object is with the same data
	 * @param tuple 
	 */
	public PathTile clone(Index index){
		TileData newData= new TileData(myTileData.getImagePath(), index, myTileData.getTileType());
		return new PathTile(newData,myTileSize.x, myTileSize.y);
	}

	public TileData getTileData() {
		return myTileData;
	}
	
	public void setTileData(TileData newTileData){
		//TODO
	}
}
