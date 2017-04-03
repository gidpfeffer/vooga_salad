package gameengine.path;

import java.util.Collection;
import java.util.List;

/**
 * Interface for finding path and providing direction for any actor to move next basing on the tile that it
 * is currently on
 * @author Anh
 *
 */
public interface IPathFinder {
	/**
	 * PathFinder class has an instance of the map that contains all tiles and all towers,
	 * and pass recommended direction(s) correspond to the path(s) with the least towers along them.
	 * Initially when there is no towers, it will choose the path that has the minimum number of 
	 * tiles. When user adds in tower as the game goes, the algorithm may favor paths with minimum 
	 * number of towers.Note that each tile contains a set of possible directions that an Actor can go if 
	 * they are currently in that tile. This set of possible directions are based on the neighboring tiles 
	 * which are walkable tiles. 
	 * @return
	 */
	Collection<Tile> getStartTile();
	/**
	 * provides the direction(s) for any Actor that is within the currentTile to move next.
	 * @param currentTile
	 * @return The direction could be an enum type (like left, right, up, down). The direction(s) are 
	 * computed by calculating the number of towers along the path backtracking from the end 
	 * tile to the currentTile
	 */
	Collection<Directions> getDirection(Tile currentTile);
	Collection<Tile> getEndTile();
}

