package util;

public class Transformer {

public static double ratioToCoordinate(double ratio, double length) {
	return ratio*length;
}

public static double coordinateToRatio(double coordinate,double length) {
	return length/coordinate;
}
}
