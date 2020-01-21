package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import sysengineering.app.DrawingHelper;





public class DrawingLineSolid extends DrawingLine{

	public DrawingLineSolid(DrawingRectangle rec1, DrawingRectangle rec2, String _name) {
		super(rec1.getCenter(), rec2.getCenter());
		this.addPart(new DrawingText(new Point2D.Double((double) this.getCenter().getX(), (double) this.getCenter().getY()), _name, new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f)));
		this.setStroke(new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f));
		this.setColor(Color.BLACK);
		this.setName(_name);
	}
	
	
	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		Graphics2D g2 = (Graphics2D) _g;
		g2.setColor(this.getColor());
		g2.setStroke(this.getStroke());
		
		Point2D newStart = DrawingHelper.getPoint(start, (1.0 / _scale));
		Point2D newEnd = DrawingHelper.getPoint(end, (1.0 / _scale));
		
		g2.drawLine((int) newStart.getX(), (int) newStart.getY(), (int) newEnd.getX(), (int) newEnd.getY());
		
		if (_all) {
			this.drawFullArrow(this.getCenter(), g2);
			for(DrawingShapesPrimitive gp : this.getParts()) {
				gp.draw(_g, _all, _scale);
			}
		}
	}
	
	public void updateTextPosition(Point2D newPoint) {
		for (DrawingShapesPrimitive gp : this.getParts()) {
			if (gp instanceof DrawingText) {
				DrawingText text = (DrawingText) gp;
				text.translateTo(newPoint);
			}
		}
	}


	@Override
	public void translateTo(Point2D _p) {
		// TODO Auto-generated method stub
		
	}
	
}
