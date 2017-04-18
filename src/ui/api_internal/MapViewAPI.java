package ui.api_internal;

import ui.authoring.map.PathTile;

/**
 * Intenal API for the MapView
 * This interface defines how other UI components can interact with MapView
 * 
 * @author TNK
 *
 */
public interface MapViewAPI {
	public abstract void setBackgroundColor();
	public abstract void setGridLineVisibility(boolean b);
	public abstract void setPathTile(PathTile tile);
}
