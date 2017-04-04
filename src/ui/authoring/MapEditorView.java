package ui.authoring;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import types.TileType;
import ui.authoring.map.PathTile;
import ui.data.MapData;
import ui.data.TileData;
import ui.general.CustomColors;
import ui.general.Frame;
import ui.general.Frameable;
import ui.general.UIView;
import ui.map.MapView;
import util.Index;
import util.Tuple;

public class MapEditorView extends UIView{
	
	//private GridPane myGridPane;
	private MapView myMapView;
	private Tuple<Integer, Integer> myDimensions;
	private final String defaultBlockImage = "grass.png";
	private final String defaultWalkImage = "dirt.png";
	private final String defaultEntryImage = "default_entry.png";
	private final String defaultExitImage = "default_exit.png";
	private double myTileHeight;
	private double myTileWidth;
	private MapData myMapData;
	private PathTile mySelectedTile; //this is the tile that will be added on mouse click
	
	/**
	 * sets up a MapEditorView with dimensions set to 20x20 by default
	 * @param frame
	 */
	public MapEditorView(Frame frame){
		this(20,20,frame);
	}
	
	public MapEditorView(int xDim, int yDim, Frame frame){
		super(frame);
		this.setBackgroundColor(CustomColors.GREEN_200);
		
		myDimensions = new Tuple<Integer,Integer>(xDim,yDim);
		myTileHeight = frame.getHeight() / yDim;
		myTileWidth = frame.getWidth() / xDim;
		mySelectedTile = new PathTile(new TileData(defaultWalkImage, new Index(0,0), TileType.WALK), myTileWidth,myTileHeight);
		myMapData = new MapData(xDim, yDim);
		setupDefaultMapData();
		myMapView = new MapView(this.getBounds(), myMapData);
		
		setupViews();
		setupMouseEvents();
	}

	private void setupViews() {
		this.getChildren().add(myMapView);
	}

	private void setupDefaultMapData() {
		for(int i=0; i < myDimensions.x;i++){
			for(int j=0; j < myDimensions.y; j++){
				TileData tile;
				Index index = new Index(i,j);
				if(j==myDimensions.y/2) //dirt
					tile = new TileData(defaultWalkImage,index, TileType.WALK);
				else //grass
					tile = new TileData(defaultBlockImage, index, TileType.BLOCK);
				myMapData.addTileDataAtIndex(tile);
			}
		}
		myMapData.addTileDataAtIndex(new TileData(defaultEntryImage, new Index(0,myDimensions.y/2), TileType.ENTRY));
		myMapData.addTileDataAtIndex(new TileData(defaultExitImage, new Index(myDimensions.x - 1,myDimensions.y/2), TileType.EXIT));

	}
	
	private void replaceTile(PathTile tile){
		myMapView.setPathTile(tile);
	}

	private void setupMouseEvents(){
		this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.getScene().setCursor(Cursor.CROSSHAIR));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> this.getScene().setCursor(Cursor.DEFAULT));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			PathTile tile = findTileAtPoint(e.getX(),e.getY());
			if(tile!= null){
				replaceTile(makeTileFromSelection(tile.getTileData().getIndex()));
			}
				
		});
	}
	
	private PathTile findTileAtPoint(double x, double y){
		Optional<Node> node = myMapView.getChildren().stream().
				filter(e -> e.getBoundsInParent().contains(x, y)).
				findFirst();
        if(node.isPresent()){
        	return (PathTile) node.get();
        }
        System.out.println("not found tile");
        return null;	
	}
	
	private PathTile makeTileFromSelection(Index index){
		return mySelectedTile.clone(index);
	}
	
	public void setBlockToAdd(PathTile tile){
		mySelectedTile = tile;
	}
	

}
