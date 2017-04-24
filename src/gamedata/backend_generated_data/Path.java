package gamedata.backend_generated_data;

import java.util.List;

import gameengine.grid.interfaces.Identifiers.Grid2D;

public class Path {
	List<Grid2D> path;
	
	public Path(List<Grid2D> path){
		this.path = path;
	}

	public List<Grid2D> getPath() {
		return path;
	}

	public void setPath(List<Grid2D> path) {
		this.path = path;
	}
	
	
}
