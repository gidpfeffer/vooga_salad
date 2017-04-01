package usecases;
import gamedata.*;

import java.util.Optional;

import util.Tuple;

/**
 * holds all the information about the map
 * it contains a 2d-array of the size of the map dimensions
 * where each object encodes the tile type and tile image
 * @author TNK
 *
 */
public class MapData {
	private TileData[][] data;
	private Tuple<Integer,Integer> dimensions;
	
	public MapData(int xDim, int yDim){
		dimensions = new Tuple<Integer, Integer>(xDim,yDim);
		data = new TileData[xDim][yDim];
	}
	
	/**
	 * adds/replaces the tiledata at the given (col, row)
	 * checks for errors relating to invalid dimensions
	 * @param tile
	 * @param col
	 * @param row
	 */
	public void addTileDataAtIndex(TileData tile, int col, int row){
		if(checkDimensions(col,row))
			data[col][row] = tile;
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
	public Optional<TileData> getTileAtIndex(int col, int row) {
		return (checkDimensions(col,row))? 
				Optional.ofNullable(data[col][row]) : Optional.empty();
	}
	
	private boolean checkDimensions(int col, int row){
		return col >= 0 && col < dimensions.x && row >=0 && row < dimensions.y;
	}
}
