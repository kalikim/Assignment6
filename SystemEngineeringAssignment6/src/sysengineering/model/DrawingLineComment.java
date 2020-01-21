package sysengineering.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import sysengineering.app.DrawingHelper;





public class DrawingLineComment extends DrawingLine {

	public DrawingLineComment(DrawingRectangle rec1, DrawingComment rec2, String _name) {
		super(rec1.getCenter(), rec2.getCenter());
//		this.addPart(new EA_Text(new Point2D.Double((double) this.getCenter().getX(), (double) this.getCenter().getY()), _name, EA_Settings.STROKE_SOLID));
		this.setStroke(new BasicStroke(1.0f, // Width
				BasicStroke.CAP_ROUND, // End cap
				BasicStroke.JOIN_MITER, // Join style
				10.0f, // Miter limit
				new float[] { 3.0f, 5.0f, 10.0f, 5.0f }, // Dash pattern
				0.0f));
		this.setColor(Color.BLUE);
	}
	
	
	@Override
	public void draw(Graphics _g, boolean _all, Double _scale) {
		Graphics2D g2 = (Graphics2D) _g;
		g2.setColor(this.getColor());
		g2.setStroke(this.getStroke());
		
//		g2.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
		Point2D newStart = DrawingHelper.getPoint(start, (1.0 / _scale));
		Point2D newEnd = DrawingHelper.getPoint(end, (1.0 / _scale));
		
		g2.drawLine((int) newStart.getX(), (int) newStart.getY(), (int) newEnd.getX(), (int) newEnd.getY());
		
		
		if (_all) {
			this.drawArrow(this.getCenter(), g2);
			for(DrawingShapesPrimitive gp : this.getParts()) {
				gp.draw(_g, _all, _scale);
			}
		}
	}


	@Override
	public void translateTo(Point2D _p) {
		// TODO Auto-generated method stub
		
	}
}
