package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;




public class DrawingSquare extends DrawingRectangle {
	
	public DrawingSquare(Point2D start, String _name) {
		super(start, 70);
		this.addPart(new DrawingText(new Point2D.Double((double) this.start.getX() + 5, (double) this.start.getY() + this.height / 2), _name, new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 1.0f }, // Dash pattern
				0.0f)));
		this.addPart(new DrawingRectangle(new Point2D.Double(this.start.getX(), this.start.getY() + this.height), 70));
		this.setStroke(new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 1.0f }, // Dash pattern
				0.0f));
		this.setColor(Color.BLACK);
		this.setName(_name);
	}
	
	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		super.draw(_g, _all, _scale);
	}

}
