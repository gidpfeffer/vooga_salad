package ui.data;

import java.util.Optional;

import util.Index;
import util.Tuple;

/**
 * holds all the information about the map
 * it contains a 2d-array of the size of the map dimensions
 * where each object encodes the tile type and tile image
 * 
 * TODO: Figure out necessity
 * 
 * @author TNK
 *
 */
public class MapData {
	private TileData[][] data;
	private Tuple<Integer,Integer> dimensions;
	
	public MapData(int xDim, int yDim){
		dimensions = new Tuple<Integer, Integer>(xDim,yDim);
		data = new TileData[xDim][yDim];
		//init to default values
	}
	
	
	
	/**
	 * adds/replaces the tile data at the given (col, row)
	 * checks for errors relating to invalid dimensions
	 * @param tile
	 * @param col
	 * @param row
	 */
	public void addTileDataAtIndex(TileData tile){
		if(checkDimensions(tile.getIndex().getX(),tile.getIndex().getY()))
			data[tile.getIndex().getX()][tile.getIndex().getY()] = tile;
		else
			//TODO remove this; for debugging purposes only
			System.out.println("!!!INVALID INDEX!!!");
	}
	
	/**
	 * 
	 * @param col
	 * @param row
	 * @return an Optional TileData,
	 * 		Optional has null value if the col and row are invalid indexes or if the value is null
	 * 		Value is valid TileData if the row and col are valid and tiledata is present in the data array
	 */
	public Optional<TileData> getTileAtIndex(Index i) {
		return (checkDimensions(i.getX(),i.getY()))? 
				Optional.ofNullable(data[i.getX()][i.getY()]) : Optional.empty();
	}
	
	private boolean checkDimensions(int col, int row){
		return col >= 0 && col < dimensions.x && row >=0 && row < dimensions.y;
	}
	
	public Tuple<Integer,Integer> getDimensions(){
		return this.dimensions;
	}
	
}
