package gamedata;

import java.util.HashMap;
import java.util.Map;

import gamedata.map.LayerData;


/**
 * MapData holds information about layers of specific polygons on which actors can be placed
 * The basic implementation is a Map of Integer to LayerData (an object holding 
 * a list of polygons defining spaces within the map)
 * 
 * Use the PlaceableData object to specify which layer an actor can be placed on
 * 
 * Example usage: Add a land layer and water layer. Then separate the tower actors 
 * into ones that can be placed on water and ones that can be placed on land.
 * @author TNK
 *
 */
public class MapData {
	
	private Map<Integer, LayerData> myLayers;
	private Integer index = 1;
	
	public MapData(){
		myLayers = new HashMap<>();
	}
	
	/**
	 * use this method to place a new layer into the mapdata
	 * index is given automatically
	 * @param layer
	 */
	public void addLayer(LayerData layer){
		myLayers.put(index, layer);
		index++;
	}
	
	/**
	 * user this add multiple layers to the map
	 * @param layers
	 */
	public void addLayers(LayerData... layers){
		for(LayerData layer: layers){
			addLayer(layer);
		}
	}
	
	/**
	 * meant to be used only with the authoring environment
	 * Purpose is to remove a layer
	 * @param layer
	 */
	public void removeLayer(LayerData layer){
		if(myLayers.containsValue(layer)){
			myLayers.remove(layer);	
			index--;
		}
	}
	
	/**
	 * returns a copy of the map that goes from index to layer
	 * @return
	 */
	public Map<Integer, LayerData> getMyLayers() {
		return new HashMap<Integer, LayerData>(myLayers);
	}



	
}

