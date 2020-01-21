package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;



public class DrawingComment extends DrawingRectangle {

	public DrawingComment(Point2D start, String _name) {
		this(start, 70 * 2, 70, _name);
	}
	
	public DrawingComment(Point2D start, int width, int height, String _name) {
		super(start, width, height);
		this.addPart(new DrawingText(new Point2D.Double((double) this.start.getX() + 5, (double) this.start.getY() + this.height / 2), _name, new BasicStroke( 1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f)));
		this.setStroke(new BasicStroke( 1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f));
		this.setColor(Color.BLUE);
		this.setName(_name);
	}
	
	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		super.draw(_g, _all, _scale);
	}

}
