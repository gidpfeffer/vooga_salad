package util;

public class Location {
	private Tuple<Double,Double> loc;
	
	public Location(Double col, Double row){
		setValue(col, row);
	}

	public Double getX() {
		return loc.x;
	}
	
	public Double getY() {
		return loc.y;
	}

	public void setValue(Double col, Double row) {
		this.loc = new Tuple<Double,Double>(col, row);
	}
}
