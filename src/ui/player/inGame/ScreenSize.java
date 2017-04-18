package ui.player.inGame;

public class ScreenSize {
	double top;
	double bottom;
	double left;
	double right;
	
	public ScreenSize(double top, double bottom, double left, double right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
	
	public ScreenSize() {
		this(0, 0, 0, 0);
	}
	
	public double getTop() {
		return top;
	}
	
	public double getBottom() {
		return bottom;
	}

	public double getLeft() {
		return left;
	}
	
	public double getRight() {
		return right;
	}
}
