package sysengineering.app;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class DrawingHelper {
	public final static Point2D getPoint(Point2D _point, Double scale) {
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		return at.transform(_point, null);
	}
}
